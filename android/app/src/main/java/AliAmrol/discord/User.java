package AliAmrol.discord;



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

    private String state = "ONLINE";

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
        state = "Online";
    }

    /**
     * Check user name boolean.
     *
     * @param username the username
     * @return the boolean
     * @throws IOException the io exception
     */
    public boolean checkUserName(String username) throws IOException{
        if(username == null){
            throw new IOException("Please choose an username!");
        }
        else {
            if(username.length() < 6) {
                System.out.println("please choose an username longer than 6 characters!");
                return false;
            }
            else {
                for (int i = 0; i < username.length(); i++) {
                    if (!(Character.isDigit(username.charAt(i)) || (username.charAt(i) >= 'A' && username.charAt(i) <= 'Z') || (username.charAt(i) >= 'a' && username.charAt(i) <= 'z'))) {
                        System.out.println("Please just choose numeric characters or english letters!");
                        return false;
                    }
                }
                return true;
            }
        }
    }

    /**
     * Sets username.
     *
     * @param username the username
     * @return the username
     */
    public boolean setUsername(String username) {
        try {
            if(checkUserName(username)){
                this.username = username;
                return true;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Check password boolean.
     *
     * @param password the password
     * @return the boolean
     * @throws IOException the io exception
     */
    public boolean checkPassword(String password) throws IOException{
        if(password == null){
            throw new IOException("Please choose a password.");
        }
        else {
            if(password.length() < 8) {
                System.out.println("Please choose a password longer than 8 characters!");
                return false;
            }
            else {
                int i;
                for (i = 0; i < password.length(); i++){
                    if(Character.isDigit(password.charAt(i))){
                        break;
                    }
                }
                if(i == password.length()){
                    System.out.println("Your password should have at least one digit!");
                    return false;
                }
                for (i = 0; i < password.length(); i++){
                    if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z'){
                        break;
                    }
                }
                if(i == password.length()){
                    System.out.println("Your password should have at least one capital letter!");
                    return false;
                }
                for (i = 0; i < password.length(); i++){
                    if(password.charAt(i) >= 'a' && password.charAt(i) <= 'z'){
                        break;
                    }
                }
                if(i == password.length()){
                    System.out.println("Your password should have at least one small letter!");
                    return false;
                }
                return true;
            }
        }
    }

    /**
     * Set password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public boolean setPassword(String password){
        try{
            if(checkPassword(password)){
                this.password = password;
                return true;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Check email boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws IOException the io exception
     */
    public boolean checkEmail(String email) throws IOException{
        if(email == null) {
            throw new IOException("Please choose an Email address!");
        }
        else {
            if(email.contains("@gmail.com")){
                if(email.length() == 10){
                    System.out.println("Your Email address should have an username!");
                    return false;
                }
            }
            else if(email.contains("@hotmail.com")){
                if(email.length() == 12){
                    System.out.println("Your Email address should have an username!");
                    return false;
                }
            }
            else if(email.contains("@yahoo.com")){
                if(email.length() == 10){
                    System.out.println("Your Email address should have an username!");
                    return false;
                }
            }
            else if(email.contains("@aol.com")){
                if(email.length() == 8){
                    System.out.println("Your Email address should have an username!");
                    return false;
                }
            }
            else {
                System.out.println("Your Email address' domain is invalid!");
                return false;
            }
            return true;
        }
    }

    /**
     * Set email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean setEmail(String email){
        try{
            if(checkEmail(email)){
                this.Email = email;
                return true;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
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
            this.phoneNumber = "Not Linked";
            return true;
        }
        else {
            if(phoneNumber.charAt(0) != '+'){
                System.out.println("Your phone number has to start with +");
                return false;
            }
            else {
                if(phoneNumber.length() < 12){
                    System.out.println("Your phone number should have at least 12 digits!");
                    return false;
                }
                else {
                    int n = phoneNumber.length();
                    if (n - 10 >= 5) {
                        System.out.println("Phone number prefix should have at last 3 digits!");
                        return false;
                    }
                    else {
                        int prefix = Integer.parseInt(phoneNumber.substring(1, n -10));
                        if (prefix > 195) {
                            System.out.println("Your phone number prefix should be smaller than 195!");
                            return false;
                        }
                    }
                }
                return true;
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
     * Creat user boolean.
     *
     * @param username the username
     * @param password the password
     * @param email    the email
     * @param user     the user
     * @return the boolean
     */
    public boolean creatUser(String username, String password, String email, User user){
        user = new User();
        if(!user.setUsername(username)){
            return false;
        }
        else if(!user.setPassword(password)){
            return false;
        }
        else return user.setEmail(email);
    }

    /**
     * Creat user boolean.
     *
     * @param username    the username
     * @param password    the password
     * @param email       the email
     * @param phoneNumber the phone number
     * @param user        the user
     * @return the boolean
     */
    public boolean creatUser(String username, String password, String email, String phoneNumber, User user){
        if(!user.setUsername(username)){
            return false;
        }
        else if(!user.setPassword(password)){
            return false;
        }
        else if(!user.setEmail(email)){
            return false;
        }
        else return user.setPhoneNumber(phoneNumber);
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

