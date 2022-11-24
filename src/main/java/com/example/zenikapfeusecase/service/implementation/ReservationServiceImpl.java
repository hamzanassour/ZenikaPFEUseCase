package com.example.zenikapfeusecase.service.implementation;

import com.example.zenikapfeusecase.entity.Reservation;
import com.example.zenikapfeusecase.entity.Room;
import com.example.zenikapfeusecase.exception.CapacityException;
import com.example.zenikapfeusecase.exception.EquipmentException;
import com.example.zenikapfeusecase.exception.NoEmptyRoomException;
import com.example.zenikapfeusecase.repository.ReservationRepository;
import com.example.zenikapfeusecase.service.ReservationService;
import com.example.zenikapfeusecase.service.RoomService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository ;
    private RoomService roomService ;

    @Override
    public String reservation(Reservation reservation) throws NoEmptyRoomException, CapacityException, EquipmentException {
        // as we know rooms need one hour to be infected so for example if i want to reserve a Room in 10h --> rooms reserved from 9h(to be infected from 10h to 11h) to 11h(from 10 --> 11 is the time for infection) are not allowed
        // in general if x time of reservation rooms reserved from at  x-1 , x  , x+1 not allowed
        List<Room> eligibleRoomsForReservation =  roomService.getRoomsEligibleForReservation(reservation.getDate ().minusHours (1) , reservation.getDate ().plusHours (1));
        if (eligibleRoomsForReservation.isEmpty ()){
            throw new NoEmptyRoomException ("there is no empty room for now please check later ");
        }
        else {
            // check of capacity (70 % in covid /!\ )
            eligibleRoomsForReservation =  eligibleRoomsForReservation.stream ().filter (salle -> roomService.checkCapacity (reservation.getNbrOfPearson () , salle) ).collect(Collectors.toList());
            if (eligibleRoomsForReservation.isEmpty ()){
                throw  new CapacityException ("oops we don't have a Room with capacity demanded check later ");
            }
            else {
                // check by MeetType
                eligibleRoomsForReservation =  roomService.getRoomsThatCanHandleSpecificMeeting (reservation.getMeetMType (), eligibleRoomsForReservation);
                if (eligibleRoomsForReservation.isEmpty ()){
                    throw  new EquipmentException ("Rooms available for now don't have all of  equipments that you need  ");
                }
            }
        }
        reservation.setRoom (eligibleRoomsForReservation.get (0));
        reservationRepository.save (reservation);
        return "you  reserved Room  "+reservation.getRoom ().getRoomName ();
    }
}
