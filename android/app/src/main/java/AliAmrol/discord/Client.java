package AliAmrol.discord;



import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


/**
 * The type Client.
 */
public class Client {
    private Socket socket;

    private User user = new User();

    private DataBase dataBase = new DataBase();

    private String order;

    private int choose;

    private ObjectOutputStream outputStream;

    private ObjectInputStream inputStream;

    /**
     * Instantiates a new Client.
     */

    public Client(Context context) {
        try {
            socket = new Socket("192.168.156.158", 8000);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            //Toast.makeText(context , "clinet succes" , Toast.LENGTH_LONG).show();
            System.out.println("Hello");
        } catch (IOException e) {
            //Toast.makeText(context , e.getMessage() , Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /**
     * First menu int.
     *
     * @return the int
     */
    public int firstMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("1.sign in");
        System.out.println("2.sign up");
        System.out.println("3.exit");
        choose = input.nextInt();
        input.nextLine();
        if(choose == 1){
            String username;
            String password;
            while(true){
                System.out.println("Please enter your username");
                username = input.nextLine();
                System.out.println("Please enter your password");
                password = input.nextLine();
                if(dataBase.checkLogin(username, password, user)) {
                    break;
                }
            }
            return dataBase.returnIdAfterLogin(username, password);
        }
        else if(choose == 2){
            while(true) {
                System.out.println("Please choose an username");
                while (true) {
                    if (user.setUsername(input.nextLine())) {
                        break;
                    }
                }
                System.out.println("Please choose a password");
                while (true) {
                    if (user.setPassword(input.nextLine())) {
                        break;
                    }
                }
                System.out.println("Please choose an email");
                while (true) {
                    if (user.setEmail(input.nextLine())) {
                        break;
                    }
                }
                System.out.println("Please choose an phone number(optional: press enter to pass)");
                String string;
                while (true) {
                    string = input.nextLine();
                    if (user.setPhoneNumber(string)) {
                        break;
                    } else if (string.equals("")) {
                        break;
                    }
                }
                if(dataBase.checkSignup(user)){
                    break;
                }
            }
            return dataBase.returnIdAfterSignup(user);
        }
        else if(choose == 3){
            System.exit(0);
        }
        return 0;
    }

    /**
     * Second menu.
     *
     * @throws Exception the exception
     */
    public void secondMenu() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter an order");
        order = "";
        if(choose == 1 || choose == 2) {
            while (!(order.toLowerCase(Locale.ROOT).equals("#exit"))) {
                order = input.nextLine();
                outputClientsOrders(order);
                if (order.toLowerCase(Locale.ROOT).equals("#change username")) {
                    System.out.println("Please choose your new username");
                    while (true) {
                        String newUsername = input.nextLine();
                        if (user.setUsername(newUsername)) {
                            break;
                        }
                    }
                    dataBase.updateUser(user);
                    outputClientDataBase();
                    inputClientDataBase();
                }
                else if (order.toLowerCase(Locale.ROOT).equals("#change password")) {
                    while(true) {
                        System.out.println("Please enter your current password.");
                        String password = input.nextLine();
                        if (password.equals(user.getPassword())) {
                            System.out.println("Please choose your new password");
                            while (true) {
                                if (user.setPassword(input.nextLine())) {
                                    break;
                                }
                            }
                            dataBase.updateUser(user);
                            break;
                        }
                        System.out.println("Wrong password");
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                }
                else if (order.toLowerCase(Locale.ROOT).equals("#change email")) {
                    System.out.println("Please choose an email");
                    while (true) {
                        if (user.setEmail(input.nextLine())) {
                            break;
                        }
                    }
                    dataBase.updateUser(user);
                    outputClientDataBase();
                    inputClientDataBase();
                }
                else if (order.toLowerCase(Locale.ROOT).equals("#change phone number")) {
                    System.out.println("Please choose an phone number(optional: press enter to pass)");
                    String string;
                    while (true) {
                        string = input.nextLine();
                        if (user.setPhoneNumber(string)) {
                            break;
                        } else if (string.equals("")) {
                            break;
                        }
                    }
                    dataBase.updateUser(user);
                    outputClientDataBase();
                    inputClientDataBase();
                }
                else if (order.toLowerCase(Locale.ROOT).equals("#change status")) {
                    System.out.println("Please choose one of the status:");
                    System.out.println("Online");
                    System.out.println("Idle");
                    System.out.println("Do Not Disturb");
                    System.out.println("Invisible");
                    while (true) {
                        if (user.setState(input.nextLine())) {
                            break;
                        }
                        System.out.println("Please choose one of the status in list:");
                    }
                    dataBase.updateUser(user);
                    outputClientDataBase();
                    inputClientDataBase();
                }
                else if (order.toLowerCase(Locale.ROOT).equals("#delete account")) {
                    System.out.println("Are you sure you want to delete your account?");
                    System.out.println("1.Yes");
                    System.out.println("2.No");
                    int choose;
                    while (!((choose = input.nextInt()) == 1 || choose == 2)) {
                        System.out.println("Please choose 1 or 2");
                    }
                    if (choose == 1) {
                        dataBase.deleteUser(user);
                        System.exit(0);
                    }
                    outputClientDataBase();
                    inputClientDataBase();
                }
                //#add to friends name
                else if (order.toLowerCase(Locale.ROOT).contains("#add to friends")) {
                    try {
                        String receiverUsername = order.substring(16);
                        Integer receiverId = dataBase.determineId(receiverUsername);
                        if (!dataBase.checkBlockList(user.getId(), receiverId)) {
                            if (receiverId == 0) {
                                System.out.println(receiverUsername + " accounts doesn't exist");
                            } else {
                                if (dataBase.addToFriendRequestList(user.getId(), receiverId)) {
                                    outputClientDataBase();
                                    inputClientDataBase();
                                    System.out.println("Your request was sent");
                                } else {
                                    System.out.println("You can't send request to yourself!");
                                }
                            }
                        } else {
                            System.out.println("Can't send a request to " + receiverUsername + " because you have blocked it before");
                        }
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                else if (order.toLowerCase(Locale.ROOT).equals("#show friend requests")) {
                    String string = dataBase.showFriendRequests(user);
                    System.out.println(string);
                    if(string.length() > 1){
                        System.out.println("Please choose #accept friend request or #decline friend request.(put a username instead of friend)");
                        String order2 = input.nextLine();
                        String username;
                        if(order2.toLowerCase(Locale.ROOT).contains("#accept") && order2.contains("request")){
                            int i = 8;
                            while (order2.charAt(i) != ' '){
                                i++;
                            }
                            username = order2.substring(8, i);
                            int friendId = dataBase.acceptRequest(username, user.getId());
                            if(friendId == 0) {
                                System.out.println(username + " didn't give you friend request");
                            }
                            else {
                                dataBase.addToFriendsList(user.getId(), friendId);
                                dataBase.addToFriendsList(friendId, user.getId());
                                dataBase.clearFriendRequest(user.getId(), friendId);
                                outputClientDataBase();
                                inputClientDataBase();
                                System.out.println(username + " added to your friends list");
                            }
                        }
                        else if(order2.toLowerCase(Locale.ROOT).contains("#decline") && order2.contains("request")){
                            int i = 9;
                            while (order2.charAt(i) != ' '){
                                i++;
                            }
                            username = order2.substring(9, i);
                            int friendId = dataBase.determineId(username);
                            dataBase.clearFriendRequest(user.getId(), friendId);
                            outputClientDataBase();
                            inputClientDataBase();
                        }
                    }
                }
                //#remove from friends name
                else if (order.toLowerCase(Locale.ROOT).contains("#remove from friends")) {
                    try {
                        String username = order.substring(21);
                        int friendId = dataBase.determineId(username);
                        dataBase.removeFromFriendsList(user.getId(), friendId);
                        outputClientDataBase();
                        inputClientDataBase();
                        System.out.println(username + " was removed from friends list");
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                else if (order.toLowerCase(Locale.ROOT).equals("#show all friends")) {
                    System.out.println(dataBase.showUsersAllFriends(user));
                }
                else if (order.toLowerCase(Locale.ROOT).equals("#show online friends")) {
                    System.out.println(dataBase.showUsersOnlineFriends(user));
                }
                //#block name
                else if (order.toLowerCase(Locale.ROOT).contains("#block")){
                    try {
                        String username = order.substring(7);
                        int blockedId = dataBase.determineId(username);
                        dataBase.addToBlockList(user.getId(), blockedId);
                        if (dataBase.checkFriendsList(user.getId(), blockedId)) {
                            dataBase.removeFromFriendsList(user.getId(), blockedId);
                        }
                        outputClientDataBase();
                        inputClientDataBase();
                        System.out.println(username + " was blocked");
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#unblock name
                else if (order.toLowerCase(Locale.ROOT).contains("#unblock")){
                    try {
                        String username = order.substring(9);
                        int blockedId = dataBase.determineId(username);
                        dataBase.removeFromBlockList(user.getId(), blockedId);
                        outputClientDataBase();
                        inputClientDataBase();
                        System.out.println(username + " was removed from your block list");
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                else if (order.toLowerCase(Locale.ROOT).equals("#show blocked users")) {
                    System.out.println(dataBase.showUsersBlocked(user));
                }
                //#create server name
                else if (order.toLowerCase(Locale.ROOT).contains("#create server")){
                    try {
                        String serverName = order.substring(15);
                        System.out.println(dataBase.createServerGroup(user.getId(), serverName));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#create chat name in server n
                else if (order.toLowerCase(Locale.ROOT).contains("#create chat") && order.toLowerCase(Locale.ROOT).contains("in server")){
                    try {
                        int i = 13;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        String chatName = order.substring(13, i);
                        int serverId = Integer.parseInt(order.substring(i + 11, order.length()), 10);
                        System.out.println(dataBase.createChatMessages(user.getId(), serverId, chatName));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //add user (name) to chat m
                else if (order.toLowerCase(Locale.ROOT).contains("#add user")){
                    try {
                        int i = 10;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        String username = order.substring(10, i);
                        int userId = dataBase.determineId(username);
                        int chatId = Integer.parseInt(order.substring(i + 9, order.length()), 10);
                        System.out.println(dataBase.addUserChat(user.getId(), userId, chatId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //add admin name to chat m
                else if (order.toLowerCase(Locale.ROOT).contains("#add admin") && order.toLowerCase(Locale.ROOT).contains("chat")){
                    try {
                        int i = 11;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        String username = order.substring(11, i);
                        int adminId = dataBase.determineId(username);
                        int chatId = Integer.parseInt(order.substring( i + 9, order.length()), 10);
                        System.out.println(dataBase.addAdminChat(user.getId(), adminId, chatId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#show messages of chat n
                else if (order.toLowerCase(Locale.ROOT).contains("#show messages of chat")){
                    try {
                        int chatId = Integer.parseInt(order.substring( 23, order.length()), 10);
                        ArrayList<ChatMessage> messages = dataBase.showMessagesChat(user.getId(), chatId);
                        for (ChatMessage chatMessage : messages) {
                            System.out.println(chatMessage.toString());
                        }
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#send message to chat n: message
                else if (order.toLowerCase(Locale.ROOT).contains("#send message to chat")){
                    try {
                        int i = 22;
                        while (order.charAt(i) != ':') {
                            i++;
                        }
                        int chatId = Integer.parseInt(order.substring( 22, i), 10);
                        String message = order.substring(i + 2);
                        System.out.println(dataBase.sendMessageToChat(user.getId(), chatId, message));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#edit message n chat m: newMessage
                else if (order.toLowerCase(Locale.ROOT).contains("#edit message") && order.contains("chat")){
                    try {
                        int i = 14;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        int messageId = Integer.parseInt(order.substring(14, i), 10);
                        int j = i + 6;
                        i = j;
                        while (order.charAt(i) != ':') {
                            i++;
                        }
                        int chatId = Integer.parseInt(order.substring( j, i), 10);
                        i += 2;
                        String message = order.substring(i);
                        System.out.println(dataBase.editMessageChat(user.getId(), chatId, messageId, message));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#reaction to message n chat m
                else if (order.toLowerCase(Locale.ROOT).contains("#reaction to message") && order.contains("chat")){
                    try {
                        int i = 21;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        int messageId = Integer.parseInt(order.substring( 21, i), 10);
                        i += 6;
                        int chatId = Integer.parseInt(order.substring(i, order.length()), 10);
                        System.out.println("Please choose an reaction");
                        System.out.println("like");
                        System.out.println("disLike");
                        System.out.println("heart");
                        String reaction = "";
                        while (!(reaction.equals("like") || reaction.equals("disLike") || reaction.equals("heart"))) {
                            System.out.println("Please choose one of the reactions in the list");
                            reaction = input.nextLine();
                        }
                        System.out.println(dataBase.reactionMessageChat(user.getId(), chatId, messageId, reaction));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#pin message n chat m
                else if (order.toLowerCase(Locale.ROOT).contains("#pin message") && order.contains("chat")){
                    try {
                        int i = 13;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        int messageId = Integer.parseInt(order.substring(13, i), 10);
                        i += 6;
                        int chatId = Integer.parseInt(order.substring(i, order.length()), 10);
                        System.out.println(dataBase.pinMessageChat(user.getId(), chatId, messageId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#delete message n chat m
                //check
                else if (order.toLowerCase(Locale.ROOT).contains("#delete message") && order.contains("chat")){
                    try {
                        int i = 16;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        int messageId = Integer.parseInt(order.substring( 16, i), 10);
                        i += 6;
                        int chatId = Integer.parseInt(order.substring( i, order.length()), 10);
                        System.out.println(dataBase.deleteMessageChat(user.getId(), chatId, messageId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#show admins of chat n
                else if (order.toLowerCase(Locale.ROOT).contains("#show admins of chat")){
                    try {
                        int chatId = Integer.parseInt(order.substring(21, order.length()), 10);
                        ArrayList<Integer> admins = dataBase.showAdminChat(user.getId(), chatId);
                        for (int i : admins) {
                            System.out.println(dataBase.getUser(i).toString());
                        }
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                else if (order.toLowerCase(Locale.ROOT).equals("#show my chats")){
                    ArrayList<Chat> usersChats = dataBase.showChat(user.getId());
                    for(Chat ch : usersChats){
                        System.out.println(ch.toString());
                    }
                }
                //#show users in voiceChat n
                else if (order.contains("#show users in voiceChat")){
                    try {
                        int chatId = Integer.parseInt(order.substring(25, order.length()), 10);
                        ArrayList<Integer> onlineUsers = dataBase.showOnlineVoiceChatUser(user.getId(), chatId);
                        for (int i : onlineUsers) {
                            System.out.println(dataBase.getUser(i).toString());
                        }
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#show users of chat n
                else if (order.toLowerCase(Locale.ROOT).contains("#show users of chat")){
                    try {
                        int chatId = Integer.parseInt(order.substring(20, order.length()), 10);
                        ArrayList<Integer> users = dataBase.showUserChat(user.getId(), chatId);
                        for (int i : users) {
                            System.out.println(dataBase.getUser(i).toString());
                        }
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#remove admin name from chat n
                else if (order.toLowerCase(Locale.ROOT).contains("#remove admin") && order.toLowerCase(Locale.ROOT).contains("from chat")){
                    try {
                        int i = 14;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        String username = order.substring(14, i);
                        int deletedId = dataBase.determineId(username);
                        int chatId = Integer.parseInt(order.substring(i + 11, order.length()), 10);
                        System.out.println(dataBase.deleteAdminChat(user.getId(), deletedId, chatId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#remove user (name) from chat n
                else if (order.toLowerCase(Locale.ROOT).contains("#remove user") && order.toLowerCase(Locale.ROOT).contains("from chat")){
                    try {
                        int i = 13;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        String username = order.substring(13, i);
                        int deletedId = dataBase.determineId(username);
                        int chatId = Integer.parseInt(order.substring( i + 11, order.length()), 10);
                        System.out.println(dataBase.deleteUserChat(user.getId(), deletedId, chatId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#add admin name to server n
                else if (order.toLowerCase(Locale.ROOT).contains("#add admin") && order.toLowerCase(Locale.ROOT).contains("to server")){
                    try {
                        int i = 11;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        String username = order.substring(11, i);
                        int admin = dataBase.determineId(username);
                        int serverId = Integer.parseInt(order.substring( i + 11, order.length()), 10);
                        System.out.println(dataBase.addAdminGroupServer(user.getId(), admin, serverId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#remove admin name from sever n
                else if (order.toLowerCase(Locale.ROOT).contains("#remove admin") && order.toLowerCase(Locale.ROOT).contains("from server")){
                    try {
                        int i = 14;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        String username = order.substring(14, i);
                        int admin = dataBase.determineId(username);
                        int serverId = Integer.parseInt(order.substring(i + 12, order.length()), 10);
                        System.out.println(dataBase.deleteAdminGroupServer(user.getId(), admin, serverId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#create privateChat with name
                else if (order.contains("#create privateChat with")){
                    try {
                        String username = order.substring(25);
                        int userId = dataBase.determineId(username);
                        System.out.println(dataBase.creatPrivateChat(user.getId(), userId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#send message to privateChat n: message
                else if (order.contains("#send message to privateChat")){
                    try {
                        int i = 29;
                        while (order.charAt(i) != ':') {
                            i++;
                        }
                        int privateChatId = Integer.parseInt(order.substring( 29, i), 10);
                        String message = order.substring(i + 2);
                        System.out.println(dataBase.sendMessageToPrivateChat(user.getId(), privateChatId, message));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#edit message n privateChat m: newMessage
                else if (order.toLowerCase(Locale.ROOT).contains("#edit message") && order.contains("privateChat")){
                    try {
                        int i = 14;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        int messageId = Integer.parseInt(order.substring( 14, i), 10);
                        int j = i + 13;
                        i = j;
                        while (order.charAt(i) != ':') {
                            i++;
                        }
                        int privateChatId = Integer.parseInt(order.substring( j, i), 10);
                        i += 2;
                        String message = order.substring(i);
                        System.out.println(dataBase.editMessagePrivateChat(user.getId(), privateChatId, messageId, message));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#reaction to message n privateChat m
                else if (order.toLowerCase(Locale.ROOT).contains("#reaction to message") && order.contains("privateChat")){
                    try {
                        int i = 21;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        int messageId = Integer.parseInt(order.substring( 21, i), 10);
                        i += 13;
                        int chatId = Integer.parseInt(order.substring( i, order.length()), 10);
                        System.out.println("Please choose an reaction");
                        System.out.println("like");
                        System.out.println("disLike");
                        System.out.println("heart");
                        String reaction = "";
                        while (!(reaction.equals("like") || reaction.equals("disLike") || reaction.equals("heart"))) {
                            System.out.println("Please choose one of the reactions in the list");
                            reaction = input.nextLine();
                        }
                        System.out.println(dataBase.reactionMessagePrivateChat(user.getId(), chatId, messageId, reaction));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#pin message n privateChat m
                else if (order.toLowerCase(Locale.ROOT).contains("#pin message") && order.contains("privateChat")){
                    try {
                        int i = 13;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        int messageId = Integer.parseInt(order.substring(13, i), 10);
                        i += 13;
                        int chatId = Integer.parseInt(order.substring(i, order.length()), 10);
                        System.out.println(dataBase.pinMessagePrivateChat(user.getId(), chatId, messageId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#delete message n privateChat m
                else if (order.toLowerCase(Locale.ROOT).contains("#delete message") && order.contains("privateChat")){
                    try {
                        int i = 16;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        int messageId = Integer.parseInt(order.substring( 16, i), 10);
                        i += 13;
                        int chatId = Integer.parseInt(order.substring( i, order.length()), 10);
                        System.out.println(dataBase.deleteMessagePrivateChat(user.getId(), chatId, messageId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#show messages of privateChat n
                else if (order.contains("#show messages of privateChat")){
                    try {
                        int privateChatId = Integer.parseInt(order.substring( 30, order.length()), 10);
                        ArrayList<ChatMessage> show = dataBase.showMessagesPrivateChat(user.getId(), privateChatId);
                        for (ChatMessage ch : show) {
                            System.out.println(ch.toString());
                        }
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                else if (order.equals("#show my privateChats")){
                    dataBase.showPrivateChats(user.getId());
                }
                //#create voiceChat name in server n
                else if (order.contains("#create voiceChat") && order.toLowerCase(Locale.ROOT).contains("in server")){
                    try {
                        int i = 18;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        String chatName = order.substring(18, i);
                        int serverId = Integer.parseInt(order.substring( i + 11, order.length()), 10);
                        System.out.println(dataBase.createVoiceMessage(user.getId(), serverId, chatName));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#add me to voiceChat n
                else if (order.contains("#add me to voiceChat")){
                    try {
                        int voiceChatId = Integer.parseInt(order.substring(21, order.length()), 10);
                        System.out.println(dataBase.connectVoiceChat(user.getId(), voiceChatId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#leave voiceChat n
                else if (order.contains("#leave voiceChat")){
                    try {
                        int voiceChatId = Integer.parseInt(order.substring( 17, order.length()), 10);
                        System.out.println(dataBase.leftVoiceMessages(user.getId(), voiceChatId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                //#add user (name) to voiceChat n
                else if (order.toLowerCase(Locale.ROOT).contains("#add user") && order.contains("to voiceChat")){
                    try {
                        int i = 10;
                        while (order.charAt(i) != ' ') {
                            i++;
                        }
                        String username = order.substring(10, i);
                        int userId = dataBase.determineId(username);
                        int voiceChatId = Integer.parseInt(order.substring(i + 14, order.length()), 10);
                        System.out.println(dataBase.addUserVoiceChat(user.getId(), userId, voiceChatId));
                        outputClientDataBase();
                        inputClientDataBase();
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println("Please type an order that is in the list of orders");
                    }
                }
                else if (order.toLowerCase(Locale.ROOT).equals("#show list of orders")){
                    System.out.println("Please choose one of the orders, below as your order");
                    System.out.println("*.#change username");
                    System.out.println("*.#change password");
                    System.out.println("*.#change email");
                    System.out.println("*.#change phone number");
                    System.out.println("*.#change status");
                    System.out.println("*.#change picture");
                    System.out.println("*.#delete account");
                    System.out.println("*.#add to friends (username) : this order will send a friend request to the user that you have chosen in your order");
                    System.out.println("*.#show friend requests");
                    System.out.println("*.#show all friends");
                    System.out.println("*.#show online friends");
                    System.out.println("*.#remove from friends (username) : this order will remove the user that you have chosen from your friends list");
                    System.out.println("*.#block (username)");
                    System.out.println("*.#unblock (username)");
                    System.out.println("*.#show blocked users");
                    System.out.println("*.#create server (server name)");
                    System.out.println("*.#create chat (chat name) in server (server id)");
                    System.out.println("*.#add user (user id) to chat (chat id)");
                    System.out.println("*.#add admin (user id) to chat (chat id)");
                    System.out.println("*.#show message of chat (chat id)");
                    System.out.println("*.#send message to chat (chat id): (message)");
                    System.out.println("*.#edit message (message id) chat (chat id): (newMessage)");
                    System.out.println("*.#reaction to message (message id) chat (chat id)");
                    System.out.println("*.#pin message (message id) chat (chat id)");
                    System.out.println("*.#delete message (message id) chat (chat id)");
                    System.out.println("*.#show admins of chat (chat id)");
                    System.out.println("*.#show users of chat (chat id)");
                    System.out.println("*.#show my chats");
                    System.out.println("*.#remove admin (username) from chat (chat id)");
                    System.out.println("*.#remove user (username) from chat (chat id)");
                    System.out.println("*.#add admin (username) to server (server id)");
                    System.out.println("*.#remove admin (username) from server (server id)");
                    System.out.println("*.#create privateChat with (username)");
                    System.out.println("*.#send message to privateChat (privateChat id): (message)");
                    System.out.println("*.#edit message (message id) privateChat (privateChat id): (newMessage)");
                    System.out.println("*.#reaction to message (message id) privateChat (privateChat id)");
                    System.out.println("*.#pin message (message id) privateChat (privateChat id)");
                    System.out.println("*.#delete message (message id) privateChat (privateChat id)");
                    System.out.println("*.#show messages of privateChat (privateChat id)");
                    System.out.println("*.#show my privateChats");
                    System.out.println("*.#create voiceChat (voice chat name) in server (server id)");
                    System.out.println("*.#add me to voiceChat (voice chat id)");
                    System.out.println("*.#leave voiceChat (voice chat id)");
                    System.out.println("*.#add user (username) to voiceChat (voice chat id)");
                    System.out.println("*.#show users in voiceChat n");
                }
                else {
                    System.out.println("Please type an order that is in the list of orders");
                }
                System.out.println("Please enter an order");
            }
        }
    }

    /**
     * Output clients orders.
     *
     * @param order the order
     */
    public void outputClientsOrders(String order){
        try {
            outputStream.reset();
            outputStream.writeUnshared(order);
        }
        catch (IOException e) {
            System.exit(0);
        }
    }

    /**
     * Output client data base.
     */
    public void outputClientDataBase(){
        try {
            outputStream.writeUnshared(dataBase);
        }
        catch (IOException e) {
            System.exit(0);
        }
    }

    /**
     * Input client data base.
     */
    public void inputClientDataBase(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dataBase = (DataBase) inputStream.readObject();
                }
                catch (IOException e) {
                    System.exit(0);
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Connect.
     *
     * @throws Exception the exception
     */
    public void connect() throws Exception {
        firstMenu();
        outputClientDataBase();
        secondMenu();
    }

    public Socket getSocket() {
        return socket;
    }

    public User getUser() {
        return user;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public String getOrder() {
        return order;
    }

    public int getChoose() {
        return choose;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }
}
