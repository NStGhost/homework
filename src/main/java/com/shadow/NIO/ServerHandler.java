package com.shadow.NIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerHandler implements IServerHandler{

    @Override
    public String readMessage(SocketChannel client, ByteBuffer buffer) throws IOException {
        client.read(buffer);
        return new String(buffer.array()).trim();
    }

    @Override
    public void sendMessage(SocketChannel client, ByteBuffer buffer) throws IOException {
        buffer.flip();
        client.write(buffer);
        buffer.clear();
    }

    @Override
    public void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        System.out.println(client.getRemoteAddress());
        client.register(selector, SelectionKey.OP_READ);
    }
}
