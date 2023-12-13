package ru.dolmatov.weathersensorapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.dolmatov.weathersensorapi.exceptions.BadRequestPropertiesException;
import ru.dolmatov.weathersensorapi.exceptions.responses.BadRequestPropertiesResponse;

@ControllerAdvice
public class GlobalAdviserExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<BadRequestPropertiesResponse> sendBadRequestPropertiesRespond(BadRequestPropertiesException e) {
        return new ResponseEntity<>(new BadRequestPropertiesResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }
}
