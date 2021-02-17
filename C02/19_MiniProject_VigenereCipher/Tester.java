
/** 
 * @author (mahmoudjs14) 
 * @version (02/17/2021)
 */

import edu.duke.*;

public class Tester {
    
    public void testCaesarCipher() {
        int key = 5;
        CaesarCipher cc = new CaesarCipher(key);
        System.out.println("Testing Caesar Cipher");
        System.out.println("The key is: \t"+cc);
        
        char my_char = 'a';
        char my_char_en = cc.encryptLetter(my_char);
        char my_char_de = cc.decryptLetter(my_char_en);
        System.out.println("\nTesting encryptLetter and decryptLetter methods");
        System.out.println("The encrypted letter is \""+my_char_en+"\" and it was originally \""+my_char_de+"\"");
        
        FileResource fr = new FileResource("./data/TestData/titus-small.txt");
        String text = fr.asString();
        String encrypted = cc.encrypt(text);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("\nTesting encrypt and decrypt methods");
        System.out.println("The encrypted text is:\n"+encrypted+"\nand it was originally:\n"+decrypted);
    }
    
    public void testCaesarCracker() {
        System.out.println("Testing Caesar Cracker");
        CaesarCracker ccr1 = new CaesarCracker();
        String filename1 = "titus-small_key5.txt";
        FileResource fr1 = new FileResource("./data/TestData/"+filename1);
        String encrypted1 = fr1.asString();
        String decrypted1 = ccr1.decrypt(encrypted1);
        System.out.println("\nDecrypting English ("+filename1+")\n"+decrypted1);
        
        CaesarCracker ccr2 = new CaesarCracker('a');
        String filename2 = "oslusiadas_key17.txt";
        FileResource fr2 = new FileResource("./data/TestData/"+filename2);
        String encrypted2 = fr2.asString();
        String decrypted2 = ccr2.decrypt(encrypted2);
        System.out.println("\nDecrypting Portuguese where the most common character is ‘a’ ("+filename2+")\n"+decrypted2);
    }
    
    public void testVigenereCipher() {
        System.out.println("Testing Vigenere Cipher");
        int[] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println("The key is: \t"+vc);
        
        FileResource fr = new FileResource("./data/TestData/titus-small.txt");
        String text = fr.asString();
        String encrypted = vc.encrypt(text);
        String decrypted = vc.decrypt(encrypted);
        System.out.println("\nTesting encrypt and decrypt methods");
        System.out.println("The encrypted text is:\n"+encrypted+"\nand it was originally:\n"+decrypted);
    }
}
