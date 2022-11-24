package com.example.zenikapfeusecase.controller;

import com.example.zenikapfeusecase.dto.request.ReservationRequest;
import com.example.zenikapfeusecase.entity.Reservation;
import com.example.zenikapfeusecase.enumerations.MType;
import com.example.zenikapfeusecase.exception.CapacityException;
import com.example.zenikapfeusecase.exception.EquipmentException;
import com.example.zenikapfeusecase.exception.InputException;
import com.example.zenikapfeusecase.exception.NoEmptyRoomException;
import com.example.zenikapfeusecase.mapper.ReservationMapper;
import com.example.zenikapfeusecase.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class ReservationController {

   private ReservationService reservationService;
   private ReservationMapper reservationMapper ;

    @PostMapping("/")
    public ResponseEntity<String> reservation(@Valid @RequestBody  ReservationRequest reservationRequest , BindingResult bindingResult) throws CapacityException, EquipmentException, NoEmptyRoomException, InputException {

        if (bindingResult.hasErrors ()){
            throw new InputException (bindingResult.getAllErrors ().toString ());
        }
        //Reservation reservation = new Reservation ();
        //reservation.setDate (LocalDateTime.of (2022 , 11,23,10,00));
        //reservation.setMeetMType (MType.VC);
        //reservation.setNbrOfPearson (10);

        Reservation reservation = reservationMapper.reservationRequestToReservationEntity (reservationRequest);

       return ResponseEntity.status (HttpStatus.CREATED).body (reservationService.reservation (reservation));
    }



}
