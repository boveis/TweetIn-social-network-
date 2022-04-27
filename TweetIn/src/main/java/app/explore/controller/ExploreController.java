package app.explore.controller;

import context.TweetDB;
import controller.Controller;
import model.user.User;

public class ExploreController extends Controller {
    public ExploreController(){
        super();
    }
    public void unfollow(int userId , int visitorId){
        User user = getUser(userId);
        User visitor = getUser(visitorId);
        user.following=deleteUser(user.following , visitorId);
        visitor.followers=deleteUser(visitor.followers , userId);
        for (int i=0 ; i<user.timeline.size() ; i++){
            if(TweetDB.getTweet(user.timeline.get(i)).author_id==visitorId) {
                user.timeline.remove(i);
                i--;
            }
        }
        user.muted=deleteUser(user.muted , visitorId);
        visitor.announcement.add("user "+user.profile.username+" unfollowed you");
        updateUser(user);
        updateUser(visitor);
    }
    public void follow(int userId , int visitorId){
        User user = getUser(userId);
        User visitor = getUser(visitorId);

        if(visitor.profile.isPublic){
            if(!existUser(user.following , visitorId)){
                user.following.add(visitorId);

            }
            if(!existUser(visitor.followers , userId)){
                visitor.followers.add(userId);

            }
            visitor.announcement.add("user "+user.profile.username+" followed you");

        }else {
            if (!existUser(user.followRequests, visitorId)) {
                user.followRequests.add(visitorId);
            }
            if (!existUser(visitor.followerRequest, userId)) {
                visitor.followerRequest.add(userId);
            }
        }

        updateUser(user);
        updateUser(visitor);
    }
    public void mute(int userId , int visitorId){
        User user = getUser(userId);
        if(!existUser(user.muted , visitorId)){
            user.muted.add(visitorId);
        }
        updateUser(user);

    }
    public void unmute(int userId , int visitorId){
        User user = getUser(userId);
        user.muted = deleteUser(user.muted , visitorId);
        updateUser(user);
    }
}
