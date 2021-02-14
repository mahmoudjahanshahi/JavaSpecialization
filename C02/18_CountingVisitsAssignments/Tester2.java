
/**
 * Assignment: Website Visits
 * 
 * @author (mahmoudjs14) 
 * @version (02/14/2021)
 */

import java.util.*;

public class Tester2 {
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        String filename = "weblog3-short_log";
        la.readFile("./data/"+filename);
        HashMap<String,Integer> visitCounts = la.countVisitsPerIP();
        System.out.println("Visit counts per IP:");
        for (String ip : visitCounts.keySet()) {
            System.out.println(ip+"\t"+visitCounts.get(ip));
        }
        System.out.println();
        int max = la.mostNumberVisitsByIP(visitCounts);
        System.out.println("Maximum number of visits by a single IP address: "+max);
        ArrayList<String> maxIPs = la.iPsMostVisits(visitCounts);
        System.out.println();
        System.out.println("The IPs visited "+max+" times are:");
        for (String ip : maxIPs) {
            System.out.println(ip);
        }
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        String filename = "weblog3-short_log";
        la.readFile("./data/"+filename);
        HashMap<String,ArrayList<String>> daysIPs = la.iPsForDays();
        for (String date : daysIPs.keySet()) {
            System.out.println(date);
            for (String ip : daysIPs.get(date)) {
                System.out.println(ip);
            }
            System.out.println();
        }
        String mostVisitDay = la.dayWithMostIPVisits(daysIPs);
        System.out.println("The day that has the most IP address visits:\t"+mostVisitDay);
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        String filename = "weblog3-short_log";
        la.readFile("./data/"+filename);
        HashMap<String,ArrayList<String>> daysIPs = la.iPsForDays();
        String date = "Sep 30";
        ArrayList<String> maxIPs = la.iPsWithMostVisitsOnDay(daysIPs, date);
        System.out.println("IP addresses that had the most accesses on "+date);
        for (String ip : maxIPs) {
            System.out.println(ip);
        }
    }
}
