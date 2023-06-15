package com.example.calculator;

import java.io.*;
import java.util.ArrayList;

/**
 * The type Group server.
 */
public class GroupServer implements Serializable {
    private ArrayList<Integer> chats;

    private ArrayList<Integer> admins;

    private int owner;

    private int id;

    private String name;

    /**
     * Instantiates a new Group server.
     *
     * @param userId the user id
     * @param name   the name
     */
    public GroupServer(int userId, String name) {
        chats = new ArrayList<>();
        admins = new ArrayList<>();
        this.name = name;
        this.owner = userId;
        admins.add(userId);
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        FileOutputStream fileOutputStream1 = null;
        ObjectOutputStream objectOutputStream1 = null;
        try {
            fileInputStream = new FileInputStream("groupservers.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            GroupServers groupServers = (GroupServers) objectInputStream.readObject();
            ArrayList<GroupServer> groups = groupServers.getGroupServers();
            GroupServer groupServer;
            if (groups.size() == 0) {
                this.id = 1;
            } else {
                groupServer = groups.get(groups.size() - 1);
                this.id = groupServer.getId() + 1;
            }
            fileOutputStream1 = new FileOutputStream("groupservers.bin");
            objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
            groups.add(this);
            groupServers.setGroupServers(groups);
            objectOutputStream1.writeObject(groupServers);
            fileOutputStream = new FileOutputStream("groupServer-" + this.id + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream1 != null) {
                    fileOutputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (objectOutputStream1 != null) {
                    objectOutputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets chats.
     *
     * @return the chats
     */
    public ArrayList<Integer> getChats() {
        return this.chats;
    }

    /**
     * Sets chats.
     *
     * @param chats the chats
     */
    public void setChats(ArrayList<Integer> chats) {
        this.chats = chats;
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
     * Gets owner.
     *
     * @return the owner
     */
    public int getOwner() {
        return this.owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(int owner) {
        this.owner = owner;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " Id : " + id;
    }
}
