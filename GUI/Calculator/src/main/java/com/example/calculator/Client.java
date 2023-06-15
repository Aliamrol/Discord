package com.example.calculator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * The type Client.
 */
public class Client {
    private Socket socket;

    private User user = new User();

    private DataBase dataBase = new DataBase();

    private String order;

    private int choose;

    private ObjectOutputStream outputStream;

    private ObjectInputStream inputStream;

    /**
     * Instantiates a new Client.
     */
    public Client() {
        try {
            socket = new Socket("127.0.0.1", 2000);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Output client data base.
     */
    public void outputClientDataBase(){
        try {
            outputStream.writeUnshared(dataBase);
        }
        catch (IOException e) {
            System.exit(0);
        }
    }

    /**
     * Input client data base.
     */
    public void inputClientDataBase(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dataBase = (DataBase) inputStream.readObject();
                }
                catch (IOException e) {
                    System.exit(0);
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Connect.
     *
     * @throws Exception the exception
     */

    public User getUser() {
        return user;
    }

    public DataBase getDataBase() {
        return dataBase;
    }
}
