
/**
 * MiniProject Baby Names: Average Rank
 * 
 * @author (mahmoudjs14) 
 * @version (01/07/2021)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class AverageRank {
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
    
    public double getAverageRank(String name, String gender) {
        int count = 0;
        double total = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String curYearStr = f.getName().substring(3,7);
            int curYear = Integer.parseInt(curYearStr);
            int rank = getRank(curYear, name, gender);
            if (rank != -1) {
                total += rank;
                count ++;
            }
            
        }
        if (count == 0) {
            return -1.0;
        }
        return total/count;
    }
    
    public void testGetAverageRank() {
        double average = getAverageRank("Mason", "M");
        System.out.println("The average rank of Mason is: " + average);
    }
}
