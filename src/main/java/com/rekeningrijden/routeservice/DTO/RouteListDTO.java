package com.rekeningrijden.routeservice.DTO;

import com.rekeningrijden.routeservice.DataPoint.DataPoint;

import java.util.Date;
import java.util.List;

public class RouteListDTO {
    private String routeId;
    private Date startTime;
    private Date endTime;


    public RouteListDTO(String routeId, Date startTime, Date endTime) {
        this.routeId = routeId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public RouteListDTO() {

    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
    public String getRouteId() {
        return routeId;
    }
}
