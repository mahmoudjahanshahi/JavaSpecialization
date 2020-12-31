
/**
 * This assignment will give you additional practice using String methods. 
 * You will write two methods to solve some problems using strings and a 
 * third method to test these two methods.
 * 
 * @author (mahmoudjs14) 
 * @version (12/31/2020)
 */
public class Part3 {
        public boolean twoOccurrences(String stringa, String stringb) {
                int first = stringb.indexOf(stringa);
                if (first == -1) {
                        return false;
                }
                int second = stringb.indexOf(stringa, first+stringa.length() );
                if (second == -1) {
                        return false;
                }
                return true;
        }
        
        public String lastPart(String stringa, String stringb) {
                int first = stringb.indexOf(stringa);
                if (first == -1) {
                        return stringb;
                }
                return stringb.substring(first+stringa.length(),stringb.length());
        }
        
        public void testing() {
                String stringa = "by";
                String stringb = "A story by Abby Long";
                System.out.println("\"" + stringa + "\" occurs in \"" + stringb + "\" at least two times: " + twoOccurrences(stringa, stringb));
                
                stringa = "a";
                stringb = "banana";
                System.out.println("\"" + stringa + "\" occurs in \"" + stringb + "\" at least two times: " + twoOccurrences(stringa, stringb));
                
                stringa = "atg";
                stringb = "ctgtatgta";
                System.out.println("\"" + stringa + "\" occurs in \"" + stringb + "\" at least two times: " + twoOccurrences(stringa, stringb));
                
                stringa = "an";
                stringb = "banana";
                System.out.println("Part of \"" + stringb + "\" after \"" + stringa + "\" is: " + lastPart(stringa, stringb));
                
                stringa = "zoo";
                stringb = "forest";
                System.out.println("Part of \"" + stringb + "\" after \"" + stringa + "\" is: " + lastPart(stringa, stringb));
        }
}
