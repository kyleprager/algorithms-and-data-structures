/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.questions;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Given a string, ex: "peanutbutter", split it into all possible lists of subwords.
 * EX: yes: "peanut butter", no: "pea nut butt er" - "er" is not a word.
 * @author kyleprager
 */
public class WordSplit {
    
    private static Set<String> dict = Dictionary.getDictionary();
    
    public static void main(String[] args) {
        WordSplit.printSplit("peanutbutter");
        WordSplit.printSplit("teatime");
        WordSplit.printSplit("forwhomthebelltolls");
        WordSplit.printSplit("flyinghome");
        WordSplit.printSplit("butterrible");
    }
    
    private static void split(String s, Node curr, List<Node> ends) {
        if (s == null || curr == null) {
            return;
        }
        
        for(int i = 0; i < s.length(); i++) {
            // check if left-hand-side (LHS) is a word
            if (dict.contains(s.substring(0, i+1))) {
                Node n = new Node(s.substring(0, i+1));
                n.parent = curr;
                curr.children.add(n);
                if (i+1 < s.length()) { // recur to the right if we have a RHS
                    split(s.substring(i+1), n, ends);
                } else { // otherwise the whole word matched, so mark it as ended
                    n.end = true;
                    ends.add(n);
                }
            }
        }
    }
    
    /**
     * 
     * @param s string we are splitting apart
     * @return List<List<String>> which represents all the lists of valid words our original
     * string can be split into.
     */
    public static List<List<String>> getMatches(String s) {
        s = s.toLowerCase(); // we want this to work with our lower-case dictionary
        Node head = new Node(null);
        List<Node> ends = new LinkedList<>();
        split(s, head, ends);
        List<List<String>> matches = new LinkedList<>();
        for (Node n : ends) {
            List<String> list = new LinkedList<>();
            while (n!=null) {
                if (n.value != null) {
                    list.add(n.value);
                }
                n = n.parent;
            }
            matches.add(list);
        }
        for (List<String> list : matches) {
            Collections.reverse(list);
        }
        return matches;
    }
    
    public static void printSplit(String s) {
        List<List<String>> matches = WordSplit.getMatches(s);
        System.out.println("Matches for: " + s);
        for (List<String> match : matches) {
            System.out.println(match);
        }
    }
}
