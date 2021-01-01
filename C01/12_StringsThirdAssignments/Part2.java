
/**
 * Part2
 * 
 * @author (mahmoudjs14) 
 * @version (01/01/2021)
 */

import edu.duke.*;

public class Part2 {
    public float cgRatio(String dna) {
        float count = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.substring(i,i+1).equals("C") || dna.substring(i,i+1).equals("G")) {
                count ++;
            }
        }
        float ratio = count / dna.length();
        return ratio;
    }
    
    public int countCTG(String dna) {
        int curIndex = dna.indexOf("CTG");
        int count = 0;
        while (curIndex != -1) {
            count ++;
            curIndex = dna.indexOf("CTG", curIndex+3);
        }
        return count;
    }
    
    public void test() {
        String dna = "ATGCCATAG";
        float ratio = cgRatio(dna);
        System.out.println("DNA strand: " + dna);
        System.out.println("CG ratio: " + ratio);
        
        FileResource fr = new FileResource();
        dna = fr.asString();
        int count = countCTG(dna);
        System.out.println("DNA strand: " + dna);
        System.out.println("CTG count: " + count);
    }
}
