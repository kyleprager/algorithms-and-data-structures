/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.datastructures.tree;

/**
 *
 * @author kyleprager
 */
public class TNode {
    public TNode left;
    public TNode right;
    public int value;
    
    public TNode() {}
    
    public TNode(int val) {
        this.value = val;
    }
}
