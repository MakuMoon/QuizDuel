import java.io.IOException;
import java.net.ServerSocket;

public class DemoServer {

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(24500);
        System.out.println("Server is running...");

        try {
            System.out.println("Waiting for players to connect...");
            DemoServerSidePlayer player1 = new DemoServerSidePlayer(listener.accept(), 1);
            System.out.println("Player 1 connected.");
            DemoServerSidePlayer player2 = new DemoServerSidePlayer(listener.accept(), 2);
            System.out.println("Player 2 connected.");
            DemoServerThreads game = new DemoServerThreads(player1, player2);
            System.out.println("Starting game thread...");
            game.start();
        } finally {
            listener.close();
        }
    }
}