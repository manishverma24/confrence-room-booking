package com.example.confrenceroombooking.dto;

import com.example.confrenceroombooking.entity.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {
    List<ConferenceRoom> findByBuilding(String building);
    List<ConferenceRoom> findByFloorAndBuilding(int floor, String building);
}
