package com.shadow.homework20;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        //First();
        //FirstA();
        //FirstB();
        Second();
    }

    private static void Second() {
        CountDownLatch countDownLatch = new CountDownLatch(2000);
        ExecutorService executorService = Executors.newFixedThreadPool(2000);
        WaitEnd waitEnd = new WaitEnd(countDownLatch);
        for (int i = 0; i < 2000; i++) {
            executorService.submit(waitEnd);
        }
        try {
            countDownLatch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitEnd.soutChuchu();
        executorService.shutdown();
    }

    private static void FirstB() {
        int countOperation = 2000;
        ExecutorService executorService = Executors.newFixedThreadPool(countOperation);
        CountDownLatch start = new CountDownLatch(1);
        final WorkerAtomic workerAtomaric = new WorkerAtomic(start);
        for (int i=0; i < countOperation; i++)
            executorService.submit(workerAtomaric);
        start.countDown();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        workerAtomaric.soutCount();
        executorService.shutdown();
    }

    private static void FirstA() {
        int countOperation = 2000;
        ExecutorService executorService = Executors.newFixedThreadPool(countOperation);
        CountDownLatch start = new CountDownLatch(1);
        final WorkerVolatile workerVolatile = new WorkerVolatile(start);
        for (int i=0; i < countOperation; i++)
            executorService.submit(workerVolatile);
        start.countDown();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        workerVolatile.soutCount();
        executorService.shutdown();
    }

    private static void First() {
        int countOperation = 2000;
        ExecutorService executorService = Executors.newFixedThreadPool(countOperation);
        CountDownLatch start = new CountDownLatch(1);
        final Worker worker = new Worker(start);
        for (int i=0; i < countOperation; i++)
            executorService.submit(worker);
        start.countDown();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.soutCount();
        executorService.shutdown();
    }

}
