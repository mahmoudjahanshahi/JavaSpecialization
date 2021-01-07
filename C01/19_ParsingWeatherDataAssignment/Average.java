
/**
 * Programming Exercise: Parsing Weather Data
 * 
 * @author (mahmoudjs14) 
 * @version (01/07/2021)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Average {
    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0;
        int count = 0;
        for (CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            if (temp != -9999) {
                sum += temp;
                count++;
            }
        }
        return sum/count;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + average);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        int count = 0;
        for (CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            String humStr = record.get("Humidity");
            if (humStr.equalsIgnoreCase("N/A")) {
                continue;
            }
            double hum = Double.parseDouble(humStr);
            if (hum >= value && temp != -9999) {
                sum += temp;
                count++;
            }
        }
        return sum/count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (Double.isNaN(average)) {
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("Average temperature in file is " + average);
        }
    }    
}
