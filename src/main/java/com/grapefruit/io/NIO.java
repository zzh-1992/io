/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * io模型
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2022-01-19 8:52 下午
 */
public class NIO {
    static List<SocketChannel> channelList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(9000));
        channel.configureBlocking(false);
        System.out.println("Server start --->");
        while (true) {
            SocketChannel socketChannel = channel.accept();
            if (socketChannel != null) {
                System.out.println("连接成功--->");
                socketChannel.configureBlocking(false);
                channelList.add(socketChannel);
            }
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel sc = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(6);
                int read = sc.read(byteBuffer);
                if (read > 0) {
                    System.out.println("msg:" + new String(byteBuffer.array()));
                } else if (read == -1) {
                    iterator.remove();
                    System.out.println("连接断开---->");
                }
            }
            System.out.println("Connecting------->");

        }
    }
}
