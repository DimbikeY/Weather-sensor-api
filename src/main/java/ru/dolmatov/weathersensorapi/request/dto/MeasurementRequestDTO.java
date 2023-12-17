package ru.dolmatov.weathersensorapi.request.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dolmatov.weathersensorapi.models.Sensor;

@Schema(description = "Contains temperature, time, if it rains info, sensor")
@Getter
@Setter
@NoArgsConstructor
public class MeasurementRequestDTO {

    @NotNull(message = "Must be any value")
    @Min(value = -100, message = "Min must be higher than -100")
    @Max(value = 100, message = "Max must be not higher than 100")
    private float temperature;

    @Schema(description = "Show if it rains", example = "TRUE/FALSE")
    @NotNull(message = "Must be any value")
    private boolean isRaining;

    @Schema(description = "Sensor that has been used for measurement")
    @NotNull(message = "sensor object can't be null")
    private SensorRequestDTO sensor;
}
