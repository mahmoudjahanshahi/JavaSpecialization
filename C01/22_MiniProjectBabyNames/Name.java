
/**
 * MiniProject Baby Names: Name
 * 
 * @author (mahmoudjs14) 
 * @version (01/07/2021)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Name {
    public String getName(int year, int rank, String gender) {
        String path = "./data/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(path);
        int curRank = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                curRank ++;
                if (rank == curRank) {
                    return record.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public void testGetName() {
        String name = getName(2012, 2, "M");
        System.out.println("Rank 2 of male names in year 2012: " + name);
    }
}
