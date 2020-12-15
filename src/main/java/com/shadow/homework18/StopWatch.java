package com.shadow.homework18;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StopWatch {

    private ExecutorService executorService;
    private boolean isCancel = false;

    public StopWatch() {
        executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            int hour = 0;
            int minute = 0;
            int second = 0;
            while (!isCancel) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                second++;
                if (second == 60) {
                    second = 0;
                    minute++;
                }
                if (minute == 60) {
                    minute = 0;
                    hour++;
                }

                String tempSecond;
                if (second < 10)
                    tempSecond = "0" + second;
                else
                    tempSecond = Integer.toString(second);

                String tempMinute;
                if (minute < 10)
                    tempMinute = "0" + minute;
                else
                    tempMinute = Integer.toString(minute);

                String tempHour;
                if (hour < 10)
                    tempHour = "0" + hour;
                else
                    tempHour = Integer.toString(hour);

                System.out.println(String.format("%s:%s:%s", tempHour, tempMinute, tempSecond));

            }
        });
    }

    public void setCancel() {
        isCancel = true;
        executorService.shutdown();
    }


}
