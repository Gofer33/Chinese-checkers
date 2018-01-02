package MainMenu;

import javafx.scene.Group;
import javafx.scene.control.Button;

import java.util.concurrent.TimeUnit;

/**
 * Created by Kamil on 2017-12-27.
 */
public class Refresh extends Thread {
    Button button[] = new Button[6];
    ConnectionManager connectionManager;

    public Refresh(Button button[], ConnectionManager connectionManager){
        this.button = button;
        this.connectionManager = connectionManager;
    }

    public void run(){
        while(true) {
            for (int i = 0; i < 6; i++) {
                button[i].setText(connectionManager.menuData.players[i]);
            }
        }
    }
}
