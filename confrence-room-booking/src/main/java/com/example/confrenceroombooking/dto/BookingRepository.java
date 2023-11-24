package com.example.confrenceroombooking.dto;

import com.example.confrenceroombooking.entity.Booking;
import com.example.confrenceroombooking.entity.ConferenceRoom;
import com.example.confrenceroombooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    List<Booking> findByRoom(ConferenceRoom room);
    List<Booking> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Booking> findByRoomAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(ConferenceRoom room,
                                                LocalDateTime startTime, LocalDateTime endTime);

}
