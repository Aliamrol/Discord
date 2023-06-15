package com.example.calculator;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;

/**
 * The type User.
 */
public class User implements Serializable {
    private int id;

    private String username;

    private String password;

    private String Email;

    private String phoneNumber;

    private String pictureAddress;

    private String state;

    private HashSet<Integer> friendsId;

    private HashSet<Integer> blocked;

    private HashSet<Integer> friendRequests;

    /**
     * Instantiates a new User.
     */
    public User(){
        friendsId = new HashSet<>();
        blocked = new HashSet<>();
        friendRequests = new HashSet<>();
        pictureAddress = "C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\discordUserLogo.png";
        state = "Online";
    }

    /**
     * Check user name boolean.
     *
     * @param username the username
     * @return the boolean
     * @throws IOException the io exception
     */
    public int checkUserName(String username) throws IOException{
        if(username == null || username.equals("")) {
            return 0;
        }
        else {
            if(username.length() < 6) {
                return 1;
            }
            else {
                for (int i = 0; i < username.length(); i++) {
                    if (!(Character.isDigit(username.charAt(i)) || (username.charAt(i) >= 'A' && username.charAt(i) <= 'Z') || (username.charAt(i) >= 'a' && username.charAt(i) <= 'z'))) {
                        return 2;
                    }
                }
                return 3;
            }
        }
    }

    /**
     * Sets username.
     *
     * @param username the username
     * @return the username
     */
    public int setUsername(String username) {
        try {
            if(checkUserName(username) == 3){
                this.username = username;
                return 3;
            }
            else {
                if(checkUserName(username) == 0){
                    return 0;
                }
                else if(checkUserName(username) == 1){
                    return 1;
                }
                else if(checkUserName(username) == 2){
                    return 2;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Check password boolean.
     *
     * @param password the password
     * @return the boolean
     * @throws IOException the io exception
     */
    public int checkPassword(String password) throws IOException{
        if(password == null || password.equals("")){
            return 0;
        }
        else {
            if(password.length() < 8) {
                return 1;
            }
            else {
                int i;
                for (i = 0; i < password.length(); i++){
                    if(Character.isDigit(password.charAt(i))){
                        break;
                    }
                }
                if(i == password.length()){
                    return 2;
                }
                for (i = 0; i < password.length(); i++){
                    if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z'){
                        break;
                    }
                }
                if(i == password.length()){
                    return 3;
                }
                for (i = 0; i < password.length(); i++){
                    if(password.charAt(i) >= 'a' && password.charAt(i) <= 'z'){
                        break;
                    }
                }
                if(i == password.length()){
                    return 4;
                }
                return 5;
            }
        }
    }

    /**
     * Set password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public int setPassword(String password){
        try{
            if(checkPassword(password) == 5){
                this.password = password;
                return 5;
            }
            else {
                if(checkPassword(password) == 0){
                    return 0;
                }
                else if(checkPassword(password) == 1){
                    return 1;
                }
                else if(checkPassword(password) == 2){
                    return 2;
                }
                else if(checkPassword(password) == 3){
                    return 3;
                }
                else if(checkPassword(password) == 4){
                    return 4;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Check email boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws IOException the io exception
     */
    public int checkEmail(String email) throws IOException{
        if(email == null || email.equals("")) {
            return 0;
        }
        else {
            if(email.contains("@gmail.com")){
                if(email.length() == 10){
                    return 1;
                }
            }
            else if(email.contains("@hotmail.com")){
                if(email.length() == 12){
                    return 1;
                }
            }
            else if(email.contains("@yahoo.com")){
                if(email.length() == 10){
                    return 1;
                }
            }
            else if(email.contains("@aol.com")){
                if(email.length() == 8){
                    return 1;
                }
            }
            else {
                return 2;
            }
            return 3;
        }
    }

    /**
     * Set email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public int setEmail(String email){
        try{
            if(checkEmail(email) == 3){
                this.Email = email;
                return 3;
            }
            else {
                if(checkEmail(email) == 0){
                    return 0;
                }
                else if(checkEmail(email) == 1){
                    return 1;
                }
                else if(checkEmail(email) == 2){
                    return 2;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Check phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     * @throws IOException the io exception
     */
    public boolean checkPhoneNumber(String phoneNumber) throws IOException{
        if(phoneNumber.equals("")) {
            return true;
        }
        else {
            if(phoneNumber.charAt(0) != '+'){
                return false;
            }
            else {
                if(phoneNumber.length() < 12){
                    return false;
                }
                else {
                    int n = phoneNumber.length();
                    if (n - 10 >= 5) {
                        return false;
                    }
                    else {
                        int prefix = Integer.parseInt(phoneNumber, 1, n - 10, 10);
                        return prefix <= 195;
                    }
                }
            }
        }
    }

    /**
     * Set phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public boolean setPhoneNumber(String phoneNumber){
        try{
            if(checkPhoneNumber(phoneNumber)){
                this.phoneNumber = phoneNumber;
                return true;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Sets picture address.
     *
     * @param pictureAddress the picture address
     */
    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }

    /**
     * Sets state.
     *
     * @param state the state
     * @return the state
     */
    public boolean setState(String state) {
        if(state.equals("Online") || state.equals("Idle") || state.equals("Do Not Disturb") || state.equals("Invisible")) {
            this.state = state;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return username + " id: " + id;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets picture address.
     *
     * @return the picture address
     */
    public String getPictureAddress() {
        return pictureAddress;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets friends id.
     *
     * @return the friends id
     */
    public HashSet<Integer> getFriendsId() {
        return friendsId;
    }

    /**
     * Sets friends id.
     *
     * @param friendsId the friends id
     */
    public void setFriendsId(HashSet<Integer> friendsId) {
        this.friendsId = friendsId;
    }

    /**
     * Gets blocked.
     *
     * @return the blocked
     */
    public HashSet<Integer> getBlocked() {
        return blocked;
    }

    /**
     * Sets blocked.
     *
     * @param blocked the blocked
     */
    public void setBlocked(HashSet<Integer> blocked) {
        this.blocked = blocked;
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
     * Gets friend requests.
     *
     * @return the friend requests
     */
    public HashSet<Integer> getFriendRequests() {
        return friendRequests;
    }

    /**
     * Sets friend requests.
     *
     * @param friendRequests the friend requests
     */
    public void setFriendRequests(HashSet<Integer> friendRequests) {
        this.friendRequests = friendRequests;
    }
}
