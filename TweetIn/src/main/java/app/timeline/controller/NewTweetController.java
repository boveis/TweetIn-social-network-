package app.timeline.controller;

import context.TweetDB;
import context.UserDB;
import model.tweet.Tweet;
import model.user.User;

public class NewTweetController extends TweetController {


    public void addTweet(Tweet tweet){
        TweetDB.add(tweet);
        User user = UserDB.getUser(tweet.author_id);
        user.tweets.add(tweet.id);
        UserDB.update(user);
        for (int i=0 ; i<user.followers.size() ; i++){
            User tmp = UserDB.getUser(user.followers.get(i));
            tmp.timeline.add(tweet.id);
            UserDB.update(tmp);
        }
        return;
    }

}
