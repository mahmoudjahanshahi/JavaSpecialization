
/**
 * Programming Exercise: Parsing Weather Data
 * 
 * @author (mahmoudjs14) 
 * @version (01/07/2021)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Coldest {
    public CSVRecord coldestOfTwo(CSVRecord record1, CSVRecord record2) {
        if (record1 == null) {
            return record2;
        }
        double temp1 = Double.parseDouble(record1.get("TemperatureF"));
        double temp2 = Double.parseDouble(record2.get("TemperatureF"));
        if (temp2 < temp1) {
            if (temp2 ==  -9999) {
                return record1;
            }
            return record2;
        }
        return record1;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldest = null;
        for (CSVRecord record : parser) {
            coldest = coldestOfTwo(coldest, record);
        }
        return coldest;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldest = null;
        File file = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord current = coldestHourInFile(parser);
            if (current == coldestOfTwo(coldest, current)) {
                coldest = current;
                file = f;
            }
        }
        String filename = file.getName();
        System.out.println("Coldest day was in file " + filename);
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        FileResource fr = new FileResource(file);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
        }
        return filename;
    }
    
    public void testFileWithColdestTemperature() {
        String file = fileWithColdestTemperature();
    }
     
}
