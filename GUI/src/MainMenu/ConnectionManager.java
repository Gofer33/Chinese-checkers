package MainMenu;

import java.io.*;
import java.net.Socket;

import static java.lang.System.out;

/**
 * Created by Kamil on 2017-12-29.
 */
public class ConnectionManager extends Thread{
    Socket socket;
    DataInputStream dataInputStream = null;
    PrintStream printStream = null;
    String room = new String();


    private BufferedReader in;
    private PrintWriter out;


    int makeConnection(String IP) {
        try {
            if (IP.length() > 0) {
                socket = new Socket(IP, 1980);
                in = new BufferedReader(new InputStreamReader( socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                return 0;
            } else {
                return 2;
            }
        } catch (IOException e) {
            return 1;
        }
    }

    public String getRooms(){
        out.println("A");
        String response = "";
        try {
            response = in.readLine();
            System.out.println(response);
        }
        catch(IOException e){
            System.out.println(e);
        }
        System.out.println(response);
        return response;
    }

 /*   public void run() {
    //    out.println("A");
        while (true) {
            System.out.println("AAAA");
            try {
                room = in.readLine();
                System.out.println(room);
            }
            catch(IOException e){
                System.out.println(e);
            }
        }
    }*/

    public void test(){
        out.println("SZATAN^^^");
    }
}
