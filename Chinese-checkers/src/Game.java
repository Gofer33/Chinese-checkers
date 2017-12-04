import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Damian Borek on 27/11/2017.
 */
public class Game {

    Board board = new Board();
    ArrayList<Player> players;
    int playersNumber;
    TurnManager turn;
    boolean isStarted;
    boolean roomFull;

    public Game (int humanPlayers, int AIPlayers)
    {
        players = new ArrayList<Player>();
        this.turn = new TurnManager(players);
        playersNumber = humanPlayers + AIPlayers;
        isStarted = false;
        roomFull = false;
    }
    public void addPlayer(Player player)
    {
        players.add(player);

        if(players.size() == (playersNumber))
            roomFull = true;
    }
    public void removePlayer(Player player)
    {
        players.remove(player);
    }

    public void play ()
    {
        while(true)
        {
            turn.next();
        }
    }

    public void start ()
    {
        this.isStarted = true;
    }

    public boolean isStarted()
    {
        return isStarted;
    }

    public boolean isFull()
    {
        return roomFull;
    }

}
