
/**
 * Part 2: HowMany - Finding Multiple Occurrences
 * 
 * @author (mahmoudjs14) 
 * @version (01/01/2021)
 */

public class Part2 {
    public int howMany(String stringa, String stringb) {
        int result = 0;
        int curIndex = 0;
        while (true) {
            int occur = stringb.indexOf(stringa, curIndex);
            if (occur == -1) {
                break;
            }
            result ++;
            curIndex = occur + stringa.length();
        }
        return result;
    }
    
    public void testHowMany() {
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        int result = howMany(stringa, stringb);
        if (result != 3) {
            System.out.println("Test case 1 failed!");
        }
        
        stringa = "AA";
        stringb = "ATAAAA";
        result = howMany(stringa, stringb);
        if (result != 2) {
            System.out.println("Test case 2 failed!");
        }
        
        stringa = "GAG";
        stringb = "GAGAGAGAGAGAGAGA";
        result = howMany(stringa, stringb);
        if (result != 4) {
            System.out.println("Test case 3 failed!");
        }
        
        System.out.println("Tests finished!");
    }
}
