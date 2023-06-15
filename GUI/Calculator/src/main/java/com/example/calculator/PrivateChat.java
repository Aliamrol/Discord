package com.example.calculator;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Private chat.
 */
public class PrivateChat implements Serializable {

    private ArrayList<ChatMessage> privateChatMessages;

    private int[] usersId = new int[2];

    private int id;

    private ChatMessage pin;

    /**
     * Instantiates a new Private chat.
     *
     * @param id1 the id 1
     * @param id2 the id 2
     */
    public PrivateChat(int id1, int id2) {
        usersId = new int[2];
        privateChatMessages = new ArrayList<>();
        this.usersId[0] = id1;
        this.usersId[1] = id2;
        this.pin = null;


        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("privateChats.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChats privateChatMessages = (PrivateChats) objectInputStream.readObject();
            if (privateChatMessages.getPrivateChats().size() < 1) {
                this.id = 1;
            } else {
                this.id = privateChatMessages.getPrivateChats().get(privateChatMessages.getPrivateChats().size() - 1)
                        .getId() + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    }

    /**
     * Gets private chat messages.
     *
     * @return the private chat messages
     */
    public ArrayList<ChatMessage> getPrivateChatMessages() {
        return this.privateChatMessages;
    }

    /**
     * Sets private chat messages.
     *
     * @param privateChatMessages the private chat messages
     */
    public void setPrivateChatMessages(ArrayList<ChatMessage> privateChatMessages) {
        this.privateChatMessages = privateChatMessages;
    }

    /**
     * Get users id int [ ].
     *
     * @return the int [ ]
     */
    public int[] getUsersId() {
        return this.usersId;
    }

    /**
     * Sets users id.
     *
     * @param usersId the users id
     */
    public void setUsersId(int[] usersId) {
        this.usersId = usersId;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
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
     * Gets pin.
     *
     * @return the pin
     */
    public ChatMessage getPin() {
        return this.pin;
    }

    /**
     * Sets pin.
     *
     * @param pin the pin
     */
    public void setPin(ChatMessage pin) {
        this.pin = pin;
    }

}
