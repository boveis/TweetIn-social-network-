package app.message.Listener;

import controller.Controller;
import javafx.collections.ObservableList;
import model.user.GroupOfPeaple;

import model.user.User;

import view.MainStage;

import java.util.ArrayList;

public class GroupPageListener  {
    Controller controller = new Controller();
    public void create(ObservableList<String>  strings , String name){
        ArrayList<Integer> integers  = new ArrayList<>();
        for (String s:strings){

            integers.add(controller.getId(s));
        }
        integers.add(MainStage.getInstance().user.getId());
        GroupOfPeaple groupOfPeaple = new GroupOfPeaple(name , integers);
        MainStage.getInstance().user.groups.add(groupOfPeaple);
        controller.updateUser(MainStage.getInstance().user);
        MainStage.getInstance().refresh();
    }
    public void deleteGoup(String name){
        User user = MainStage.getInstance().user;
        for (int i=0  ; i<user.groups.size() ; i++){
            if (user.groups.get(i).name.equals(name)){
                user.groups.remove(i);
                controller.updateUser(user);
                MainStage.getInstance().user=controller.getUser(user.getId());
                MainStage.getInstance().refresh();
                return;
            }
        }
    }
}
