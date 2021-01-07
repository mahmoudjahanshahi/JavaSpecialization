
/**
 * MiniProject Baby Names: Totals
 * 
 * @author (mahmoudjs14) 
 * @version (01/07/2021)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class Totals {
    public void printNames () {
	FileResource fr = new FileResource();
	for (CSVRecord rec : fr.getCSVParser(false)) {
	    int numBorn = Integer.parseInt(rec.get(2));
	    if (numBorn <= 100) {
		System.out.println("Name " + rec.get(0) +
		                   " Gender " + rec.get(1) +
				   " Num Born " + rec.get(2));
	    }
	}
    }

    public void totalBirths (FileResource fr) {
	int totalBirths = 0;
	int totalNames = 0;
	int totalFBirths = 0;
	int totalFNames = 0;
	int totalMBirths = 0;
	int totalMNames = 0;
	for (CSVRecord rec : fr.getCSVParser(false)) {
	    int numBorn = Integer.parseInt(rec.get(2));
	    totalBirths += numBorn;
	    totalNames ++;
	    if (rec.get(1).equals("F")) {
	        totalFBirths += numBorn;
	        totalFNames ++;
	    }
	    else {
	        totalMBirths += numBorn;
		totalMNames ++;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Female births = " + totalFBirths);
        System.out.println("Male births = " + totalMBirths);
        System.out.println("Total names = " + totalNames);
        System.out.println("Female names = " + totalFNames);
        System.out.println("Male names = " + totalMNames);
    }

    public void testTotalBirths () {
	FileResource fr = new FileResource();
	totalBirths(fr);
    }
}
