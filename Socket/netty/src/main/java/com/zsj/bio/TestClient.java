package com.zsj.bio;

import java.io.*;
import java.net.Socket;

public class TestClient {

    public static void main(String[] args) {
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket("127.0.0.1",8080);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
           if((len = inputStream.read(bytes)) > 0){
               System.out.println(new String(bytes,0,len));
           }
            outputStream.write("客户端连接成功".getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }finally {
        }
    }
}
