/**
 * Created by Damian Borek on 08/12/2017.
 */
public class Piece {
    int x;
    int y;


    Piece(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    void setX(int x)
    {
        this.x = x;
    }

    void setY(int y)
    {
        this.y = y;
    }



    int getX()
    {
        return this.x;
    }

    int getY()
    {
        return this.y;
    }
}
