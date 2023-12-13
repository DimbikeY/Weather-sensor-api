package ru.dolmatov.weathersensorapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dolmatov.weathersensorapi.models.Measurement;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
}
