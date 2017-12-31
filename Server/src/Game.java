import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
    String botsNames[] = {"Bot George", "Bot Tom", "Bot Kevin", "Bot Paul", "Bot Oliver"};
    ArrayList<Player> winners = new ArrayList<Player>();

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

    public void addWinner(Player player)
    {
        winners.add(player);
    }

    private void addBots(int ammount)
    {
        for(int i=0;i<ammount ; i++) {
            AIPlayer bot = new AIPlayer(botsNames[i], this.tableName);
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
        Collections.shuffle(players, new Random(System.currentTimeMillis()));
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
        players.get(0).setMark('L');
        players.get(1).setMark('G');
        players.get(2).setMark('P');
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

    public boolean isOver()
    {
        System.out.print("winners = " + winners.size() + " players = " + players.size() + " ");
        if(winners.size() == players.size()-1) {
            System.out.println("TRUE");
            return true;
        }
        else {
            System.out.println("False");
            return false;
        }
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
