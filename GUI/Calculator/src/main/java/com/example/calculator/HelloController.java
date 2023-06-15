package com.example.calculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Hello controller.
 */
public class HelloController implements Initializable {

    @FXML
    private AnchorPane backGround;

    @FXML
    private Text invalidInfoLogin;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField usernameField;

    /**
     * The Client.
     */
    Client client = null;

    /**
     * The Counter.
     */
    static int counter = 0;

    /**
     * Set client.
     *
     * @param client the client
     */
    public void setClient (Client client){
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        invalidInfoLogin.setVisible(false);
        if ( counter == 0){
            client = new Client();
            counter++;
        }
    }

    /**
     * Call sign up.
     *
     * @param event the event
     */
    @FXML
    void callSignUp(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        try {
            Parent root = loader.load();
            SignUpPage signUpPage  = loader.getController();
            signUpPage.setClient(client);
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
     * Login.
     *
     * @param event the event
     */
    @FXML
    void login(MouseEvent event) {
        String username = usernameField.getText();
        String password;
        if (cnt % 2 != 0) {
            password = passwordTextField.getText();
        }
        else {
            password = passwordField.getText();
        }
        if ( client.getDataBase().checkLogin(username, password, client.getUser())){
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
        else{
            invalidInfoLogin.setVisible(true);
            usernameField.clear();
            passwordField.clear();
        }
    }

    /**
     * The Cnt.
     */
    int cnt = 0;

    /**
     * Visible clicked.
     *
     * @param mouseEvent the mouse event
     */
    public void visibleClicked(MouseEvent mouseEvent) {
        if ( cnt % 2 != 0){
            ((ImageView)mouseEvent.getSource()).setImage(new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\visible.png"));
            passwordTextField.setVisible(false);
            passwordField.setText(passwordTextField.getText());
            passwordTextField.clear();
            passwordField.setVisible(true);
            cnt++;
        }
        else{
            ((ImageView)mouseEvent.getSource()).setImage(new Image("C:\\Users\\LENOVO\\IdeaProjects\\Calculator\\src\\main\\resources\\com\\example\\calculator\\Pics\\invisible.png"));
            passwordField.setVisible(false);
            passwordTextField.setText(passwordField.getText());
            passwordField.clear();
            passwordTextField.setVisible(true);
            cnt++;
        }
    }
}
