
/**
 * This assignment is to write the code from the lesson to use a StorageResource
 * to store the genes you find instead of printing them out. This will help you 
 * see if you really understood how to put the code together, and might identify 
 * a part that you did not fully understand. If you get stuck, then you can go back 
 * and watch the coding videos that go with this lesson again.
 * 
 * @author (mahmoudjs14) 
 * @version (01/01/2021)
 */

import edu.duke.*;

public class Part1 {
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
    
    public StorageResource getAllGenes(String dna) {
        int curIndex = 0;
        StorageResource sr = new StorageResource();
        while (true) {
            String gene = findGene(dna,curIndex);
            if (gene.isEmpty()) {
                break;
            }
            sr.add(gene);
            curIndex = dna.indexOf(gene, curIndex) + gene.length();
        }
        return sr;
    }
    
    public void printAllGenes(String dna) {
        StorageResource genes = getAllGenes(dna) ;
        for (String gene : genes.data()) {
            System.out.println("Gene: " + gene);
        }
    }
    
    public void testPrintAllGenes() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        printAllGenes(dna);
    }
}