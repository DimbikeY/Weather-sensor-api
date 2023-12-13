package ru.dolmatov.weathersensorapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dolmatov.weathersensorapi.models.Sensor;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {

}
