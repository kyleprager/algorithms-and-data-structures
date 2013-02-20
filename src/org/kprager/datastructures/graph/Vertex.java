/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kyleprager
 */
public class Vertex {
    public int value;
    public List<Vertex> neighbors = new ArrayList<>();
    
    public Vertex(){}
    public Vertex(int val) {
        this.value = val;
    }
}
