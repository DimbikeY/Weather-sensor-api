package ru.dolmatov.weathersensorapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class WeatherSensorApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Bean
    public TestRestTemplate testRestTemplate() {
        return new TestRestTemplate();
    }

}
