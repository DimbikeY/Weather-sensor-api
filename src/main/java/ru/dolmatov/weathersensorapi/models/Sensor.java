package ru.dolmatov.weathersensorapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "sensor")
@Getter
@Setter
@NoArgsConstructor
public class Sensor {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull(message = "The name must have any names")
    @Size(min = 3, max = 30, message = "The size should be between 3 and 30")
    private String name;

    @OneToMany(mappedBy = "sensorId")
    private List<Measurement> measurements;
}
