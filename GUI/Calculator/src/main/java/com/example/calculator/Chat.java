package com.example.calculator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Chat.
 */
public abstract class Chat implements Serializable {
    private int id;

    private ArrayList<Integer> usersId;

    private ArrayList<Integer> admins = new ArrayList<Integer>();

    private String name;

    private int servergroupId;

    private ChatMessage pinMessage;

    private int admin;

    /**
     * Instantiates a new Chat.
     *
     * @param admin         the admin
     * @param servergroupId the servergroup id
     * @param name          the name
     */
    public Chat(int admin, int servergroupId, String name) {
        this.name = name;
        usersId = new ArrayList<>();
        this.admin = admin;
        this.servergroupId = servergroupId;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("chats.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            Chats c = (Chats) objectInputStream.readObject();
            ArrayList<Chat> chats = c.getChats();
            if (chats.size() == 0) {
                this.id = 1;
            } else {
                this.id = chats.get(chats.size() - 1).getId() + 1;
            }
            chats.add(this);
            fileOutputStream = new FileOutputStream("chats.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chats);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                objectInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets users id.
     *
     * @return the users id
     */
    public ArrayList<Integer> getUsersId() {
        return usersId;
    }

    /**
     * Sets users id.
     *
     * @param usersId the users id
     */
    public void setUsersId(ArrayList<Integer> usersId) {
        this.usersId = usersId;
    }

    /**
     * Gets admins.
     *
     * @return the admins
     */
    public ArrayList<Integer> getAdmins() {
        return this.admins;
    }

    /**
     * Sets admins.
     *
     * @param admins the admins
     */
    public void setAdmins(ArrayList<Integer> admins) {
        this.admins = admins;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets servergroup id.
     *
     * @return the servergroup id
     */
    public int getServergroupId() {
        return this.servergroupId;
    }

    /**
     * Sets servergroup id.
     *
     * @param servergroupId the servergroup id
     */
    public void setServergroupId(int servergroupId) {
        this.servergroupId = servergroupId;
    }

    /**
     * Gets admin.
     *
     * @return the admin
     */
    public int getAdmin() {
        return this.admin;
    }

    /**
     * Sets admin.
     *
     * @param admin the admin
     */
    public void setAdmin(int admin) {
        this.admin = admin;
    }

    /**
     * Gets pin message.
     *
     * @return the pin message
     */
    public ChatMessage getPinMessage() {
        return this.pinMessage;
    }

    /**
     * Sets pin message.
     *
     * @param pinMessage the pin message
     */
    public void setPinMessage(ChatMessage pinMessage) {
        this.pinMessage = pinMessage;
    }

    @Override
    public String toString() {
        return name + " id : " + id + " in server " + servergroupId;
    }
}