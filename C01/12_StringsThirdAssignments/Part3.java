
/**
 * Part3
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
    
    public void processGenes(StorageResource sr) {
        int count60 = 0;
        int countCG = 0;
        int longest = 0;
        for (String item : sr.data()) {
            int length = item.length();
            if (length > longest) {
                longest = length;
            }
            if (length > 60) {
                System.out.println(item);
                count60 ++;
            }
            if (cgRatio(item) > 0.35) {
                System.out.println(item);
                countCG ++;
            }
        }
        System.out.println("Genes with greater than 60 count: " + count60);
        System.out.println("Genes with CG ratio greater than 0.35 count: " + countCG);
        System.out.println("Longest gene length: " + longest);
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource sr = getAllGenes(dna);
        System.out.println("Number of genes found: " + sr.size());
        processGenes(sr);
    }
}
