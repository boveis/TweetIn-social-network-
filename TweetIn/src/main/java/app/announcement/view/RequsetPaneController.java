package app.announcement.view;

import app.announcement.listener.RequestListener;
import app.explore.listener.GetUserPictureListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RequsetPaneController {

    int id;
    String username;
    GetUserPictureListener getUserPictureListener = new GetUserPictureListener();
    RequestListener requestListener;
    @FXML
    private Label usernameLabel;

    @FXML
    private ImageView imageView;
    public void loadData(int id , String username){
        this.id = id;
        this.username = username;
        requestListener = new RequestListener();
        File file = getUserPictureListener.getUserImage(id);
        if(file!=null){
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);

            imageView.setImage(image);
        }
        usernameLabel.setText(username);
    }
    public void accept(){
        requestListener.eventOccur("accept" , id);
    }
    public void reject(){
        requestListener.eventOccur("reject" , id);
    }
    public void rejectWithoutMSG(){
        requestListener.eventOccur("rejectWithoutTelling" , id);
    }
}
