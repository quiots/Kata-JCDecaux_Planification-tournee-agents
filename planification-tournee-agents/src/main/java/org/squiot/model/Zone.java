package org.squiot.model;

import java.util.Objects;

public class Zone {
    public static final double INCREMENT_STEP = 0.5;
    private final double minLatitude;
    private final double minLongitude;

    private Zone(double minLatitude, double minLongitude){
        this.minLatitude=minLatitude;
        this.minLongitude=minLongitude;
    }

    public static Zone of(double latitude, double longitude){
        return new Zone(roundHalfDown(latitude),roundHalfDown(longitude));
    }

    public double getMinLatitude() {
        return minLatitude;
    }

    public double getMinLongitude() {
        return minLongitude;
    }

    public boolean contains(PointOfInterest pointOfInterest) {
        return pointOfInterest.latitude() >= minLatitude
                && pointOfInterest.latitude() < minLatitude + INCREMENT_STEP
                && pointOfInterest.longitude() >= minLongitude
                && pointOfInterest.longitude() < minLongitude + INCREMENT_STEP;
    }


    private static double roundHalfDown(double d) {
        double i = Math.floor(d);
        double f = d - i;
        return f < INCREMENT_STEP ? i : i + INCREMENT_STEP;
    }

    public String toString(){
        return "{\"minLat\":" + minLatitude
                + ", \"maxLat\":" + (minLatitude+INCREMENT_STEP)
                + ", \"minLon\":" + minLongitude
                + ", \"maxLon\":" + (minLongitude+INCREMENT_STEP)
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zone zone = (Zone) o;
        return Double.compare(zone.minLatitude, minLatitude) == 0 && Double.compare(zone.minLongitude, minLongitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minLatitude, minLongitude);
    }
}
