/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.datastructures.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author kyleprager
 */
public class Tree {
    
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.printInOrder();
        
        // Create tree
        Random r = new Random();
        System.out.print("Inserting: ");
        
        int[] arr = {9,2,5,6,3,7,1,0,8,4};
        for(int i = 0; i < arr.length; i++) {
//            tree.insert(r.nextInt(10));
            tree.insert(arr[i]);
        }
        System.out.println();
        
        // test tree operations
        tree.printPreOrder();
        System.out.println();
        
        tree.printInOrder();
        System.out.println();
        
        tree.printPostOrder();
        System.out.println();
        
        System.out.println(tree.getHeight());
        
        System.out.println(tree.DFS(5));
        System.out.println(tree.BFS(5));
        System.out.println(tree.isDAG());
    }
    
    public Node head;
    
    public Tree() {}
    public Tree(int[] insert_arr) {
        for (int i = 0; i < insert_arr.length; i++) {
            this.insert(insert_arr[i]);
        }
    }
    
    public void insert(int val) {
        Node node = new Node(val);
        if (head == null) {
            head = node;
            return;
        }
        
        insert(head, node);
    }
    
    private void insert(Node curr, Node node) {
        // if node.value < curr.value, add or recur to the left
        // otherwise add or recur to the right
        if (node.value < curr.value) {
            if (curr.left == null) {
                curr.left = node;
            } else {
                insert(curr.left, node);
            }
        } else {
            if (curr.right == null) {
                curr.right = node;
            } else {
                insert(curr.right, node);
            }
        }
    }
    public void printPreOrder() {
        printPreOrder(head);
    }
    private void printPreOrder(Node node){
        if (node == null) {
            return;
        }
        // print, recur left, recur right
        System.out.print(node.value + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }
    public void printInOrder() {
        printInOrder(head);
    }
    private void printInOrder(Node node){
        if (node == null) {
            return;
        }
        // recur left, print, recur right
        
        printInOrder(node.left);
        System.out.print(node.value + " ");
        printInOrder(node.right);
    }
    public void printPostOrder() {
        printPostOrder(head);
    }
    private void printPostOrder(Node node){
        if (node == null) {
            return;
        }
        // recur left, recur right, print
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.print(node.value + " ");
        
    }
    public int getHeight(){
        return getHeight(head);
    }
    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight  = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        } else {
            return rightHeight + 1;
        }
    }
    
    public boolean BFS(int searchval) {
        
        // marking visited nodes not necessary in binary tree.  but putting
        // it in to remind me that in general, for graphs, you want to mark
        // visted nodes as visited and continue if you hit a visited node
        HashSet<Node> visited = new HashSet<>();
        
        // uses a queue (fifo)
        System.out.print("BFS: ");
        Queue<Node> q = new LinkedList<>();
        q.add(head);
        Node node;
        while (! q.isEmpty()) {
            node = q.poll();
            if (node == null || visited.contains(node)) {
                continue;
            }
            
            visited.add(node);
            System.out.print(node.value + " ");
            
            if (node.value == searchval) {
                return true;
            } else {
                q.add(node.left);
                q.add(node.right);
            }
        }
        
        return false;
    }
    
    public boolean DFS(int searchval) {
        HashSet<Node> visited = new HashSet<>();
        
        // uses a stack (filo)
        System.out.print("DFS: ");
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        Node node;
        while (! stack.isEmpty()) {
            node = stack.pop();
            if (node == null  || visited.contains(node)) {
                continue;
            }

            visited.add(node);
            System.out.print(node.value + " ");
            
            if (node.value == searchval) {
                return true;
            } else {
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        
        return false;
    }
    
    public boolean isDAG() {
        // This version tags vertices as visited
        // but it can also be done by saving a
        // list of visited nodes and checking that
        
        // uses a stack (filo)
        System.out.print("isDAG: ");
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        Node node;
        while (! stack.isEmpty()) {
            node = stack.pop();
            if (node == null) {
                continue;
            } if (node.visited) {
                return false;
            }
            node.visited = true;

            System.out.print(node.value + " ");
            stack.push(node.right);
            stack.push(node.left);
        }
        
        return true;
    }
}
