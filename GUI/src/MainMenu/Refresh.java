package MainMenu;

import Game.MapDisplay;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Button;

import java.util.concurrent.TimeUnit;

/**
 * Created by Kamil on 2017-12-27.
 */
public class Refresh extends Thread {
    Button b_nick[];
    Button b_bot[];
    Button b_close[];
    Button b_kick[];
    ConnectionManager connectionManager;
    MapDisplay mapDisplay;

    public Refresh(Button b_nick[], Button b_bot[], Button b_close[], Button b_kick[], ConnectionManager connectionManager) {
        this.b_nick = b_nick;
        this.b_bot = b_bot;
        this.b_close = b_close;
        this.b_kick = b_kick;
        this.connectionManager = connectionManager;
    }

    public void makeRefresh() {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                for(int i = 0; i < 6; i++){
                    b_nick[i].setText(connectionManager.menuData.players[i]);
                    if(connectionManager.menuData.players[i] != "*" && i > 0){
                        b_kick[i].setLayoutX(795);
                        b_bot[i].setLayoutX(1095);
                        b_close[i].setLayoutX(1135);
                    }
                    if(connectionManager.menuData.players[i] == "*" && i > 0){
                        b_kick[i].setLayoutX(1095);
                        b_bot[i].setLayoutX(795);
                        b_close[i].setLayoutX(835);
                    }
                }
            }
        });
    }

    public void updateRefresh(MapDisplay mapDisplay){
        this.mapDisplay = mapDisplay;
    }

    public void makeMapRefresh(String pos){
        int posX1 = Integer.valueOf(pos.substring(0,2));
        int posY1 = Integer.valueOf(pos.substring(2,4));
        int posX2 = Integer.valueOf(pos.substring(4,6));
        int posY2 = Integer.valueOf(pos.substring(6,8));

        System.out.println(posX1);
        System.out.println(posY1);
        char color = this.mapDisplay.pawn[posX1][posY1].getType();
        this.mapDisplay.pawn[posX1][posY1].setType('#');
        this.mapDisplay.pawn[posX2][posY2].setType(color);
    }
}
