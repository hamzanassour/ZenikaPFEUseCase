package com.example.zenikapfeusecase.service;

import com.example.zenikapfeusecase.entity.Equipment;
import com.example.zenikapfeusecase.entity.Room;
import com.example.zenikapfeusecase.enumerations.MType;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {

        boolean  checkCapacity(long nbrOfPearsonInMeeting ,  Room room);
        List<Room> getAllowedRoomsForReservation(List<Room> notAllowedRooms);
        List<Equipment> getAllEquipmentInSpecificRoom(Room room);
        List<Room> getRoomsEligibleForReservation(LocalDateTime first , LocalDateTime end);
        List<Room> getRoomsThatContainDemandedEquipment(List<Equipment> equipment , List<Room> rooms);
        List<Room> getRoomsThatCanHandleSpecificMeeting(MType meetType, List<Room> roomsWithCapacity);

}
