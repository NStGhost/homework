package com.shadow.homework19;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        First();
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
