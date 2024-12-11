package com.sensors.sensors.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Entity
public class SensorModel {

    //Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "windSpeed|humidity|temperature|pressure|rain", message = "Invalid sensor type")
    private String sensorType;

    @NotNull
    private Double metric;

    @NotNull
    @Pattern(regexp = "km/h|%|Celsius|hPa|mm", message = "Invalid unit type")
    private String unit;

    @NotNull
    private String location;

    @NotNull
    private LocalDateTime dateTime;

    //No args constructor
    public SensorModel() {
        location = "Galway City Centre";
        dateTime = LocalDateTime.now();
    }

    //args constructor
    public SensorModel(String sensorType, Double metric, String unit) {
        this.sensorType = sensorType;
        this.metric = metric;
        this.unit = unit;
        location = "Galway City Centre";
        dateTime = LocalDateTime.now();
    }

    //Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setMetric(Double metric) {
        this.metric = metric;
    }

    public Double getMetric() {
        return metric;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


}