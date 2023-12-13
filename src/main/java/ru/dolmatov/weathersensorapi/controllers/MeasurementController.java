package ru.dolmatov.weathersensorapi.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dolmatov.weathersensorapi.exceptions.BadRequestPropertiesException;
import ru.dolmatov.weathersensorapi.request.dto.MeasurementRequestDTO;
import ru.dolmatov.weathersensorapi.services.MeasurementsService;
import ru.dolmatov.weathersensorapi.utils.MeasurementsSensorRegistrationValidator;

import java.util.List;

@Controller
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementsService measurementsService;
    private final MeasurementsSensorRegistrationValidator measurementsSensorRegistrationValidator;

    @Autowired
    public MeasurementController(MeasurementsService measurementsService,
                                 MeasurementsSensorRegistrationValidator measurementsSensorRegistrationValidator) {

        this.measurementsService = measurementsService;
        this.measurementsSensorRegistrationValidator = measurementsSensorRegistrationValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody
                                                     @Valid
                                                     MeasurementRequestDTO registrationRequestDTO,
                                                     BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            measurementsSensorRegistrationValidator.validate(registrationRequestDTO, bindingResult);
        }
        if (bindingResult.hasErrors()) {
            StringBuilder msgError = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            errorList.forEach(fieldError -> msgError.append(fieldError.getDefaultMessage())
                    .append(" - ")
                    .append(fieldError.getField()));
            throw new BadRequestPropertiesException(msgError.toString());
        }
        measurementsService.saveNewMeasurement(registrationRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
