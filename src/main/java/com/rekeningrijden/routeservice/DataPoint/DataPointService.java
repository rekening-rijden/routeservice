package com.rekeningrijden.routeservice.DataPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
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
    public List<DataPoint> getDatapointByRouteId(String routeId)
    {
        return dataPointRepository.findByRouteId(routeId);
    }

    public List<DataPoint> getDistinctRouteByVehicleId(int vehicleId)
    {
        List<DataPoint> datapoints = dataPointRepository.findByVehicleId(vehicleId);
        List<DataPoint> tempRoutes = new ArrayList<>();
        List<DataPoint> routes = new ArrayList<>();
        for (DataPoint dp: datapoints)
        {
            if (!containsRouteId(tempRoutes, dp.getRouteId()))
            {
                tempRoutes.add(dp);
            };
        }

        for (DataPoint dp: tempRoutes)
        {
            routes.add(dp);
            routes.add(dataPointRepository.findTopByRouteIdOrderByTimestampDesc(dp.getRouteId()));
        }
        return routes;
    }

    public boolean containsRouteId(final List<DataPoint> list, final String routeId){
        return list.stream().filter(o -> o.getRouteId().equals(routeId)).findFirst().isPresent();
    }
}
