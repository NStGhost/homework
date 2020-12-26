package com.shadow.Other.lesson27;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    private final BlockingQueue<Item> consumedItem;
    private final ConsumerObsorver consumerObsorver;
    private final Random random;

    public Consumer(BlockingQueue<Item> consumedItem, ConsumerObsorver consumerObsorver) {
        this.consumedItem = consumedItem;
        this.consumerObsorver = consumerObsorver;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Item item = consumedItem.take();
                consumeItem(item);
                consumerObsorver.onItemConsumed(item);
            }
            catch (InterruptedException e) {
                return;
            }
        }
    }

    private void consumeItem(Item item) throws InterruptedException {
        int consumeWork = random.nextInt(item.value.intValue())+1;
        Thread.sleep(consumeWork);
    }

    public interface ConsumerObsorver {
        void onItemConsumed(Item item);
    }
}
