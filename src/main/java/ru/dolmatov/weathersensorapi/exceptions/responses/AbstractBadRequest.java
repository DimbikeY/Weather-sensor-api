package ru.dolmatov.weathersensorapi.exceptions.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractBadRequest {

    private String messageToSend;

    public AbstractBadRequest(String message) {
        this.messageToSend = message;
    }
}
