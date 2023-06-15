package com.example.calculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * The type Discord setting.
 */
public class DiscordSetting {

    /**
     * The Client.
     */
    Client client;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private AnchorPane changePasswordPane;

    @FXML
    private AnchorPane changeUsernamePane;

    @FXML
    private PasswordField confirmNewPassword;

    @FXML
    private PasswordField currentPassword;

    @FXML
    private Text emailText;

    @FXML
    private Text emptyNewPassword;

    @FXML
    private Text emailWithoutDomain;

    @FXML
    private Text emailWithoutUsername;

    @FXML
    private Text emptyEmail;

    @FXML
    private TextField changeEmailField;

    @FXML
    private AnchorPane changeEmailPane;

    @FXML
    private Text invalidPassword;

    @FXML
    private Text longerPassword;

    @FXML
    private Text longerUsername;

    @FXML
    private Circle myImage;

    @FXML
    private PasswordField newPassword;

    @FXML
    private Text newPasswordWithoutCapitalLetter;

    @FXML
    private Text newPasswordWithoutNumber;

    @FXML
    private Text newPasswordWithoutSmallLetter;

    @FXML
    private Text phoneNumberText;

    @FXML
    private Text repeatedUsername;

    @FXML
    private Text usernameField;

    @FXML
    private Text usernameText;

    @FXML
    private Text wrongConfirmPassword;

    @FXML
    private Text wrongUsername;

    @FXML
    private TextField changeUsernameField;

    @FXML
    private Button ChangePasswordButton;

    @FXML
    private Text PasswordAndAuthentication;

    @FXML
    private ImageView escImage;

    @FXML
    private Text escText;

    @FXML
    private Line line;

    @FXML
    private Text emptyUsername;

    @FXML
    private TextField changePhoneNumberField;

    @FXML
    private AnchorPane changePhoneNumberPane;

    @FXML
    private Text wrongPhoneNumber;

    /**
     * Set client.
     *
     * @param client the client
     */
    public void setClient (Client client){
        this.client = client;
        usernameField.setText(client.getUser().getUsername());
        myImage.setStroke(Color.valueOf( "#23272A"));
        Image image = new Image(client.getUser().getPictureAddress());
        myImage.setFill(new ImagePattern(image));
        usernameText.setText(client.getUser().getUsername());
        emailText.setText(client.getUser().getEmail());
        if(client.getUser().getPhoneNumber() == null){
            phoneNumberText.setText("You haven't added a phone number yet.");
        }
        else {
            phoneNumberText.setText(client.getUser().getPhoneNumber());
        }
    }

    /**
     * Change email.
     *
     * @param event the event
     */
    @FXML
    void changeEmail(MouseEvent event) {
        emptyEmail.setVisible(false);
        emailWithoutUsername.setVisible(false);
        emailWithoutDomain.setVisible(false);
        String email = changeEmailField.getText();
        if (client.getUser().setEmail(email) == 0) {
            emptyEmail.setVisible(true);
            changeEmailField.clear();
        } else if (client.getUser().setEmail(email) == 1) {
            emailWithoutUsername.setVisible(true);
            changeEmailField.clear();
            ;
        } else if (client.getUser().setEmail(email) == 2) {
            emailWithoutDomain.setVisible(true);
            changeEmailField.clear();
        } else {
            client.getUser().setEmail(email);
            client.getDataBase().updateUser(client.getUser());
            client.outputClientDataBase();
            client.inputClientDataBase();
            PasswordAndAuthentication.setVisible(true);
            ChangePasswordButton.setVisible(true);
            escImage.setVisible(true);
            escText.setVisible(true);
            line.setVisible(true);
            emailText.setText(client.getUser().getEmail());
            changeEmailPane.setVisible(false);
        }
    }

