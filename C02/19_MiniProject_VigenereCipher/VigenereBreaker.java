
/** 
 * @author (mahmoudjs14) 
 * @version (02/17/2021)
 */

import java.util.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            slice.append(message.charAt(i));
        }
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker ccr = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            key[i] = ccr.getKey(slice);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String word : fr.lines()) {
            dictionary.add(word.toLowerCase());
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (String word : dictionary) {
            for(int i = 0; i < word.length(); i++) {
                int dex = alphabet.indexOf(Character.toLowerCase(word.charAt(i)));
                if (dex != -1){
                    counts[dex]++;
                }
            }
        }
        int maxDex = 0;
        for(int i = 1; i < counts.length; i++){
            if (counts[i] > counts[maxDex]){
                maxDex = i;
            }
        }
        return alphabet.charAt(maxDex);
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int max_count = 0;
        VigenereCipher bestVC = null;
        String decrypted = "";
        char most_common = mostCommonCharIn(dictionary);
        for (int i = 1; i < 101; i++) {
            int[] currKey = tryKeyLength(encrypted, i, most_common);
            VigenereCipher vc = new VigenereCipher(currKey);
            String currDecrypted = vc.decrypt(encrypted);
            int count = countWords(currDecrypted, dictionary);
            if (count > max_count) {
                max_count = count;
                bestVC = vc;
                decrypted = currDecrypted;
            }
        }
        System.out.println("The most common character is:\t"+most_common);
        System.out.println("The key is:\t"+bestVC);
        System.out.println("Ciphers count:\t"+bestVC.ciphers.length);
        System.out.println("Valid Words count:\t"+max_count);
        return decrypted;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages) {
        int max_count = 0;
        String language = "";
        String decrypted = "";
        System.out.println();
        for (String lan : languages.keySet()) {
            HashSet<String> dictionary = languages.get(lan);
            System.out.println("The language:\t"+lan);
            String currDecrypted = breakForLanguage(encrypted, dictionary);
            int count = countWords(currDecrypted, dictionary);
            if (count > max_count) {
                max_count = count;
                language = lan;
                decrypted = currDecrypted;
            }
        }
        System.out.println("\nThe correct language:\t"+language);
        System.out.println("Valid Words count:\t"+max_count);
        System.out.println("\nThe message:\n"+decrypted);
    }
    
    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        DirectoryResource dr = new DirectoryResource();
        HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>>();
        for (File f : dr.selectedFiles()) {
            FileResource cfr = new FileResource(f);
            String language = f.getName();
            HashSet<String> dictionary = readDictionary(cfr);
            languages.put(language,dictionary);
            System.out.println("Read "+language+" dictionary successfully!");
        }
        breakForAllLangs(encrypted, languages);
    }
    
}
