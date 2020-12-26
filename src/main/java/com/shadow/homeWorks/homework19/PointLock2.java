package com.shadow.homeWorks.homework19;

public class PointLock2 {

    private static final Object lock = new Object();
    private int x;
    private int y;

    public PointLock2() {
        x = 0;
        y = 0;
    }

    public static void moveS(PointLock2 point, int dx, int dy) {
        synchronized (lock) {
            point.x += dx;
            point.y += dy;
        }
    }

    public void showCoordinates() {
        System.out.println(String.format("X - %d, Y - %d",x,y));
    }

}
