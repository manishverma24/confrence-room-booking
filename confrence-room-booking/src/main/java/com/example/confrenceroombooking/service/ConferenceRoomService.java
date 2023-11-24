package com.example.confrenceroombooking.service;

import com.example.confrenceroombooking.entity.ConferenceRoom;
import com.example.confrenceroombooking.exception.UserHandledException;

import java.util.List;

public interface ConferenceRoomService {
    List<ConferenceRoom> listAllRoom(String buildingName) throws UserHandledException;

    Integer findRoomCapacity(ConferenceRoom room) throws UserHandledException;
}
