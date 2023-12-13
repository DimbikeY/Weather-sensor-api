package ru.dolmatov.weathersensorapi.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dolmatov.weathersensorapi.models.Measurement;
import ru.dolmatov.weathersensorapi.models.Sensor;
import ru.dolmatov.weathersensorapi.repositories.MeasurementsRepository;
import ru.dolmatov.weathersensorapi.request.dto.MeasurementRequestDTO;

import java.time.LocalDateTime;

@Service
public class MeasurementsService {

    private final ModelMapper mapper;
    private final MeasurementsRepository measurementsService;
    private final SensorsService sensorsService;

    public MeasurementsService(ModelMapper mapper, MeasurementsRepository measurementsService, SensorsService sensorsService) {
        this.mapper = mapper;
        this.measurementsService = measurementsService;
        this.sensorsService = sensorsService;
    }

    @Transactional
    public void saveNewMeasurement(MeasurementRequestDTO registrationRequestDTO) {
        Measurement measurementToSave = transformFromDTOToModel(registrationRequestDTO);

        Sensor sensorToAddToMeasurement = sensorsService.findSensorByName(measurementToSave.getSensor().getName()).get();

        enrichMeasurementModel(measurementToSave, sensorToAddToMeasurement);

        measurementsService.save(measurementToSave);
    }

    private static void enrichMeasurementModel(Measurement measurementToSave, Sensor sensorToAddToMeasurement) {
        measurementToSave.setDate(LocalDateTime.now());
        measurementToSave.setSensor(sensorToAddToMeasurement);
    }

    public Measurement transformFromDTOToModel(MeasurementRequestDTO registrationRequestDTO) {
        return mapper.map(registrationRequestDTO, Measurement.class);
    }
}
