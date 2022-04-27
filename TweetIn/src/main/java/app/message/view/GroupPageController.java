package app.message.view;

import app.message.Listener.GroupPageListener;
import app.personal.listener.GetListOfPeopleListener;
import app.setting.listener.BackHomeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.user.GroupOfPeaple;
import model.user.User;
import view.MainStage;
import view.MyConfig;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GroupPageController implements Initializable {
    User user;
    GetListOfPeopleListener getListOfPeopleListener = new GetListOfPeopleListener();
    BackHomeListener backHomeListener = new BackHomeListener();
    GroupPageListener groupPageListener =new GroupPageListener();



    @FXML
    private TreeView<String> treeView;


    @FXML
    private ListView<String> followingListView;

    @FXML
    private TextField textField;



    ObservableList<String> followingList = FXCollections.observableArrayList();
    ObservableList<String> groupList = FXCollections.observableArrayList();

    @FXML
    private ListView<String> deleteGroupListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = MainStage.getInstance().user;
        TreeItem<String> root = new TreeItem<>("goups");
        for (int i = 0; i < user.groups.size(); i++) {
            TreeItem<String> groupname = new TreeItem<>(user.groups.get(i).name);
            for (int j = 0 ; j<user.groups.get(i).usersId.size() ; j++){
                TreeItem<String> member = new TreeItem<>(getListOfPeopleListener.getUsername(user.groups.get(i).usersId.get(j)));
                groupname.getChildren().add(member);
            }
            root.getChildren().add(groupname);
        }
        treeView.setRoot(root);

        followingList.clear();
        ArrayList<String> tmp = getListOfPeopleListener.getUsername(user.following);
        for (int i=0  ; i<tmp.size() ;i++) {
            followingList.add(tmp.get(i));
        }
        followingListView.getItems().removeAll();
        followingListView.setItems(followingList);

        followingListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        groupList.removeAll();
        for (int i=0 ; i<user.groups.size() ; i++){
            groupList.add(user.groups.get(i).name);
        }
        deleteGroupListView.getItems().removeAll();
        deleteGroupListView.setItems(groupList);
        deleteGroupListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    public void deleteGroup(){
        ObservableList<String> stringObservableList = deleteGroupListView.getSelectionModel().getSelectedItems();
        if(stringObservableList.size()==0){
            return;
        }
        String name = stringObservableList.get(0);
        groupPageListener.deleteGoup(name);
        return;

    }
    public void CreateGroup(){
        ObservableList<String> stringObservableList = followingListView.getSelectionModel().getSelectedItems();
        if(stringObservableList.size()==0){
            return;
        }
        if(textField.getText().equals("")){
            textField.setPromptText(MyConfig.instance.pageAddressProperties.getProperty("messageLabel"));
            return;
        }
        for (int i=0 ; i<textField.getText().length() ; i++){
            if (textField.getText().charAt(i)=='#'){
                textField.setPromptText(MyConfig.instance.pageAddressProperties.getProperty("messageLabel"));
                textField.setText("");
                return;
            }
        }
        groupPageListener.create(stringObservableList,textField.getText());
        return;
    }
    public void back(){
        backHomeListener.eventOccur("back");
    }
    public void home(){
        backHomeListener.eventOccur("home");
    }

}
