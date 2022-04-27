package app.timeline.view;

import app.setting.listener.BackHomeListener;
import app.timeline.listener.CommentListener;
import app.timeline.listener.GetListOfMyTweetListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.tweet.Tweet;
import view.MainStage;
import view.MyConfig;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CommentPageController implements Initializable {
    CommentListener commentListener = new CommentListener();
    Tweet tweet;
    BackHomeListener backHomeListener = new BackHomeListener();
    GetListOfMyTweetListener getListOfMyTweetListener = new GetListOfMyTweetListener();

    @FXML
    private VBox Vbox;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tweet = MainStage.getInstance().tweet;
        loadTweet();



    }
    void loadTweet(){
        Vbox.getChildren().clear();
        for (int i=tweet.comments.size()-1; i>-1 ; i--){
                FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource(MyConfig.instance.pageAddressProperties.getProperty("TweetPane")));
                try {
                    Parent root = fxmlLoader1.load();
                    TweetPaneController tweetPaneController = fxmlLoader1.getController();
                    tweetPaneController.loadData(getListOfMyTweetListener.getTweet(tweet.comments.get(i)) , MainStage.getInstance().user.getId());
                    Vbox.getChildren().add(new AnchorPane(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
       }
    }

    public void back(){
        commentListener.back();
    }
    public void home(){
        backHomeListener.eventOccur("home");
    }



}
