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
        this.mapDisplay.pawn[13][10].setType('Y');
        this.mapDisplay.pawn[12][4].setType('Y');
    }
}
