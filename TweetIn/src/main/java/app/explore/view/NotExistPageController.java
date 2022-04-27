package app.explore.view;

import app.setting.listener.BackHomeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NotExistPageController {
    BackHomeListener backHomeListener = new BackHomeListener();
    @FXML
    private Label usernameLabel;

    public  void loadData(String name){
        this.usernameLabel.setText(name);
    }
    public void back()   {
        backHomeListener.eventOccur("refresh");
    }



}
