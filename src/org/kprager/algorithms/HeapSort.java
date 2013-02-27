/*
 * The MIT License
 *
 * Copyright 2013 Kyle Prager (http://github.com/kyleprager).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.kprager.algorithms;

/**
 *
 * @author Kyle Prager (http://github.com/kyleprager)
 */
public class HeapSort extends AbstractSort {
    
    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        HeapSort heapsorter = new HeapSort();
        heapsorter.sort(arr);
        Utils.print(arr);
    }
    
    private int heapsize;

    @Override
    public void sort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        buildHeap(arr);
        for(int i = arr.length-1; i > 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            this.heapsize--;
            heapify(arr,0);
        }
    }
    
    private void heapify(int[] arr, int i) {
        int left = i*2;
        int right = i*2+1;
        int idx = i;
        
        if (i >= arr.length || left >= arr.length || right >= arr.length) {
            return;
        }
        
        if (left < heapsize && arr[left] > arr[idx]) {
            idx = left;
        }
        if (right < heapsize && arr[right] > arr[idx]) {
            idx = right;
        }
        if (idx != i) {
            int tmp = arr[idx];
            arr[idx] = arr[i];
            arr[i] = tmp;
            heapify(arr,idx);
        }
    }
    
    private void buildHeap(int[] arr) {
        this.heapsize = arr.length;
        int mid = arr.length / 2;
        for (int i = mid; i >= 0; i--) {
            heapify(arr, i);
        }
    }
    
}
