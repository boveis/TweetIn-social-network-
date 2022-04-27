package controller;

import Log.Logger;
import app.message.controller.MessageController;
import com.google.gson.Gson;
import context.Context;
import context.UserDB;
import model.message.Message;
import model.message.MessagePlatform;
import model.user.User;
import org.graalvm.compiler.lir.alloc.lsra.IntervalWalker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    public Controller(){
    }

    public User getUser(String username){
        return UserDB.getUser(getId(username));
    }
    public User getUser(int  id){
        return UserDB.getUser(id);
    }
    public  boolean existUser(ArrayList<Integer> arrayList , int id){
        for (int i=0 ; i<arrayList.size() ; i++){
            if (arrayList.get(i) == id)return true;
        }
        return false;
    }
    public  ArrayList<Integer> deleteUser(ArrayList<Integer> arrayList  , int id){
        ArrayList<Integer> integers=new ArrayList<>();
        for (int i=0 ; i<arrayList.size() ; i++){
            if (arrayList.get(i)!=id){
               integers.add(arrayList.get(i));
            }
        }
        return integers;
    }
    public void addUser(User user){
        Logger.setUser(user);
        Logger.logSignUp();
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(user.getId());
        MessagePlatform messagePlatform = new MessagePlatform(tmp ,  0,Context.getId("messagePlatforms"));
        user.savedMessage=messagePlatform.id;
        user.messagePlatform.add(messagePlatform.id);
        UserDB.addUser(user);
        MessageController messageController = new MessageController();
        messageController.addMessagePlatform(messagePlatform);
    }
    public int getId(String username1){
        ArrayList<User> userArrayList = UserDB.allUsers();
        for (int i=0 ; i<userArrayList.size() ; i++){
            if (userArrayList.get(i).profile.username.equals(username1))return userArrayList.get(i).getId();
        }
        return -1;
    }
    public  String getPass(String username1){
        ArrayList<User> userArrayList = UserDB.allUsers();
        for (int i=0 ; i<userArrayList.size() ; i++){
            if (userArrayList.get(i).profile.username.equals(username1))return userArrayList.get(i).profile.password;
        }
        return "";
    }
    public String getUsername(int id){
        if(UserDB.exist(id)){
            return UserDB.getUser(id).profile.username;
        }
        return " User not exist";
    }
    public  ArrayList<User> getGroupOfUser(ArrayList<Integer> idArrayList){
        ArrayList<User> userArrayList = new ArrayList<>();
        for (int i=0 ; i<idArrayList.size() ; i++){
            userArrayList.add(UserDB.getUser(idArrayList.get(i)));
        }
        return userArrayList;
    }
    public ArrayList<String> getGroupOfUsername(ArrayList<Integer> idArrayList){
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i=0 ; i<idArrayList.size() ; i++){
            stringArrayList.add(getUsername(idArrayList.get(i)));
        }
        return stringArrayList;
    }
    public void updateUser(User user){
        UserDB.update(user);
        return;
    }
    //delete deactive User
    public ArrayList<Integer> getListOfInteger(ArrayList<Integer> arrayList){
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i=0 ;i<arrayList.size() ; i++){
            User user =getUser(arrayList.get(i));
            if (user.profile.isActive){
                integerArrayList.add(arrayList.get(i));
            }
        }
        return integerArrayList;
    }
}
