package ru.dolmatov.weathersensorapi.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.dolmatov.weathersensorapi.exceptions.BadRequestPropertiesException;

import java.util.List;

@Service
public class EndPointValidationProcessingMessageService {
    public static void bindingResultHandlerMessage(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder msgError = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            errorList.forEach(fieldError -> msgError.append(fieldError.getDefaultMessage())
                    .append(" - ")
                    .append(fieldError.getField()));
            throw new BadRequestPropertiesException(msgError.toString());
        }
    }
}
