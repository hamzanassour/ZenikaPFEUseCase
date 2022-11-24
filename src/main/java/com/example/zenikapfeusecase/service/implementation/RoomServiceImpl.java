package com.example.zenikapfeusecase.service.implementation;

import com.example.zenikapfeusecase.entity.Equipment;
import com.example.zenikapfeusecase.entity.Reservation;
import com.example.zenikapfeusecase.entity.Room;
import com.example.zenikapfeusecase.enumerations.MType;
import com.example.zenikapfeusecase.repository.ReservationRepository;
import com.example.zenikapfeusecase.repository.RoomRepository;
import com.example.zenikapfeusecase.service.ReservationService;
import com.example.zenikapfeusecase.service.RoomService;
import com.example.zenikapfeusecase.utils.Helper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {


    RoomRepository roomRepository;
    ReservationRepository reservationRepository ;


    @Override
    public boolean checkCapacity(long nbrOfPearsonInMeeting, Room room) {
        long  nbrAllowedInARoomWithCovid = room.getRoomCapacity () - room.getRoomCapacity () * 30 / 100 ;
        return nbrOfPearsonInMeeting < nbrAllowedInARoomWithCovid ;
    }

    @Override
    public List<Room> getAllowedRoomsForReservation(List<Room> notAllowedRooms) {
        return roomRepository.findAllowedRooms (notAllowedRooms);
    }

    @Override
    public List<Equipment> getAllEquipmentInSpecificRoom(Room room) {
        return roomRepository.getEquipmentInARoom (room.getRoomName ());
    }

    @Override
    public List<Room> getRoomsEligibleForReservation(LocalDateTime first, LocalDateTime end) {
        List<Reservation> reservationList = reservationRepository.findAllReservationBetweenTwoGivenDates ( first ,  end);
        List<Room> notAllowedRooms = reservationList.stream ().map (reservation1 -> reservation1.getRoom ()).collect(Collectors.toList());
        return  getAllowedRoomsForReservation (notAllowedRooms);
    }

    @Override
    public List<Room> getRoomsThatContainDemandedEquipment(List<Equipment> equipment, List<Room> rooms) {
        List<Room> RoomsReadyForReservation = new ArrayList<> ();
        for (Room room : rooms){
            if ( getAllEquipmentInSpecificRoom (room).containsAll (equipment)){
                RoomsReadyForReservation.add (room);
            }
        }
        return  RoomsReadyForReservation ;
    }
     public List<Room> getRoomsThatCanHandleSpecificMeeting(MType meetType, List<Room> roomsWithCapacity) {
        if(meetType == MType.RS){
            // because RS meeting type don't need any equipment
            return roomsWithCapacity;
        }
        else {
            List<Equipment> equipmentsNeededForTheMeeting = Helper.getEquipmentsNeededForEachMeeting (meetType);
            // get Equipment in each Room of the allowed rooms
            return getRoomsThatContainDemandedEquipment (equipmentsNeededForTheMeeting, roomsWithCapacity);
        }
    }
}
