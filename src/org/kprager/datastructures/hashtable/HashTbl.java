/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.datastructures.hashtable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Very simple array based implementation of a HashTable. Collisions are handled with buckets.
 * @author kyleprager
 */
public class HashTbl<T> {
    
    public static void main(String[] args) {
        HashTbl<Integer> ht = new HashTbl();
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,100,101,102,103,104,105,106};
        for (int x : arr) {
            ht.add(x);
            int tmp = ht.get(x);
            System.out.println(tmp);
        }
        ht.print();
        for (int x : arr) {
            boolean b = ht.remove(x);
            System.out.println(b);
        }
    }
    
    
    private int size = 11;
    private List<LinkedList<T>> list;
    
    public HashTbl() {
        list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new LinkedList<T>());
        }
    }
    public HashTbl(int size) {
        if (size > this.size) {
            this.size = size;
        }
        list = new ArrayList<>(size);
    }
    
    public void add(T x) {
        int hash = hash(x);
        list.get(hash).add(x);
    }
    
    public T get(T x) {
        int hash = hash(x);
        List<T> bucket = list.get(hash);
        if (bucket != null) {
            for(T tmp : bucket) {
                if (tmp.equals(x)) {
                    return tmp;
                }
            }
        }
        return null;
    }
    
    public boolean remove(T x) {
        int hash = hash(x);
        List<T> bucket = list.get(hash);
        if (bucket != null) {
            return bucket.remove(x);
        }
        return false;
    }
    
    private int hash(T x) {
        return x.hashCode() % size;
    }
    
    private void print() {
        for (List<T> bucket : list) {
            System.out.println(bucket);
        }
    }
}