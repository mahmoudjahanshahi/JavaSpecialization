
/** 
 * @author (mahmoudjs14) 
 * @version (02/17/2021)
 */

import java.util.*;
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
        CaesarCracker ccr = new CaesarCracker();
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
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int max_count = 0;
        VigenereCipher bestVC = null;
        String decrypted = "";
        for (int i = 1; i < 101; i++) {
            int[] currKey = tryKeyLength(encrypted, i, 'e');
            VigenereCipher vc = new VigenereCipher(currKey);
            String currDecrypted = vc.decrypt(encrypted);
            int count = countWords(currDecrypted, dictionary);
            if (count > max_count) {
                max_count = count;
                bestVC = vc;
                decrypted = currDecrypted;
            }
        }
        System.out.println("The key is:\t"+bestVC+"\nValid Words count:\t"+max_count);
        return decrypted;
    }
    
    public void breakVigenere() {
        FileResource fr1 = new FileResource();
        String encrypted = fr1.asString();
        FileResource fr2 = new FileResource();
        HashSet<String> dictionary = readDictionary(fr2); 
        String decrypted = breakForLanguage(encrypted, dictionary);
        System.out.println();
        System.out.println(decrypted);
    }
    
}
