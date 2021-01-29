
/**
 * Caesar Cipher
 * 
 * @author (mahmoudjs14) 
 * @version (01/28/2021)
 */

import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        int myKey = key%26;
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedUpper = upperAlphabet.substring(myKey) + upperAlphabet.substring(0,myKey);
        String shiftedLower = lowerAlphabet.substring(myKey) + lowerAlphabet.substring(0,myKey);
        String alphabet = upperAlphabet + lowerAlphabet;
        String shiftedAlphabet = shiftedUpper + shiftedLower;
        StringBuilder myInput = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currChar = myInput.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
                myInput.setCharAt(i,shiftedAlphabet.charAt(idx));
            }
        }
        return myInput.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
       String encrypt1 = encrypt(input, key1);
       String encrypt2 = encrypt(input, key2);
       StringBuilder encrypt = new StringBuilder(input);
       for (int i = 0; i < input.length(); i += 2) {
            encrypt.setCharAt(i,encrypt1.charAt(i));
       }
       for (int i = 1; i < input.length(); i += 2) {
            encrypt.setCharAt(i,encrypt2.charAt(i));
       }
       return encrypt.toString();
    }
    
    public void testCaesar() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println("Original message:\n" + decrypted);
    }
    
    public void testEncryptTwoKeys() {
        String test = "First Legion";
        int key1 = 23;
        int key2 = 17;
        System.out.println(test + ", " + key1 + ", " + key2 + " ---> " + encryptTwoKeys(test, key1, key2));
    }
}
