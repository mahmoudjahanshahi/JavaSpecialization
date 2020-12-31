
/**
 * Finds a Gene within a strand of DNA represented as a string of C,G,T,A letters.
 * 
 * @author (mahmoudjs14) 
 * @version (12/31/2020)
 */
public class Part1 {
        public String findSimpleGene(String dna) {
                int startIndex = dna.indexOf("ATG");
                if (startIndex == -1) {
                        return "No ATG";
                }
                int stopIndex = dna.indexOf("TAA", startIndex+3);
                if (stopIndex == -1) {
                        return "No TAA";
                }
                if ((stopIndex - startIndex) % 3 == 0) {
                        return dna.substring(startIndex, stopIndex+3);
                }
                else {
                        return "Not Valid Gene";
                }
        }
        
        public void testSimpleGene() {
                String dna = "AACATGGACGCGTAACGTAACCGA";    //valid
                String result = findSimpleGene(dna);
                System.out.println("The DNA: " + dna);
                System.out.println("The Gene: " + result);
                
                dna = "AACATGGACCGTAACGTAACCGA";     //not valid
                result = findSimpleGene(dna);
                System.out.println("The DNA: " + dna);
                System.out.println("The Gene: " + result);
                
                dna = "AACAAGGACGCGTAACGTAACCGA";    //no ATG
                result = findSimpleGene(dna);
                System.out.println("The DNA: " + dna);
                System.out.println("The Gene: " + result);
                
                dna = "AACATGGACGCGTAGACGTACACCGA";  //no TAA
                result = findSimpleGene(dna);
                System.out.println("The DNA: " + dna);
                System.out.println("The Gene: " + result);
        }
}
