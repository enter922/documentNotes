package com.example.notes;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesApplication {


    static Object obj = new Object();

    public static void method1() {
        synchronized (obj) {
            // 同步块 A    
            method2();
        }
    }

    public static void method2() {
        synchronized (obj) {
            // 同步块 B
        }
    }


    static {
        final String s = "1";
    }

    public static void main(String[] args) throws InterruptedException {
        notSyn();

    }

    static int i = 0;

    private static void notSyn() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                i--;
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                i++;
            }
        });
        t2.start();

        t1.join();
        t2.join();
        System.out.println(i);
    }


    private static void syn() {
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                i--;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                i++;
            }
        });
    }

}



























