package context;

import context.MessagePlatformDB;
import context.TweetDB;
import context.UserDB;
import model.message.MessagePlatform;
import model.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Context {
    public Context instance;
    public Context(){
        instance = this;

    }
    public  static int getId(String string){

        try {
            File file = new File("src\\main\\resources\\context\\ID\\" +string );
            InputStream input = new FileInputStream(file);
            Scanner scanner = new Scanner(input);
            int id = scanner.nextInt() ;
            id++;
            scanner.close();
            input.close();
            file.createNewFile();
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.println(id);
            printStream.close();
            id--;
            return id;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
