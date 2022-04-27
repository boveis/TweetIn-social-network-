package Log;

import model.user.User;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Stack;

public class Logger {
    public static User user = null;

    public static void setUser(User user) {
        Logger.user = user;
        Logger.name = user.getId();
    }

    public static int name;
    public Logger(){}
    public static void start(){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\main" , true);
            fr.write("this program start: " + LocalDateTime.now().toString()+"\n");
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void mainInfo(String s){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\main" , true);
            fr.write(s+"\n" + LocalDateTime.now().toString()+"\n");
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void makeTweet(int number){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nB\n" +"make tweet number:\n"+number+"\n"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeMessage(int number){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nB\n" +"make messsage in platform number:"+number+"\n"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void logSignUp(){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+user.getId() , true);
            fr.write("this Account built in :" + LocalDateTime.now().toString()+"\n username:"+ user.profile.username+"\n password:"+ user.profile.password
                    +"\n email:"+ user.profile.email + "\n phone:"+ user.profile.phone);
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         fr= null;
        try {
            fr = new FileWriter("Log\\main" , true);
            fr.write("this user start app : "+user.getId()+"\n" + LocalDateTime.now().toString()+"\n");
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void logLogin(){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nA\n" +"log to her account At:"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logLogout(){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nA\n" +"log out from her account At:"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void logChangePassword( String password){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nB\n" +"Change password to:"+password+"At:"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logChangeBirthday( String password){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nB\n" +"Change birthdat to:"+password+"At:"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void logChangeProfile(){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nB\n" +"Change profile:\n"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void logChangeName(String password){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nB\n" +"Change fullname to:"+password+"At:"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logChangeUsername( String password){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nB\n" +"Change username to:"+password+"At:"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logChangePhone( String password){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nB\n" +"Change phone to:"+password+"At:"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logChangeEmail(String password){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nB\n" +"Change email to:"+password+"At:"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logDeactive(){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nC\n" +"Deactive account At:"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logReport( int i){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nC\n" +"report tweet number"+i+"At:"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void logDeleteAccount(){
        FileWriter fr= null;
        try {
            fr = new FileWriter("Log\\"+name , true);
            fr.write("\nD\n" +"delete account At:"+LocalDateTime.now().toString());
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
