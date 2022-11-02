package com.rekeningrijden.routeservice;

import com.rekeningrijden.routeservice.DTO.RouteDTO;
import com.rekeningrijden.routeservice.DTO.RouteListDTO;
import com.rekeningrijden.routeservice.DataPoint.DataPoint;
import com.rekeningrijden.routeservice.DataPoint.DataPointService;
import com.rekeningrijden.routeservice.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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

    @GetMapping("/vehicleid/{vehicleId}")
    public List<RouteListDTO> getRouteListByVehicleId(@PathVariable int vehicleId) {
        List<DataPoint> routeList = dataPointService.getDistinctRouteByVehicleId(vehicleId);
        List<RouteListDTO> routeListDTOList = new ArrayList<>();

        // TODO: Convert raw data to DTO
        for (int i = 0; i < routeList.size(); i++) {
            if (i % 2 == 0) {
                String routeId = routeList.get(i).getRouteId();
                Date startTime = routeList.get(i).getTimestamp();
                Date endTime = routeList.get(i + 1).getTimestamp();
                routeListDTOList.add(new RouteListDTO(routeId, startTime, endTime));
            }
        }
        return routeListDTOList;
    }
}
