package com.shadow.homework18;

import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyFiles {

    private ExecutorService executorService;
    private boolean isCancel = false;


    public CopyFiles() {
        executorService = Executors.newCachedThreadPool();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                String fileName = "netbeans.exe";
                char[] temp = new char[4048];
                double tempValuePercent = 0.1;
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
                    long size = new File(fileName).length();
                    long sizeCopied = 0;
                    try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("temp.exe")))) {
                        int c = 0;
                        while ((c = bufferedReader.read(temp)) != -1 && !isCancel) {
                            bufferedWriter.write(temp);
                            sizeCopied += temp.length;
                            double d = (double) sizeCopied/size * 100;
                            if (tempValuePercent<d) {
                                System.out.println(String.format("Скопированно - %.1f",d) + "%");
                                tempValuePercent += 0.1;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setCancel() {
        isCancel = true;
        executorService.shutdown();
    }

}
