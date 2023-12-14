package ru.dolmatov.weathersensorapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.dolmatov.weathersensorapi.models.Sensor;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class SensorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createSensor() throws Exception {
        String url = "/sensors/registration";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Sensor sensorToAdd = new Sensor();
        sensorToAdd.setName("Dima");

        HttpEntity<Sensor> sensorHttpEntity = new HttpEntity<>(sensorToAdd, httpHeaders);
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, sensorHttpEntity, Void.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
