package com.example.confrenceroombooking.service;

import com.example.confrenceroombooking.entity.Booking;
import com.example.confrenceroombooking.entity.User;
import com.example.confrenceroombooking.exception.UserHandledException;
import com.example.confrenceroombooking.model.BookRoomInputRequest;

import java.util.List;

public interface BookingService {
    Booking bookRoomForSlot(BookRoomInputRequest inputRequest) throws UserHandledException;

    List<Booking> findBookingByUser(User user);

    void cancelBooking(Long bookingId) throws UserHandledException;
}
