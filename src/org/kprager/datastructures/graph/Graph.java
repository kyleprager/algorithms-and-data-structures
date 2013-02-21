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
    
    // used to generate graph - his is handy because you can generate
    // the same graph over and over again for testing purposes.
    private int[][] adjacency_list = {
            {0, 1},
            {1,0,2,3},
            {2,1,3},
            {3,1,2}
        };
    private List<Vertex> vertices = new ArrayList<>();
    
    // the following are used if we want to generate our graph randomly
    private static final int MAX_VERTICES = 100;
    private static final int MAX_NEIGHBORS = 10;
    private static final int MIN_NEIGHBORS = 0;
    
    public static void main(String[] args) {
        
        Graph graph = new Graph();
        graph.print();
        graph.DFS(5);
        graph.BFS(5);
        graph.DFS(2);
        graph.BFS(2);
        List<Vertex> vertices = graph.getVertices();
        for (int i = 0; i < vertices.size(); i++) {
            Vertex v = vertices.get(i);
            boolean b = graph.isDAG(v);
            System.out.println(b);
        }
    }
    
    
    
    /**
     * Generates a graph represented as adjacency list.  The graph generation
     * does not guarantee than you will not have orphaned vertices.
     */
    public Graph() {
        // generate the graph
        generateGraph();
        
    }
    
    private void generateGraph() {
        // create all the vertices
        for (int i = 0; i < adjacency_list.length; i++) {
            vertices.add(new Vertex(i));
        }
        // add neighbors to create ajacency list
        for (int i = 0; i < adjacency_list.length; i++) {
            int length = adjacency_list[i].length;
            for (int j = 0; j < length; j++) {
                vertices.get(i).neighbors.add(vertices.get(adjacency_list[i][j]));
            }
        }
    }
    
    /**
     * Generate our graph randomly.  This is currently not used but you
     * can pick which method is used in the Graph() constructor.
     */
    private void generateGraphRandomly() {
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
        System.out.printf("DFS (%d): ", val);
        // uses stack (filo)
        Stack<Vertex> stack = new Stack<>();
        Set<Vertex> visited = new HashSet<>(); // instead of node.visited
        
        stack.push(vertices.get(0));
        
        while (!stack.isEmpty()) {
            Vertex v = stack.pop();
            if (visited.contains(v)) {
                continue;
            }
            System.out.print(v.value + " ");
            visited.add(v);
            ctr++;
            if (v.value == val) {
                System.out.println(true);
                System.out.println("Vertices traversed: " + ctr);
                return true;
            } else {
                for (Vertex tmp: v.neighbors) {
                    stack.push(tmp);
                }
            }
        }
        System.out.println(false);
        return false;
    }
    
    public boolean BFS(int val) {
        int ctr = 0;
        System.out.printf("BFS (%d): ", val);
        // uses queue (fifo)
        Queue<Vertex> q = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>(); // instead of node.visited
        
        q.add(vertices.get(0));
        
        while (!q.isEmpty()) {
            Vertex v = q.remove();
            if (visited.contains(v)) {
                continue;
            }
            System.out.print(v.value + " ");
            visited.add(v);
            ctr++;
            if (v.value == val) {
                System.out.println(true);
                System.out.println("Vertices traversed: " + ctr);
                return true;
            } else {
                for (Vertex tmp: v.neighbors) {
                    q.add(tmp);
                }
            }
        }
        System.out.println(false);
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
