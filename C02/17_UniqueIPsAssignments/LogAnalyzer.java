
/**
 * @author (mahmoudjs14) 
 * @version (02/14/2021)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             LogEntry currEntry = WebLogParser.parseEntry(line);
             records.add(currEntry);
         }
            
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> unique_IPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (!unique_IPs.contains(ip)) {
                 unique_IPs.add(ip);
             }
         }
         return unique_IPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             if (le.getStatusCode() > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniq_IPs = new ArrayList<String>();
         for (LogEntry le : records) {
             Date d = le.getAccessTime();
             String date = d.toString().substring(4,10);
             if (date.equals(someday)) {
                 String ip = le.getIpAddress();
                 if (!uniq_IPs.contains(ip)) {
                     uniq_IPs.add(ip);
                 }
             }
         }
         return uniq_IPs;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> unique_IPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             int status_code = le.getStatusCode();
             if (status_code >= low && status_code <= high && !unique_IPs.contains(ip)) {
                 unique_IPs.add(ip);
             }
         }
         return unique_IPs.size();
     }
}
