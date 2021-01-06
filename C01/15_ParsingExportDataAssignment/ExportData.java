
/**
 * Programming Exercise: Parsing Export Data
 * 
 * @author (mahmoudjs14) 
 * @version (01/06/2021)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ExportData {
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (country.equalsIgnoreCase(record.get("Country"))) {
                String info = record.get("Country") + ": ";
                info = info + record.get("Exports") + ": ";
                info = info + record.get("Value (dollars)");
                return info;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > amount.length()) {
                System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Country Info ");
        System.out.println(countryInfo(parser, "Germany"));
        System.out.println();
        
        parser = fr.getCSVParser();
        System.out.println("Gold and diamonds exporters: ");
        listExportersTwoProducts(parser, "gold", "diamonds");
        System.out.println();
        
        parser = fr.getCSVParser();
        System.out.println("Number of Gold exporters: " + numberOfExporters(parser, "gold"));
        System.out.println();
        
        parser = fr.getCSVParser();
        System.out.println("Big exporters: ");
        bigExporters(parser, "$999,999,999");
    }
}
