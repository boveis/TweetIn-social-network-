package app.message.view;

import app.explore.listener.GetUserPictureListener;
import app.message.Listener.GetMessagePictureListener;
import app.message.Listener.MessagePaneListener;
import app.message.event.EditMessageEvent;
import app.personal.listener.GetListOfPeopleListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.message.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MessagePaneController  {

    public Message message ;
    public GetMessagePictureListener getMessagePictureListener;
    public GetUserPictureListener getUserPictureListener = new GetUserPictureListener();
    public MessagePlatformPageController messagePlatformPageController;
    public MessagePaneListener messagePaneListener;
    public GetListOfPeopleListener getListOfPeopleListener = new GetListOfPeopleListener();
    @FXML
    private ImageView userImage;

    @FXML
    private TextField textField;

    @FXML
    private ImageView messageImage;


    @FXML
    public Label usernameLabel;


    public void loadDate(MessagePlatformPageController messagePlatformPageController , Message message){
        this.message = message;
        this.messagePlatformPageController = messagePlatformPageController;

        getMessagePictureListener=new GetMessagePictureListener();
        messagePaneListener=new MessagePaneListener();
        textField.setText(message.text);

        if(message.hasImage) {
            File file = getMessagePictureListener.getPicture(message.imageNumber);
            if(file!=null){
                FileInputStream inputstream = null;
                try {
                    inputstream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image = new Image(inputstream);

                messageImage.setImage(image);
            }
        }
        File file = getUserPictureListener.getUserImage(message.sender);
        if(file!=null){
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);

            userImage.setImage(image);
        }
        usernameLabel.setText(getListOfPeopleListener.getUsername(message.sender));
    }

    public void edit(){
        if(message.isForwarded){
            textField.setText(message.text);
            return;
        }
        message.text = textField.getText();
        messagePaneListener.eventOccur("edit" , new EditMessageEvent(messagePlatformPageController.messagePlatform , messagePlatformPageController,null));
        return;
    }
    public void delete(){
        messagePaneListener.eventOccur("delete" , new EditMessageEvent(messagePlatformPageController.messagePlatform , messagePlatformPageController,message));
        return;
    }
    public void forward(){
        messagePaneListener.eventOccur("forward" , new EditMessageEvent(messagePlatformPageController.messagePlatform , messagePlatformPageController,message));
    }
}
