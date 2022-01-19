/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * io模型
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2022-01-19 8:52 下午
 */
public class BIO {
    public static void main(String[] args) throws IOException {
        while (true) {
            ServerSocket socket = new ServerSocket(9000);
            Socket accept = socket.accept();
            System.out.println("Connecting------->");
            handle(accept);
        }
    }

    private static void handle(Socket accept) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println("reading------>");
        int read = accept.getInputStream().read(bytes);
        if (read != -1) {
            System.out.println("Content:" + new String(bytes, 0, read));
        }
    }
}
