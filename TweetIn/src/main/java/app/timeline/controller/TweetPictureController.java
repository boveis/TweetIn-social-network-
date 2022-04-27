package app.timeline.controller;

import context.ImageDB;
import context.TweetDB;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;

public class TweetPictureController {
    public void AddImage(int id , File file){
        try {
            ImageDB.saveImage("tweet" , id , file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public File getPicture(int id) {
        if (TweetDB.getTweet(id).hasImage) {

            File file = new File("src\\main\\resources\\context\\image\\tweet\\" + id + ".png");
            if (!file.exists()) {
                return null;
            }

            return file;
        }
        return null;
    }
    public void copyPicture(int mainPicture , int newImage){
        File file = new File("src\\main\\resources\\context\\image\\tweet\\" + mainPicture + ".png");
        try {
            ImageDB.saveImage("tweet" , newImage , file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
