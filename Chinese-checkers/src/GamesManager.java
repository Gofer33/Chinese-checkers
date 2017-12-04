import java.util.ArrayList;

/**
 * Created by Damian Borek on 28/11/2017.
 */
public class GamesManager {

    public static ArrayList<Game> games;
    private volatile static GamesManager instance;

    static{
        games = new ArrayList<Game>();
    }

    public static GamesManager getInstance()
    {
        if (instance == null)
        {
            synchronized (GamesManager.class)
            {
                if(instance == null)
                {
                    instance = new GamesManager();
                }
            }
        }
        return instance;
    }
}
