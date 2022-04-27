package model.user;

import context.Context;
import context.UserDB;
import model.message.MessagePlatform;
import org.graalvm.compiler.hotspot.aarch64.AArch64HotSpotRegisterAllocationConfig;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class User {

    private final int id;
    public int savedMessage;
    public Profile profile;

    public ArrayList<Integer> tweets;
    public ArrayList<Integer> comments;
    public ArrayList<Integer> following;
    public ArrayList<Integer> followers;
    public ArrayList<Integer> blocked;
    public ArrayList<Integer> muted;
    public ArrayList<Integer> followRequests;
    public ArrayList<Integer> followerRequest;
    public ArrayList<Integer> blockedBy;
    public ArrayList<GroupOfPeaple> groups;
    public ArrayList<Integer> timeline;
    public ArrayList<Integer> messagePlatform;
    public ArrayList<String> announcement;

    public User(
            String userName,
            String password,
            String name,
            String birthday,
            String email,
            String phone,
            String bio,
            int id
    ) {
        profile = new Profile(name , userName , email , phone , password , bio , birthday);
        this.id = id;
        comments = new ArrayList<>();
        tweets = new ArrayList<>();
        followers = new ArrayList<>();
        following = new ArrayList<>();
        blocked = new ArrayList<>();
        muted = new ArrayList<>();
        followRequests = new ArrayList<>();
        groups = new ArrayList<>();
        blockedBy = new ArrayList<>();
        timeline = new ArrayList<>();
        followerRequest = new ArrayList<>();
        messagePlatform = new ArrayList<>();
        announcement = new ArrayList<>();
    }














    public int getId() {
        return id;
    }

    public int getSavedMessage() {
        return savedMessage;
    }

    public void setSavedMessage(int savedMessage) {
        this.savedMessage = savedMessage;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }



    public ArrayList<Integer> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<Integer> tweets) {
        this.tweets = tweets;
    }

    public ArrayList<Integer> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Integer> comments) {
        this.comments = comments;
    }

    public ArrayList<Integer> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<Integer> following) {
        this.following = following;
    }

    public ArrayList<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<Integer> followers) {
        this.followers = followers;
    }

    public ArrayList<Integer> getBlocked() {
        return blocked;
    }

    public void setBlocked(ArrayList<Integer> blocked) {
        this.blocked = blocked;
    }

    public ArrayList<Integer> getMuted() {
        return muted;
    }

    public void setMuted(ArrayList<Integer> muted) {
        this.muted = muted;
    }

    public ArrayList<Integer> getFollowRequests() {
        return followRequests;
    }

    public void setFollowRequests(ArrayList<Integer> followRequests) {
        this.followRequests = followRequests;
    }

    public ArrayList<Integer> getBlockedBy() {
        return blockedBy;
    }

    public void setBlockedBy(ArrayList<Integer> blockedBy) {
        this.blockedBy = blockedBy;
    }

    public ArrayList<GroupOfPeaple> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<GroupOfPeaple> groups) {
        this.groups = groups;
    }


}
