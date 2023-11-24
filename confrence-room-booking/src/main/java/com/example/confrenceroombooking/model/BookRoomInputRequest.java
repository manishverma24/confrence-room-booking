package com.example.confrenceroombooking.model;

import com.example.confrenceroombooking.entity.ConferenceRoom;
import com.example.confrenceroombooking.entity.User;
import java.time.LocalDateTime;

public class BookRoomInputRequest {
    private Header header;
    private User user;
    private ConferenceRoom room;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public BookRoomInputRequest(User user, ConferenceRoom room, LocalDateTime startTime, LocalDateTime endTime) {
        this.user = user;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ConferenceRoom getRoom() {
        return room;
    }

    public void setRoom(ConferenceRoom room) {
        this.room = room;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
