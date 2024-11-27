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

        int turns = 1;
        while (turns <= 3) {
            runTurn(turns);
            turns++;
        }

//        //is player1
//        player1.send("true");
//        player2.send("false");
//
//        //allow playyer1 to play
//        player1.send("true");
//
//        //send cat to player1
//        player1.send("Sports,Vehicles,History");
//        System.out.println("Categories sent to player1");
//
//        //selected cat
//        String category = player1.receive();
//        System.out.println("Category received from player1: " + category);
//
//        DataBaseConnection dataBaseConnection = new DataBaseConnection();
//        ArrayList<Question> questions = dataBaseConnection.getQuestions(category, true);
//
//        //send qquestions to player1
//        for (Question question : questions) {
//            player1.send(question.getQuestion());
//            player1.send(question.getAnswers().toString());
//            player1.send(question.getCorrectAnswer());
//        }
//
//        //get score result from player1
//        String score = player1.receive();
//
//        player1.setScore(score);
//
//        //truetables sent to player1
//        player1.send(player1.toString());
//        player1.send(player2.toString());
//
//
//        //player1 has given permission to proceed answered
//        String answersDone = player1.receive();
//
//        //if yourTurn should be true AND category should show up!
//        player1.send("false");
//
//        player1.send("false");
//        //player1 is done and player2 is allowed to proceed
//        player2.send(answersDone);
//
//        //send cat to open questions
//        player2.send(category);
//
//        //send questions to player2
//        for (Question question : questions) {
//            player2.send(question.getQuestion());
//            player2.send(question.getAnswers().toString());
//            player2.send(question.getCorrectAnswer());
//        }
//
//        //recive result of answers
//        score = player2.receive();
//
//        player2.setScore(score);
//
//        player2.send(player2.toString());
//        player2.send(player1.toString());
//
//        //gives permission to the other client to play
//        answersDone = player2.receive();
//
//        //gets to pick category
//        player2.send("true");
//
//        //player2 has permission to proceed
//        player2.send(answersDone);
//
//        //send cat to player2
//        player2.send("Sports,Vehicles,History");
//        System.out.println("Categories sent to player2");
//
//        category = player1.receive();
//        System.out.println("Category received from player2: " + category);;
//
//        //send qquestions to player1
//        for (Question question : questions) {
//            player1.send(question.getQuestion());
//            player1.send(question.getAnswers().toString());
//            player1.send(question.getCorrectAnswer());
//        }
//
//        //get score result from player1
//        score = player2.receive();
//
//        player2.setScore(score);
//
//        //truetables sent to player1
//        player1.send(player1.toString());
//        player1.send(player2.toString());
//
//
//        //player1 has given permission to proceed answered
//        answersDone = player2.receive();
//
//        //if yourTurn should be true AND category should show up!
//        player2.send("false");
//
//        player2.send("false");
//        //player1 is done and player2 is allowed to proceed
//        player1.send(answersDone);


        while (true) {

        }
    }

    private void runTurn(int turns) {
        //is player1
        System.out.println("kör omgång " + turns);
        DemoServerSidePlayer firstPlayer;
        DemoServerSidePlayer secondPlayer;

        player1.send("true");
        player2.send("false");
       // boolean isPlayer1 = true;
        if(turns == 2) {
           firstPlayer = player2;
            secondPlayer = player1;
        }  else {
            secondPlayer = player2;
            firstPlayer = player1;
        }
        System.out.println(firstPlayer);
        System.out.println(secondPlayer);

        firstPlayer.send("true");

        //allow playyer1 to play
       // player1.send("true");

        //send cat to player1
        firstPlayer.send("Sports,Vehicles,History");
        System.out.println("Categories sent to player1");

        //selected cat
        String category = player1.receive();
        System.out.println("Category received from player1: " + category);

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        ArrayList<Question> questions = dataBaseConnection.getQuestions(category, true);

        //send qquestions to player1
        for (Question question : questions) {
            firstPlayer.send(question.getQuestion());
            firstPlayer.send(question.getAnswers().toString());
            firstPlayer.send(question.getCorrectAnswer());
        }

        //get score result from player1
        String score = firstPlayer.receive();

        firstPlayer.setScore(score);

        //truetables sent to player1
        firstPlayer.send(firstPlayer.toString());
        firstPlayer.send(firstPlayer.toString());


        //player1 has given permission to proceed answered
        String answersDone = firstPlayer.receive();

        //if yourTurn should be true AND category should show up!
        firstPlayer.send("false");

        firstPlayer.send("false");
        //player1 is done and player2 is allowed to proceed
        secondPlayer.send(answersDone);

        //send cat to open questions
        secondPlayer.send(category);

        //send questions to player2
        for (Question question : questions) {
            secondPlayer.send(question.getQuestion());
            secondPlayer.send(question.getAnswers().toString());
            secondPlayer.send(question.getCorrectAnswer());
        }

        //recive result of answers
        score = secondPlayer.receive();

        secondPlayer.setScore(score);

        secondPlayer.send(secondPlayer.toString());
        secondPlayer.send(secondPlayer.toString());

        //gives permission to the other client to play
        answersDone = secondPlayer.receive();

//        //gets to pick category
//        secondPlayer.send("true");
//
//        //player2 has permission to proceed
//        secondPlayer.send(answersDone);
//
//        //send cat to player2
//        secondPlayer.send("Sports,Vehicles,History");
//        System.out.println("Categories sent to player2");
//
//        category = firstPlayer.receive();
//        System.out.println("Category received from player2: " + category);;
//
//        //send qquestions to player1
//        for (Question question : questions) {
//            firstPlayer.send(question.getQuestion());
//            firstPlayer.send(question.getAnswers().toString());
//            firstPlayer.send(question.getCorrectAnswer());
//        }
//
//        //get score result from player1
//        score = secondPlayer.receive();
//
//        secondPlayer.setScore(score);
//
//        //truetables sent to player1
//        firstPlayer.send(firstPlayer.toString());
//        firstPlayer.send(firstPlayer.toString());
//
//
//        //player1 has given permission to proceed answered
//        answersDone = secondPlayer.receive();
//
//        //if yourTurn should be true AND category should show up!
//        secondPlayer.send("false");
//
//        firstPlayer.send(answersDone);

//        player2.send("false");
        //player1 is done and player2 is allowed to proceed
        //player1.send(answersDone);
        return;
    }
}