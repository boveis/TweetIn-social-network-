package app.message.controller;

import app.explore.listener.FollowListener;
import context.ImageDB;
import context.MessagePlatformDB;
import context.UserDB;
import controller.Controller;
import javafx.scene.image.Image;
import model.message.Message;
import model.message.MessagePlatform;
import model.user.User;

import java.io.File;
import java.io.IOException;

public class MessageController extends Controller {
    public void updateMessagePlatform(MessagePlatform messagePlatform){
        MessagePlatformDB.update(messagePlatform);
        return;
    }
    public MessagePlatform getMessagePlatform(int id){
        return MessagePlatformDB.get(id);
    }
    public void addMessagePlatform(MessagePlatform messagePlatform){
        MessagePlatformDB.add(messagePlatform);
        for (int i= 0 ; i<messagePlatform.users.size() ; i++){
            User tmp  = UserDB.getUser(messagePlatform.users.get(i));
                for (Integer j:tmp.messagePlatform){

                    if (j==messagePlatform.id)return;

                }
                tmp.messagePlatform.add(messagePlatform.id);
            UserDB.update(tmp);
        }
    }
    public void savePicture(int id  , File file){
        try {
            ImageDB.saveImage("message" , id , file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public File getPicture(int id){
        File file = new File("src\\main\\resources\\context\\image\\message\\"+id+".png");
        if (!file.exists()){
            return null;
        }
        return file;
    }
    public void deleteMessagePlatform(MessagePlatform messagePlatform){
        for (int i=0 ; i<messagePlatform.users.size() ; i++){
            User user = UserDB.getUser(messagePlatform.users.get(i));
            user.messagePlatform = deleteUser(user.messagePlatform , messagePlatform.id);
            UserDB.update(user);
        }
        MessagePlatformDB.remove(messagePlatform);
    }



}
