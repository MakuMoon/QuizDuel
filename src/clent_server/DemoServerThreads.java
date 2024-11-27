package clent_server;

import db.DataBaseConnection;
import model.Question;

import java.util.ArrayList;

public class DemoServerThreads extends Thread {

    DemoServerSidePlayer player1;
    DemoServerSidePlayer player2;

    public DemoServerThreads(DemoServerSidePlayer player1, DemoServerSidePlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1.setOpponent(player2);
        player2.setOpponent(player1);

    }


    public void run() {
        System.out.println("Thread is running...");

        //is player1
        player1.send("true");
        player2.send("false");

        //allow playyer1 to play
        player1.send("true");

        //send cat to player1
        player1.send("Sports,Vehicles,History");
        System.out.println("Categories sent to player1");

        //selected cat
        String category = player1.receive();
        System.out.println("Category received from player1: " + category);

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        ArrayList<Question> questions = dataBaseConnection.getQuestions(category, true);

        //send qquestions to player1
        for (Question question : questions) {
            player1.send(question.getQuestion());
            player1.send(question.getAnswers().toString());
            player1.send(question.getCorrectAnswer());
        }

        //get score result from player1
        String score = player1.receive();

        player1.setScore(score);

        //truetables sent to player1
        player1.send(player1.toString());
        player1.send(player2.toString());


        //player1 has given permission to proceed answered
        String answersDone = player1.receive();

        //if yourTurn should be true AND category should show up!
        player1.send("false");

        player1.send("false");

        //player1 is done and player2 is allowed to proceed
        player2.send(answersDone);

        //send cat to open questions
        player2.send(category);

        //send questions to player2
        for (Question question : questions) {
            player2.send(question.getQuestion());
            player2.send(question.getAnswers().toString());
            player2.send(question.getCorrectAnswer());
        }

        //recive result of answers
        score = player2.receive();

        player2.setScore(score);

        player2.send(player2.toString());
        player2.send(player1.toString());

        //gives permission to the other client to play
        answersDone = player2.receive();

        //gets to pick category
        player2.send("true");

        //player2 has permission to proceed
        player2.send(answersDone);

        //send cat to player2
        player2.send("Sports,Vehicles,History");
        System.out.println("Categories sent to player2");

        category = player2.receive();

        questions = dataBaseConnection.getQuestions(category, true);

        //send questions to player2
        for (Question question : questions) {
            player2.send(question.getQuestion());
            player2.send(question.getAnswers().toString());
            player2.send(question.getCorrectAnswer());
        }

        //recive result of answers
        score = player2.receive();

        player2.setScore(score);

        player2.send(player2.toString());
        player2.send(player1.toString());

        player2.send("false");
        player2.send("false");


        while (true) {
        }


    }
}