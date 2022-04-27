package app.explore.listener;

import app.explore.view.NotExistPageController;
import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import view.MainStage;
import view.MyConfig;

import java.io.IOException;

public class ExploreListener {
    Controller controller = new Controller();
    public void seeUserPage(String username){
        int id  = controller.getId(username);
        if(id == -1 || !controller.getUser(id).profile.isActive || controller.existUser(controller.getUser(id).blocked , MainStage.getInstance().user.getId())){
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource(MyConfig.instance.pageAddressProperties.getProperty("NotExistUser")));
            try {
                Parent root = fxmlLoader1.load();
                NotExistPageController notExistPageController = fxmlLoader1.getController();
                notExistPageController.loadData(username);
                Scene scene = new Scene(root);
                MainStage.getInstance().stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        if (controller.existUser(MainStage.getInstance().user.blocked , id)){
            MainStage.getInstance().user = controller.getUser(MainStage.getInstance().user.getId());

            MainStage.getInstance().visitor = controller.getUser(id);
            MainStage.getInstance().setPage(MyConfig.instance.pageAddressProperties.getProperty("BlockedAccountPage"));
            return;
        }
        MainStage.getInstance().user = controller.getUser(MainStage.getInstance().user.getId());
        MainStage.getInstance().visitor = controller.getUser(id);
        MainStage.getInstance().setPage(MyConfig.instance.pageAddressProperties.getProperty("UserProfile"));
        return;
    }
}
