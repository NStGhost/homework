package com.shadow.lesson27;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Supervisor implements Runnable, Producer.ProducerObsorver, Consumer.ConsumerObsorver {
    private final Queue<Item> queue;
    private int lastProduceCount;
    private int lastConsumedCount;
    private final AtomicInteger consumedCount;
    private final AtomicInteger produceCount;

    public Supervisor(Queue<Item> queue) {
        this.queue = queue;
        produceCount = new AtomicInteger(0);
        consumedCount = new AtomicInteger(0);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
                report();
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void report() {
        final int currentConsumedCount = consumedCount.get();
        final int currentProduceCount = produceCount.get();

        final int diffConsumed = currentConsumedCount - lastConsumedCount;
        final int diffProduced = currentProduceCount - lastProduceCount;

        lastProduceCount = currentProduceCount;
        lastConsumedCount = currentConsumedCount;

        System.out.println(String.format("Скорость производства: %d шт/с", diffProduced));
        System.out.println(String.format("Скорость потребления: %d шт/с", diffConsumed));
        System.out.println(String.format("Очередь: %d", queue.size()));
    }

    @Override
    public void onItemConsumed(Item item) {
        consumedCount.getAndIncrement();
    }

    @Override
    public void onItemProduced(Item item) {
        produceCount.getAndIncrement();
    }
}
