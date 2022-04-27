package app.timeline.listener;

import app.timeline.controller.TweetController;
import context.TweetDB;
import model.tweet.Tweet;
import view.MainStage;

import java.util.ArrayList;

public class GetListOfMyTweetListener {
    TweetController tweetController = new TweetController();

    public ArrayList<Tweet> eventOccur(ArrayList<Integer> integerArrayList){
        return tweetController.getListOfTweet(integerArrayList);
    }
    public ArrayList<Tweet> getListOfTimeline(){
        ArrayList<Tweet> tweetArrayList = eventOccur(MainStage.getInstance().user.timeline);
        ArrayList<Tweet> finalList = new ArrayList<>();
        for (Tweet tweet :tweetArrayList){
            boolean checkreport = true;
            boolean checkFollowing =false;
            boolean checkMute  = true;
            boolean checkBlock = true;
            for (Integer id :tweet.reportedBy){
                if (id == MainStage.getInstance().user.getId())checkreport=false;
            }
            for (Integer id :MainStage.getInstance().user.blocked){
                if (id == tweet.author_id)checkBlock=false;
            }

            for (Integer id :MainStage.getInstance().user.muted){
                if (id == tweet.author_id)checkMute=false;
            }
            for (Integer id :MainStage.getInstance().user.following){
                if (id == tweet.author_id)checkFollowing=true;
            }
            if (checkBlock && checkFollowing && checkMute && checkreport ){
                finalList.add(tweet);
            }
        }
        return  finalList;
    }
    public ArrayList<Tweet> getListOfExplore(){
        return tweetController.getRandomTweet(MainStage.getInstance().user , 20);
    }
    public ArrayList<Tweet> getListOfVisitor(){
        ArrayList<Tweet> tweetArrayList = tweetController.getListOfTweet(MainStage.getInstance().visitor.tweets);
        for (int i=0 ; i<tweetArrayList.size() ; i++){
            if (tweetController.existUser(tweetArrayList.get(i).reportedBy , MainStage.getInstance().user.getId())){
                tweetArrayList.remove(i);
                i--;
            }
        }
        return tweetArrayList;
    }
    public Tweet getTweet(int id){
        return tweetController.getTweet(id);
    }
}
