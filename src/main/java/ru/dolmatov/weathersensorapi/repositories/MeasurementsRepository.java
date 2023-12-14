package ru.dolmatov.weathersensorapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.dolmatov.weathersensorapi.models.Measurement;

import java.util.List;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {

    @Query("select m from Measurement m left join fetch m.sensor")
    List<Measurement> findAll();

    List<Measurement> findAllByIsRainingIsTrue();
}
