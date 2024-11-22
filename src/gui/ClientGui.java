package src.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class ClientGui extends JFrame implements ActionListener {

    private JPanel gamePanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel upperPanel = new JPanel();
    private JPanel lowerPanel = new JPanel();
    private JLabel quizQuestion = new JLabel("Vilken färg är en apelsin", SwingConstants.CENTER);
    private JButton continueButton = new JButton("Fortsätt");

    JButton button1 = new JButton("Orange");
    JButton button2 = new JButton("Blå");
    JButton button3 = new JButton("Röd");
    JButton button4 = new JButton("Lila");
    List<JButton> buttonList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ClientGui clientGui = new ClientGui();
    }

    public ClientGui() throws Exception {
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
        quizQuestion.setForeground(Color.BLUE);
        upperPanel.setLayout(new BorderLayout());
        upperPanel.setBackground(Color.WHITE);
        upperPanel.setPreferredSize(new Dimension(110, 140));
        upperPanel.add(quizQuestion, BorderLayout.CENTER);
    }

    public void addButtonsToGamePanel() {
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);

        for (JButton button : buttonList) {
            button.setForeground(Color.WHITE);
            button.setBackground(Color.BLUE);
            button.setPreferredSize(new Dimension(110, 60));
            button.addActionListener(this);
            gamePanel.add(button);
        }
    }

    private void configureGamePanel() {
        gamePanel.setLayout(new GridLayout(2, 2, 3, 3));
        gamePanel.setOpaque(false);
        gamePanel.setBorder(new EmptyBorder(30, 0, 0, 0));
    }

    private void configureLowerPanel() {
        lowerPanel.setLayout(new BorderLayout());
        lowerPanel.setOpaque(false);
        lowerPanel.setPreferredSize(new Dimension(110, 75));
        lowerPanel.setBorder(new EmptyBorder(20, 30, 25, 30));
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

    public boolean returnAnswerTrueOrFalse(JButton clickedButton) {
        if (clickedButton == button1) {
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton == continueButton) {
            return;
        }

        if (returnAnswerTrueOrFalse(clickedButton)) {
            clickedButton.setBackground(Color.GREEN);
            addContinueButton(clickedButton);
        } else {
            clickedButton.setBackground(Color.RED);
            addContinueButton(clickedButton);
        }
    }

    private void addContinueButton(JButton clickedButton) {
        lowerPanel.removeAll();

        for (JButton button : buttonList) {
            button.setEnabled(false);
        }

        if (returnAnswerTrueOrFalse(clickedButton)) {
            continueButton.setBackground(Color.GREEN);
        } else {
            continueButton.setBackground(Color.RED);
        }

        lowerPanel.add(continueButton, BorderLayout.CENTER);

        lowerPanel.revalidate();
        lowerPanel.repaint();
    }
}
