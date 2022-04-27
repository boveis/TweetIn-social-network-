package app.timeline.view;

import Log.Logger;
import app.setting.listener.BackHomeListener;
import app.timeline.listener.NewTweetListener;
import app.timeline.listener.TweetPictureListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import listener.GetIdListener;
import model.tweet.Tweet;
import view.MainStage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class NewTweetPageController {
    BackHomeListener backHomeListener = new BackHomeListener();
    NewTweetListener newTweetListener = new NewTweetListener();
    TweetPictureListener tweetPictureListener = new TweetPictureListener();
    GetIdListener getIdListener = new GetIdListener();
    File file;
    boolean hasImage = false;

    @FXML
    private TextField textField;

    @FXML
    private ImageView pictureShow;

    @FXML
    private Label textLabel;
    public void back(){
        backHomeListener.eventOccur("back");
    }
    public void home(){
        backHomeListener.eventOccur("home");
    }
    public void ChooseImage() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        this.file = fileChooser.showOpenDialog(null);
        if (file != null){
            hasImage = true;
            FileInputStream input = new FileInputStream(file);
            Image img = new Image(input);
            pictureShow.setImage(img);
        }
        if (file==null){
            hasImage = false;
        }
        return;
    }
    public void createTweet(){
        if (textField.getText().equals("")){
            textLabel.setVisible(true);
            return;
        }else {
            Tweet tweet = new Tweet(MainStage.getInstance().user.getId() , textField.getText() , hasImage , getIdListener.getTweetId()  ,0 ,false);
            if(hasImage){
                tweetPictureListener.addImage(tweet.id , file);
            }
            Logger.makeTweet(tweet.id);
            newTweetListener.eventOccured(tweet);
        }
    }



}
