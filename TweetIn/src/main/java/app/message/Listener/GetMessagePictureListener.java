package app.message.Listener;

import app.message.controller.MessageController;
import app.setting.controller.SettingController;
import context.Context;
import javafx.scene.image.Image;
import model.message.Message;
import model.message.MessagePlatform;

import java.awt.*;
import java.io.File;
import java.util.Set;

public class GetMessagePictureListener {
    MessageController messageController = new MessageController();
    public Message savePicture(File file , Message message){
        message.imageNumber = Context.getId("images");
        messageController.savePicture(message.imageNumber ,file );
        return message;
    }
    public File getPicture(int id){
        return messageController.getPicture( id);
    }
    public Message copyPictureTweet(Message message  , int tweetId){

        File file = new File("src\\main\\resources\\context\\image\\tweet\\" + tweetId + ".png");
        return savePicture(file,message);
    }
}
