package MainMenu;

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
import java.util.concurrent.TimeUnit;

import javafx.scene.image.ImageView;

import javafx.scene.image.Image;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;


import javafx.event.EventHandler;

/**
 * Created by Kamil on 2017-12-27.
 */

public class RoomMenuSlider {
    public RoomMenuSlider(GroupContainer root, ConnectionManager connectionManager) {

        /**********DECLARATIONS**********/
        Button b_rooms[];
        int amount = 6;

        /**********BUTTONS**********/
        b_rooms = new Button[amount];

        connectionManager.test();

        String tmp = connectionManager.getRooms();
        String[] array = tmp.split("\\|",-1);

        //Set texts
        for(int i = 0; i < 6; i++){
            b_rooms[i] = new Button(array[i]);
            b_rooms[i].setLayoutX(320);
            b_rooms[i].setLayoutY(100 + i * 30);
            b_rooms[i].setStyle("-fx-background-color: rgba(200,200,200,0.6); -fx-text-fill: rgba(0,0,0,0.8); -fx-pref-width: 260px;");
        }


        /**********ROOT OPERATIONS**********/
        root.roomMenuSlider = new Group();
        for(int i = 0; i < amount; i++)
            root.roomMenuSlider.getChildren().add(b_rooms[i]);
        root.menuElements.getChildren().add(root.roomMenuSlider);

    }
}
