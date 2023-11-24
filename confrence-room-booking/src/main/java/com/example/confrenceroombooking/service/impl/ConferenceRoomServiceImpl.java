package com.example.confrenceroombooking.service.impl;

import com.example.confrenceroombooking.dto.ConferenceRoomRepository;
import com.example.confrenceroombooking.entity.ConferenceRoom;
import com.example.confrenceroombooking.exception.UserHandledException;
import com.example.confrenceroombooking.service.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ConferenceRoomServiceImpl implements ConferenceRoomService {
    private ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    public ConferenceRoomServiceImpl(ConferenceRoomRepository conferenceRoomRepository) {
        this.conferenceRoomRepository = conferenceRoomRepository;
    }

    @Override
    public List<ConferenceRoom> listAllRoom(String buildingName) throws UserHandledException {
        List<ConferenceRoom> conferenceRoomList = conferenceRoomRepository.findByBuilding(buildingName);
        if (conferenceRoomList.isEmpty()) {
            throw new UserHandledException("No room found in building ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return conferenceRoomList;
    }

    @Override
    public Integer findRoomCapacity(ConferenceRoom room) throws UserHandledException {
        Optional<ConferenceRoom> conferenceRoom = conferenceRoomRepository.findById(room.getId());
        if (conferenceRoom.isPresent()) {
            return conferenceRoom.get().getCapacity();
        }
        throw new UserHandledException("No room capacity find for request roomId "+room.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
