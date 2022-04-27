package controller;

import Log.Logger;
import context.UserDB;
import model.user.User;

import java.util.ArrayList;

public class SignUp_In_Controller extends Controller {
    ArrayList<User> userArrayList;
    public SignUp_In_Controller() {
        super();

    }

    public void loginAccount(User user){
        Logger.setUser(user);
        Logger.logLogin();
        user.profile.isActive=true;
        user.profile.lastseen="Online";
        UserDB.update(user);
    }
    public boolean checkEmail(String email){
        userArrayList = UserDB.allUsers();
        for (int i=0 ; i<userArrayList.size() ; i++){
            if (userArrayList.get(i).profile.email.equals(email))return false;
        }
        return true;
    }
    public boolean checkUsername(String username){
        userArrayList = UserDB.allUsers();
        for (int i=0 ; i<userArrayList.size() ; i++){
            if (userArrayList.get(i).profile.username.equals(username))return false;
        }
        return true;
    }

    public boolean checkPhone(String phone) {
        userArrayList = UserDB.allUsers();
        for (int i=0 ; i<userArrayList.size() ; i++){
            if (userArrayList.get(i).profile.phone.equals(phone))return false;
        }
        return true;
    }
}
