package com.sensors.sensors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sensors.sensors.model.SensorModel;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<SensorModel, Long> {

    @Query(value = "SELECT * FROM SENSOR_MODEL WHERE SENSOR_TYPE = :sensorType ORDER BY DATE_TIME DESC LIMIT 1", nativeQuery = true)
    SensorModel findLatestBySensorType(@Param("sensorType") String sensorType);

    @Query("SELECT d FROM SensorModel d WHERE d.sensorType IN :sensorTypes AND d.dateTime BETWEEN :startDate AND :endDate")
    public List<SensorModel> findBySensorTypeInAndDateRange(
        @Param("sensorTypes") List<String> sensorTypes, 
        @Param("startDate") LocalDateTime startDate, 
        @Param("endDate") LocalDateTime endDate
    );

}