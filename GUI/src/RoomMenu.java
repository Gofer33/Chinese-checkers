import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.ImageView;

import javafx.scene.image.Image;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;


import javafx.event.EventHandler;



public class RoomMenu {

    public RoomMenu(Group root) throws FileNotFoundException {

        //Background

        Image background = new Image(new FileInputStream("Background.png"));

        //Setting the image view
        ImageView imageView = new ImageView(background);

        //Setting the position of the image
        imageView.setX(0);
        imageView.setY(100);

        //setting the fit height and width of the image view
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);

        //Headers

        Text t_header = new Text();
        Text t_IPadres = new Text();
        Text t_create = new Text();

        //Setting font
        t_header.setFont(new Font(25));
        t_IPadres.setFont(new Font(14));
        t_create.setFont(new Font(14));

        //setting the position of the text
        t_header.setX(50);
        t_header.setY(40);
        t_IPadres.setX(20);
        t_IPadres.setY(110);
        t_create.setX(20);
        t_create.setY(170);

        //Setting the text to be added.
        t_header.setText("Chinese Checkers");
        t_IPadres.setText("Join server");
        t_create.setText("Create server");

        t_header.setFill(Color.WHITE);
        t_IPadres.setFill(Color.WHITE);
        t_create.setFill(Color.WHITE);

        //Buttons
        Button b_connect = new Button("Connect");
        Button b_create = new Button("Create");
        TextField tf_connect = new TextField();

        b_connect.setLayoutX(220);
        b_connect.setLayoutY(120);
        b_create.setLayoutX(20);
        b_create.setLayoutY(180);
        tf_connect.setLayoutX(20);
        tf_connect.setLayoutY(120);

        b_connect.setStyle("-fx-background-color: rgba(200,200,200,0.6); -fx-text-fill: rgba(0,0,0,0.8); -fx-pref-width: 60px;");
        b_create.setStyle("-fx-background-color: rgba(200,200,200,0.6); -fx-text-fill: rgba(0,0,0,0.8); -fx-pref-width: 60px;");
        tf_connect.setStyle("-fx-background-color: rgba(200,200,200,0.6); -fx-text-fill: rgba(0,0,0,0.8); -fx-pref-width: 180px;");


        b_connect.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("Change state");
            }
        });
        b_create.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("Start the server");
            }
        });

        //Creating a main Group object and images Group object
        Group image_root = new Group(imageView);
        Group buttons = new Group();

        //Root operations, and children list
        ObservableList t_list = root.getChildren();
        ObservableList t_buttons = root.getChildren();
        root.getChildren().add(image_root);
        root.getChildren().add(buttons);

        //Setting the text object as a node to the group object
        t_list.add(t_header);
        t_list.add(t_IPadres);
        t_list.add(t_create);
        t_buttons.add(b_connect);
        t_buttons.add(b_create);
        t_buttons.add(tf_connect);
    }
}