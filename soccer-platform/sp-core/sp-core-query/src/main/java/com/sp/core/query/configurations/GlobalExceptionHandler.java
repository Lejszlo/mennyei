package com.sp.core.query.configurations;

import com.sp.core.backend.exception.ClubNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lejsz on 2017. 04. 23..
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(value = ClubNotFoundException.class)
    public String handleBaseException(ClubNotFoundException e){
        return e.getMessage();
    }
}
