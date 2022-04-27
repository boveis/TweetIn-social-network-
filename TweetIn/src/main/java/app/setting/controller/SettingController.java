package app.setting.controller;

import app.message.Listener.GroupPageListener;
import app.message.controller.MessageController;
import app.message.view.MessagePlatformPageController;
import app.timeline.controller.TweetController;
import context.ImageDB;
import context.MessagePlatformDB;
import context.TweetDB;
import context.UserDB;
import controller.SignUp_In_Controller;
import javafx.scene.image.Image;
import model.message.MessagePlatform;
import model.tweet.Tweet;
import model.user.GroupOfPeaple;
import model.user.User;
import org.graalvm.compiler.lir.alloc.lsra.IntervalWalker;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SettingController extends SignUp_In_Controller {
    public SettingController(){
        super();
    }
    MessageController messageController = new MessageController();
    public void logOut(User user){
        user.profile.lastseen= LocalDateTime.now().toString();
        updateUser(user);
    }
    public void deleteAccount(User user){
        ArrayList<User> userArrayList = UserDB.allUsers();
        for (User tmp:userArrayList){
            if (user.getId()!=tmp.getId()) {
                tmp.blocked=deleteUser(tmp.blocked , user.getId());
                tmp.blockedBy =deleteUser(tmp.blockedBy , user.getId());
                tmp.followers=deleteUser(tmp.followers , user.getId());
                tmp.following=deleteUser(tmp.following , user.getId());
                tmp.followRequests=deleteUser(tmp.followRequests , user.getId());
                tmp.muted=deleteUser(tmp.muted , user.getId());
                tmp.followerRequest=deleteUser(tmp.followerRequest , user.getId());

                ArrayList<Tweet> tweetArrayList = TweetDB.allTweetOfList(tmp.timeline);
                tmp.timeline = new ArrayList<>();
                for (int j=0 ; j<tweetArrayList.size() ; j++){
                    if (tweetArrayList.get(j).author_id==user.getId()){

                    }
                    else{
                        tmp.timeline.add(tweetArrayList.get(j).id);
                    }
                }
                for(int k=0 ; k<tmp.groups.size() ; k++){
                    GroupOfPeaple groupOfPeaple = tmp.groups.get(k);
                    for(int i=0 ; i<groupOfPeaple.usersId.size() ; i++){
                        if (groupOfPeaple.usersId.get(i)==user.getId()){
                            tmp.groups.get(k).usersId.remove(i);
                            groupOfPeaple.usersId.remove(i);
                            i--;
                        }
                    }
                }
                UserDB.update(tmp);
            }

        }
        ArrayList<Integer> idTweet = new ArrayList<>();
        ArrayList<Tweet> allTweet = TweetDB.AllTweet();
        for (Tweet tweet:allTweet){
            tweet.likes = deleteUser(tweet.likes , user.getId());
            if (tweet.author_id==user.getId()){
                idTweet.add(tweet.id);
                if (tweet.isComment){
                    Tweet father = TweetDB.getTweet(tweet.fatherTweet);
                    father.comments = deleteUser(father.comments , tweet.id);
                    TweetDB.update(father);
                }
            }
            TweetDB.update(tweet);
        }
        for (int i=0 ; i<idTweet.size() ; i++){
            if (TweetDB.existTweet(idTweet.get(i))){
                TweetDB.remove(idTweet.get(i));
            }
        }
        ArrayList<MessagePlatform> messagePlatformArrayList =MessagePlatformDB.AllMessagePlatform();
        for (int i=0 ; i<messagePlatformArrayList.size() ; i++){
            MessagePlatform tmp  = messagePlatformArrayList.get(i);
            if (tmp.type==1){
                if (tmp.users.get(0)==user.getId() || tmp.users.get(1)==user.getId()){
                    User user1 = UserDB.getUser(tmp.users.get(0));
                    User user2 = UserDB.getUser(tmp.users.get(1));
                        user1.messagePlatform = deleteUser(user1.messagePlatform , tmp.id);
                    user2.messagePlatform = deleteUser(user2.messagePlatform , tmp.id);
                    UserDB.update(user1);
                    UserDB.update(user2);
                }
            }
            if (tmp.type==2){
                if (existUser(tmp.users , user.getId())){
                    tmp.users= deleteUser(tmp.users , user.getId());
                    for (int j=0 ; j<tmp.messages.size() ; j++){
                        if(tmp.messages.get(j).sender == user.getId()){
                            tmp.messages.remove(j);
                            j--;
                        }
                    }
                }
            }
            MessagePlatformDB.update(tmp);
        }
        UserDB.removeUser(user.getId());

    }
    public void changeImage(String field , int id, File file) {
        try {
            ImageDB.saveImage(field , id , file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
