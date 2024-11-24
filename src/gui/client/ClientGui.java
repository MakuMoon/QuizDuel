package gui.client;

import model.Question;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class ClientGui extends JFrame implements ActionListener {

    private JPanel gamePanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel upperPanel = new JPanel();
    private JPanel lowerPanel = new JPanel();
    private JLabel quizQuestionLabel = new JLabel("", JLabel.CENTER);
    private JButton continueButton = new JButton("Fortsätt");
    private int currentQuestionIndex = 0;
    private int maxGamePlays = 3;

    ArrayList<Question> quizQuestionList = new ArrayList<>();
    ArrayList<QuizButton> buttonList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ClientGui clientGui = new ClientGui();
    }

    //Temporär metod för att lägga in frågor
    public void setUpQuizQuestions() {

        String correctAnswer1 = "Orange";
        String correctAnswer2 = "Avocado";
        String correctAnswer3 = "Jordgubb";

        ArrayList<String> answers1 = new ArrayList<>();
        answers1.add(correctAnswer1);
        answers1.add("Blå");
        answers1.add("Röd");
        answers1.add("Lila");

        ArrayList<String> answers2 = new ArrayList<>();
        answers2.add(correctAnswer2);
        answers2.add("Broccoli");
        answers2.add("Morot");
        answers2.add("Vitkål");

        ArrayList<String> answers3 = new ArrayList<>();
        answers3.add(correctAnswer3);
        answers3.add("Blåbär");
        answers3.add("Päron");
        answers3.add("Kiwi");

        quizQuestionList.add(new Question("Frukt & Grönt", "Vad har en apelsin för färg?", correctAnswer1, answers1));
        quizQuestionList.add(new Question("Frukt & Grönt", "Vad är inte en grönsak?", correctAnswer2, answers2));
        quizQuestionList.add(new Question("Frukt & Grönt", "Vilken frukt är röd?", correctAnswer3, answers3));
    }

    public ClientGui() throws Exception {
        setUpQuizQuestions();
        setupPanels();
        configureMainPanel();
        setUpMainPanel();
    }

    private void setupPanels() {
        configureUpperPanel();
        addButtonsToGamePanel();
        configureGamePanel();
        configureLowerPanel();
    }

    private void configureUpperPanel() {
        upperPanel.setForeground(Color.BLUE);
        upperPanel.setLayout(new BorderLayout());
        upperPanel.setBackground(Color.WHITE);
        upperPanel.setPreferredSize(new Dimension(110, 140));
        upperPanel.add(quizQuestionLabel, BorderLayout.CENTER);
    }

    public void addButtonsToGamePanel() {
        gamePanel.removeAll();
        buttonList.clear();

        Question currentQuestion = quizQuestionList.get(currentQuestionIndex);

        for (String quizAnswer : currentQuestion.getAnswers()) {
            QuizButton quizButton = new QuizButton(quizAnswer);
            quizButton.getjButton().setForeground(Color.WHITE);
            quizButton.getjButton().setBackground(Color.BLUE);
            quizButton.getjButton().setPreferredSize(new Dimension(110, 60));
            quizButton.getjButton().addActionListener(this);
            quizButton.getjButton().setEnabled(true);
            buttonList.add(quizButton);
            gamePanel.add(quizButton.getjButton());
        }

        quizQuestionLabel.setText(currentQuestion.getQuestion());
        continueButton.setVisible(false);
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    private void configureGamePanel() {
        gamePanel.setLayout(new GridLayout(2, 2, 3, 3));
        gamePanel.setOpaque(false);
        gamePanel.setBorder(new EmptyBorder(30, 0, 0, 0));
    }

    private void configureLowerPanel() {
        lowerPanel.removeAll();

        lowerPanel.setLayout(new BorderLayout());
        lowerPanel.setOpaque(false);
        lowerPanel.setPreferredSize(new Dimension(110, 75));
        lowerPanel.setBorder(new EmptyBorder(20, 30, 25, 30));
        lowerPanel.add(continueButton, BorderLayout.CENTER);
        continueButton.addActionListener(this);
        continueButton.setVisible(false);

        lowerPanel.revalidate();
        lowerPanel.repaint();
    }

    private void configureMainPanel() {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLUE);
        mainPanel.add(upperPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(lowerPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(new EmptyBorder(60, 10, 0, 10));
    }

    private void setUpMainPanel() {
        this.add(mainPanel);
        setTitle("Quiz Kampen");
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean returnAnswerTrueOrFalse(String clickedButtonText) {
        Question currentQuestion = quizQuestionList.get(currentQuestionIndex);
        return currentQuestion.getCorrectAnswer().equals(clickedButtonText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String clickedButtonText = clickedButton.getText();

        if (clickedButton == continueButton) {
            currentQuestionIndex++;

            if (currentQuestionIndex >= maxGamePlays) {
                //Ändra så att spelet avslutas
                return;
            } else {
                addButtonsToGamePanel();
                return;
            }
        }

        if (returnAnswerTrueOrFalse(clickedButtonText)) {
            clickedButton.setBackground(Color.GREEN);
            addContinueButton(clickedButtonText);
        } else {
            clickedButton.setBackground(Color.RED);
            addContinueButton(clickedButtonText);
        }
    }

    private void addContinueButton(String clickedButtonText) {
        for (QuizButton quizButton : buttonList) {
            quizButton.getjButton().setEnabled(false);
        }

        if (returnAnswerTrueOrFalse(clickedButtonText)) {
            continueButton.setBackground(Color.GREEN);
        } else {
            continueButton.setBackground(Color.RED);
        }

        continueButton.setVisible(true);
    }
}

