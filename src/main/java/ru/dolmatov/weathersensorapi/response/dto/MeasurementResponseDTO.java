package ru.dolmatov.weathersensorapi.response.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dolmatov.weathersensorapi.request.dto.SensorRequestDTO;

@Schema(description = "Contains temperature, the info if it rains, sensor")
@Getter
@Setter
@NoArgsConstructor
public class MeasurementResponseDTO {

    @NotNull(message = "Must be any value")
    @Min(value = -100, message = "Min must be higher than -100")
    @Max(value = 100, message = "Max must be not higher than 100")
    private float temperature;

    @NotNull(message = "Must be any value")
    private boolean isRaining;

    @NotNull(message = "sensor object can't be null")
    private SensorRequestDTO sensor;
}
