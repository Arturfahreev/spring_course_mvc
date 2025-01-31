package com.zaurtregulov.spring.mvc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServerRunner implements Runnable {
    public static void main(String[] args) throws IOException {
        new Thread(new SocketServerRunner()).start();
//        new Thread(new SocketServerRunner()).start();
//        SocketServerRunner socketServerRunner = new SocketServerRunner();
//        socketServerRunner.run();
    }

    @Override
    public void run() {
        try (var serverSocket = new ServerSocket(7777)) {
//            var socket = serverSocket.accept();

            for (int i = 0; i < 5 ; i++) {
                Thread thread = new Thread(() -> {
                    try {
                        Socket accept = serverSocket.accept();
                        var output = new DataOutputStream(accept.getOutputStream());
                        var input = new DataInputStream(accept.getInputStream());
                        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
                        threadLocal.set(1);
                        String threadName1 = Thread.currentThread().getName();
                        while (true) {
                            try {
                                String str = input.readUTF();
                                threadLocal.set(threadLocal.get() + 1);
                                System.out.println("(AT SERVER) request: " + str + " to server " + threadName1 + " number " + threadLocal.get());
                                output.writeUTF("It is Server " + threadName1);
                            } catch (IOException exception) {
                                accept.close();
                                output.close();
                                input.close();
                                System.out.println("Waiting " + threadName1 + "...");
                                accept = serverSocket.accept();
                                output = new DataOutputStream(accept.getOutputStream());
                                input = new DataInputStream(accept.getInputStream());

                            }
                        }
                    } catch(IOException e){
                        throw new RuntimeException(e);
                    }
                });
                thread.start();
            }

            while (true){

            }


//            var outputStream = new DataOutputStream(socket.getOutputStream());
//            var inputStream = new DataInputStream(socket.getInputStream());
//
//            int i = 0;
//            while (true) {
//                String threadName = Thread.currentThread().getName();
//                try {
//                    String strIn = inputStream.readUTF();
//                    System.out.println("(AT SERVER) request: " + strIn + " i = " + i);
//                    outputStream.writeUTF("It is Server " + threadName);
//                    i++;
//                } catch (IOException exception){
//                    socket.close();
//                    outputStream.close();
//                    inputStream.close();
//                    System.out.println("Waiting " + threadName + "...");
//                    socket = serverSocket.accept();
//                    outputStream = new DataOutputStream(socket.getOutputStream());
//                    inputStream = new DataInputStream(socket.getInputStream());
//
//                }
//            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
