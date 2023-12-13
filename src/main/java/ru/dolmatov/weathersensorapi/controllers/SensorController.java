package ru.dolmatov.weathersensorapi.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dolmatov.weathersensorapi.exceptions.BadRequestPropertiesException;
import ru.dolmatov.weathersensorapi.exceptions.responses.BadRequestPropertiesResponse;
import ru.dolmatov.weathersensorapi.request.dto.SensorRegistrationRequestDTO;
import ru.dolmatov.weathersensorapi.services.SensorsService;
import ru.dolmatov.weathersensorapi.utils.SensorRegistrationUniqueValidator;

import java.util.List;

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
                                                SensorRegistrationRequestDTO registrationRequestDTO,
                                                BindingResult bindingResult) {
        registrationUniqueValidator.validate(registrationRequestDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder msgError = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            errorList.forEach(fieldError -> msgError.append(fieldError.getDefaultMessage())
                    .append(" - ")
                    .append(fieldError.getField()));
            throw new BadRequestPropertiesException(msgError.toString());
        }
        sensorsService.saveNewSensors(registrationRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<BadRequestPropertiesResponse> sendBadRequestPropertiesRespond(BadRequestPropertiesException e) {
        return new ResponseEntity<>(new BadRequestPropertiesResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }
}
