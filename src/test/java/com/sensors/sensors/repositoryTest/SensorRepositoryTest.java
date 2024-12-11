package com.sensors.sensors.repositoryTest;

import com.sensors.sensors.model.SensorModel;
import com.sensors.sensors.repository.SensorRepository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

@DataJpaTest
public class SensorRepositoryTest {
    
    @Autowired
    private SensorRepository sensorRepository;


    @Test
    public void testSubmitEntry() {

        SensorModel sensor = new SensorModel();
        sensor.setSensorType("temperature");
        sensor.setMetric(25.0);
        sensor.setUnit("Celsius");
        sensor.setDateTime(LocalDateTime.now());

        SensorModel savedSensor = sensorRepository.save(sensor);

        assertNotNull(savedSensor.getId());
        assertEquals("temperature", savedSensor.getSensorType());
        assertEquals(25.0, savedSensor.getMetric());
    }
}