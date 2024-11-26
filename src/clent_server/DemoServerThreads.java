package clent_server;

import db.DataBaseConnection;
import model.Question;

import java.util.ArrayList;

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

        player1.send("true");
        player2.send("false");

        player1.send("true");

        player1.send("Sports,Vehicles,History");
        System.out.println("Categories sent to player1");

        String category = player1.receive();
        System.out.println("Category received from player1: " + category);

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        ArrayList<Question> questions = dataBaseConnection.getQuestions(category);

        for (Question question : questions) {
            player1.send(question.getQuestion());
            player1.send(question.getAnswers().toString());
            player1.send(question.getCorrectAnswer());
        }

        String answersDone = player1.receive();

        player2.send(answersDone);

        player2.send(category);

        for (Question question : questions) {
            player2.send(question.getQuestion());
            player2.send(question.getAnswers().toString());
            player2.send(question.getCorrectAnswer());
        }
    
        player2.send("true");

        player1.send("false");


        while (true) {}


    }
}