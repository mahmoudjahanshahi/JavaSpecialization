
/**
 * Assignment 1: Word Play 
 * 
 * @author (mahmoudjs14) 
 * @version (01/28/2021)
 */

public class WordPlay {
    public boolean isVowel(char ch) {
        char c = Character.toLowerCase(ch);
        String vowels = "aeiou";
        if (vowels.indexOf(c) != -1) {
            return true;
        }
        return false;
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder myPhrase = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = myPhrase.charAt(i);
            if (isVowel(currChar)) {
                myPhrase.setCharAt(i,ch);
            }
        }
        return myPhrase.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder myPhrase = new StringBuilder(phrase);
        char c = Character.toLowerCase(ch);        
        String caseInsensitivePhrase = phrase.toLowerCase();
        int startIdx = 0;
        while (true) {
            int idx = caseInsensitivePhrase.indexOf(c,startIdx);
            if (idx == -1) {
                break;
            }
            if ((idx+1)%2 == 0) {
                myPhrase.setCharAt(idx,'+');
            }
            else {
                myPhrase.setCharAt(idx,'*');
            }
            startIdx = idx + 1;
        }
        return myPhrase.toString();
    }
    
    public void test() {
        System.out.println("Testing isVowel");
        System.out.println("F: " + isVowel('F'));
        System.out.println("f: " + isVowel('f'));
        System.out.println("A: " + isVowel('A'));
        System.out.println("a: " + isVowel('a'));
        System.out.println("1: " + isVowel('1'));
        System.out.println();
        System.out.println("Testing replaceVowels");
        String test = "Hello World";
        System.out.println(test + " -> " + replaceVowels(test,'*'));
        System.out.println();
        System.out.println("Testing emphasize");
        System.out.println("emphasize(“dna ctgaaactga”, ‘a’)" + " -> " + emphasize("dna ctgaaactga", 'a'));
        System.out.println("emphasize(“Mary Bella Abracadabra”, ‘a’)" + " -> " + emphasize("Mary Bella Abracadabra", 'a'));
    }
}
