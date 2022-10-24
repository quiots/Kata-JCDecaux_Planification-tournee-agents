package org.squiot.model;

import org.squiot.exception.InvalidValueException;

import java.util.*;

public class World {

    public static final double MIN_LATITUDE = -90;
    public static final double MIN_LONGITUDE = -180;
    public static final double MAX_LATITUDE = 90;
    public static final double MAX_LONGITUDE = 180;

    private final List<PointOfInterest> worldPOIs;

    public static World of(List<PointOfInterest> pointsOfInterest) throws InvalidValueException {

        for (PointOfInterest pointOfInterest :pointsOfInterest) {
            if(!canContain(pointOfInterest)){
                throw new InvalidValueException("Invalid data. Point interest can't be added to our world. Creation aborted.");
            }
        }

        return new World(pointsOfInterest);
    }

    private World(List<PointOfInterest> pointOfInterests){
        this.worldPOIs = pointOfInterests;
    }

    public List<PointOfInterest> getWorldPointsOfInterest() {
        return Collections.unmodifiableList(worldPOIs);
    }

    private static boolean canContain(PointOfInterest pointOfInterest) {
        return pointOfInterest.latitude() >= MIN_LATITUDE
                && pointOfInterest.latitude() < MAX_LATITUDE
                && pointOfInterest.longitude() >= MIN_LONGITUDE
                && pointOfInterest.longitude() < MAX_LONGITUDE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        World world = (World) o;
        return Objects.equals(worldPOIs, world.worldPOIs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worldPOIs);
    }
}
