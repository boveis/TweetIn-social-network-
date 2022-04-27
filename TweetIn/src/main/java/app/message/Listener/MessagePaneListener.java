package app.message.Listener;

import app.message.controller.MyMessageController;
import app.message.event.EditMessageEvent;
import app.message.view.MessagePaneController;
import app.message.view.MessagePlatformPageController;
import app.message.view.ReceiverChoosePageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import jdk.internal.net.http.common.ImmutableExtendedSSLSession;
import model.message.Message;
import model.message.MessagePlatform;
import view.MainStage;
import view.MyConfig;

import java.io.IOException;

public class MessagePaneListener {
    MyMessageController myMessageController = new MyMessageController();

    public void eventOccur(String s , EditMessageEvent editMessageEvent){
        if (s.equals("edit")){
            myMessageController.edit(editMessageEvent.messagePlatform);
            MainStage.getInstance().user = myMessageController.getUser(MainStage.getInstance().user.getId());
            editMessageEvent.messagePlatformPageController.loadData(myMessageController.getMessagePlatform(editMessageEvent.messagePlatform.id) , MainStage.getInstance().user.getId());
            return;
        }
        if (s.equals("delete")){
            myMessageController.deleteMessage(editMessageEvent.messagePlatform , editMessageEvent.message);
            MainStage.getInstance().user = myMessageController.getUser(MainStage.getInstance().user.getId());
            editMessageEvent.messagePlatformPageController.loadData(myMessageController.getMessagePlatform(editMessageEvent.messagePlatform.id) , MainStage.getInstance().user.getId());
            return;
        }
        if (s.equals("forward")){
            Message message = editMessageEvent.message;
            Message newMessage = new Message(MainStage.getInstance().user.getId(),
                    myMessageController.getUsername(message.sender)+": "+message.text ,
                    message.hasImage,
                    message.imageNumber,
                    true);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MyConfig.instance.pageAddressProperties.getProperty("ChooseReciever")));
            try {
                Parent root = fxmlLoader.load();
                ReceiverChoosePageController receiverChoosePageController = fxmlLoader.getController();
                receiverChoosePageController.loadData(newMessage , MainStage.getInstance().user);
                Scene scene = new Scene(root);
                MainStage.getInstance().stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }






}
