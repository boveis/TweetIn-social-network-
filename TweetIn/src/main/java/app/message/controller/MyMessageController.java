package app.message.controller;

import model.message.Message;
import model.message.MessagePlatform;

import java.util.ArrayList;

public class MyMessageController extends MessageController{


    public void edit(MessagePlatform messagePlatform){
        updateMessagePlatform(messagePlatform);
        return;
    }
    public void deleteMessage(MessagePlatform messagePlatform , Message message){
        for (int i=0 ; i<messagePlatform.messages.size() ; i++){
            if (message.equals(messagePlatform.messages.get(i))){
                messagePlatform.messages.remove(i);
                updateMessagePlatform(messagePlatform);
                return;
            }
        }
        return;
    }
}
