package com.shadow.homework19;

public class FibanachiRunnable implements Runnable{

    private int N;

    public FibanachiRunnable(int N) {
        this.N = N;
    }

    @Override
    public void run() {
        System.out.println("Start fibanachi");
        long temp1 = 0;
        long temp2 = 1;
        long result = 0;
        for (int i=0; i < N; i++) {
            if (Thread.interrupted()){
                break;
            }
            result = temp1 + temp2;
            temp1 = temp2;
            temp2 = result;
        }
        System.out.println("Stop fibanachi");
        System.out.println(String.format("Current value fibanachi - %d", result));
    }
}
