package model.message;

import java.util.ArrayList;

public class Message {

    public int sender;
    public String text;
    public boolean hasImage = false;
    public int imageNumber;
    public boolean isForwarded;
    public ArrayList<Integer> readingPerson;
    public Message(int sender, String text, boolean hasImage , int imageNumber , boolean isForwarded) {
        this.sender = sender;
        this.text = text;
        this.hasImage = hasImage;
        this.imageNumber = imageNumber;
        this.isForwarded= isForwarded;
        readingPerson = new ArrayList<>();
        read(sender);
    }

    public void read(int id){
        for(Integer i:readingPerson){
            if (i==id)return;
        }
        readingPerson.add(id);
    }
    public boolean readUser(int id){
        for(Integer i:readingPerson){
            if (i==id)return true;
        }
        return false;
    }
}
