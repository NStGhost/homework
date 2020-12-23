package com.shadow.homework19;

import com.shadow.homework18.Fibanachi;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        //First();
        //Second();
        //Third();
        Fourth();
    }

    private void Second() {
        FibanachiThread fibanachi = new FibanachiThread(20);
        fibanachi.start();
    }

    private void Third() {
        FibanachiRunnable fibanachiRunnable = new FibanachiRunnable(20);
        new Thread(fibanachiRunnable).start();
    }

    private void Fourth() {
        FibanachiThread fibanachi = new FibanachiThread(20);
        fibanachi.start();
        while (true) {
            if (!fibanachi.isAlive()) {
                break;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
        fibanachi.interrupt();

        FibanachiRunnable fibanachiRunnable = new FibanachiRunnable(20);
        Thread thread = new Thread(fibanachiRunnable);
        thread.start();

        while (true) {
            if (fibanachiRunnable.getStatus()) {
                break;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
        thread.interrupt();
    }

    private void First() {
        //No Synchro
        Point point = new Point();
        ExecutorService executorService = Executors.newFixedThreadPool(2000);
        Future<Boolean> future = null;
        for (int i=0; i<2000; i++){
            future = executorService.submit(() -> {
                point.move(1,1);
                return null;
            });
        }
        try {
            future.get();
            point.showCoordinates();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();

        //Synchro1
        PointLock pointLock = new PointLock();
        executorService = Executors.newFixedThreadPool(2000);
        future = null;
        for (int i=0; i<2000; i++){
            future = executorService.submit(() -> {
                pointLock.moveS(1,1);
                return null;
            });
        }

        try {
            future.get();
            pointLock.showCoordinates();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();

        //Synchro2
        PointLock1 pointLock1 = new PointLock1();
        executorService = Executors.newFixedThreadPool(2000);
        future = null;
        for (int i=0; i<2000; i++){
            future = executorService.submit(() -> {
                pointLock1.moveS(1,1);
                return null;
            });
        }

        try {
            future.get();
            pointLock.showCoordinates();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();

        //Synchro3
        PointLock2 pointLock2 = new PointLock2();
        executorService = Executors.newFixedThreadPool(2000);
        future = null;
        for (int i=0; i<2000; i++){
            future = executorService.submit(() -> {
                pointLock2.moveS(pointLock2,1,1);
                return null;
            });
        }

        try {
            future.get();
            pointLock.showCoordinates();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();

        //Synchro4
        PointLock3 pointLock3 = new PointLock3();
        executorService = Executors.newFixedThreadPool(2000);
        future = null;
        for (int i=0; i<2000; i++){
            future = executorService.submit(() -> {
                pointLock3.moveS(pointLock3,1,1);
                return null;
            });
        }

        try {
            future.get();
            pointLock.showCoordinates();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
