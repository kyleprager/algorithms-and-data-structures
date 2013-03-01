/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.kprager.questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.kprager.datastructures.trie.Trie;

/**
 * This dictionary is created using the Enable1 dictionary Words With Friends uses.
 * This class is also a Singleton.
 * @author kprager
 */
public class Dictionary {
    private static final Dictionary singleton_dictionary = new Dictionary();
    private static Trie trie;
    
    private Dictionary() {
    }

    /**
     * Get a copy of the Dictionary singleton
     * @return Returns the singleton copy of the Dictionary class.
     */
    public static Dictionary getDictionary() {
        if (trie != null) {
            return singleton_dictionary;
        }
        long millis = System.currentTimeMillis();
        trie = new Trie();
        InputStream is = Dictionary.class.getClassLoader().getResourceAsStream("enable1.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String word;
        try {
            while ((word = br.readLine()) != null) {
                trie.insert(word);
            }
        } catch (IOException ex) {
            throw new RuntimeException("uh oh, our dictionary didn't load!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111", ex);
        }
        System.out.println((System.currentTimeMillis() - millis) / 1000f);
        return singleton_dictionary;
    }
    
    /**
     * Check to see if the dictionary contains a given word.
     * @param s String to search for
     * @return Returns true if the word was found, false otherwise.
     */
    public boolean contains(String s) {
        return trie.search(s);
    }

}
