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
    boolean inGame = false;
    DataInputStream input = null;
    PrintStream output = null;
    char mark;
    char oppositeMark;
    ArrayList<Piece> pieces;
    boolean onlyJump=false;

    public HumanPlayer(Socket socket) {
        this.socket = socket;
        this.name = "Default name";
        room = -1;

        try {
            this.input = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            this.output = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public void run() {

        String line = null;

        while (true) {
            try {
                line = input.readLine();
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println(line);

            //new room
            if (line.charAt(0) == 'N') {
                int humanPlayers = line.charAt(1) - '0';
                int AIPlayers = line.charAt(2) - '0';
                Game game = new Game(humanPlayers, AIPlayers);
                //game.addPlayer(this);

                String botName = "Bot";
                for(int i=0;i<AIPlayers ; i++) {
                    botName = botName + GamesManager.getInstance().games.size() +Integer.toString(i);
                    AIPlayer bot = new AIPlayer(botName, GamesManager.getInstance().games.size());
                    game.addPlayer(bot);
                }


                GamesManager.getInstance().games.add(game);
                room = GamesManager.getInstance().games.size() - 1;
                this.turn = GamesManager.games.get(room).turn;
                inGame = true;
                output.println(socket + "Game with " + humanPlayers + " human players and " + AIPlayers + " succesfully created!");

            }
            // how many rooms
            else if (line.charAt(0) == 'D') {
                output.println(socket + "Actual ammount of games " + GamesManager.getInstance().games.size());
            }
            // show games with players
            else if (line.charAt(0) == 'A') {
                for (int i = 0; i < GamesManager.getInstance().games.size(); i++) {
                    output.println(i);
                    for (int j = 0; j < GamesManager.getInstance().games.get(i).players.size(); j++) {
                        output.println(GamesManager.getInstance().games.get(i).players.get(j).getName_());
                    }
                }
            }
            else if (line.charAt(0) == 'K') {
                onlyJump = false;
                turn.next();
                for (int i = 0; i < GamesManager.getInstance().games.get(room).players.size(); i++) {
                    if (GamesManager.getInstance().games.get(room).players.get(i) == turn.current()) {
                        if (GamesManager.getInstance().games.get(room).players.get(i) instanceof HumanPlayer)
                            GamesManager.getInstance().games.get(room).players.get(i).getOutput().println("Your Turn");
                        System.out.println("runda gracza= " + GamesManager.getInstance().games.get(room).players.get(i).getName_());
                    }
                }
            }
            // move(xxyyxxyy)
            else if (line.charAt(0) == 'M') {
                System.out.println(this + "Chce wykonac ruch!");
                if (turn.current() == this) {
                    int oldX, oldY, newX, newY;
                    oldX = Integer.parseInt(line.substring(1, 3));
                    oldY = Integer.parseInt(line.substring(3, 5));
                    newX = Integer.parseInt(line.substring(5, 7));
                    newY = Integer.parseInt(line.substring(7, 9));
                    int status = 0;
                    output.println(name + " Move: " + oldX + " " + oldY + " " + newX + " " + newY);
                    for (int i = 0; i < pieces.size(); i++) {
                            if (oldX == pieces.get(i).getX() && oldY == pieces.get(i).getY()) {
                                status = GamesManager.getInstance().games.get(room).board.move(pieces.get(i), newX, newY, this.mark, onlyJump);
                            }


                    }
                    if(status == 2)
                    {
                        System.out.println("KOLEJNY RUCH!");
                        onlyJump = true;
                        GamesManager.getInstance().games.get(room).board.displayBoard();
                        for (int i = 0; i < GamesManager.getInstance().games.get(room).players.size(); i++) {
                            if (GamesManager.getInstance().games.get(room).players.get(i) instanceof HumanPlayer)
                            GamesManager.getInstance().games.get(room).players.get(i).getOutput().println("U" + oldX + oldY + newX + newY);
                        }

                    }
                    if (status == 1) //wykonano ruch i dalej nastepna tura
                    {
                        System.out.print("Wykonano ruch!");
                        GamesManager.getInstance().games.get(room).board.displayBoard();
                        //update all players
                        for (int i = 0; i < GamesManager.getInstance().games.get(room).players.size(); i++) {
                            if (GamesManager.getInstance().games.get(room).players.get(i) instanceof HumanPlayer)
                            GamesManager.getInstance().games.get(room).players.get(i).getOutput().println("U" + oldX + oldY + newX + newY);
                        }

                    } else if (status == 0)
                        System.out.print("NIE PYKLO");
                    if(status != 2) {
                        while(turn.next() instanceof AIPlayer) {
                            turn.current().move();
                            GamesManager.getInstance().games.get(room).board.displayBoard();
                        }
                    }
                    for (int i = 0; i < GamesManager.getInstance().games.get(room).players.size(); i++) {
                        if (GamesManager.getInstance().games.get(room).players.get(i) == turn.current()) {
                            GamesManager.getInstance().games.get(room).players.get(i).getOutput().println("Your Turn");
                            System.out.println("runda gracza= " + GamesManager.getInstance().games.get(room).players.get(i).getName_());
                        }
                    }
                }
                if(GamesManager.getInstance().games.get(room).board.checkWinCondition(pieces, mark))
                    System.out.println( "GRACZ " + mark + " WYGRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAL!");
            } else if (line.charAt(0) == 'G') {
                output.println("Twoje pionki sa na pozycjach:");
                for (int i = 0; i < pieces.size(); i++) {
                    output.println(i + ":");
                    output.println("X = " + pieces.get(i).getX());
                    output.println("Y = " + pieces.get(i).getY());
                }
            }

            // leave room
            else if (line.charAt(0) == 'R') {
                GamesManager.getInstance().games.get(room).removePlayer(this);
                output.println(name + "removed from " + room + " room!");
            }
            // DEBUGGING
            else if (line.charAt(0) == 'L') {
                GamesManager.getInstance().games.get(room).board.displayBoard();
            }
            // DEBUGGING
            else if (line.charAt(0) == 'X') {
                for (int i = 0; i < GamesManager.getInstance().games.size(); i++) {
                    System.out.println(i);
                    for (int j = 0; j < GamesManager.getInstance().games.get(i).players.size(); j++) {
                        System.out.println(GamesManager.getInstance().games.get(i).players.get(j).getName_());
                    }
                }
                System.out.println("Starting game: 1");
                GamesManager.getInstance().games.get(1).start();
                GamesManager.getInstance().games.get(1).turn.resetTurn();
                while(GamesManager.getInstance().games.get(1).turn.next() instanceof AIPlayer) {
                    GamesManager.getInstance().games.get(1).turn.current().move();
                    GamesManager.getInstance().games.get(1).board.displayBoard();
                    if(GamesManager.getInstance().games.get(1).board.checkWinCondition(GamesManager.getInstance().games.get(1).turn.current().getPieces(),GamesManager.getInstance().games.get(1).turn.current().getMark())) {
                        System.out.printf("BOT " + GamesManager.getInstance().games.get(1).turn.current().getMark() + " WYGRAL!");
                        try {
                            Thread.sleep(50000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            // set name
            else if (line.charAt(0) == 'S') {
                name = line.substring(1);
                output.println("Name = " + name);
            }
            // enter room
            else {
                System.out.println("Liczba graczy = " + GamesManager.getInstance().games.get(room).players.size());
                int roomNumber = Integer.parseInt(line);
                room = roomNumber;
                if (!GamesManager.getInstance().games.get(roomNumber).players.contains(this))
                    GamesManager.getInstance().games.get(roomNumber).addPlayer(this);
                this.turn = GamesManager.games.get(room).turn;
                inGame = true;
                if (GamesManager.games.get(room).isFull()) {
                    System.out.println("Starting game: " + room);
                    GamesManager.getInstance().games.get(room).start();
                    GamesManager.getInstance().games.get(room).turn.resetTurn();
                    while(turn.next() instanceof AIPlayer) {
                        turn.current().move();
                    }
                    System.out.println(turn.current().getName_() + " turn!");
                    for (int i = 0; i < GamesManager.getInstance().games.get(roomNumber).players.size(); i++) {
                        System.out.print(i);
                        if (GamesManager.getInstance().games.get(roomNumber).players.get(i) == turn.current() && GamesManager.getInstance().games.get(roomNumber).players.get(i) instanceof HumanPlayer)
                            GamesManager.getInstance().games.get(roomNumber).players.get(i).getOutput().println("Your Turn");

                    }

                    output.println("Game Started! " + turn.current().getName_() + "'s turn!");
                }
            }


        }
    }

    public DataInputStream getInput() {
        return input;
    }

    public PrintStream getOutput() {
        return output;
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

    @Override
    public void move() {

    }

    @Override
    public String getName_() {
        return name;
    }

    @Override
    public ArrayList<Piece> getPieces()
    {
        return this.pieces;
    }

    private void setPieces() {
        pieces = GamesManager.getInstance().games.get(room).board.setPieces(mark);
    }
}
