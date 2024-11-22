import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataBaseConnection {
    private String url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7746033";
    private String user = "sql7746033";
    private String password = "zZPWl1Eb7p";
    private Connection con;
    private Statement stmt;

    public DataBaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
            System.out.println("Question inserted: " + question.getQuestion());

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
            answers.add(correctAnswer); // Add correct answer first
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

    public static void main(String[] args) {
        DataBaseConnection db = new DataBaseConnection();
        db.loadQuestions();
    }
}
