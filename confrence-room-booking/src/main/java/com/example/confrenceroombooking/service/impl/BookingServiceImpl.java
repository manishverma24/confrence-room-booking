package com.example.confrenceroombooking.service.impl;

import com.example.confrenceroombooking.dto.BookingRepository;
import com.example.confrenceroombooking.entity.Booking;
import com.example.confrenceroombooking.entity.ConferenceRoom;
import com.example.confrenceroombooking.entity.User;
import com.example.confrenceroombooking.exception.UserHandledException;
import com.example.confrenceroombooking.model.BookRoomInputRequest;
import com.example.confrenceroombooking.service.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public Booking bookRoomForSlot(BookRoomInputRequest bookRoomInputRequest) throws UserHandledException {
        if (!isRoomAvailableForSlot(bookRoomInputRequest.getRoom(), bookRoomInputRequest.getStartTime(),
                bookRoomInputRequest.getEndTime())) {
            throw new UserHandledException("room is not available for requested slot", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Booking booking = new Booking();
        booking.setUser(bookRoomInputRequest.getUser());
        booking.setRoom(bookRoomInputRequest.getRoom());
        booking.setStartTime(bookRoomInputRequest.getStartTime());
        booking.setEndTime(booking.getEndTime());
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findBookingByUser(User user) {
        if (Objects.nonNull(user)) {
            return bookingRepository.findByUser(user);
        }
        return Collections.emptyList();
    }

    @Override
    public void cancelBooking(Long bookingId) throws UserHandledException {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            bookingRepository.delete(booking);
        } else {
            throw new UserHandledException("Booking not found with ID: "+ bookingId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean isRoomAvailableForSlot(ConferenceRoom room, LocalDateTime startTime,
                                           LocalDateTime endTime) {
        List<Booking> bookingList = bookingRepository.findByRoomAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                room, startTime, endTime);
        return bookingList.isEmpty();
    }
}
