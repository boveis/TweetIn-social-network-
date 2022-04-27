package app.personal.controller;

import app.setting.event.FileEvent;
import context.ImageDB;
import controller.Controller;
import javafx.scene.image.Image;
import java.io.File;
import java.io.IOException;

public class PersonalController extends Controller {

    public File getPicture(String field,int id){
            File file = new File("src\\main\\resources\\context\\image\\user\\"+id+".png");
            if (!file.exists()){

                return null;
            }
            return file;
    }
    public void saveImage(File file , int id){
        try {
            ImageDB.saveImage("user" , id , file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
