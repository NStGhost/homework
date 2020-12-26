package com.shadow.homework18;

import java.util.concurrent.*;

public class Fibanachi implements Callable<Long> {

    @Override
    public Long call() throws Exception {
        System.out.println("Start fibanachi");
        long temp1 = 0;
        long temp2 = 1;
        long result = 0;
        while (!Thread.currentThread().isInterrupted()) {
            result = temp1 + temp2;
            temp1 = temp2;
            temp2 = result;
            try {
                Thread.sleep(250);
            } catch (Exception e) {
                break;
            }
        }
        System.out.println("Stop fibanachi");
        return result;
    }
}
