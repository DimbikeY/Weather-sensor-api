package ru.dolmatov.weathersensorapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
@Getter
@Setter
@NoArgsConstructor
public class Measurement {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "temperature")
    @NotNull(message = "Must be any value")
    @Min(value = -100, message = "Min must be higher than -100")
    @Max(value = 100, message = "Max must be not higher than 100")
    private float temperature;

    @Column(name = "is_raining")
    @NotNull(message = "Must be any value")
    private boolean isRaining;

    @Column(name = "time_measurement")
    @NotNull(message = "Date must be not null")
    private LocalDateTime timeMeasurement;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
}
