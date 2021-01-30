
/**
 * Assignment 2: Two Keys
 * 
 * @author (mahmoudjs14) 
 * @version (01/30/2021)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    
    private void countLetters(String text, int[] counts) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < text.length(); i++) {
            int idx = alphabet.indexOf(Character.toLowerCase(text.charAt(i)));
            if (idx != -1) {
                counts[idx]++;
            }
        }
    }
    
    private int maxIndex(int[] values) {
        int maxIndex = 0;
        for (int i = 1; i < values.length; i++) {
            if(values[i] >= values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    private int getKey(String s) {
        int[] counts = new int[26];
        countLetters(s, counts);
        int maxIndex = maxIndex(counts);
        int key = maxIndex - 4;
        if (key < 0) {
            key = 22 + maxIndex;
        }
        return key;
    }
    
    private String halfOfString(String message, int start) {
        StringBuilder half = new StringBuilder();
        for (int i=start; i < message.length(); i += 2) {
            half.append(message.charAt(i));
        }
        return half.toString();
    }
    
    private String breakCaesarCipherTwo(String input) {
        String half0 = halfOfString(input, 0);
        String half1 = halfOfString(input, 1);
        int key1 = getKey(half0);
        int key2 = getKey(half1);
        CaesarCipherTwo cct = new CaesarCipherTwo(26-key1,26-key2);
        String decrypted = cct.encrypt(input);
        return decrypted;
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipherTwo cct = new CaesarCipherTwo(17,3);
        String encrypted = cct.encrypt(input);
        System.out.println("Encrypted text:\n"+encrypted);
        String decrypted = breakCaesarCipherTwo(encrypted);
        System.out.println("Decrypted text using breaker:\n"+decrypted);
    }
}
