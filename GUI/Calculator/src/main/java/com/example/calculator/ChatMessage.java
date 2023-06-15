package com.example.calculator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * The type Chat message.
 */
public class ChatMessage implements Serializable {
    private int chatMessageId;

    private int chatMessagesId;

    private int senderId;

    private String message;

    private HashSet<Integer> likes = new HashSet<>();

    private HashSet<Integer> dislikes = new HashSet<>();

    private HashSet<Integer> hearts = new HashSet<>();

    private long unix_seconds;

    private Date date;

    private SimpleDateFormat jdf;

    private String java_date;

    private DataBase dataBase = new DataBase();

    /**
     * Instantiates a new Chat message.
     *
     * @param message the message
     */
// for server
    public ChatMessage(String message) {
        this.message = message;
        this.senderId = -1;
        this.chatMessagesId = -1;
        Calendar cal = Calendar.getInstance();
        unix_seconds = cal.getTimeInMillis();
        date = new Date(unix_seconds);
        jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java_date = jdf.format(date);
    }

    /**
     * Instantiates a new Chat message.
     *
     * @param message       the message
     * @param senderId      the sender id
     * @param privatechatId the privatechat id
     */
// for privatechat
    public ChatMessage(String message, int senderId, int privatechatId) {
        this.senderId = senderId;
        this.message = message;
        Calendar cal = Calendar.getInstance();
        unix_seconds = cal.getTimeInMillis();
        date = new Date(unix_seconds);
        jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java_date = jdf.format(date);
        this.chatMessagesId = privatechatId;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("privateChats-" + privatechatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChat chatMessages = (PrivateChat) objectInputStream.readObject();
            if (chatMessages.getPrivateChatMessages().size() == 0) {
                this.chatMessageId = 1;
            } else {
                this.chatMessageId = chatMessages.getPrivateChatMessages().get(chatMessages.getPrivateChatMessages().size() - 1)
                        .getChatMessageId() + 1;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instantiates a new Chat message.
     *
     * @param senderId       the sender id
     * @param chatMessagesId the chat messages id
     * @param message        the message
     */
// for chat
    public ChatMessage(int senderId, int chatMessagesId, String message) {
        this.senderId = senderId;
        this.message = message;
        Calendar cal = Calendar.getInstance();
        unix_seconds = cal.getTimeInMillis();
        date = new Date(unix_seconds);
        jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java_date = jdf.format(date);
        this.chatMessagesId = chatMessagesId;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatMessagesId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ChatMessages pv = (ChatMessages) objectInputStream.readObject();
            if (pv.getChatMessages().size() == 0) {
                this.chatMessageId = 1;
            } else {
                this.chatMessageId = pv.getChatMessages().get(pv.getChatMessages().size() - 1)
                        .getChatMessageId() + 1;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets chat message id.
     *
     * @return the chat message id
     */
    public int getChatMessageId() {
        return this.chatMessageId;
    }

    /**
     * Sets chat message id.
     *
     * @param chatMessageId the chat message id
     */
    public void setChatMessageId(int chatMessageId) {
        this.chatMessageId = chatMessageId;
    }

    /**
     * Gets chat messages id.
     *
     * @return the chat messages id
     */
    public int getChatMessagesId() {
        return this.chatMessagesId;
    }

    /**
     * Sets chat messages id.
     *
     * @param chatMessagesId the chat messages id
     */
    public void setChatMessagesId(int chatMessagesId) {
        this.chatMessagesId = chatMessagesId;
    }

    /**
     * Gets sender id.
     *
     * @return the sender id
     */
    public int getSenderId() {
        return this.senderId;
    }

    /**
     * Sets sender id.
     *
     * @param senderId the sender id
     */
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets java date.
     *
     * @return the java date
     */
    public String getJava_date() {
        return java_date;
    }

    /**
     * Gets likes.
     *
     * @return the likes
     */
    public HashSet<Integer> getLikes() {
        return likes;
    }

    /**
     * Sets likes.
     *
     * @param senderId the sender id
     */
    public void setLikes(int senderId) {
        this.likes.add(senderId);
    }

    /**
     * Gets dislikes.
     *
     * @return the dislikes
     */
    public HashSet<Integer> getDislikes() {
        return dislikes;
    }

    /**
     * Sets dislikes.
     *
     * @param senderId the sender id
     */
    public void setDislikes(int senderId) {
        this.dislikes.add(senderId);
    }

    /**
     * Gets hearts.
     *
     * @return the hearts
     */
    public HashSet<Integer> getHearts() {
        return hearts;
    }

    /**
     * Sets hearts.
     *
     * @param senderId the sender id
     */
    public void setHearts(int senderId) {
        this.hearts.add(senderId);
    }

    @Override
    public String toString() {
        String name = dataBase.getUser(this.getSenderId()).getUsername();
        if (senderId == -1 || chatMessagesId == -1) {
            return "server : " + message;
        }
        String result = "";
        result += name + ": " + this.message +  " : " + java_date + " id: "+  chatMessageId +  "\n";
        if (likes.size() != 0) {
            result += "\uD83D\uDC4D" + likes.size();
        }
        if (this.dislikes.size() != 0) {
            result += "\uD83D\uDC4E" + dislikes.size();
        }
        if (this.hearts.size() != 0) {
            result += "❤" + hearts.size();
        }
        return result;
    }
}
