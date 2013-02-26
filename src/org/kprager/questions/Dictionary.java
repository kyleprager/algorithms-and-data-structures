/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.kprager.questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 * @author kprager
 */
public class Dictionary {
    private static Set<String> dictionary;

    public static Set<String> getDictionary() {
        if (dictionary != null) {
            return dictionary;
        }
        ConcurrentSkipListSet<String> myDictionary = new ConcurrentSkipListSet<String>();
        InputStream is = Dictionary.class.getClassLoader().getResourceAsStream("enable1.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String word;
        try {
            while ((word = br.readLine()) != null) {
                myDictionary.add(word);
            }
        } catch (IOException ex) {
            throw new RuntimeException("uh oh, our dictionary didn't load!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111", ex);
        }
        dictionary = myDictionary;
        return myDictionary;
    }

}
