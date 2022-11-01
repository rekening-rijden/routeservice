package com.rekeningrijden.routeservice.DataPoint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class DataPoint {

    @JsonIgnore
    private int id;
    @JsonIgnore
    private String routeId;
    @JsonIgnore
    @PrimaryKeyColumn(name = "vehicleId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int vehicleId;
    @JsonIgnore
    @PrimaryKeyColumn(name = "timestamp", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String timestamp;
    private double lat;
    private double lng;

    public String getRouteId() {
        return routeId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getTimestamp(){

        try{
            SimpleDateFormat SDFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date _timestamp = SDFormat.parse(timestamp);
            return _timestamp;
        }
        catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTimestamp(Date timestamp) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(timestamp);
        this.timestamp = strDate;
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
