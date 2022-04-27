package app.timeline.view;

import app.explore.listener.ExploreListener;
import app.explore.listener.GetUserPictureListener;
import app.personal.listener.GetListOfPeopleListener;
import app.timeline.listener.TweetPaneListener;
import app.timeline.listener.TweetPictureListener;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.tweet.Tweet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TweetPaneController {
    Tweet tweet ;
    int user;

    TweetPictureListener tweetPictureListener = new TweetPictureListener();
    TweetPaneListener tweetPaneListener = new TweetPaneListener();
    GetListOfPeopleListener getListOfPeopleListener = new GetListOfPeopleListener();
    GetUserPictureListener getUserPictureListener = new GetUserPictureListener();
    ExploreListener exploreListener = new ExploreListener();
    @FXML
    private TextField textField;

    @FXML
    private ImageView userPicture;

    @FXML
    private Label usernaemLabel;

    @FXML
    private ImageView tweetPicure;

    @FXML
    private CheckBox likeCheckBox;



   public void loadData(Tweet tweet , int user){
       likeCheckBox.setSelected(false);
       this.user = user ;
        this.tweet = tweet;
        usernaemLabel.setText(getListOfPeopleListener.getUsername(tweet.author_id));

       File file = getUserPictureListener.getUserImage(tweet.author_id);

       FileInputStream inputstream = null;
       if(file!=null){
       try {
           inputstream = new FileInputStream(file);
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       Image image = new Image(inputstream);

           userPicture.setImage(image);
       }

        if (tweet.hasImage){

             inputstream = null;
            try {
                inputstream = new FileInputStream(tweetPictureListener.getImage(tweet.id));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image imagetmp = new Image(inputstream);
            tweetPicure.setImage(imagetmp);
        }
        for (int i = 0; i<tweet.likes.size() ; i++){
            if (tweet.likes.get(i)==user){
                likeCheckBox.setSelected(true);
            }
        }
        textField.setText(tweet.text);
        textField.setEditable(false);
    }
    public void like(){
        tweetPaneListener.like(tweet , likeCheckBox.isSelected());
    }
    public void report(){
        tweetPaneListener.report(tweet);
    }

    public void seeUserPage(){
        exploreListener.seeUserPage(getListOfPeopleListener.getUsername(tweet.author_id));
    }

    public void retweet(){
        tweetPaneListener.retweet(tweet);
   }

    public void sendAsMessage(){
        tweetPaneListener.sendAsMessage(tweet);
    }

    public void seeCommment(){
       tweetPaneListener.seeComment(tweet);
    }
    public void addComment(){
        tweetPaneListener.addComment(tweet);
    }

}
