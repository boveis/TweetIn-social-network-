package model.user;

import context.UserDB;

import java.util.ArrayList;

public class GroupOfPeaple {

    public ArrayList<Integer> usersId;
    public String name;

    public GroupOfPeaple(String name , ArrayList<Integer> integerArrayList) {
        this.name = name;
        usersId = integerArrayList;
    }
}
