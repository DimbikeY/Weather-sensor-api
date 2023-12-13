package ru.dolmatov.weathersensorapi.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dolmatov.weathersensorapi.request.dto.MeasurementRequestDTO;
import ru.dolmatov.weathersensorapi.response.dto.MeasurementResponseDTO;
import ru.dolmatov.weathersensorapi.services.EndPointValidationProcessingMessageService;
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

    @GetMapping()
    public ResponseEntity<List<MeasurementResponseDTO>> getAllMeasurements() {

        List<MeasurementResponseDTO> responseDTOListToSend = measurementsService.findAllMeasurements();
        return new ResponseEntity<>(responseDTOListToSend, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody
                                                     @Valid
                                                     MeasurementRequestDTO registrationRequestDTO,
                                                     BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            measurementsSensorRegistrationValidator.validate(registrationRequestDTO, bindingResult);
        }
        EndPointValidationProcessingMessageService.bindingResultHandlerMessage(bindingResult);
        measurementsService.saveNewMeasurement(registrationRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
