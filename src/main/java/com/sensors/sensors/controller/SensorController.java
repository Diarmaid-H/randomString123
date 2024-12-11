package com.sensors.sensors.controller;

import org.springframework.web.bind.annotation.*;

import com.sensors.sensors.service.SensorService;
import com.sensors.sensors.model.SensorModel;

import java.util.List;
import java.util.Map;
//import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    //Instance variable
    private final SensorService sensorService;

    //Constructor for dependency injection
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public List<SensorModel> getSensorEntries() {
        return sensorService.getSensorEntries();
    }

    @GetMapping("/filter/calc")
    public List<SensorModel> getLatestEntryBySensors(@RequestParam List<String> sensorTypes) {
        return sensorService.getLatestEntryBySensors(sensorTypes);
    }

    @GetMapping("/filter/calc/lastDays")
    public Map<String, Double> getCalcBySensorsForLastXDays(
        @RequestParam String calc,
        @RequestParam List<String> sensorTypes,
        @RequestParam(required = false) Integer days) {

            if("sum".equalsIgnoreCase(calc)) {
                return sensorService.calcSumBySensorsForLastXDays(sensorTypes, days);
            }
            else if("average".equalsIgnoreCase(calc)) {
                return sensorService.calcAvgBySensorsForLastXDays(sensorTypes, days);
            }
            else if("min".equalsIgnoreCase(calc)) {
                return sensorService.calcMinBySensorsForLastXDays(sensorTypes, days);
            }
            else if("max".equalsIgnoreCase(calc)) {
                return sensorService.calcMaxBySensorsForLastXDays(sensorTypes, days);
            }
            else {
                throw new IllegalArgumentException("Invalid calc value - sum, average, min, or max allowed");
            }
    }
    

    @PostMapping
    public SensorModel saveSensorEntry(@RequestBody SensorModel sensorModel) {
        return sensorService.saveSensorEntry(sensorModel);
    }


}