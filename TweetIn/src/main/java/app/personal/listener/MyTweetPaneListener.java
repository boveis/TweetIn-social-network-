package app.personal.listener;

import app.personal.controller.MyTweetController;
import app.timeline.view.NewCommentPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.tweet.Tweet;
import view.MainStage;
import view.MyConfig;

import java.io.IOException;

public class MyTweetPaneListener {
    MyTweetController myTweetController = new MyTweetController();
    public void delete(Tweet tweet){
            myTweetController.deleteTweet(tweet);
            MainStage.getInstance().user = myTweetController.getUser(MainStage.getInstance().user.getId());
            MainStage.getInstance().refresh();
    }
    public void addComment(Tweet tweet){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MyConfig.instance.pageAddressProperties.getProperty("NewComment")));
        try {
            Parent root = fxmlLoader.load();
            NewCommentPageController newCommentPageController = fxmlLoader.getController();
            newCommentPageController.loadDate(tweet);
            Scene scene = new Scene(root);
            MainStage.getInstance().stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
}
