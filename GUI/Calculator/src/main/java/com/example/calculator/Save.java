package com.example.calculator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save {
    public static void main(String[] args) throws FileNotFoundException {
        ObjectOutputStream objectOutputStream1 = null;
        ObjectOutputStream objectOutputStream2 = null;
        ObjectOutputStream objectOutputStream3 = null;
        ObjectOutputStream objectOutputStream4 = null;
        try {
            objectOutputStream1 = new ObjectOutputStream(new FileOutputStream("Users.bin"));
            objectOutputStream2 = new ObjectOutputStream(new FileOutputStream("groupservers.bin"));
            objectOutputStream3 = new ObjectOutputStream(new FileOutputStream("chats.bin"));
            objectOutputStream4 = new ObjectOutputStream(new FileOutputStream("privateChats.bin"));
            objectOutputStream1.writeObject(new Users());
            objectOutputStream2.writeObject(new GroupServers());
            objectOutputStream3.writeObject(new Chats());
            objectOutputStream4.writeObject(new PrivateChats());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(objectOutputStream1 != null){
                    objectOutputStream1.close();
                }
                if(objectOutputStream2 != null){
                    objectOutputStream2.close();
                }
                if(objectOutputStream3 != null){
                    objectOutputStream3.close();
                }
                if(objectOutputStream4 != null){
                    objectOutputStream4.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
