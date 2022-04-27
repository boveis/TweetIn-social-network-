package app.message.view;

import Log.Logger;
import app.message.Listener.GetMessagePictureListener;
import app.message.Listener.MessagePlatformListener;
import app.setting.listener.BackHomeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import model.message.Message;
import model.message.MessagePlatform;
import view.MyConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MessagePlatformPageController {
    public int user;
    public MessagePlatformListener messagePlatformListener  = new MessagePlatformListener();
    public MessagePlatform messagePlatform;
    public GetMessagePictureListener getMessagePictureListener = new GetMessagePictureListener();
    public BackHomeListener backHomeListener = new BackHomeListener();
    File file;
    boolean hasImage;
    @FXML
    private Label nameLabel;

    @FXML
    private VBox vBox;

    @FXML
    private TextField textField;

    @FXML
    private ImageView imageView;


    public void loadData(MessagePlatform messagePlatform , int user){
        this.user = user;
        this.messagePlatform = messagePlatform;
        loadMessages(user);
        hasImage=false;
        file=null;
        textField.setText("");
        nameLabel.setText(messagePlatformListener.getName(messagePlatform));
    }
    public void loadMessages(int user){
        vBox.getChildren().clear();
        for (int i=0 ; i<messagePlatform.messages.size() ; i++) {
            Message message = messagePlatform.messages.get(i);
            if (message.sender==user){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MyConfig.instance.pageAddressProperties.getProperty("MyMessagePane")));
                try {
                    Parent root = fxmlLoader.load();
                    MessagePaneController messagePaneController = fxmlLoader.getController();
                    messagePaneController.loadDate(this , message);
                    messagePaneController.usernameLabel.setText("me");
                    vBox.getChildren().add(new AnchorPane(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MyConfig.instance.pageAddressProperties.getProperty("OthersMessagePane")));
                try {
                    Parent root = fxmlLoader.load();
                    MessagePaneController messagePaneController = fxmlLoader.getController();
                    messagePaneController.loadDate(this , message);
                    vBox.getChildren().add(new AnchorPane(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void chooseImage() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        this.file = fileChooser.showOpenDialog(null);
        if (file != null){
            hasImage = true;
            FileInputStream input = new FileInputStream(file);
            Image img = new Image(input);
            imageView.setImage(img);
        }else{
            hasImage = false;
        }
        return;
    }
    public void send(){
        if(!hasImage && textField.getText().equals(""))return;
        Message message = new Message(user , textField.getText() , hasImage , 0 , false);
        if(hasImage){
            message = getMessagePictureListener.savePicture(file , message);
        }
        messagePlatform.messages.add(message);
        messagePlatformListener.sendMessage(messagePlatform);
        Logger.makeMessage(messagePlatform.id);
        imageView.setImage(null);
        loadData(messagePlatform , user);
    }
    public void back(){
        backHomeListener.eventOccur("refresh");
        return;
    }
    public void home(){
        backHomeListener.eventOccur("home");
    }
}
