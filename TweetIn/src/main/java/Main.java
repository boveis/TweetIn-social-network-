
import Log.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import view.MainStage;
import view.MyConfig;

public class Main extends Application {

    public static void main(String[] args){
            launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        MyConfig myConfig = new MyConfig();

        Logger.start();
        MainStage.instance = new MainStage(stage);

    }
}