/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kprager.concurrency;

/**
 *
 * @author kyleprager
 */
public class Deadlock2 {
    private static class Foo {
        public synchronized void a(Foo foo) {
            System.out.format("%s: %s"
                + " has bowed back to me!%n",
                "asdf", "jkll");
            foo.b();
        }
        public synchronized void b() {
//            System.out.format("%s: %s"
//                + " has bowed back to me!%n",
//                "asdf", "jkll");
        }
    }
    
    public static void main(String[] args) {
        final Foo foo = new Foo();
        final Foo bar = new Foo();
        new Thread(new Runnable() {
            public void run() {
                foo.a(bar);
            }
        }).start();
        
        new Thread(new Runnable() {
            public void run() {
                bar.a(foo);
            }
        }).start();
    }
}
