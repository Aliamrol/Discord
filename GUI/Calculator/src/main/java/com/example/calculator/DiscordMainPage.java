package com.example.calculator;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Discord main page.
 */
public class DiscordMainPage {

    /**
     * The Client.
     */
    Client client;

    @FXML
    private AnchorPane DirectMessagesColumnPane;

    @FXML
    private AnchorPane FriendRequest0Pane;

    @FXML
    private AnchorPane FriendRequestPane;

    @FXML
    private AnchorPane FriendsMainPane;

    @FXML
    private AnchorPane ServerChatsPane;

    @FXML
    private AnchorPane VoiceChannelPane;

    @FXML
    private AnchorPane addFriendPane;

    @FXML
    private Circle addServerIcon;

    @FXML
    private VBox allFriends;

    @FXML
    private AnchorPane allFriends0Pane;

    @FXML
    private AnchorPane allFriendsPane;

    @FXML
    private Circle blackCircle;

    @FXML
    private Text blockMe;

    @FXML
    private AnchorPane blockPane;

    @FXML
    private VBox blocked;

    @FXML
    private AnchorPane blocked0Pane;

    @FXML
    private AnchorPane blockedPane;

    @FXML
    private Text blockedSuccess;

    @FXML
    private Circle callFriendsIcon;

    @FXML
    private AnchorPane changeStatus;

    @FXML
    private AnchorPane createPrivateChatPage;

    @FXML
    private TextField createPrivateChatSearchField;

    @FXML
    private AnchorPane createServerPane;

    @FXML
    private TextField createTextChannelField;

    @FXML
    private AnchorPane createTextChannelPane;

    @FXML
    private TextField createVoiceChannelField;

    @FXML
    private AnchorPane createVoiceChannelPane;

    @FXML
    private VBox directMessages;

    @FXML
    private Text doesntBelong;

    @FXML
    private AnchorPane editMessagePane;

    @FXML
    private AnchorPane editMessagePane1;

    @FXML
    private TextField editedMessageField;

    @FXML
    private TextField editedMessageField1;

    @FXML
    private ImageView emptyPageImage;

    @FXML
    private Text existedPrivateChat;

    @FXML
    private Text friendRequestError;

    @FXML
    private Text friendRequestSuccess;

    @FXML
    private VBox friendRequests;

    @FXML
    private ScrollPane friendsInfo;

    @FXML
    private Circle grayCircle;

    @FXML
    private Circle greenCircle;

    @FXML
    private Line headSetLine;

    @FXML
    private TextField inviteAdminField;

    @FXML
    private AnchorPane inviteAdminOrUserToChat;

    @FXML
    private AnchorPane inviteAdminPane;

    @FXML
    private TextField invitePeopleField;

    @FXML
    private AnchorPane invitePeoplePane;

    @FXML
    private TextField inviteUserField;

    @FXML
    private AnchorPane inviteUserPane;

    @FXML
    private TextField messageField;

    @FXML
    private Line micLine;

    @FXML
    private AnchorPane moreMessagePane;

    @FXML
    private AnchorPane moreMessagePane1;

    @FXML
    private Circle myImage;

    @FXML
    private Text myUsername;

    @FXML
    private VBox onlineFriends;

    @FXML
    private AnchorPane onlineFriends0Pane;

    @FXML
    private AnchorPane onlineFriendsPane;

    @FXML
    private Text pin;

    @FXML
    private Text privateChatIdText;

    @FXML
    private Circle privateChatImage;

    @FXML
    private VBox privateChatMessages;

    @FXML
    private Text privateChatName;

    @FXML
    private AnchorPane privateChatPane;

    @FXML
    private AnchorPane reactionPane;

    @FXML
    private AnchorPane reactionPane1;

    @FXML
    private TextField searchBlockName;

    @FXML
    private TextField searchFriend;

    @FXML
    private VBox serverAdmins;

    @FXML
    private AnchorPane serverAdminsPane;

    @FXML
    private VBox serverChats;

    @FXML
    private VBox serverIcons;

    @FXML
    private AnchorPane serverMessagesPane;

    @FXML
    private Button serverNameButton;

    @FXML
    private TextField serverNameField;

    @FXML
    private AnchorPane serverSettingPane;

    @FXML
    private Text successCreateTextChannel;

    @FXML
    private Text successCreateVoiceChannel;

    @FXML
    private Text successInvitePeople;

    @FXML
    private Text successInviteUser;

    @FXML
    private Text succressInviteAdmin;

    @FXML
    private VBox textChannelAdmins;

    @FXML
    private AnchorPane textChannelAdminsPane;

    @FXML
    private TextField textChannelMessageField;

    @FXML
    private VBox textChannelMessages;

    @FXML
    private Text textChannelPinMessage;

    @FXML
    private Text textChannelTitle;

    @FXML
    private VBox textChannelUsers;

    @FXML
    private AnchorPane textChannelUsersPane;

    @FXML
    private Text userBlock;

    @FXML
    private Text wrongBlock;

    @FXML
    private Text wrongCreateTextChannel;

    @FXML
    private Text wrongCreateVoiceChat;

    @FXML
    private Text wrongInfoCreatePrivateChat;

    @FXML
    private Text wrongInviteAdmin;

    @FXML
    private Text wrongInvitePeople;

    @FXML
    private Text wrongInviteUser;

    @FXML
    private Text wrongPin;

    @FXML
    private Text wrongSendMessage;

