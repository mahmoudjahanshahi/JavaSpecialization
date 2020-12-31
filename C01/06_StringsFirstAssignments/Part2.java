
/**
 * This assignment will determine if a DNA strand has a gene in it by using the simplified 
 * algorithm from the lesson, but organizing the code in a slightly different way. You 
 * will modify the method findSimpleGene to have three parameters, one for the DNA string, 
 * one for the start codon and one for the stop codon.
 * 
 * @author (mahmoudjs14) 
 * @version (12/31/2020)
 */
public class Part2 {
        public String findSimpleGene(String dna, String startCodon, String stopCodon) {
                boolean upper = dna.equals(dna.toUpperCase());
                boolean lower = dna.equals(dna.toLowerCase());
                if (!upper && !lower) {
                        return "Input DNA not Valid!";
                }
                dna = dna.toLowerCase();
                startCodon = startCodon.toLowerCase();
                stopCodon = stopCodon.toLowerCase();
                int startIndex = dna.indexOf(startCodon);
                if (startIndex == -1) {
                        return "No start Codon";
                }
                int stopIndex = dna.indexOf(stopCodon, startIndex+3);
                if (stopIndex == -1) {
                        return "No stop Codon";
                }
                if ((stopIndex - startIndex) % 3 == 0) {
                        if (upper) {
                                return dna.substring(startIndex, stopIndex+3).toUpperCase();
                        }
                        return dna.substring(startIndex, stopIndex+3);
                }
                else {
                        return "Not Valid Gene";
                }
        }
        
        public void testSimpleGene() {
                String dna = "AACATGGACGCGTAACGTAACCGA";    //valid Upper
                String result = findSimpleGene(dna, "ATG", "TAA");
                System.out.println("The DNA: " + dna);
                System.out.println("The Gene: " + result);
                
                dna = "aacatggacgcgtaacgtaaccga";    //valid Lower
                result = findSimpleGene(dna, "ATG", "TAA");
                System.out.println("The DNA: " + dna);
                System.out.println("The Gene: " + result);
                
                dna = "aacatggacgcGtaacgtaaccga";    //not valid DNA
                result = findSimpleGene(dna, "ATG", "TAA");
                System.out.println("The DNA: " + dna);
                System.out.println("The Gene: " + result);
                
                dna = "AACATGGACCGTAACGTAACCGA";     //not valid Gene
                result = findSimpleGene(dna, "ATG", "TAA");
                System.out.println("The DNA: " + dna);
                System.out.println("The Gene: " + result);
                
                dna = "AACAAGGACGCGTAACGTAACCGA";    //no ATG
                result = findSimpleGene(dna, "ATG", "TAA");
                System.out.println("The DNA: " + dna);
                System.out.println("The Gene: " + result);
                
                dna = "AACATGGACGCGTAGACGTACACCGA";  //no TAA
                result = findSimpleGene(dna, "ATG", "TAA");
                System.out.println("The DNA: " + dna);
                System.out.println("The Gene: " + result);
                
                dna = "AAATGCCCTAACTAGATTAAGAAACC";  //quiz
                result = findSimpleGene(dna, "ATG", "TAA");
                System.out.println("The DNA: " + dna);
                System.out.println("The Gene: " + result);
        }
}
