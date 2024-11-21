package src.resultat_gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static src.resultat_gui.RedondearImagen.getRoundedImage;

public class CopiaDos extends JFrame {
    // Carga la imagen original
    BufferedImage originalImage1 = ImageIO.read(new File("src/resultat_gui/RandomImage/imagen1.png"));
    BufferedImage originalImage2 = ImageIO.read(new File("src/resultat_gui/RandomImage/imagen2.png"));
    // Crea un version redonda de la imagen
    ImageIcon roundIcon1 = new ImageIcon(getRoundedImage(originalImage1));
    ImageIcon roundIcon2 = new ImageIcon(getRoundedImage(originalImage2));
    // Muestra la imagen en un JLabel
    JLabel roundLabel1 = new JLabel(roundIcon1);
    JLabel roundLabel2 = new JLabel(roundIcon2);

    int pointsPlayer1;
    int pointsPlayer2;
    JLabel labelPuntos = new JLabel(pointsPlayer1+" - "+pointsPlayer2,JLabel.CENTER);


    JLabel setText = new JLabel();

    JPanel panel = new JPanel();
    JPanel panelWest = new JPanel();
    JPanel panelCenter = new JPanel();
    JPanel panelEast = new JPanel();

    JPanel panelNorth = new JPanel();

    JPanel panelNorth1 = new JPanel();
    JPanel panelNorth2 = new JPanel();
    JPanel panelNorthLeft = new JPanel();
    JPanel panelNorthRight = new JPanel();

    JPanel panelSouth = new JPanel();
    JPanel panelSouth1 = new JPanel();
    JButton SpelaButton = new JButton("Spela");

    public CopiaDos() throws IOException {
        add(panel);
        panel.setLayout(new BorderLayout());

        // Panel norte
        panelNorth.setLayout(new GridLayout(2,1));
        panel.add(panelNorth,BorderLayout.NORTH);
        panelNorth.add(panelNorth1);
        setText.setText("Din Tur");
        panelNorth1.add(setText);

        panelNorth2.setLayout(new BorderLayout());
        panelNorth2.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        panelNorth.add(panelNorth2);

        panelNorthLeft.add(roundLabel1,BorderLayout.CENTER);
        panelNorth2.add(panelNorthLeft,BorderLayout.WEST);

        panelNorthRight.add(roundLabel2,BorderLayout.CENTER);
        panelNorth2.add(panelNorthRight,BorderLayout.EAST);

        labelPuntos.setFont(new Font(Font.SANS_SERIF,Font.BOLD,25));
        labelPuntos.setForeground(Color.BLUE);
        panelNorth2.add(labelPuntos,BorderLayout.CENTER);

        //Panel norte ends

        panelWest.setPreferredSize(new Dimension(120, 0));
        panelEast.setPreferredSize(new Dimension(120, 0));

        // Porque no pasa nada aqui?
        //panelCenter.setPreferredSize(new Dimension(120, 0));

        panel.add(panelWest, BorderLayout.WEST);
        panel.add(panelCenter, BorderLayout.CENTER);
        panel.add(panelEast, BorderLayout.EAST);

        panelCenter.setLayout(new GridLayout(6, 1));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        CirclePanelMiddle[] circlesInMiddle = new CirclePanelMiddle[6];
        for (int i = 0; i < circlesInMiddle.length; i++) {
            circlesInMiddle[i] = new CirclePanelMiddle();
            panelCenter.add(circlesInMiddle[i]);
            circlesInMiddle[i].setNumber(i);

            if(circlesInMiddle[i].getNumber() == 1){
                circlesInMiddle[0].setColor(Color.GREEN);
            }
        }

        panelWest.setLayout(new GridLayout(6, 3));
        CirclePanel[] circlesOnWest = new CirclePanel[18];
        for (int i = 0; i < circlesOnWest.length; i++) {
            circlesOnWest[i] = new CirclePanel();
            panelWest.add(circlesOnWest[i]);
        }

        panelEast.setLayout(new GridLayout(6, 3));
        CirclePanel[] circlesOnEast = new CirclePanel[18];
        for (int i = 0; i < circlesOnEast.length; i++) {
            circlesOnEast[i] = new CirclePanel();
            panelEast.add(circlesOnEast[i]);
        }


        panel.add(panelSouth, BorderLayout.SOUTH);
        SpelaButton.setText("Spela");
        panelSouth.add(SpelaButton);
        SpelaButton.addActionListener(l->{if(l.getSource() == SpelaButton){
            // Spelet börjar här.
            JOptionPane.showMessageDialog(null,"Spela");
        }
        });

        this.setTitle("QuizDuel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static void main(String[] args) throws IOException {
        new CopiaDos();
    }
}