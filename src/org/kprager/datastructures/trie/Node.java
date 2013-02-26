/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.datastructures.trie;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kyleprager
 */
public class Node{
    // I wanted to use a dynamic array instead of a linked list so that lookups
    // are done more quickly in contiguous memory while searching a built Trie.
    // The downside is that during construction of the Trie the array list of children
    // might have to be dynamically resized.
    public List<Node> children = new ArrayList<>();
    public boolean marker;
    public char value;
    
    public Node(char c) {
        this.value = c;
    }
    
    public Node getChild(char c) {
        for (Node child : children) {
            if (child.value == c) {
                return child;
            }
        }
        return null;
    }
}

