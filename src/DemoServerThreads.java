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

        player1.send("WELCOME Player 1");
        System.out.println("before read");
        System.out.println(player1.receive());
        System.out.println("after read");

        player1.send("Exit");
        player2.send("Exit");
    }
}