package MainMenu;

import java.io.*;
import java.net.Socket;

import static java.lang.System.out;

/**
 * Created by Kamil on 2017-12-29.
 */
public class ConnectionManager extends Thread{
    private Socket socket;
    private DataInputStream dataInputStream = null;
    private PrintStream printStream = null;
    private String input_data = new String();
    public MenuData menuData = null;
    private boolean readt_to_use = false;
    private Refresh refresh;

    class MenuData{
        boolean refresh = false;
        String nickname = null;
        String players[] = new String[6];
        int place = 0;

        MenuData(){
            for(int i = 0; i < 6; i++){
                players[i] = "*";
            }
        }
    }


    private BufferedReader in;
    private PrintWriter out;

    ConnectionManager(){

        menuData = new MenuData();
    }

    public void setRefresh(Refresh refresh){
        this.refresh = refresh;
    }

    int makeConnection(String IP) {
        try {
            if (IP.length() > 0) {
                socket = new Socket(IP, 1982);
                in = new BufferedReader(new InputStreamReader( socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                readt_to_use = true;
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

    public void createRoom(String name) {
        out.println("N" + name);
    }

    public void joinRoom(String name) {
        out.println("E" + name);
    }

    public void changeName(String name) {
        out.println("S" + name);
    }

    public void addBot() { out.println("B"); }

    public void closeSlot() { out.println("C"); }

    public void kickPlayer(String name) { out.println("T" + name); }

    public void startGame() { out.println("G"); }

    public void makeMove(String pos) { out.println("M" + pos); }

    public void run() {
        while (true) {
            if(readt_to_use) {
                try {
                    input_data = in.readLine();
                    System.out.println(input_data);
                    if(input_data.charAt(0) == 'P'){
                        if(menuData.place == 0) {
                            for (int i = 0; i < 6; i++) {
                                if (menuData.players[i] == "*") {
                                    menuData.players[i] = input_data.substring(1);
                                    refresh.makeRefresh();
                                    break;
                                }
                            }
                        }
                        else {
                            menuData.players[menuData.place] = input_data.substring(1);
                            menuData.place = 0;
                            refresh.makeRefresh();
                        }
                    }
                    if(input_data.charAt(0) == 'R'){
                        for(int i = 0; i < 6; i++){
                            System.out.println("Wyrzuc: " + input_data.substring(1));
                            if(menuData.players[i].equals(input_data.substring(1))) {
                                menuData.players[i] = "*";
                                refresh.makeRefresh();
                                break;
                            }
                        }
                    }
                    if(input_data.charAt(0) == 'U'){
                        refresh.makeMapRefresh(input_data.substring(1));
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public void test(){
        out.println("N24Pokoik");out.println("N13Room");out.println("N42Chata");
    }
}
