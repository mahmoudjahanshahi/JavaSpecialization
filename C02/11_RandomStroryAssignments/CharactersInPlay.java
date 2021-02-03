
/**
 * Assignment 2: Character Names
 * 
 * @author (mahmoudjs14) 
 * @version (02/03/2021)
 */

import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    private void update(String person) {
        String name = person; //.toLowerCase();
            int index = names.indexOf(name);
            if (index == -1) {
                names.add(name);
                counts.add(1);
            }
            else {
                int value = counts.get(index);
                counts.set(index, value+1);
            }
    }
    
    private void findAllCharacters() {
        names.clear();
        counts.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int periodIndex = line.indexOf(".");
            if (periodIndex != -1) {
                update(line.substring(0,periodIndex));
            }
        }
    }
    
    private void charactersWithNumParts(int num1, int num2) {
        for (int i=0; i < names.size(); i++) {
            int value = counts.get(i);
            if (value >= num1 && value <= num2) {
                System.out.println(names.get(i)+" "+value);
            }
        }
    }
    
    private int findMax() {
        int maxIndex = 0;
        int maxValue = counts.get(0);
        for (int i=1; i < counts.size(); i++) {
            int value = counts.get(i);
            if (value > maxValue) {
                maxValue = value;
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void tester() {
       findAllCharacters();
       charactersWithNumParts(10,15);
       int maxIndex = findMax();
       System.out.println("Character \""+names.get(maxIndex)+"\" has the most parts which is :"+counts.get(maxIndex));
    }
}
