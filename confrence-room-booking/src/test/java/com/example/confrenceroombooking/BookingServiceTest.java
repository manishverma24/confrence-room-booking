package com.example.confrenceroombooking;

import com.example.confrenceroombooking.dto.ConferenceRoomRepository;
import com.example.confrenceroombooking.dto.UserRepository;
import com.example.confrenceroombooking.entity.Booking;
import com.example.confrenceroombooking.entity.ConferenceRoom;
import com.example.confrenceroombooking.entity.User;
import com.example.confrenceroombooking.exception.UserHandledException;
import com.example.confrenceroombooking.model.BookRoomInputRequest;
import com.example.confrenceroombooking.service.BookingService;
import com.example.confrenceroombooking.service.ConferenceRoomService;
import com.example.confrenceroombooking.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookingServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    private BookingService bookingService;

    @Test
    public void testBookRoomForSlot() throws UserHandledException {
        // create a user
        User user = new User();
        user.setUserId("userId");
        user.setName("userName");
        userRepository.save(user);

        ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setBuilding("101");
        conferenceRoom.setBuilding("Building A");
        conferenceRoom.setFloor(1);
        conferenceRoom.setCapacity(25);
        conferenceRoomRepository.save(conferenceRoom);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(2);

        BookRoomInputRequest bookRoomInputRequest = new BookRoomInputRequest(user, conferenceRoom, startTime, endTime);

        Booking booking = bookingService.bookRoomForSlot(bookRoomInputRequest);

        assertNotNull(booking.getId());
        assertEquals(user.getId(), booking.getUser().getId());
        assertEquals(conferenceRoom.getId(), booking.getRoom().getId());
        assertEquals(startTime, booking.getStartTime());
        assertEquals(endTime, booking.getEndTime());
    }
}
