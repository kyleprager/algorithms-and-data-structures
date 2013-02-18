/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.datastructures;

import org.kprager.algorithms.Utils;

/**
 *
 * @author kyleprager
 */
public class LinkedLst {
    private Node head;
    private static final int NODECOUNT = 10;
    
    public LinkedLst() {
        // setup the beginning of the list
        head = new Node(0);
        Node curr = head;
        
        for (int i = 1; i < NODECOUNT; i++) {
            Node tmp = new Node(i);
            
            // link node and move curr pointer forward
            curr.next = tmp;
            curr = tmp;
        }
    }
    
    public void reverse() {
        Node curr = head;
        Node next = curr.next;
        curr.next = null;
        Node tmp;
        while (next != null) {
            tmp = next.next;
            next.next = curr;
            curr = next;
            next = tmp;
            
            if (next == null) {
                head = curr;
            }
        }
    }
    
    public void insert(int pos, int val) {
        // insert at head position if pos == 0
        if (pos == 0) {
            Node tmp = head.next;
            Node tmp2 = head;
            head = new Node(val);
            head.next = tmp2;
            head.next.next = tmp;
            return;
        }
        
        // start counter at position 1 since position 0 is taken care of above
        int ctr = 1; 
        Node curr = head;
        while (ctr < pos) {
            curr = curr.next;
            ctr++;
        }
        
        Node tmp = curr.next;
        curr.next = new Node(val);
        curr.next.next = tmp;
    }
    
    public void print() {
        Utils.print(head);
    }
    
    public static void main(String[] args) {
        LinkedLst ll = new LinkedLst();
        ll.print();
        ll.reverse();
        ll.print();
        ll.insert(1, 420);
        ll.print();
    }
}
