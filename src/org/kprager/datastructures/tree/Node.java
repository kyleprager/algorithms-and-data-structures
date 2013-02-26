/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.datastructures.tree;

/**
 *
 * @author kyleprager
 */
public class Node{
    public Node left;
    public Node right;
    public Node parent;
    public int value;
    public boolean visited;
    
    public Node() {}
    
    public Node(int val) {
        this.value = val;
    }
}
