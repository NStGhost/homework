package com.shadow.homework18;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Fibanachi {

    private ExecutorService executorService;
    private Future<Long> future;
    private long result = 0;

    private boolean setCancel = false;

    public Fibanachi() {
        executorService = Executors.newFixedThreadPool(4);
        future = executorService.submit(() -> {
            System.out.println("Start factorial");
            long temp1 = 0;
            long temp2 = 1;
            long result = 0;
            while (!setCancel) {
                result = temp1 + temp2;
                temp1 = temp2;
                temp2 = result;
                System.out.println(String.format("Current value factorail - %d", result));
                Thread.sleep(250);
            }
            System.out.println("Stop factorial");
            return result;
        });

    }

    public long cancel() {
        setCancel = true;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        return result;
    }
}
