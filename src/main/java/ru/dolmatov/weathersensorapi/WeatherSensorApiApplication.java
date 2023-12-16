package ru.dolmatov.weathersensorapi;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XYChart;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.dolmatov.weathersensorapi.request.dto.MeasurementRequestDTO;
import ru.dolmatov.weathersensorapi.request.dto.SensorRequestDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class WeatherSensorApiApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(WeatherSensorApiApplication.class, args);

        /*Thread.sleep(3000);
        String url = "http://localhost:8081/measurements/add";

        SensorRequestDTO sensor = new SensorRequestDTO();
        sensor.setName("kek");

        for (int i = 0; i < 1000; i++) {
            MeasurementRequestDTO requestToAddMeasurementDTO = new MeasurementRequestDTO();

            float temperatureToAdd = new Random().nextFloat() * 100;
            Boolean booleanToAdd = new Random().nextBoolean();

            if (temperatureToAdd > 50) {
                temperatureToAdd *= -1f;
            }

            requestToAddMeasurementDTO.setTemperature(temperatureToAdd);
            requestToAddMeasurementDTO.setRaining(booleanToAdd);
            requestToAddMeasurementDTO.setSensor(sensor);

            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<MeasurementRequestDTO> entityToSend = new HttpEntity<>(requestToAddMeasurementDTO);

            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, entityToSend, Void.class);
        }

        Thread.sleep(2000);

        String urlGet = "http://localhost:8081/measurements";
        RestTemplate restTemplate = new RestTemplate();
        MeasurementRequestDTO[] getResultList = restTemplate.getForObject(urlGet, MeasurementRequestDTO[].class);
        System.out.println(getResultList.length);
        List<Double> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();
        for (int i = 0; i < getResultList.length; i++) {
            xData.add((double) i);
            yData.add((double) getResultList[i].getTemperature());
        }
        System.setProperty("java.awt.headless", "true");
        XYChart chart = QuickChart.getChart("Sample Chart", "Time", "Temperature", "y(x)", xData, yData);
        try {
            BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapEncoder.BitmapFormat.PNG, 300);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } */
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
