/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.datastructures.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

/**
 * Graph made with an adjacency list
 * @author kyleprager
 */
public class Graph {
    
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.print();
        graph.DFS(5);
        System.out.println();
        graph.BFS(5);
        System.out.println();
        List<Vertex> vertices = graph.getVertices();
        for (int i = 0; i < vertices.size(); i++) {
            Vertex v = vertices.get(i);
            boolean b = graph.isDAG(v);
            System.out.println(b);
            if (b) {
                System.exit(0);
            }
        }
    }
    
    private List<Vertex> vertices = new ArrayList<>();
    private static final int MAX_VERTICES = 100;
    private static final int MAX_NEIGHBORS = 10;
    private static final int MIN_NEIGHBORS = 0;
    
    /**
     * Generates a graph represented as adjacency list.  The graph generation
     * does not guarantee than you will not have orphaned vertices.
     */
    public Graph() {
        // generate the graph
        
        // generate MAX_VERTICES number of vertices
        for (int i = 0; i < MAX_VERTICES; i++) {
            Vertex v = new Vertex(i);
            vertices.add(v);
        }
        
        // randomly add neighbors from list of vertices
        Random r = new Random();
        for (int i = 0; i < MAX_VERTICES; i++) {
            Vertex curr = vertices.get(i);
            int num_neighbors = r.nextInt(MAX_NEIGHBORS) + MIN_NEIGHBORS;
            for (int j = 0; j < num_neighbors; j++) {
                int idx = i;
                Vertex v;
                while (idx == i) {
                    idx = r.nextInt(vertices.size());
                    // dont link to yourself as a neighbor, that's a loop
                    // right there.
                    if (idx == i) {
                        continue;
                    }
                    v = vertices.get(idx);
                    if (!curr.neighbors.contains(v)) {
                        curr.neighbors.add(v);
                    }
                }
                
            }
        }
    }
    
    public List<Vertex> getVertices() {
        return vertices;
    }
    
    public boolean DFS(int val) {
        int ctr = 0;
        System.out.print("DFS: ");
        // uses stack (filo)
        Stack<Vertex> stack = new Stack<>();
        Set<Vertex> visited = new HashSet<>(); // instead of node.visited
        
        stack.push(vertices.get(0));
        
        while (!stack.isEmpty()) {
            ctr++;
            Vertex v = stack.pop();
            System.out.print(v.value + " ");
            if (visited.contains(v)) {
                continue;
            }
            visited.add(v);
            if (v.value == val) {
                System.out.println();
                System.out.println("Vertices traversed: " + ctr);
                return true;
            } else {
                for (Vertex tmp: v.neighbors) {
                    stack.push(tmp);
                }
            }
        }
        
        return false;
    }
    
    public boolean BFS(int val) {
        int ctr = 0;
        System.out.print("BFS: ");
        // uses queue (fifo)
        Queue<Vertex> q = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>(); // instead of node.visited
        
        q.add(vertices.get(0));
        
        while (!q.isEmpty()) {
            ctr++;
            Vertex v = q.remove();
            System.out.print(v.value + " ");
            if (visited.contains(v)) {
                continue;
            }
            visited.add(v);
            if (v.value == val) {
                System.out.println();
                System.out.println("Vertices traversed: " + ctr);
                return true;
            } else {
                for (Vertex tmp: v.neighbors) {
                    q.add(tmp);
                }
            }
        }
        return false;
    }
    
    public boolean isDAG(Vertex start) {
        int ctr = 0;
        System.out.print("isDag: ");
        // uses stack (filo)
        Stack<Vertex> stack = new Stack<>();
        Set<Vertex> visited = new HashSet<>();
        
        if (start == null) {
            stack.push(vertices.get(0));
        } else {
            stack.push(start);
        }
        
        while (!stack.isEmpty()) {
            Vertex v = stack.pop();
            System.out.print(v.value + " ");
            if (visited.contains(v)) {
                return false;
            }
            visited.add(v);
            for (Vertex tmp: v.neighbors) {
                stack.push(tmp);
            }
        }
        
        return true;
    }

    
    public void print() {
        for (Vertex v: vertices) {
            System.out.print(v.value + ": ");
            for (Vertex w: v.neighbors) {
                System.out.print(w.value + " ");
            }
            System.out.println();
        }
    }
}
