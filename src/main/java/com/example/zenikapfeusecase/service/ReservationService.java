package com.example.zenikapfeusecase.service;

import com.example.zenikapfeusecase.entity.Reservation;
import com.example.zenikapfeusecase.exception.CapacityException;
import com.example.zenikapfeusecase.exception.EquipmentException;
import com.example.zenikapfeusecase.exception.NoEmptyRoomException;


public interface ReservationService {

    String reservation(Reservation reservation) throws NoEmptyRoomException, CapacityException, EquipmentException;

}
