import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damian Borek on 27/11/2017.
 */
public class HumanPlayer extends Thread implements Player {

    private Socket socket;
    int room;
    String name;
    TurnManager turn;

    public HumanPlayer(Socket socket) {
        this.socket = socket;
        this.name = "Default name";
        room = -1;
    }


    public void run() {

        String line = null;
        DataInputStream input = null;
        try {
            input = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
        PrintStream output = null;
        try {
            output = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
        while (true) {
            try {
                line = input.readLine();
            } catch (IOException e) {
                System.out.println(e);
            }
            if(room != -1 && GamesManager.getInstance().games.get(room).isFull() == true)
            {
                System.out.println("Room " + room + " is full!");
            }
            System.out.println(line);

            //new room
            if(line.charAt(0) == 'N')
            {
                int humanPlayers = line.charAt(1) - '0';
                int AIPlayers = line.charAt(2) - '0';
                Game game = new Game(humanPlayers,AIPlayers);
                game.addPlayer(this);
                GamesManager.getInstance().games.add(game);
                room = GamesManager.getInstance().games.size()-1;
                this.turn = GamesManager.games.get(room).turn;
                output.println(socket + "Game with " + humanPlayers + " human players and " + AIPlayers + " succesfully created!");

            }
            // how many rooms
            else if (line.charAt(0) == 'D')
            {
                output.println(socket + "Actual ammount of games " + GamesManager.getInstance().games.size());
            }
            // show games with players
            else if (line.charAt(0) == 'A')
            {
                for(int i = 0 ; i<GamesManager.getInstance().games.size() ; i++) {
                    output.println(i);
                    for(int j = 0 ; j < GamesManager.getInstance().games.get(i).players.size() ; j++) {
                        output.println(GamesManager.getInstance().games.get(i).players.get(j).getName_());
                    }
                }
            }
            // move(xxyyxxyy)
            else if (line.charAt(0) == 'M')
            {
                if(turn.current() == this)
                {
                    int oldX, oldY, newX, newY;
                    oldX = Integer.parseInt(line.substring(1,3));
                    oldY = Integer.parseInt(line.substring(3,5));
                    newX = Integer.parseInt(line.substring(5,7));
                    newY = Integer.parseInt(line.substring(7,9));
                    output.println(name+" Move: " + oldX + " " + oldY + " " + newX + " " + newY);
                }
            }
            // leave room
            else if (line.charAt(0) == 'R')
            {
                GamesManager.getInstance().games.get(room).removePlayer(this);
                output.println(name + "removed from " + room + " room!");
            }
            // set name
            else if (line.charAt(0) == 'S')
            {
                name = line.substring(1);
                output.println("Name = " + name);
            }
            // enter room
            else
            {
                int roomNumber = Integer.parseInt(line);
                room = roomNumber;
                GamesManager.getInstance().games.get(roomNumber).addPlayer(this);
                output.println(name + " Enters " + room + "!");
                this.turn = GamesManager.games.get(room).turn;

                if(GamesManager.games.get(room).isFull())
                {
                    System.out.println("Starting game: " + room);
                    GamesManager.getInstance().games.get(room).start();
                    GamesManager.getInstance().games.get(room).turn.resetTurn();
                    System.out.println(turn.current().getName_() + " turn!");
                    if(this == turn.current())
                        output.println("Game Started! Your Turn");
                    else
                        output.println("Game Started! " + turn.current().getName_() + "'s turn!");
                }
            }


        }
    }


    @Override
    public void move() {

    }

    @Override
    public String getName_()
    {
        return name;
    }
}
