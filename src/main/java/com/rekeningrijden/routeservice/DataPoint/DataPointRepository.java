package com.rekeningrijden.routeservice.DataPoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.xml.crypto.Data;
import java.util.List;

public interface DataPointRepository extends JpaRepository<DataPoint, Long> {

    List<DataPoint> findByVehicleId(int vehicleId);
    List<DataPoint> findByVehicleIdAndRouteId(int vehicleId, String routeId);
    DataPoint findTopByRouteIdOrderByTimestampDesc(String RouteId);
}
