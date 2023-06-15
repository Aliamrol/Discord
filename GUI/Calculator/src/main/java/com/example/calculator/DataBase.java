package com.example.calculator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 * The type Data base.
 */
public class DataBase implements Serializable {
    private Users users;

    /**
     * Instantiates a new Data base.
     */
    public DataBase() {
        this.users = new Users();
    }

    /**
     * Login boolean.
     *
     * @param username the username
     * @param password the password
     * @param user     the user
     * @return the boolean
     */
    public boolean login(String username, String password, User user) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getUsername().equals(username)) {
                    if (password.equals(u.getPassword())) {
                        user.setUsername(u.getUsername());
                        user.setPassword(u.getPassword());
                        user.setId(u.getId());
                        user.setEmail(u.getEmail());
                        user.setPictureAddress(u.getPictureAddress());
                        user.setState(u.getState());
                        return true;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Check login boolean.
     *
     * @param username the username
     * @param password the password
     * @param user     the user
     * @return the boolean
     */
    public boolean checkLogin(String username, String password, User user) {
        if (login(username, password, user)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return id after login int.
     *
     * @param username the username
     * @param password the password
     * @return the int
     */
    public int returnIdAfterLogin(String username, String password) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getUsername().equals(username)) {
                    if (password.equals(u.getPassword())) {
                        return u.getId();
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * Signup boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean signup(User user) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            // ^ (u.getPassword() == null || u.getEmail() == null)
            for (User u : users.getUsers()) {
                if (u.getUsername().equals(user.getUsername())) {
                    return false;
                }
            }
            if (users.getUsers().size() == 0) {
                user.setId((1));
            }
            else {
                user.setId(users.getUsers().get(users.getUsers().size() - 1).getId() + 1);
            }
            users.getUsers().add(user);
            fileOutputStream = new FileOutputStream("Users.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Check signup boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean checkSignup(User user) {
        if (signup(user)) {
            System.out.println("You have successfully signed up");
            return true;
        } else {
            System.out.println("This username already exists, please choose another username");
            return false;
        }
    }

    /**
     * Return id after signup int.
     *
     * @param user the user
     * @return the int
     */
    public int returnIdAfterSignup(User user) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getUsername().equals(user.getUsername())) {
                    return u.getId();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * Update user.
     *
     * @param updatedUser the updated user
     */
    public void updateUser(User updatedUser) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            ArrayList<User> newUsers = new ArrayList<>();
            for (User u : users.getUsers()) {
                if (u.getId() != updatedUser.getId()) {
                    newUsers.add(u);
                }
            }
            users.setUsers(newUsers);
            users.getUsers().add(updatedUser);
            fileOutputStream = new FileOutputStream("Users.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Delete user.
     *
     * @param user the user
     */
    public void deleteUser(User user) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            ArrayList<User> newUsers = new ArrayList<>();
            for (User u : users.getUsers()) {
                if (u.getId() != user.getId()) {
                    newUsers.add(u);
                }
            }
            users.setUsers(newUsers);
            fileOutputStream = new FileOutputStream("Users.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Determine id integer.
     *
     * @param receiverUsername the receiver username
     * @return the integer
     */
    public Integer determineId(String receiverUsername) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getUsername().equals(receiverUsername)) {
                    Integer receiverId = u.getId();
                    return receiverId;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * Add to friend request list boolean.
     *
     * @param senderId   the sender id
     * @param receiverId the receiver id
     * @return the boolean
     */
    public boolean addToFriendRequestList(int senderId, int receiverId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            if (senderId != receiverId) {
                fileInputStream = new FileInputStream("Users.bin");
                objectInputStream = new ObjectInputStream(fileInputStream);
                users = (Users) objectInputStream.readObject();
                int i = 0;
                for (User u : users.getUsers()) {
                    if (u.getId() == receiverId) {
                        HashSet<Integer> newFriendsRequests = u.getFriendRequests();
                        newFriendsRequests.add(senderId);
                        u.setFriendRequests(newFriendsRequests);
                        break;
                    }
                    i++;
                }
                if(i == users.getUsers().size()){
                    return false;
                }
                fileOutputStream = new FileOutputStream("Users.bin");
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(users);
                return true;
            }
            else {
                return false;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Clear friend request.
     *
     * @param myId     the my id
     * @param friendId the friend id
     */
    public void clearFriendRequest(int myId, int friendId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            HashSet<Integer> newFriendsRequestId = new HashSet<>();
            for (User u : users.getUsers()) {
                if (u.getId() == myId) {
                    for (Integer id : u.getFriendRequests()) {
                        if (id != friendId) {
                            newFriendsRequestId.add(id);
                        }
                    }
                    u.setFriendRequests(newFriendsRequestId);
                    break;
                }
            }
            fileOutputStream = new FileOutputStream("Users.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Accept request integer.
     *
     * @param username the username
     * @param myId     the my id
     * @return the integer
     */
    public Integer acceptRequest(String username, int myId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            int friendId = 0;
            for (User u : users.getUsers()) {
                if (u.getUsername().equals(username)) {
                    friendId = u.getId();
                }
            }
            for (User u : users.getUsers()) {
                if (u.getId() == myId) {
                    for (Integer id : u.getFriendRequests()) {
                        if (id == friendId) {
                            return id;
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * Add to friends list.
     *
     * @param myId     the my id
     * @param friendId the friend id
     */
    public void addToFriendsList(int myId, int friendId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getId() == myId) {
                    u.getFriendsId().add(friendId);
                    break;
                }
            }
            fileOutputStream = new FileOutputStream("Users.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Remove from friends list.
     *
     * @param myId      the my id
     * @param blockedId the blocked id
     */
    public void removeFromFriendsList(int myId, int blockedId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getId() == myId) {
                    u.getFriendsId().remove(blockedId);
                    break;
                }
            }
            fileOutputStream = new FileOutputStream("Users.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Add to block list.
     *
     * @param myId      the my id
     * @param blockedId the blocked id
     */
    public void addToBlockList(int myId, int blockedId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getId() == myId) {
                    u.getBlocked().add(blockedId);
                    break;
                }
            }
            fileOutputStream = new FileOutputStream("Users.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Remove from block list.
     *
     * @param myId      the my id
     * @param blockedId the blocked id
     */
    public void removeFromBlockList(int myId, int blockedId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getId() == myId) {
                    u.getBlocked().remove(blockedId);
                    break;
                }
            }
            fileOutputStream = new FileOutputStream("Users.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Check friends list boolean.
     *
     * @param myId   the my id
     * @param userId the user id
     * @return the boolean
     */
    public boolean checkFriendsList(int myId, int userId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getId() == myId) {
                    for (Integer id : u.getFriendsId()) {
                        if (id == userId) {
                            return true;
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Check block list boolean.
     *
     * @param myId   the my id
     * @param userId the user id
     * @return the boolean
     */
    public boolean checkBlockList(int myId, int userId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getId() == myId) {
                    for (Integer id : u.getBlocked()) {
                        if (id == userId) {
                            return true;
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Return picture address string.
     *
     * @param username the username
     * @return the string
     */
    public String returnPictureAddress(String username){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if(u.getUsername().equals(username)){
                    return u.getPictureAddress();
                }
            }
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Return status string.
     *
     * @param id the id
     * @return the string
     */
    public String returnStatus(int id){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if(u.getId() == id){
                    return u.getState();
                }
            }
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Show users all friends string.
     *
     * @param user the user
     * @return the string
     */
    public String showUsersAllFriends(User user) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        StringBuilder string = new StringBuilder();
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getId() == user.getId()) {
                    user.setFriendsId(u.getFriendsId());
                    break;
                }
            }
            for (User u : users.getUsers()) {
                for (int friend : user.getFriendsId()) {
                    if (friend == u.getId()) {
                        string.append(u.getUsername()).append(" ");
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return string.toString();
    }

    /**
     * Show users online friends string.
     *
     * @param user the user
     * @return the string
     */
    public String showUsersOnlineFriends(User user) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        StringBuilder string = new StringBuilder();
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getId() == user.getId()) {
                    user.setFriendsId(u.getFriendsId());
                    break;
                }
            }
            for (User u : users.getUsers()) {
                for (int friend : user.getFriendsId()) {
                    if (friend == u.getId() && u.getState().equals("Online")) {
                        string.append(u.getUsername()).append(" ");
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return string.toString();
    }

    /**
     * Show users blocked string.
     *
     * @param user the user
     * @return the string
     */
    public String showUsersBlocked(User user) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        StringBuilder string = new StringBuilder();
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getId() == user.getId()) {
                    user.setBlocked(u.getBlocked());
                    break;
                }
            }
            for (User u : users.getUsers()) {
                for (int blocked : user.getBlocked()) {
                    if (blocked == u.getId()) {
                        string.append(u.getUsername()).append(" ");
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return string.toString();
    }

    /**
     * Show friend requests string.
     *
     * @param user the user
     * @return the string
     */
    public String showFriendRequests(User user) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        StringBuilder string = new StringBuilder();
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getId() == user.getId()) {
                    user.setFriendRequests(u.getFriendRequests());
                    break;
                }
            }
            for (User u : users.getUsers()) {
                for (Integer id : user.getFriendRequests()) {
                    if (u.getId() == id) {
                        string.append(u.getUsername()).append(" ");
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return string.toString();
    }

    /**
     * Gets user.
     *
     * @param userId the user id
     * @return the user
     */
    public User getUser(int userId) {
        User server = new User();
        server.setUsername("Server");
        if(userId == -1){
            return server;
        }
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("Users.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (Users) objectInputStream.readObject();
            for (User u : users.getUsers()) {
                if (u.getId() == userId) {
                    return u;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * User in chat boolean.
     *
     * @param userId the user id
     * @param chatId the chat id
     * @return the boolean
     */
// show boolean of user in chat or not in chat
    public boolean userInChat(int userId, int chatId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ChatMessages chat = (ChatMessages) objectInputStream.readObject();
            for (Integer i : chat.getUsersId()) {
                if (i == userId) {
                    return true;
                }
            }
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
        }
        return false;
    }

    /**
     * User admin chat boolean.
     *
     * @param userId the user id
     * @param chatId the chat id
     * @return the boolean
     */
// show boolean of user is adminORNo
    public boolean userAdminChat(int userId, int chatId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ChatMessages chat = (ChatMessages) objectInputStream.readObject();
            for (Integer i : chat.getAdmins()) {
                if (i == userId) {
                    return true;
                }
            }
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
        }
        return false;
    }

    /**
     * Send message to chat string.
     *
     * @param userId  the user id
     * @param chatId  the chat id
     * @param message the message
     * @return the string
     */
// send message to chat
    public boolean sendMessageToChat(int userId, int chatId, String message) {
        if (!userInChat(userId, chatId)) {
            return false;
        }
        if (!userAdminChat(userId, chatId)) {
            return false;
        }
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ChatMessages chatMessages = (ChatMessages) objectInputStream.readObject();
            ChatMessage chatMessage = new ChatMessage(userId, chatId, message);
            ArrayList<ChatMessage> chatMessageCreated = chatMessages.getChatMessages();
            chatMessageCreated.add(chatMessage);
            chatMessages.setChatMessages(chatMessageCreated);
            fileOutputStream = new FileOutputStream("chat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chatMessages);

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
        }
        return true;
    }

    /**
     * Edit message chat string.
     *
     * @param userId        the user id
     * @param chatId        the chat id
     * @param chatMessageId the chat message id
     * @param chatMessage   the chat message
     * @return the string
     */
// edit a message in chat
    public boolean editMessageChat(int userId, int chatId, int chatMessageId, String chatMessage) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ChatMessages chatMessages = (ChatMessages) objectInputStream.readObject();
            ArrayList<ChatMessage> messages = chatMessages.getChatMessages();
            for (ChatMessage ch : messages) {
                if (ch.getSenderId() != userId && ch.getChatMessageId() == chatMessageId) {
                    return false;
                }
                if (ch.getSenderId() == userId && ch.getChatMessageId() == chatMessageId) {
                    ch.setMessage(chatMessage);
                }
            }
            chatMessages.setChatMessages(messages);
            fileOutputStream = new FileOutputStream("chat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chatMessages);
        } catch (Exception e) {
            return false;
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
        }
        return true;
    }

    /**
     * Reaction message chat string.
     *
     * @param userId        the user id
     * @param chatId        the chat id
     * @param chatMessageId the chat message id
     * @param reaction      the reaction
     * @return the string
     */
// reaction to a message in chat
    public boolean reactionMessageChat(int userId, int chatId, int chatMessageId, String reaction) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ChatMessages chat = (ChatMessages) objectInputStream.readObject();
            if (!userInChat(userId, chatId)) {
                return false;
            }
            if (!reaction.equals("heart") && !reaction.equals("like") && !reaction.equals("dislike")) {
                return false;
            }
            boolean flag = false;
            ArrayList<ChatMessage> messages = chat.getChatMessages();
            if (reaction.equals("like")) {
                for (ChatMessage ch : messages) {
                    if (ch.getChatMessageId() == chatMessageId) {
                        ch.setLikes(userId);
                        flag = true;
                    }
                }
            }
            if (reaction.equals("dislike")) {
                for (ChatMessage ch : messages) {
                    if (ch.getChatMessageId() == chatMessageId) {
                        ch.setDislikes(userId);
                        flag = true;
                    }
                }
            }
            if (reaction.equals("heart")) {
                for (ChatMessage ch : messages) {
                    if (ch.getChatMessageId() == chatMessageId) {
                        ch.setHearts(userId);
                        flag = true;
                    }
                }
            }
            if (!flag) {
                return false;
            }
            chat.setChatMessages(messages);
            fileOutputStream = new FileOutputStream("chat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chat);
        } catch (Exception e) {
            return false;
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
        }
        return true;

    }

    /**
     * Pin message chat string.
     *
     * @param userId        the user id
     * @param chatId        the chat id
     * @param chatmessageId the chatmessage id
     * @return the string
     */
// pin a message in a chat
    public boolean pinMessageChat(int userId, int chatId, int chatmessageId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ChatMessages chatMessages = (ChatMessages) objectInputStream.readObject();
            Boolean flag = false;
            if (chatMessages.getAdmin() == userId) {
                flag = true;
            }
            if (userAdminChat(userId, chatId)) {
                flag = true;
            }
            if (!flag) {
                return false;
            }
            ArrayList<ChatMessage> messages = chatMessages.getChatMessages();
            ChatMessage pin = null;
            for (ChatMessage message : messages) {
                if (message.getChatMessageId() == chatmessageId) {
                    pin = message;
                }
            }
            chatMessages.setPinMessage(pin);
            fileOutputStream = new FileOutputStream("chat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chatMessages);

        } catch (Exception e) {
            return false;
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
        }
        return true;
    }

    /**
     * Delete message chat string.
     *
     * @param userId        the user id
     * @param chatId        the chat id
     * @param chatMessageId the chat message id
     * @return the string
     */
// delete a message from a chat
    public boolean deleteMessageChat(int userId, int chatId, int chatMessageId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ChatMessages chatMessages = (ChatMessages) objectInputStream.readObject();
            ArrayList<ChatMessage> messages = chatMessages.getChatMessages();
            for (ChatMessage ch : messages) {
                if (ch.getSenderId() != userId && ch.getChatMessageId() == chatMessageId) {
                    return false;
                }
                if (ch.getSenderId() == userId && ch.getChatMessageId() == chatMessageId) {
                    messages.remove(ch);
                }
            }
            chatMessages.setChatMessages(messages);
            fileOutputStream = new FileOutputStream("chat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chatMessages);
        }
        catch (Exception e) {
            return false;
        }
        finally {
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
        }
        return true;
    }

    /**
     * Create chat messages string.
     *
     * @param userId        the user id
     * @param servergroupId the servergroup id
     * @param nameChat      the name chat
     * @return the string
     */
// create a chat chatMessages
    public int createChatMessages(int userId, int servergroupId, String nameChat) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream1 = null;
        ObjectInputStream objectInputStream1 = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream1 = null;
        ObjectOutputStream objectOutputStream1 = null;
        FileOutputStream fileOutputStream2 = null;
        ObjectOutputStream objectOutputStream2 = null;
        int id = 0;
        try {
            fileInputStream = new FileInputStream("chats.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            fileInputStream1 = new FileInputStream("groupServer-" + servergroupId + ".bin");
            objectInputStream1 = new ObjectInputStream(fileInputStream1);
            GroupServer groupServer = (GroupServer) objectInputStream1.readObject();
            ArrayList<Integer> admins = groupServer.getAdmins();
            boolean checkAdmin = false;
            if (groupServer.getOwner() == userId) {
                checkAdmin = true;
            }
            for (int i : admins) {
                if (i == userId) {
                    checkAdmin = true;
                    break;
                }
            }
            if (!checkAdmin) {
                return 0;
            }
            Chats c = (Chats) objectInputStream.readObject();
            ArrayList<Chat> chats = c.getChats();
            ChatMessages chatMessages = new ChatMessages(userId, servergroupId, nameChat);
            id = chatMessages.getId();
            ArrayList<Integer> adminss = chatMessages.getAdmins();
            adminss.add(userId);
            ArrayList<Integer> users = chatMessages.getUsersId();
            users.add(userId);
            chatMessages.setUsersId(users);
            chatMessages.setAdmins(adminss);
            chats.add(chatMessages);
            ArrayList<Integer> chatsGroup = groupServer.getChats();
            chatsGroup.add(chatMessages.getId());
            groupServer.setChats(chatsGroup);
            c.setChats(chats);
            fileOutputStream = new FileOutputStream("chats.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(c);
            fileOutputStream1 = new FileOutputStream("chat-" + chatMessages.getId() + ".bin");
            objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
            objectOutputStream1.writeObject(chatMessages);
            fileOutputStream2 = new FileOutputStream("groupServer-" + servergroupId + ".bin");
            objectOutputStream2 = new ObjectOutputStream(fileOutputStream2);
            objectOutputStream2.writeObject(groupServer);
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
                if (fileInputStream1 != null) {
                    fileInputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream1 != null) {
                    objectInputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream1 != null) {
                    fileOutputStream1.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectOutputStream1 != null) {
                    objectOutputStream1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectOutputStream2 != null) {
                    objectOutputStream2.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return id;
    }

    /**
     * Add user chat string.
     *
     * @param userId  the user id
     * @param memeber the memeber
     * @param chatId  the chat id
     * @return the string
     */
// done
    // add user to a chat messages
    public boolean addUserChat(int userId, int memeber, int chatId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream1 = null;
        ObjectInputStream objectInputStream1 = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream1 = null;
        ObjectOutputStream objectOutputStream1 = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            fileInputStream1 = new FileInputStream("chats.bin");
            objectInputStream1 = new ObjectInputStream(fileInputStream1);
            Chats chats = (Chats) objectInputStream1.readObject();
            ArrayList<Chat> allChats = chats.getChats();
            ChatMessages chat = (ChatMessages) objectInputStream.readObject();
            if (!userAdminChat(userId, chatId)) {
                return false;
            }
            ArrayList<Integer> users = chat.getUsersId();
            users.add(memeber);
            chat.setUsersId(users);
            for(Chat chat1 : allChats){
                if(chat1.getId() == chatId){
                    chat1.getUsersId().add(memeber);
                    break;
                }
            }
            chats.setChats(allChats);
            fileOutputStream = new FileOutputStream("chat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chat);
            fileOutputStream1 = new FileOutputStream("chats.bin");
            objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
            objectOutputStream1.writeObject(chats);
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileInputStream1 != null) {
                    fileInputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (objectInputStream1 != null) {
                    objectInputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (fileOutputStream1 != null) {
                    fileOutputStream1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (objectOutputStream1 != null) {
                    objectOutputStream1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Add admin chat string.
     *
     * @param userId the user id
     * @param admin  the admin
     * @param chatId the chat id
     * @return the string
     */
// add a user to admin arraylist
    public boolean addAdminChat(int userId, int admin, int chatId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream1 = null;
        ObjectInputStream objectInputStream1 = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream1 = null;
        ObjectOutputStream objectOutputStream1 = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            fileInputStream1 = new FileInputStream("chats.bin");
            objectInputStream1 = new ObjectInputStream(fileInputStream1);
            Chats chats = (Chats) objectInputStream1.readObject();
            ArrayList<Chat> allChats = chats.getChats();
            ChatMessages chat = (ChatMessages) objectInputStream.readObject();
            if (chat.getAdmin() != userId) {
                return false;
            }
            ArrayList<Integer> admins = chat.getAdmins();
            admins.add(admin);
            chat.setAdmins(admins);
            ArrayList<Integer> users = chat.getUsersId();
            boolean flag = false;
            for (Integer user : users) {
                if (user == admin) {
                    flag = true;
                }
            }
            if (!flag) {
                users.add(admin);
            }
            chat.setUsersId(users);
            for(Chat chat1 : allChats){
                if(chat1.getId() == chatId){
                    chat1.getAdmins().add(admin);
                    break;
                }
            }
            chats.setChats(allChats);
            fileOutputStream = new FileOutputStream("chat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chat);
            fileOutputStream1 = new FileOutputStream("chats.bin");
            objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
            objectOutputStream1.writeObject(chats);
        }  catch (Exception e) {
            return false;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileInputStream1 != null) {
                    fileInputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (objectInputStream1 != null) {
                    objectInputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (fileOutputStream1 != null) {
                    fileOutputStream1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (objectOutputStream1 != null) {
                    objectOutputStream1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Return users array list.
     *
     * @param chatId the chat id
     * @return the array list
     */
    public ArrayList<User> returnUsers(int chatId){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("chats.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            Chats chats = (Chats) objectInputStream.readObject();
            ArrayList<Chat> allChats = chats.getChats();
            ArrayList<User> myUsers = new ArrayList<>();
            for(Chat chat : allChats){
                if(chat.getId() == chatId){
                    for(Integer id : chat.getUsersId()){
                        User user = getUser(id);
                        myUsers.add(user);
                    }
                    break;
                }
            }
            return myUsers;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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
        }
        return null;
    }

    /**
     * Return admins array list.
     *
     * @param chatId the chat id
     * @return the array list
     */
    public ArrayList<User> returnAdmins(int chatId){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("chats.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            Chats chats = (Chats) objectInputStream.readObject();
            ArrayList<Chat> allChats = chats.getChats();
            ArrayList<User> myAdmins = new ArrayList<>();
            for(Chat chat : allChats){
                if(chat.getId() == chatId){
                    for(Integer id : chat.getAdmins()){
                        User admin = getUser(id);
                        myAdmins.add(admin);
                    }
                    break;
                }
            }
            return myAdmins;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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
        }
        return null;
    }

    /**
     * Delete admin chat string.
     *
     * @param userId  the user id
     * @param deleted the deleted
     * @param chatId  the chat id
     * @return the string
     */
// remove the admin
    public String deleteAdminChat(int userId, int deleted, int chatId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ChatMessages chat = (ChatMessages) objectInputStream.readObject();
            if (chat.getAdmin() != userId) {
                return "You are not owner";
            }
            ArrayList<Integer> admins = chat.getAdmins();
            admins.remove(admins.indexOf(deleted));
            chat.setAdmins(admins);
            fileOutputStream = new FileOutputStream("chat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chat);
        } catch (Exception e) {
            return "The id is invalid";
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
        }
        return "Admin was removed";
    }

    /**
     * Delete user chat string.
     *
     * @param userId  the user id
     * @param deleted the deleted
     * @param chatId  the chat id
     * @return the string
     */
// remove a user from chat
    public String deleteUserChat(int userId, int deleted, int chatId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ChatMessages chat = (ChatMessages) objectInputStream.readObject();
            Boolean flag = false;
            if (chat.getAdmin() == userId) {
                flag = true;
            }
            if (userAdminChat(userId, chatId)) {
                flag = true;
            }
            if (!flag) {
                return "You are not admin";
            }
            ArrayList<Integer> users = chat.getUsersId();
            users.remove(users.indexOf(deleted));
            chat.setUsersId(users);
            fileOutputStream = new FileOutputStream("chat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chat);
        } catch (Exception e) {
            return "The id is invalid";
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
        }
        return "User was removed";
    }

    /**
     * Show messages chat array list.
     *
     * @param userId the user id
     * @param chatId the chat id
     * @return the array list
     */
// show messages of chat
    public ArrayList<ChatMessage> showMessagesChat(int userId, int chatId) {
        ArrayList<ChatMessage> invalid = new ArrayList<ChatMessage>();
        invalid.add(new ChatMessage("The id is invalid"));
        ArrayList<ChatMessage> notUser = new ArrayList<ChatMessage>();
        notUser.add(new ChatMessage("You are not allowed to see this chat"));
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ChatMessages chat = (ChatMessages) objectInputStream.readObject();
            if (!userInChat(userId, chatId)) {
                return notUser;
            }
            ArrayList<ChatMessage> show = chat.getChatMessages();
            if (chat.getPinMessage() != null) {
                show.add(new ChatMessage( "The pin message is :"));
                show.add(chat.getPinMessage());
            }
            return show;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return invalid;
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
        }
    }

    /**
     * Add admin group server string.
     *
     * @param userId        the user id
     * @param admin         the admin
     * @param groupServerId the group server id
     * @return the string
     */
// add admin to a servergroup
    public boolean addAdminGroupServer(int userId, int admin, int groupServerId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream1 = null;
        ObjectInputStream objectInputStream1 = null;
        FileOutputStream fileOutputStream1 = null;
        ObjectOutputStream objectOutputStream1 = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("groupServer-" + groupServerId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            fileInputStream1 = new FileInputStream("groupservers.bin");
            objectInputStream1 = new ObjectInputStream(fileInputStream1);
            GroupServer groupServer = (GroupServer) objectInputStream.readObject();
            GroupServers groupServers = (GroupServers) objectInputStream1.readObject();
            ArrayList<GroupServer> allGroupServers = groupServers.getGroupServers();
            if (groupServer.getOwner() != userId) {
                return false;
            }
            for(GroupServer groupServer1 : allGroupServers){
                if(groupServer1.getId() == groupServerId){
                    groupServer1.getAdmins().add(admin);
                }
            }
            groupServers.setGroupServers(allGroupServers);
            groupServer.getAdmins().add(admin);
            fileOutputStream1 = new FileOutputStream("groupservers.bin");
            objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
            objectOutputStream1.writeObject(groupServers);
            fileOutputStream = new FileOutputStream("groupServer-" + groupServerId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(groupServer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileInputStream1 != null) {
                    fileInputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (objectInputStream1 != null) {
                    objectInputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (fileOutputStream1 != null) {
                    fileOutputStream1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (objectOutputStream1 != null) {
                    objectOutputStream1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Delete admin group server string.
     *
     * @param userId        the user id
     * @param admin         the admin
     * @param groupServerId the group server id
     * @return the string
     */
// remove a admin from servergroup
    public String deleteAdminGroupServer(int userId, int admin, int groupServerId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("groupeServer-" + groupServerId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            GroupServer groupServer = (GroupServer) objectInputStream.readObject();
            if (groupServer.getOwner() != userId) {
                return "You are not owner of the group server";
            }
            ArrayList<Integer> admins = groupServer.getAdmins();
            Boolean flag = false;
            for (int i : admins) {
                if (i == admin) {
                    flag = true;
                }
            }
            if (!flag) {
                return "user with id " + admin + " is not admin";
            }
            admins.remove(admins.indexOf(admin));
            groupServer.setAdmins(admins);
            fileOutputStream = new FileOutputStream("groupeServer-" + groupServerId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(groupServer);
        } catch (Exception e) {
            return "The id is invalid";
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
        }
        return "Successfully done";
    }

    /**
     * Create server group string.
     *
     * @param userId the user id
     * @param name   the name
     * @return the string
     */
// create a server group
    public int createServerGroup(int userId, String name) {
        GroupServer group = new GroupServer(userId, name);
        return group.getId();
    }

    /**
     * Return group server group server.
     *
     * @param groupServerId the group server id
     * @return the group server
     */
    public GroupServer returnGroupServer(int groupServerId){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("groupservers.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            GroupServers groupServers = (GroupServers) objectInputStream.readObject();
            ArrayList<GroupServer> allGroupServers = groupServers.getGroupServers();
            for(GroupServer groupServer : allGroupServers){
                if(groupServer.getId() == groupServerId){
                    return groupServer;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Pin message private chat string.
     *
     * @param userId        the user id
     * @param privateChatId the private chat id
     * @param chatmessageId the chatmessage id
     * @return the string
     */
// pin the message in privatechat
    public boolean pinMessagePrivateChat(int userId, int privateChatId, int chatmessageId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("privateChats-" + privateChatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChat privateChat = (PrivateChat) objectInputStream.readObject();
            if (privateChat.getUsersId()[0] != userId && privateChat.getUsersId()[1] != userId) {
                return false;
            }
            boolean flag = false;
            for (ChatMessage m : privateChat.getPrivateChatMessages()) {
                if (m.getChatMessageId() == chatmessageId) {
                    privateChat.setPin(m);
                    flag = true;
                }
            }
            if (!flag) {
                return false;
            }
            fileOutputStream = new FileOutputStream("privateChats-" + privateChatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(privateChat);
        } catch (Exception e) {
            return false;
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
        }
        return true;
    }

    /**
     * Check pin private chat boolean.
     *
     * @param privateChatId the private chat id
     * @return the boolean
     */
    public boolean checkPinPrivateChat(int privateChatId){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("privateChats-" + privateChatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChat privateChat = (PrivateChat) objectInputStream.readObject();
            if(privateChat.getPin() != null){
                return true;
            }
            fileOutputStream = new FileOutputStream("privateChats-" + privateChatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(privateChat);
        }
        catch (Exception e) {
            return false;
        }
        finally {
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
        }
        return false;
    }

    /**
     * Creat private chat string.
     *
     * @param user1 the user 1
     * @param user2 the user 2
     * @return the string
     */
// create a privatechat
    public int creatPrivateChat(int user1, int user2) {
        boolean flag = false;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream1 = null;
        ObjectOutputStream objectOutputStream1 = null;
        PrivateChat pr = null;
        int id = 0;
        try {
            fileInputStream = new FileInputStream("privateChats.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChats privateChats = (PrivateChats) objectInputStream.readObject();
            for (PrivateChat p : privateChats.getPrivateChats()) {
                if (p.getUsersId()[0] == user1 && p.getUsersId()[1] == user2) {
                    flag = true;
                    pr = p;
                }
                if (p.getUsersId()[0] == user2 && p.getUsersId()[1] == user1) {
                    flag = true;
                    pr = p;
                }
            }
            if (flag) {
                return 0;
            }
            PrivateChat privateChat = new PrivateChat(user1, user2);
            ArrayList<PrivateChat> privateChats1 = privateChats.getPrivateChats();
            privateChats1.add(privateChat);
            privateChats.setPrivateChats(privateChats1);
            fileOutputStream = new FileOutputStream("privateChats.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(privateChats);
            fileOutputStream1 = new FileOutputStream("privateChats-" + privateChat.getId() + ".bin");
            objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
            objectOutputStream1.writeObject(privateChat);
            id = privateChat.getId();
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

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectOutputStream1 != null) {
                    objectOutputStream1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    /**
     * Send message to private chat string.
     *
     * @param userid        the userid
     * @param privateChatId the private chat id
     * @param message       the message
     * @return the string
     */
// send a message to privatechat
    public String sendMessageToPrivateChat(int userid, int privateChatId, String message) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        FileInputStream fileInputStream1 = null;
        ObjectInputStream objectInputStream1 = null;
        try {
            fileInputStream = new FileInputStream("privateChats-" + privateChatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChat pr = (PrivateChat) objectInputStream.readObject();
            if (pr.getUsersId()[0] != userid && pr.getUsersId()[1] != userid) {
                return "You are not allowed to send message to this private chat";
            }
            boolean flagblock = false;
            fileInputStream1 = new FileInputStream("users.bin");
            objectInputStream1 = new ObjectInputStream(fileInputStream1);
            users = (Users) objectInputStream1.readObject();
            User user = new User();
            if (pr.getUsersId()[0] == userid) {
                for (User u : users.getUsers()) {
                    if (u.getId() == pr.getUsersId()[1]) {
                        user.setBlocked(u.getBlocked());
                    }
                }
            }
            if (pr.getUsersId()[1] == userid) {
                for (User u : users.getUsers()) {
                    if (u.getId() == pr.getUsersId()[0]) {
                        user = u;
                    }
                }
            }
            for (int i : user.getBlocked()) {
                if (i == userid) {
                    flagblock = true;
                }
            }
            if (flagblock) {
                return "You have been blocked by this user";
            }
            ChatMessage chatMessage = new ChatMessage(message, userid, pr.getId());
            ArrayList<ChatMessage> messages = pr.getPrivateChatMessages();
            messages.add(chatMessage);
            pr.setPrivateChatMessages(messages);
            fileOutputStream = new FileOutputStream("privateChats-" + privateChatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(pr);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return "The private chat id is invalid";
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

            }
            catch (IOException e) {
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
                if (fileInputStream1 != null) {
                    fileInputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream1 != null) {
                    objectInputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Your message was sent";
    }

    /**
     * Edit message private chat string.
     *
     * @param userId        the user id
     * @param privateChatId the private chat id
     * @param chatMessageId the chat message id
     * @param chatMessage   the chat message
     * @return the string
     */
// edit a message in chat
    public boolean editMessagePrivateChat(int userId, int privateChatId, int chatMessageId, String chatMessage) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        Boolean flag = false;
        try {
            fileInputStream = new FileInputStream("privateChats-" + privateChatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChat privateChat = (PrivateChat) objectInputStream.readObject();
            ArrayList<ChatMessage> messages = privateChat.getPrivateChatMessages();
            for (ChatMessage ch : messages) {
                if (ch.getChatMessageId() == chatMessageId) {
                    flag = true;
                    if (ch.getSenderId() == userId) {
                        ch.setMessage(chatMessage);
                        flag = true;
                    } else {
                        return false;
                    }
                }
            }
            if (!flag) {
                return false;
            }
            privateChat.setPrivateChatMessages(messages);
            fileOutputStream = new FileOutputStream("privateChats-" + privateChatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(privateChat);
        } catch (Exception e) {
            return false;
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
        }
        return true;
    }

    /**
     * Delete message private chat string.
     *
     * @param userId        the user id
     * @param privateChatId the private chat id
     * @param chatMessageId the chat message id
     * @return the string
     */
// delete a message from chat
    public boolean deleteMessagePrivateChat(int userId, int privateChatId, int chatMessageId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        FileInputStream fileInputStream1 = null;
        ObjectInputStream objectInputStream1 = null;
        FileOutputStream fileOutputStream1 = null;
        ObjectOutputStream objectOutputStream1 = null;
        boolean flag = false;
        try {
            fileInputStream = new FileInputStream("privateChats-" + privateChatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            fileInputStream1 = new FileInputStream("privateChats.bin");
            objectInputStream1 = new ObjectInputStream(fileInputStream1);
            PrivateChat privateChat = (PrivateChat) objectInputStream.readObject();
            ArrayList<ChatMessage> messages = privateChat.getPrivateChatMessages();
            PrivateChats privateChats = (PrivateChats) objectInputStream1.readObject();
            ArrayList<PrivateChat> allPrivateChats = privateChats.getPrivateChats();
            for (ChatMessage ch : messages) {
                if (ch.getChatMessageId() == chatMessageId) {
                    flag = true;
                    if (ch.getSenderId() == userId) {
                        messages.remove(ch);
                    } else {
                        return false;
                    }
                }
            }
            for(PrivateChat privateChat1 : allPrivateChats){
                if (privateChat1.getId() == privateChatId){
                    privateChat1.setPrivateChatMessages(messages);
                    break;
                }
            }
            privateChats.setPrivateChats(allPrivateChats);
            if (!flag) {
                return false;
            }
            privateChat.setPrivateChatMessages(messages);
            fileOutputStream = new FileOutputStream("privateChats-" + privateChatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(privateChat);
            fileOutputStream1 = new FileOutputStream("privateChats.bin");
            objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
            objectOutputStream1.writeObject(privateChats);
        } catch (Exception e) {
            return false;
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
        }
        return true;
    }

    /**
     * Show messages private chat array list.
     *
     * @param userId        the user id
     * @param privateChatId the private chat id
     * @return the array list
     */
// show messages from a privateChat
    public ArrayList<ChatMessage> showMessagesPrivateChat(int userId, int privateChatId) {
        ArrayList<ChatMessage> invlaidId = new ArrayList<ChatMessage>();
        invlaidId.add(new ChatMessage("Invalid Id"));
        ArrayList<ChatMessage> notBelog = new ArrayList<ChatMessage>();
        notBelog.add(new ChatMessage("This chat does not belong to you"));
        ArrayList<ChatMessage> nullll = new ArrayList<ChatMessage>();
        nullll.add(new ChatMessage("!!!!!!!!!!!!!!!!!!"));
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("privateChats-" + privateChatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChat privateChat = (PrivateChat) objectInputStream.readObject();
            if (privateChat.getUsersId()[0] != userId && privateChat.getUsersId()[1] != userId) {
                return notBelog;
            }
            ArrayList<ChatMessage> show = privateChat.getPrivateChatMessages();
            return show;
        } catch (Exception e) {
            return invlaidId;
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
        }
    }

    /**
     * Create voice message string.
     *
     * @param userId        the user id
     * @param servergroupId the servergroup id
     * @param name          the name
     * @return the string
     */
// create a voice chat
    public boolean createVoiceMessage(int userId, int servergroupId, String name) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream1 = null;
        ObjectInputStream objectInputStream1 = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream1 = null;
        ObjectOutputStream objectOutputStream1 = null;
        FileOutputStream fileOutputStream2 = null;
        ObjectOutputStream objectOutputStream2 = null;
        int id = 0;
        try {
            fileInputStream = new FileInputStream("chats.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            fileInputStream1 = new FileInputStream("groupServer-" + servergroupId + ".bin");
            objectInputStream1 = new ObjectInputStream(fileInputStream1);
            GroupServer groupServer = (GroupServer) objectInputStream1.readObject();
            ArrayList<Integer> admins = groupServer.getAdmins();
            boolean adminORNo = groupServer.getOwner() == userId;
            for (int i : admins) {
                if (i == userId) {
                    adminORNo = true;
                }
            }
            if (!adminORNo) {
                return false;
            }
            Chats c = (Chats) objectInputStream.readObject();
            ArrayList<Chat> chats = c.getChats();
            VoiceMessages voicemessages = new VoiceMessages(userId, servergroupId, name);
            id = voicemessages.getId();
            chats.add(voicemessages);
            c.setChats(chats);
            fileOutputStream = new FileOutputStream("chats.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(c);
            ArrayList<Integer> chatsGroup = groupServer.getChats();
            chatsGroup.add(voicemessages.getId());
            groupServer.setChats(chatsGroup);
            ArrayList<Integer> users = voicemessages.getUsersId();
            users.add(userId);
            voicemessages.setUsersId(users);
            fileOutputStream1 = new FileOutputStream("VoiceChat-" + voicemessages.getId() + ".bin");
            objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
            objectOutputStream1.writeObject(voicemessages);
            fileOutputStream2 = new FileOutputStream("groupServer-" + servergroupId + ".bin");
            objectOutputStream2 = new ObjectOutputStream(fileOutputStream2);
            objectOutputStream2.writeObject(groupServer);
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
                if (fileInputStream1 != null) {
                    fileInputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream1 != null) {
                    objectInputStream1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream1 != null) {
                    fileOutputStream1.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectOutputStream1 != null) {
                    objectOutputStream1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectOutputStream2 != null) {
                    objectOutputStream2.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Connect voice chat string.
     *
     * @param userId the user id
     * @param chatId the chat id
     * @return the string
     */
// connect to a voice chat
    public String connectVoiceChat(int userId, int chatId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("voicechat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            VoiceMessages voicemessages = (VoiceMessages) objectInputStream.readObject();
            boolean flag = false;
            for (int i : voicemessages.getUsersId()) {
                if (i == chatId) {
                    flag = true;
                }
            }
            if (!flag) {
                return "You are not in this voice chat";
            }
            flag = false;
            for (int i : voicemessages.getAdmins()) {
                if (i == userId) {
                    flag = true;
                }
            }
            if (flag) {
                return "You were connected until now";
            }

            ArrayList<Integer> admins = voicemessages.getAdmins();
            admins.add(userId);
            voicemessages.setAdmins(admins);
            fileOutputStream = new FileOutputStream("voicechat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(voicemessages);
        } catch (Exception e) {
            return "The id is invalid";
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

        }
        return "You are connected";
    }

    /**
     * Left voice messages string.
     *
     * @param userId the user id
     * @param chatId the chat id
     * @return the string
     */
// left from a voice chat
    public String leftVoiceMessages(int userId, int chatId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("voicechat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            VoiceMessages voicemessages = (VoiceMessages) objectInputStream.readObject();
            boolean flag = false;
            for (int i : voicemessages.getUsersId()) {
                if (i == chatId) {
                    flag = true;
                }
            }
            if (!flag) {
                return "You are not in voice chat";
            }
            flag = false;
            for (int i : voicemessages.getAdmins()) {
                if (i == userId) {
                    flag = true;
                }
            }
            if (!flag) {
                return "You are not connected until now";
            }
            ArrayList<Integer> admins = voicemessages.getAdmins();
            admins.remove(admins.indexOf(userId));
            voicemessages.setAdmins(admins);
            fileOutputStream = new FileOutputStream("voicechat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(voicemessages);
        } catch (Exception e) {
            return "The id is invalid";
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
        }
        return "You have left the voice chat";
    }

    /**
     * Show user chat array list.
     *
     * @param userId the user id
     * @param chatId the chat id
     * @return the array list
     * @throws Exception the exception
     */
// show users of a chat
    public ArrayList<Integer> showUserChat(int userId, int chatId) throws Exception {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        ChatMessages chatMessage;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            chatMessage = (ChatMessages) objectInputStream.readObject();
            if (!userInChat(userId, chatId)) {
                throw new Exception("You aren't allowed to see this chat");
            }
        } catch (Exception e) {
            throw new Exception("The id is invalid");
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
        }
        return chatMessage.getUsersId();
    }

    /**
     * Show online voice chat user array list.
     *
     * @param userId the user id
     * @param chatId the chat id
     * @return the array list
     * @throws Exception the exception
     */
// show online users in a voicechat
    public ArrayList<Integer> showOnlineVoiceChatUser(int userId, int chatId) throws Exception {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        VoiceMessages voiceMessage;
        try {
            fileInputStream = new FileInputStream("voicechat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            voiceMessage = (VoiceMessages) objectInputStream.readObject();
            if (!userInChat(userId, chatId)) {
                throw new Exception("You aren't allowed to see this chat");
            }
        } catch (Exception e) {
            throw new Exception("The id is invalid");
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return voiceMessage.getAdmins();
    }

    /**
     * Show chat array list.
     *
     * @param userId the user id
     * @return the array list
     */
// show the chats of user
    public ArrayList<Chat> showChat(int userId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        ArrayList<Chat> chatList = new ArrayList<Chat>();
        try {
            fileInputStream = new FileInputStream("chats.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            Chats c = (Chats) objectInputStream.readObject();
            ArrayList<Chat> chats = c.getChats();
            for (Chat ch : chats) {
                if (userInChat(userId, ch.getId())) {
                    chatList.add(ch);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return chatList;
    }

    /**
     * Show admin chat array list.
     *
     * @param userId the user id
     * @param chatId the chat id
     * @return the array list
     * @throws Exception the exception
     */
// for voice and chatMessages
    public ArrayList<Integer> showAdminChat(int userId, int chatId) throws Exception {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        ChatMessages chatMessage;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            chatMessage = (ChatMessages) objectInputStream.readObject();
            if (!userInChat(userId, chatId)) {
                throw new Exception("You aren't allowed to see this chat");
            }
        } catch (Exception e) {
            throw new Exception("The id is invalid");
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return chatMessage.getAdmins();
    }

    /**
     * Reaction message private chat string.
     *
     * @param myId      the my id
     * @param chatId    the chat id
     * @param messageId the message id
     * @param reaction  the reaction
     * @return the string
     */
// reaction to a message in pv
    public boolean reactionMessagePrivateChat(int myId, int chatId, int messageId, String reaction) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("privateChats-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChat pv = (PrivateChat) objectInputStream.readObject();
            if (pv.getUsersId()[0] != myId && pv.getUsersId()[1] != myId) {
                return false;
            }
            ArrayList<ChatMessage> messages = pv.getPrivateChatMessages();
            boolean flag = false;
            boolean flagReaction = false;
            if(Objects.equals(reaction, "heart")){
                flagReaction = true;
                for (ChatMessage message : messages) {
                    if (message.getChatMessageId() == messageId) {
                        message.setHearts(myId);
                        flag = true;
                    }
                }
            }
            if(Objects.equals(reaction, "like")){
                flagReaction = true;
                for (ChatMessage message : messages) {
                    if (message.getChatMessageId() == messageId) {
                        message.setLikes(myId);
                        flag = true;
                    }
                }
            }
            if(Objects.equals(reaction, "dislike")){
                flagReaction = true;
                for (ChatMessage message : messages) {
                    if (message.getChatMessageId() == messageId) {
                        message.setDislikes(myId);
                        flag = true;
                    }
                }
            }
            if(!flagReaction){
                return false;
            }
            if (!flag) {
                return false;
            }
            pv.setPrivateChatMessages(messages);
            fileOutputStream = new FileOutputStream("privateChats-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(pv);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
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
        }

        return true;
    }

    /**
     * Show private chats.
     *
     * @param userId the user id
     * @return the array list
     */
// show privateChat for a user
    public ArrayList<PrivateChat> checkPrivateChats(int userId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("privateChats.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChats privateChats = (PrivateChats) objectInputStream.readObject();
            ArrayList<PrivateChat> pvs = privateChats.getPrivateChats();
            ArrayList<PrivateChat> myPvs = new ArrayList<>();
            for (PrivateChat pv : pvs) {
                if (pv.getUsersId()[0] == userId || pv.getUsersId()[1] == userId) {
                    myPvs.add(pv);
                }
            }
            return myPvs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Return chat chat.
     *
     * @param chatId the chat id
     * @return the chat
     */
    public Chat returnChat(int chatId){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("chats.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            Chats chats = (Chats) objectInputStream.readObject();
            ArrayList<Chat> chatsList = chats.getChats();
            for(Chat chat : chatsList){
                if(chat.getId() == chatId){
                    return chat;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Check servers array list.
     *
     * @param userId the user id
     * @return the array list
     */
    public ArrayList<GroupServer> checkServers(int userId){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("groupservers.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            GroupServers groupServers = (GroupServers) objectInputStream.readObject();
            ArrayList<GroupServer> allGroupServers = groupServers.getGroupServers();
            ArrayList<GroupServer> myGroupServers = new ArrayList<>();
            for (GroupServer groupServer : allGroupServers) {
                for(Integer id : groupServer.getAdmins()){
                    if(id == userId){
                        myGroupServers.add(groupServer);
                        break;
                    }
                }
            }
            int size;
            for (GroupServer groupServer : allGroupServers) {
                size = myGroupServers.size();
                ArrayList<Chat> groupServerChats = new ArrayList<>();
                for(Integer chatId : groupServer.getChats()) {
                    groupServerChats.add(returnChat(chatId));
                }
                for(Chat chat : groupServerChats){
                    for(Integer id : chat.getUsersId()){
                        if(id == userId){
                            myGroupServers.add(groupServer);
                            break;
                        }
                    }
                    if(size != myGroupServers.size()){
                        break;
                    }
                    for(Integer id : chat.getAdmins()){
                        if(id == userId){
                            myGroupServers.add(groupServer);
                            break;
                        }
                    }
                    if(size != myGroupServers.size()){
                        break;
                    }
                }
            }
            return myGroupServers;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Check chats array list.
     *
     * @param serverId the server id
     * @return the array list
     */
    public ArrayList<Chat> checkChats(int serverId){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream("chats.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            Chats chats = (Chats) objectInputStream.readObject();
            ArrayList<Chat> allChats = chats.getChats();
            ArrayList<Chat> myChats = new ArrayList<>();
            for (Chat chat : allChats) {
                if (chat.getServergroupId() == serverId) {
                    myChats.add(chat);
                }
            }
            return myChats;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Add user voice chat string.
     *
     * @param userId the user id
     * @param target the target
     * @param chatId the chat id
     * @return the string
     */
//add a user to voice chat
    public String addUserVoiceChat(int userId , int target , int chatId){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        boolean flag = false;
        try {
            fileInputStream = new FileInputStream("VoiceChat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            VoiceMessages voicemessages = (VoiceMessages) objectInputStream.readObject();
            ArrayList<Integer> users = voicemessages.getUsersId();
            for(int i : users) {
                if (i == target) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                return "";
            }
            users.add(target);
            voicemessages.setUsersId(users);
            fileOutputStream = new FileOutputStream("VoiceChat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(voicemessages);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
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
        }
        return "The user was added to voice chat";
    }

}
