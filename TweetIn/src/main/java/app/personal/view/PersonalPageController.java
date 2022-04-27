package app.personal.view;

import app.explore.listener.ExploreListener;
import app.explore.listener.GetUserPictureListener;
import app.personal.listener.ChangePageListener;
import app.personal.listener.GetListOfPeopleListener;
import app.timeline.listener.GetListOfMyTweetListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.tweet.Tweet;
import model.user.User;
import view.MainStage;
import view.MyConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PersonalPageController  implements Initializable {
    User user;
    ChangePageListener changePageListener;
    GetUserPictureListener getUserPictureListener = new GetUserPictureListener();
    GetListOfMyTweetListener getListOfMyTweetListener;
    GetListOfPeopleListener getListOfPeopleListener = new GetListOfPeopleListener();
    ExploreListener exploreListener = new ExploreListener();
    @FXML
    ImageView pictureView;

    @FXML
    private VBox tweetBox;
    @FXML
    private ScrollPane tweetScroll;

    @FXML
    private ComboBox<String> follower;

    @FXML
    private ComboBox<String> following;

    @FXML
    private TextArea bioField;

    @FXML
    private Label followingLabel;

    @FXML
    private Label followerLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label birthdayLabel;

    @FXML
    private Label nameLabel;

    ObservableList<String> followingList = FXCollections.observableArrayList();
    ObservableList<String> followerList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = MainStage.getInstance().user;
        getListOfMyTweetListener = new GetListOfMyTweetListener();
        changePageListener = new ChangePageListener();

        File file = getUserPictureListener.getUserImage(user.getId());
        FileInputStream inputstream = null;
        if(file!=null){
        try {
            inputstream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);

            pictureView.setImage(image);
        }



        bioField.setText(user.profile.bio);
        bioField.setEditable(false);
        usernameLabel.setText(user.profile.username);
        nameLabel.setText(user.profile.name);
        birthdayLabel.setText(user.profile.birthday);
        followerLabel.setText(Integer.toString(user.followers.size()));
        followingLabel.setText(Integer.toString(user.following.size()));
        loadComboBox();
        loadTweet();
    }
    void loadTweet(){
        tweetBox.getChildren().clear();
        ArrayList<Tweet> myTweetArraylist =  getListOfMyTweetListener.eventOccur(user.tweets);
        for (int i=myTweetArraylist.size()-1 ; i>-1 ; i--){
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource(MyConfig.instance.pageAddressProperties.getProperty("MyTweetPane")));
            try {
                Parent root = fxmlLoader1.load();
                MyTweetPaneController myTweetPaneController = fxmlLoader1.getController();
                myTweetPaneController.tweet = myTweetArraylist.get(i);
                myTweetPaneController.loadData();
                tweetBox.getChildren().add(new AnchorPane(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void loadComboBox(){
        ArrayList<String> tmpArrayList = getListOfPeopleListener.getUsername(user.following);
        followingList.removeAll();
        for (String s : tmpArrayList){
            followingList.add(s);
        }
        following.getItems().removeAll();
        following.setItems(followingList);


        ArrayList<String> arrayList = getListOfPeopleListener.getUsername(user.followers);
        followerList.removeAll();
        for (String s : arrayList){
            followerList.add(s);
        }
        follower.getItems().removeAll();
        follower.setItems(followerList);

    }



    public void back(){
        MainStage.getInstance().back();
    }

    public void setting(){

        changePageListener.eventOccur((MyConfig.instance.pageAddressProperties.getProperty("SettingPage")));
    }


    public void timeline(){
        changePageListener.eventOccur((MyConfig.instance.pageAddressProperties.getProperty("Timeline")));
    }


    public void announcement(){
        changePageListener.eventOccur(MyConfig.instance.pageAddressProperties.getProperty("AnnouncementPage"));
    }


    public void explore( ){
        changePageListener.eventOccur(MyConfig.instance.pageAddressProperties.getProperty("ExplorePage"));
    }



    public void message( ){
        changePageListener.eventOccur(MyConfig.instance.pageAddressProperties.getProperty("Message"));
    }
    public void seeFollowing(){
        if(following.getValue().equals("")){
            return;
        }
        exploreListener.seeUserPage(following.getValue());
    }
    public void seeFollower(){

        if(follower.getValue().equals("")){
            return;
        }
        exploreListener.seeUserPage(follower.getValue());

    }
    public  void newTweet(){
        changePageListener.eventOccur(MyConfig.instance.pageAddressProperties.getProperty("NewTweet"));
    }




}
