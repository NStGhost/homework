package com.shadow.homework18;

import java.util.concurrent.*;

public class Factorial {

    private ExecutorService executorService;
    private Future<Long> future;
    private long result = 0;

    private boolean setCancel = false;

    public Factorial(int i) {
        executorService = Executors.newFixedThreadPool(4);
        future = executorService.submit(() -> {
            System.out.println("Start factorial");
            long temp = 1;
            for (int j=1; j <= i; j++) {
                if (setCancel) {
                    break;
                }
                temp *= j;
                System.out.println(String.format("Current value factorail - %d", temp));
                Thread.sleep(250);
            }
            System.out.println("Stop factorial");
            return temp;
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
