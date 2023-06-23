package com.example.calculator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Private chats.
 */
public class PrivateChats implements Serializable {

    private ArrayList<PrivateChat> privateChats = new ArrayList<PrivateChat>();

    /**
     * Gets private chats.
     *
     * @return the private chats
     */
    public ArrayList<PrivateChat> getPrivateChats() {
        return this.privateChats;
    }

    /**
     * Sets private chats.
     *
     * @param privateChats the private chats
     */
    public void setPrivateChats(ArrayList<PrivateChat> privateChats) {
        this.privateChats = privateChats;
    }
}
