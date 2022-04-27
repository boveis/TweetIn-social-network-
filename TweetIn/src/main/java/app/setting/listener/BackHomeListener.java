package app.setting.listener;

import controller.Controller;
import view.MainStage;
import view.MyConfig;

public class BackHomeListener {
    Controller controller = new Controller();
    public void eventOccur(String s){

        if (s.equals("back")){
            MainStage.getInstance().back();
            return;
        }
        if(s.equals("home")){
                MainStage.getInstance().setPage(MyConfig.instance.pageAddressProperties.getProperty("PersonalPage"));
                return;
        }
        if (s.equals("refresh")){
            MainStage.getInstance().user = controller.getUser(MainStage.getInstance().user.getId());
            MainStage.getInstance().refresh();
            return;
        }
    }
}
