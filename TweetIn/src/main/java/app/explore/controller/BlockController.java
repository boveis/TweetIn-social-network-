package app.explore.controller;

import controller.Controller;
import model.user.User;

public class BlockController extends ExploreController {
    public BlockController(){
        super();
    }
    public void unblock(int userId , int visitorId){
        User user = getUser(userId);
        User visitor  = getUser(visitorId);
        user.blocked=deleteUser(user.blocked , visitorId);
        visitor.blockedBy=deleteUser(visitor.blockedBy , userId);
        updateUser(user);
        updateUser(visitor);
        return;
    }
    public void block(int userId , int visitorId){
        unfollow(userId,visitorId);
        User user = getUser(userId);
        User visitor = getUser(visitorId);
        user.blocked.add(visitorId);
        visitor.blockedBy.add(userId);
        updateUser(user);
        updateUser(visitor);
        return;
    }
}
