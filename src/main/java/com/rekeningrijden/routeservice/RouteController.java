package com.rekeningrijden.routeservice;

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

import javax.xml.crypto.Data;
import java.util.List;

@RestController
@RequestMapping("/route")

public class RouteController {

    private final DataPointService dataPointService;

    @Autowired

    public RouteController(DataPointService dataPointService) {this.dataPointService = dataPointService;}

    @GetMapping("/{vehicleId}")
    public ResponseEntity<?> getRouteByCarId(@PathVariable int vehicleId)
    {
        List<DataPoint> dataPointList = dataPointService.getDatapointByVehicleId(vehicleId);
        if(dataPointList.size() > 0)
        {
            return new ResponseEntity<>(dataPointList, HttpStatus.OK);
        }
        else throw new NotFoundException("No datapoints found");

    }
}
