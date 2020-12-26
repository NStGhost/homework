package com.shadow.homeWorks.homework19;

public class PointLock1 {

    private final Object lock = new Object();
    private int x;
    private int y;

    public PointLock1() {
        x = 0;
        y = 0;
    }

    public void moveS(int dx, int dy) {
        synchronized (lock) {
            x += dx;
            y += dy;
        }
    }

    public void showCoordinates() {
        System.out.println(String.format("X - %d, Y - %d",x,y));
    }

}
