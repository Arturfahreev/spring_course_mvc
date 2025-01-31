package com.zaurtregulov.spring.mvc;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class SocketRunnerDemo implements Runnable {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("We are in main " + Thread.currentThread().getName());

            Thread thread = new Thread(new SocketRunnerDemo());
            thread.start();
            Thread.sleep(1000L);
        }



    }

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 7777);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            for (int i = 0; i < 10; i++) {
                String threadName = Thread.currentThread().getName();
                System.out.println("It is " + threadName);
                out.writeUTF("Hello from " + threadName);
                String response = in.readUTF();
                System.out.println("Response from Server: " + response);
                Thread.sleep(1000L);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
