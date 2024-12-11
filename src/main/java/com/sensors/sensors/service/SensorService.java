package com.sensors.sensors.service;

import com.sensors.sensors.repository.SensorRepository;
import com.sensors.sensors.model.SensorModel;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Objects;

import java.time.LocalDateTime;

@Service
public class SensorService {

    //Instance variable
    private final SensorRepository sensorRepository;

    //Constructor for dependency injection
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    //Methods 
    public List<SensorModel> getSensorEntries() {
        return sensorRepository.findAll();
    }

    public SensorModel saveSensorEntry(SensorModel sensorModel) {
        return sensorRepository.save(sensorModel);
    }

    public List<SensorModel> getLatestEntryBySensors(List<String> sensorTypes) {
        return sensorTypes.stream()
            .map(sensorType -> sensorRepository.findLatestBySensorType(sensorType))
            .filter(Objects::nonNull)
            .toList();
    }

    public Map<String, Double> calcSumBySensorsForLastXDays(List<String> sensorTypes, int days) {

        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(days);

        List<SensorModel> sensors = sensorRepository.findBySensorTypeInAndDateRange(sensorTypes, startDate, endDate);

        return sensors.stream()
            .collect(Collectors.groupingBy(
                SensorModel::getSensorType,
                Collectors.summingDouble(SensorModel::getMetric)
            ))
            .entrySet().stream()
            .collect(Collectors.toMap(entry -> entry.getKey() + " Sum for the last " + days + " days", Map.Entry::getValue));
    }

    public Map<String, Double> calcAvgBySensorsForLastXDays(List<String> sensorTypes, int days) {

        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(days);

        List<SensorModel> sensors = sensorRepository.findBySensorTypeInAndDateRange(sensorTypes, startDate, endDate);

            return sensors.stream()
            .collect(Collectors.groupingBy(
                SensorModel::getSensorType,
                Collectors.averagingDouble(SensorModel::getMetric)
            ))
            .entrySet().stream()
            .collect(Collectors.toMap(entry -> entry.getKey() + " Average for the last " + days + " days", Map.Entry::getValue));
    }

    public Map<String, Double> calcMinBySensorsForLastXDays(List<String> sensorTypes, int days) {

        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(days);

        List<SensorModel> sensors = sensorRepository.findBySensorTypeInAndDateRange(sensorTypes, startDate, endDate);

            return sensors.stream()
            .collect(Collectors.groupingBy(
                SensorModel::getSensorType,
                Collectors.collectingAndThen(
                    Collectors.minBy(Comparator.comparingDouble(SensorModel::getMetric)),
                    min -> min.map(SensorModel::getMetric).orElse(0.0)    
                )
            ))
            .entrySet().stream()
            .collect(Collectors.toMap(entry -> entry.getKey() + " Min for the last " + days + " days", Map.Entry::getValue));
    }

    public Map<String, Double> calcMaxBySensorsForLastXDays(List<String> sensorTypes, int days) {

        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(days);

        List<SensorModel> sensors = sensorRepository.findBySensorTypeInAndDateRange(sensorTypes, startDate, endDate);

            return sensors.stream()
            .collect(Collectors.groupingBy(
                SensorModel::getSensorType,
                Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparingDouble(SensorModel::getMetric)),
                    max -> max.map(SensorModel::getMetric).orElse(0.0)    
                )
            ))
            .entrySet().stream()
            .collect(Collectors.toMap(entry -> entry.getKey() + " Max for the last " + days + " days", Map.Entry::getValue));
    }

}