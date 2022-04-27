package app.message.Listener;

import app.message.controller.MessageController;
import app.message.view.MessagePaneController;
import app.message.view.MessagePlatformPageController;
import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import listener.GetIdListener;
import model.message.Message;
import model.message.MessagePlatform;
import model.user.GroupOfPeaple;
import model.user.User;


import view.MainStage;
import view.MyConfig;

import java.io.IOException;
import java.util.ArrayList;


public class MessageListener  {
    MessageController messageController = new MessageController();
    MessagePlatformListener messagePlatformListener = new MessagePlatformListener();
    GetIdListener getIdListener = new GetIdListener();
    Controller controller = new Controller();
    public void groupManagerPage(){
        MainStage.getInstance().user = messageController.getUser(MainStage.getInstance().user.getId());
        MainStage.getInstance().setPage(MyConfig.instance.pageAddressProperties.getProperty("GroupPage"));
        return;
    }
    public void deleteChat(MessagePlatform messagePlatform){
        User user = MainStage.getInstance().user;
        user.messagePlatform= controller.deleteUser(user.messagePlatform , messagePlatform.id);
        controller.updateUser(user);
        MainStage.getInstance().user =controller.getUser(user.getId());
        MainStage.getInstance().refresh();
    }
    public MessagePlatform seeChat(String name){

        User user = MainStage.getInstance().user;
        MessagePlatform messagePlatform= null;
        for (int i=0 ; i<user.messagePlatform.size() ; i++){
            if (messagePlatformListener.getName(messagePlatformListener.getMessageplatform(user.messagePlatform.get(i))).equals(name)){
                messagePlatform = messagePlatformListener.getMessageplatform(user.messagePlatform.get(i));
            }
        }

        return messagePlatform;
    }
    public void loadPlatformPage(MessagePlatform messagePlatform){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MyConfig.instance.pageAddressProperties.getProperty("MessagePlatform")));
        try {
            Parent root = fxmlLoader.load();
            MessagePlatformPageController messagePlatformPageController = fxmlLoader.getController();
            messagePlatformPageController.loadData(messagePlatform  , MainStage.getInstance().user.getId());
            Scene scene = new Scene(root);
            MainStage.getInstance().stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Message message:messagePlatform.messages){
            message.read(MainStage.getInstance().user.getId());
        }
        update(messagePlatform);
        return;
    }
    public MessagePlatform sendGroup(GroupOfPeaple groupOfPeaple){
        User user = MainStage.getInstance().user;
        for(int i=0 ; i<user.messagePlatform.size(); i++){
            if(messagePlatformListener.getName(messagePlatformListener.getMessageplatform(user.messagePlatform.get(i))).equals(groupOfPeaple.name)){
                if(messagePlatformListener.getMessageplatform(user.messagePlatform.get(i)).type==2) {
                    return messagePlatformListener.getMessageplatform(user.messagePlatform.get(i));
                }
            }
        }

        MessagePlatform messagePlatform = new MessagePlatform( groupOfPeaple.usersId,groupOfPeaple.name ,2 , getIdListener.getMessageId());
        messagePlatformListener.add(messagePlatform);
        MainStage.getInstance().user = messageController.getUser(user.getId());

        return messagePlatform;
    }
    public MessagePlatform sendToFollowing(String username){
        User user = MainStage.getInstance().user;
        for(int i=0 ; i<user.messagePlatform.size(); i++){
            if(messagePlatformListener.getName(messagePlatformListener.getMessageplatform(user.messagePlatform.get(i))).equals(username)){
                if(messagePlatformListener.getMessageplatform(user.messagePlatform.get(i)).type==1) {
                    return messagePlatformListener.getMessageplatform(user.messagePlatform.get(i));
                }
            }
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(user.getId());
        arrayList.add(messageController.getId(username));
        MessagePlatform messagePlatform = new MessagePlatform(arrayList ,1,getIdListener.getMessageId());
        messagePlatformListener.add(messagePlatform);
        MainStage.getInstance().user = messageController.getUser(user.getId());
        return messagePlatform;
    }
    public void update(MessagePlatform messagePlatform){
        messageController.updateMessagePlatform(messagePlatform);
    }

}
