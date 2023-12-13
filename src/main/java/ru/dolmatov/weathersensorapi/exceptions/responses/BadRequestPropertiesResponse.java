package ru.dolmatov.weathersensorapi.exceptions.responses;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestPropertiesResponse extends AbstractBadRequest {

    private long timestamp;

    public BadRequestPropertiesResponse(String message, long timestamp) {
        super(message);
        this.timestamp = timestamp;
    }
}
