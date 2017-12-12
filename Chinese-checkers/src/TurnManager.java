import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Damian Borek on 27/11/2017.
 */
public class TurnManager {
    private ArrayList<Player> players;
    private int playerTurn = 0;
    Random generator;

    public TurnManager(ArrayList<Player> players)
    {
        this.players = players;
        generator = new Random();
    }

    public Player current ()
    {
        return players.get(playerTurn);
    }

    public Player next ()
    {
        playerTurn = (playerTurn + 1)%(players.size());
        System.out.println(players.size() +" "+playerTurn);
        return players.get(playerTurn);
    }

    public void resetTurn ()
    {
        playerTurn = generator.nextInt(players.size());
    }
}
