package com.rekeningrijden.routeservice.DataPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataPointService {

    private final DataPointRepository dataPointRepository;

    @Autowired
    public DataPointService(DataPointRepository dataPointRepository) {
        this.dataPointRepository = dataPointRepository;
    }

    public List<DataPoint> getDatapointByVehicleId(int vehicleId)
    {
        return dataPointRepository.findByVehicleId(vehicleId);
    }
    public List<DataPoint> getDatapointByVehicleIdAndRouteId(int vehicleId, String routeId)
    {
        return dataPointRepository.findByVehicleIdAndRouteId(vehicleId, routeId);
    }
    public List<DataPoint> getAll()
    {
        return dataPointRepository.findAll();
    }


}
