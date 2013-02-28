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
public class HashTbl<K, V> {
    
    public static void main(String[] args) {
        HashTbl<Integer, Integer> ht = new HashTbl<>();
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,100,101,102,103,104,105,106};
        for (int x : arr) {
            ht.add(x,x);
            int tmp = ht.get(x);
            System.out.println(tmp);
        }
        System.out.println(ht);
        for (int x : arr) {
            ht.remove(x);
        }
        System.out.println(ht);
    }
    
    
    private int size = 11;
    private List<List<HashEntry<K,V>>> list;
    
    public HashTbl() {
        list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new LinkedList<HashEntry<K,V>>());
        }
    }
    public HashTbl(int size) {
        if (size > this.size) {
            this.size = size;
        }
        list = new ArrayList<>(size);
    }
    
    public void add(K key, V value) {
        int hash = key.hashCode() % size;
        list.get(hash).add(new HashEntry<K,V>(key, value));
    }
    
    public V get(K key) {
        int hash = key.hashCode() % size;
        List<HashEntry<K,V>> bucket = list.get(hash);
        if (bucket != null) {
            for(HashEntry<K,V> tmp : bucket) {
                if (tmp.key.equals(key)) {
                    return tmp.value;
                }
            }
        }
        return null;
    }
    
    public void remove(K key) {
        boolean retval = false;
        int hash = key.hashCode() % size;
        List<HashEntry<K,V>> bucket = list.get(hash);
        bucket.clear();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<HashEntry<K,V>> bucket : list) {
            sb.append(bucket);
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public static class HashEntry<K,V> {
        public K key;
        public V value;
        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        @Override
        public String toString() {
            return "<" + key.toString() + "," + value.toString() + ">";
        }
    }
}