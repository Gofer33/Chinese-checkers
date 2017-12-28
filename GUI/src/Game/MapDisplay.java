package Game;

import javafx.scene.Group;

/**
 * Created by Kamil on 2017-12-28.
 */
public class MapDisplay extends Thread {

    Pawn pawn[][];
    Board board;

    MapDisplay(GroupContainer root){

        board = new Board();
        pawn = new Pawn[19][19];

        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 19; j++){
                if(board.mainArray[j][i] != '.') {
                    pawn[j][i] = new Pawn();
                    pawn[j][i].setType(board.mainArray[j][i]);
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
                if(board.mainArray[j][i] != '.') {
                    pawn[j][i].symbol.setLayoutX(-165 + i * 34 + j * 17);
                    pawn[j][i].symbol.setLayoutY(-15 + j * 34);
                }
            }
        }
    }
}
