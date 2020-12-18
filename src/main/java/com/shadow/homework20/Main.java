package com.shadow.homework20;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        First();
    }

    private static void First() {
        int countOperation = 2000;
        ExecutorService executorService = Executors.newFixedThreadPool(countOperation);
        CountDownLatch start = new CountDownLatch(countOperation);
        Worker worker = new Worker(start);
        for (int i=0; i < countOperation; i++)
            executorService.submit(worker);
        start.countDown();
        worker.soutCount();
        executorService.shutdown();
    }

}
