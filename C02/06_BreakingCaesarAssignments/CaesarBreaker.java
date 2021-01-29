
/**
 * Assignment 2:  Caesar Cipher Two Keys Decrypt
 * 
 * @author (mahmoudjs14) 
 * @version (01/29/2021)
 */

import edu.duke.*;

public class CaesarBreaker {
    public void countLetters(String text, int[] counts) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < text.length(); i++) {
            int idx = alphabet.indexOf(Character.toLowerCase(text.charAt(i)));
            if (idx != -1) {
                counts[idx]++;
            }
        }
    }
    
    public int maxIndex(int[] values) {
        int maxIndex = 0;
        for (int i = 1; i < values.length; i++) {
            if(values[i] >= values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public int getKey(String s) {
        int[] counts = new int[26];
        countLetters(s, counts);
        int maxIndex = maxIndex(counts);
        int key = maxIndex - 4;
        if (key < 0) {
            key = 22 + maxIndex;
        }
        return key;
    }
    
    public String decrypt(String encrypted) {
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encrypt(encrypted, 26 - key);
        return decrypted;
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder half = new StringBuilder();
        for (int i=start; i < message.length(); i += 2) {
            half.append(message.charAt(i));
        }
        return half.toString();
    }
    
    public String decryptTwoKeys(String encrypted) {
        String half0 = halfOfString(encrypted, 0);
        String half1 = halfOfString(encrypted, 1);
        int key1 = getKey(half0);
        int key2 = getKey(half1);
        System.out.println("Key1 is:\t" + key1);
        System.out.println("Key2 is:\t" + key2);
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
        return decrypted;
    }
    
    public void testDecrypt() {
        String text = "This is just a test, so I need lots of eeeeeeeeeeees!";
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(text, 15828);
        String decrypted = decrypt(encrypted);
        System.out.println("Input: \n" + encrypted);
        System.out.println("Decrypted: \n" + decrypted);
    }
    
    public void testHalfOfString() {
        String text = "This is just a test!";
        String half1 = halfOfString(text, 0);
        String half2 = halfOfString(text, 1);
        String half3 = halfOfString(text, 10);
        System.out.println("Text: "+text);
        System.out.println("half from 0: "+half1);
        System.out.println("half from 1: "+half2);
        System.out.println("half from 10: "+half3);
    }
    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println("Input: \n" + encrypted);
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println("\nDecrypted: \n" + decrypted);
    }
}
