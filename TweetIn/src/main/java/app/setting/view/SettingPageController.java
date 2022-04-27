package app.setting.view;

import Log.Logger;
import app.explore.listener.ExploreListener;
import app.explore.listener.GetUserPictureListener;
import app.personal.listener.GetListOfPeopleListener;
import app.setting.event.StringEvent;
import app.setting.listener.BackHomeListener;
import app.setting.listener.ChangeProfileListener;
import exception.EmailInvalid;
import exception.PhoneInvalid;
import exception.UsernameInvalid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.user.User;
import view.MainStage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SettingPageController implements Initializable {

    ObservableList<String> listLastseen = FXCollections.observableArrayList();

    ObservableList<String> listPrivacy = FXCollections.observableArrayList();

    ObservableList<String> listBlock = FXCollections.observableArrayList();
    public User user;
    ExploreListener exploreListener = new ExploreListener();
    ChangeProfileListener changeProfileListener;
    GetUserPictureListener getUserPictureListener = new GetUserPictureListener();

    GetListOfPeopleListener getListOfPeopleListene  = new GetListOfPeopleListener();
    BackHomeListener backHomeListener;

    @FXML
    private ComboBox<String> lastseenChoiceBox;

    @FXML
    private ComboBox<String> privacyChoiceBox;

    @FXML
    private ComboBox<String> blockedChoioceBox;


    @FXML
    private TextField nameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField newPasswordField;
    @FXML
    private TextField oldPasswordField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField bioField;
    @FXML
    private ImageView profileShow;
    @FXML
    private Label emailLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label phoneLabel;

    @FXML
    private DatePicker datePickre;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phoneLabel.setVisible(false);
        passwordLabel.setVisible(false);
        emailLabel.setVisible(false);
        usernameLabel.setVisible(false);
        user = MainStage.getInstance().user;
        loadChoiceBox();
        loadText();
        changeProfileListener = new ChangeProfileListener();

        backHomeListener = new BackHomeListener();
        File file = getUserPictureListener.getUserImage(user.getId());
        if(file!=null){
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);

            profileShow.setImage(image);
        }

    }

    public void loadText() {
        usernameField.setText(user.profile.username);
        nameField.setText(user.profile.name);
        emailField.setText(user.profile.email);
        phoneField.setText(user.profile.phone);
        bioField.setText(user.profile.bio);
        datePickre.setPromptText(user.profile.birthday);

    }

    public void loadChoiceBox() {
        listLastseen.removeAll();
        listLastseen.addAll("everyone", "followers", "no one");
        listPrivacy.removeAll();
        listPrivacy.addAll("public", "private");
        listBlock.removeAll();
        ArrayList<String> tmp = getListOfPeopleListene.getUsername(user.blocked);
        for (int i = 0; i < tmp.size(); i++) {
            listBlock.add(tmp.get(i));
        }
        blockedChoioceBox.getItems().removeAll();
        blockedChoioceBox.getItems().addAll(listBlock);
        lastseenChoiceBox.getItems().removeAll();
        lastseenChoiceBox.getItems().addAll(listLastseen);
        privacyChoiceBox.getItems().removeAll();
        privacyChoiceBox.getItems().addAll(listPrivacy);
        if (user.profile.lastSeenShow == 0) {
            lastseenChoiceBox.setValue("everyone");
        } else if (user.profile.lastSeenShow == 1) {
            lastseenChoiceBox.setValue("followers");
        } else {
            lastseenChoiceBox.setValue("no one");
        }

        if (user.profile.isPublic) {
            privacyChoiceBox.setValue("public");
        } else {
            privacyChoiceBox.setValue("private");
        }

    }
    @FXML
    void changePass(ActionEvent event) throws PhoneInvalid, EmailInvalid, UsernameInvalid {
        if (oldPasswordField.getText().equals(user.profile.password)) {
            if (newPasswordField.getText().equals("")) {
                passwordLabel.setVisible(true);
                return;
            }
            passwordLabel.setVisible(false);
            user.profile.password = newPasswordField.getText();
            changeProfileListener.eventOccur(new StringEvent("password", ""));
            Logger.logChangePassword(user.profile.password);
            newPasswordField.setText("");
            oldPasswordField.setText("");
            return;
        } else {
            passwordLabel.setVisible(true);
            return;
        }
    }
    @FXML
    void changeName(ActionEvent event) throws PhoneInvalid, EmailInvalid, UsernameInvalid {
        if (nameField.getText().equals("")) {
            nameField.setText(user.profile.name);
            return;
        }
        user.profile.name = nameField.getText();
        Logger.logChangeName(user.profile.name);
        changeProfileListener.eventOccur(new StringEvent("name", ""));
    }
    @FXML
    void changeBio(ActionEvent event) throws PhoneInvalid, EmailInvalid, UsernameInvalid {
        user.profile.bio = bioField.getText();
        changeProfileListener.eventOccur(new StringEvent("bio", ""));
    }
    @FXML
    void selectDate(ActionEvent event) throws PhoneInvalid, EmailInvalid, UsernameInvalid {
        user.profile.birthday = datePickre.getValue().toString();
        Logger.logChangeBirthday(user.profile.birthday);
        changeProfileListener.eventOccur(new StringEvent("birthday", ""));
    }
    @FXML
    void changePrivacy(ActionEvent event) throws PhoneInvalid, EmailInvalid, UsernameInvalid {
        if (privacyChoiceBox.getValue().equals("private")) {
            user.profile.isPublic = false;
        } else {
            user.profile.isPublic = true;
        }
        changeProfileListener.eventOccur(new StringEvent("privacy", ""));
    }
    @FXML
    void changeLastseen(ActionEvent event) throws PhoneInvalid, EmailInvalid, UsernameInvalid {
        if (lastseenChoiceBox.getValue().equals("everyone")) {
            user.profile.lastSeenShow = 0;
        } else if (lastseenChoiceBox.getValue().equals("followers")) {
            user.profile.lastSeenShow = 1;
        } else {
            user.profile.lastSeenShow = 2;
        }
        changeProfileListener.eventOccur(new StringEvent("lastseen", ""));
    }
    @FXML
    void changeUsername(ActionEvent event) throws EmailInvalid, PhoneInvalid {
        if (usernameField.getText().equals("")) {
            usernameField.setText(user.profile.username);
            return;
        }
        usernameLabel.setVisible(false);
        try {

            changeProfileListener.eventOccur(new StringEvent("username", usernameField.getText()));
        } catch (UsernameInvalid usernameInvalid) {
            usernameLabel.setVisible(true);
        }
    }
    @FXML
    void changeEmail(ActionEvent event) throws PhoneInvalid, UsernameInvalid {
        if (emailField.getText().equals("")) {
            emailField.setText(user.profile.email);
            return;
        }
        emailLabel.setVisible(false);
        try {
            changeProfileListener.eventOccur(new StringEvent("email", emailField.getText()));
        } catch (EmailInvalid emailInvalid) {
            emailLabel.setVisible(true);
        }
    }
    @FXML
    void changePhone(ActionEvent event) throws UsernameInvalid, EmailInvalid {
        phoneLabel.setVisible(false);
        try {
            changeProfileListener.eventOccur(new StringEvent("phone", phoneField.getText()));
        } catch (PhoneInvalid phoneInvalid) {
            phoneLabel.setVisible(true);
        }
    }
    @FXML
    void logOut(ActionEvent event) throws PhoneInvalid, EmailInvalid, UsernameInvalid {
        changeProfileListener.eventOccur(new StringEvent("log out" ,"" ));
    }
    @FXML
    void back(ActionEvent event) throws PhoneInvalid, EmailInvalid, UsernameInvalid {
        backHomeListener.eventOccur("back");
    }
    @FXML
    void deactiva(ActionEvent event) throws PhoneInvalid, EmailInvalid, UsernameInvalid {
        Logger.logDeactive();
        changeProfileListener.eventOccur(new StringEvent("deactiva" ,"" ));
    }
    @FXML
    void delete(ActionEvent event) throws PhoneInvalid, EmailInvalid, UsernameInvalid {
        Logger.logDeleteAccount();
        changeProfileListener.eventOccur(new StringEvent("delete" ,"" ));
    }
    @FXML
    void home(ActionEvent event) throws PhoneInvalid, EmailInvalid, UsernameInvalid {
        backHomeListener.eventOccur("home");
    }
    public void changePicture(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        if (file != null){
            getUserPictureListener.saveImage(file);

             file = getUserPictureListener.getUserImage(user.getId());
            if(file!=null){
            FileInputStream inputstream = null;
            try {
                inputstream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image = new Image(inputstream);
                Logger.logChangeProfile();
                profileShow.setImage(image);
            }

        }
        return;
    }
    public void seeBlockedPerson(){
        if (blockedChoioceBox.getValue().equals("")){
            return;
        }
        exploreListener.seeUserPage(blockedChoioceBox.getValue());
    }







}