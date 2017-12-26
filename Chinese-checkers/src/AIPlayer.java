import java.io.DataInputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by Damian Borek on 27/11/2017.
 */
public class AIPlayer implements Player {
    char mark;
    char oppositeMark;
    public ArrayList<Piece> pieces;
    int room;
    String name;

    AIPlayer(String name, int room)
    {
        this.name = name;
        this.room = room;
    }

    @Override
    public void move() {

        System.out.println ("[NAME] = " + name + " [MARK] = " + mark + " [ROOM] = " + room +  " WYKONUJE RUCH");
        GamesManager.getInstance().games.get(room).board.AIMove(pieces, mark);

    }

    @Override
    public String getName_() {
        return this.name;
    }

    public void setName_(String name) {
        this.name = name;
    }

    @Override
    public DataInputStream getInput() {
        return null;
    }

    @Override
    public PrintStream getOutput() {
        return null;
    }

    @Override
    public char getMark() {
        return this.mark;
    }

    @Override
    public char getOppositeMark() {
        return this.oppositeMark;
    }

    @Override
    public void setMark(char mark) {

        this.mark = mark;
        setPieces();
    }

    @Override
    public void setOppositeMark(char mark) {
        this.oppositeMark = mark;
    }

    public void setPieces() {
        pieces = GamesManager.getInstance().games.get(room).board.setPieces(mark);
    }

    public ArrayList<Piece> getPieces()
    {
        return this.pieces;
    }

    public int getRoom()
    {
        return this.room;
    }

    public void setRoom(int room)
    {
        this.room = room;
    }
}
