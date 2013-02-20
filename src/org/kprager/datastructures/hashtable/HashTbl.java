/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.datastructures.hashtable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kyleprager
 */
public class HashTbl<T> {
    
    public static void main(String[] args) {
        HashTbl ht = new HashTbl();
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,100,101,102,103,104,105,106};
        for (int x : arr) {
            ht.add(x);
            System.out.println(ht.get(x));
        }
        ht.print();
    }
    
    
    private int size = 10;
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
        System.out.println(list);
    }
    
//    public boolean setSize(int x) {
//        if (x < size) {
//            return false;
//        } else {
//            size = x;
//            return true;
//        }
//    }
    
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
        System.out.println(list);
    }
}
