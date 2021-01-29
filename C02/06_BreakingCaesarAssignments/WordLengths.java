
/**
 * Assignment 1: Word lengths
 * 
 * @author (mahmoudjs14) 
 * @version (01/29/2021)
 */

import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word: resource.words()) {
            int idx = word.length();
            if (!Character.isLetter(word.charAt(0))) {
                idx --;
            }
            if (!Character.isLetter(word.charAt(word.length()-1))) {
                idx --;
            }
            counts[Math.min(idx,counts.length-1)]++;
        }
    }
    
    public int indexOfMax(int[] values) {
        int maxIndex = 0;
        for (int i = 1; i < values.length; i++) {
            if(values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr,counts);
        for (int i = 1; i < counts.length; i++) {
            System.out.println(i+"'s\t -->\t"+counts[i]);
        }
        System.out.println("The most common word length is: "+ indexOfMax(counts));
    }
}
