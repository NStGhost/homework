package com.shadow.homework20;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable{

    private final CountDownLatch countDownLatch;
    private int count;

    public Worker(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.count = 0;
    }

    @Override
    public void run() {
        count++;
    }

    public void soutCount() {
        System.out.println("Число - " + count);
    }

}
