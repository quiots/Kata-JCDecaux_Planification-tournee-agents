package org.squiot.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ZoneTest {


    @ParameterizedTest
    @MethodSource("poisProvider")
    void shouldReturnNewZoneWithHalfDownRoundedValues(double givenLatitude,double givenLongitude,double expectedLatitude, double expectedLongitude){
        Zone zoneRounded = Zone.of(givenLatitude,givenLongitude);

        assertEquals(expectedLatitude,zoneRounded.getMinLatitude());
        assertEquals(expectedLongitude,zoneRounded.getMinLongitude());
    }

    public static Stream<Arguments> poisProvider(){
        return Stream.of(
                Arguments.of(-5.3, 3.7, -5.5, 3.5),
                Arguments.of(0.1, 0.0, 0.0, 0.0),
                Arguments.of(-5.5, 3.5, -5.5, 3.5),
                Arguments.of(-1.7, -2.4, -2.0, -2.5),
                Arguments.of(13.3, 25.6, 13.0, 25.5),
                Arguments.of(3.3, -48.7, 3.0, -49.0),
                Arguments.of(-0.1, 0.0, -0.5, 0.0)
        );
    }
}