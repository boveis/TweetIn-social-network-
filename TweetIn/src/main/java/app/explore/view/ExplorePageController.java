package app.explore.view;

import app.explore.listener.ExploreListener;
import app.setting.listener.BackHomeListener;
import app.timeline.listener.GetListOfMyTweetListener;
import app.timeline.view.TweetPaneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.tweet.Tweet;
import view.MainStage;
import view.MyConfig;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExplorePageController implements Initializable {
    GetListOfMyTweetListener getListOfMyTweetListener = new GetListOfMyTweetListener();
    BackHomeListener backHomeListener = new BackHomeListener();
    ExploreListener exploreListener = new ExploreListener();
    @FXML
    private TextField textField;

    @FXML
    private Label usernameLabel;

    @FXML
    private VBox vbox;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setText("");
        loadTweet();
    }
    void loadTweet(){
        ArrayList<Tweet> tweetArrayList = getListOfMyTweetListener.getListOfExplore();
        for (Tweet tweet :tweetArrayList){
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource(MyConfig.instance.pageAddressProperties.getProperty("TweetPane")));
            try {
                Parent root = fxmlLoader1.load();

                TweetPaneController tweetPaneController = fxmlLoader1.getController();
                tweetPaneController.loadData(tweet , MainStage.getInstance().user.getId());
                vbox.getChildren().add(new AnchorPane(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void chercher(){
        if (textField.getText().equals("")){
            usernameLabel.setText("write username");
            return;
        }
        exploreListener.seeUserPage(textField.getText());
        return;
    }

    public void back(){
        backHomeListener.eventOccur("back");
    }


    public void home(){
        backHomeListener.eventOccur("home");
    }

    public void refresh(){
        backHomeListener.eventOccur("refresh");
    }






}
