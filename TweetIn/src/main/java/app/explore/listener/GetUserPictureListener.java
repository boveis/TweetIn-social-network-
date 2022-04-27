package app.explore.listener;

import app.personal.controller.PersonalController;
import app.setting.controller.SettingController;
import javafx.scene.image.Image;
import view.MainStage;

import java.io.File;

public class GetUserPictureListener {

    PersonalController personalController = new PersonalController();
    public File getUserImage(int id){
        return personalController.getPicture( "user", id);
    }
    public void saveImage(File file){
        personalController.saveImage(file , MainStage.getInstance().user.getId());
    }
}
