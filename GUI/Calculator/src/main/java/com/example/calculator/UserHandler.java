package com.example.calculator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * The type User handler.
 */
public class UserHandler implements Runnable {
    private Socket client;

    private ObjectOutputStream outputStream;

    private ObjectInputStream inputStream;

    private static ArrayList<UserHandler> clients = new ArrayList<>();

    private static DataBase dataBase;

    /**
     * Instantiates a new User handler.
     *
     * @param client the client
     * @throws IOException the io exception
     */
    public UserHandler(Socket client) throws IOException {
        this.client = client;
        outputStream = new ObjectOutputStream(client.getOutputStream());
        inputStream = new ObjectInputStream(client.getInputStream());
    }

    @Override
    public void run() {
        try {
            DataBase newDataBase;
            while (!client.isClosed()) {
                newDataBase = (DataBase) inputStream.readObject();
                updateList(newDataBase);
                sendDataBaseToClients();
            }
            client.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update list.
     *
     * @param updatedDataBase the updated data base
     */
    public synchronized void updateList(DataBase updatedDataBase){
        dataBase = updatedDataBase;
    }

    /**
     * Send data base to clients.
     *
     * @throws IOException the io exception
     */
    public synchronized void sendDataBaseToClients() throws IOException {
        for (UserHandler other : clients) {
            try {
                other.outputStream.writeUnshared(dataBase);
            } catch (IOException e) {
                //closeEverything(other.client, other.outputStream, other.inputStream);
            }
        }
    }

    /**
     * Gets clients.
     *
     * @return the clients
     */
    public ArrayList<UserHandler> getClients() {
        return clients;
    }
}
