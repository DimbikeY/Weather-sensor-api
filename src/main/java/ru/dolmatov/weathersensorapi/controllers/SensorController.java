package ru.dolmatov.weathersensorapi.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dolmatov.weathersensorapi.request.dto.SensorRequestDTO;
import ru.dolmatov.weathersensorapi.services.EndPointValidationProcessingMessageService;
import ru.dolmatov.weathersensorapi.services.SensorsService;
import ru.dolmatov.weathersensorapi.utils.SensorRegistrationUniqueValidator;

@Controller
@RequestMapping("/sensors")
public class SensorController {

    private final SensorsService sensorsService;
    private final SensorRegistrationUniqueValidator registrationUniqueValidator;

    @Autowired
    public SensorController(SensorsService sensorsService, SensorRegistrationUniqueValidator registrationUniqueValidator) {
        this.sensorsService = sensorsService;
        this.registrationUniqueValidator = registrationUniqueValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addSensor(@RequestBody
                                                @Valid
                                                SensorRequestDTO registrationRequestDTO,
                                                BindingResult bindingResult) {
        registrationUniqueValidator.validate(registrationRequestDTO, bindingResult);
        EndPointValidationProcessingMessageService.bindingResultHandlerMessage(bindingResult);
        sensorsService.saveNewSensors(registrationRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
