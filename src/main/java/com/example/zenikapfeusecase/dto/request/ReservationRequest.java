package com.example.zenikapfeusecase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    @NotNull
    @Pattern (regexp = "^(VC|RC|SPEC|RS)$")
    private String meetType;
    @NotNull
    @Min (2)
    private long nbrOfPearson ;
    @NotNull

    private String  reservationDate ;

}
