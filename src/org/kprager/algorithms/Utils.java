/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.algorithms;

import org.kprager.datastructures.Node;

/**
 *
 * @author kyleprager
 */
public class Utils {
    /**
     * Convenience method to print out an integer array
     * @param arr 
     */
    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    public static void print(Node n) {
        while (n != null) {
            System.out.print(n.value);
            n = n.next;
        }
        System.out.println();
    }
}
