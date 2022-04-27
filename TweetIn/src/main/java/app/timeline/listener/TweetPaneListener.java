package app.timeline.listener;

import Log.Logger;
import app.message.Listener.GetMessagePictureListener;
import app.message.view.ReceiverChoosePageController;
import app.timeline.controller.NewTweetController;
import app.timeline.controller.TweetController;
import app.timeline.controller.TweetPictureController;
import app.timeline.view.NewCommentPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import listener.GetIdListener;
import model.message.Message;
import model.tweet.Tweet;
import view.MainStage;
import view.MyConfig;

import java.io.IOException;

public class TweetPaneListener {
    TweetController tweetController = new TweetController();
    GetIdListener getIdListener = new GetIdListener();
    public void like(Tweet tweet  , boolean like) {
        if (like) {
            tweetController.likeTweet(tweet, MainStage.getInstance().user.getId());
        } else {
            tweetController.UnlikeTweet(tweet, MainStage.getInstance().user.getId());
        }
    }

    public void report(Tweet tweet){
        Logger.logReport(tweet.id);
        tweetController.report(tweet , MainStage.getInstance().user.getId());
        refresh();
    }
    public void refresh(){
        MainStage.getInstance().user = tweetController.getUser(MainStage.getInstance().user.getId());
        MainStage.getInstance().refresh();
    }
    public void retweet(Tweet tweet){

        NewTweetController newTweetController = new NewTweetController();
        Tweet newTweet = new Tweet(MainStage.getInstance().user.getId() , tweetController.getUsername(tweet.author_id)+":\n"+tweet.text , tweet.hasImage , getIdListener.getTweetId(),0,false);
        newTweetController.addTweet(newTweet);
        if (tweet.hasImage){
            TweetPictureController tweetPictureController = new TweetPictureController();
            tweetPictureController.copyPicture(tweet.id , newTweet.id);
        }
        refresh();
        return;
    }
    public void sendAsMessage(Tweet tweet){

        Message message = new Message(MainStage.getInstance().user.getId() ,
                tweetController.getUsername(tweet.author_id)+":\n"+
                tweet.text ,
                tweet.hasImage ,
                0 ,
                true);
        if (tweet.hasImage){
            GetMessagePictureListener getMessagePictureListener = new GetMessagePictureListener();
            message = getMessagePictureListener.copyPictureTweet(message  , tweet.id);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MyConfig.instance.pageAddressProperties.getProperty("ChooseReciever")));
        try {
            Parent root = fxmlLoader.load();
            ReceiverChoosePageController receiverChoosePageController = fxmlLoader.getController();
            receiverChoosePageController.loadData(message , MainStage.getInstance().user);
            Scene scene = new Scene(root);
            MainStage.getInstance().stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void seeComment(Tweet tweet){
        MainStage.getInstance().tweet = tweetController.getTweet(tweet.id);
        MainStage.getInstance().user = tweetController.getUser(MainStage.getInstance().user.getId());
        MainStage.getInstance().setPage(MyConfig.instance.pageAddressProperties.getProperty("CommentPage"));

    }
}
