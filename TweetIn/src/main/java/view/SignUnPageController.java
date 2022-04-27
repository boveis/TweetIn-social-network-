package view;

import event.SignUpFormEvent;
import exception.EmailInvalid;
import exception.PhoneInvalid;
import exception.UsernameInvalid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import listener.SignUpListener;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUnPageController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private TextField bio;


    @FXML
    private Button signUpBtn;

    @FXML
    private Label informationLabel;

    @FXML
    private Button loginBtn;

    @FXML
    private DatePicker datePickre;
    private String birthday = "";

    SignUpListener signUpListener ;

    @FXML
    void selectDate(ActionEvent event) {
        birthday = datePickre.getValue().toString();
    }

    public void signUp(){
        informationLabel.setText("you must fill fields with star(*) mark .");
        if(username.getText().equals("")){
            informationLabel.setText("you must fill fields with star(*) mark . (username)");
            return;
        }if(password.getText().equals("")){
            informationLabel.setText("you must fill fields with star(*) mark . (password)");
            return;
        }if(name.getText().equals("")){
            informationLabel.setText("you must fill fields with star(*) mark . (name)");
            return;
        }if(email.getText().equals("")){
            informationLabel.setText("you must fill fields with star(*) mark . (email)");
            return;
        }
        try {
            signUpListener.eventOccur(new SignUpFormEvent(username.getText() ,
                    password.getText() ,
                    name.getText() ,
                    email.getText() ,
                    phone.getText() ,
                    bio.getText(),
                    birthday));

        } catch (UsernameInvalid usernameInvalid) {
            informationLabel.setText("username has already exist!");
        } catch (EmailInvalid emailInvalid) {
            informationLabel.setText("email has already exist!");
        } catch (PhoneInvalid phoneInvalid) {
            informationLabel.setText("phone has already exist!");
        }
    }

    public void login() throws IOException {
        MainStage.getInstance().setPage((MyConfig.instance.pageAddressProperties.getProperty("LoginPage")));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signUpListener = new SignUpListener();
    }




}
