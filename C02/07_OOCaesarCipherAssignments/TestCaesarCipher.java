
/**
 * Assignment 1: One Key
 * 
 * @author (mahmoudjs14) 
 * @version (01/30/2021)
 */

import edu.duke.*;

public class TestCaesarCipher {
    
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
    
    private String breakCaesarCipher(String input) {
        int key = getKey(input);
        CaesarCipher cc = new CaesarCipher(key);
        String decrypted = cc.decrypt(input);
        return decrypted;
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(input);
        System.out.println("Encrypted text:\n"+encrypted);
        String decrypted = breakCaesarCipher(encrypted);
        System.out.println("Decrypted text using breaker:\n"+decrypted);
    }
}
