package view;

import event.LoginFormEvent;
import exception.PasswordInvalid;
import exception.UsernameInvalid;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import listener.LoginListener;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    public LoginListener loginListener;
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setVisible(false);
        passwordLabel.setVisible(false);
        loginListener = new LoginListener();

    }
    @FXML
    public void login(){
        usernameLabel.setVisible(false);
        passwordLabel.setVisible(false);
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.equals("")){
            usernameLabel.setText(MyConfig.instance.pageAddressProperties.getProperty("invalidUsername"));
            usernameLabel.setVisible(true);
            return;
        }
        else {
            usernameLabel.setVisible(false);
        }

        if (password.equals("")){
            passwordLabel.setText(MyConfig.instance.pageAddressProperties.getProperty("invalidPass"));
            passwordLabel.setVisible(true);
            return;
        }
        else {
            passwordLabel.setVisible(false);
        }
        try {
            loginListener.eventOccur(new LoginFormEvent(this  , username , password));
        } catch (UsernameInvalid usernameInvalid) {
            usernameLabel.setText(MyConfig.instance.pageAddressProperties.getProperty("wrongUsername"));
            usernameLabel.setVisible(true);
            return;
        } catch (PasswordInvalid passwordInvalid) {
            passwordLabel.setText(MyConfig.instance.pageAddressProperties.getProperty("wrongPass"));
            passwordLabel.setVisible(true);
            return;
        }
    }
    @FXML
    public void signUp() throws IOException {
        MainStage.getInstance().setPage(MyConfig.instance.pageAddressProperties.getProperty("SignUnPage"));
    }
}
