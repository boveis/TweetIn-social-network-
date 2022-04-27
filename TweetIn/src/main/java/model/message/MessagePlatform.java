package model.message;

import context.Context;
import context.MessagePlatformDB;
import context.UserDB;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MessagePlatform {
    public int id;
    public ArrayList<Integer> users;
    public ArrayList<Message> messages;
    public int type;//0 for save Message 1 for pv message 2 for group message
    public String name;
    public MessagePlatform(ArrayList<Integer> users , int type , int id) {
        messages = new ArrayList<>();
        this.users = users;
        this.id = id;
        this.name = "";
        MessagePlatformDB.add(this);
        this.type = type;
    }

    public MessagePlatform(ArrayList<Integer> users , String name , int type  , int id) {
        messages = new ArrayList<>();
        this.users = users;
        this.id =id;
        this.name = name;
        MessagePlatformDB.add(this);
        this.type = type;
    }

}
