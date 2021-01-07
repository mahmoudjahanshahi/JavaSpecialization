
/**
 * MiniProject Baby Names: Total Births Ranked Higher
 * 
 * @author (mahmoudjs14) 
 * @version (01/07/2021)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BirthsRankedHigher {
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int total = 0;
        String path = "./data/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(path);
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                if (record.get(0).equalsIgnoreCase(name)) {
                    break;
                }
                total += Integer.parseInt(record.get(2));
            }
        }
        return total;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        int total = getTotalBirthsRankedHigher(2012, "Ethan", "M");
        System.out.println("The total number of births with names ranked higher than Ethan is: " + total);
    }
}
