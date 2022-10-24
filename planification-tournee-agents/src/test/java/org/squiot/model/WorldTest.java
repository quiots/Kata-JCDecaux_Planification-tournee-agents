package org.squiot.model;

import org.squiot.exception.InvalidValueException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    private final List<PointOfInterest> pointsOfInterest = List.of(
            new PointOfInterest("id1", -48.6, -37.7),
            new PointOfInterest("id2", -27.1, 8.4),
            new PointOfInterest("id3", 6.6, -6.9),
            new PointOfInterest("id4", -2.3, 38.3),
            new PointOfInterest("id5", 6.8, -6.9),
            new PointOfInterest("id6", -2.5, 38.3),
            new PointOfInterest("id7", 0.1, -0.1),
            new PointOfInterest("id8", -2.1, 38.1)
    );

    private final List<PointOfInterest> wrongPointsOfInterest = List.of(
            new PointOfInterest("id1", -304.6, -37.7),
            new PointOfInterest("id2", -27.1, 860.4)
    );

    @Test
    void shouldReturnWorldWithCorrectPointsOfInterest() throws InvalidValueException {
        World world = World.of(pointsOfInterest);
        assertEquals(pointsOfInterest,world.getWorldPointsOfInterest());
    }

    @Test
    void shouldThrowInvalidValueExceptionWhenPointOfInterestIsNotContained(){
        assertThrows(InvalidValueException.class,()->
                World.of(wrongPointsOfInterest)
                );
    }

}