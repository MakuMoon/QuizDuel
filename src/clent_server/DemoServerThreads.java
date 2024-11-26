package clent_server;

public class DemoServerThreads extends Thread{

    DemoServerSidePlayer player1;
    DemoServerSidePlayer player2;

    public DemoServerThreads(DemoServerSidePlayer player1, DemoServerSidePlayer player2){
        this.player1=player1;
        this.player2=player2;
        player1.setOpponent(player2);
        player2.setOpponent(player1);

    }


    public void run() {
        System.out.println("Thread is running...");

        player2.send("Sports,Vehicles,History");

        while(true){}


    }
}