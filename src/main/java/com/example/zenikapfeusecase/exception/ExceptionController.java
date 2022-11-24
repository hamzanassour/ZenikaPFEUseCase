package com.example.zenikapfeusecase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(InputException.class)
    public ResponseEntity<Object> handleBusinessException(InputException exception){
        return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (exception.getMessage ());
    }
    // the same for the other exceptions ...


}
