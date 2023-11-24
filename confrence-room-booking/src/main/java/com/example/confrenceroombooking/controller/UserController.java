package com.example.confrenceroombooking.controller;

import com.example.confrenceroombooking.entity.Booking;
import com.example.confrenceroombooking.exception.UserHandledException;
import com.example.confrenceroombooking.model.BookRoomInputRequest;
import com.example.confrenceroombooking.service.BookingService;
import com.example.confrenceroombooking.service.ConferenceRoomService;
import com.example.confrenceroombooking.service.UserService;
import com.example.confrenceroombooking.entity.ConferenceRoom;
import com.example.confrenceroombooking.entity.User;
import com.example.confrenceroombooking.model.InputRequest;
import com.example.confrenceroombooking.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private ConferenceRoomService conferenceRoomService;
    private BookingService bookingService;

    @Autowired
    public UserController(UserService userService, ConferenceRoomService conferenceRoomService,
                          BookingService bookingService) {
        this.userService = userService;
        this.conferenceRoomService = conferenceRoomService;
        this.bookingService = bookingService;
    }

    //register new user
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody Map<?, ?> inputMap) {
        InputRequest inputRequest = (InputRequest) Utility.convertMapToObj(inputMap, InputRequest.class);
        User user = userService.createUser((User) inputRequest.getRequestPayload().getRequestUser());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // list all conference room in  any building
    @GetMapping("/listconferenceroom")
    public ResponseEntity<Object> getConferenceRoom(@RequestParam String buildingName) throws UserHandledException {
        List<ConferenceRoom> conferenceRoomList = conferenceRoomService.listAllRoom(buildingName);
        return new ResponseEntity<>(conferenceRoomList, HttpStatus.OK);
    }

    // book room for a give slot
    @PostMapping("/bookroom")
    public ResponseEntity<Object> bookroom(@RequestBody BookRoomInputRequest bookRoomInputRequest) throws UserHandledException {
        Booking roomBook = bookingService.bookRoomForSlot(bookRoomInputRequest);
        return new ResponseEntity<>(roomBook, HttpStatus.OK);
    }

    // cancel existing booking for particular slot
    @PostMapping("/cancelbooking")
    public ResponseEntity<Object> cancelBooking(@RequestBody Long bookingId) throws UserHandledException {
        bookingService.cancelBooking(bookingId);
        return new ResponseEntity<>("Booking Cancelled Successfully", HttpStatus.OK);
    }


    // list all bookings by a user
    @PostMapping("/listroombyuser")
    public ResponseEntity<Object> getAllRoomsByUser(@RequestBody User user) {
        List<Booking> bookingList = bookingService.findBookingByUser(user);
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    // filter


    // view room capacity
    @PostMapping("/roomCapacity")
    public ResponseEntity<Object> getRoomCapacity(@RequestBody ConferenceRoom room) throws UserHandledException {
        Integer roomCapacity = conferenceRoomService.findRoomCapacity(room);
        Map<String, Integer> resp = new HashMap<>();
        resp.put(room.getRoomNumber(), roomCapacity);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
