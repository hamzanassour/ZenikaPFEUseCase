package com.example.zenikapfeusecase.entity;

import com.example.zenikapfeusecase.enumerations.MType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private LocalDateTime date;
    private long nbrOfPearson;

    @ManyToOne
    private Room room ;

    @Enumerated(EnumType.STRING)
    private MType meetMType;

}
