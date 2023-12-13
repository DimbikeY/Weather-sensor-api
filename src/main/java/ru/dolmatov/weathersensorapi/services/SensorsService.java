package ru.dolmatov.weathersensorapi.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dolmatov.weathersensorapi.models.Sensor;
import ru.dolmatov.weathersensorapi.repositories.SensorsRepository;
import ru.dolmatov.weathersensorapi.request.dto.SensorRegistrationRequestDTO;

@Service
public class SensorsService {
    private final ModelMapper modelMapper;
    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(ModelMapper mapper, SensorsRepository sensorsRepository){
        this.modelMapper = mapper;
        this.sensorsRepository = sensorsRepository;
    }

    @Transactional
    public void saveNewSensors(SensorRegistrationRequestDTO registrationRequestDTO) {
        Sensor modelToSave = transformFromDTOToModel(registrationRequestDTO);
        sensorsRepository.save(modelToSave);
    }

    public Sensor transformFromDTOToModel(SensorRegistrationRequestDTO requestDTO){
        return modelMapper.map(requestDTO, Sensor.class);
    }
}
