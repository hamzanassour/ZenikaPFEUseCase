package com.example.zenikapfeusecase.mapper;

import com.example.zenikapfeusecase.dto.request.ReservationRequest;
import com.example.zenikapfeusecase.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
            @Mapping(target="meetMType", source="meetType")
            @Mapping(target="date", source="reservationDate" , dateFormat = "yyyy-MM-dd HH:mm")
            Reservation reservationRequestToReservationEntity(ReservationRequest reservationRequest);
}
