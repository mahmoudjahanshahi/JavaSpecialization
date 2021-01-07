
/**
 * Programming Exercise: Parsing Weather Data
 * 
 * @author (mahmoudjs14) 
 * @version (01/07/2021)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Humidity {
    public CSVRecord lowestOfTwo(CSVRecord record1, CSVRecord record2) {   
        if (record1 == null) {
            return record2;
        }
        double hum1 = Double.parseDouble(record1.get("Humidity"));
        String hum2str = record2.get("Humidity");
        if (hum2str.equalsIgnoreCase("N/A")) {
            return record1;
        }
        double hum2 = Double.parseDouble(hum2str);
        if (hum2 < hum1) {
            return record2;
        }
        return record1;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowest = null;
        for (CSVRecord record : parser) {
            lowest = lowestOfTwo(lowest, record);
        }
        return lowest;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowest = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowest = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord current = lowestHumidityInFile(parser);
            lowest = lowestOfTwo(lowest, current);
        }
        return lowest;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
}
