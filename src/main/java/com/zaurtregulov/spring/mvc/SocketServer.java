package com.zaurtregulov.spring.mvc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        try (var socketServer = new ServerSocket(7777);
            var socketClient = socketServer.accept();
            var input = new DataInputStream(socketClient.getInputStream());
            var output = new DataOutputStream(socketClient.getOutputStream());
            var scanner = new Scanner(System.in)) {

            String request = input.readUTF();
            while (!"stop".equals(request)) {
                System.out.println("Client request: " + request);
                String response = scanner.nextLine();
                output.writeUTF(response);
                request = input.readUTF();
            }


        }
    }
}
