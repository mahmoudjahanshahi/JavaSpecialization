
/**
 * Assignment 1: Codon Count
 * 
 * @author (mahmoudjs14) 
 * @version (02/04/2021)
 */

import java.util.*;
import edu.duke.*;

public class CodonCount {
    private HashMap<String,Integer> codonMap;
    
    public CodonCount(){
        codonMap = new HashMap<String,Integer>();
    }
    
    private void buildCodonMap(int start, String dna){
        codonMap.clear();
        for (int i = start; i< dna.length()-2; i=i+3){
            String codon = dna.substring(i,i+3);
            if (codonMap.containsKey(codon)){
                codonMap.put(codon,codonMap.get(codon)+1);
            }
            else {
                codonMap.put(codon,1);
            }
        }
    }
    
    private String getMostCommonCodon(){
        int maxCount = 0;
        String mostCommon = "NULL";
        for (String codon : codonMap.keySet()){
            int currCount = codonMap.get(codon);
            if (currCount > maxCount){
                maxCount = currCount;
                mostCommon = codon;
            }
        }
        return mostCommon;
    }
    
    private void printCodonCounts(int start, int end){
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:");
        for (String codon : codonMap.keySet()){
            int count = codonMap.get(codon);
            if (count >= start && count <= end){
                System.out.println(codon+"\t"+count);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        //dna = dna.substring(0,dna.length()-1);
        dna = dna.trim();
        //String dna = "CGTTCAAGTTCAA";
        for (int i=0; i<3; i++){
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with "+i+" results in "+codonMap.size()+" unique codons");
            String mostCommon = getMostCommonCodon();
            System.out.println("The most common codon is "+mostCommon+" with count "+codonMap.get(mostCommon));
            printCodonCounts(1, 5);
            System.out.println();
        }
    }
}
