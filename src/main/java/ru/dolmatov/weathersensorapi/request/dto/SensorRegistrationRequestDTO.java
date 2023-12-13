package ru.dolmatov.weathersensorapi.request.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SensorRegistrationRequestDTO {

    @NotNull(message = "The name must have any names")
    @Size(min = 3, max = 30, message = "The size should be between 3 and 30")
    private String name;
}
