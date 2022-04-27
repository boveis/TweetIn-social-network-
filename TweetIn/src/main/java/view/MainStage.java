package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.tweet.Tweet;
import model.user.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class MainStage {
    public static MainStage instance;
    public Stack<String> pages = new Stack<>();
    public Stage stage;
    public User user;
    public User visitor;
    public Tweet tweet;
    public MainStage(Stage stage) throws IOException {
        Image icon = new Image(getClass().getResourceAsStream("/image/frameIcon/frameIcon.jpg"));
        this.stage = stage;
        this.stage.setTitle("TweetIn");
        this.stage.setResizable(false);
        this.stage.getIcons().add(icon);
        setPage(MyConfig.instance.pageAddressProperties.getProperty("LoginPage"));

        this.stage.show();
    }
    public static MainStage getInstance(){
        return instance;
    }
    public void setPage(String pageName)  {
        if (!pageName.equals(MyConfig.instance.pageAddressProperties.getProperty("LoginPage")) && !pageName.equals(MyConfig.instance.pageAddressProperties.getProperty("SignUnPage"))){
            pages.push(pageName);
        }
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(pageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    public void setUser(User user){
        this.user = user;
    }
    public void back(){
        if (pages.size()>1){
            String s = pages.pop();
            s=pages.pop();
            setPage(s);
        }
        else{
            refresh();
        }
        return;
    }
    public void refresh(){
        String s = pages.pop();
        setPage(s);
    }



}
