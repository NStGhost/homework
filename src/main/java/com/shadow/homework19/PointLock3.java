package com.shadow.homework19;

public class PointLock3 {

    private final Object lock = new Object();
    private int x;
    private int y;

    public PointLock3() {
        x = 0;
        y = 0;
    }

    public static  synchronized void moveS(PointLock3 pointLock3, int dx, int dy) {
        pointLock3.x += dx;
        pointLock3.y += dy;
    }

    public void showCoordinates() {
        System.out.println(String.format("X - %d, Y - %d",x,y));
    }

}
