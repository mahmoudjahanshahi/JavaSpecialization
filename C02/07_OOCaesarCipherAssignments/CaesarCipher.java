
/**
 * Assignment 1: One Key
 * 
 * @author (mahmoudjs14) 
 * @version (01/30/2021)
 */

public class CaesarCipher {
    
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key) {
        mainKey = key%26;
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedUpper = upperAlphabet.substring(mainKey) + upperAlphabet.substring(0,mainKey);
        String shiftedLower = lowerAlphabet.substring(mainKey) + lowerAlphabet.substring(0,mainKey);
        alphabet = upperAlphabet + lowerAlphabet;
        shiftedAlphabet = shiftedUpper + shiftedLower;
    }
    
    public String encrypt(String input) {
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
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
