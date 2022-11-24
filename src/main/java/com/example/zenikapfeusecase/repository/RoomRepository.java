package com.example.zenikapfeusecase.repository;

import com.example.zenikapfeusecase.entity.Equipment;
import com.example.zenikapfeusecase.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {


    @Query("select c from Room c where c not  in :notAllowedRooms")
    List<Room> findAllowedRooms(List<Room> notAllowedRooms);

    @Query("select room.roomMaterial from Room room where room.roomName = :roomName  ")
    List<Equipment> getEquipmentInARoom(String roomName);

}
