
/**
 * MiniProject Baby Names: The year with the highest rank
 * 
 * @author (mahmoudjs14) 
 * @version (01/07/2021)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class HighestRank {
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
    
    public int yearOfHighestRank(String name, String gender) {
        int year = -1;
        int highestRank = 1000000000;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String curYearStr = f.getName().substring(3,7);
            int curYear = Integer.parseInt(curYearStr);
            int rank = getRank(curYear, name, gender);
            if (rank != -1 && rank < highestRank) {
                highestRank = rank;
                year = curYear;
            }
            
        }
        return year;
    }
    
    public void testYearOfHighestRank() {
        int year = yearOfHighestRank("Mason", "M");
        System.out.println("The year with highest rank of Mason is: " + year);
    }
}
