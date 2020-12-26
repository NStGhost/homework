package com.shadow.homeWorks.homework20;

import java.util.concurrent.CountDownLatch;

public class TempClass2 {
    private static final Object object = new Object();
    private static TempClass2 instance;

    public static TempClass2 getInstance(CountDownLatch countDownLatch) {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (object) {
            if (instance == null) {
                instance = new TempClass2();
            }
            return instance;
        }
    }

    private TempClass2() {
        System.out.println("Это сообщение должно вывестись только один раз");
    }
}
