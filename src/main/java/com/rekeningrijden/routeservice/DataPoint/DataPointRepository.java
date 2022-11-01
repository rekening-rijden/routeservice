package com.rekeningrijden.routeservice.DataPoint;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DataPointRepository extends CassandraRepository<DataPoint, Long> {
    @Query("SELECT * FROM datapoint WHERE vehicleid=2")
    List<DataPoint> findByVehicleId(int vehicleId);
    List<DataPoint> findByVehicleIdAndRouteId(int vehicleId, String routeId);

}
