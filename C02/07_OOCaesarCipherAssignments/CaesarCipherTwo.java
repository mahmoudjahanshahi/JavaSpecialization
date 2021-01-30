
/**
 * Assignment 2: Two Keys
 * 
 * @author (mahmoudjs14) 
 * @version (01/30/2021)
 */

public class CaesarCipherTwo {
    
    private CaesarCipher cc1;
    private CaesarCipher cc2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        cc1 = new CaesarCipher(key1);
        cc2 = new CaesarCipher(key2);
        mainKey1 = key1%26;
        mainKey2 = key2%26;        
    }
    
    public String encrypt(String input) {
       String encrypt1 = cc1.encrypt(input);
       String encrypt2 = cc2.encrypt(input);
       StringBuilder encrypt = new StringBuilder(input);
       for (int i = 0; i < input.length(); i += 2) {
            encrypt.setCharAt(i,encrypt1.charAt(i));
       }
       for (int i = 1; i < input.length(); i += 2) {
            encrypt.setCharAt(i,encrypt2.charAt(i));
       }
       return encrypt.toString();
    }
    
    private String halfOfString(String message, int start) {
        StringBuilder half = new StringBuilder();
        for (int i=start; i < message.length(); i += 2) {
            half.append(message.charAt(i));
        }
        return half.toString();
    }
    
    public String decrypt(String input) {
        String half0 = halfOfString(input, 0);
        String half1 = halfOfString(input, 1);
        CaesarCipherTwo cct = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
        String decrypted = cct.encrypt(input);
        return decrypted;
    }
}
