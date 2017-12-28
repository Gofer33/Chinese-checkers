package Game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Kamil on 2017-12-28.
 */

public class Game {
    public Game(){

        /**********DECLARATIONS**********/
        Stage stage;
        Image background;
        ImageView imageView;
        Group root = null;

        /**********BACKGROUND**********/
        try{
            background = new Image(new FileInputStream("GUI\\resources\\GameBackground.png"));
            imageView = new ImageView(background);
            imageView.setX(0);
            imageView.setY(0);
            imageView.setFitHeight(620);
            imageView.setFitWidth(620);
            root = new Group(imageView);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }

        /**********STAGE PROPERTIES**********/
        //Set stage properties
        stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Chinese Checkers");
        if(root == null) root = new Group();


        //Creating a scene object
        Scene mainScene = new Scene(root, 600, 600);
        mainScene.setFill(Color.BLUE);



        //Adding scene to the stage
        stage.setScene(mainScene);
        stage.show();
    }
}