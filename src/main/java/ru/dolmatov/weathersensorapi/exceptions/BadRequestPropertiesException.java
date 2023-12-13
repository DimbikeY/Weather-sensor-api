package ru.dolmatov.weathersensorapi.exceptions;

public class BadRequestPropertiesException extends RuntimeException{

    public BadRequestPropertiesException(String msg){
        super(msg);
    }
}
