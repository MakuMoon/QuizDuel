package src.resultat_gui;

import javax.swing.*;
import java.awt.*;

public class CirclePanel extends JPanel {
    private Color color = Color.gray;

    // Tamaño del circulo
    public CirclePanel(){
        this.setPreferredSize(new Dimension(40,40));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int diametro = Math.min(getWidth(),getHeight()) -5;
        int x = (getWidth() - diametro) / 2;
        int y = (getHeight() - diametro) / 2;
        g2d.setColor(color);
        g2d.fillOval(x,y,diametro,diametro);
    }

    // Pensar como esto se utilizara en los panles WEST/EAST
    public void setState(String state){
        switch (state){
            case "Correct":
                color = Color.GREEN;
                //icon = new ImageIcon("Path de la imagen").getImage(); // Se necesitara implementar un foto de "visto bueno"
                break;
            case "Incorrect":
                color = Color.RED;
                //icon = new ImageIcon("Path").getImage();
                break;
            default:
                color = Color.GRAY;
                //icon = null;
                repaint();
        }
    }
}