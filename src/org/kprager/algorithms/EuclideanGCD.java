/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.algorithms;

/**
 *
 * @author kyleprager
 */
public class EuclideanGCD {
    
    public static void main(String[] args) {
        int result = EuclideanGCD.gcd(100, 25);
        System.out.println(result);
    }
    
    public static int gcd(int a, int b) {
        int tmp;
        while (b != 0) {
            tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
}
