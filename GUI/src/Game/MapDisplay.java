package Game;

import MainMenu.ConnectionManager;
import MainMenu.Move;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

/**
 * Created by Kamil on 2017-12-28.
 */
public class MapDisplay extends Thread {

    public Pawn pawn[][];
    Board board;
    int posX1 = 0;
    int posY1 = 0;
    int posX2 = 0;
    int posY2 = 0;
    char color = '#';
    ConnectionManager connectionManager;

    MapDisplay(GroupContainer root, ConnectionManager connectionManager){

        board = new Board();
        pawn = new Pawn[19][19];
        this.connectionManager = connectionManager;

        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 19; j++){
                if(board.mainArray[j][i] != '.') {
                    pawn[j][i] = new Pawn();
                    pawn[j][i].setType(board.mainArray[j][i]);
                    pawn[j][i].symbol.setLayoutX(-165 + i * 34 + j * 17);
                    pawn[j][i].symbol.setLayoutY(-15 + j * 34);
                    root.pawns.getChildren().add(pawn[j][i].symbol);
                }
            }
        }

        /**********ROOT OPERATIONS**********/
        root.mainRoot.getChildren().add(root.board);
        root.mainRoot.getChildren().add(root.pawns);
    }

    public void run(){
        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 19; j++){
                final int counterX = i;
                final int counterY = j;
                if(board.mainArray[j][i] != '.') {
                    pawn[j][i].symbol.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            if(posX1 == 0 && posY1 == 0){
                                posX1 = counterX;
                                posY1 = counterY;
                                color = pawn[counterY][counterX].getType();
                            }
                            else{
                                pawn[posY1][posX1].setType('#');
                                pawn[counterY][counterX].setType(color);
                                System.out.println(String.valueOf(posX1) + " " + String.valueOf(posY1) + " " + String.valueOf(counterX) + " " + String.valueOf(counterY));
                                color = '#';
                                String output = "";

                                if(String.valueOf(posX1).length() < 2){
                                    output = "0" + String.valueOf(posX1);
                                }
                                else{
                                    output = String.valueOf(posX1);
                                }
                                if(String.valueOf(posY1).length() < 2){
                                    output += "0" + String.valueOf(posY1);
                                }
                                else{
                                    output += String.valueOf(posY1);
                                }
                                if(String.valueOf(counterX).length() < 2){
                                    output += "0" + String.valueOf(counterX);
                                }
                                else{
                                    output += String.valueOf(counterX);
                                }
                                if(String.valueOf(counterY).length() < 2){
                                    output += "0" + String.valueOf(counterY);
                                }
                                else{
                                    output += String.valueOf(counterY);
                                }

                                posX1 = 0;
                                posY1 = 0;

                                connectionManager.makeMove(output);
                            }
                        }
                    });
                }
            }
        }
    }
}
