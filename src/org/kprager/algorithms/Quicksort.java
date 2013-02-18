/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.algorithms;

/**
 *
 * @author kyleprager
 */
public class Quicksort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Quicksort sorter = new Quicksort();
        int[] arr = {1,2,5,7,18,3,4,12,99,5};
        Utils.print(arr);
        sorter.sort(arr, 0, arr.length-1);
        Utils.print(arr);
    }
    
    /**
     * This function does Quicksort recursively
     * @param arr array of integers to sort using Quicksort
     * @param left left index (start with 0)
     * @param right right index (start with <param>arr</param>.length-1)
     */
    public void sort(int[] arr, int left, int right) {
        int i = left;
        int j = right;
        int pivot = arr[(left+right+1) / 2];
        
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        
        if (left < j) {
            sort(arr, left, i-1);
        }
        if (right > i) {
            sort(arr, i, right);
        }
    }
}