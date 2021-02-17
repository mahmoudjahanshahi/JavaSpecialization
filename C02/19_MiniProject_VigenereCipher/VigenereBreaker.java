
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

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int klength = 5;
        int[] key = tryKeyLength(encrypted, klength, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
    }
    
}
