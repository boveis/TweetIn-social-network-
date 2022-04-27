package app.message.Listener;

import app.message.controller.MessageController;
import controller.Controller;
import model.message.MessagePlatform;
import model.user.User;
import view.MainStage;

import java.util.MissingFormatArgumentException;

public class MessagePlatformListener {
    MessageController messageController = new MessageController();
    public MessagePlatform getMessageplatform(int id){
        return messageController.getMessagePlatform(id);
    }
    public String getName(MessagePlatform messagePlatform){
        if(messagePlatform.type == 0){
            return "saved Message";
        }
        if(messagePlatform.type==2){
            return messagePlatform.name;
        }
        if(messagePlatform.users.get(0)!= MainStage.getInstance().user.getId())return messageController.getUsername(messagePlatform.users.get(0));
        return messageController.getUsername(messagePlatform.users.get(1));
    }
    public void sendMessage(MessagePlatform messagePlatform){
        messageController.updateMessagePlatform(messagePlatform);
    }
    public void add(MessagePlatform messagePlatform){
        messageController.addMessagePlatform(messagePlatform);
    }
}
