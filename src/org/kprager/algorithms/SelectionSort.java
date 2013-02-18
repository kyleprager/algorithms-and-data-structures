/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.algorithms;

/**
 *
 * @author kyleprager
 */
public class SelectionSort {
    
    public static void main(String[] args) {
        int[] arr = {123, 1, 4, 3, 6, 54, 333, 74};
        Utils.print(arr);
        SelectionSort sorter = new SelectionSort();
        sorter.sort(arr);
        Utils.print(arr);
    }
    
    /**
     * Go through and do our sort, putting the lowest item in the
     * position passed to it, for each position in the list
     * until length-1.  this is because we need a list size of at
     * least 2 from the startidx passed to the other sort() function.
     * @param arr array of integers to sort
     */
    public void sort(int[] arr) {
        
        for(int i = 0; i < arr.length-1; i++) {
            sort(arr, i);
        }
    }
    
    /**
     * We start at the startidx and go to the right from there, putting
     * the lowest item at the startidx by the time we're done.
     * @param arr array of integers to sort
     * @param startidx the index to start at.
     */
    public void sort(int[] arr, int startidx) {
        // put the lowest item at the first position
        int lowidx = startidx;
        for (int i = startidx; i < arr.length; i++) {
            if (arr[i] < arr[lowidx]) {
                lowidx = i;
            }
        }
        int tmp = arr[startidx];
        arr[startidx] = arr[lowidx];
        arr[lowidx] = tmp;
        
        // swap anything lower with the first (lowest) item
        for (int i = startidx; i < arr.length; i++) {
            if (arr[i] < arr[startidx]) {
                tmp = arr[i];
                arr[i] = arr[startidx];
                arr[startidx] = tmp;
            }
        }
    }
    
}
