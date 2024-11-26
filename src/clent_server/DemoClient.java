package clent_server;

import resultat_gui.ResultatGUI;
import resultat_gui.SaveResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DemoClient {

    private static final int PORT = 24503;
    Socket socket;
    public BufferedReader in;
    public PrintWriter out;

    public DemoClient(String serverAddress) throws IOException {
        System.out.println(serverAddress);
        System.out.println(PORT);
        socket = new Socket(serverAddress, PORT);

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public static void main(String[] args) {
        System.out.println("Player Started");

        try {
            DemoClient client = new DemoClient("localhost");

            SaveResult saveResult = new SaveResult();
            saveResult.createFile();

            String response = client.in.readLine();
            boolean isPlayer1 = Boolean.parseBoolean(response);

            
            System.out.println("gui started");
            new ResultatGUI(client, 0, 0, isPlayer1, saveResult.readResult(true), saveResult.readResult(false));


        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void play() throws IOException {

        String response;


        response = in.readLine();

        System.out.println(response);

        if (response.equals("Exit")) {

            socket.close();
            System.exit(0);
        }

        out.println("Hello from client");

    }
}