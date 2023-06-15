package com.example.calculator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Group servers.
 */
public class GroupServers implements Serializable {
    private ArrayList<GroupServer> groupServers;

    /**
     * Instantiates a new Group servers.
     */
    public GroupServers() {
        groupServers = new ArrayList<>();
    }

    /**
     * Gets group servers.
     *
     * @return the group servers
     */
    public ArrayList<GroupServer> getGroupServers() {
        return this.groupServers;
    }

    /**
     * Sets group servers.
     *
     * @param groupServers the group servers
     */
    public void setGroupServers(ArrayList<GroupServer> groupServers) {
        this.groupServers = groupServers;
    }
}
