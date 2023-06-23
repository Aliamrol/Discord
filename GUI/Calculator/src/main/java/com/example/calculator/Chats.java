package com.example.calculator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Chats.
 */
public class Chats implements Serializable{
    private ArrayList<Chat> chats = new ArrayList<>();

    /**
     * Gets chats.
     *
     * @return the chats
     */
    public ArrayList<Chat> getChats() {
        return this.chats;
    }

    /**
     * Sets chats.
     *
     * @param chats the chats
     */
    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }
}
