package com.zsj.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

    public static void main(String[] args) {
        ServerSocket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            socket  = new ServerSocket(8080);
            System.out.println("BIO服务器启动");
            while (true){
                Socket accept = socket.accept();
                reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                writer =new PrintWriter(accept.getOutputStream()) ;
                System.out.println(reader.readLine());
                writer.println("服务端接收到");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
