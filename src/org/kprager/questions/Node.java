/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.questions;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kyleprager
 */
public class Node {
    public Node parent = null;
    public boolean end = false;
    public String value;
    public List<Node> children = new LinkedList<>();
    public Node(String s) {
        this.value = s;
    }
}
