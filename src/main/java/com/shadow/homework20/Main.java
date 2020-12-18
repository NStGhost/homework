package com.shadow.homework20;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        First();
        FirstA();
        FirstB();
    }

    private static void FirstB() {
        int countOperation = 2000;
        ExecutorService executorService = Executors.newFixedThreadPool(countOperation);
        CountDownLatch start = new CountDownLatch(countOperation);
        final WorkerAtomic workerAtomaric = new WorkerAtomic(start);
        for (int i=0; i < countOperation; i++)
            executorService.submit(workerAtomaric);
        start.countDown();
        workerAtomaric.soutCount();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    private static void FirstA() {
        int countOperation = 2000;
        ExecutorService executorService = Executors.newFixedThreadPool(countOperation);
        CountDownLatch start = new CountDownLatch(countOperation);
        final WorkerVolatile workerVolatile = new WorkerVolatile(start);
        for (int i=0; i < countOperation; i++)
            executorService.submit(workerVolatile);
        start.countDown();
        workerVolatile.soutCount();
        executorService.shutdown();
    }

    private static void First() {
        int countOperation = 2000;
        ExecutorService executorService = Executors.newFixedThreadPool(countOperation);
        CountDownLatch start = new CountDownLatch(countOperation);
        final Worker worker = new Worker(start);
        for (int i=0; i < countOperation; i++)
            executorService.submit(worker);
        start.countDown();
        worker.soutCount();
        executorService.shutdown();
    }

}
