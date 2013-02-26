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
        List<List<String>> matches = WordSplit.getMatches("peanutbutter");
        for (List<String> match : matches) {
            System.out.println(match);
        }
    }
    
    private static void split(String s, Node curr) {
        if (s == null || curr == null) {
            return;
        }
        
        for(int i = 0; i < s.length(); i++) {
            // check if left-hand-side (LHS) is a word
            if (dict.contains(s.substring(0, i+1))) {
                Node n = new Node(s.substring(0, i+1));
                curr.children.add(n);
                if (i+1 < s.length()) { // recur to the right if we have a RHS
                    split(s.substring(i+1), n);
                } else { // otherwise the whole word matched, so mark it as ended
                    n.end = true;
                }
            }
        }
    }
    
    /**
     * 
     * @param curr current node we're traversing
     * @param ends list of words we are creating by traversing parent nodes
     */
    private static void BFS(Node curr, List<Node> ends) {
        if (curr == null || ends == null) {
            return;
        }
        if (curr.end) {
            ends.add(curr);
        } else {
            for (Node n : curr.children) {
                if (n != null) {
                    n.parent = curr;
                    BFS(n, ends);
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
        Node head = new Node(null);
        split(s, head);
        List<Node> ends = new LinkedList<>();
        BFS(head, ends);
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
}
