/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.algorithms;

/**
 *
 * @author kyleprager
 */
public class MergeSort extends AbstractSort {
    
    /**
     * Main function to test MergeSort
     * @param args 
     */
    public static void main (String[] args) {
        int[] arr = {4, 3, 67, 18, 3, 44, 123, 8};
        Utils.print(arr);
        MergeSort sorter = new MergeSort();
        sorter.sort(arr);
        Utils.print(arr);
    }
    
    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length-1);
    }
    
    public void sort(int[] arr, int left, int right) {
        int middle = (left + right + 1) / 2;
        if (left < right) {
            sort(arr, left, middle-1);
            sort(arr, middle, right);
        }
        
        // merge
        
        // create a temporary array
        int size = right+1 - left;
        int[] tmp = new int[size];
        
        // create pointers to the front of each side of the array (larr rarr)
        // and a counter to track where we are in the tmp array
        int i = left;
        int j = middle;
        int ctr = 0;
        
        // go through both lists (sides of the array) and start putting
        // their contents into the temp array, always putting the lowest
        // number available in each time around.
        while (i <= middle-1 && j <= right) {
            if (arr[i] < arr[j]) {
                tmp[ctr] = arr[i];
                i++;
            } else {
                tmp[ctr] = arr[j];
                j++;
            }
            ctr++;
        }
        
        // finish off the left side
        while (i <= middle-1) {
            tmp[ctr] = arr[i];
            i++;
            ctr++;
        }
        
        // finish off the right side
        while (j <= right) {
            tmp[ctr] = arr[j];
            j++;
            ctr++;
        }
        
        // copy the temp array back over our primary array
        System.arraycopy(tmp, 0, arr, left, tmp.length);
    }
    
}
