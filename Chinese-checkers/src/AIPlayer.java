import java.io.DataInputStream;
import java.io.PrintStream;

/**
 * Created by Damian Borek on 27/11/2017.
 */
public class AIPlayer implements Player {
    char mark;
    char oppositeMark;

    @Override
    public void move() {

    }

    @Override
    public String getName_() {
        return "Bot";
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
    }

    @Override
    public void setOppositeMark(char mark) {
        this.oppositeMark = mark;
    }
}
