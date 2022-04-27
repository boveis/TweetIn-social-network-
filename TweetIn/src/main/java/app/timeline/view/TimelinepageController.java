package app.timeline.view;

import app.setting.listener.BackHomeListener;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TimelinepageController implements Initializable {

    @FXML
    private VBox vbox;




    GetListOfMyTweetListener getListOfMyTweetListener = new GetListOfMyTweetListener();
    BackHomeListener backHomeListener = new BackHomeListener();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Tweet> timeline = getListOfMyTweetListener.getListOfTimeline();
        for(int i= timeline.size()-1 ; i>-1 ; i--){
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource(MyConfig.instance.pageAddressProperties.getProperty("TweetPane")));
            try {
                Parent root = fxmlLoader1.load();

                TweetPaneController tweetPaneController = fxmlLoader1.getController();
                tweetPaneController.loadData(timeline.get(i) , MainStage.getInstance().user.getId());
                vbox.getChildren().add(new AnchorPane(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
