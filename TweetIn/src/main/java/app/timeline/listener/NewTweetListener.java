package app.timeline.listener;

import Log.Logger;
import app.timeline.controller.NewTweetController;
import controller.Controller;
import model.tweet.Tweet;
import view.MainStage;

public class NewTweetListener {
    NewTweetController newTweetController = new NewTweetController();

    public void eventOccured(Tweet tweet){
        newTweetController.addTweet(tweet);
        MainStage.getInstance().user=newTweetController.getUser(MainStage.getInstance().user.getId());
        MainStage.getInstance().back();
    }
}
