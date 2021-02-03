
/**
 * Assignment 1: Most Frequent Word
 * 
 * @author (mahmoudjs14) 
 * @version (02/03/2021)
 */

import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    private void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            String currWord = word.toLowerCase();
            int index = myWords.indexOf(currWord);
            if (index == -1) {
                myWords.add(currWord);
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    private int findMax() {
        int maxIndex = 0;
        int maxValue = myFreqs.get(0);
        for (int i=1; i < myFreqs.size(); i++) {
            int value = myFreqs.get(i);
            if (value > maxValue) {
                maxValue = value;
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void tester() {
        findUnique();
        int maxIndex = findMax();
        System.out.println("Number of uniq words: "+myWords.size());
        /*
        for (int i=0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i)+" "+myWords.get(i));
        }
        */
        System.out.println("The most frequent word is \""+myWords.get(maxIndex)+"\" which accures "+myFreqs.get(maxIndex)+" times.");
    }
}
