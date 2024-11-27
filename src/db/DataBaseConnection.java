package db;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Question;

public class DataBaseConnection {
    private String url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7746033";
    private String user = "sql7746033";
    private String password = "zZPWl1Eb7p";
    private Connection con;
    private Statement stmt;

    public DataBaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.");

            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established successfully.");

            stmt = con.createStatement();
            System.out.println("Statement created successfully.");

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Error: " + e.getMessage());
        }
    }

    public ArrayList<Question> getQuestions(String category, boolean test) {
        ArrayList<Question> questions = new ArrayList<>();

        Question question1 = null;
        Question question2 = null;
        Question question3 = null;

        if (category.equals("Sports")) {
            question1 = new Question("Sports", "What is the most popular sport in the world?", "Soccer", new ArrayList<>(Arrays.asList("Soccer", "Basketball", "Tennis", "Golf")));
            question2 = new Question("Sports", "What is the most popular sport in the USA?", "American Football", new ArrayList<>(Arrays.asList("Soccer", "Basketball", "Tennis", "American Football")));
            question3 = new Question("Sports", "What is the most popular sport in Sweden?", "Soccer", new ArrayList<>(Arrays.asList("Soccer", "Basketball", "Tennis", "Golf")));
        }
        else if (category.equals("History")){
            question1 = new Question("History", "When did World War II end?", "1945", new ArrayList<>(Arrays.asList("1945", "1939", "1940", "1941")));
            question2 = new Question("History", "When did World War I end?", "1918", new ArrayList<>(Arrays.asList("1918", "1914", "1915", "1916")));
            question3 = new Question("History", "When did the Cold War end?", "1991", new ArrayList<>(Arrays.asList("1991", "1989", "1990", "1992")));
        }
        else if (category.equals("Vehicles")){
            question1 = new Question("Vehicles", "What is the most popular car brand in the world?", "Toyota", new ArrayList<>(Arrays.asList("Toyota", "Ford", "Chevrolet", "Honda")));
            question2 = new Question("Vehicles", "What is the most popular car brand in the USA?", "Ford", new ArrayList<>(Arrays.asList("Toyota", "Ford", "Chevrolet", "Honda")));
            question3 = new Question("Vehicles", "What is the most popular car brand in Sweden?", "Volvo", new ArrayList<>(Arrays.asList("Toyota", "Ford", "Chevrolet", "Volvo")));
        }

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);


        return questions;

    }

    public ArrayList<Question> getQuestions(String category) {
        ArrayList<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE category = '" + category + "'";

        try {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String question = rs.getString("question");
                String correctAnswer = rs.getString("correct_answer");
                String answersString = rs.getString("answer");
                String[] answers = answersString.split(",");

                ArrayList<String> answersList = new ArrayList<>();
                for (String answer : answers) {
                    answersList.add(answer);
                }

                Question q = new Question(category, question, correctAnswer, answersList);
                questions.add(q);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        Collections.shuffle(questions);

        //only keep the first three questions
        if (questions.size() > 3) {
            questions = new ArrayList<>(questions.subList(0, 3));
        }

        return questions;
    }



    public void loadQuestions() {
        String url = "https://opentdb.com/api.php?amount=100&type=multiple";

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();

            ArrayList<Question> questions = parseJsonToQuestions(jsonResponse);

            for (Question q : questions) {
                insertQuestionIntoDatabase(q);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertQuestionIntoDatabase(Question question) {
        String sql = "INSERT INTO questions (category, question, correct_answer, answer) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, question.getCategory());
            pstmt.setString(2, question.getQuestion());
            pstmt.setString(3, question.getCorrect_answer());


            String answersString = String.join(",", question.getAnswers());
            pstmt.setString(4, answersString);


            pstmt.executeUpdate();
            System.out.println("model.Question inserted: " + question.getQuestion());

        } catch (SQLException e) {
            System.out.println("Error inserting question: " + e.getMessage());
        }
    }

    public ArrayList<Question> parseJsonToQuestions(String jsonResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        JsonNode resultsNode = rootNode.path("results");

        ArrayList<Question> questions = new ArrayList<>();
        for (JsonNode node : resultsNode) {
            String category = node.path("category").asText();
            String question = node.path("question").asText();
            String correctAnswer = node.path("correct_answer").asText();


            ArrayList<String> answers = new ArrayList<>();
            answers.add(correctAnswer);
            JsonNode incorrectAnswersNode = node.path("incorrect_answers");
            for (JsonNode answerNode : incorrectAnswersNode) {
                answers.add(answerNode.asText());
            }

            Collections.shuffle(answers);

            Question questionObject = new Question(category, question, correctAnswer, answers);
            questions.add(questionObject);
        }

        for (Question question : questions) {
            insertQuestionIntoDatabase(question);
        }
        return questions;
    }
    
}
