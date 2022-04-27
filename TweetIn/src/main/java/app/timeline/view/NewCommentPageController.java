package app.timeline.view;

import app.explore.listener.GetUserPictureListener;
import app.personal.listener.GetListOfPeopleListener;
import app.setting.listener.BackHomeListener;
import app.timeline.listener.CommentListener;
import app.timeline.listener.TweetPictureListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import listener.GetIdListener;
import model.tweet.Tweet;
import view.MainStage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class NewCommentPageController {
    Tweet tweet ;
   File file= null;
    boolean hasImage = false;
    GetListOfPeopleListener getListOfPeopleListener = new GetListOfPeopleListener();
    BackHomeListener backHomeListener = new BackHomeListener();
    TweetPictureListener tweetPictureListener = new TweetPictureListener();
    CommentListener commentListener = new CommentListener();
    GetIdListener getIdListener = new GetIdListener();
    GetUserPictureListener getUserPictureListener = new GetUserPictureListener();
    @FXML
    private TextField lastTweetText;

    @FXML
    private ImageView tweetPicture;

    @FXML
    private Label usernameLabel;

    @FXML
    private ImageView userPicture;

    @FXML
    private TextField textField;

    @FXML
    private ImageView pictureView;

    @FXML
    private Label textLabel;

    public void loadDate(Tweet tweet) {
        this.tweet=tweet;
        lastTweetText.setText(tweet.text);
        usernameLabel.setText(getListOfPeopleListener.getUsername(tweet.author_id));

        File filee = getUserPictureListener.getUserImage(tweet.author_id);

        FileInputStream inputstream = null;
        if(filee!=null){
        try {
            inputstream = new FileInputStream(filee);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);

            userPicture.setImage(image);
        }
        if(tweet.hasImage) {
             inputstream = null;
            try {
                inputstream = new FileInputStream(tweetPictureListener.getImage(tweet.id));

                Image imagetmp = new Image(inputstream);
                tweetPicture.setImage(imagetmp);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        lastTweetText.setEditable(false);
    }

    public void back(){
        backHomeListener.eventOccur("refresh");
    }
    public void home(){
        backHomeListener.eventOccur("home");
    }
    public void chooseImage() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        this.file = fileChooser.showOpenDialog(null);
        if (file != null){
            hasImage = true;
            FileInputStream input = new FileInputStream(file);
            Image img = new Image(input);
            pictureView.setImage(img);
        }
        if (file==null){
            hasImage=false;
        }
        return;
    }
    public void createComment(){
        if (textField.getText().equals("")){
            textLabel.setVisible(true);
            return;
        }
        else {
            Tweet comment = new Tweet(MainStage.getInstance().user.getId() , textField.getText()  , hasImage ,getIdListener.getTweetId(),tweet.id  , true);
            if (hasImage){
                tweetPictureListener.addImage(comment.id , file);
            }
            commentListener.AddComment(comment);
            back();
        }
    }
}
