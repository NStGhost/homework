package com.shadow.homework18;

import java.util.concurrent.*;

public class Factorial implements Callable<Long> {

    private long result = 0;
    private int i;

    public Factorial(int i) {
        this.i = i;
    }

    @Override
    public Long call() throws Exception {
        long temp = 1;
        for (int j=1; j <= i; j++) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            temp *= j;
        }
        return temp;
    }
}
