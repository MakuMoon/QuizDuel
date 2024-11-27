package clent_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class DemoServerSidePlayer {

        DemoServerSidePlayer opponent;
        Socket socket;
        BufferedReader input;
        PrintWriter output;
        int player;
        ArrayList<Integer> trueTable = new ArrayList<>();


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

            for (int i = 0; i < 18; i++) {
                trueTable.add(-1);
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

        public ArrayList<Integer> getTrueTable() {
            return trueTable;
        }

        public void setScore(String roundResultString) {

            ArrayList<Integer> roundResult = new ArrayList<>();

            String[] roundResultArray = roundResultString.split(",");

            for (String value : roundResultArray) {
                roundResult.add(Integer.parseInt(value));
            }

            int countNegativeOne = 0;

            for (int i = 0; i < trueTable.size(); i++) {

                if (trueTable.get(i) == -1){
                    trueTable.set(i, roundResult.get(countNegativeOne));
                    countNegativeOne++;

                    if (countNegativeOne == 3) {
                        break;
                    }
                }

            }
        }


        public void setOpponent(DemoServerSidePlayer opponent) {
            this.opponent = opponent;
        }


        public DemoServerSidePlayer getOpponent() {
            return opponent;
        }

        public String toString() {
            String s = "";
            for (Integer v: getTrueTable()){
                s += v + ",";
            }
            return s;
        }


}
