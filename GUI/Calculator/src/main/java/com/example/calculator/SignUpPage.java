package com.example.calculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Sign up page.
 */
public class SignUpPage {

    @FXML
    private Text WrongEmailDomain;

    @FXML
    private AnchorPane backGround;

    @FXML
    private PasswordField confirmField;

    @FXML
    private TextField confirmTextField;

    @FXML
    private TextField emailField;

    @FXML
    private Text emailWithoutUsername;

    @FXML
    private Text emptyEmail;

    @FXML
    private Text emptyPassword;

    @FXML
    private Text emptyUsername;

    @FXML
    private Text emptyConfirm;

    @FXML
    private ImageView invisible1;

    @FXML
    private ImageView invisible2;

    @FXML
    private Text longerPassword;

    @FXML
    private Text longerUsername;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Text passwordWithoutCapitalLetter;

    @FXML
    private Text passwordWithoutDigit;

    @FXML
    private Text passwordWithoutSmallLetter;

    @FXML
    private Text repeatedUsername;

    @FXML
    private TextField usernameField;

    @FXML
    private ImageView visible1;

    @FXML
    private ImageView visible2;

    @FXML
    private Text wrongConfirmPassword;

    @FXML
    private Text wrongUsernameCharacters;


    /**
     * The Client.
     */
    Client client;

    /**
     * Set client.
     *
     * @param client the client
     */
    public void setClient (Client client){
        this.client = client;
    }

    /**
     * Call sign in.
     *
     * @param event the event
     */
    @FXML
    void callSignIn(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        try {
            Parent root = loader.load();
            HelloController signInPage = loader.getController();
            signInPage.setClient(client);
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
     * Sign up.
     *
     * @param mouseEvent the mouse event
     */
    public void SignUp(MouseEvent mouseEvent) {
        emptyUsername.setVisible(false);
        longerUsername.setVisible(false);
        wrongUsernameCharacters.setVisible(false);
        repeatedUsername.setVisible(false);
        emptyPassword.setVisible(false);
        longerPassword.setVisible(false);
        passwordWithoutDigit.setVisible(false);
        passwordWithoutCapitalLetter.setVisible(false);
        passwordWithoutSmallLetter.setVisible(false);
        emptyEmail.setVisible(false);
        emailWithoutUsername.setVisible(false);
        WrongEmailDomain.setVisible(false);
        wrongConfirmPassword.setVisible(false);
        emptyConfirm.setVisible(false);
        User user = new User();
        String username = usernameField.getText();
        user.setUsername(username);
        if(client.getUser().setUsername(username) == 0){
            emptyUsername.setVisible(true);
            usernameField.clear();
        }
        else if(client.getUser().setUsername(username) == 1){
            longerUsername.setVisible(true);
            usernameField.clear();
        }
        else if(client.getUser().setUsername(username) == 2){
            wrongUsernameCharacters.setVisible(true);
            usernameField.clear();
        }
        else if(!client.getDataBase().signup(user)){
            repeatedUsername.setVisible(true);
            usernameField.clear();
        }
        else {
            client.getDataBase().deleteUser(user);
            String password;
            if (cnt1 % 2 != 0) {
                password = passwordTextField.getText();
            }
            else {
                password = passwordField.getText();
            }
            if (client.getUser().setPassword(password) == 0) {
                emptyPassword.setVisible(true);
                passwordTextField.clear();
                passwordField.clear();
            } else if (client.getUser().setPassword(password) == 1) {
                longerPassword.setVisible(true);
                passwordTextField.clear();
                passwordField.clear();
            } else if (client.getUser().setPassword(password) == 2) {
                passwordWithoutDigit.setVisible(true);
                passwordTextField.clear();
                passwordField.clear();
            } else if (client.getUser().setPassword(password) == 3) {
                passwordWithoutCapitalLetter.setVisible(true);
                passwordTextField.clear();
                passwordField.clear();
            } else if (client.getUser().setPassword(password) == 4) {
                passwordWithoutSmallLetter.setVisible(true);
                passwordTextField.clear();
                passwordField.clear();
            }
            else {
                String confirmPassword;
                if(cnt2 % 2 != 0){
                    confirmPassword = confirmTextField.getText();
                }
                else {
                    confirmPassword = confirmField.getText();
                }
                if (!confirmPassword.equals(password)) {
                    wrongConfirmPassword.setVisible(true);
                    confirmField.clear();
                    confirmTextField.clear();
                }
                else if (confirmPassword.equals("")) {
                    emptyConfirm.setVisible(true);
                    confirmField.clear();
                    confirmTextField.clear();
                }
                else {
                    String email = emailField.getText();
                    if (client.getUser().setEmail(email) == 0) {
                        emptyEmail.setVisible(true);
                        emailField.clear();
                    } else if (client.getUser().setEmail(email) == 1) {
                        emailWithoutUsername.setVisible(true);
                        emailField.clear();
                    } else if (client.getUser().setEmail(email) == 2) {
                        WrongEmailDomain.setVisible(true);
                        emailField.clear();
                    } else {
                        client.getDataBase().signup(client.getUser());
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("DiscordMainPage.fxml"));
                        try {
                            Parent root = loader.load();
                            DiscordMainPage discordMainPage = loader.getController();
                            discordMainPage.setClient(client);
                            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * The Cnt 1.
     */
    int cnt1 = 0;

    /**
     * Visible 1 clicked.
     *
     * @param mouseEvent the mouse event
     */
    public void visible1Clicked(MouseEvent mouseEvent) {
        if ( cnt1 % 2 != 0){
            ((ImageView)mouseEvent.getSource()).setImage(new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\visible.png"));
            passwordTextField.setVisible(false);
            passwordField.setText(passwordTextField.getText());
            passwordTextField.clear();
            passwordField.setVisible(true);
            cnt1++;
        }
        else{
            ((ImageView)mouseEvent.getSource()).setImage(new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\invisible.png"));
            passwordField.setVisible(false);
            passwordTextField.setText(passwordField.getText());
            passwordField.clear();
            passwordTextField.setVisible(true);
            cnt1++;
        }
    }

    /**
     * The Cnt 2.
     */
    int cnt2 = 0;

    /**
     * Visible 2 clicked.
     *
     * @param mouseEvent the mouse event
     */
    public void visible2Clicked(MouseEvent mouseEvent) {
        if ( cnt2 % 2 != 0){
            ((ImageView)mouseEvent.getSource()).setImage(new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\visible.png"));
            confirmTextField.setVisible(false);
            confirmField.setText(confirmTextField.getText());
            confirmTextField.clear();
            confirmField.setVisible(true);
            cnt2++;
        }
        else{
            ((ImageView)mouseEvent.getSource()).setImage(new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\invisible.png"));
            confirmField.setVisible(false);
            confirmTextField.setText(confirmField.getText());
            confirmField.clear();
            confirmTextField.setVisible(true);
            cnt2++;
        }
    }

}

