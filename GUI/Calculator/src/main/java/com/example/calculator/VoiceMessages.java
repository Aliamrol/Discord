package com.example.calculator;

import java.io.Serializable;

/**
 * The type Voice messages.
 */
public class VoiceMessages extends Chat implements Serializable {

    /**
     * Instantiates a new Voice messages.
     *
     * @param id            the id
     * @param servergroupId the servergroup id
     * @param name          the name
     */
    public VoiceMessages(int id , int servergroupId , String name) {
        super(id , servergroupId , name);
    }
}
