package com.example.zenikapfeusecase.repository;

import com.example.zenikapfeusecase.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation , Long> {

    @Query("select  r from  Reservation r   where r.date between  :reservationDateMinusOneHourForInfection and  :reservationDatePlusOneHourForInfection ")
    List<Reservation> findAllReservationBetweenTwoGivenDates(LocalDateTime reservationDateMinusOneHourForInfection , LocalDateTime reservationDatePlusOneHourForInfection);

}
