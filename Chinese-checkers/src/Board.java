import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Math.min;

/**
 * Created by Damian Borek on 27/11/2017.
 */
public class Board {
    public final int WIDTH = 19;
    public final int HEIGHT = 19;
    private int YZone[][] = {{1,13}, {2,12}, {2,13}, {3,11}, {3,12}, {3,13}, {4,10}, {4,11}, {4,12}, {4,13}};
    private int GZone[][] = {{5,17}, {5,16}, {6,16}, {5,15}, {6,15}, {7,15}, {5,14}, {6,14}, {7,14}, {8,14}};
    private int LZone[][] = {{5,5}, {5,6}, {6,5}, {5,7}, {6,6}, {7,5}, {5,8}, {6,7}, {7,6}, {8,5}};
    private int BZone[][] = {{13,1}, {12,2}, {13,2}, {11,3}, {12,3}, {13,3}, {10,4}, {11,4}, {12,4}, {13,4}};
    private int PZone[][] = {{13,13}, {12,13}, {13,12}, {11,13}, {12,12}, {13,11}, {10,13}, {11,12}, {12,11}, {13,10}};
    private int RZone[][] = {{17,5}, {16,6}, {16,5}, {15,5}, {15,7}, {15,6}, {14,5}, {14,7}, {14,6}, {14,8}};
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
        System.out.println("            " + boardArray[1][13] + "            ");
        System.out.println("           " + boardArray[2][12] + " "  +  boardArray[2][13]+"           ");
        System.out.println("          " + boardArray[3][11] + " "  +  boardArray[3][12]+ " "  +  boardArray[3][13]+"          ");
        System.out.println("         " + boardArray[4][10] + " "  +  boardArray[4][11]+ " "  +  boardArray[4][12]+" " + boardArray[4][13] +"         ");
        for(int i=5;i<18;i++) System.out.print(boardArray[5][i] + " "); System.out.println();
        for(int i=5;i<17;i++) System.out.print(" "+boardArray[6][i]); System.out.println(); System.out.print("  ");
        for(int i=5;i<16;i++) System.out.print(boardArray[7][i] + " "); System.out.println(); System.out.print("   ");
        for(int i=5;i<15;i++) System.out.print(boardArray[8][i] + " "); System.out.println(); System.out.print("    ");
        for(int i=5;i<14;i++) System.out.print(boardArray[9][i] + " "); System.out.println(); System.out.print("   ");
        for(int i=4;i<14;i++) System.out.print(boardArray[10][i] + " "); System.out.println(); System.out.print("  ");
        for(int i=3;i<14;i++) System.out.print(boardArray[11][i] + " "); System.out.println(); System.out.print(" ");
        for(int i=2;i<14;i++) System.out.print(boardArray[12][i] + " "); System.out.println();
        for(int i=1;i<14;i++) System.out.print(boardArray[13][i] + " "); System.out.println();
        System.out.println("         " + boardArray[14][5] + " "  +  boardArray[14][6]+ " "  +  boardArray[14][7]+" " + boardArray[14][8] +"         ");
        System.out.println("          " + boardArray[15][5] + " "  +  boardArray[15][6]+ " "  +  boardArray[15][7]+"          ");
        System.out.println("           " + boardArray[16][5] + " "  +  boardArray[16][6]+"           ");
        System.out.println("            " + boardArray[17][5] + "            ");
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
        if(x+2 < 19)
        if(jump(x,y,x+2,y) && boardArray[x+2][y] == '#')
            return true;
        if(y+2 < 19)
        if(jump(x,y,x,y+2) && boardArray[x][y+2] == '#')
            return true;
        if(y-2 > -1)
        if(jump(x,y,x,y-2) && boardArray[x][y-2] == '#')
            return true;
        if(x-2 > -1)
        if(jump(x,y,x-2,y) && boardArray[x-2][y] == '#')
            return true;
        if(x-2 > -1 && y+2 < 19)
        if(jump(x,y,x-2,y+2) && boardArray[x-2][y+2] == '#')
            return true;
        if(x+2 < 19 && y-2 > -1)
        if(jump(x,y,x+2,y-2) && boardArray[x+2][y-2] == '#')
            return true;
        return false;
    }
    private int valid(int oldX, int oldY, int newX, int newY, boolean onlyJump)
    {
        System.out.println("Dystans ruchu: "+ distance(oldX, oldY, newX, newY) + " " + jump(oldX, oldY, newX, newY));
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
        if(newX == oldX && abs(newY-oldY) == 1)
            return 1;
        if(newY == oldY && abs(newX-oldX) == 1)
            return 1;
        if(newX == oldX && abs(newY-oldY) == 2)
            return 2;
        if(newY == oldY && abs(newX-oldX) == 2)
            return 2;
        if(newX-oldX == -1 && newY-oldY == 1)
            return 1;
        if(newX-oldX == 1 && newY-oldY == -1)
            return 1;
        if(newX-oldX == -2 && newY-oldY == 2)
            return 2;
        if(newX-oldX == 2 && newY-oldY == -2)
            return 2;

        return 0;
    }

    boolean checkWinCondition ( ArrayList<Piece> pieces, char mark )
    {
        int tmp = 0;
        if (mark == 'Y')
        {
            for(int i = 0;i <pieces.size() ; i++)
            {
                for(int j =0 ; j<10 ; j++)
                {
                    if(pieces.get(i).getX() == RZone[j][0] && pieces.get(i).getY() == RZone[j][1]) {
                        tmp++;
                        break;
                    }
                }
            }
        }
        else if (mark == 'G')
        {
            for(int i = 0;i <pieces.size() ; i++)
            {
                for(int j =0 ; j<10 ; j++)
                {
                    if(pieces.get(i).getX() == BZone[j][0] && pieces.get(i).getY() == BZone[j][1]) {
                        tmp++;
                        break;
                    }
                }
            }
        }
        else if (mark == 'L')
        {
            for(int i = 0;i <pieces.size() ; i++)
            {
                for(int j =0 ; j<10 ; j++)
                {
                    if(pieces.get(i).getX() == PZone[j][0] && pieces.get(i).getY() == PZone[j][1]) {
                        tmp++;
                        break;
                    }
                }
            }
        }
        else if (mark == 'P')
        {
            for(int i = 0;i <pieces.size() ; i++)
            {
                for(int j =0 ; j<10 ; j++)
                {
                    if(pieces.get(i).getX() == LZone[j][0] && pieces.get(i).getY() == LZone[j][1]) {
                        tmp++;
                        break;
                    }
                }
            }
        }
        else if (mark == 'R')
        {
            for(int i = 0;i <pieces.size() ; i++)
            {
                for(int j =0 ; j<10 ; j++)
                {
                    if(pieces.get(i).getX() == YZone[j][0] && pieces.get(i).getY() == YZone[j][1]) {
                        tmp++;
                        break;
                    }
                }
            }
        }
        else if (mark == 'B')
        {
            for(int i = 0;i <pieces.size() ; i++)
            {
                for(int j =0 ; j<10 ; j++)
                {
                    if(pieces.get(i).getX() == GZone[j][0] && pieces.get(i).getY() == GZone[j][1]) {
                        tmp++;
                        break;
                    }
                }
            }
        }
        if(tmp == pieces.size())
            return true;
        else
            return false;
    }

    int dist(int x1, int y1, int x2, int y2)
    {
        int dif = 0;
        if(x1 != x2 && y1 !=y2)
        {
            dif = min(abs(x1-x2),abs(y1-y2));
        }
        if(x2<x1 && y2<y1 || x2>x1 && y2>y1)
            dif = 0;
        return abs(x2-x1) + abs(y2-y1) - dif;
    }

    int[] checkOptions(Piece piece, int destX, int destY, int onlyJump)
    {
        int oldDist = 0;
        int d = 500;
        int tmpD = 500;
        int bestX = 0;
        int bestY = 0;
        int jump = 0;
        oldDist = dist(piece.getX(),piece.getY(), destX, destY);

        if(boardArray[piece.getX()+1][piece.getY()] == '#' && onlyJump == 0)
        {
            tmpD = dist(piece.getX() + 1, piece.getY(), destX, destY);
            if (tmpD <= d) {
                d = tmpD;
                bestX = piece.getX() + 1;
                bestY = piece.getY();
            }
        }
        if(boardArray[piece.getX()-1][piece.getY()] == '#' && onlyJump == 0)
        {
            tmpD = dist(piece.getX() - 1, piece.getY(), destX, destY);
            if (tmpD <= d) {
                d = tmpD;
                bestX = piece.getX() - 1;
                bestY = piece.getY();
            }
        }
        if(boardArray[piece.getX()][piece.getY() + 1] == '#' && onlyJump == 0)
        {
            tmpD = dist(piece.getX(), piece.getY() + 1, destX, destY);
            if (tmpD <= d) {
                d = tmpD;
                bestX = piece.getX();
                bestY = piece.getY() + 1;
            }
        }
        if(boardArray[piece.getX()][piece.getY()-1] == '#' && onlyJump == 0)
        {
            tmpD = dist(piece.getX(), piece.getY() - 1, destX, destY);
            if (tmpD <= d) {
                d = tmpD;
                bestX = piece.getX();
                bestY = piece.getY() - 1;
            }
        }
        if(boardArray[piece.getX()+1][piece.getY()-1] == '#' && onlyJump == 0)
        {
            tmpD = dist(piece.getX() + 1, piece.getY() - 1, destX, destY);
            if (tmpD <= d) {
                d = tmpD;
                bestX = piece.getX() + 1;
                bestY = piece.getY() - 1;
            }
        }
        if(boardArray[piece.getX()-1][piece.getY()+1] == '#' && onlyJump == 0)
        {
            tmpD = dist(piece.getX() - 1, piece.getY() + 1, destX, destY);
            if (tmpD <= d) {
                d = tmpD;
                bestX = piece.getX() - 1;
                bestY = piece.getY() + 1;
            }
        }
        if(piece.getX() + 2 < 19)
        if(boardArray[piece.getX()+2][piece.getY()] == '#' && boardArray[piece.getX()+1][piece.getY()] != '#' && boardArray[piece.getX()+1][piece.getY()] != '.')
        {
            tmpD = dist(piece.getX() + 2, piece.getY(), destX, destY);
            if (tmpD <= d) {
                d = tmpD;
                bestX = piece.getX() + 2;
                bestY = piece.getY();
                jump = 1;
            }
        }
        if(piece.getX() - 2 > -1)
        if(boardArray[piece.getX()-2][piece.getY()] == '#' && boardArray[piece.getX()-1][piece.getY()] != '#'&& boardArray[piece.getX()-1][piece.getY()] != '.')
        {
            tmpD = dist(piece.getX() - 2, piece.getY(), destX, destY);
            if (tmpD <= d) {
                d = tmpD;
                bestX = piece.getX() - 2;
                bestY = piece.getY();
                jump = 1;
            }
        }
        if(piece.getY() + 2 < 19)
        if(boardArray[piece.getX()][piece.getY()+2] == '#' && boardArray[piece.getX()][piece.getY()+1] != '#'&& boardArray[piece.getX()][piece.getY()+1] != '.')
        {
            tmpD = dist(piece.getX(), piece.getY() + 2, destX, destY);
            if (tmpD <= d) {
                d = tmpD;
                bestX = piece.getX();
                bestY = piece.getY() + 2;
                jump = 1;
            }
        }
        if(piece.getY() - 2 > -1)
        if(boardArray[piece.getX()][piece.getY()-2] == '#' && boardArray[piece.getX()][piece.getY()-1] != '#'&& boardArray[piece.getX()][piece.getY()-1] != '.')
        {
            tmpD = dist(piece.getX(), piece.getY() - 2, destX, destY);
            if (tmpD <= d) {
                d = tmpD;
                bestX = piece.getX();
                bestY = piece.getY() - 2;
                jump = 1;
            }
        }
        if(piece.getX() + 2 < 19 && piece.getY() - 2 > -1)
        if(boardArray[piece.getX()+2][piece.getY()-2] == '#' && boardArray[piece.getX()+1][piece.getY()-1] != '#' && boardArray[piece.getX()+1][piece.getY()-1] != '.')
        {
            tmpD = dist(piece.getX() + 2, piece.getY() - 2, destX, destY);
            if (tmpD <= d) {
                d = tmpD;
                bestX = piece.getX() + 2;
                bestY = piece.getY() - 2;
                jump = 1;
            }
        }
        if(piece.getX() - 2 > -1 && piece.getY() + 2 < 19)
        if(boardArray[piece.getX()-2][piece.getY()+2] == '#' && boardArray[piece.getX()-1][piece.getY()+1] != '#' && boardArray[piece.getX()-1][piece.getY()+1] != '.')
        {
            tmpD = dist(piece.getX() - 2, piece.getY() + 2, destX, destY);
            if (tmpD <= d) {
                d = tmpD;
                bestX = piece.getX() - 2;
                bestY = piece.getY() + 2;
                jump = 1;
            }
        }
        d = oldDist - d;
        int result[] = {bestX,bestY,d,jump};
        return result;

    }

    void AIMove(ArrayList<Piece> pieces, char mark)
    {
        int dest[][];
        if (mark == 'Y')
            dest = RZone;
        else if (mark == 'R')
            dest = YZone;
        else if (mark == 'B')
            dest = GZone;
        else if (mark == 'G')
            dest = BZone;
        else if (mark == 'P')
            dest = LZone;
        else
            dest = PZone;
        int done = 0;
        for(int i=0 ; i<pieces.size(); i++)
        {
            pieces.get(i).setDone(false);
        }
        for(int i=0; i<10 ; i++)
        {
            if(boardArray[dest[i][0]][dest[i][1]] == mark) {
                for(int j=0 ; j<pieces.size() ; j++) {
                    if(pieces.get(j).getByPosition(dest[i][0],dest[i][1]) != null)
                    {
                        pieces.remove(pieces.get(j).getByPosition(dest[i][0],dest[i][1]));
                    }
                }
                done++;
            }
            else break;
        }

        Random generator;
        generator = new Random();
        int jump = 0;
        int piece = -1;
        int limit = 0;
        do {
            piece = generator.nextInt(pieces.size());
            limit++;
            if(limit == 20)
                break;
        }while(checkOptions(pieces.get(piece), dest[done][0], dest[done][1], jump)[2] <= 0);

        int result[] = {-1,-1,500,0};
        int newX=-1;
        int newY=-1;

        do
        {
            result = checkOptions(pieces.get(piece), dest[done][0], dest[done][1], jump);
            if(result[2]> 0 || limit == 20) {
                limit = 0;
                newX = result[0];
                newY = result[1];
                jump = result[3];
                System.out.println("WYKONANO RUCH " +pieces.get(piece).getX() + " " + pieces.get(piece).getY() + " " + newX + " " + newY + "dys = " + result[2]);

                boardArray[newX][newY] = mark;
                boardArray[pieces.get(piece).getX()][pieces.get(piece).getY()] = '#';
                pieces.get(piece).setX(newX);
                pieces.get(piece).setY(newY);

                //displayBoard();
            }
            else break;

        }while(jump == 1);
    }
}
