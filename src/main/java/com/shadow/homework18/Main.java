package com.shadow.homework18;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        firstEx();
        //secondEx();
        //thirdEx();
        //fourthEx();

    }

    private void fourthEx() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CopyFiles copyFiles = new CopyFiles();
        executorService.submit(copyFiles);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }

    private void thirdEx() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        StopWatch stopWatch = new StopWatch();
        executorService.submit(stopWatch);
        try {
            Thread.sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }

    private void secondEx() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Fibanachi fibanachi = new Fibanachi();
        final Future<Long> future= executorService.submit(fibanachi);
        long res1 = 0;
        try {
            Thread.sleep(2000);
            executorService.shutdownNow();
            res1 = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Result fibanachi - " + res1);
    }

    private void firstEx() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Factorial factorial = new Factorial(10);
        final Future<Long> future= executorService.submit(factorial);
        long res1 = 0;
        try {
            Thread.sleep(5000);
            executorService.shutdownNow();
            res1 = future.get();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Result factorial - " + res1);
    }
}
