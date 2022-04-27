package app.timeline.listener;

import app.timeline.controller.TweetPictureController;
import javafx.scene.image.Image;

import java.io.File;

public class TweetPictureListener {
    TweetPictureController tweetPictureController = new TweetPictureController();
    public void addImage(int id  , File file){
        tweetPictureController.AddImage(id , file);
    }
    public File getImage(int id){
        return tweetPictureController.getPicture(id);
    }




}
