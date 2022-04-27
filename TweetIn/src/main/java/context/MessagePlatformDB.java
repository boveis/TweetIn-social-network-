package context;

import com.google.gson.Gson;
import model.message.Message;
import model.message.MessagePlatform;
import model.tweet.Tweet;

import java.io.*;
import java.util.ArrayList;

public class MessagePlatformDB {
    static File filePath;
    static String path ="src\\main\\resources\\context\\messagePlatform\\";
    public MessagePlatformDB() {
        filePath = new File("src\\main\\resources\\context\\messagePlatform");
    }

    public static MessagePlatform get(int id) {
        try {
            Gson gson = new Gson();
            File file = new File(path+id + ".json");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            MessagePlatform messagePlatform = gson.fromJson(bufferedReader, MessagePlatform.class);
            bufferedReader.close();
            return messagePlatform;
        } catch (IOException e) {
        }
        return null;
    }

    public static void add(MessagePlatform messagePlatform) {
        try {
            Gson gson = new Gson();
            File file = new File(path+ messagePlatform.id + ".json");
            file.createNewFile();
            String data = gson.toJson(messagePlatform);
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(data);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void remove(MessagePlatform messagePlatform) {
        File file = new File(path+ messagePlatform.id + ".json");
        if (file.exists()) {
            file.delete();
        }
    }
    public static void update(MessagePlatform messagePlatform) {
        remove(messagePlatform);
        add(messagePlatform);
    }
    public static ArrayList<MessagePlatform> AllMessagePlatform(){
        ArrayList<MessagePlatform> arrayList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            File filePath = new File("src\\main\\resources\\context\\messagePlatform");
            for (File file : filePath.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                MessagePlatform messagePlatform = gson.fromJson(bufferedReader, MessagePlatform.class);
                arrayList.add(messagePlatform);
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
