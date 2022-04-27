package app.explore.view;
import app.explore.listener.BlockListener;
import app.setting.listener.BackHomeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.user.User;
import view.MainStage;
import java.net.URL;
import java.util.ResourceBundle;

public class BlockedAccountPageController implements Initializable {
    User user;
    User visitor;
    BlockListener blockListener;
    BackHomeListener backHomeListener;
    @FXML
    private Label usernameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = MainStage.getInstance().user;
        visitor= MainStage.getInstance().visitor;
        backHomeListener=new BackHomeListener();
        usernameLabel.setText(visitor.profile.username);
        blockListener= new BlockListener();
    }
    public void back(){
        backHomeListener.eventOccur("back");
    }
    public void home(){
        backHomeListener.eventOccur("home");
    }
    public void unblock(){
        blockListener.eventOccur("unblock" , user.getId() , visitor.getId());
    }
}
