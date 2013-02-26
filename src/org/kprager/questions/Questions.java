/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.questions;

import java.util.List;
import org.kprager.datastructures.graph.Graph;
import org.kprager.datastructures.graph.Vertex;
import org.kprager.datastructures.tree.Node;
import org.kprager.datastructures.tree.Tree;

/**
 * These are a few uses of the data structures I've written.
 * @author kyleprager
 */
public class Questions {
    
    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            Integer x = Questions.popcount(i);
            System.out.print(x + " ");
            System.out.println(Integer.bitCount(x));
        }
        int[] arr = {9,2,5,6,3,7,1,0,8,4};
        Questions.twoLargestBST(new Tree(arr));
        Questions.createTreeFromTraversals();
    }
    
    // count set bits
    public static int popcount(int n) {
        int count = 0;
        while (n != 0) {
          n = n & (n-1) ;
          count++;
        }
        return count;
    }
    
    /**
     * Find the two largest numbers in a binary search tree (BST).
     * @param tree 
     */
    public static void twoLargestBST(Tree tree) {
        if (tree == null || tree.head == null) {
            System.out.println("Sorry, your tree is empty. Your bad.");
            return;
        }
        
        if (tree.head.left == null && tree.head.right == null) {
            System.out.println("Largest and only value: " + tree.head.value);
            return;
        }
        
        // traverse all the way to the right and print the largest value
        // NOTE: we update each node's parent value along the way.
        Node node = tree.head;
        while (node.right != null) {
            node.right.parent = node;
            node = node.right;
        }
        System.out.println("Largest: " + node.value);
        
        // check if the largest value node has a left child.  If so, traverse
        // from the left child as far right as you can and print the second
        // largest node.
        if (node.left != null) {
            node = node.left;
            while (node.right != null) {
                node = node.right;
            }
            System.out.println("Second largest: " + node.value);
        } else {
            // If the left child node was null, print the largest node's parent's value
            System.out.println("Second largest: " + node.parent.value);
        }
    }
    
    /**
     * Given a pre-order and in-order traversal of a BST, recreate the tree.
     */
    public static void createTreeFromTraversals() {
        int[] preorder =  {9, 2, 1, 0, 5, 3, 4, 6, 7, 8};
        int[] inorder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Tree tree = new Tree();
        tree.head = recur(preorder, inorder, 0, 0, inorder.length-1);
        tree.printPreOrder();
        System.out.println();
        tree.printInOrder();
        System.out.println();
    }
    
    /**
     * This is the recursive function that recreates a tree from an in-order and pre-order traversal.
     * @param preorder the pre-order traversal list
     * @param inorder  the in-order traversal list
     * @param preidx   the current index into the pre-index list
     * @param left     left index in the pre-order list
     * @param right    right index in the pre-order list
     * @return 
     */
    private static Node recur(int[] preorder, int[] inorder, int preidx, int left, int right) {
        
        // if our index to create the current node is out of bounds, return
        if (left > right) {
            return null;
        }
        
        // create current node
        Node node = new Node(preorder[preidx]);
        
        // get the index of currently created node so we can split the
        // preorder list in half
        int idx = 0;
        while (idx < inorder.length && inorder[idx] != node.value) {
            idx++;
        }
        
        // we now split the inorder list into two sublists on either side of idx (current node's value)
        
        // get the length of each side of the inorder list
        int left_length  = idx - left;
        int right_length = right - idx;
        
        // recur only if either side's list length > 0
        if (left_length > 0) {
            // the preidx value is the next item in the list for the left side
            node.left = recur(preorder, inorder, preidx+1, left, idx-1);
        }
        if (right_length > 0) {
            // the preidx value is the next item plus the left_length
            node.right = recur(preorder, inorder, preidx+1+left_length, idx+1, right);
        }
        
        return node;
    }
    
    /**
     * Given 2 nodes in a BST, find their closest common parent.
     */
    public static void findClosestSharedParentBST() {
        
        // initialize graph (adjacency list) as a BST
        int[][] bst = {
            {1,2}, // vertex 0 ....
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
