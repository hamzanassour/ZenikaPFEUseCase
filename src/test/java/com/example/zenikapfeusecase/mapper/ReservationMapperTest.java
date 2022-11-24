package com.example.zenikapfeusecase.mapper;

import com.example.zenikapfeusecase.dto.request.ReservationRequest;
import com.example.zenikapfeusecase.entity.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReservationMapperTest {

    @Autowired
    ReservationMapper reservationMapper;

    @Test
    public void should_return_reservation_from_reservation_request(){
        //given
        ReservationRequest reservationDto=new ReservationRequest();
        reservationDto.setNbrOfPearson (10);
        reservationDto.setReservationDate ("2022-11-23 10:00");
        reservationDto.setMeetType ("VC");
        //when
        Reservation reservationEntity =reservationMapper.reservationRequestToReservationEntity (reservationDto);
        //then
        assertEquals(reservationDto.getNbrOfPearson (), reservationEntity.getNbrOfPearson ());
        assertEquals("VC",reservationEntity.getMeetMType ().toString ());

    }

}
