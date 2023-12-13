package ru.dolmatov.weathersensorapi.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.dolmatov.weathersensorapi.models.Measurement;
import ru.dolmatov.weathersensorapi.models.Sensor;
import ru.dolmatov.weathersensorapi.request.dto.MeasurementRequestDTO;
import ru.dolmatov.weathersensorapi.services.MeasurementsService;
import ru.dolmatov.weathersensorapi.services.SensorsService;

import java.util.Optional;

@Component
public class MeasurementsSensorRegistrationValidator implements Validator {

    private final SensorsService sensorsService;
    private final MeasurementsService measurementsService;

    public MeasurementsSensorRegistrationValidator(SensorsService sensorsService, MeasurementsService measurementsService) {
        this.sensorsService = sensorsService;
        this.measurementsService = measurementsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementRequestDTO measurementRequestDTO = (MeasurementRequestDTO) target;
        Measurement measurementToCheck = measurementsService.transformFromDTOToModel(measurementRequestDTO);
        Optional<Sensor> optionalSensor = sensorsService.findSensorByName(measurementToCheck.getSensor().getName());
        if (optionalSensor.isEmpty()) {
            errors.rejectValue("sensor", "", "This name of a sensor doesn't exist at all!");
        }
    }
}
