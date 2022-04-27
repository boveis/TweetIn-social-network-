package app.personal.view;

import app.personal.listener.GetListOfPeopleListener;
import app.personal.listener.MyTweetPaneListener;
import app.timeline.listener.TweetPaneListener;
import app.timeline.listener.TweetPictureListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.tweet.Tweet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MyTweetPaneController {
    public Tweet tweet ;
    TweetPictureListener tweetPictureListener = new TweetPictureListener();
    GetListOfPeopleListener getListOfPeopleListener;
    MyTweetPaneListener myTweetPaneListener;
    ObservableList likeList = FXCollections.observableArrayList();
    TweetPaneListener tweetPaneListener = new TweetPaneListener();
    @FXML
    private ImageView pictureView;

    @FXML
    private TextField textField;

    @FXML
    private ComboBox<String> likedComboBox;


    public void loadData() {
        textField.setText(tweet.text);
        textField.setEditable(false);
        if (tweet.hasImage){
            try {
                FileInputStream inputstream = new FileInputStream(tweetPictureListener.getImage( tweet.id));
                Image image = new Image(inputstream);
                pictureView.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        loadComboBox();
        myTweetPaneListener = new MyTweetPaneListener();

    }
    void loadComboBox(){
        likeList.removeAll();
        getListOfPeopleListener =new GetListOfPeopleListener();
        ArrayList<String> tweetLike = getListOfPeopleListener.getUsername(tweet.likes);
        for (int i=0 ; i<tweetLike.size() ; i++){
            likeList.addAll(tweetLike.get(i));
        }
        likedComboBox.getItems().removeAll();
        likedComboBox.getItems().addAll(likeList);

    }

    public void delete(){
        myTweetPaneListener.delete( tweet);
    }
    public void addComment(){
        myTweetPaneListener.addComment(tweet);
    }
    public void seeComment(){
            tweetPaneListener.seeComment(tweet);

    }
    public void sendAsMessage(){
        tweetPaneListener.sendAsMessage(tweet);
    }




}
