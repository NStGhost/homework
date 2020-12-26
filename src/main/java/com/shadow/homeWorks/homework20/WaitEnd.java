package com.shadow.homeWorks.homework20;

import java.util.concurrent.CountDownLatch;

public class WaitEnd implements Runnable{
    private int chucha = 3000;
    private final CountDownLatch countDownLatch;
    public WaitEnd(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        chucha--;
        countDownLatch.countDown();
    }

    public void soutChuchu() {
        System.out.println("Start 3000 : End " + chucha);
    }
}
