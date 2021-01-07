
/**
 * MiniProject Baby Names: Name in Year
 * 
 * @author (mahmoudjs14) 
 * @version (01/07/2021)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class NameYear {
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
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }
}
