package com.shadow.homework18;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        firstEx();
        secondEx();
        thirdEx();
        fourthEx();

    }

    private void fourthEx() {
        CopyFiles copyFiles = new CopyFiles();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        copyFiles.setCancel();
    }

    private void thirdEx() {
        StopWatch stopWatch = new StopWatch();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.setCancel();
    }

    private void secondEx() {
        Fibanachi fibanachi = new Fibanachi();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long res1 = fibanachi.cancel();
        System.out.println("Result fibanachi - " + res1);
    }

    private void firstEx() {
        Factorial factorial = new Factorial(10);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long res1 = factorial.cancel();
        System.out.println("Result factorial - " + res1);
    }
}
