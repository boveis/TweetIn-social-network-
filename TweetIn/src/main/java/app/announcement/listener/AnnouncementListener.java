package app.announcement.listener;

import controller.Controller;
import view.MainStage;

import java.util.ArrayList;

public class AnnouncementListener {
    Controller controller = new Controller();
    public ArrayList<Integer> getReqestList(){
        return controller.getListOfInteger(MainStage.getInstance().user.followerRequest);
    }
    public void seeAnnouncement(){
        MainStage.getInstance().user.announcement = new ArrayList<>();
        controller.updateUser(MainStage.getInstance().user);
    }

}
