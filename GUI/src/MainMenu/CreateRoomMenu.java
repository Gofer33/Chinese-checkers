package MainMenu;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Kamil on 2017-12-27.
 */
public class CreateRoomMenu {
    CreateRoomMenu(GroupContainer root){

        /**********DECLARATIONS**********/
        Text t_room_name;
        Text t_players;
        Button b_create;
        Button b_exit;
        Button b_start;
        Button b_bot[];
        Button b_close[];
        TextField tf_room_name;

        /**********TEXTS**********/
        t_room_name = new Text();
        t_players = new Text();

        //Setting font
        t_room_name.setFont(new Font(14));
        t_players.setFont(new Font(14));

        //Setting positions
        t_room_name.setX(620);
        t_room_name.setY(30);
        t_players.setX(620);
        t_players.setY(90);

        //Setting the text to be added.
        t_room_name.setText("Room name:");
        t_players.setText("Players:");

        //Setting color
        t_room_name.setFill(Color.WHITE);
        t_players.setFill(Color.WHITE);

        /**********BUTTONS**********/
        b_create = new Button("Create");
        b_start = new Button("Start");
        b_exit = new Button("Exit");
        b_bot = new Button[6];
        b_close = new Button[6];
        tf_room_name = new TextField();

        //Set texts
        for(int i = 0; i < 6; i++){
            b_bot[i] = new Button("Bot");
            b_close[i] = new Button("Close");
        }

        //Setting positions
        b_create.setLayoutX(820);
        b_create.setLayoutY(40);
        b_start.setLayoutX(820);
        b_start.setLayoutY(360);
        b_exit.setLayoutX(620);
        b_exit.setLayoutY(360);
        for(int i = 0; i < 6; i++){
            b_bot[i].setLayoutX(795);
            b_bot[i].setLayoutY(100 + i * 30);
            b_close[i].setLayoutX(835);
            b_close[i].setLayoutY(100 + i * 30);
        }
        tf_room_name.setLayoutX(620);
        tf_room_name.setLayoutY(40);

        //Setting style
        b_create.setStyle("-fx-background-color: rgba(200,200,200,0.6); -fx-text-fill: rgba(0,0,0,0.8); -fx-pref-width: 60px;");
        b_start.setStyle("-fx-background-color: rgba(200,200,200,0.6); -fx-text-fill: rgba(0,0,0,0.8); -fx-pref-width: 60px;");
        b_exit.setStyle("-fx-background-color: rgba(200,200,200,0.6); -fx-text-fill: rgba(0,0,0,0.8); -fx-pref-width: 60px;");
        for(int i = 0; i < 6; i++){
            b_bot[i].setStyle("-fx-background-color: rgba(200,200,200,0.6); -fx-text-fill: rgba(0,0,0,0.8); -fx-pref-width: 35px;");
            b_close[i].setStyle("-fx-background-color: rgba(200,200,200,0.6); -fx-text-fill: rgba(0,0,0,0.8); -fx-pref-width: 45px;");
        }
        tf_room_name.setStyle("-fx-background-color: rgba(200,200,200,0.6); -fx-text-fill: rgba(0,0,0,0.8); -fx-pref-width: 180px;");

        //Setting handlers
        b_exit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //TO DO SINGLETON MOVE
                Move m = new Move(root.menuElements, Move.Dir.RIGHT, 300, 5);
                m.start();
                System.out.println("Change state111");
            }
        });

        /**********ROOT OPERATIONS**********/
        root.roomMenuHeader = new Group(t_room_name, t_players, b_create, b_start, b_exit, tf_room_name,
                b_bot[0], b_bot[1], b_bot[2], b_bot[3], b_bot[4], b_bot[5],
                b_close[0], b_close[1], b_close[2], b_close[3], b_close[4], b_close[5]);
        root.menuElements.getChildren().add(root.roomMenuHeader);
    }
}
