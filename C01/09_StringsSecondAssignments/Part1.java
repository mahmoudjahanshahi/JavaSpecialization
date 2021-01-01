
/**
 * Part 1: Finding many Genes
 * 
 * @author (mahmoudjs14) 
 * @version (12/31/2020)
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
    
    public void testFindStopCodon() {
        String dna = "CAGGTACACTTATGATCCGCTAGCTAAGGTAACCGTC";
        int startIndex = dna.indexOf("ATG");
        int stopIndex = findStopCodon(dna, startIndex, "TAA");
        if (stopIndex != 29) {
            System.out.println("Error in test case 1");
        }
        
        dna = "CAGGTACACTTATGATCCGCTAGCTAAGGTCAACCGTC";
        startIndex = dna.indexOf("ATG");
        stopIndex = findStopCodon(dna, startIndex, "TAA");
        if (stopIndex != dna.length()) {
            System.out.println("Error in test case 2");
        }
        
        dna = "CAGGTACACTTATGATCCGCTAGCTAGATTAACCGTC";
        startIndex = dna.indexOf("ATG");
        stopIndex = findStopCodon(dna, startIndex, "GAT");
        if (stopIndex != 26) {
            System.out.println("Error in test case 3");
        }
        
        System.out.println("Tests completed!");
    }
    
    public void testFindGene() {
        String dna = "CAGGTACACTTAAGATCCGCTAGCTAAGGTAACCGTC";   //no ATG
        String gene = findGene(dna, 0);
        System.out.println("DNA strand: " + dna);
        System.out.println("Gene: " + gene);
        
        dna = "CAGGTACACTTAATGATCCGCTTGCTAAGGTAACCGTC";   //one valid stops
        gene = findGene(dna, 0);
        System.out.println("DNA strand: " + dna);
        System.out.println("Gene: " + gene);
        
        dna = "CAGGTACACTTAATGATCCGCTAGCTAAGGTAACCGTC";   //two valid stops
        gene = findGene(dna, 0);
        System.out.println("DNA strand: " + dna);
        System.out.println("Gene: " + gene);
        
        dna = "CAGGTACACTTAATGATCCGCTTGCTAAGGGAACCGTC";   //no valid stops
        gene = findGene(dna, 0);
        System.out.println("DNA strand: " + dna);
        System.out.println("Gene: " + gene);
        
        dna = "AATGCTAACTAGCTGACTAAT";   //quiz
        gene = findGene(dna, 0);
        System.out.println("DNA strand: " + dna);
        System.out.println("Gene: " + gene);
    }
    
    public void testPrintAllGenes() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        printAllGenes(dna);
    }
}
