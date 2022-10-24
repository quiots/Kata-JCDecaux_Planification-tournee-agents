package org.squiot.service;

import org.squiot.exception.InvalidValueException;
import org.squiot.model.World;
import org.squiot.model.Zone;
import org.squiot.model.PointOfInterest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class WorldServiceTest {


    private final WorldService worldService = new WorldService();


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

    private final World world = World.of(pointsOfInterest);


    WorldServiceTest() throws InvalidValueException {
    }

    @ParameterizedTest
    @MethodSource("zoneProviderForCountingPOIs")
    void shouldReturnNumberOfPOIsInAGivenArea(Zone givenZone, int expectedCountPOIs){
        assertEquals(expectedCountPOIs,worldService.countPointsOfInterestWithinZone(world,givenZone));
    }

    @ParameterizedTest
    @MethodSource("numberZonesAskedAmongDensestZones")
    void shouldReturnDensestAreas(int givenNumberZones, int expectedNumberZones) throws InvalidValueException {
        assertEquals(expectedNumberZones,worldService.getDensestZones(world,givenNumberZones).size());
    }

    public static Stream<Arguments> zoneProviderForCountingPOIs(){
        return Stream.of(
                Arguments.of(Zone.of(6.5,-7), 2),
                Arguments.of(Zone.of(10,40),0),
                Arguments.of(Zone.of(0.0,-0.5), 1),
                Arguments.of(Zone.of(-2.5,38),3)
        );
    }

    public static Stream<Arguments> numberZonesAskedAmongDensestZones(){
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1,1),
                Arguments.of(4, 4),
                Arguments.of(12,5)
        );
    }

    @Test
    @DisplayName("should throw a InvalidValueException when asking densest zones with negative value")
    void shouldThrowNegativeNumberZonesException(){
        assertThrows(InvalidValueException.class,()->{
            worldService.getDensestZones(world,-1);
        });

    }

}