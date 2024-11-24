package gui.client;

import javax.swing.*;

public class QuizButton {
    private JButton jButton;

    public QuizButton(String quizAnswer) {
        this.jButton = new JButton(quizAnswer);
    }

    public JButton getjButton() {
        return jButton;
    }
}

