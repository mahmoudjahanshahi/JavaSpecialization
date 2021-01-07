
/**
 * MiniProject Baby Names: Rank
 * 
 * @author (mahmoudjs14) 
 * @version (01/07/2021)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Rank {
    public int getRank(int year, String name, String gender) {
        String path = "./data/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(path);
        int rank = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                rank ++;
                if (record.get(0).equalsIgnoreCase(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public void testGetRank() {
        int rank = getRank(2012, "Mason", "M");
        System.out.println("Rank of Mason in year 2012 between male names: " + rank);
    }
}
