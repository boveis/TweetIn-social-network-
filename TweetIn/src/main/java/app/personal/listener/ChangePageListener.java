package app.personal.listener;

import controller.Controller;
import view.MainStage;

public class ChangePageListener {
    Controller controller  = new Controller();
    public void eventOccur(String path){
        MainStage.getInstance().user = controller.getUser(MainStage.getInstance().user.getId());
        MainStage.getInstance().setPage(path);
    }
}
