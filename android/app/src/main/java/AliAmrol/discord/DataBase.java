package AliAmrol.discord;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
                        user.setPhoneNumber(u.getPhoneNumber());
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
            System.out.println("You have successfully signed in");
            return true;
        } else {
            System.out.println("Incorrect username or password");
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
            for (User u : users.getUsers()) {
                if (u.getUsername().equals(user.getUsername())) {
                    return false;
                }
            }
            if (users.getUsers().size() == 0) {
                user.setId((1));
            } else {
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
            System.out.println("Your information updated");
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
                for (User u : users.getUsers()) {
                    if (u.getId() == receiverId) {
                        HashSet<Integer> newFriendsRequests = u.getFriendRequests();
                        newFriendsRequests.add(senderId);
                        u.setFriendRequests(newFriendsRequests);
                        break;
                    }
                }
                fileOutputStream = new FileOutputStream("Users.bin");
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(users);
                return true;
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
                        string.append(u.getUsername()).append(": ").append(u.getState()).append("\n");
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
                        string.append(u.getUsername()).append("\n");
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
                        string.append(u.getUsername()).append("\n");
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
                        string.append(u.getUsername()).append(" wants to be friend\n");
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
    public String sendMessageToChat(int userId, int chatId, String message) {
        if (!userInChat(userId, chatId)) {
            return "You are not in this chat group";
        }
        if (!userAdminChat(userId, chatId)) {
            return "You are not allowed to send message to this chat";
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
        return "Your message was sent";
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
    public String editMessageChat(int userId, int chatId, int chatMessageId, String chatMessage) {
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
                    return "This message doesn't belong to you";
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
        return "Your message has been edited";
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
    public String reactionMessageChat(int userId, int chatId, int chatMessageId, String reaction) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ChatMessages chat = (ChatMessages) objectInputStream.readObject();
            if (!userInChat(userId, chatId)) {
                return "You are not allowed to react in this chat";
            }
            if (!reaction.equals("heart") && !reaction.equals("like") && !reaction.equals("dislike")) {
                return "The reaction is invalid";
            }
            boolean flag = false;
            ArrayList<ChatMessage> messages = chat.getChatMessages();

            for (ChatMessage ch : messages) {
                if (ch.getChatMessageId() == chatMessageId) {
                    HashSet<Integer> likes = ch.getLikes();
                    HashSet<Integer> dislikes = ch.getDislikes();
                    HashSet<Integer> hearts = ch.getHearts();
                    likes.remove(userId);
                    dislikes.remove(userId);
                    hearts.remove(userId);
                    ch.setLikes(likes);
                    ch.setDislikes(dislikes);
                    ch.setHearts(hearts);
                }
            }
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
                return "The message id is invalid";
            }

            chat.setChatMessages(messages);
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
        return "The reaction was sent";

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
    public String pinMessageChat(int userId, int chatId, int chatmessageId) {
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
                return "You are not admin and can't pin message";
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
            return "The chat id is invalid";
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
        return "Message pinned";
    }




    public String unPinMessageChat(int userId, int chatId) {
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
                return "You are not admin and can't pin message";
            }
            chatMessages.setPinMessage(null);
            fileOutputStream = new FileOutputStream("chat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chatMessages);

        } catch (Exception e) {
            return "The chat id is invalid";
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
        return "Message unpinned";
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
    public String deleteMessageChat(int userId, int chatId, int chatMessageId) {
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
                    return "This message doesn't belong to you";
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
            return "The chat id is invalid";
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
        return "The message was deleted";
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
    public String createChatMessages(int userId, int servergroupId, String nameChat) {
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
                return "You aren't allowed to create chat in this server";
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

        return "Chat successfully created: chat id is " + id;
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
    public String addUserChat(int userId, int memeber, int chatId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("chat-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            ChatMessages chat = (ChatMessages) objectInputStream.readObject();
            if (!userAdminChat(userId, chatId)) {
                return "You are not allowed to add user to this chat";
            }

            ArrayList<Integer> users = chat.getUsersId();
            users.add(memeber);
            chat.setUsersId(users);
            fileOutputStream = new FileOutputStream("chat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chat);
        } catch (Exception e) {
            return "The chat id is invalid";
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
        return "The user successfully added to chat";
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
    public String addAdminChat(int userId, int admin, int chatId) {
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
            admins.add(admin);
            chat.setAdmins(admins);
            ArrayList<Integer> users = chat.getUsersId();
            Boolean flag = false;
            for (Integer user : users) {
                if (user == admin) {
                    flag = true;
                }
            }
            if (!flag) {
                users.add(admin);
            }
            chat.setUsersId(users);
            fileOutputStream = new FileOutputStream("chat-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chat);
        } catch (Exception e) {
            return "The chat id is invalid";
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
        return "The user successfully became the admin of chat " + chatId;
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
    public String addAdminGroupServer(int userId, int admin, int groupServerId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("groupServer-" + groupServerId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            GroupServer groupServer = (GroupServer) objectInputStream.readObject();
            if (groupServer.getOwner() != userId) {
                return "You are not owner of the group server";
            }
            ArrayList<Integer> admins = groupServer.getAdmins();
            admins.add(admin);
            groupServer.setAdmins(admins);
            fileOutputStream = new FileOutputStream("groupServer-" + groupServerId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(groupServer);
        } catch (Exception e) {
            e.printStackTrace();
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
    public String createServerGroup(int userId, String name) {
        GroupServer group = new GroupServer(userId, name);
        return "Server successfully created: the id is: " + group.getId();
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
    public String pinMessagePrivateChat(int userId, int privateChatId, int chatmessageId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("privateChats-" + privateChatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChat privateChat = (PrivateChat) objectInputStream.readObject();
            if (privateChat.getUsersId()[0] != userId && privateChat.getUsersId()[1] != userId) {
                return "This private chat doesn't belong to you";
            }
            boolean flag = false;
            for (ChatMessage m : privateChat.getPrivateChatMessages()) {
                if (m.getChatMessageId() == chatmessageId) {
                    privateChat.setPin(m);
                    flag = true;
                }
            }
            if (!flag) {
                return "The chat message id is invalid";
            }
            fileOutputStream = new FileOutputStream("privateChats-" + privateChatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(privateChat);
        } catch (Exception e) {
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
        return "Message pinned";
    }

    /**
     * Creat private chat string.
     *
     * @param user1 the user 1
     * @param user2 the user 2
     * @return the string
     */
// create a privatechat
    public String creatPrivateChat(int user1, int user2) {
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
                return "You have private chat with this user and the id of privatchat is " + pr.getId();
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
        return "The chat has been made and the id of private chat is: " + id;
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
    public String editMessagePrivateChat(int userId, int privateChatId, int chatMessageId, String chatMessage) {
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
                        return "This message doesn't belong to you";
                    }
                }
            }
            if (!flag) {
                return "Message id is invalid";
            }
            privateChat.setPrivateChatMessages(messages);
            fileOutputStream = new FileOutputStream("privateChats-" + privateChatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(privateChat);
        } catch (Exception e) {
            return "The chat id is invalid";
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
        return "Your message has been edited";
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
    public String deleteMessagePrivateChat(int userId, int privateChatId, int chatMessageId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        boolean flag = false;
        try {
            fileInputStream = new FileInputStream("privateChats-" + privateChatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChat privateChat = (PrivateChat) objectInputStream.readObject();
            ArrayList<ChatMessage> messages = privateChat.getPrivateChatMessages();
            for (ChatMessage ch : messages) {
                if (ch.getChatMessageId() == chatMessageId) {
                    flag = true;
                    if (ch.getSenderId() == userId) {
                        messages.remove(ch);
                    } else {
                        return "This message doesn't belong to you";
                    }
                }
            }
            if (!flag) {
                return "Message id is invalid";
            }
            privateChat.setPrivateChatMessages(messages);
            fileOutputStream = new FileOutputStream("privateChats-" + privateChatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(privateChat);
        } catch (Exception e) {
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
        if (flag) {
            return "Message was deleted";
        }
        return "not successfully";
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
        notBelog.add(new ChatMessage("This chat is not belong to you"));
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
            if (privateChat.getPin() != null) {
                show.add(new ChatMessage( "The pin message is :"));
                show.add(privateChat.getPin());
            }
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
    public String createVoiceMessage(int userId, int servergroupId, String name) {
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
            Boolean adminORNo = false;
            if (groupServer.getOwner() == userId) {
                adminORNo = true;
            }
            for (int i : admins) {
                if (i == userId) {
                    adminORNo = true;
                }
            }
            if (!adminORNo) {
                return "You aren't allowed to create voice chat in this server";
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
        return "the voice chat successfully created and the id is " + id;
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
    public String reactionMessagePrivateChat(int myId, int chatId, int messageId, String reaction) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("privateChats-" + chatId + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChat pv = (PrivateChat) objectInputStream.readObject();
            if (pv.getUsersId()[0] != myId && pv.getUsersId()[1] != myId) {
                return "You are not in this private chat";
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
                return "The reaction is invalid";
            }
            if (!flag) {
                return "This message id is invalid";
            }
            pv.setPrivateChatMessages(messages);
            fileOutputStream = new FileOutputStream("privateChats-" + chatId + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(pv);
        } catch (Exception e) {
            e.printStackTrace();
            return "The private chat id is invalid";
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

        return "Reaction sent";
    }

    /**
     * Show private chats.
     *
     * @param userId the user id
     */
// show privateChat for a user
    public ArrayList<PrivateChat> showPrivateChats(int userId) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        ArrayList<PrivateChat> pvbsck = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream("privateChats.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            PrivateChats privateChats = (PrivateChats) objectInputStream.readObject();
            ArrayList<PrivateChat> pvs = privateChats.getPrivateChats();
//            boolean flag = false;
            User user = new User();
            for (PrivateChat pv : pvs) {
                if (pv.getUsersId()[0] == userId || pv.getUsersId()[1] == userId) {
//                    flag = true;
                    if (pv.getUsersId()[0] == userId) {
                        user.setUsername(getUser(pv.getUsersId()[1]).getUsername());
                    } else {
                        user.setUsername(getUser(pv.getUsersId()[0]).getUsername());
                    }
//                    System.out.println("Private chat between you and " + user.getUsername()
//                            + " that the id is " + pv.getId());
                    pvbsck.add(pv);
                }
            }
//            if (!flag) {
//                System.out.println("You don't have any private chat");
//            }
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
        return pvbsck;
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
