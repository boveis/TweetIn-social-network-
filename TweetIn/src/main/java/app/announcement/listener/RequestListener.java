package app.announcement.listener;

import app.announcement.controller.RequestController;
import controller.Controller;
import view.MainStage;

public class RequestListener {
    RequestController requestController = new RequestController();
    public void eventOccur(String s , int following){
        if (s.equals("accept")){
            requestController.accept(following , MainStage.getInstance().user.getId());

        }
        if (s.equals("reject")){
            requestController.reject(following  , MainStage.getInstance().user.getId());
        }
        if(s.equals("rejectWithoutTelling")){
            requestController.rejectWithoutTelling(following,MainStage.getInstance().user.getId());
        }
        MainStage.getInstance().user = requestController.getUser(MainStage.getInstance().user.getId());
        MainStage.getInstance().refresh();
        return;
    }

}
