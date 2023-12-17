package ru.dolmatov.weathersensorapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dolmatov.weathersensorapi.request.dto.SensorRequestDTO;
import ru.dolmatov.weathersensorapi.services.EndPointValidationProcessingMessageService;
import ru.dolmatov.weathersensorapi.services.SensorsService;
import ru.dolmatov.weathersensorapi.utils.SensorRegistrationUniqueValidator;

@RestController
@RequestMapping("/sensors")
@Tag(name="Sensor", description="Controller for sensors")
public class SensorController {

    private final SensorsService sensorsService;
    private final SensorRegistrationUniqueValidator registrationUniqueValidator;

    @Autowired
    public SensorController(SensorsService sensorsService, SensorRegistrationUniqueValidator registrationUniqueValidator) {
        this.sensorsService = sensorsService;
        this.registrationUniqueValidator = registrationUniqueValidator;
    }
    @Operation(
            summary = "Registration a sensor",
            description = "Allows to ad a new sensor to the database"
    )
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addSensor(@RequestBody
                                                @Valid
                                                @Parameter(description = "Received DTO that contains a new name of a new sensor")
                                                SensorRequestDTO registrationRequestDTO,
                                                BindingResult bindingResult) {
        registrationUniqueValidator.validate(registrationRequestDTO, bindingResult);
        EndPointValidationProcessingMessageService.bindingResultHandlerMessage(bindingResult);
        sensorsService.saveNewSensors(registrationRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
