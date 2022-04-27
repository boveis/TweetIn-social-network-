package app.personal.controller;

import context.TweetDB;
import context.UserDB;
import controller.Controller;
import model.tweet.Tweet;
import model.user.User;

import java.util.ArrayList;

public class MyTweetController  extends Controller {
    public MyTweetController (){
        super();
    }
    public void deleteTweet(Tweet tweet){
        ArrayList<User> allUserArrayList  = UserDB.allUsers();
        for (User user :allUserArrayList){
                user.timeline = deleteUser(user.timeline , tweet.id);
            updateUser(user);
        }
        User user = getUser(tweet.author_id);
        user.tweets = deleteUser(user.tweets , tweet.id);
        updateUser(user);
        TweetDB.remove(tweet);
    }
}
