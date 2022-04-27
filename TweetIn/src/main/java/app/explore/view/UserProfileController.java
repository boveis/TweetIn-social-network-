package app.explore.view;

import app.explore.listener.BlockListener;
import app.explore.listener.FollowListener;
import app.explore.listener.GetUserPictureListener;
import app.message.Listener.MessageListener;
import app.setting.listener.BackHomeListener;
import app.timeline.listener.GetListOfMyTweetListener;
import app.timeline.view.TweetPaneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

public class UserProfileController implements Initializable {

    User visitor;
    User user;
    boolean ismute = false;
    boolean isFollowing = false;
    boolean isRequest = false;
    GetListOfMyTweetListener getListOfMyTweetListener = new GetListOfMyTweetListener();
    GetUserPictureListener getUserPictureListener = new GetUserPictureListener();
    FollowListener followListener;
    BackHomeListener backHomeListener;
    BlockListener blockListener;
    MessageListener messageListener = new MessageListener();
    @FXML
    private ImageView pictureView;

    @FXML
    private Label usernameLabell;

    @FXML
    private Label nameLabel;

    @FXML
    private Label birthdayLabel;

    @FXML
    private TextArea bioField;

    @FXML
    private Label lastseenLabel;
    @FXML
    private Button followBtn;

    @FXML
    private Button blockBtn;

    @FXML
    private Button muteBtn;

    @FXML
    private Button reportBtn;

    @FXML
    private Button messageBtn;

    @FXML
    private VBox vbox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = MainStage.getInstance().user;
        visitor = MainStage.getInstance().visitor;
        loadData();

        followListener = new FollowListener();
        blockListener = new BlockListener();
        backHomeListener = new BackHomeListener();
    }
    void loadData(){
        nameLabel.setText("name :"+visitor.profile.name);
        birthdayLabel.setText("birthday :"+visitor.profile.birthday);
        usernameLabell.setText("username"+visitor.profile.username);
        bioField.setText(visitor.profile.bio);
        bioField.setEditable(false);
        File file = getUserPictureListener.getUserImage(visitor.getId());
        if(file!=null){
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);

            pictureView.setImage(image);
        }


        for (int i=0 ; i<user.following.size() ; i++){
            if(user.following.get(i)==visitor.getId()){
                isFollowing=true;
                break;
            }
        }
        for (int i=0 ; i<user.followRequests.size() ; i++){
            if(user.followRequests.get(i)==visitor.getId()){
                isRequest=true;
                break;
            }
        }
        followBtn.setDisable(false);
        lastseenLabel.setText(visitor.profile.lastseen);
        birthdayLabel.setVisible(true);
        lastseenLabel.setVisible(true);
        if (isFollowing){
            laodTweet();
            birthdayLabel.setVisible(true);
            followBtn.setText("unFollow");
            if(visitor.profile.lastSeenShow==2){
                lastseenLabel.setVisible(false);
            }
            for (int i=0 ; i<user.muted.size() ; i++){
                if (user.muted.get(i)==visitor.getId())ismute= true;
            }
            if(ismute){
                muteBtn.setText("unmute");
            }
        }
        else{
            birthdayLabel.setVisible(false);
            if (visitor.profile.isPublic){
                birthdayLabel.setVisible(true);
                laodTweet();
            }
            followBtn.setText("follow");
            if (isRequest){
                followBtn.setText("requested");
                followBtn.setDisable(true);
            }
            lastseenLabel.setVisible(false);
            if (visitor.profile.lastSeenShow==0){
                lastseenLabel.setVisible(true);
            }
            messageBtn.setDisable(true);
            muteBtn.setDisable(true);
        }
    }
    void laodTweet(){
        vbox.getChildren().clear();
        ArrayList<Tweet> tweetArrayList = getListOfMyTweetListener.getListOfVisitor();
        for (int i=tweetArrayList.size()-1;i>-1;i--){
            Tweet tweet = tweetArrayList.get(i);
            boolean check =true;
            for (int id : tweet.reportedBy){
                if (id == MainStage.getInstance().user.getId())check=false;
            }
            if (check) {
                FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource(MyConfig.instance.pageAddressProperties.getProperty("TweetPane")));
                try {
                    Parent root = fxmlLoader1.load();

                    TweetPaneController tweetPaneController = fxmlLoader1.getController();
                    tweetPaneController.loadData(tweet, MainStage.getInstance().user.getId());
                    vbox.getChildren().add(new AnchorPane(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
   public void followAction(){
        if (isFollowing){
            followListener.eventOccur("unfollow"  , user.getId() , visitor.getId());
        }
        else{

            followListener.eventOccur("follow" , user.getId() , visitor.getId());
        }
    }
    public void block(){
        blockListener.eventOccur("block" , user.getId() , visitor.getId());
    }
    public void home(){
        backHomeListener.eventOccur("home");
    }

    public void backk(){
        backHomeListener.eventOccur("back");
    }
    public void mute(){
        if(ismute){
            followListener.eventOccur("unmute" , user.getId() , visitor.getId());
            return;
        }
        followListener.eventOccur("mute" , user.getId() , visitor.getId());
        return;
    }
    public void message(){
        messageListener.loadPlatformPage(messageListener.sendToFollowing(visitor.profile.username));
    }
}
