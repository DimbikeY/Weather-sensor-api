package ru.dolmatov.weathersensorapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dolmatov.weathersensorapi.request.dto.MeasurementRequestDTO;
import ru.dolmatov.weathersensorapi.response.dto.MeasurementResponseDTO;
import ru.dolmatov.weathersensorapi.services.EndPointValidationProcessingMessageService;
import ru.dolmatov.weathersensorapi.services.MeasurementsService;
import ru.dolmatov.weathersensorapi.utils.MeasurementsSensorRegistrationValidator;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/measurements")
@Tag(name = "Measurement", description = "Controller for measurements")
public class MeasurementController {
    private final MeasurementsService measurementsService;
    private final MeasurementsSensorRegistrationValidator measurementsSensorRegistrationValidator;

    @Autowired
    public MeasurementController(MeasurementsService measurementsService,
                                 MeasurementsSensorRegistrationValidator measurementsSensorRegistrationValidator) {

        this.measurementsService = measurementsService;
        this.measurementsSensorRegistrationValidator = measurementsSensorRegistrationValidator;
    }

    @Operation(
            summary = "Get All measurements",
            description = "Allows you to get full info about every measurements have been taken"
    )
    @GetMapping()
    public ResponseEntity<List<MeasurementResponseDTO>> getAllMeasurements() {

        List<MeasurementResponseDTO> responseDTOListToSend = measurementsService.findAllMeasurements();
        return new ResponseEntity<>(responseDTOListToSend, HttpStatus.OK);
    }

    @Operation(
            summary = "Amount of rainy days",
            description = "Allows you to get an amount of rainy days happened ever"
    )
    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Map<String, Integer>> getRainyDaysMeasurements() {

        return new ResponseEntity<>(measurementsService.countRainyDays(), HttpStatus.OK);
    }

    @Operation(
            summary = "Add a measurement",
            description = "Allows to add a new measurement taken by a sensor"
    )
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
