package com.example.calculator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Chat messages.
 */
public class ChatMessages extends Chat implements Serializable {
    private ArrayList<ChatMessage> chatMessages;

    // array list of users that can message to chat

    /**
     * Instantiates a new Chat messages.
     *
     * @param admin         the admin
     * @param servergroupId the servergroup id
     * @param name          the name
     */
    public ChatMessages(int admin, int servergroupId, String name) {
        super(admin, servergroupId, name);
        chatMessages = new ArrayList<>();
    }

    /**
     * Gets chat messages.
     *
     * @return the chat messages
     */
    public ArrayList<ChatMessage> getChatMessages() {
        return this.chatMessages;
    }

    /**
     * Sets chat messages.
     *
     * @param chatMessages the chat messages
     */
    public void setChatMessages(ArrayList<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }
}