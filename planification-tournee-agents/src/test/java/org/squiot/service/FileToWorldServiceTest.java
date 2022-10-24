package org.squiot.service;

import com.opencsv.exceptions.CsvValidationException;
import org.squiot.exception.InvalidValueException;
import org.squiot.model.PointOfInterest;
import org.squiot.model.World;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileToWorldServiceTest {

    private final FileToWorldService fileToWorldService = new FileToWorldService();

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

    @Test
    void shouldReturnWorldWithListOfPointsOfInterest() throws InvalidValueException, CsvValidationException, IOException {
        World expectedWorld = World.of(pointsOfInterest);
        assertEquals(expectedWorld,fileToWorldService.worldParser(getClass().getClassLoader()
                .getResource("POIs_example.csv").getFile()));
    }

    @Test
    void shouldThrowInvalidValueExceptionWhenFileWithPointOfInterestOutOfBounds(){
        assertThrows(InvalidValueException.class,()-> fileToWorldService.worldParser(getClass().getClassLoader()
                .getResource("POIs_out_of_bounds_example.csv").getFile()));
    }

    @Test
    void shouldThrownInvalidValueExceptionWhenFileContainsWrongNumberOfArguments(){
        assertThrows(InvalidValueException.class,()-> fileToWorldService.worldParser(getClass().getClassLoader()
                .getResource("POIs_wrong_number_arguments_example.csv").getFile()));
    }

    @Test
    void shouldThrownInvalidValueExceptionWhenFileContainsWrongFormatArguments(){
        assertThrows(InvalidValueException.class,()-> fileToWorldService.worldParser(getClass().getClassLoader()
                .getResource("POIs_wrong_format_arguments_example.csv").getFile()));
    }

}