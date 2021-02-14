
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
         ArrayList<String> uniqIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (!uniqIPs.contains(ip)) {
                 uniqIPs.add(ip);
             }
         }
         return uniqIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             if (le.getStatusCode() > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             Date d = le.getAccessTime();
             String date = d.toString().substring(4,10);
             if (date.equals(someday)) {
                 String ip = le.getIpAddress();
                 if (!uniqIPs.contains(ip)) {
                     uniqIPs.add(ip);
                 }
             }
         }
         return uniqIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             int status_code = le.getStatusCode();
             if (status_code >= low && status_code <= high && !uniqIPs.contains(ip)) {
                 uniqIPs.add(ip);
             }
         }
         return uniqIPs.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP() {
         HashMap<String,Integer> visitCounts = new HashMap<String,Integer>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (visitCounts.containsKey(ip)) {
                 visitCounts.put(ip, visitCounts.get(ip)+1);
             }
             else {
                 visitCounts.put(ip, 1);
             }
         }
         return visitCounts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> visitCounts) {
         int max = 0;
         for (Integer count : visitCounts.values()) {
             if (count > max) {
                 max = count;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> visitCounts) {
         ArrayList<String> maxIPs = new ArrayList<String>();
         int max = mostNumberVisitsByIP(visitCounts);
         for (String ip : visitCounts.keySet()) {
             if (visitCounts.get(ip) == max) {
                 maxIPs.add(ip);
             }
         }
         return maxIPs;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays() {
         HashMap<String,ArrayList<String>> daysIPs = new HashMap<String,ArrayList<String>>();
         for (LogEntry le : records) {
             Date d = le.getAccessTime();
             String date = d.toString().substring(4,10);
             if (!daysIPs.containsKey(date)) {
                 ArrayList<String> currIPs = new ArrayList<String>();
                 daysIPs.put(date, currIPs);
             }
             String ip = le.getIpAddress();
             daysIPs.get(date).add(ip);
         }
         return daysIPs;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> daysIPs) {
         int max = 0;
         String maxDate = "";
         for (String date : daysIPs.keySet()) {
             int size = daysIPs.get(date).size();
             if (size > max) {
                 max = size;
                 maxDate = date;
             }
         }
         return maxDate;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> daysIPs, String date) {
         HashMap<String,Integer> visitCounts = new HashMap<String,Integer>();
         for (String ip : daysIPs.get(date)) {
             if (visitCounts.containsKey(ip)) {
                 visitCounts.put(ip, visitCounts.get(ip)+1);
             }
             else {
                 visitCounts.put(ip, 1);
             }
         }
         ArrayList<String> maxIPs = iPsMostVisits(visitCounts);
         return maxIPs;
     }
}
