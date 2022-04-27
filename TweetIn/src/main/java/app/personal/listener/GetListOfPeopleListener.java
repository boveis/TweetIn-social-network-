package app.personal.listener;

import controller.Controller;

import java.util.ArrayList;

public class GetListOfPeopleListener {
    Controller controller = new Controller();
    public ArrayList<String> getUsername(ArrayList<Integer> integerArrayList){
        return controller.getGroupOfUsername(controller.getListOfInteger(integerArrayList));
    }
    public String getUsername(int id){
        return controller.getUsername(id);
    }

}
