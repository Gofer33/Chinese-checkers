import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by Damian Borek on 27/11/2017.
 */
public class Board {
    public final int WIDTH = 19;
    public final int HEIGHT = 19;
    private int YZone[][] = {{1,13}, {2,12}, {2,13}, {3,11}, {3,12}, {3,13}, {4,10}, {4,11}, {4,12}, {4,13}};
    private int GZone[][] = {{5,14}, {5,15}, {5,16}, {5,17}, {6,14}, {6,15}, {6,16}, {7,14}, {7,15}, {8,14}};
    private int LZone[][] = {{5,5}, {5,6}, {5,7}, {5,8}, {6,5}, {6,6}, {6,7}, {7,5}, {7,6}, {8,5}};
    private int BZone[][] = {{10,4}, {11,3}, {11,4}, {12,2}, {12,3}, {12,4}, {13,1}, {13,2}, {13,3}, {13,4}};
    private int PZone[][] = {{10,13}, {11,12}, {11,13}, {12,11}, {12,12}, {12,13}, {13,10}, {13,11}, {13,12}, {13,13}};
    private int RZone[][] = {{14,5}, {14,6}, {14,7}, {14,8}, {15,5}, {15,6}, {15,7}, {16,5}, {16,6}, {17,5}};
    /*
    Y
   L  G
   B  P
    R
    */
    char boardArray[][] =
    {
            {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','#','#','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','#','#','#','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','#','#','#','#','.','.','.','.','.'},
            {'.','.','.','.','.','#','#','#','#','#','#','#','#','#','#','#','#','#','.'},
            {'.','.','.','.','.','#','#','#','#','#','#','#','#','#','#','#','#','.','.'},
            {'.','.','.','.','.','#','#','#','#','#','#','#','#','#','#','#','.','.','.'},
            {'.','.','.','.','.','#','#','#','#','#','#','#','#','#','#','.','.','.','.'},
            {'.','.','.','.','.','#','#','#','#','#','#','#','#','#','.','.','.','.','.'},
            {'.','.','.','.','#','#','#','#','#','#','#','#','#','#','.','.','.','.','.'},
            {'.','.','.','#','#','#','#','#','#','#','#','#','#','#','.','.','.','.','.'},
            {'.','.','#','#','#','#','#','#','#','#','#','#','#','#','.','.','.','.','.'},
            {'.','#','#','#','#','#','#','#','#','#','#','#','#','#','.','.','.','.','.'},
            {'.','.','.','.','.','#','#','#','#','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','#','#','#','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','#','#','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','#','.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'}
    };
    void displayBoard()
    {
        for(int i = 0 ; i < WIDTH ; i++)
        {
            for(int j = 0 ; j < HEIGHT ; j++)
            {
                System.out.print(boardArray[i][j]);
            }
            System.out.println("");
        }
    }
    void initialize(int playersNumber)
    {
        if(playersNumber == 2)
        {
            for(int i =0 ; i< 10 ; i ++)
            {
                boardArray[YZone[i][0]][YZone[i][1]] = 'Y';
                boardArray[RZone[i][0]][RZone[i][1]] = 'R';
            }
        }
        else if(playersNumber == 3)
        {
            for(int i =0 ; i< 10 ; i ++)
            {
                boardArray[YZone[i][0]][YZone[i][1]] = 'Y';
                boardArray[PZone[i][0]][PZone[i][1]] = 'P';
                boardArray[BZone[i][0]][BZone[i][1]] = 'B';
            }
        }
        else if(playersNumber == 4)
        {
            for(int i =0 ; i< 10 ; i ++)
            {
                boardArray[GZone[i][0]][GZone[i][1]] = 'G';
                boardArray[PZone[i][0]][PZone[i][1]] = 'P';
                boardArray[BZone[i][0]][BZone[i][1]] = 'B';
                boardArray[LZone[i][0]][LZone[i][1]] = 'L';
            }
        }
        else if(playersNumber == 6)
        {
            for(int i =0 ; i< 10 ; i ++)
            {
                boardArray[GZone[i][0]][GZone[i][1]] = 'G';
                boardArray[PZone[i][0]][PZone[i][1]] = 'P';
                boardArray[BZone[i][0]][BZone[i][1]] = 'B';
                boardArray[LZone[i][0]][LZone[i][1]] = 'L';
                boardArray[RZone[i][0]][RZone[i][1]] = 'R';
                boardArray[YZone[i][0]][YZone[i][1]] = 'Y';
            }
        }
    }
    public ArrayList<Piece> setPieces(char mark)
    {
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        for(int i = 0 ; i <10 ; i++)
        {
            if(mark == 'Y')
                pieces.add(new Piece(YZone[i][0],YZone[i][1]));
            else if(mark == 'P')
                pieces.add(new Piece(PZone[i][0],PZone[i][1]));
            else if(mark == 'R')
                pieces.add(new Piece(RZone[i][0],RZone[i][1]));
            else if(mark == 'L')
                pieces.add(new Piece(LZone[i][0],LZone[i][1]));
            else if(mark == 'G')
                pieces.add(new Piece(GZone[i][0],GZone[i][1]));
            else if(mark == 'B')
                pieces.add(new Piece(BZone[i][0],BZone[i][1]));
        }
        return pieces;
    }

    public int move(Piece piece, int newX, int newY, char mark, boolean onlyJump)
    {
        int valid = valid(piece.getX(), piece.getY(), newX, newY, onlyJump);
            if(valid == 1 || valid == 2 )
            {
                if(valid == 2)
                    System.out.println("JUMPED!");
                else
                    System.out.println("NOT JUMPED!");
                boardArray[newX][newY] = mark;
                boardArray[piece.getX()][piece.getY()] = '#';
                piece.setX(newX);
                piece.setY(newY);
                if(valid == 2 && isValidMove(newX,newY))
                    return 2;
                return 1;
            }


        return 0;
    }
    private boolean isValidMove(int x, int y)
    {
        if(jump(x,y,x+2,y) && boardArray[x+2][y] == '#') // tu mozna wyskoczyc poza zakres
            return true;
        if(jump(x,y,x,y+2) && boardArray[x][y+2] == '#')
            return true;
        if(jump(x,y,x,y-2) && boardArray[x][y-2] == '#')
            return true;
        if(jump(x,y,x-2,y) && boardArray[x-2][y] == '#')
            return true;
        if(jump(x,y,x-2,y-2) && boardArray[x-2][y-2] == '#')
            return true;
        if(jump(x,y,x-2,y+2) && boardArray[x-2][y+2] == '#')
            return true;
        if(jump(x,y,x+2,y+2) && boardArray[x+2][y+2] == '#')
            return true;
        if(jump(x,y,x+2,y-2) && boardArray[x+2][y-2] == '#')
            return true;
        return false;
    }
    private int valid(int oldX, int oldY, int newX, int newY, boolean onlyJump)
    {
        System.out.println("Dystans ruchu: "+ distance(oldX, oldY, newX, newY));
        if(boardArray[newX][newY] != '#')
            return 0;
        if(distance(oldX, oldY, newX, newY) == 1 && onlyJump == false)
            return 1;
        else if((distance(oldX, oldY, newX, newY) == 2) && jump(oldX, oldY, newX, newY))
            return 2;

        else {
            System.out.println("Dystans nie taki");
            return 0;
        }
    }
    private boolean jump(int oldX, int oldY, int newX, int newY)
    {
        int x = (oldX + newX)/2;
        int y = (oldY + newY)/2;
        if(boardArray[x][y] != '#' || boardArray[x][y] != '.')
            return true;
        else return false;
    }
    private int distance(int oldX, int oldY, int newX, int newY)
    {

        if(oldX == newX || oldY == newY)
            return abs(oldX-newX) + abs(oldY - newY) ;
        else
        {
            if(abs(oldX-newX) == 2  && abs(oldY - newY) == 2)
                return 2;
            else if (abs(oldX-newX) == 1  && abs(oldY - newY) == 1)
                return 1;
        }
        return 0;
    }

    boolean checkWinCondition ( ArrayList<Piece> pieces, char mark )
    {
        boolean win=true;
        if (mark == 'Y')
        {
            for(int i = 0;i <10 ; i++)
            {
                int temp = 0;
                if(pieces.get(i).getX() == RZone[i][0] && pieces.get(i).getY() == RZone[i][1])
                    temp++;
                if(temp != 1)
                    win = false;
            }
        }
        else if (mark == 'G')
        {
            for(int i = 0;i <10 ; i++)
            {
                int temp = 0;
                if(pieces.get(i).getX() == BZone[i][0] && pieces.get(i).getY() == BZone[i][1])
                    temp++;
                if(temp != 1)
                    win = false;
            }
        }
        else if (mark == 'L')
        {
            for(int i = 0;i <10 ; i++)
            {
                int temp = 0;
                if(pieces.get(i).getX() == PZone[i][0] && pieces.get(i).getY() == PZone[i][1])
                    temp++;
                if(temp != 1)
                    win = false;
            }
        }
        else if (mark == 'P')
        {
            for(int i = 0;i <10 ; i++)
            {
                int temp = 0;
                if(pieces.get(i).getX() == LZone[i][0] && pieces.get(i).getY() == LZone[i][1])
                    temp++;
                if(temp != 1)
                    win = false;
            }
        }
        else if (mark == 'R')
        {
            for(int i = 0;i <10 ; i++)
            {
                int temp = 0;
                if(pieces.get(i).getX() == YZone[i][0] && pieces.get(i).getY() == YZone[i][1])
                    temp++;
                if(temp != 1)
                    win = false;
            }
        }
        else if (mark == 'B')
        {
            for(int i = 0;i <10 ; i++)
            {
                int temp = 0;
                if(pieces.get(i).getX() == GZone[i][0] && pieces.get(i).getY() == GZone[i][1])
                    temp++;
                if(temp != 1)
                    win = false;
            }
        }
        return win;
    }
}
