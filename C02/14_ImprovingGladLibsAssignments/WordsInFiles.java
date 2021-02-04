
/**
 * Assignment 2: Words in Files
 * 
 * @author (mahmoudjs14) 
 * @version (02/04/2021)
 */

import java.util.*;
import java.io.*;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> wordMap;
    
    public WordsInFiles(){
        wordMap = new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        String filename = f.getName();
        FileResource fr = new FileResource(f);
        for (String word: fr.words()){
            if (wordMap.containsKey(word)){
                if (!wordMap.get(word).contains(filename)){
                    wordMap.get(word).add(filename);
                }
            }
            else {
                ArrayList<String> al = new ArrayList<String>();
                al.add(filename);
                wordMap.put(word,al);
            }
        }
    }
    
    private void buildWordFileMap(){
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber(){
        int maxNumber = 0;
        for (String word: wordMap.keySet()){
            int currNumber = wordMap.get(word).size();
            if (currNumber > maxNumber){
                maxNumber = currNumber;
            }
        }
        return maxNumber;
    }
    
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        for (String word: wordMap.keySet()){
            int currNumber = wordMap.get(word).size();
            if (currNumber == number){
                words.add(word);
            }
        }
        return words;
    }
    
    private void printFilesIn(String word){
        ArrayList<String> files = wordMap.get(word);
        for (String filename: files){
            System.out.println(filename);
        }
    }
    
    private void printMap(){
        for (String word: wordMap.keySet()){
            System.out.println("\""+word+"\" appears in the files:");
            printFilesIn(word);
        }
    }
    
    public void tester(){
        buildWordFileMap();
        int maxNumber = maxNumber();
        ArrayList<String> maxWords = wordsInNumFiles(maxNumber);
        System.out.println("The greatest number of files a word appears in is "+maxNumber+" and there are "+maxWords.size()+" such words:");
        for (String word: maxWords){
            System.out.print("\""+word+"\"\t");
        }
        System.out.println("\n");
        for (String word: maxWords){
            System.out.println("\""+word+"\" appears in the files:");
            printFilesIn(word);
        }
        System.out.println();
        System.out.println("The complete map:");
        printMap();
    }
}
