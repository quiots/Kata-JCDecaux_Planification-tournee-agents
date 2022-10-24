package org.squiot;

import com.opencsv.exceptions.CsvValidationException;
import org.squiot.exception.*;
import org.squiot.model.World;
import org.squiot.model.Zone;
import org.squiot.service.FileToWorldService;
import org.squiot.service.WorldService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        FileToWorldService fileToWorldService = new FileToWorldService();
        WorldService worldService = new WorldService();

        try {
            World worldParsed = fileToWorldService.worldParser(FileToWorldService.class.getClassLoader()
                    .getResource("POIs_example.csv").getFile());

            int points = worldService.countPointsOfInterestWithinZone(
                    worldParsed, Zone.of(6.5, -7));
            LOGGER.log(Level.INFO,"Number of points of interest in our zone : {0}", points);

            List<Zone> densestZones = worldService.getDensestZones(worldParsed, 2);
            LOGGER.log(Level.INFO,"The two densest zones of our world are : {0}", densestZones);
        } catch (InvalidValueException | CsvValidationException e) { // exceptions related to content
            LOGGER.log(Level.SEVERE,e.getMessage(),e);
        } catch (IOException e) { // exception related to loading of the file
            LOGGER.log(Level.SEVERE,e.getMessage(),e);
        }
    }
}