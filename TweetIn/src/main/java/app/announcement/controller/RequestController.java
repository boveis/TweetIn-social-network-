package app.announcement.controller;

import com.google.gson.internal.$Gson$Preconditions;
import controller.Controller;
import model.user.User;

public class RequestController extends Controller {
    public RequestController(){
        super();
    }
    public void accept(int following , int user){
        User userFollowing = getUser(following);
        User userMain = getUser(user);
        userFollowing.followRequests = deleteUser(userFollowing.followRequests , user);

        if (!existUser(userFollowing.following , user))userFollowing.following.add(user);
        userMain.followerRequest = deleteUser(userMain.followerRequest , following);
        if(!existUser(userMain.followers , following))userMain.followers.add(following);
        userFollowing.announcement.add("user "+userMain.profile.username+" accept you");
        updateUser(userFollowing);
        updateUser(userMain);
    }

    public void reject(int following , int user){
        User userFollowing = getUser(following);
        User userMain = getUser(user);
        userFollowing.followRequests = deleteUser(userFollowing.followRequests , user);
        userMain.followerRequest = deleteUser(userMain.followerRequest , following);
        userFollowing.announcement.add("user "+userMain.profile.username+" reject your request to follow");
        updateUser(userFollowing);
        updateUser(userMain);
    }
    public void rejectWithoutTelling(int following , int user){
        User userFollowing = getUser(following);
        User userMain = getUser(user);
        userFollowing.followRequests = deleteUser(userFollowing.followRequests , user);
        userMain.followerRequest = deleteUser(userMain.followerRequest , following);
        updateUser(userFollowing);
        updateUser(userMain);
    }
}
