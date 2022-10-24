package org.squiot.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.squiot.exception.InvalidValueException;
import org.squiot.model.PointOfInterest;
import org.squiot.model.World;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileToWorldService {

    public World worldParser(String filepath) throws InvalidValueException, IOException, CsvValidationException {

        String fileNamePath = Objects.requireNonNull(filepath);
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();

        List<PointOfInterest> pointOfInterestsForWorld = new ArrayList<>();

        try(CSVReader csvReader = new CSVReaderBuilder(
                new FileReader(fileNamePath))
                .withCSVParser(csvParser)   // custom CSV parser
                .withSkipLines(1)           // skip the first line, header info
                .build()){

            String[] values;

            while ((values = csvReader.readNext()) != null) {
                PointOfInterest pointOfInterest = pointOfInterestParser(Arrays.asList(values));
                pointOfInterestsForWorld.add(pointOfInterest);
            }

            return World.of(pointOfInterestsForWorld);
        }
    }

    private PointOfInterest pointOfInterestParser(List<String> dataLine) throws InvalidValueException {
        if (dataLine.size() != 3) {
            throw new InvalidValueException("3 args are necessary for creating a POI.");
        }

        String id = dataLine.get(0);

        try {
            double latitude = Double.parseDouble(dataLine.get(1));
            double longitude = Double.parseDouble(dataLine.get(2));

            return new PointOfInterest(id, latitude, longitude);
        } catch (NumberFormatException e) {
            throw new InvalidValueException("Data received is not in a number format.");
        }
    }
}
