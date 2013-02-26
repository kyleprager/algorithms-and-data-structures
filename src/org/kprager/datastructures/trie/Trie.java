/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.datastructures.trie;



/**
 * Java didn't have one of these.  Who knew there was a data structure they didn't offer.
 * @author kyleprager
 */
public class Trie {
    
    Node root;
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] arr = {"kyle", "kylie", "prager", "prayger"};
        
        for (int i = 0; i < arr.length; i++) {
            trie.insert(arr[i]);
        }
        
        for (int i = 0; i < arr.length; i++) {
            boolean b = trie.search(arr[i]);
            System.out.println(b);
        }
    }
    
    public Trie() {
        // root is left blank to represent the empty string "".  But we need to
        // create a node with a character so we choose a space (' ').
        root = new Node(' ');
    }
    
    public void insert(String s) {
        Node curr = root;
        Node tmp;
        if (s.length() == 0) {
            root.marker = true;
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            tmp = curr.getChild(c);
            if (tmp == null) {
                tmp = new Node(c);
                curr.children.add(tmp);
                if (i == s.length() -1) { // mark if we are at the end of the word.
                    tmp.marker = true;
                }
            }
            curr = tmp;
        }
    }
    
    public boolean search(String s) {
        
        if (s.length() == 0) {
            root.marker = true;
            return true;
        }
        
        Node curr = root;
        int ctr = 0;
        while (ctr < s.length()) {
            char c = s.charAt(ctr);
            Node child = curr.getChild(c);
            if (child == null) {
                return false;
            } else {
                curr = child;
            }
            ctr++;
        }
        if (curr.marker) {
            return true;
        } else {
            return false;
        }
    }
}
