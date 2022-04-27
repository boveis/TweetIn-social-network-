package model.tweet;

import context.Context;
import context.TweetDB;

import java.util.ArrayList;

public class Tweet {
    public int id;
    public int author_id;
    public String text;
    public ArrayList<Integer> reportedBy = new ArrayList<>();
    public boolean hasImage = false;
    public ArrayList<Integer> likes;
    public ArrayList<Integer> comments;
    public int fatherTweet;
    public boolean isComment ;
    public Tweet(int author, String text , boolean hasImage  , int id  ,int fatherTweet ,  boolean isComment) {
        this.text = text;
        this.author_id = author;
        comments = new ArrayList<>();
        likes = new ArrayList<>();
        this.id =id;
        this.isComment = isComment;
        this.hasImage = hasImage;
        TweetDB.add(this);
        this.fatherTweet = fatherTweet;
    }




}
