package context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import controller.Controller;
import model.user.User;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class UserDB {

    final static String path = "src\\main\\resources\\context\\users\\";

    public static User getUser(int id){
        try {
            Gson gson = new Gson();
            File file = new File(path+id + ".json");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            User user = gson.fromJson(bufferedReader, User.class);
            bufferedReader.close();
            return user;
        } catch (IOException e) {
            return null;
        }
    }
    public static ArrayList<User> allUsers(){
        ArrayList<User> arrayList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            File filePath = new File("src\\main\\resources\\context\\users");
            for (File file : filePath.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                User user = gson.fromJson(bufferedReader, User.class);
                arrayList.add(user);
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }




    public static void addUser(User user){
        try {
            Gson gson = new Gson();
            File file = new File(path + user.getId() + ".json");
            file.createNewFile();
            String data = gson.toJson(user);
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(data);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void removeUser(int id) {
        File file = new File(path+ id + ".json");
        file.delete();
    }

    public static void update(User user) {
        removeUser(user.getId());
        try {
            Gson gson = new Gson();
            File file = new File(path+ user.getId() + ".json");
            file.createNewFile();
            String data = gson.toJson(user);
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(data);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean exist(int id ){
        File file = new File(path+ id + ".json");
        return file.exists();
    }



}