    /**
     * Exit change email pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitChangeEmailPane(MouseEvent mouseEvent) {
        PasswordAndAuthentication.setVisible(true);
        ChangePasswordButton.setVisible(true);
        escImage.setVisible(true);
        escText.setVisible(true);
        line.setVisible(true);
        changeEmailPane.setVisible(false);
    }

    /**
     * Change picture.
     *
     * @param event the event
     */
    @FXML
    void changePicture(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose picture");
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            client.getUser().setPictureAddress(file.getAbsolutePath());
            Image image = new Image(client.getUser().getPictureAddress());
            myImage.setFill(new ImagePattern(image));
            client.getDataBase().updateUser(client.getUser());
            client.outputClientDataBase();
            client.inputClientDataBase();
        }
    }

    /**
     * Close change password pane.
     *
     * @param event the event
     */
    @FXML
    void closeChangePasswordPane(MouseEvent event) {
        changePasswordPane.setVisible(false);
    }

    /**
     * Edit email.
     *
     * @param event the event
     */
    @FXML
    void editEmail(MouseEvent event) {
        PasswordAndAuthentication.setVisible(false);
        ChangePasswordButton.setVisible(false);
        escImage.setVisible(false);
        escText.setVisible(false);
        line.setVisible(false);
        changeEmailPane.setVisible(true);
    }

    /**
     * Edit password.
     *
     * @param event the event
     */
    @FXML
    void editPassword(MouseEvent event) {
        changePasswordPane.setVisible(true);
    }

    /**
     * Change password.
     *
     * @param event the event
     */
    @FXML
    void changePassword(MouseEvent event) {
        invalidPassword.setVisible(false);
        wrongConfirmPassword.setVisible(false);
        newPasswordWithoutCapitalLetter.setVisible(false);
        newPasswordWithoutNumber.setVisible(false);
        newPasswordWithoutSmallLetter.setVisible(false);
        emptyNewPassword.setVisible(false);
        String password = currentPassword.getText();
        if(password.equals(client.getUser().getPassword())){
            String passwordNew = newPassword.getText();
            if (client.getUser().setPassword(passwordNew) == 0) {
                emptyNewPassword.setVisible(true);
                newPassword.clear();
                confirmNewPassword.clear();
                currentPassword.clear();
            } else if (client.getUser().setPassword(passwordNew) == 1) {
                longerPassword.setVisible(true);
                newPassword.clear();
                confirmNewPassword.clear();
                currentPassword.clear();
            } else if (client.getUser().setPassword(passwordNew) == 2) {
                newPasswordWithoutNumber.setVisible(true);
                newPassword.clear();
                confirmNewPassword.clear();
                currentPassword.clear();
            } else if (client.getUser().setPassword(passwordNew) == 3) {
                newPasswordWithoutCapitalLetter.setVisible(true);
                newPassword.clear();
                confirmNewPassword.clear();
                currentPassword.clear();
            } else if (client.getUser().setPassword(passwordNew) == 4) {
                newPasswordWithoutSmallLetter.setVisible(true);
                newPassword.clear();
                confirmNewPassword.clear();
                currentPassword.clear();
            } else {
                String confirmPassword = confirmNewPassword.getText();
                if(!confirmPassword.equals(passwordNew)){
                    wrongConfirmPassword.setVisible(true);
                    newPassword.clear();
                    confirmNewPassword.clear();
                    currentPassword.clear();
                }
                else {
                    client.getUser().setPassword(passwordNew);
                    client.getDataBase().updateUser(client.getUser());
                    client.outputClientDataBase();
                    client.inputClientDataBase();
                    changePasswordPane.setVisible(false);
                }
            }
        }
        else {
            invalidPassword.setVisible(true);
            newPassword.clear();
            confirmNewPassword.clear();
            currentPassword.clear();
        }
    }

    /**
     * Edit phone number.
     *
     * @param event the event
     */
    @FXML
    void editPhoneNumber(MouseEvent event) {
        PasswordAndAuthentication.setVisible(false);
        ChangePasswordButton.setVisible(false);
        escImage.setVisible(false);
        escText.setVisible(false);
        line.setVisible(false);
        changePhoneNumberPane.setVisible(true);
    }

    /**
     * Exit change username pane.
     *
     * @param event the event
     */
    @FXML
    void exitChangeUsernamePane(MouseEvent event) {
        PasswordAndAuthentication.setVisible(true);
        ChangePasswordButton.setVisible(true);
        escImage.setVisible(true);
        escText.setVisible(true);
        line.setVisible(true);
        changeUsernamePane.setVisible(false);
    }

    /**
     * Change username.
     *
     * @param event the event
     */
    @FXML
    void changeUsername(MouseEvent event) {
        User user = new User();
        repeatedUsername.setVisible(false);
        wrongUsername.setVisible(false);
        longerUsername.setVisible(false);
        emptyUsername.setVisible(false);
        String username = changeUsernameField.getText();
        user.setUsername(username);
        if(client.getUser().setUsername(username) == 0){
            emptyUsername.setVisible(true);
            changeUsernameField.clear();
        }
        else if(client.getUser().setUsername(username) == 1){
            longerUsername.setVisible(true);
            changeUsernameField.clear();
        }
        else if(client.getUser().setUsername(username) == 2){
            wrongUsername.setVisible(true);
            changeUsernameField.clear();
        }
        else if(!client.getDataBase().signup(user)){
            repeatedUsername.setVisible(true);
            changeUsernameField.clear();
        }
        else {
            client.getDataBase().deleteUser(user);
            client.getUser().setUsername(username);
            client.getDataBase().updateUser(client.getUser());
            client.outputClientDataBase();
            client.inputClientDataBase();
            PasswordAndAuthentication.setVisible(true);
            ChangePasswordButton.setVisible(true);
            escImage.setVisible(true);
            escText.setVisible(true);
            line.setVisible(true);
            usernameText.setText(client.getUser().getUsername());
            changeUsernamePane.setVisible(false);
        }
    }

    /**
     * Edit username.
     *
     * @param event the event
     */
    @FXML
    void editUsername(MouseEvent event) {
        PasswordAndAuthentication.setVisible(false);
        ChangePasswordButton.setVisible(false);
        escImage.setVisible(false);
        escText.setVisible(false);
        line.setVisible(false);
        changeUsernamePane.setVisible(true);
    }

    /**
     * Exit.
     *
     * @param event the event
     */
    @FXML
    void exit(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DiscordMainPage.fxml"));
        try {
            Parent root = loader.load();
            DiscordMainPage discordMainPage  = loader.getController();
            discordMainPage.setClient(client);
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
     * Change phone number.
     *
     * @param mouseEvent the mouse event
     */
    public void changePhoneNumber(MouseEvent mouseEvent) {
        wrongPhoneNumber.setVisible(false);
        String phoneNumber = changePhoneNumberField.getText();
        if(client.getUser().setPhoneNumber(phoneNumber)){
            client.getUser().setPhoneNumber(phoneNumber);
            client.getDataBase().updateUser(client.getUser());
            client.outputClientDataBase();
            client.inputClientDataBase();
            PasswordAndAuthentication.setVisible(true);
            ChangePasswordButton.setVisible(true);
            escImage.setVisible(true);
            escText.setVisible(true);
            line.setVisible(true);
            if(client.getUser().getPhoneNumber() == null || client.getUser().getPhoneNumber().equals("")){
                phoneNumberText.setText("You haven't added a phone number yet.");
            }
            else {
                phoneNumberText.setText(client.getUser().getPhoneNumber());
            }
            changePhoneNumberPane.setVisible(false);
        }
        else {
            wrongPhoneNumber.setVisible(true);
            changePhoneNumberField.clear();
        }
    }

    /**
     * Exit change phone number pane.
     *
     * @param mouseEvent the mouse event
     */
    public void exitChangePhoneNumberPane(MouseEvent mouseEvent) {
        PasswordAndAuthentication.setVisible(true);
        ChangePasswordButton.setVisible(true);
        escImage.setVisible(true);
        escText.setVisible(true);
        line.setVisible(true);
        changePhoneNumberPane.setVisible(false);
    }
}
