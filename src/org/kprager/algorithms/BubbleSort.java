/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.algorithms;

/**
 *
 * @author kyleprager
 */
public class BubbleSort {
    
    public static void main(String[] args) {
        int[] arr = {123, 1, 4, 3, 6, 54, 333, 74};
        Utils.print(arr);
        BubbleSort sorter = new BubbleSort();
        sorter.sort(arr);
        Utils.print(arr);
    }
    
    /**
     * This sorts an integer array using the Bubble Sort algorithm.
     * @param arr the integer array to sort
     */
    public void sort(int[] arr) {
        boolean swapped = false;
        int a = 0;
        int b = 1;
        
        int tmp;
        while (b < arr.length) {
            
            if (arr[a] > arr[b]) {
                tmp = arr[a];
                arr[a] = arr[b];
                arr[b] = tmp;
                swapped = true;
            }
            
            a++;
            b++;
        }
        
        // if we had to swap any items, iterate over the list again
        if (swapped) {
            sort(arr);
        }
        
    }
}
