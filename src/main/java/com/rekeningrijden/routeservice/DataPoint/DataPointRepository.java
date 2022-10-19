package com.rekeningrijden.routeservice.DataPoint;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataPointRepository extends JpaRepository<DataPoint, Long> {

    List<DataPoint> findByVehicleId(int vehicleId);
    List<DataPoint> findByVehicleIdAndRouteId(int vehicleId, String routeId);
}
