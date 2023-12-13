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
import java.util.List;

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
        measurementToSave.setDate(LocalDateTime.now());
        measurementToSave.setSensor(sensorToAddToMeasurement);
    }

    public Measurement transformFromDTOToModel(MeasurementRequestDTO registrationRequestDTO) {
        return mapper.map(registrationRequestDTO, Measurement.class);
    }
    public MeasurementResponseDTO transformFromModelToDTO(Measurement measurementToTransform){
        return mapper.map(measurementToTransform, MeasurementResponseDTO.class);
    }

    public List<MeasurementResponseDTO> findAllMeasurements() {
        System.out.println("do");
        List<Measurement> measurements = measurementsRepository.findAll();
        System.out.println("posle");
        return measurements.stream().map(this::transformFromModelToDTO).toList();
    }
}
