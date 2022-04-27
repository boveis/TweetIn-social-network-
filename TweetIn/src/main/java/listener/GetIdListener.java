package listener;

import controller.IdController;

public class GetIdListener {
    IdController idController = new IdController();
    public int getUserId(){
        return idController.nextId("users");
    }

    public int getMessageId(){

        return idController.nextId("messagePlatforms");
    }


    public int getImageId(){

        return idController.nextId("images");
    }
    public int getTweetId(){


        return idController.nextId("tweets");
    }


}
