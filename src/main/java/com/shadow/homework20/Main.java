package com.shadow.homework20;

import java.util.concurrent.*;


public class Main {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        First();
        FirstA();
        FirstB();
        //Second();
        //Third();
        //ThirdA();
        //Fourth();
        //Fifth();
    }

    private void Fifth() {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        Runnable runnable = () -> {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Thread thread1 = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello ");
            });

            Thread thread2 = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("World! ");
            });
            thread1.start();
            thread2.start();
            countDownLatch.countDown();

        };
        ScheduledFuture<?> future = executorService.scheduleAtFixedRate(runnable, 5,5, TimeUnit.SECONDS);
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                future.cancel(true);
                executorService.shutdown();
            }
        }, 30, TimeUnit.SECONDS);

    }

    private void Fourth() {
        Dead1 dead1 = new Dead1();
        Dead2 dead2 = new Dead2();
        dead1.start();
        dead2.start();

    }

    private void ThirdA() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            for (int i=0; i < 200; i++) {
                TempClass2.getInstance(countDownLatch);
            }
        });
        countDownLatch.countDown();
        executorService.shutdown();
    }

    private void Third() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            for (int i=0; i < 200; i++) {
                TempClass.getInstance(countDownLatch);
            }
        });
        countDownLatch.countDown();
        executorService.shutdown();
    }

    private void Second() {
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

    private void FirstB() {
        int countOperation = 5000;
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

    private void FirstA() {
        int countOperation = 5000;
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

    private void First() {
        int countOperation = 5000;
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


    public class Dead1 extends Thread{
        @Override
        public void run() {
            synchronized (Main.lock1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Main.lock2) {
                    System.out.println("OLOLOLO - 1");
                }
            }
        }
    }

    public class Dead2 extends Thread{
        public void run() {
            synchronized (Main.lock2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Main.lock1) {
                    System.out.println("OLOLOLO - 2");
                }
            }
        }
    }
}
