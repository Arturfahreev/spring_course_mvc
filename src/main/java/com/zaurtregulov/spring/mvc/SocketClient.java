package com.zaurtregulov.spring.mvc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        try (var socket = new Socket("localhost", 7777);
             var input = new DataInputStream(socket.getInputStream());
             var output = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String request = scanner.nextLine();
                output.writeUTF(request);
                String response = input.readUTF();
                System.out.println("Server answered: " + response);

            }
        }


    }
}
