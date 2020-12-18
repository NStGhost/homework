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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fibanachi.interrupt();

        FibanachiRunnable fibanachiRunnable = new FibanachiRunnable(20);
        Thread thread = new Thread(fibanachiRunnable);
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }



    private void First() {
        //No Synchro
        Point point = new Point();
        point.showCoordinates();
        ExecutorService executorService = Executors.newFixedThreadPool(2000);
        Future<Boolean> future = null;
        for (int i=0; i<2000; i++){
            future = executorService.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    point.move(1,1);
                    return null;
                }
            });
        }
        try {
            future.get();
            point.showCoordinates();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        //Synchro
        PointLock pointLock = new PointLock();
        executorService = Executors.newFixedThreadPool(2000);
        future = null;
        for (int i=0; i<2000; i++){
            future = executorService.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    pointLock.moveS(1,1);
                    return null;
                }
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
