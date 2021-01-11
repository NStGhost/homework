package com.shadow.NIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface IServerHandler {

    public String readMessage(SocketChannel client, ByteBuffer buffer) throws IOException;
    public void sendMessage(SocketChannel client, ByteBuffer buffer) throws IOException;

}
