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
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,100,101,102,103,104,105,106,112,134,156};
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

    public int getSize() {
        return size;
    }

    public int getMaxBucketSize() {
        return maxBucketSize;
    }

    public void setMaxBucketSize(int maxBucketSize) {
        this.maxBucketSize = maxBucketSize;
    }

    
    private int maxBucketSize = 3;
    private List<List<HashEntry<K,V>>> list;
    
    public HashTbl() {
        initList();
    }
    public HashTbl(int size) {
        if (size > this.size) {
            this.size = size;
        }
        initList();
    }
    
    /**
     * Completely clear our list if it has anything in it, and make a new one with current size
     */
    private void initList() {
        if (list != null) {
            list.clear();
        }
        list = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            list.add(new LinkedList<HashEntry<K,V>>());
        }
    }
    
    /**
     * Add an entry into the HashTbl
     * @param key
     * @param value 
     */
    public void add(K key, V value) {
        add(new HashEntry<>(key, value));
    }
    
    /**
     * used when rehashing
     * @param entry 
     */
    private void add(HashEntry<K,V> entry) {
        int hash = entry.key.hashCode() % size;
        if (list.get(hash).size() >= maxBucketSize) {
            rehash();
            hash = entry.key.hashCode() % size;
        }
        list.get(hash).add(entry);
    }
    
    /**
     * Get a value you hashed given a key
     * @param key used to retrieve your value
     * @return returns value if it's found, otherwise returns null
     */
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
    
    private void rehash() {
        // save all HashEntry objects and clear our buckets and main list
        List<HashEntry<K,V>> entries = new LinkedList<>();
        for (List<HashEntry<K,V>> bucket : list) {
            for(HashEntry<K,V> entry : bucket) {
                entries.add(entry);
            }
            bucket.clear();
        }

        // double the size of our array and rehash our entries back in
        size = size*2;
        initList();
        for (HashEntry<K,V> entry : entries) {
            add(entry);
        }
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
        sb.append("Size: ");
        sb.append(size);
        sb.append("\n");
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