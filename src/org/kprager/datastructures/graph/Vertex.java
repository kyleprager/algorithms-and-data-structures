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
public class Vertex implements Comparable {
    public boolean visited;
    public Vertex parent;
    public Integer distance = Integer.MAX_VALUE; // used for Dijsktra's and possibly other algorithms
    public int value;
    public List<Vertex> neighbors = new ArrayList<>();
    
    public Vertex(){}
    public Vertex(int val) {
        this.value = val;
    }
    
    @Override
    public String toString() {
        Integer parentval = null;
        if (parent != null) {
            parentval = parent.value;
        }
        return String.format("[distance, parent, value]: " + "[%s, %d, %d]", distance, parentval, value);
    }

    @Override
    public int compareTo(Object o) {
        Vertex v = (Vertex)o;
        
        return v.distance - this.distance;
    }
}
