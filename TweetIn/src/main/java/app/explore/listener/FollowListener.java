package app.explore.listener;

import app.explore.controller.ExploreController;
import view.MainStage;

public class FollowListener {

    ExploreController exploreController = new ExploreController();


    public void eventOccur(String string  , int userId , int visitorId){
        if (string.equals("unfollow")){
            exploreController.unfollow(userId , visitorId);
        }
        if(string.equals("follow")){
            exploreController.follow(userId , visitorId);
        }
        if(string.equals("mute")){
            exploreController.mute(userId , visitorId);
        }
        if(string.equals("unmute")){
                exploreController.unmute(userId , visitorId);
        }
        MainStage.getInstance().user = exploreController.getUser(userId);
        MainStage.getInstance().visitor=exploreController.getUser(visitorId);
        MainStage.getInstance().refresh();
        return;
    }

}
