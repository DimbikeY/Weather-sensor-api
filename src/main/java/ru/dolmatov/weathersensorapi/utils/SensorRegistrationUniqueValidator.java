package ru.dolmatov.weathersensorapi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.dolmatov.weathersensorapi.models.Sensor;
import ru.dolmatov.weathersensorapi.request.dto.SensorRequestDTO;
import ru.dolmatov.weathersensorapi.services.SensorsService;

import java.util.Optional;

@Component
public class SensorRegistrationUniqueValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public SensorRegistrationUniqueValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorRequestDTO dtoToCheck = (SensorRequestDTO) target;
        Sensor modelToCheck = sensorsService.transformFromDTOToModel(dtoToCheck);
        Optional<Sensor> sensorToCheck = sensorsService.findSensorByName(modelToCheck.getName());
        if (sensorToCheck.isPresent()) {
            errors.rejectValue("name", "", "This scanner already exists");
        }
    }
}
