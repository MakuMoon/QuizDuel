import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DemoServerSidePlayer {

        DemoServerSidePlayer opponent;
        Socket socket;
        BufferedReader input;
        PrintWriter output;
        int player;

        public DemoServerSidePlayer(Socket socket, int player) {
            this.socket = socket;
            this.player = player;
            try {
                input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            }
        }

        /*
        Sends data to client
         */

        public void send(String mess){
            output.println(mess);
        }

/*
Receives data from client
 */

        public String receive()  {
            try {
                return input.readLine();
            } catch (IOException e) {
                System.out.println("Player "+ this +" could not receive data " + e);
                throw new RuntimeException(e);
            }
        }


        public void setOpponent(DemoServerSidePlayer opponent) {
            this.opponent = opponent;
        }


        public DemoServerSidePlayer getOpponent() {
            return opponent;
        }


}
