package com.shadow.NIO;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class Server {

    private final int sizeBuffer = 1024;
    private final Charset charset = StandardCharsets.UTF_8;

    public static void main(String[] args) {
        new Server().run();
    }


    private void run()  {
        try {
            final Selector selector = Selector.open();
            final ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(9644));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isAcceptable()) {
                        register(selector, serverSocket);
                    } else if (key.isReadable()) {
                        isRead(key);
                    }
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void isRead(SelectionKey key){
        ServerHandler serverHandler = new ServerHandler();
        SocketChannel client = (SocketChannel) key.channel();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(sizeBuffer);

            String message = serverHandler.readMessage(client, buffer);

            System.out.println(message);
            TempClass temp = new Gson().fromJson(message, TempClass.class);
            System.out.println(temp.getId() + " " + temp.getName());
            buffer = objectToByteBuffer(temp);
            serverHandler.sendMessage(client, buffer);
            client.close();
        } catch (IOException | NullPointerException e) {
            System.out.println("isRead: " + e.getMessage());
            try {
                client.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private ByteBuffer objectToByteBuffer(Object obj) {
        return ByteBuffer.wrap(new Gson().toJson(obj, obj.getClass()).getBytes(charset));
    }

    private void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        System.out.println(client.getRemoteAddress());
        client.register(selector, SelectionKey.OP_READ);
    }




}
