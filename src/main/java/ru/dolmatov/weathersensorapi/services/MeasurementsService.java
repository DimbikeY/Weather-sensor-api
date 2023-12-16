package ru.dolmatov.weathersensorapi.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dolmatov.weathersensorapi.models.Measurement;
import ru.dolmatov.weathersensorapi.models.Sensor;
import ru.dolmatov.weathersensorapi.repositories.MeasurementsRepository;
import ru.dolmatov.weathersensorapi.request.dto.MeasurementRequestDTO;
import ru.dolmatov.weathersensorapi.response.dto.MeasurementResponseDTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MeasurementsService {

    private final ModelMapper mapper;
    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    public MeasurementsService(ModelMapper mapper, MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.mapper = mapper;
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    @Transactional
    public void saveNewMeasurement(MeasurementRequestDTO registrationRequestDTO) {
        Measurement measurementToSave = transformFromDTOToModel(registrationRequestDTO);

        Sensor sensorToAddToMeasurement = sensorsService.findSensorByName(measurementToSave.getSensor().getName()).get();

        enrichMeasurementModel(measurementToSave, sensorToAddToMeasurement);

        measurementsRepository.save(measurementToSave);
    }

    private static void enrichMeasurementModel(Measurement measurementToSave, Sensor sensorToAddToMeasurement) {
        measurementToSave.setTimeMeasurement(LocalDateTime.now());
        measurementToSave.setSensor(sensorToAddToMeasurement);
    }

    public Measurement transformFromDTOToModel(MeasurementRequestDTO registrationRequestDTO) {
        return mapper.map(registrationRequestDTO, Measurement.class);
    }

    public MeasurementResponseDTO transformFromModelToDTO(Measurement measurementToTransform) {
        return mapper.map(measurementToTransform, MeasurementResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public List<MeasurementResponseDTO> findAllMeasurements() {
        System.out.println("Before");
        List<Measurement> measurements = measurementsRepository.findAll();
        System.out.println(measurements.size());
        return measurements.stream().map(this::transformFromModelToDTO).toList();
    }

    public Map<String, Integer> countRainyDays() {
        Map<String, Integer> mapToSend = new HashMap<>();
        List<Measurement> rainyDaysListMeasurements = measurementsRepository.findAllByIsRainingIsTrue();
        mapToSend.put("Count", rainyDaysListMeasurements.size());

        return mapToSend;
    }
}
