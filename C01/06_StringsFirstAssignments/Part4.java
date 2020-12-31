
/**
 * Write a program that reads the lines from the file at this URL location, 
 * http://www.dukelearntoprogram.com/course2/data/manylinks.html, and prints each 
 * URL on the page that is a link to youtube.com. Assume that a link to youtube.com 
 * has no spaces in it and would be in the format (where [stuff] represents 
 * characters that are not verbatim): “http:[stuff]youtube.com[stuff]”
 * 
 * @author (mahmoudjs14) 
 * @version (12/31/2020)
 */

import edu.duke.*;

public class Part4 {
        public void main() {
                URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
                for (String word : ur.words()) {
                        String lword = word.toLowerCase();
                        int youtubeIndex = lword.indexOf("youtube.com");
                        if (youtubeIndex != -1) {
                                int start = lword.lastIndexOf("\"", youtubeIndex);
                                if (start != -1) {
                                        int stop = lword.indexOf("\"", youtubeIndex+11);
                                        if (stop != -1) {
                                                System.out.println(word.substring(start+1,stop));
                                        }
                                }
                        }
                }
        }
}
