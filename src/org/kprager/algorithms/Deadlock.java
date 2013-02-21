/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.algorithms;

/**
 * I got this off of Oracle's site, but I've included it as a simple
 * example of deadlock for study purposes.
 * @author Oracle Inc.
 */
public class Deadlock {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                + "  has bowed to me!%n", 
                this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                + " has bowed back to me!%n",
                this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse =
            new Friend("Alphonse");
        final Friend gaston =
            new Friend("Gaston");
        new Thread(new Runnable() {
            @Override
            public void run() { alphonse.bow(gaston); }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() { gaston.bow(alphonse); }
        }).start();
    }
}