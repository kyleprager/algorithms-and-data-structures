/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.datastructures.graph;

import java.util.ArrayList;
import java.util.Collections;
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
    
    // used to generate graph - this is handy because you can generate
    // the same graph over and over again for testing purposes.
    private int[][] adjacency_list2 = {
            {1},
            {0,2,3},
            {1,3},
            {1,2}
        };
    private int[][] adjacency_list = {
            {1},
            {0,2,3},
            {1,3,4},
            {1,2,5},
            {2,5},
            {4,3}
        };
    private int[][] adjacency_list3 = {
        {1},
        {0,2},
        {1,6,3},
        {2,4,9},
        {3,5},
        {4},
        {2,7,8},
        {6,8},
        {6,7,9},
        {8,3,10},
        {9}
    };
    private List<Vertex> vertices = new ArrayList<>();
    
    // the following are used if we want to generate our graph randomly
    // NOTE: graph may not be connnected.  no euler tour.
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
        graph.dijsktra(vertices.get(0));
        graph.getShortestPath(vertices.get(3));
        graph.getShortestPath(vertices.get(10));
        graph.getShortestPath(vertices.get(8));
    }
    
    
    
    /**
     * Generates a graph represented as adjacency list.  The graph generation
     * does not guarantee than you will not have orphaned vertices.
     */
    public Graph() {
        // generate the graph
        generateGraph(adjacency_list3);
        
    }
    
    public Graph(int[][] adjacency_list) {
        // generate the graph
        generateGraph(adjacency_list);
    }
    
    private void generateGraph(int[][] adjacency_list) {
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
                // add children in reverse order so they are popped off the
                // stack in the correct order
                Vertex tmp;
                for (int i = v.neighbors.size() - 1; i >= 0; i--) {
                    stack.push(v.neighbors.get(i));
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
    
    /**
     * 
     * @param start
     * @param end
     * @return 
     */
    public List<Vertex> dijsktra(Vertex start) {
        
        // initialize our queue.  this is for an adjacency list representation
        // of a graph, not a graph with weighted edges like Dijkstra's algorithm
        // normally assumes.  For this reason we are using a standard queue
        // instead of minimum priority queue.  We don't care which neighbor we
        // visit next because they all have an edge weight of 1 (in essence).
        Queue<Vertex> q = new LinkedList<>();
        q.add(start);
        // initialize single source
        for (Vertex v: vertices) {
            v.distance = Integer.MAX_VALUE;
            v.parent = null;
            v.visited = false;
        }
        start.distance = 0;
        
        // begin
        Vertex curr;
        while ((curr = q.poll()) != null) {
            curr.visited = true;
            System.out.println("curr: " + curr + " " + curr.distance + " " + curr.visited);
           
           // relax the neighbors:  bring them cookies.
           for (Vertex v : curr.neighbors) {
               
               // we don't want to visit any neighbors we've already added
               // to the queue
               if (v.visited) {continue;}
               
               // add the non-visited neighbor to our queue
               q.add(v);
               
               // calculate distance to origin by traversing parents of vertex.
               // if the vertex has no parent, it's distance is not changed.
               int distance = v.distance;
               if (v.parent != null) {
                    int ctr = 0;
                    Vertex tmp = v;
                    while (tmp.parent != null) {
                        ctr++;
                        tmp = tmp.parent;
                    }
                    distance = ctr;
               }
               
               // update the distance to each neighbor
               System.out.println(v + " " + distance + " " + v.visited);
               if (distance > curr.distance && curr != v) {
                   v.distance = curr.distance+1;
                   v.parent = curr;
               }
           }
           System.out.println();
        }
        for (Vertex v: vertices) {
            System.out.println(v);
        }
        
        return vertices;
    }
    
    /**
     * This function only works after calling Graph.dijkstra(start)
     * @param end the destination vertex you are looking for
     * @return a list that represents the shortest path of vertices to take
     * to get to the given vertex from the start vertex passed to the Dijkstra function
     */
    public List<Vertex> getShortestPath(Vertex end) {
        // create shortest path list by traversing parents of 'end' until
        // we hit 'start'
        List<Vertex> shortest_path = new LinkedList<>();
        shortest_path.add(end);
        while (end.parent != null) {
            shortest_path.add(end.parent);
            end = end.parent;
        }
        // reverse the list becuase we added vertices in reverse order
        Collections.reverse(shortest_path);
        
        System.out.println("SHORTEST PATH: ");
        for (Vertex v : shortest_path) {
            System.out.println(v);
        }
        return shortest_path;
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
