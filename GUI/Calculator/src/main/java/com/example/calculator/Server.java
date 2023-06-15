package com.example.calculator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2000);
            while (true) {
                Socket client;
                client = serverSocket.accept();
                UserHandler userHandler = new UserHandler(client);
                userHandler.getClients().add(userHandler);
                Thread thread = new Thread(userHandler);
                thread.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
