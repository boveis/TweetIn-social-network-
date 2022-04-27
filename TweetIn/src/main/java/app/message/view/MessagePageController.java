package app.message.view;

import app.message.Listener.MessageListener;
import app.message.Listener.MessagePlatformListener;
import app.personal.listener.GetListOfPeopleListener;
import app.setting.listener.BackHomeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.message.Message;
import model.message.MessagePlatform;
import model.user.User;
import view.MainStage;

import java.lang.management.PlatformLoggingMXBean;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MessagePageController implements Initializable {
    ObservableList<String> followingList = FXCollections.observableArrayList();
    ObservableList<String> groupList = FXCollections.observableArrayList();
    ObservableList<String> messageList = FXCollections.observableArrayList();

    MessagePlatformListener messagePlatformListener = new MessagePlatformListener();
    GetListOfPeopleListener getListOfPeopleListener= new GetListOfPeopleListener();
    BackHomeListener backHomeListener = new BackHomeListener();
    MessageListener messageListener = new MessageListener();

    User user;
    public ArrayList<Integer> unreadArrayList = new ArrayList<>();

    @FXML
    private ComboBox<String> groupCombo;


    @FXML
    private ComboBox<String> followingCombo;

    @FXML
    private Button followingBtn;

    @FXML
    private Label chooseLabel;



    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = MainStage.getInstance().user;
        loadList();
        chooseLabel.setVisible(false);
    }
    void loadList(){
        groupList.clear();
        for (int i = 0; i < user.groups.size(); i++) {
            groupList.add(user.groups.get(i).name);
        }
        groupCombo.getItems().removeAll();
        groupCombo.setItems(groupList);


        loadUnread();
        sortPlatform();
        messageList.clear();
        for (int i= 0; i<user.messagePlatform.size() ; i++) {
            messageList.add(messagePlatformListener.getName(messagePlatformListener.getMessageplatform(user.messagePlatform.get(i)))+"#"+unreadArrayList.get(i));
        }
        listView.getItems().removeAll();
        listView.setItems(messageList);

        followingList.clear();
        ArrayList<String> tmp = getListOfPeopleListener.getUsername(user.following);
        for (int i=0  ; i<tmp.size() ;i++) {
            followingList.add(tmp.get(i));
        }
        followingCombo.getItems().removeAll();
        followingCombo.setItems(followingList);
    }
    void sortPlatform(){
        ArrayList<Integer> tmpArr = new ArrayList<>();
        ArrayList<Integer> messagePlatforms = new ArrayList<>();
        while (unreadArrayList.size()>0){
            int max = unreadArrayList.get(0);
           int index = 0;
            for (int i=0  ;i<unreadArrayList.size() ; i++){
                if (unreadArrayList.get(i)>max){
                    max = unreadArrayList.get(i);
                   index = i;
                }
            }
            tmpArr.add(unreadArrayList.get(index));
            messagePlatforms.add(user.messagePlatform.get(index));
            unreadArrayList.remove(index);
            user.messagePlatform.remove(index);
        }
        for (int i=0 ; i<tmpArr.size() ; i++){
            user.messagePlatform.add(messagePlatforms.get(i));
            unreadArrayList.add(tmpArr.get(i));
        }
    }
    void loadUnread (){
        unreadArrayList = new ArrayList<>();
        for (int i=0 ; i<user.messagePlatform.size() ; i++){
            MessagePlatform messagePlatform = messagePlatformListener.getMessageplatform(user.messagePlatform.get(i));
            int unread = 0;
            for (Message message:messagePlatform.messages){
                    if (!message.readUser(MainStage.getInstance().user.getId()))unread++;
            }
            unreadArrayList.add(unread);
        }
        return;
    }
    public void refresh(){
        backHomeListener.eventOccur("refresh");
    }

    public void back(){
        backHomeListener.eventOccur("back");
    }

    public void home(){
        backHomeListener.eventOccur("home");
    }

    public void groupManager() {
        messageListener.groupManagerPage();
    }
    public void seeChat(){
        ObservableList<String> stringObservableList = listView.getSelectionModel().getSelectedItems();
        if (stringObservableList.size()==0){
            chooseLabel.setVisible(true);
            return;
        }
        String[] strings = stringObservableList.get(0).split("#");
        messageListener.loadPlatformPage(messageListener.seeChat(strings[0]));
        return;
    }
    public void deleteChat(){
        ObservableList<String> stringObservableList = listView.getSelectionModel().getSelectedItems();
        if (stringObservableList.size()==0){
            chooseLabel.setVisible(true);
            return;
        }
        String[] strings = stringObservableList.get(0).split("#");
        messageListener.deleteChat(messageListener.seeChat(strings[0]));
        return;

    }
    public void sendToFollowing(){

        messageListener.loadPlatformPage(messageListener.sendToFollowing(followingCombo.getValue()));
    }
    public void sentToGroup(){
        for (int i=0 ; i<user.groups.size() ; i++){
            if (user.groups.get(i).name.equals(groupCombo.getValue())){
                messageListener.loadPlatformPage(messageListener.sendGroup(user.groups.get(i)));
                return;
            }
        }
    }
}
