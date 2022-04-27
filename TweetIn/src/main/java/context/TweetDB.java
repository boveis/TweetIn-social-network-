package context;

import com.google.gson.Gson;
import model.tweet.Tweet;
import model.user.User;

import java.io.*;
import java.util.ArrayList;

public class TweetDB {

    static File filePath;
    static String path = "src\\main\\resources\\context\\tweets\\";
    public TweetDB() {
        filePath = new File("src\\main\\resources\\context\\tweets");
    }

    public static Tweet getTweet(int id) {
        try {
            Gson gson = new Gson();
            File file = new File(path+id + ".json");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Tweet tweet = gson.fromJson(bufferedReader, Tweet.class);
            bufferedReader.close();
            return tweet;
        } catch (IOException e) {
        }
        return null;
    }
    public static boolean existTweet(int id){
        File file = new File(path+ id + ".json");
        return file.exists();
    }

    public static ArrayList<Tweet> allTweetOfList(ArrayList<Integer> idOfTweet){
       ArrayList<Tweet> tweetArrayList = new ArrayList<>();
       for (int i=0 ; i<idOfTweet.size() ; i++){
           tweetArrayList.add(getTweet(idOfTweet.get(i)));
       }
       return  tweetArrayList;
    }

    public static void add(Tweet tweet) {
        try {
            Gson gson = new Gson();
            File file = new File(path+ tweet.id + ".json");
            file.createNewFile();
            String data = gson.toJson(tweet);
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(data);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void remove(int id) {
        File file = new File(path+ id + ".json");
        if (file.exists())
        file.delete();
    }

    public static void remove(Tweet tweet) {
        File file = new File(path+ tweet.id + ".json");
        file.delete();
    }
    public static void update(Tweet tweet) {
        remove(tweet);
        add(tweet);
    }
    public static ArrayList<Tweet> AllTweet(){
        ArrayList<Tweet> arrayList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            File filePath = new File("src\\main\\resources\\context\\tweets");
            for (File file : filePath.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                Tweet tweet = gson.fromJson(bufferedReader, Tweet.class);
                arrayList.add(tweet);
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

}
