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
        update(playerTurn);
        System.out.println(players.get(playerTurn).getName_() + "'S TURN!");
        while(players.get(playerTurn) instanceof AIPlayer)
        {
            botTurn((AIPlayer)players.get(playerTurn));
            playerTurn = (playerTurn + 1)%(players.size());
            update(playerTurn);
        }

        return players.get(playerTurn);
    }


    public void resetTurn ()
    {
        playerTurn = generator.nextInt(players.size());
        System.out.println(players.get(playerTurn).getName_() + "'S TURN! " + "MARK " + players.get(playerTurn).getMark());
        update(playerTurn);
        while(players.get(playerTurn) instanceof AIPlayer)
        {
            botTurn((AIPlayer) players.get(playerTurn));
            playerTurn = (playerTurn + 1)%(players.size());
            update(playerTurn);
        }
    }

    private void update(int playerTurn)
    {
        for(int i=0;i<players.size();i++)
        {
            if(i==playerTurn && players.get(i) instanceof HumanPlayer)
                players.get(i).getOutput().println("Your Turn!");
            else if(players.get(i) instanceof HumanPlayer)
                players.get(i).getOutput().println(players.get(playerTurn).getName_() + "'S TURN!");
        }
    }

    private void botTurn(AIPlayer player)
    {
        player.move();
        if(GamesManager.getInstance().getGameByName(player.getRoom()).board.checkWinCondition(player.getPieces(), player.getMark()))
        {
            System.out.printf("BOT " + GamesManager.getInstance().getGameByName(player.getRoom()).turn.current().getMark() + " WYGRAL!");
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
