package com.rekeningrijden.routeservice.DataPoint;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class DataPoint {

    @Id
    private int id;
    private int vehicleId;
    private Date timestamp;
    private double lat;
    private double lng;

    public int getId() {
        return id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
