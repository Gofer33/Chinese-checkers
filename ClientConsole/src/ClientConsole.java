import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static java.lang.System.*;

/**
 * Created by Damian Borek on 28/11/2017.
 */
/*
TODO: pozamykac sockety i strumienie przy wylaczaniu klienta
protokol komunikacyjny z serwerem:
NXY (zalozenie nowego stolika, X - liczba graczy zywych, Y - liczba botow)
D (zwraca liczbe aktywnych stolikow)
A (zwraca informacje o stolikach razem z graczami, ktorzy sa przypisani do danego stolika)
MXXYYZZWW (wykonaniu ruchu, XX YY stare wspolrzedne, ZZ WW nowe wspolrzedne, aktualnie nic nie robi)
R ( wyjscie z aktualnego stolika)
SX( gdzie X jest nowym nickiem gracza)
X ( wejscie do stolika o ID = X)
 */
public class ClientConsole {
    static final int PORT = 1978;

    public static void main(String[] args) {
        Socket socket = null;
        String response;
        String tmp;
        try {
            socket = new Socket("127.0.0.1", PORT);
        } catch (IOException e) {
            out.println(e);
        }

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
        DataInputStream inputLine = new DataInputStream(new BufferedInputStream(System.in));

        try {

            //Do testowania
            System.out.println("Podaj nick");
            output.println("S" + inputLine.readLine());
            System.out.println("Stworz gre");
            output.println("N" + inputLine.readLine());
            System.out.println("Do ktorego pokoju chcesz dolaczyc?");
            output.println(inputLine.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                //nasluchiwanie odpowiedzi serwera
                 response = input.readLine();
                if(response.equals("Your Turn")) {
                    System.out.println("Your Turn, Move!");
                    output.println(inputLine.readLine());
                }
                else if(response.startsWith("U")) {
                    System.out.println("Board changed !" + response);
                }
                else
                    System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }
}
