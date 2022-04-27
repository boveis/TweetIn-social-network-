package app.timeline.listener;

import app.timeline.controller.CommentController;
import app.timeline.controller.TweetController;
import model.tweet.Tweet;
import view.MainStage;


public class CommentListener {
    CommentController commentController = new CommentController();

    public void AddComment(Tweet comment){
        commentController.addComment(comment);
    }
    public void back(){
        if (MainStage.getInstance().tweet.isComment) {
            MainStage.getInstance().user = commentController.getUser(MainStage.getInstance().user.getId());
            MainStage.getInstance().tweet =commentController.getTweet(MainStage.getInstance().tweet.fatherTweet);
            MainStage.getInstance().back();
            return;
        }
        MainStage.getInstance().back();
    }

}
