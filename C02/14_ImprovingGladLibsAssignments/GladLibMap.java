
/**
 * Assignment 3: Maps Version of GladLibs
 * 
 * @author (mahmoudjs14) 
 * @version (02/04/2021)
 */

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedLabels;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data/long";
    
    public GladLibMap() {
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        usedLabels = new ArrayList<String>();
    }
    
    public GladLibMap(String source) {
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        usedLabels = new ArrayList<String>();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"adjective","noun","color","country","name","animal",
                           "timeframe","verb","fruit"};
        for (String label: labels) {
            ArrayList<String> words = readIt(source+"/"+label+".txt");
            myMap.put(label,words);
        }
    }
    
    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (!usedLabels.contains(label)) {
            usedLabels.add(label);
        }
        if (myMap.containsKey(label)) {
            ArrayList<String> words = myMap.get(label);
            return randomFrom(words);
        }
        if (label.equals("number")) {
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (usedWords.contains(sub)) {                        //possible infinite loop!
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedWords.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for(String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()) {
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap() {
        int count = 0;
        for (String label: myMap.keySet()) {
            ArrayList<String> words = myMap.get(label);
            count += words.size();
        }
        return count;
    }
    
    private int totalWordsConsidered() {
        int count = 0;
        for (String label: usedLabels) {
            if (myMap.containsKey(label)) {
                ArrayList<String> words = myMap.get(label);
                count += words.size();
            }
        }
        return count;
    }
    
    public void makeStory() {
        usedWords.clear();
        String story = fromTemplate(dataSourceDirectory+"/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n\nTotal number of label changes: "+usedWords.size());
        int totalWords = totalWordsInMap();
        System.out.println("\nThere were "+totalWords+" words that were possible to pick from.");
        int consideredWords = totalWordsConsidered();
        System.out.println("\n"+consideredWords+" words were considered.");
    }
}
