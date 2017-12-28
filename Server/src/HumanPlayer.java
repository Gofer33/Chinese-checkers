import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.io.DataInputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damian Borek on 27/11/2017.
 */
public class HumanPlayer extends Thread implements Player {

    private Socket socket;
    String name;
    DataInputStream input = null;
    PrintStream output = null;
    char mark;
    char oppositeMark;
    ArrayList<Piece> pieces;
    boolean onlyJump=false;
    String roomName;
    Piece recentPiece;

    public HumanPlayer(Socket socket) {
        this.socket = socket;
        this.name = "Default name";
        //room = -1;

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

        try {
            while ((line = input.readLine()) != null) {
                System.out.println(line);

                //new room
                if (line.charAt(0) == 'N') {

                    int humanPlayers = line.charAt(1) - '0';
                    int AIPlayers = line.charAt(2) - '0';
                    String tableName = line.substring(3);

                    if(AIPlayers + humanPlayers > 6 || AIPlayers + humanPlayers <= 1 || humanPlayers == 0 || AIPlayers + humanPlayers == 5)
                    {
                        displayErrorMsg(output, "Wrong ammount of players!");
                        break;
                    }
                    if(tableName.equals(""))
                    {
                        displayErrorMsg(output, "Table Name can't be empty!");
                        break;
                    }
                    if(createNewGame(humanPlayers, AIPlayers, tableName) == 0)
                    {
                        displayErrorMsg(output, "Game couldn't be created!");
                        break;
                    }
                    else
                        System.out.println("NEW GAME CREATED [humanPlayers] = " + humanPlayers + " [AIPlayers] = " + AIPlayers + " [Name] = " + tableName );
                }
                // show games with players
                else if (line.charAt(0) == 'A') {
                    sendOutput();
                }
                else if (line.charAt(0) == 'K') {
                    onlyJump = false;
                    GamesManager.getInstance().getGameByName(roomName).turn.next();
                }
                // move(xxyyxxyy)
                else if (line.charAt(0) == 'M') {
                    if (GamesManager.getInstance().getGameByName(roomName).turn.current() == this) {
                        int oldX, oldY, newX, newY;
                        oldX = Integer.parseInt(line.substring(1, 3));
                        oldY = Integer.parseInt(line.substring(3, 5));
                        newX = Integer.parseInt(line.substring(5, 7));
                        newY = Integer.parseInt(line.substring(7, 9));
                        int status = 0;
                        //looking for choosen piece and perform move
                        if(onlyJump)
                        {
                            if(oldX == recentPiece.getX() && oldY == recentPiece.getY())
                            {
                                status = GamesManager.getInstance().getGameByName(roomName).board.move(recentPiece, newX, newY, this.mark, onlyJump);
                            }
                            else
                                status = 0;
                        }
                        else {
                            for (int i = 0; i < pieces.size(); i++) {
                                if (oldX == pieces.get(i).getX() && oldY == pieces.get(i).getY()) {
                                    status = GamesManager.getInstance().getGameByName(roomName).board.move(pieces.get(i), newX, newY, this.mark, onlyJump);
                                    if (status == 2) {
                                        recentPiece = pieces.get(i);
                                        onlyJump = true;
                                    }
                                }
                            }
                        }
                        //update if move performed
                        if (status != 0)
                            update(oldX, oldY, newX, newY);
                         else if (status == 0) {
                            displayErrorMsg(output, "Wrong Move!");
                            output.println("Your Turn!");
                        }
                        if(status == 2)
                            output.println("Your Turn!");

                        GamesManager.getInstance().getGameByName(roomName).board.displayBoard();
                        if(GamesManager.getInstance().getGameByName(roomName).board.checkWinCondition(pieces, mark))
                            System.out.println( "GRACZ " + mark + " WYGRAL!");
                        if(status != 2 && status != 0) {
                            GamesManager.getInstance().getGameByName(roomName).turn.next();
                        }
                    }
                    else
                        displayErrorMsg(output, "Not Your Turn!");

                }
                // leave room
                else if (line.charAt(0) == 'R') {
                    GamesManager.getInstance().getGameByName(roomName).removePlayer(this);
                    System.out.println(name + "removed from " + roomName + " room!");
                }

                // DEBUGGING
                /*else if (line.charAt(0) == 'X') {
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
                }*/
                // set name
                else if (line.charAt(0) == 'S') {
                    this.name = line.substring(1);
                }
                // enter room
                else if (line.charAt(0) == 'E') {
                    //looking for given room
                    if(GamesManager.getInstance().getGameByName(line.substring(1)) == null)
                    {
                        displayErrorMsg(output, "There is no such room!");
                        break;
                    }
                    // remember room name
                    this.roomName = line.substring(1);
                    //preventing entering twice into same room
                    if (!GamesManager.getInstance().getGameByName(roomName).players.contains(this))
                        GamesManager.getInstance().getGameByName(roomName).addPlayer(this);
                    else {
                        displayErrorMsg(output, "You already are in this room!");
                        break;
                    }

                    if (GamesManager.getInstance().getGameByName(roomName).isFull()) {
                        startGame(roomName);
                    }
                }
                else
                {
                    displayErrorMsg(output, "Wrong command!");
                }
            }
        } catch (SocketException ex) {
            System.out.println("SOCKET = " + socket + " WYCHODZI!");
            try {
                input.close();
                output.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendOutput()
    {
        String str = "";
        for (int i = 0; i < GamesManager.getInstance().games.size(); i++) {
            str = str + GamesManager.getInstance().games.get(i).getTableName()+"|"+GamesManager.getInstance().games.get(i).getHumanPlayers();
            str = str + "|"+ GamesManager.getInstance().games.get(i).getAIPlayers()+"|"+GamesManager.getInstance().games.get(i).players.size()+"|";

            for (int j = 0; j < GamesManager.getInstance().games.get(i).players.size(); j++) {
                str = str + "|" + GamesManager.getInstance().games.get(i).players.get(j).getName_();
            }
            str = str + " ";
        }
        output.println(str);
    }
    private void update(int oldX, int oldY, int newX, int newY)
    {
        String oX="",oY="",nX="",nY="",msg="";
        if(oldX<10)
            oX="0";
        oX = oX + Integer.toString(oldX);
        if(oldY<10)
            oY="0";
        oY=oY + Integer.toString(oldY);
        if(newX<10)
            nX="0";
        nX=nX + Integer.toString(newX);
        if(newY<10)
            nY="0";
        nY=nY + Integer.toString(newY);

        msg = msg + oX + oY + nX + nY;
        for(int i =0 ; i<GamesManager.getInstance().getGameByName(roomName).players.size(); i++)
            if(GamesManager.getInstance().getGameByName(roomName).players.get(i) instanceof HumanPlayer)
                GamesManager.getInstance().getGameByName(roomName).players.get(i).getOutput().println("U" + msg);
    }

    private void startGame(String tableName)
    {
        System.out.println("Starting game: " + roomName);
        GamesManager.getInstance().getGameByName(roomName).start();
        GamesManager.getInstance().getGameByName(roomName).turn.resetTurn();
    }

    private void displayErrorMsg(PrintStream output, String msg)
    {
        output.println("E" + msg);
    }

    private int createNewGame(int humanPlayers, int AIPlayers, String tableName)
    {
        for(int i=0;i<GamesManager.getInstance().games.size();i++)
        {
            // that name exists
            if(GamesManager.getInstance().games.get(i).getTableName() == tableName)
                return 0;
        }
        //create new table
        Game game = new Game(humanPlayers, AIPlayers, tableName);
        //add table to manager
        GamesManager.getInstance().games.add(game);
        //remember table
        this.roomName = tableName;
        return 1;
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
        pieces = GamesManager.getInstance().getGameByName(roomName).board.setPieces(mark);
    }

}