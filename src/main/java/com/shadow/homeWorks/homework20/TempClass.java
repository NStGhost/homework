package com.shadow.homeWorks.homework20;

import java.util.concurrent.CountDownLatch;

public class TempClass{
    private static TempClass instance;


    public static TempClass getInstance(CountDownLatch countDownLatch) {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (instance == null) {
            instance = new TempClass();
        }
        return instance;
    }

    private TempClass() {
        System.out.println("Это сообщение должно вывестись только один раз");
    }

}
