/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.datastructures.trie;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kyleprager
 */
public class Node{
    public List<Node> children = new LinkedList<>();
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

