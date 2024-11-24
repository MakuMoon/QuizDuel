package src.resultat_gui;

import gui.client.Kategori;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResultatGUI extends JFrame {

    public ResultatGUI() throws IOException {
        JPanel MainPanel = new JPanel(new BorderLayout());
        add(MainPanel);
        // Panel NORTH
        MainPanel.add(createNorthPanel(),BorderLayout.NORTH);
        // Panel WEST
        MainPanel.add(createSidePanel(6, 3), BorderLayout.WEST);
        // Panel CENTER
        MainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        // Panel EAST
        MainPanel.add(createSidePanel(6, 3), BorderLayout.EAST);
        // Panel SOUTH
        MainPanel.add(createSouthPanel(), BorderLayout.SOUTH);

        this.setTitle("QuizDuel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private JPanel createNorthPanel() throws IOException{
        JPanel panelNorth = new JPanel(new BorderLayout());

        panelNorth.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        JLabel setText = new JLabel("Din Tur",JLabel.CENTER);
        panelNorth.add(setText,BorderLayout.NORTH);

        // Panel de puntos y jugadores
        JPanel panelPlayers = new JPanel(new BorderLayout());

        panelPlayers.add(createRoundedImageLabel("src/resultat_gui/RandomImage/imagen1.png"), BorderLayout.WEST);
        panelPlayers.add(createRoundedImageLabel("src/resultat_gui/RandomImage/imagen2.png"), BorderLayout.EAST);


        int pointsPlayer1 = 0;
        int pointsPlayer2 = 0;

        JLabel labelPoints = new JLabel(pointsPlayer1 + " - " + pointsPlayer2, JLabel.CENTER);
        labelPoints.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        labelPoints.setForeground(Color.BLUE);
        panelPlayers.add(labelPoints, BorderLayout.CENTER);

        panelNorth.add(panelPlayers, BorderLayout.CENTER);
        return panelNorth;
    }
    private JPanel createCenterPanel(){
        JPanel panelCenter = new JPanel(new GridLayout(6,1));

        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        src.resultat_gui.CirclePanelMiddle[] circlesInMiddle = new src.resultat_gui.CirclePanelMiddle[6];
        for (int i = 0; i < circlesInMiddle.length; i++) {
            circlesInMiddle[i] = new src.resultat_gui.CirclePanelMiddle();
            panelCenter.add(circlesInMiddle[i]);
            circlesInMiddle[i].setNumber(i);

            if(circlesInMiddle[i].getNumber() == 0){
                circlesInMiddle[0].setColor(Color.GREEN);
            }
        }


        return panelCenter;
    }
    private JPanel createSidePanel(int rows, int cols) {
        JPanel sidePanel = new JPanel(new GridLayout(rows, cols));
        sidePanel.setPreferredSize(new Dimension(120, 0));

        for (int i = 0; i < rows * cols; i++) {
            sidePanel.add(new src.resultat_gui.CirclePanel());
        }

        return sidePanel;
    }
    private JPanel createSouthPanel(){
        JPanel panelSouth = new JPanel(new BorderLayout());
        panelSouth.setBorder(BorderFactory.createEmptyBorder(10,120,10,120));

        JButton playButton = new JButton("Spela");
        panelSouth.add(playButton,BorderLayout.SOUTH);
        playButton.addActionListener(l->{if(l.getSource() == playButton){
            // Spelet börjar här.
            JOptionPane.showMessageDialog(null,"Spela");
            //När man trycker på denna knapp poppar upp kategori fönstret.
            Kategori kategori = new Kategori();

        }
        });
        return panelSouth;
    }
    private BufferedImage getCircleImage1(BufferedImage originalImage){
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
    private JLabel createRoundedImageLabel(String imagePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        ImageIcon icon = new ImageIcon(getCircleImage1(image));
        return new JLabel(icon);
    }

    public static void main(String[] args) throws IOException {
        new ResultatGUI();
    }
}
