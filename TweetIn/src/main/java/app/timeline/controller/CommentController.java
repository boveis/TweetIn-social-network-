package app.timeline.controller;

import context.TweetDB;
import model.tweet.Tweet;
import model.user.User;


public class CommentController extends TweetController{
    public void addComment(Tweet comment){
        TweetDB.add(comment);
        Tweet tweet = TweetDB.getTweet(comment.fatherTweet);
        User user = getUser(tweet.author_id);
        user.announcement.add(getUsername(comment.author_id )+"make comment for this tweet of you\n"+tweet.text);
        updateUser(user);
        tweet.comments.add(comment.id);
        TweetDB.update(tweet);
    }

}
