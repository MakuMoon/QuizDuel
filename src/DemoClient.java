import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DemoClient {

    private static final int PORT = 24500;
    Socket socket;
    private BufferedReader in;
    private PrintWriter out;

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
            while (true) {
                client.play();
            }
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