package app.timeline.controller;

import app.personal.controller.MyTweetController;
import context.TweetDB;
import context.UserDB;
import controller.Controller;
import model.tweet.Tweet;
import model.user.User;

import java.util.ArrayList;
import java.util.Random;

public class TweetController extends Controller {
    public ArrayList<Tweet> getListOfTweet(ArrayList<Integer> arrayList){
        ArrayList<Tweet> tweetArrayList = new ArrayList<>();
        for (int i=0 ; i<arrayList.size() ; i++){
            Tweet tmp = TweetDB.getTweet(arrayList.get(i));
            if(getUser(tmp.author_id).profile.isActive) {
                tweetArrayList.add(TweetDB.getTweet(arrayList.get(i)));
            }
        }
        return tweetArrayList;
    }
    public void likeTweet(Tweet tweet , int user){
        tweet.likes = deleteUser(tweet.likes, user);
        tweet.likes.add(user);
        TweetDB.update(tweet);
        User author = getUser(tweet.author_id);
        author.announcement.add(getUsername(user)+" like this tweet of you \n"+tweet.text);
        updateUser(author);
    }
    public void UnlikeTweet(Tweet tweet , int user){
        tweet.likes = deleteUser(tweet.likes, user);
        TweetDB.update(tweet);
        User author = getUser(tweet.author_id);
        author.announcement.add(getUsername(user)+" unlike this tweet of you \n"+tweet.text);
        updateUser(author);
    }
    public void report(Tweet tweet , int userId){
        if (!existUser(tweet.reportedBy , userId)){
            tweet.reportedBy.add(userId);
            if (tweet.reportedBy.size()>10){
                MyTweetController myTweetController = new MyTweetController() ;
                myTweetController.deleteTweet(tweet);
                User author = getUser(tweet.author_id);
                author.announcement.add("this tweet is remove because it report by many people!\n" +tweet.text);
                return;
            }
            TweetDB.update(tweet);
            User user = getUser(userId);
            user.timeline=deleteUser(user.timeline , tweet.id);
            updateUser(user);
        }
    }
    public Tweet getTweet(int id ){
        return TweetDB.getTweet(id);
    }
    public ArrayList<Tweet> getRandomTweet(User mainUser , int number){
        ArrayList<Tweet> tweetArrayList = new ArrayList<>();
        ArrayList<User> usersArrayList = UserDB.allUsers();
        for(User user : usersArrayList){
            if (mainUser.getId()!=user.getId() && user.profile.isActive && !existUser(user.blocked , mainUser.getId())){
                if(user.profile.isPublic || existUser(mainUser.following , user.getId())){
                    if(!existUser(mainUser.blocked , user.getId()) && !existUser(mainUser.muted , user.getId())){
                        for (Tweet tweet : getListOfTweet(user.tweets)){
                            if (!existUser(tweet.reportedBy , mainUser.getId())){
                                tweetArrayList.add(tweet);
                            }
                        }
                    }
                }
            }
        }
        while (tweetArrayList.size()>number){
            Random random = new Random();
            int tmp = random.nextInt(tweetArrayList.size());
            tweetArrayList.remove(tmp);
        }
        return tweetArrayList;
    }


}
