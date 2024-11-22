import java.util.ArrayList;

public class Question {
    private String category;
    private String question;
    private String correct_answer;
    private ArrayList<String> answers;

    public Question(String category, String question, String correct_answer, ArrayList<String> answers) {
        this.category = category;
        this.question = question;
        this.correct_answer = correct_answer;
        this.answers = answers;
    }

    public String getCategory() {
        return category;
    }


    public String getQuestion() {
        return question;
    }


    public String getCorrect_answer() {
        return correct_answer;
    }

    public String getCorrectAnswer() {
        return correct_answer;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }
}