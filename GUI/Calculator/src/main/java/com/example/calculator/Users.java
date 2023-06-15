package com.example.calculator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Users.
 */
public class Users implements Serializable{
    private ArrayList<User> users;

    /**
     * Instantiates a new Users.
     */
    public Users() {
        users = new ArrayList<>();
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
