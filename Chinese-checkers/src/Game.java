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
        board.initialize(playersNumber);
    if(playersNumber == 2)
    {
        players.get(0).setMark('Y');
        players.get(1).setMark('R');
    }
    else if(playersNumber == 3)
    {
        players.get(0).setMark('Y');
        players.get(1).setMark('P');
        players.get(2).setMark('B');
    }
    else if(playersNumber == 4)
    {
        players.get(0).setMark('G');
        players.get(1).setMark('P');
        players.get(2).setMark('L');
        players.get(3).setMark('B');
    }
    else if(playersNumber == 6)
    {
        players.get(0).setMark('Y');
        players.get(1).setMark('G');
        players.get(2).setMark('P');
        players.get(3).setMark('R');
        players.get(4).setMark('B');
        players.get(5).setMark('L');
    }
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
