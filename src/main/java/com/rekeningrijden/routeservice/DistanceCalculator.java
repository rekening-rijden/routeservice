package com.rekeningrijden.routeservice;

import com.rekeningrijden.routeservice.DataPoint.DataPoint;

import java.util.List;

public class DistanceCalculator {

    private double distance = 0;
    private List<DataPoint> dataPointList;

    public DistanceCalculator(List<DataPoint> dataPointList) {
        this.dataPointList = dataPointList;
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    private static double distance(double lat1, double lat2, double lon1,
                                  double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    public double totalDistance()
    {
        int listSize = dataPointList.size();
        for (int i = 0; i < listSize-1; i++) {
            if (i != listSize-2)
            {
                distance += distance(dataPointList.get(i).getLat(), dataPointList.get(i+1).getLat(), dataPointList.get(i+1).getLng(), dataPointList.get(i+1).getLng());
            }
        }
        return distance;
    }
}