    /**
     * Set client.
     *
     * @param client the client
     */
    public void setClient (Client client){
        this.client = client;
        myUsername.setText(client.getUser().getUsername());
        myImage.setStroke(Color.valueOf( "#23272A"));
        Image image = new Image(client.getUser().getPictureAddress());
        myImage.setFill(new ImagePattern(image));
        addServerIcon.setStroke(Color.valueOf( "#23272A"));
        Image addServerPic = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\greenPlus.png");
        addServerIcon.setFill(new ImagePattern(addServerPic));
        callFriendsIcon.setStroke(Color.valueOf( "#23272A"));
        Image discordIcon = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\discordUserLogo.png");
        callFriendsIcon.setFill(new ImagePattern(discordIcon));
        switch (client.getUser().getState()) {
            case "Online" -> {
                greenCircle.setVisible(true);
                blackCircle.setVisible(false);
                grayCircle.setVisible(false);
            }
            case "Idle" -> {
                greenCircle.setVisible(true);
                blackCircle.setVisible(false);
                grayCircle.setVisible(false);
                Image image1 = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\idle.png");
                greenCircle.setFill(new ImagePattern(image1));
            }
            case "Do Not Disturb" -> {
                greenCircle.setVisible(true);
                blackCircle.setVisible(false);
                grayCircle.setVisible(false);
                Image image2 = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\doNotDisturb.png");
                greenCircle.setFill(new ImagePattern(image2));
            }
            case "Invisible" -> {
                greenCircle.setVisible(false);
                blackCircle.setVisible(true);
                grayCircle.setVisible(true);
            }
        }
        ArrayList<PrivateChat> myPvs = client.getDataBase().checkPrivateChats(client.getUser().getId());
        for(PrivateChat pv : myPvs){
            HBox row = new HBox();
            String friendName;
            if(pv.getUsersId()[0] == client.getUser().getId()){
                friendName = client.getDataBase().getUser(pv.getUsersId()[1]).getUsername();
            }
            else {
                friendName = client.getDataBase().getUser(pv.getUsersId()[0]).getUsername();
            }
            String result = friendName + "\n" + "chat id: " + pv.getId();
            Text text = new Text(result);
            text.setFill(Color.valueOf("white"));
            Circle circle = new Circle();
            circle.setStroke(Color.valueOf( "#23272A"));
            Image image1 = new Image(client.getDataBase().returnPictureAddress(friendName));
            circle.setFill(new ImagePattern(image1));
            circle.setRadius(18.0);
            row.setSpacing(15.0);
            row.getChildren().add(circle);
            row.getChildren().add(text);
            directMessages.setSpacing(15.0);
            directMessages.getChildren().add(row);
            row.setCursor(Cursor.cursor("HAND"));
            row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
                }
            });
            row.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
                }
            });
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    FriendsMainPane.setVisible(false);
                    privateChatPane.setVisible(true);
                    privateChatImage.setStroke(Color.valueOf( "#23272A"));
                    Image image = new Image(client.getDataBase().returnPictureAddress(friendName));
                    privateChatImage.setFill(new ImagePattern(image));
                    privateChatName.setText(friendName);
                    privateChatIdText.setText("Chat ID: " + pv.getId());
                }
            });
        }
        ArrayList<GroupServer> myServers = client.getDataBase().checkServers(client.getUser().getId());
        for(GroupServer groupServer : myServers){
            Button server = new Button(groupServer.getName());
            server.setStyle("-fx-background-color:   #23272A" + ";-fx-background-radius: 5Px" + ";-fx-text-fill: white");
            server.setCursor(Cursor.cursor("HAND"));
            serverIcons.setSpacing(15.0);
            serverIcons.getChildren().add(server);
            server.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:   #206694");
                }
            });
            server.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent" + ";-fx-text-fill: white");
                }
            });
            server.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    DirectMessagesColumnPane.setVisible(false);
                    friendsInfo.setVisible(false);
                    FriendsMainPane.setVisible(false);
                    ServerChatsPane.setVisible(true);
                    serverMessagesPane.setVisible(true);
                    serverAdminsPane.setVisible(true);
                    serverNameButton.setText(groupServer.getName() + ": id " + groupServer.getId());
                }
            });
        }
    }

    /**
     * Call setting page.
     *
     * @param event the event
     */
    @FXML
    void callSettingPage(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DiscordSetting.fxml"));
        try {
            Parent root = loader.load();
            DiscordSetting discordSetting = loader.getController();
            discordSetting.setClient(client);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The Head set cnt.
     */
    int headSetCnt = 1;

    /**
     * Mute head set.
     *
     * @param event the event
     */
    @FXML
    void muteHeadSet(MouseEvent event) {
        headSetLine.setVisible(headSetCnt % 2 != 0);
        headSetCnt++;
    }

    /**
     * The Mic cnt.
     */
    int micCnt = 1;

    /**
     * Mute mic.
     *
     * @param event the event
     */
    @FXML
    void muteMic(MouseEvent event) {
        micLine.setVisible(micCnt % 2 != 0);
        micCnt++;
    }

    /**
     * Online clicked.
     *
     * @param event the event
     */
    @FXML
    void onlineClicked(MouseEvent event) {
        onlineFriendsPane.setVisible(false);
        onlineFriends0Pane.setVisible(false);
        FriendRequestPane.setVisible(false);
        FriendRequest0Pane.setVisible(false);
        allFriends0Pane.setVisible(false);
        allFriendsPane.setVisible(false);
        addFriendPane.setVisible(false);
        blockedPane.setVisible(false);
        blockPane.setVisible(false);
        blocked0Pane.setVisible(false);
        String friendsName = client.getDataBase().showUsersAllFriends(client.getUser());
        if(client.getUser().getFriendsId().size() == 0){
            onlineFriends0Pane.setVisible(true);
        }
        else {
            onlineFriendsPane.setVisible(true);
            int j = 0;
            onlineFriends.getChildren().clear();
            for(int i : client.getUser().getFriendsId()){
                int k = j;
                while(j < friendsName.length() && friendsName.charAt(j) != ' '){
                    j++;
                }
                String name = friendsName.substring(k, j);
                int id = client.getDataBase().determineId(name);
                String status = client.getDataBase().returnStatus(id);
                j++;
                if(status.equals("Online") || status.equals(("Idle")) || status.equals("Do Not Disturb")) {
                    HBox row = new HBox();
                    String result = name + "\n" + status;
                    Text text = new Text(result);
                    text.setFill(Color.valueOf("white"));
                    Circle circle = new Circle();
                    circle.setStroke(Color.valueOf("#23272A"));
                    Image image = new Image(client.getDataBase().returnPictureAddress(name));
                    circle.setFill(new ImagePattern(image));
                    circle.setRadius(18.0);
                    Button remove = new Button("Remove From Friends");
                    remove.setStyle("-fx-background-color:  #e74c3c" + ";-fx-background-radius: 5Px" + ";-fx-text-fill: white");
                    remove.setCursor(Cursor.cursor("HAND"));
                    Text text1 = new Text("                                                                 ");
                    row.setSpacing(15.0);
                    row.getChildren().add(circle);
                    row.getChildren().add(text);
                    row.getChildren().add(text1);
                    row.getChildren().add(remove);
                    onlineFriends.setSpacing(15.0);
                    onlineFriends.getChildren().add(row);
                    remove.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            onlineFriends.getChildren().remove(row);
                            client.getDataBase().removeFromFriendsList(client.getUser().getId(), id);
                            client.getDataBase().removeFromFriendsList(id, client.getUser().getId());
                            client.outputClientDataBase();
                            client.inputClientDataBase();
                        }
                    });
                }
            }
        }
    }

    /**
     * Button entered.
     *
     * @param mouseEvent the mouse event
     */
    public void buttonEntered(MouseEvent mouseEvent) {
        ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:  #2C2F33");
    }

    /**
     * Button exited.
     *
     * @param mouseEvent the mouse event
     */
    public void buttonExited(MouseEvent mouseEvent) {
        ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
    }

    /**
     * Edit status.
     *
     * @param mouseEvent the mouse event
     */
    public void editStatus(MouseEvent mouseEvent) {
        changeStatus.setVisible(true);
    }

    /**
     * Add to friends requests list.
     *
     * @param mouseEvent the mouse event
     */
    public void addToFriendsRequestsList(MouseEvent mouseEvent) {
        friendRequestSuccess.setVisible(false);
        friendRequestError.setVisible(false);
        searchFriend.clear();
        onlineFriendsPane.setVisible(false);
        onlineFriends0Pane.setVisible(false);
        FriendRequestPane.setVisible(false);
        FriendRequest0Pane.setVisible(false);
        allFriends0Pane.setVisible(false);
        allFriendsPane.setVisible(false);
        addFriendPane.setVisible(true);
        blockedPane.setVisible(false);
        blockPane.setVisible(false);
        blocked0Pane.setVisible(false);
    }

    /**
     * Send friend request.
     *
     * @param mouseEvent the mouse event
     */
    public void sendFriendRequest(MouseEvent mouseEvent) {
        friendRequestSuccess.setVisible(false);
        friendRequestError.setVisible(false);
        String friendName = searchFriend.getText();
        int senderId = client.getDataBase().determineId(client.getUser().getUsername());
        int receiverId = client.getDataBase().determineId(friendName);
        if(!client.getDataBase().checkBlockList(senderId, receiverId)){
            if (client.getDataBase().addToFriendRequestList(senderId, receiverId)) {
                friendRequestSuccess.setVisible(true);
                client.outputClientDataBase();
                client.inputClientDataBase();
            }
            else {
                friendRequestError.setVisible(true);
            }
        }
    }

    /**
     * Show friend requests.
     *
     * @param mouseEvent the mouse event
     */
    public void showFriendRequests(MouseEvent mouseEvent) {
        onlineFriendsPane.setVisible(false);
        onlineFriends0Pane.setVisible(false);
        FriendRequestPane.setVisible(false);
        FriendRequest0Pane.setVisible(false);
        allFriends0Pane.setVisible(false);
        allFriendsPane.setVisible(false);
        addFriendPane.setVisible(false);
        blockedPane.setVisible(false);
        blocked0Pane.setVisible(false);
        blockPane.setVisible(false);
        String friendsName = client.getDataBase().showFriendRequests(client.getUser());
        if(client.getUser().getFriendRequests().size() == 0){
            FriendRequest0Pane.setVisible(true);
        }
        else {
            FriendRequestPane.setVisible(true);
            int j = 0;
            friendRequests.getChildren().clear();
            for(int i : client.getUser().getFriendRequests()){
                int k = j;
                while(friendsName.charAt(j) != ' '){
                    j++;
                }
                String name = friendsName.substring(k, j);
                j++;
                HBox row = new HBox();
                String result = name + "\n" + "Incoming Friend Request";
                Text text = new Text(result);
                text.setFill(Color.valueOf("white"));
                Circle circle = new Circle();
                circle.setStroke(Color.valueOf( "#23272A"));
                Image image = new Image(client.getDataBase().returnPictureAddress(name));
                circle.setFill(new ImagePattern(image));
                circle.setRadius(18.0);
                Button accept = new Button("Accept");
                Button reject = new Button("Reject");
                accept.setStyle("-fx-background-color:  #1F8B4C" + ";-fx-background-radius: 5Px" + ";-fx-text-fill: white");
                reject.setStyle("-fx-background-color:  #e74c3c" + ";-fx-background-radius: 5Px" + ";-fx-text-fill: white");
                accept.setCursor(Cursor.cursor("HAND"));
                reject.setCursor(Cursor.cursor("HAND"));
                Text text1 = new Text("                                                                 ");
                row.setSpacing(15.0);
                row.getChildren().add(circle);
                row.getChildren().add(text);
                row.getChildren().add(text1);
                row.getChildren().add(accept);
                row.getChildren().add(reject);
                friendRequests.setSpacing(15.0);
                friendRequests.getChildren().add(row);
                accept.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        friendRequests.getChildren().remove(row);
                        int friendId = client.getDataBase().acceptRequest(name, client.getUser().getId());
                        client.getDataBase().addToFriendsList(client.getUser().getId(), friendId);
                        client.getDataBase().addToFriendsList(friendId, client.getUser().getId());
                        client.getDataBase().clearFriendRequest(client.getUser().getId(), friendId);
                        client.outputClientDataBase();
                        client.inputClientDataBase();
                    }
                });
                reject.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        friendRequests.getChildren().remove(row);
                        int friendsId = client.getDataBase().determineId(name);
                        client.getDataBase().clearFriendRequest(client.getUser().getId(), friendsId);
                        client.outputClientDataBase();
                        client.inputClientDataBase();
                    }
                });
            }
        }
    }

    /**
     * Show all friends.
     *
     * @param mouseEvent the mouse event
     */
    public void showAllFriends(MouseEvent mouseEvent) {
        onlineFriendsPane.setVisible(false);
        onlineFriends0Pane.setVisible(false);
        FriendRequestPane.setVisible(false);
        FriendRequest0Pane.setVisible(false);
        allFriends0Pane.setVisible(false);
        allFriendsPane.setVisible(false);
        addFriendPane.setVisible(false);
        blockedPane.setVisible(false);
        blocked0Pane.setVisible(false);
        blockPane.setVisible(false);
        String friendsName = client.getDataBase().showUsersAllFriends(client.getUser());
        if(client.getUser().getFriendsId().size() == 0){
            allFriends0Pane.setVisible(true);
        }
        else {
            allFriendsPane.setVisible(true);
            int j = 0;
            allFriends.getChildren().clear();
            for(int i : client.getUser().getFriendsId()){
                int k = j;
                while(friendsName.charAt(j) != ' '){
                    j++;
                }
                String name = friendsName.substring(k, j);
                int id = client.getDataBase().determineId(name);
                j++;
                HBox row = new HBox();
                String result = name + "\n" + client.getDataBase().returnStatus(id);
                Text text = new Text(result);
                text.setFill(Color.valueOf("white"));
                Circle circle = new Circle();
                circle.setStroke(Color.valueOf( "#23272A"));
                Image image = new Image(client.getDataBase().returnPictureAddress(name));
                circle.setFill(new ImagePattern(image));
                circle.setRadius(18.0);
                Button remove = new Button("Remove From Friends");
                remove.setStyle("-fx-background-color:  #e74c3c" + ";-fx-background-radius: 5Px" + ";-fx-text-fill: white");
                remove.setCursor(Cursor.cursor("HAND"));
                Text text1 = new Text("                                                                 ");
                row.setSpacing(15.0);
                row.getChildren().add(circle);
                row.getChildren().add(text);
                row.getChildren().add(text1);
                row.getChildren().add(remove);
                allFriends.setSpacing(15.0);
                allFriends.getChildren().add(row);
                remove.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        allFriends.getChildren().remove(row);
                        client.getDataBase().removeFromFriendsList(client.getUser().getId(), id);
                        client.getDataBase().removeFromFriendsList(id, client.getUser().getId());
                        client.outputClientDataBase();
                        client.inputClientDataBase();
                    }
                });
            }
        }
    }


    /**
     * Show blocked.
     *
     * @param mouseEvent the mouse event
     */
    public void showBlocked(MouseEvent mouseEvent) {
        onlineFriendsPane.setVisible(false);
        onlineFriends0Pane.setVisible(false);
        FriendRequestPane.setVisible(false);
        FriendRequest0Pane.setVisible(false);
        allFriends0Pane.setVisible(false);
        allFriendsPane.setVisible(false);
        addFriendPane.setVisible(false);
        blockedPane.setVisible(false);
        blocked0Pane.setVisible(false);
        blockPane.setVisible(false);
        String blockedName = client.getDataBase().showUsersBlocked(client.getUser());
        if(client.getUser().getBlocked().size() == 0){
            blocked0Pane.setVisible(true);
        }
        else {
            blockedPane.setVisible(true);
            int j = 0;
            blocked.getChildren().clear();
            for(int i : client.getUser().getFriendsId()){
                int k = j;
                while(j < blockedName.length() && blockedName.charAt(j) != ' '){
                    j++;
                }
                String name = blockedName.substring(k, j);
                j++;
                HBox row = new HBox();
                String result = name + "\n" + "Blocked";
                Text text = new Text(result);
                text.setFill(Color.valueOf("white"));
                Circle circle = new Circle();
                circle.setStroke(Color.valueOf( "#23272A"));
                Image image = new Image(client.getDataBase().returnPictureAddress(name));
                circle.setFill(new ImagePattern(image));
                circle.setRadius(18.0);
                Button unblock = new Button("Unblock");
                unblock.setStyle("-fx-background-color:  #e74c3c" + ";-fx-background-radius: 5Px" + ";-fx-text-fill: white");
                unblock.setCursor(Cursor.cursor("HAND"));
                Text text1 = new Text("                                                                 ");
                row.setSpacing(15.0);
                row.getChildren().add(circle);
                row.getChildren().add(text);
                row.getChildren().add(text1);
                row.getChildren().add(unblock);
                unblock.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        blocked.getChildren().remove(row);
                        int blockedId = client.getDataBase().determineId(name);
                        client.getDataBase().removeFromBlockList(client.getUser().getId(), blockedId);
                        client.outputClientDataBase();
                        client.inputClientDataBase();
                    }
                });
                blocked.setSpacing(15.0);
                blocked.getChildren().add(row);
            }
        }
    }

    /**
     * Make block.
     *
     * @param mouseEvent the mouse event
     */
    public void makeBlock(MouseEvent mouseEvent) {
        wrongBlock.setVisible(false);
        blockedSuccess.setVisible(false);
        searchBlockName.clear();
        onlineFriendsPane.setVisible(false);
        onlineFriends0Pane.setVisible(false);
        FriendRequestPane.setVisible(false);
        FriendRequest0Pane.setVisible(false);
        allFriends0Pane.setVisible(false);
        allFriendsPane.setVisible(false);
        addFriendPane.setVisible(false);
        blockedPane.setVisible(false);
        blocked0Pane.setVisible(false);
        blockPane.setVisible(true);
    }

    /**
     * Block user.
     *
     * @param mouseEvent the mouse event
     */
    public void blockUser(MouseEvent mouseEvent) {
        wrongBlock.setVisible(false);
        blockedSuccess.setVisible(false);
        String blockedName = searchBlockName.getText();
        int senderId = client.getUser().getId();
        int receiverId = client.getDataBase().determineId(blockedName);
        if(receiverId == 0){
            wrongBlock.setVisible(true);
        }
        else {
            client.getDataBase().addToBlockList(senderId, receiverId);
            if(client.getDataBase().checkFriendsList(senderId, receiverId)){
                client.getDataBase().removeFromFriendsList(senderId, receiverId);
                client.getDataBase().removeFromFriendsList(receiverId, senderId);
            }
            client.outputClientDataBase();
            client.inputClientDataBase();
            blockedSuccess.setVisible(true);
        }
    }

    /**
     * Make status online.
     *
     * @param mouseEvent the mouse event
     */
    public void makeStatusOnline(MouseEvent mouseEvent) {
        greenCircle.setFill(Color.valueOf("#21ae2a"));
        client.getUser().setState("Online");
        client.getDataBase().updateUser(client.getUser());
        client.outputClientDataBase();
        client.inputClientDataBase();
        greenCircle.setVisible(true);
        blackCircle.setVisible(false);
        grayCircle.setVisible(false);
        changeStatus.setVisible(false);
    }

    /**
     * Make status idle.
     *
     * @param mouseEvent the mouse event
     */
    public void makeStatusIdle(MouseEvent mouseEvent) {
        client.getUser().setState("Idle");
        client.getDataBase().updateUser(client.getUser());
        client.outputClientDataBase();
        client.inputClientDataBase();
        greenCircle.setVisible(true);
        blackCircle.setVisible(false);
        grayCircle.setVisible(false);
        Image image = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\idle.png");
        greenCircle.setFill(new ImagePattern(image));
        changeStatus.setVisible(false);
    }

    /**
     * Make status do not disturb.
     *
     * @param mouseEvent the mouse event
     */
    public void makeStatusDoNotDisturb(MouseEvent mouseEvent) {
        client.getUser().setState("Do Not Disturb");
        client.getDataBase().updateUser(client.getUser());
        client.outputClientDataBase();
        client.inputClientDataBase();
        greenCircle.setVisible(true);
        blackCircle.setVisible(false);
        grayCircle.setVisible(false);
        Image image = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\doNotDisturb.png");
        greenCircle.setFill(new ImagePattern(image));
        changeStatus.setVisible(false);
    }

    /**
     * Make status invisible.
     *
     * @param mouseEvent the mouse event
     */
    public void makeStatusInvisible(MouseEvent mouseEvent) {
        client.getUser().setState("Invisible");
        client.getDataBase().updateUser(client.getUser());
        client.outputClientDataBase();
        client.inputClientDataBase();
        greenCircle.setVisible(false);
        blackCircle.setVisible(true);
        grayCircle.setVisible(true);
        changeStatus.setVisible(false);
    }

    /**
     * Button entered 1.
     *
     * @param mouseEvent the mouse event
     */
    public void buttonEntered1(MouseEvent mouseEvent) {
        ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:   #206694");
    }

    /**
     * Button exited 1.
     *
     * @param mouseEvent the mouse event
     */
    public void buttonExited1(MouseEvent mouseEvent) {
        ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
    }

    /**
     * Exit change status.
     *
     * @param mouseEvent the mouse event
     */
    public void exitChangeStatus(MouseEvent mouseEvent) {
        changeStatus.setVisible(false);
    }

    /**
     * Show friends page.
     *
     * @param mouseEvent the mouse event
     */
    public void showFriendsPage(MouseEvent mouseEvent) {
        privateChatPane.setVisible(false);
        FriendsMainPane.setVisible(true);
    }

    /**
     * Show add private chat page.
     *
     * @param mouseEvent the mouse event
     */
    public void showAddPrivateChatPage(MouseEvent mouseEvent) {
        createPrivateChatSearchField.clear();
        blockMe.setVisible(false);
        userBlock.setVisible(false);
        wrongInfoCreatePrivateChat.setVisible(false);
        existedPrivateChat.setVisible(false);
        createPrivateChatPage.setVisible(true);
    }

    /**
     * Button entered 2.
     *
     * @param mouseEvent the mouse event
     */
    public void buttonEntered2(MouseEvent mouseEvent) {
        ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
    }

    /**
     * Search for create chat.
     *
     * @param mouseEvent the mouse event
     */
    public void searchForCreateChat(MouseEvent mouseEvent) {
        blockMe.setVisible(false);
        userBlock.setVisible(false);
        existedPrivateChat.setVisible(false);
        wrongInfoCreatePrivateChat.setVisible(false);
        String friendName = createPrivateChatSearchField.getText();
        int friendId = client.getDataBase().determineId(friendName);
        if(friendId == 0){
            wrongInfoCreatePrivateChat.setVisible(true);
            createPrivateChatSearchField.clear();
        }
        else {
            if(client.getDataBase().checkBlockList(client.getUser().getId(), friendId)){
                userBlock.setVisible(true);
            }
            else if(client.getDataBase().checkBlockList(friendId, client.getUser().getId())){
                blockMe.setVisible(true);
            }
            else {
                int flag = client.getDataBase().creatPrivateChat(client.getUser().getId(), friendId);
                if (flag == 0) {
                    existedPrivateChat.setVisible(true);
                } else {
                    client.outputClientDataBase();
                    client.inputClientDataBase();
                    createPrivateChatPage.setVisible(false);
                    HBox row = new HBox();
                    String result = friendName + "\n" + "chat id: " + flag;
                    Text text = new Text(result);
                    text.setFill(Color.valueOf("white"));
                    Circle circle = new Circle();
                    circle.setStroke(Color.valueOf( "#23272A"));
                    Image image = new Image(client.getDataBase().returnPictureAddress(friendName));
                    circle.setFill(new ImagePattern(image));
                    circle.setRadius(18.0);
                    row.setSpacing(15.0);
                    row.getChildren().add(circle);
                    row.getChildren().add(text);
                    directMessages.setSpacing(15.0);
                    directMessages.getChildren().add(row);
                    row.setCursor(Cursor.cursor("HAND"));
                    row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
                        }
                    });
                    row.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
                        }
                    });
                    row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            FriendsMainPane.setVisible(false);
                            privateChatPane.setVisible(true);
                            privateChatImage.setStroke(Color.valueOf( "#23272A"));
                            Image image = new Image(client.getDataBase().returnPictureAddress(friendName));
                            privateChatImage.setFill(new ImagePattern(image));
                            privateChatName.setText(friendName);
                            privateChatIdText.setText("Chat ID: " + flag);
                            String text = privateChatIdText.getText();
                            int id = Integer.parseInt(text, 9, text.length(), 10);
                            ArrayList<ChatMessage> messages = client.getDataBase().showMessagesPrivateChat(client.getUser().getId(), id);
                            int i;
                            privateChatMessages.getChildren().clear();
                            for(ChatMessage msg : messages){
                                i = 0;
                                HBox row = new HBox();
                                String messageForm = msg.toString();
                                while(i < messageForm.length() && messageForm.charAt(i) != ':'){
                                    i++;
                                }
                                String name = messageForm.substring(0, i);
                                i += 2;
                                int j = i;
                                while(i < messageForm.length() && messageForm.charAt(i) != ':' && messageForm.charAt(i + 1) != ' '){
                                    i++;
                                }
                                i--;
                                String message = messageForm.substring(j, i);
                                i += 3;
                                j = i;
                                while(i < messageForm.length() && messageForm.charAt(i) != 'i'){
                                    i++;
                                }
                                i--;
                                String date = messageForm.substring(j, i);
                                i += 5;
                                j = i;
                                while(i < messageForm.length() && messageForm.charAt(i) != '\n'){
                                    i++;
                                }
                                String messageId = messageForm.substring(j, i);
                                String numberOfLikes = "0";
                                String numberOfDislikes = "0";
                                String numberOfHearts = "0";
                                if(i != messageForm.length()){
                                    if(msg.getLikes().size() != 0){
                                        i += 2;
                                        numberOfLikes = messageForm.substring(i, i + 1);
                                        if(msg.getDislikes().size() != 0){
                                            i += 3;
                                            numberOfDislikes = messageForm.substring(i, i + 1);
                                            if(msg.getHearts().size() != 0){
                                                i += 2;
                                                numberOfHearts = messageForm.substring(i, i + 1);
                                            }
                                        }
                                        else {
                                            if(msg.getHearts().size() != 0){
                                                i += 2;
                                                numberOfHearts = messageForm.substring(i, i + 1);
                                            }
                                        }
                                    }
                                    else {
                                        if(msg.getDislikes().size() != 0){
                                            i += 2;
                                            numberOfDislikes = messageForm.substring(i, i + 1);
                                            if(msg.getHearts().size() != 0){
                                                i += 2;
                                                numberOfHearts = messageForm.substring(i, i + 1);
                                            }
                                        }
                                        else {
                                            if(msg.getHearts().size() != 0){
                                                i += 1;
                                                numberOfHearts = messageForm.substring(i, i + 1);
                                            }
                                        }
                                    }
                                }
                                Circle circle = new Circle();
                                circle.setStroke(Color.valueOf( "#23272A"));
                                Image image1 = new Image(client.getDataBase().returnPictureAddress(name));
                                circle.setFill(new ImagePattern(image1));
                                circle.setRadius(18.0);
                                VBox column = new VBox();
                                Text nameText = new Text(name);
                                nameText.setFill(Color.valueOf("white"));
                                Text messageText = new Text(message);
                                messageText.setFill(Color.valueOf("white"));
                                column.setSpacing(8.0);
                                column.getChildren().add(nameText);
                                column.getChildren().add(messageText);
                                Text dateText = new Text(date);
                                dateText.setFill(Color.valueOf("white"));
                                Text idText = new Text(messageId);
                                idText.setFill(Color.valueOf("white"));
                                VBox heartsEmojis = new VBox();
                                ImageView imageView1 = new ImageView();
                                Image emojiHeart = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\heart.png");
                                imageView1.setImage(emojiHeart);
                                imageView1.setFitWidth(10);
                                imageView1.setFitHeight(10);
                                Text number1 = new Text(numberOfHearts);
                                heartsEmojis.setSpacing(8.0);
                                heartsEmojis.getChildren().add(imageView1);
                                heartsEmojis.getChildren().add(number1);
                                VBox likesEmojis = new VBox();
                                ImageView imageView2 = new ImageView();
                                Image emojiLike = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\like.png");
                                imageView2.setImage(emojiLike);
                                imageView2.setFitWidth(10);
                                imageView2.setFitHeight(10);
                                Text number2 = new Text(numberOfLikes);
                                likesEmojis.setSpacing(8.0);
                                likesEmojis.getChildren().add(imageView2);
                                likesEmojis.getChildren().add(number2);
                                VBox dislikesEmojis = new VBox();
                                ImageView imageView3 = new ImageView();
                                Image emojiDislike = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\dislike.png");
                                imageView3.setImage(emojiDislike);
                                imageView3.setFitWidth(10);
                                imageView3.setFitHeight(10);
                                Text number3 = new Text(numberOfDislikes);
                                dislikesEmojis.setSpacing(8.0);
                                dislikesEmojis.getChildren().add(imageView3);
                                dislikesEmojis.getChildren().add(number3);
                                Button more = new Button("...");
                                more.setStyle("-fx-background-color:  transparent" + ";-fx-background-radius: 5Px" + ";-fx-text-fill: white");
                                more.setCursor(Cursor.cursor("HAND"));
                                Text text1 = new Text("                                                                                  ");
                                row.setSpacing(15.0);
                                row.getChildren().add(circle);
                                row.getChildren().add(column);
                                row.getChildren().add(dateText);
                                row.getChildren().add(idText);
                                row.getChildren().add(heartsEmojis);
                                row.getChildren().add(likesEmojis);
                                row.getChildren().add(dislikesEmojis);
                                row.getChildren().add(text1);
                                row.getChildren().add(more);
                                privateChatMessages.setSpacing(15.0);
                                privateChatMessages.getChildren().add(row);
                                row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent mouseEvent) {
                                        ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
                                    }
                                });
                                row.setOnMouseExited(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent mouseEvent) {
                                        ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
                                    }
                                });
                                more.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent mouseEvent) {
                                        moreMessagePane.setVisible(true);
                                        messageId0 = Integer.parseInt(messageId);
                                    }
                                });
                            }
                        }
                    });
                }
            }
        }
    }

    /**
     * Exit create private chat page.
     *
     * @param mouseEvent the mouse event
     */
    public void exitCreatePrivateChatPage(MouseEvent mouseEvent) {
        createPrivateChatPage.setVisible(false);
    }

    /**
     * Refresh.
     *
     * @param mouseEvent the mouse event
     */
    public void refresh(MouseEvent mouseEvent) {
        directMessages.getChildren().clear();
        ArrayList<PrivateChat> myPvs = client.getDataBase().checkPrivateChats(client.getUser().getId());
        for(PrivateChat pv : myPvs){
            HBox row = new HBox();
            String friendName;
            if(pv.getUsersId()[0] == client.getUser().getId()){
                friendName = client.getDataBase().getUser(pv.getUsersId()[1]).getUsername();
            }
            else {
                friendName = client.getDataBase().getUser(pv.getUsersId()[0]).getUsername();
            }
            String result = friendName + "\n" + "chat id: " + pv.getId();
            Text text = new Text(result);
            text.setFill(Color.valueOf("white"));
            Circle circle = new Circle();
            circle.setStroke(Color.valueOf( "#23272A"));
            Image image1 = new Image(client.getDataBase().returnPictureAddress(friendName));
            circle.setFill(new ImagePattern(image1));
            circle.setRadius(18.0);
            row.setSpacing(15.0);
            row.getChildren().add(circle);
            row.getChildren().add(text);
            directMessages.setSpacing(15.0);
            directMessages.getChildren().add(row);
            row.setCursor(Cursor.cursor("HAND"));
            row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
                }
            });
            row.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
                }
            });
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    FriendsMainPane.setVisible(false);
                    privateChatPane.setVisible(true);
                    privateChatImage.setStroke(Color.valueOf( "#23272A"));
                    Image image = new Image(client.getDataBase().returnPictureAddress(friendName));
                    privateChatImage.setFill(new ImagePattern(image));
                    privateChatName.setText(friendName);
                    privateChatIdText.setText("Chat ID: " + pv.getId());
                }
            });
        }
    }

    /**
     * Upload file.
     *
     * @param mouseEvent the mouse event
     */
    public void uploadFile(MouseEvent mouseEvent) {

    }

    /**
     * Send message.
     *
     * @param mouseEvent the mouse event
     */
    public void sendMessage(MouseEvent mouseEvent) {
        String message = messageField.getText();
        if(!message.equals("") && message.trim().length() > 0) {
            String text = privateChatIdText.getText();
            int id = Integer.parseInt(text, 9, text.length(), 10);
            client.getDataBase().sendMessageToPrivateChat(client.getUser().getId(), id, message);
            client.outputClientDataBase();
            client.inputClientDataBase();
            messageField.clear();
        }
    }

    /**
     * The Message id 0.
     */
    int messageId0;

    /**
     * Refresh private chat page.
     *
     * @param mouseEvent the mouse event
     */
    public void refreshPrivateChatPage(MouseEvent mouseEvent) {
        String text = privateChatIdText.getText();
        int id = Integer.parseInt(text, 9, text.length(), 10);
        ArrayList<ChatMessage> messages = client.getDataBase().showMessagesPrivateChat(client.getUser().getId(), id);
        int i;
        privateChatMessages.getChildren().clear();
        for(ChatMessage msg : messages){
            if(client.getDataBase().checkPinPrivateChat(id)){
                pin.setText("Pinned message id: " + msg.getChatMessageId());
            }
            i = 0;
            HBox row = new HBox();
            String messageForm = msg.toString();
            while(i < messageForm.length() && messageForm.charAt(i) != ':'){
                i++;
            }
            String name = messageForm.substring(0, i);
            i += 2;
            int j = i;
            while(i < messageForm.length() && messageForm.charAt(i) != ':'){
                i++;
            }
            i--;
            String message = messageForm.substring(j, i);
            i += 3;
            j = i;
            while(i < messageForm.length() && messageForm.charAt(i) != 'i'){
                i++;
            }
            i--;
            String date = messageForm.substring(j, i);
            i += 5;
            j = i;
            while(i < messageForm.length() && messageForm.charAt(i) != '\n'){
                i++;
            }
            String messageId = messageForm.substring(j, i);
            i++;
            String numberOfLikes = "0";
            String numberOfDislikes = "0";
            String numberOfHearts = "0";
            if(i != messageForm.length()){
                if(msg.getLikes().size() != 0){
                    i += 2;
                    numberOfLikes = messageForm.substring(i, i + 1);
                    if(msg.getDislikes().size() != 0){
                        i += 3;
                        numberOfDislikes = messageForm.substring(i, i + 1);
                        if(msg.getHearts().size() != 0){
                            i += 2;
                            numberOfHearts = messageForm.substring(i, i + 1);
                        }
                    }
                    else {
                        if(msg.getHearts().size() != 0){
                            i += 2;
                            numberOfHearts = messageForm.substring(i, i + 1);
                        }
                    }
                }
                else {
                    if(msg.getDislikes().size() != 0){
                        i += 2;
                        numberOfDislikes = messageForm.substring(i, i + 1);
                        if(msg.getHearts().size() != 0){
                            i += 2;
                            numberOfHearts = messageForm.substring(i, i + 1);
                        }
                    }
                    else {
                        if(msg.getHearts().size() != 0){
                            i += 1;
                            numberOfHearts = messageForm.substring(i, i + 1);
                        }
                    }
                }
            }
            Circle circle = new Circle();
            circle.setStroke(Color.valueOf( "#23272A"));
            Image image1 = new Image(client.getDataBase().returnPictureAddress(name));
            circle.setFill(new ImagePattern(image1));
            circle.setRadius(18.0);
            VBox column = new VBox();
            Text nameText = new Text(name);
            nameText.setFill(Color.valueOf("white"));
            Text messageText = new Text(message);
            messageText.setFill(Color.valueOf("white"));
            column.setSpacing(8.0);
            column.getChildren().add(nameText);
            column.getChildren().add(messageText);
            Text dateText = new Text(date);
            dateText.setFill(Color.valueOf("white"));
            Text idText = new Text(messageId);
            idText.setFill(Color.valueOf("white"));
            VBox heartsEmojis = new VBox();
            ImageView imageView1 = new ImageView();
            Image emojiHeart = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\heart.png");
            imageView1.setImage(emojiHeart);
            imageView1.setFitWidth(10);
            imageView1.setFitHeight(10);
            Text number1 = new Text(numberOfHearts);
            number1.setFill(Color.valueOf("white"));
            heartsEmojis.setSpacing(8.0);
            heartsEmojis.getChildren().add(imageView1);
            heartsEmojis.getChildren().add(number1);
            VBox likesEmojis = new VBox();
            ImageView imageView2 = new ImageView();
            Image emojiLike = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\like.png");
            imageView2.setImage(emojiLike);
            imageView2.setFitWidth(10);
            imageView2.setFitHeight(10);
            Text number2 = new Text(numberOfLikes);
            number2.setFill(Color.valueOf("white"));
            likesEmojis.setSpacing(8.0);
            likesEmojis.getChildren().add(imageView2);
            likesEmojis.getChildren().add(number2);
            VBox dislikesEmojis = new VBox();
            ImageView imageView3 = new ImageView();
            Image emojiDislike = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\dislike.png");
            imageView3.setImage(emojiDislike);
            imageView3.setFitWidth(10);
            imageView3.setFitHeight(10);
            Text number3 = new Text(numberOfDislikes);
            number3.setFill(Color.valueOf("white"));
            dislikesEmojis.setSpacing(8.0);
            dislikesEmojis.getChildren().add(imageView3);
            dislikesEmojis.getChildren().add(number3);
            Button more = new Button("...");
            more.setStyle("-fx-background-color:  transparent" + ";-fx-background-radius: 5Px" + ";-fx-text-fill: white");
            more.setCursor(Cursor.cursor("HAND"));
            Text text1 = new Text("                                            ");
            row.setSpacing(15.0);
            row.getChildren().add(circle);
            row.getChildren().add(column);
            row.getChildren().add(dateText);
            row.getChildren().add(idText);
            row.getChildren().add(heartsEmojis);
            row.getChildren().add(likesEmojis);
            row.getChildren().add(dislikesEmojis);
            row.getChildren().add(text1);
            row.getChildren().add(more);
            privateChatMessages.setSpacing(15.0);
            privateChatMessages.getChildren().add(row);
            row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
                }
            });
            row.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
                }
            });
            more.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    moreMessagePane.setVisible(true);
                    messageId0 = Integer.parseInt(messageId);
                }
            });
        }
    }

    /**
     * Reaction message.
     *
     * @param mouseEvent the mouse event
     */
    public void reactionMessage(MouseEvent mouseEvent) {
        reactionPane.setVisible(true);
    }

    /**
     * Pin message.
     *
     * @param mouseEvent the mouse event
     */
    public void pinMessage(MouseEvent mouseEvent) {
        String text = privateChatIdText.getText();
        int chatId = Integer.parseInt(text, 9, text.length(), 10);
        if(client.getDataBase().pinMessagePrivateChat(client.getUser().getId(), chatId, messageId0)){
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
        pin.setText("Pinned message id: " + messageId0);
    }

    /**
     * Edit message.
     *
     * @param mouseEvent the mouse event
     */
    public void editMessage(MouseEvent mouseEvent) {
        editMessagePane.setVisible(true);
    }

    /**
     * Delete message.
     *
     * @param mouseEvent the mouse event
     */
    public void deleteMessage(MouseEvent mouseEvent) {
        String text = privateChatIdText.getText();
        int chatId = Integer.parseInt(text, 9, text.length(), 10);
        if(client.getDataBase().deleteMessagePrivateChat(client.getUser().getId(), chatId, messageId0)) {
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
    }

    /**
     * Exit more pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitMorePane(MouseEvent mouseEvent) {
        moreMessagePane.setVisible(false);
    }

    /**
     * Add heart.
     *
     * @param mouseEvent the mouse event
     */
    public void addHeart(MouseEvent mouseEvent) {
        String reaction = "heart";
        String text = privateChatIdText.getText();
        int chatId = Integer.parseInt(text, 9, text.length(), 10);
        if(client.getDataBase().reactionMessagePrivateChat(client.getUser().getId(), chatId, messageId0, reaction)) {
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
    }

    /**
     * Add like.
     *
     * @param mouseEvent the mouse event
     */
    public void addLike(MouseEvent mouseEvent) {
        String reaction = "like";
        String text = privateChatIdText.getText();
        int chatId = Integer.parseInt(text, 9, text.length(), 10);
        if(client.getDataBase().reactionMessagePrivateChat(client.getUser().getId(), chatId, messageId0, reaction)) {
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
    }

    /**
     * Add dislike.
     *
     * @param mouseEvent the mouse event
     */
    public void addDislike(MouseEvent mouseEvent) {
        String reaction = "dislike";
        String text = privateChatIdText.getText();
        int chatId = Integer.parseInt(text, 9, text.length(), 10);
        if(client.getDataBase().reactionMessagePrivateChat(client.getUser().getId(), chatId, messageId0, reaction)) {
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
    }

    /**
     * Exit reaction pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitReactionPane(MouseEvent mouseEvent) {
        reactionPane.setVisible(false);
    }

    /**
     * Exit edit message pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitEditMessagePane(MouseEvent mouseEvent) {
        editMessagePane.setVisible(false);
    }

    /**
     * Edit selected message.
     *
     * @param mouseEvent the mouse event
     */
    public void editSelectedMessage(MouseEvent mouseEvent) {
        String editedMessage = editedMessageField.getText();
        editedMessageField.clear();
        String text = privateChatIdText.getText();
        int chatId = Integer.parseInt(text, 9, text.length(), 10);
        if(client.getDataBase().editMessagePrivateChat(client.getUser().getId(), chatId, messageId0, editedMessage)) {
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
    }

    /**
     * Call friends pane.
     *
     * @param mouseEvent the mouse event
     */
    public void callFriendsPane(MouseEvent mouseEvent){
        ServerChatsPane.setVisible(false);
        serverMessagesPane.setVisible(false);
        serverAdminsPane.setVisible(false);
        DirectMessagesColumnPane.setVisible(true);
        friendsInfo.setVisible(true);
        FriendsMainPane.setVisible(true);
    }

    /**
     * Add server.
     *
     * @param mouseEvent the mouse event
     */
    public void addServer(MouseEvent mouseEvent) {
        createServerPane.setVisible(true);
    }

    /**
     * The Determine.
     */
    int determine;

    /**
     * Create server.
     *
     * @param mouseEvent the mouse event
     */
    public void createServer(MouseEvent mouseEvent) {
        String serverName = serverNameField.getText();
        int serverId = client.getDataBase().createServerGroup(client.getUser().getId(), serverName);
        client.outputClientDataBase();
        client.inputClientDataBase();
        Button server = new Button(serverName);
        server.setStyle("-fx-background-color:   #23272A" + ";-fx-background-radius: 5Px" + ";-fx-text-fill: white");
        server.setCursor(Cursor.cursor("HAND"));
        serverIcons.setSpacing(15.0);
        serverIcons.getChildren().add(server);
        server.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:   #206694");
            }
        });
        server.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent" + ";-fx-text-fill: white");
            }
        });
        server.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DirectMessagesColumnPane.setVisible(false);
                friendsInfo.setVisible(false);
                FriendsMainPane.setVisible(false);
                textChannelAdminsPane.setVisible(false);
                textChannelUsersPane.setVisible(false);
                ServerChatsPane.setVisible(true);
                serverMessagesPane.setVisible(true);
                serverAdminsPane.setVisible(true);
                serverNameButton.setText(serverName + ": id " + serverId);
                ArrayList<Chat> chats = client.getDataBase().checkChats(serverId);
                serverChats.getChildren().clear();
                for(Chat chat : chats){
                    Text textChannels = new Text("TEXT / VOICE CHANNELS");
                    textChannels.setFill(Color.valueOf("white"));
                    HBox row = new HBox();
                    if(chat instanceof ChatMessages) {
                        determine = 1;
                        ImageView imageView = new ImageView();
                        Image textChannel = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\hashtag.png");
                        imageView.setImage(textChannel);
                        imageView.setFitWidth(12);
                        imageView.setFitHeight(12);
                        Text text1 = new Text(chat.getName());
                        text1.setFill(Color.valueOf("white"));
                        row.setSpacing(10.0);
                        row.getChildren().add(imageView);
                        row.getChildren().add(text1);
                        serverChats.setSpacing(15.0);
                        if (cnt == 0) {
                            serverChats.getChildren().add(textChannels);
                            cnt++;
                        }
                        serverChats.getChildren().add(row);
                    }
                    else {
                        ImageView imageView = new ImageView();
                        Image textChannel = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\voice.png");
                        imageView.setImage(textChannel);
                        imageView.setFitWidth(12);
                        imageView.setFitHeight(12);
                        Text text1 = new Text(chat.getName());
                        text1.setFill(Color.valueOf("white"));
                        row.setSpacing(10.0);
                        row.getChildren().add(imageView);
                        row.getChildren().add(text1);
                        serverChats.setSpacing(15.0);
                        if(cnt == 0) {
                            serverChats.getChildren().add(textChannels);
                            cnt++;
                        }
                        serverChats.getChildren().add(row);
                    }
                    row.setCursor(Cursor.cursor("HAND"));
                    row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
                        }
                    });
                    row.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
                        }
                    });
                    row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            if(determine == 1) {
                                textChannelTitle.setText("Welcome to " + chat.getName() + " id: " + chat.getId());
                                serverAdminsPane.setVisible(false);
                                textChannelUsersPane.setVisible(true);
                                textChannelAdminsPane.setVisible(true);
                            }
                            else {
                                serverAdminsPane.setVisible(true);
                            }
                            serverMessagesPane.setVisible(true);
                        }
                    });
                }
            }
        });
    }

    /**
     * Exit create server pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitCreateServerPane(MouseEvent mouseEvent) {
        createServerPane.setVisible(false);
    }

    /**
     * Show page make change in server.
     *
     * @param mouseEvent the mouse event
     */
    public void showPageMakeChangeInServer(MouseEvent mouseEvent) {
        serverSettingPane.setVisible(true);
    }

    /**
     * Change color enter.
     *
     * @param mouseEvent the mouse event
     */
    public void changeColorEnter(MouseEvent mouseEvent) {
        ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:   #23272A");
    }

    /**
     * Change color exit.
     *
     * @param mouseEvent the mouse event
     */
    public void changeColorExit(MouseEvent mouseEvent) {
        ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent" + ";-fx-text-fill: white");
    }

    /**
     * Invite admin.
     *
     * @param mouseEvent the mouse event
     */
    public void inviteAdmin(MouseEvent mouseEvent) {
        succressInviteAdmin.setVisible(false);
        wrongInviteAdmin.setVisible(false);
        String userName = inviteAdminField.getText();
        String text = textChannelTitle.getText();
        int i = 0;
        while(!text.substring(i, i + 5).equals(" id: ")){
            i++;
        }
        i += 5;
        int userId = client.getDataBase().determineId(userName);
        int chatId = Integer.parseInt(text, i, text.length(), 10);
        if(client.getDataBase().addAdminChat(client.getUser().getId(), userId, chatId)){
            succressInviteAdmin.setVisible(true);
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
        else {
            wrongInviteAdmin.setVisible(true);
        }
    }

    /**
     * Exit invite admin pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitInviteAdminPane(MouseEvent mouseEvent) {
        inviteAdminPane.setVisible(false);
    }

    /**
     * The Cnt.
     */
    int cnt = 0;

    /**
     * Create text channel.
     *
     * @param mouseEvent the mouse event
     */
    public void createTextChannel(MouseEvent mouseEvent) {
        String textChannelName = createTextChannelField.getText();
        int i = 0;
        String text = serverNameButton.getText();
        while(!text.substring(i, i + 5).equals(": id ")){
            i++;
        }
        i += 5;
        int serverId = Integer.parseInt(text, i, text.length(), 10);
        int chatId = client.getDataBase().createChatMessages(client.getUser().getId(), serverId, textChannelName);
        if(chatId != 0){
            client.outputClientDataBase();
            client.inputClientDataBase();
            successCreateTextChannel.setVisible(true);
            Text textChannels = new Text("TEXT / VOICE CHANNELS");
            textChannels.setFill(Color.valueOf("white"));
            HBox row = new HBox();
            ImageView imageView = new ImageView();
            Image textChannel = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\hashtag.png");
            imageView.setImage(textChannel);
            imageView.setFitWidth(12);
            imageView.setFitHeight(12);
            Text text1 = new Text(textChannelName);
            text1.setFill(Color.valueOf("white"));
            row.setSpacing(10.0);
            row.getChildren().add(imageView);
            row.getChildren().add(text1);
            serverChats.setSpacing(15.0);
            if(cnt == 0) {
                serverChats.getChildren().add(textChannels);
                cnt++;
            }
            serverChats.getChildren().add(row);
            row.setCursor(Cursor.cursor("HAND"));
            row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
                }
            });
            row.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
                }
            });
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    textChannelTitle.setText("Welcome to " + textChannelName + " id: " + chatId);
                    serverMessagesPane.setVisible(true);
                    serverAdminsPane.setVisible(false);
                    textChannelUsersPane.setVisible(true);
                    textChannelAdminsPane.setVisible(true);
                    String text = textChannelTitle.getText();
                    int s = 0;
                    while(!text.substring(s, s + 5).equals(" id: ")){
                        s++;
                    }
                    s += 5;
                    int chatId = Integer.parseInt(text, s, text.length(), 10);
                    ArrayList<ChatMessage> messages = client.getDataBase().showMessagesChat(client.getUser().getId(), chatId);
                    int i;
                    textChannelMessages.getChildren().clear();
                    wrongSendMessage.setVisible(false);
                    wrongPin.setVisible(false);
                    doesntBelong.setVisible(false);
                    for(ChatMessage msg : messages){
                        pin.setText("Pinned message id: " + msg.getChatMessageId());
                        i = 0;
                        HBox row = new HBox();
                        String messageForm = msg.toString();
                        while(i < messageForm.length() && messageForm.charAt(i) != ':'){
                            i++;
                        }
                        String name = messageForm.substring(0, i);
                        i += 2;
                        int j = i;
                        while(i < messageForm.length() && messageForm.charAt(i) != ':'){
                            i++;
                        }
                        i--;
                        String message = messageForm.substring(j, i);
                        i += 3;
                        j = i;
                        while(i < messageForm.length() && messageForm.charAt(i) != 'i'){
                            i++;
                        }
                        i--;
                        String date = messageForm.substring(j, i);
                        i += 5;
                        j = i;
                        while(i < messageForm.length() && messageForm.charAt(i) != '\n'){
                            i++;
                        }
                        String messageId = messageForm.substring(j, i);
                        i++;
                        String numberOfLikes = "0";
                        String numberOfDislikes = "0";
                        String numberOfHearts = "0";
                        if(i != messageForm.length()){
                            if(msg.getLikes().size() != 0){
                                i += 2;
                                numberOfLikes = messageForm.substring(i, i + 1);
                                if(msg.getDislikes().size() != 0){
                                    i += 3;
                                    numberOfDislikes = messageForm.substring(i, i + 1);
                                    if(msg.getHearts().size() != 0){
                                        i += 2;
                                        numberOfHearts = messageForm.substring(i, i + 1);
                                    }
                                }
                                else {
                                    if(msg.getHearts().size() != 0){
                                        i += 2;
                                        numberOfHearts = messageForm.substring(i, i + 1);
                                    }
                                }
                            }
                            else {
                                if(msg.getDislikes().size() != 0){
                                    i += 2;
                                    numberOfDislikes = messageForm.substring(i, i + 1);
                                    if(msg.getHearts().size() != 0){
                                        i += 2;
                                        numberOfHearts = messageForm.substring(i, i + 1);
                                    }
                                }
                                else {
                                    if(msg.getHearts().size() != 0){
                                        i += 1;
                                        numberOfHearts = messageForm.substring(i, i + 1);
                                    }
                                }
                            }
                        }
                        Circle circle = new Circle();
                        circle.setStroke(Color.valueOf( "#23272A"));
                        Image image1 = new Image(client.getDataBase().returnPictureAddress(name));
                        circle.setFill(new ImagePattern(image1));
                        circle.setRadius(18.0);
                        VBox column = new VBox();
                        Text nameText = new Text(name);
                        nameText.setFill(Color.valueOf("white"));
                        Text messageText = new Text(message);
                        messageText.setFill(Color.valueOf("white"));
                        column.setSpacing(8.0);
                        column.getChildren().add(nameText);
                        column.getChildren().add(messageText);
                        Text dateText = new Text(date);
                        dateText.setFill(Color.valueOf("white"));
                        Text idText = new Text(messageId);
                        idText.setFill(Color.valueOf("white"));
                        VBox heartsEmojis = new VBox();
                        ImageView imageView1 = new ImageView();
                        Image emojiHeart = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\heart.png");
                        imageView1.setImage(emojiHeart);
                        imageView1.setFitWidth(10);
                        imageView1.setFitHeight(10);
                        Text number1 = new Text(numberOfHearts);
                        number1.setFill(Color.valueOf("white"));
                        heartsEmojis.setSpacing(8.0);
                        heartsEmojis.getChildren().add(imageView1);
                        heartsEmojis.getChildren().add(number1);
                        VBox likesEmojis = new VBox();
                        ImageView imageView2 = new ImageView();
                        Image emojiLike = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\like.png");
                        imageView2.setImage(emojiLike);
                        imageView2.setFitWidth(10);
                        imageView2.setFitHeight(10);
                        Text number2 = new Text(numberOfLikes);
                        number2.setFill(Color.valueOf("white"));
                        likesEmojis.setSpacing(8.0);
                        likesEmojis.getChildren().add(imageView2);
                        likesEmojis.getChildren().add(number2);
                        VBox dislikesEmojis = new VBox();
                        ImageView imageView3 = new ImageView();
                        Image emojiDislike = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\dislike.png");
                        imageView3.setImage(emojiDislike);
                        imageView3.setFitWidth(10);
                        imageView3.setFitHeight(10);
                        Text number3 = new Text(numberOfDislikes);
                        number3.setFill(Color.valueOf("white"));
                        dislikesEmojis.setSpacing(8.0);
                        dislikesEmojis.getChildren().add(imageView3);
                        dislikesEmojis.getChildren().add(number3);
                        Button more = new Button("...");
                        more.setStyle("-fx-background-color:  transparent" + ";-fx-background-radius: 5Px" + ";-fx-text-fill: white");
                        more.setCursor(Cursor.cursor("HAND"));
                        Text text1 = new Text("                        ");
                        row.setSpacing(15.0);
                        row.getChildren().add(circle);
                        row.getChildren().add(column);
                        row.getChildren().add(dateText);
                        row.getChildren().add(idText);
                        row.getChildren().add(heartsEmojis);
                        row.getChildren().add(likesEmojis);
                        row.getChildren().add(dislikesEmojis);
                        row.getChildren().add(text1);
                        row.getChildren().add(more);
                        textChannelMessages.setSpacing(15.0);
                        textChannelMessages.getChildren().add(row);
                        row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
                            }
                        });
                        row.setOnMouseExited(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
                            }
                        });
                        more.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                moreMessagePane1.setVisible(true);
                                messageId1 = Integer.parseInt(messageId);
                            }
                        });
                    }
                }
            });
        }
        else {
            wrongCreateTextChannel.setVisible(true);
            createTextChannelField.clear();
        }
    }

    /**
     * Exit create text channel pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitCreateTextChannelPane(MouseEvent mouseEvent) {
        createTextChannelPane.setVisible(false);
    }

    /**
     * Exit create voice channel pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitCreateVoiceChannelPane(MouseEvent mouseEvent) {
        createVoiceChannelPane.setVisible(false);
    }

    /**
     * Create voice channel.
     *
     * @param mouseEvent the mouse event
     */
    public void createVoiceChannel(MouseEvent mouseEvent) {
        String voiceChannelName = createVoiceChannelField.getText();
        int i = 0;
        String text = serverNameButton.getText();
        while(!text.substring(i, i + 5).equals(": id ")){
            i++;
        }
        i += 5;
        int serverId = Integer.parseInt(text, i, text.length(), 10);
        if(client.getDataBase().createVoiceMessage(client.getUser().getId(), serverId, voiceChannelName)){
            client.outputClientDataBase();
            client.inputClientDataBase();
            successCreateVoiceChannel.setVisible(true);
            Text textChannels = new Text("TEXT / VOICE CHANNELS");
            textChannels.setFill(Color.valueOf("white"));
            HBox row = new HBox();
            ImageView imageView = new ImageView();
            Image textChannel = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\voice.png");
            imageView.setImage(textChannel);
            imageView.setFitWidth(12);
            imageView.setFitHeight(12);
            Text text1 = new Text(voiceChannelName);
            text1.setFill(Color.valueOf("white"));
            row.setSpacing(10.0);
            row.getChildren().add(imageView);
            row.getChildren().add(text1);
            serverChats.setSpacing(15.0);
            if(cnt == 0) {
                serverChats.getChildren().add(textChannels);
                cnt++;
            }
            serverChats.getChildren().add(row);
            row.setCursor(Cursor.cursor("HAND"));
            row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
                }
            });
            row.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
                }
            });
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    serverMessagesPane.setVisible(true);
                    serverAdminsPane.setVisible(true);
                }
            });
        }
        else {
            wrongCreateTextChannel.setVisible(true);
            createTextChannelField.clear();
        }
    }

    /**
     * Invite user.
     *
     * @param mouseEvent the mouse event
     */
    public void inviteUser(MouseEvent mouseEvent) {
        successInviteUser.setVisible(false);
        wrongInviteUser.setVisible(false);
        String userName = inviteUserField.getText();
        String text = textChannelTitle.getText();
        int i = 0;
        while(!text.substring(i, i + 5).equals(" id: ")){
            i++;
        }
        i += 5;
        int chatId = Integer.parseInt(text, i, text.length(), 10);
        int userId = client.getDataBase().determineId(userName);
        if(client.getDataBase().addUserChat(client.getUser().getId(), userId, chatId)){
            successInviteUser.setVisible(true);
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
        else {
            wrongInviteUser.setVisible(true);
        }
    }

    /**
     * Exit invite user pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitInviteUserPane(MouseEvent mouseEvent) {
        inviteUserPane.setVisible(false);
    }

    /**
     * Show create voice channel pane.
     *
     * @param mouseEvent the mouse event
     */
    public void showCreateVoiceChannelPane(MouseEvent mouseEvent) {
        createVoiceChannelPane.setVisible(true);
    }

    /**
     * Exit server setting pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitServerSettingPane(MouseEvent mouseEvent) {
        serverSettingPane.setVisible(false);
    }

    /**
     * Show create text channel pane.
     *
     * @param mouseEvent the mouse event
     */
    public void showCreateTextChannelPane(MouseEvent mouseEvent) {
        successCreateTextChannel.setVisible(false);
        wrongCreateTextChannel.setVisible(false);
        createTextChannelField.clear();
        createTextChannelPane.setVisible(true);
    }

    /**
     * Exit invite people pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitInvitePeoplePane(MouseEvent mouseEvent) {
        invitePeoplePane.setVisible(false);
    }

    /**
     * Invite people to server.
     *
     * @param mouseEvent the mouse event
     */
    public void invitePeopleToServer(MouseEvent mouseEvent) {
        successInvitePeople.setVisible(false);
        wrongInvitePeople.setVisible(false);
        String invitedPeople = invitePeopleField.getText();
        int i = 0;
        String text = serverNameButton.getText();
        while(!text.substring(i, i + 5).equals(": id ")){
            i++;
        }
        i += 5;
        int serverId = Integer.parseInt(text, i, text.length(), 10);
        int invitedPeopleId = client.getDataBase().determineId(invitedPeople);
        if(client.getDataBase().addAdminGroupServer(client.getUser().getId(), invitedPeopleId, serverId)){
            client.outputClientDataBase();
            client.inputClientDataBase();
            successInvitePeople.setVisible(true);
        }
        else {
            wrongInvitePeople.setVisible(true);
            invitePeopleField.clear();
        }
    }

    /**
     * Show invite people pane.
     *
     * @param mouseEvent the mouse event
     */
    public void showInvitePeoplePane(MouseEvent mouseEvent) {
        invitePeopleField.clear();
        successInvitePeople.setVisible(false);
        wrongInvitePeople.setVisible(false);
        invitePeoplePane.setVisible(true);
    }

    /**
     * The Message id 1.
     */
    int messageId1;

    /**
     * Refresh text channel.
     *
     * @param mouseEvent the mouse event
     */
    public void refreshTextChannel(MouseEvent mouseEvent) {
        String text = textChannelTitle.getText();
        int s = 0;
        while(!text.substring(s, s + 5).equals(" id: ")){
            s++;
        }
        s += 5;
        int chatId = Integer.parseInt(text, s, text.length(), 10);
        ArrayList<ChatMessage> messages = client.getDataBase().showMessagesChat(client.getUser().getId(), chatId);
        int i;
        textChannelMessages.getChildren().clear();
        wrongSendMessage.setVisible(false);
        wrongPin.setVisible(false);
        doesntBelong.setVisible(false);
        for(ChatMessage msg : messages){
            pin.setText("Pinned message id: " + msg.getChatMessageId());
            i = 0;
            HBox row = new HBox();
            String messageForm = msg.toString();
            while(i < messageForm.length() && messageForm.charAt(i) != ':'){
                i++;
            }
            String name = messageForm.substring(0, i);
            i += 2;
            int j = i;
            while(i < messageForm.length() && messageForm.charAt(i) != ':'){
                i++;
            }
            i--;
            String message = messageForm.substring(j, i);
            i += 3;
            j = i;
            while(i < messageForm.length() && messageForm.charAt(i) != 'i'){
                i++;
            }
            i--;
            String date = messageForm.substring(j, i);
            i += 5;
            j = i;
            while(i < messageForm.length() && messageForm.charAt(i) != '\n'){
                i++;
            }
            String messageId = messageForm.substring(j, i);
            i++;
            String numberOfLikes = "0";
            String numberOfDislikes = "0";
            String numberOfHearts = "0";
            if(i != messageForm.length()){
                if(msg.getLikes().size() != 0){
                    i += 2;
                    numberOfLikes = messageForm.substring(i, i + 1);
                    if(msg.getDislikes().size() != 0){
                        i += 3;
                        numberOfDislikes = messageForm.substring(i, i + 1);
                        if(msg.getHearts().size() != 0){
                            i += 2;
                            numberOfHearts = messageForm.substring(i, i + 1);
                        }
                    }
                    else {
                        if(msg.getHearts().size() != 0){
                            i += 2;
                            numberOfHearts = messageForm.substring(i, i + 1);
                        }
                    }
                }
                else {
                    if(msg.getDislikes().size() != 0){
                        i += 2;
                        numberOfDislikes = messageForm.substring(i, i + 1);
                        if(msg.getHearts().size() != 0){
                            i += 2;
                            numberOfHearts = messageForm.substring(i, i + 1);
                        }
                    }
                    else {
                        if(msg.getHearts().size() != 0){
                            i += 1;
                            numberOfHearts = messageForm.substring(i, i + 1);
                        }
                    }
                }
            }
            Circle circle = new Circle();
            circle.setStroke(Color.valueOf( "#23272A"));
            Image image1 = new Image(client.getDataBase().returnPictureAddress(name));
            circle.setFill(new ImagePattern(image1));
            circle.setRadius(18.0);
            VBox column = new VBox();
            Text nameText = new Text(name);
            nameText.setFill(Color.valueOf("white"));
            Text messageText = new Text(message);
            messageText.setFill(Color.valueOf("white"));
            column.setSpacing(8.0);
            column.getChildren().add(nameText);
            column.getChildren().add(messageText);
            Text dateText = new Text(date);
            dateText.setFill(Color.valueOf("white"));
            Text idText = new Text(messageId);
            idText.setFill(Color.valueOf("white"));
            VBox heartsEmojis = new VBox();
            ImageView imageView1 = new ImageView();
            Image emojiHeart = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\heart.png");
            imageView1.setImage(emojiHeart);
            imageView1.setFitWidth(10);
            imageView1.setFitHeight(10);
            Text number1 = new Text(numberOfHearts);
            number1.setFill(Color.valueOf("white"));
            heartsEmojis.setSpacing(8.0);
            heartsEmojis.getChildren().add(imageView1);
            heartsEmojis.getChildren().add(number1);
            VBox likesEmojis = new VBox();
            ImageView imageView2 = new ImageView();
            Image emojiLike = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\like.png");
            imageView2.setImage(emojiLike);
            imageView2.setFitWidth(10);
            imageView2.setFitHeight(10);
            Text number2 = new Text(numberOfLikes);
            number2.setFill(Color.valueOf("white"));
            likesEmojis.setSpacing(8.0);
            likesEmojis.getChildren().add(imageView2);
            likesEmojis.getChildren().add(number2);
            VBox dislikesEmojis = new VBox();
            ImageView imageView3 = new ImageView();
            Image emojiDislike = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\dislike.png");
            imageView3.setImage(emojiDislike);
            imageView3.setFitWidth(10);
            imageView3.setFitHeight(10);
            Text number3 = new Text(numberOfDislikes);
            number3.setFill(Color.valueOf("white"));
            dislikesEmojis.setSpacing(8.0);
            dislikesEmojis.getChildren().add(imageView3);
            dislikesEmojis.getChildren().add(number3);
            Button more = new Button("...");
            more.setStyle("-fx-background-color:  transparent" + ";-fx-background-radius: 5Px" + ";-fx-text-fill: white");
            more.setCursor(Cursor.cursor("HAND"));
            Text text1 = new Text("                        ");
            row.setSpacing(15.0);
            row.getChildren().add(circle);
            row.getChildren().add(column);
            row.getChildren().add(dateText);
            row.getChildren().add(idText);
            row.getChildren().add(heartsEmojis);
            row.getChildren().add(likesEmojis);
            row.getChildren().add(dislikesEmojis);
            row.getChildren().add(text1);
            row.getChildren().add(more);
            textChannelMessages.setSpacing(15.0);
            textChannelMessages.getChildren().add(row);
            row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
                }
            });
            row.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
                }
            });
            more.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    moreMessagePane1.setVisible(true);
                    messageId1 = Integer.parseInt(messageId);
                }
            });
        }
    }

    /**
     * Send message to text channel.
     *
     * @param mouseEvent the mouse event
     */
    public void sendMessageToTextChannel(MouseEvent mouseEvent) {
        wrongSendMessage.setVisible(false);
        wrongPin.setVisible(false);
        doesntBelong.setVisible(false);
        String message = textChannelMessageField.getText();
        if(!message.equals("") && message.trim().length() > 0) {
            String text = textChannelTitle.getText();
            int i = 0;
            while(!text.substring(i, i + 5).equals(" id: ")){
                i++;
            }
            i += 5;
            int chatId = Integer.parseInt(text, i, text.length(), 10);
            if(client.getDataBase().sendMessageToChat(client.getUser().getId(), chatId, message)) {
                client.outputClientDataBase();
                client.inputClientDataBase();
            }
            else {
                wrongSendMessage.setVisible(true);
            }
            textChannelMessageField.clear();
        }
    }

    /**
     * Send file to text channel.
     *
     * @param mouseEvent the mouse event
     */
    public void sendFileToTextChannel(MouseEvent mouseEvent) {

    }

    /**
     * Refresh server admins.
     *
     * @param mouseEvent the mouse event
     */
    public void refreshServerAdmins(MouseEvent mouseEvent) {
        int i = 0;
        String text = serverNameButton.getText();
        while(!text.substring(i, i + 5).equals(": id ")){
            i++;
        }
        i += 5;
        int serverId = Integer.parseInt(text, i, text.length(), 10);
        GroupServer groupServer = client.getDataBase().returnGroupServer(serverId);
        serverAdmins.getChildren().clear();
        for(Integer id : groupServer.getAdmins()){
            HBox row = new HBox();
            Circle circle = new Circle();
            circle.setStroke(Color.valueOf( "#23272A"));
            Image image = new Image(client.getDataBase().returnPictureAddress(client.getDataBase().getUser(id).getUsername()));
            circle.setFill(new ImagePattern(image));
            circle.setRadius(18.0);
            VBox column = new VBox();
            Text nameText = new Text(client.getDataBase().getUser(id).getUsername());
            nameText.setFill(Color.valueOf("white"));
            Text idText = new Text("" + id);
            idText.setFill(Color.valueOf("white"));
            column.setSpacing(8.0);
            column.getChildren().add(nameText);
            column.getChildren().add(idText);
            row.getChildren().add(circle);
            row.getChildren().add(column);
            serverAdmins.setSpacing(15.0);
            serverAdmins.getChildren().add(row);
        }
    }

    /**
     * Change color exit 2.
     *
     * @param mouseEvent the mouse event
     */
    public void changeColorExit2(MouseEvent mouseEvent) {
        ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:   #36393f" + ";-fx-text-fill: white");
    }

    /**
     * Refresh servers channels.
     *
     * @param mouseEvent the mouse event
     */
    public void refreshServersChannels(MouseEvent mouseEvent) {
        int i = 0;
        String text = serverNameButton.getText();
        while(!text.substring(i, i + 5).equals(": id ")){
            i++;
        }
        i += 5;
        int serverId = Integer.parseInt(text, i, text.length(), 10);
        ArrayList<Chat> chats = client.getDataBase().checkChats(serverId);
        serverChats.getChildren().clear();
        for(Chat chat : chats){
            Text textChannels = new Text("TEXT / VOICE CHANNELS");
            textChannels.setFill(Color.valueOf("white"));
            HBox row = new HBox();
            if(chat instanceof ChatMessages) {
                determine = 1;
                ImageView imageView = new ImageView();
                Image textChannel = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\hashtag.png");
                imageView.setImage(textChannel);
                imageView.setFitWidth(12);
                imageView.setFitHeight(12);
                Text text1 = new Text(chat.getName());
                text1.setFill(Color.valueOf("white"));
                row.setSpacing(10.0);
                row.getChildren().add(imageView);
                row.getChildren().add(text1);
                serverChats.setSpacing(15.0);
                if (cnt == 0) {
                    serverChats.getChildren().add(textChannels);
                    cnt++;
                }
                serverChats.getChildren().add(row);
            }
            else {
                ImageView imageView = new ImageView();
                Image textChannel = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\voice.png");
                imageView.setImage(textChannel);
                imageView.setFitWidth(12);
                imageView.setFitHeight(12);
                Text text1 = new Text(chat.getName());
                text1.setFill(Color.valueOf("white"));
                row.setSpacing(10.0);
                row.getChildren().add(imageView);
                row.getChildren().add(text1);
                serverChats.setSpacing(15.0);
                if(cnt == 0) {
                    serverChats.getChildren().add(textChannels);
                    cnt++;
                }
                serverChats.getChildren().add(row);
            }
            row.setCursor(Cursor.cursor("HAND"));
            row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
                }
            });
            row.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
                }
            });
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(determine == 1) {
                        textChannelTitle.setText("Welcome to " + chat.getName() + " id: " + chat.getId());
                    }
                    else {

                    }
                    serverMessagesPane.setVisible(true);
                    serverAdminsPane.setVisible(true);
                }
            });
        }
    }

    /**
     * Show invite user pane.
     *
     * @param mouseEvent the mouse event
     */
    public void showInviteUserPane(MouseEvent mouseEvent) {
        successInviteUser.setVisible(false);
        wrongInviteUser.setVisible(false);
        inviteUserField.clear();
        inviteUserPane.setVisible(true);
    }

    /**
     * Show invite admin pane.
     *
     * @param mouseEvent the mouse event
     */
    public void showInviteAdminPane(MouseEvent mouseEvent) {
        succressInviteAdmin.setVisible(false);
        wrongInviteAdmin.setVisible(false);
        inviteAdminField.clear();
        inviteAdminPane.setVisible(true);
    }

    /**
     * Exit invite admin or user to chat.
     *
     * @param mouseEvent the mouse event
     */
    public void exitInviteAdminOrUserToChat(MouseEvent mouseEvent) {
        inviteAdminOrUserToChat.setVisible(false);
    }

    /**
     * Show invite user or admin pane.
     *
     * @param mouseEvent the mouse event
     */
    public void showInviteUserOrAdminPane(MouseEvent mouseEvent) {
        inviteAdminOrUserToChat.setVisible(true);
    }

    /**
     * Refresh admins and users.
     *
     * @param mouseEvent the mouse event
     */
    public void refreshAdminsAndUsers(MouseEvent mouseEvent) {
        String text = textChannelTitle.getText();
        int i = 0;
        while(i < text.length() && !text.substring(i, i + 5).equals(" id: ")){
            i++;
        }
        i += 5;
        int chatId = Integer.parseInt(text, i, text.length(), 10);
        ArrayList<User> users = client.getDataBase().returnUsers(chatId);
        textChannelUsers.getChildren().clear();
        for(User u : users){
            HBox row = new HBox();
            Circle circle = new Circle();
            circle.setStroke(Color.valueOf( "#23272A"));
            Image image1 = new Image(client.getDataBase().returnPictureAddress(u.getUsername()));
            circle.setFill(new ImagePattern(image1));
            circle.setRadius(18.0);
            VBox column = new VBox();
            Text name = new Text(u.getUsername());
            name.setFill(Color.valueOf("white"));
            Text id = new Text("" + u.getId());
            id.setFill(Color.valueOf("white"));
            column.setSpacing(8.0);
            column.getChildren().add(name);
            column.getChildren().add(id);
            row.setSpacing(15.0);
            row.getChildren().add(circle);
            row.getChildren().add(column);
            textChannelUsers.setSpacing(15.0);
            textChannelUsers.getChildren().add(row);
        }
        ArrayList<User> admins = client.getDataBase().returnAdmins(chatId);
        textChannelAdmins.getChildren().clear();
        for(User u : admins){
            HBox row = new HBox();
            Text text1 = new Text("ADMINS");
            text1.setFill(Color.valueOf("white"));
            Circle circle = new Circle();
            circle.setStroke(Color.valueOf( "#23272A"));
            Image image1 = new Image(client.getDataBase().returnPictureAddress(u.getUsername()));
            circle.setFill(new ImagePattern(image1));
            circle.setRadius(18.0);
            VBox column = new VBox();
            Text name = new Text(u.getUsername());
            name.setFill(Color.valueOf("white"));
            Text id = new Text("" + u.getId());
            id.setFill(Color.valueOf("white"));
            column.setSpacing(8.0);
            column.getChildren().add(name);
            column.getChildren().add(id);
            row.setSpacing(15.0);
            row.getChildren().add(circle);
            row.getChildren().add(column);
            textChannelAdmins.setSpacing(15.0);
            textChannelAdmins.getChildren().add(row);
        }
    }

    /**
     * Reaction message text.
     *
     * @param mouseEvent the mouse event
     */
    public void reactionMessageText(MouseEvent mouseEvent) {
        reactionPane1.setVisible(true);
    }

    /**
     * Pin message text.
     *
     * @param mouseEvent the mouse event
     */
    public void pinMessageText(MouseEvent mouseEvent) {
        String text = textChannelTitle.getText();
        int i = 0;
        while(!text.substring(i, i + 5).equals(" id: ")){
            i++;
        }
        i += 5;
        int chatId = Integer.parseInt(text, i, text.length(), 10);
        if(client.getDataBase().pinMessageChat(client.getUser().getId(), chatId, messageId1)){
            client.outputClientDataBase();
            client.inputClientDataBase();
            textChannelPinMessage.setText("Pinned message id: " + messageId1);
        }
        else {
            wrongPin.setVisible(true);
        }
    }

    /**
     * Edit message text.
     *
     * @param mouseEvent the mouse event
     */
    public void editMessageText(MouseEvent mouseEvent) {
        editMessagePane1.setVisible(true);
    }

    /**
     * Delete message text.
     *
     * @param mouseEvent the mouse event
     */
    public void deleteMessageText(MouseEvent mouseEvent) {
        String text = textChannelTitle.getText();
        int i = 0;
        while(!text.substring(i, i + 5).equals(" id: ")){
            i++;
        }
        i += 5;
        int chatId = Integer.parseInt(text, i, text.length(), 10);
        if(client.getDataBase().deleteMessageChat(client.getUser().getId(), chatId, messageId1)) {
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
        else {
            doesntBelong.setVisible(true);
        }
    }

    /**
     * Exit more text pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitMoreTextPane(MouseEvent mouseEvent) {
        moreMessagePane1.setVisible(false);
    }

    /**
     * Add heart text.
     *
     * @param mouseEvent the mouse event
     */
    public void addHeartText(MouseEvent mouseEvent) {
        String reaction = "heart";
        String text = textChannelTitle.getText();
        int i = 0;
        while(!text.substring(i, i + 5).equals(" id: ")){
            i++;
        }
        i += 5;
        int chatId = Integer.parseInt(text, i, text.length(), 10);
        if(client.getDataBase().reactionMessageChat(client.getUser().getId(), chatId, messageId1, reaction)) {
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
    }

    /**
     * Add like text.
     *
     * @param mouseEvent the mouse event
     */
    public void addLikeText(MouseEvent mouseEvent) {
        String reaction = "like";
        String text = textChannelTitle.getText();
        int i = 0;
        while(!text.substring(i, i + 5).equals(" id: ")){
            i++;
        }
        i += 5;
        int chatId = Integer.parseInt(text, i, text.length(), 10);
        if(client.getDataBase().reactionMessageChat(client.getUser().getId(), chatId, messageId1, reaction)) {
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
    }

    /**
     * Add dislike text.
     *
     * @param mouseEvent the mouse event
     */
    public void addDislikeText(MouseEvent mouseEvent) {
        String reaction = "dislike";
        String text = textChannelTitle.getText();
        int i = 0;
        while(!text.substring(i, i + 5).equals(" id: ")){
            i++;
        }
        i += 5;
        int chatId = Integer.parseInt(text, i, text.length(), 10);
        if(client.getDataBase().reactionMessageChat(client.getUser().getId(), chatId, messageId1, reaction)) {
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
    }

    /**
     * Exit reaction text pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitReactionTextPane(MouseEvent mouseEvent) {
        reactionPane1.setVisible(false);
    }

    /**
     * Edit selected message text.
     *
     * @param mouseEvent the mouse event
     */
    public void editSelectedMessageText(MouseEvent mouseEvent) {
        String editedMessage = editedMessageField1.getText();
        editedMessageField1.clear();
        String text = textChannelTitle.getText();
        int i = 0;
        while(!text.substring(i, i + 5).equals(" id: ")){
            i++;
        }
        i += 5;
        int chatId = Integer.parseInt(text, i, text.length(), 10);
        if(client.getDataBase().editMessageChat(client.getUser().getId(), chatId, messageId1, editedMessage)) {
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
        else {
            doesntBelong.setVisible(true);
        }
    }

    /**
     * Exit edit message text pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitEditMessageTextPane(MouseEvent mouseEvent) {
        editMessagePane1.setVisible(false);
    }

    /**
     * Refresh servers.
     *
     * @param mouseEvent the mouse event
     */
    public void refreshServers(MouseEvent mouseEvent) {
        ArrayList<GroupServer> groupServers = client.getDataBase().checkServers(client.getUser().getId());
        serverIcons.getChildren().clear();
        for(GroupServer groupServer : groupServers){
            Button server = new Button(groupServer.getName());
            server.setStyle("-fx-background-color:   #23272A" + ";-fx-background-radius: 5Px" + ";-fx-text-fill: white");
            server.setCursor(Cursor.cursor("HAND"));
            serverIcons.setSpacing(15.0);
            serverIcons.getChildren().add(server);
            server.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:   #206694");
                }
            });
            server.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent" + ";-fx-text-fill: white");
                }
            });
            server.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    DirectMessagesColumnPane.setVisible(false);
                    friendsInfo.setVisible(false);
                    FriendsMainPane.setVisible(false);
                    textChannelAdminsPane.setVisible(false);
                    textChannelUsersPane.setVisible(false);
                    ServerChatsPane.setVisible(true);
                    serverMessagesPane.setVisible(true);
                    serverAdminsPane.setVisible(true);
                    serverNameButton.setText(groupServer.getName() + ": id " + groupServer.getId());
                    ArrayList<Chat> chats = client.getDataBase().checkChats(groupServer.getId());
                    serverChats.getChildren().clear();
                    for(Chat chat : chats){
                        Text textChannels = new Text("TEXT / VOICE CHANNELS");
                        textChannels.setFill(Color.valueOf("white"));
                        HBox row = new HBox();
                        if(chat instanceof ChatMessages) {
                            determine = 1;
                            ImageView imageView = new ImageView();
                            Image textChannel = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\hashtag.png");
                            imageView.setImage(textChannel);
                            imageView.setFitWidth(12);
                            imageView.setFitHeight(12);
                            Text text1 = new Text(chat.getName());
                            text1.setFill(Color.valueOf("white"));
                            row.setSpacing(10.0);
                            row.getChildren().add(imageView);
                            row.getChildren().add(text1);
                            serverChats.setSpacing(15.0);
                            if (cnt == 0) {
                                serverChats.getChildren().add(textChannels);
                                cnt++;
                            }
                            serverChats.getChildren().add(row);
                        }
                        else {
                            ImageView imageView = new ImageView();
                            Image textChannel = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\voice.png");
                            imageView.setImage(textChannel);
                            imageView.setFitWidth(12);
                            imageView.setFitHeight(12);
                            Text text1 = new Text(chat.getName());
                            text1.setFill(Color.valueOf("white"));
                            row.setSpacing(10.0);
                            row.getChildren().add(imageView);
                            row.getChildren().add(text1);
                            serverChats.setSpacing(15.0);
                            if(cnt == 0) {
                                serverChats.getChildren().add(textChannels);
                                cnt++;
                            }
                            serverChats.getChildren().add(row);
                        }
                        row.setCursor(Cursor.cursor("HAND"));
                        row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
                            }
                        });
                        row.setOnMouseExited(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
                            }
                        });
                        row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                if(determine == 1) {
                                    textChannelTitle.setText("Welcome to " + chat.getName() + " id: " + chat.getId());
                                    serverAdminsPane.setVisible(false);
                                    textChannelUsersPane.setVisible(true);
                                    textChannelAdminsPane.setVisible(true);
                                    String text = textChannelTitle.getText();
                                    int s = 0;
                                    while(!text.substring(s, s + 5).equals(" id: ")){
                                        s++;
                                    }
                                    s += 5;
                                    int chatId = Integer.parseInt(text, s, text.length(), 10);
                                    ArrayList<ChatMessage> messages = client.getDataBase().showMessagesChat(client.getUser().getId(), chatId);
                                    int i;
                                    textChannelMessages.getChildren().clear();
                                    wrongSendMessage.setVisible(false);
                                    wrongPin.setVisible(false);
                                    doesntBelong.setVisible(false);
                                    for(ChatMessage msg : messages){
                                        pin.setText("Pinned message id: " + msg.getChatMessageId());
                                        i = 0;
                                        HBox row = new HBox();
                                        String messageForm = msg.toString();
                                        while(i < messageForm.length() && messageForm.charAt(i) != ':'){
                                            i++;
                                        }
                                        String name = messageForm.substring(0, i);
                                        i += 2;
                                        int j = i;
                                        while(i < messageForm.length() && messageForm.charAt(i) != ':'){
                                            i++;
                                        }
                                        i--;
                                        String message = messageForm.substring(j, i);
                                        i += 3;
                                        j = i;
                                        while(i < messageForm.length() && messageForm.charAt(i) != 'i'){
                                            i++;
                                        }
                                        i--;
                                        String date = messageForm.substring(j, i);
                                        i += 5;
                                        j = i;
                                        while(i < messageForm.length() && messageForm.charAt(i) != '\n'){
                                            i++;
                                        }
                                        String messageId = messageForm.substring(j, i);
                                        i++;
                                        String numberOfLikes = "0";
                                        String numberOfDislikes = "0";
                                        String numberOfHearts = "0";
                                        if(i != messageForm.length()){
                                            if(msg.getLikes().size() != 0){
                                                i += 2;
                                                numberOfLikes = messageForm.substring(i, i + 1);
                                                if(msg.getDislikes().size() != 0){
                                                    i += 3;
                                                    numberOfDislikes = messageForm.substring(i, i + 1);
                                                    if(msg.getHearts().size() != 0){
                                                        i += 2;
                                                        numberOfHearts = messageForm.substring(i, i + 1);
                                                    }
                                                }
                                                else {
                                                    if(msg.getHearts().size() != 0){
                                                        i += 2;
                                                        numberOfHearts = messageForm.substring(i, i + 1);
                                                    }
                                                }
                                            }
                                            else {
                                                if(msg.getDislikes().size() != 0){
                                                    i += 2;
                                                    numberOfDislikes = messageForm.substring(i, i + 1);
                                                    if(msg.getHearts().size() != 0){
                                                        i += 2;
                                                        numberOfHearts = messageForm.substring(i, i + 1);
                                                    }
                                                }
                                                else {
                                                    if(msg.getHearts().size() != 0){
                                                        i += 1;
                                                        numberOfHearts = messageForm.substring(i, i + 1);
                                                    }
                                                }
                                            }
                                        }
                                        Circle circle = new Circle();
                                        circle.setStroke(Color.valueOf( "#23272A"));
                                        Image image1 = new Image(client.getDataBase().returnPictureAddress(name));
                                        circle.setFill(new ImagePattern(image1));
                                        circle.setRadius(18.0);
                                        VBox column = new VBox();
                                        Text nameText = new Text(name);
                                        nameText.setFill(Color.valueOf("white"));
                                        Text messageText = new Text(message);
                                        messageText.setFill(Color.valueOf("white"));
                                        column.setSpacing(8.0);
                                        column.getChildren().add(nameText);
                                        column.getChildren().add(messageText);
                                        Text dateText = new Text(date);
                                        dateText.setFill(Color.valueOf("white"));
                                        Text idText = new Text(messageId);
                                        idText.setFill(Color.valueOf("white"));
                                        VBox heartsEmojis = new VBox();
                                        ImageView imageView1 = new ImageView();
                                        Image emojiHeart = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\heart.png");
                                        imageView1.setImage(emojiHeart);
                                        imageView1.setFitWidth(10);
                                        imageView1.setFitHeight(10);
                                        Text number1 = new Text(numberOfHearts);
                                        number1.setFill(Color.valueOf("white"));
                                        heartsEmojis.setSpacing(8.0);
                                        heartsEmojis.getChildren().add(imageView1);
                                        heartsEmojis.getChildren().add(number1);
                                        VBox likesEmojis = new VBox();
                                        ImageView imageView2 = new ImageView();
                                        Image emojiLike = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\like.png");
                                        imageView2.setImage(emojiLike);
                                        imageView2.setFitWidth(10);
                                        imageView2.setFitHeight(10);
                                        Text number2 = new Text(numberOfLikes);
                                        number2.setFill(Color.valueOf("white"));
                                        likesEmojis.setSpacing(8.0);
                                        likesEmojis.getChildren().add(imageView2);
                                        likesEmojis.getChildren().add(number2);
                                        VBox dislikesEmojis = new VBox();
                                        ImageView imageView3 = new ImageView();
                                        Image emojiDislike = new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\dislike.png");
                                        imageView3.setImage(emojiDislike);
                                        imageView3.setFitWidth(10);
                                        imageView3.setFitHeight(10);
                                        Text number3 = new Text(numberOfDislikes);
                                        number3.setFill(Color.valueOf("white"));
                                        dislikesEmojis.setSpacing(8.0);
                                        dislikesEmojis.getChildren().add(imageView3);
                                        dislikesEmojis.getChildren().add(number3);
                                        Button more = new Button("...");
                                        more.setStyle("-fx-background-color:  transparent" + ";-fx-background-radius: 5Px" + ";-fx-text-fill: white");
                                        more.setCursor(Cursor.cursor("HAND"));
                                        Text text1 = new Text("                        ");
                                        row.setSpacing(15.0);
                                        row.getChildren().add(circle);
                                        row.getChildren().add(column);
                                        row.getChildren().add(dateText);
                                        row.getChildren().add(idText);
                                        row.getChildren().add(heartsEmojis);
                                        row.getChildren().add(likesEmojis);
                                        row.getChildren().add(dislikesEmojis);
                                        row.getChildren().add(text1);
                                        row.getChildren().add(more);
                                        textChannelMessages.setSpacing(15.0);
                                        textChannelMessages.getChildren().add(row);
                                        row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent mouseEvent) {
                                                ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:    #23272A");
                                            }
                                        });
                                        row.setOnMouseExited(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent mouseEvent) {
                                                ((HBox)mouseEvent.getSource()).setStyle("-fx-background-color:  transparent");
                                            }
                                        });
                                        more.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent mouseEvent) {
                                                moreMessagePane1.setVisible(true);
                                                messageId1 = Integer.parseInt(messageId);
                                            }
                                        });
                                    }
                                    serverMessagesPane.setVisible(true);
                                }
                                else {
                                    serverAdminsPane.setVisible(true);
                                    textChannelUsersPane.setVisible(false);
                                    textChannelAdminsPane.setVisible(false);
                                    VoiceChannelPane.setVisible(true);

                                }
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     * Remove from voice channel.
     *
     * @param mouseEvent the mouse event
     */
    public void removeFromVoiceChannel(MouseEvent mouseEvent) {

    }

    public void finalChangeColor(MouseEvent mouseEvent) {
        ((Button)mouseEvent.getSource()).setStyle("-fx-background-color:   #23272A");
    }
}