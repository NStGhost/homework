package com.shadow.NIO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Client {

    public static void main(String[] args) {
        new Client().run();
    }

    private void run() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i<10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Socket socket = new Socket();
                    try {
                        socket.connect(new InetSocketAddress("localhost",9644));

                        Gson gson = new Gson();
                        String temp = gson.toJson(new TempClass(15,"sas"));

                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write(temp.getBytes());
                        outputStream.flush();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();


    }
}
