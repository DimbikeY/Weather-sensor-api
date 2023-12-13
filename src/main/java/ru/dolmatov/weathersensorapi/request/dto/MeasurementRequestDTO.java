package ru.dolmatov.weathersensorapi.request.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dolmatov.weathersensorapi.models.Sensor;

@Getter
@Setter
@NoArgsConstructor
public class MeasurementRequestDTO {

    @NotNull(message = "Must be any value")
    @Min(value = -100, message = "Min must be higher than -100")
    @Max(value = 100, message = "Max must be not higher than 100")
    private float value;

    @NotNull(message = "Must be any value")
    private boolean isRaining;

    @NotNull(message = "sensor object can't be null")
    private SensorRequestDTO sensor;
}
