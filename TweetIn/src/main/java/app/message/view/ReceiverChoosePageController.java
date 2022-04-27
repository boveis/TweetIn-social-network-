package app.message.view;

import app.message.Listener.MessageListener;
import app.message.Listener.MessagePlatformListener;
import app.personal.listener.GetListOfPeopleListener;
import app.setting.listener.BackHomeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import model.message.Message;
import model.message.MessagePlatform;
import model.user.User;
import java.util.ArrayList;

public class ReceiverChoosePageController {
    MessagePlatformListener messagePlatformListener = new MessagePlatformListener();
    GetListOfPeopleListener getListOfPeopleListener = new GetListOfPeopleListener();
    BackHomeListener backHomeListener = new BackHomeListener();
    MessageListener messageListener  = new MessageListener();
    Message message;
    User user;

    ObservableList<String> followingList = FXCollections.observableArrayList();
    ObservableList<String> groupList = FXCollections.observableArrayList();
    ObservableList<String> messageList = FXCollections.observableArrayList();

    @FXML
    private ListView<String> chatListView;

    @FXML
    private ListView<String> followingListView;

    @FXML
    private ListView<String> groupListView;

    public void loadData(Message message  , User user){
        this.user = user;
        this.message =message;

        groupList.clear();
        for (int i = 0; i < user.groups.size(); i++) {
            groupList.add(user.groups.get(i).name);
        }
        groupListView.getItems().removeAll();
        groupListView.setItems(groupList);

        messageList.clear();
        for (int i= user.messagePlatform.size()-1 ; i>-1 ; i--) {
            messageList.add(messagePlatformListener.getName(messagePlatformListener.getMessageplatform(user.messagePlatform.get(i))));
        }
        chatListView.getItems().removeAll();
        chatListView.setItems(messageList);

        followingList.clear();
        ArrayList<String> tmp = getListOfPeopleListener.getUsername(user.following);
        for (int i=0  ; i<tmp.size() ;i++) {
            followingList.add(tmp.get(i));
        }
        followingListView.getItems().removeAll();
        followingListView.setItems(followingList);

        followingListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        groupListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        chatListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    public void SentToFollowing(){
        ObservableList<String> stringObservableList = followingListView.getSelectionModel().getSelectedItems();
        if(stringObservableList.size()==0){
            return;
        }
        for (String name :stringObservableList){
            MessagePlatform messagePlatform = messageListener.sendToFollowing(name);
            messagePlatform.messages.add(message);
            messageListener.update(messagePlatform);
        }
        back();
    }
    public void SentToGroup(){
        ObservableList<String> stringObservableList = groupListView.getSelectionModel().getSelectedItems();
        if(stringObservableList.size()==0){
            return;
        }
        for (String name :stringObservableList){
            for (int i=0 ; i<user.groups.size() ; i++){
                if (user.groups.get(i).name.equals(name)){
                    MessagePlatform messagePlatform = messageListener.sendGroup(user.groups.get(i));
                    messagePlatform.messages.add(message);
                    messageListener.update(messagePlatform);
                }
            }
        }
        back();
    }
    public void SentToChat(){

        ObservableList<String> stringObservableList = chatListView.getSelectionModel().getSelectedItems();
        if(stringObservableList.size()==0){
            return;
        }
        for (String name :stringObservableList){
            MessagePlatform messagePlatform = messageListener.seeChat(name);
            messagePlatform.messages.add(message);
            messageListener.update(messagePlatform);
        }
        back();
    }
    public void back(){

        backHomeListener.eventOccur("refresh");
    }


}
