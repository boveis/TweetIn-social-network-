package app.message.event;

import app.message.view.MessagePlatformPageController;
import model.message.Message;
import model.message.MessagePlatform;

public class EditMessageEvent {

    public MessagePlatform messagePlatform;
    public MessagePlatformPageController messagePlatformPageController ;
    public  Message message;


    public EditMessageEvent(MessagePlatform messagePlatform, MessagePlatformPageController messagePlatformPageController, Message message) {
        this.messagePlatform = messagePlatform;
        this.messagePlatformPageController = messagePlatformPageController;
        this.message = message;
    }


}
