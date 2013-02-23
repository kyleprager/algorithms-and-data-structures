/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.datastructures.graph;

import java.util.List;

/**
 *
 * @author kyleprager
 */
public class GraphProblems {
    private static int[][] bst = {
        {1,2}, // node 0 ....
        {3,4},
        {5,6},
        {7,8},
        {},
        {},
        {},
        {9},
        {},
        {}     // vertex 9
    };
    
    public static void main(String[] args) {
        
        // Given 2 Nodes in a BST, find their closest shared parent
        Graph g = new Graph(bst);
        g.dijsktra(g.getVertices().get(0));
        List<Vertex> path1 = g.getShortestPath(g.getVertices().get(1));
        List<Vertex> path2 = g.getShortestPath(g.getVertices().get(9));

        // parent will be the last item in each path before the diverge
        // EX: [1,2,3], [1,2,4,5]. - searched for 3 and 5.  parent is 2.
        // EX: [1,2], [1,2,4,5]. - searched for 2 and 5.  parent is 2.
        int min = Math.min(path1.size(), path2.size()) -1;
        int parentidx = 0;
        for (int i = 0; i <= min; i++) {
            if (path1.get(i) == path2.get(i)) {
                parentidx = i;
            } else {
                break;
            }
        }
        System.out.println("shared parent: " + path1.get(parentidx));
        
    }
}
