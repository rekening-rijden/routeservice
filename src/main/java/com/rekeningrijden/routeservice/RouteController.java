package com.rekeningrijden.routeservice;

import com.rekeningrijden.routeservice.DTO.RouteDTO;
import com.rekeningrijden.routeservice.DataPoint.DataPoint;
import com.rekeningrijden.routeservice.DataPoint.DataPointService;
import com.rekeningrijden.routeservice.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/route")

public class RouteController {

    private final DataPointService dataPointService;

    @Autowired

    public RouteController(DataPointService dataPointService) {this.dataPointService = dataPointService;}

    @GetMapping("/{routeId}")
    public ResponseEntity<?> getRouteByCarIdAndRouteId(@PathVariable String routeId)
    {
        List<DataPoint> dataPointList = dataPointService.getDatapointByRouteId(routeId);
        if(dataPointList.size() > 0)
        {
            double distance = new DistanceCalculator(dataPointList).totalDistance();
            return new ResponseEntity<>(new RouteDTO(dataPointList, dataPointList.get(0).getTimestamp(), dataPointList.get(dataPointList.size()-1).getTimestamp(), distance), HttpStatus.OK);
        }
        else throw new NotFoundException("No datapoints found");
    }

    @GetMapping("/routes/{vehicleId}")
    public List<DataPoint> getDistinctRouteByVehicleId(@PathVariable int vehicleId)
    {
        List<DataPoint> routeList = dataPointService.getDistinctRouteByVehicleId(vehicleId);

        if(routeList.size() > 0)
        {
            return routeList;
        }
        else throw new NotFoundException("No datapoints found");
    }
}
