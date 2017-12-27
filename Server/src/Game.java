import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Damian Borek on 27/11/2017.
 */
public class Game {
    int AIPlayers;
    int humanPlayers;
    Board board = new Board();
    String tableName;
    ArrayList<Player> players;
    int playersNumber;
    TurnManager turn;
    boolean isStarted;
    boolean roomFull;

    public Game (int humanPlayers, int AIPlayers, String tableName)
    {
        this.AIPlayers = AIPlayers;
        this.humanPlayers = humanPlayers;
        this.tableName = tableName;
        players = new ArrayList<Player>();
        this.turn = new TurnManager(players);
        playersNumber = humanPlayers + AIPlayers;
        isStarted = false;
        roomFull = false;
        addBots(AIPlayers);
    }

    private void addBots(int ammount)
    {
        String botName = "Bot";
        for(int i=0;i<ammount ; i++) {
            botName = botName + GamesManager.getInstance().games.size() +Integer.toString(i);
            AIPlayer bot = new AIPlayer(botName, this.tableName);
            this.addPlayer(bot);
        }
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

    public boolean isFull()
    {
        return roomFull;
    }

    public String getTableName()
    {
        return this.tableName;
    }

    public int getAIPlayers()
    {
        return this.AIPlayers;
    }

    public int getHumanPlayers()
    {
        return this.humanPlayers;
    }

    public int getPlayersNumber()
    {
        return this.playersNumber;
    }

}
