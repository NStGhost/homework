package com.shadow.homeWorks.lesson27;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final BlockingQueue<Item> queue = new LinkedBlockingQueue<>();
        Supervisor supervisor = new Supervisor(queue);

        for (int i = 0; i < 200; i++) {
            executor.submit(new Consumer(queue, supervisor));
        }

        for (int i = 0; i < 200; i++) {
            executor.submit(new Producer(queue, supervisor));
        }

        executor.submit(supervisor);

        executor.shutdown();
        try {
            executor.awaitTermination(4, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
