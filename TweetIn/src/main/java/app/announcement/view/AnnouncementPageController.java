package app.announcement.view;

import app.announcement.listener.AnnouncementListener;
import app.personal.listener.GetListOfPeopleListener;
import app.personal.view.MyTweetPaneController;
import app.setting.listener.BackHomeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.tweet.Tweet;
import model.user.User;
import view.MainStage;
import view.MyConfig;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AnnouncementPageController implements Initializable {
    AnnouncementListener announcementListener = new AnnouncementListener();
    GetListOfPeopleListener getListOfPeopleListener = new GetListOfPeopleListener();
    BackHomeListener backHomeListener = new BackHomeListener();
    User user;
    @FXML
    private ListView<String> listview;

    @FXML
    private VBox Vbox;
    @FXML
    private ListView<String> requestedname;
    ObservableList<String> yourRequestList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = MainStage.getInstance() . user;
        laodRequest();
        loadAnnouncement();

        yourRequestList.removeAll();
        ArrayList<String> tmpList = getListOfPeopleListener.getUsername(user.followRequests);
        for (String s : tmpList) {
            yourRequestList.add(s);
        }
        requestedname.getItems().removeAll();
        requestedname.getItems().addAll(yourRequestList);
    }
    void laodRequest(){
        Vbox.getChildren().clear();
        ArrayList<Integer> integers = announcementListener.getReqestList();
        for (int i=0 ; i<integers.size() ; i++){


                 FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource(MyConfig.instance.pageAddressProperties.getProperty("RequestPane")));
                try {
                    Parent root = fxmlLoader1.load();
                    RequsetPaneController requsetPaneController = fxmlLoader1.getController();
                    requsetPaneController.loadData(integers.get(i) , getListOfPeopleListener.getUsername(integers.get(i)));
                    Vbox.getChildren().add(new AnchorPane(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    void loadAnnouncement (){
        ObservableList<String> list = FXCollections.observableArrayList();
        for (int i = user.announcement.size()-1 ; i>-1; i--) {
            list.add(user.announcement.get(i));
        }
        listview.getItems().removeAll();
        listview.getItems().addAll(list);
        announcementListener.seeAnnouncement();
    }
    public void back(){
        backHomeListener.eventOccur("back");
    }
    public void home(){
        backHomeListener.eventOccur("home");
    }
}
