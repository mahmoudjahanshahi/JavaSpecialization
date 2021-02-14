
/**
 * @author (mahmoudjs14) 
 * @version (02/14/2021)
 */

import java.util.*;

public class Tester {
    
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("./data/short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
       LogAnalyzer la = new LogAnalyzer();
       String filename = "short-test_log";
       la.readFile("./data/"+filename);
       int count = la.countUniqueIPs();
       System.out.println("There are "+count+" unique IPs in the file "+filename);
    }
    
    public void testPrintAllHigherThanNum() {
       LogAnalyzer la = new LogAnalyzer();
       String filename = "short-test_log";
       la.readFile("./data/"+filename);
       int num = 200;
       System.out.println("Entries with status code greater than "+num+" are:");
       la.printAllHigherThanNum(num);
    }
    
    public void testUniqueIPVisitsOnDay() {
       LogAnalyzer la = new LogAnalyzer();
       String filename = "weblog-short_log";
       la.readFile("./data/"+filename);
       String date = "Sep 30";
       ArrayList<String> uniq_IPs = la.uniqueIPVisitsOnDay(date);
       System.out.println("Unique IPs on "+date+" are:");
       for (String ip : uniq_IPs) {
           System.out.println(ip);
       }
    }
    
    public void testCountUniqueIPsInRange() {
       LogAnalyzer la = new LogAnalyzer();
       String filename = "short-test_log";
       la.readFile("./data/"+filename);
       int count1 = la.countUniqueIPsInRange(200,299);
       int count2 = la.countUniqueIPsInRange(300,399);
       System.out.println("There are "+count1+" unique IPs in range (200,299) in the file "+filename);
       System.out.println("There are "+count2+" unique IPs in range (300,399) in the file "+filename);
    }
}
