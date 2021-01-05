package com.shadow.NIO;

import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface IServerHandler {

    public String readMessage(SocketChannel client, ByteBuffer buffer);
    public void sendMessage(SocketChannel client, ByteBuffer buffer);
    public void register(Selector selector, ServerSocketChannel serverSocket);
}
