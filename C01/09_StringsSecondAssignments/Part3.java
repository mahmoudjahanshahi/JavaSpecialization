
/**
 * Part 3: How Many Genes?
 * 
 * @author (mahmoudjs14) 
 * @version (01/01/2021)
 */

import edu.duke.*;

public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        while (stopIndex != -1 ) {
            if ((stopIndex - startIndex) % 3 == 0) {
                return stopIndex;
            }
            stopIndex = dna.indexOf(stopCodon, stopIndex+1);
        }
        return dna.length();
    }
    
    public String findGene(String dna, int index) {
        int startIndex = dna.indexOf("ATG", index);
        if (startIndex == -1) {
            return "";
        }
        int stopTAA = findStopCodon(dna, startIndex, "TAA");
        int stopTAG = findStopCodon(dna, startIndex, "TAG");
        int stopTGA = findStopCodon(dna, startIndex, "TGA");
        int minDist = Math.min(stopTAA, Math.min(stopTAG, stopTGA));
        if (minDist == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minDist+3);
    }
    
    public void printAllGenes(String dna) {
        int curIndex = 0;
        //System.out.println("DNA strand: " + dna);
        while (true) {
            String gene = findGene(dna,curIndex);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println("Gene: " + gene);
            curIndex = dna.indexOf(gene, curIndex) + gene.length();
        }
    }
    
    public int countGenes(String dna) {
        int curIndex = 0;
        int count = 0;
        while (true) {
            String gene = findGene(dna,curIndex);
            if (gene.isEmpty()) {
                break;
            }
            count ++;
            curIndex = dna.indexOf(gene, curIndex) + gene.length();
        }
        return count;
    }
    
    public void testCountGenes() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        int count = countGenes(dna);
        System.out.println("Gene count: " + count);
    }
}
