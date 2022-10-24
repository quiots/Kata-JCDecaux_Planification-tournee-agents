package org.squiot.service;

import org.squiot.exception.InvalidValueException;
import org.squiot.model.World;
import org.squiot.model.Zone;
import org.squiot.model.PointOfInterest;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WorldService {

    public int countPointsOfInterestWithinZone(World world, Zone zone){
        int countPOIs = 0;

        for (PointOfInterest pointOfInterest : world.getWorldPointsOfInterest()) {
            if(zone.contains(pointOfInterest)){
                countPOIs++;
            }
        }

        return countPOIs;
    }

    public List<Zone> getDensestZones(World world, int numberDensestZones) throws InvalidValueException {

        if (numberDensestZones<0){
            throw new InvalidValueException("Positive value should be given for number of zones.");
        }

        Map<Zone,Long> zonesCounted = world.getWorldPointsOfInterest().stream()
                .map(poi -> Zone.of(poi.latitude(),poi.longitude()))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        return zonesCounted.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(numberDensestZones)
                .map(Map.Entry::getKey).toList();
    }
}
