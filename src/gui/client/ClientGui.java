package gui.client;

import clent_server.DemoClient;
import model.Question;
import db.DataBaseConnection;
import resultat_gui.ResultatGUI;
import resultat_gui.SaveResult;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientGui extends JFrame implements ActionListener {

    private final JPanel gamePanel = new JPanel();
    private final JPanel mainPanel = new JPanel();
    private final JPanel upperPanel = new JPanel();
    private final JPanel lowerPanel = new JPanel();
    private final JLabel quizQuestionLabel = new JLabel("", JLabel.CENTER);
    private final JButton continueButton = new JButton("Fortsätt");
    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    ArrayList<Question> quizQuestionList = new ArrayList<>();
    ArrayList<QuizButton> buttonList = new ArrayList<>();
    ArrayList<Integer> roundResult;
    private int currentQuestionIndex = 0;
    private int maxGamePlays = 3;
    private DemoClient client;

    public ClientGui(DemoClient client, String category) throws Exception {
        this.client = client;


        roundResult = new ArrayList<>();
        for (int i = 0; i < maxGamePlays; i++) {

            {
                String question = client.in.readLine();
                String answers = client.in.readLine();
                String correctAnswer = client.in.readLine();

                String[] answersToArray = answers.substring(1, answers.length() - 1).split(", ");
                ArrayList<String> answersList = new ArrayList<>();
                for (String answer : answersToArray) {
                    answersList.add(answer);
                }

                //String category, String question, String correct_answer, ArrayList<String> answers) {
                Question questions = new Question(category, question, correctAnswer, answersList);

                quizQuestionList.add(questions);
            }
        }

        setupPanels();
        configureMainPanel();
        setUpMainPanel();
    }

    public static void main(String[] args) throws Exception {
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

                SaveResult saveResult = new SaveResult();

                saveResult.saveQuestionRound(roundResult, true);


                ArrayList<Integer> trueTable = saveResult.readResult(true);
                ArrayList<Integer> opponentsTrueTable = saveResult.readResult(false);


                client.out.println("true");

                this.dispose();
                new ResultatGUI(client, 0, 0, false, trueTable, opponentsTrueTable);

                return;
            } else {
                addButtonsToGamePanel();
                return;
            }
        }

        if (returnAnswerTrueOrFalse(clickedButtonText)) {
            clickedButton.setBackground(Color.GREEN);
            addContinueButton(clickedButtonText);
            roundResult.add(1);
        } else {
            clickedButton.setBackground(Color.RED);
            addContinueButton(clickedButtonText);
            roundResult.add(0);
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

