import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileNotFoundException;




public class StageManager extends Application {

    @Override
    public void start(Stage stage) throws FileNotFoundException {

        stage.setResizable(false);
        Group mainRoot = new Group();

        //Creating a scene object
        Scene mainScene = new Scene(mainRoot, 300, 390);


        MainMenu mainMenu = new MainMenu(mainRoot);
    //    RoomMenu roomMenu = new RoomMenu(mainRoot);


        mainScene.setFill(Color.BLACK);
        //Setting title to the Stage
        stage.setTitle("Main menu");
        //Adding scene to the stage
        stage.setScene(mainScene);

        //Displaying the contents of the stage
        System.out.println("111");
        stage.show();
    }
    public static void launcher(){
        launch();
    }
}