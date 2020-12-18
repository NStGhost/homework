package com.shadow.homework20;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkerAtomic implements Runnable{

    private final CountDownLatch countDownLatch;
    private AtomicInteger c = new AtomicInteger(0);

    public WorkerAtomic(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c.getAndIncrement();
    }

    public void soutCount() {
        System.out.println("Atomic - " + c);
    }
}
