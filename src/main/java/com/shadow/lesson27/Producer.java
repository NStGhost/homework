package com.shadow.lesson27;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    private final BlockingQueue<Item> itemQueue;
    private final ProducerObsorver producerObsorver;
    private final Random random;
    private int latestId = 1;

    public Producer(BlockingQueue<Item> itemQueue, ProducerObsorver producerObsorver) {
        this.itemQueue = itemQueue;
        this.random = new Random();
        this.producerObsorver = producerObsorver;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Item produceItem = produceItem();
                itemQueue.add(produceItem);
                producerObsorver.onItemProduced(produceItem);
            }
            catch (InterruptedException e) {
                return;
            }
        }
    }

    private Item produceItem() throws InterruptedException{
        final int workedValue = random.nextInt(1)+1;
        Thread.sleep(workedValue);
        final Item item = new Item(latestId++);
        item.value = workedValue;
        return item;
    }

    public interface ProducerObsorver {
        void onItemProduced(Item item);
    }
}
