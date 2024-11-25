package resultat_gui;

import gui.client.Kategori;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ResultatGUI extends JFrame {

    private int pointsPlayer1;
    private int pointsPlayer2;
    private boolean yourTurn;
    ArrayList <Integer> trueTable = new ArrayList<>();
    ArrayList <Integer> opponentsTrueTable = new ArrayList<>();

    public ResultatGUI(int pointsPlayer1, int pointsPlayer2, boolean yourTurn, ArrayList<Integer> trueTable, ArrayList<Integer> opponentsTrueTable) {
        this.pointsPlayer1 = pointsPlayer1;
        this.pointsPlayer2 = pointsPlayer2;
        this.yourTurn = yourTurn;
        this.trueTable = trueTable;
        this.opponentsTrueTable = opponentsTrueTable;

        JPanel MainPanel = new JPanel(new BorderLayout());
        add(MainPanel);
        // Panel NORTH
        MainPanel.add(createNorthPanel(yourTurn), BorderLayout.NORTH);
        // Panel WEST
        MainPanel.add(createSidePanel(6, 3, trueTable), BorderLayout.WEST);
        // Panel CENTER
        MainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        // Panel EAST
        MainPanel.add(createSidePanel(6, 3, opponentsTrueTable), BorderLayout.EAST);
        // Panel SOUTH
        MainPanel.add(createSouthPanel(), BorderLayout.SOUTH);

        this.setTitle("QuizDuel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


    private JPanel createNorthPanel(Boolean yourTurn) {
        JPanel panelNorth = new JPanel(new BorderLayout());

        panelNorth.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        String turn = null;
        if (yourTurn) {
            turn = "Din Tur";
        } else {
            turn = "Motstandarens Tur";
        }


        JLabel setText = new JLabel(turn, JLabel.CENTER);
        panelNorth.add(setText, BorderLayout.NORTH);

        // Panel de puntos y jugadores
        JPanel panelPlayers = new JPanel(new BorderLayout());

        panelPlayers.add(createRoundedImageLabel("src/resultat_gui/RandomImage/imagen1.png"), BorderLayout.WEST);
        panelPlayers.add(createRoundedImageLabel("src/resultat_gui/RandomImage/imagen2.png"), BorderLayout.EAST);


        JLabel labelPoints = new JLabel(pointsPlayer1 + " - " + pointsPlayer2, JLabel.CENTER);
        labelPoints.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        labelPoints.setForeground(Color.BLUE);
        panelPlayers.add(labelPoints, BorderLayout.CENTER);

        panelNorth.add(panelPlayers, BorderLayout.CENTER);
        return panelNorth;
    }

    private JPanel createCenterPanel() {
        JPanel panelCenter = new JPanel(new GridLayout(6, 1));

        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        CirclePanelMiddle[] circlesInMiddle = new CirclePanelMiddle[6];
        for (int i = 0; i < circlesInMiddle.length; i++) {
            circlesInMiddle[i] = new CirclePanelMiddle();
            panelCenter.add(circlesInMiddle[i]);
            circlesInMiddle[i].setNumber(i);

            if (circlesInMiddle[i].getNumber() == 0) {
                circlesInMiddle[0].setColor(Color.GREEN);
            }
        }


        return panelCenter;
    }

    private JPanel createSidePanel(int rows, int cols, ArrayList<Integer> trueTable) {
        JPanel sidePanel = new JPanel(new GridLayout(rows, cols));
        sidePanel.setPreferredSize(new Dimension(120, 0));

        for (int i = 0; i < rows * cols; i++) {
            if (trueTable.get(i) == 1) {
                sidePanel.add(new CirclePanel("Correct"));
            } else if (trueTable.get(i) == 0) {
                sidePanel.add(new CirclePanel("Incorrect"));
            }
            else {
                sidePanel.add(new CirclePanel("Not Answered"));
            }
        }

        return sidePanel;
    }

    private JPanel createSouthPanel() {
        JPanel panelSouth = new JPanel(new BorderLayout());
        panelSouth.setBorder(BorderFactory.createEmptyBorder(10, 120, 10, 120));

        JButton playButton = new JButton("Spela");
        panelSouth.add(playButton, BorderLayout.SOUTH);
        playButton.addActionListener(l -> {
            if (l.getSource() == playButton) {
                // Spelet börjar här.


                this.dispose();

                new Kategori();


            }
        });
        return panelSouth;
    }

    private BufferedImage getCircleImage1(BufferedImage originalImage) {
        int diameter = Math.min(originalImage.getWidth(), originalImage.getHeight());
        BufferedImage roundedImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = roundedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Rita en cirkel.
        g2.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2.drawImage(originalImage, 0, 0, diameter, diameter, null);
        g2.dispose();
        return roundedImage;
    }

    // Läser en ImagePath och sedan omvandlar den till ImageIcon och skickar en JLabel icon.
    private JLabel createRoundedImageLabel(String imagePath) {
        JLabel label = null;
        try{
            BufferedImage image = ImageIO.read(new File(imagePath));
            ImageIcon icon = new ImageIcon(getCircleImage1(image));
            label = new JLabel(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return label;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> trueTable = new ArrayList<>();
        ArrayList<Integer> opponentsTrueTable = new ArrayList<>();


        trueTable.add(1);
        trueTable.add(0);
        trueTable.add(1);

        trueTable.add(-1);
        trueTable.add(-1);
        trueTable.add(-1);

        trueTable.add(-1);
        trueTable.add(-1);
        trueTable.add(-1);

        trueTable.add(-1);
        trueTable.add(-1);
        trueTable.add(-1);

        trueTable.add(-1);
        trueTable.add(-1);
        trueTable.add(-1);

        trueTable.add(-1);
        trueTable.add(-1);
        trueTable.add(-1);

        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);
        opponentsTrueTable.add(-1);

        new ResultatGUI(5, 1, true, trueTable, trueTable);
    }
}
