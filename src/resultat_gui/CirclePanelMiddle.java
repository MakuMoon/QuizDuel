package src.resultat_gui;

import javax.swing.*;
import java.awt.*;

public class CirclePanelMiddle extends JPanel {
    private Color color = Color.GRAY;
    private int number;

    public Component setNumber(int number){
        this.number = number;
        repaint();
        return null;
    }

    public int getNumber(){
        return number;
    }
    public void setColor(Color color){
        this.color = color;
        repaint();
    }

    public CirclePanelMiddle(){
        this.setPreferredSize(new Dimension(50,50));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int diametro = Math.min(getWidth(),getHeight()) -10;
        int x = (getWidth() - diametro) / 2;
        int y = (getHeight() - diametro) / 2;
        g2d.setColor(color);
        g2d.fillOval(x,y,diametro,diametro);

        if(number >= 0){
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial",Font.BOLD,14));
            String numStr = String.valueOf(number+1);
            FontMetrics fm = g2d.getFontMetrics();
            int numX = x + (diametro - fm.stringWidth(numStr)) / 2;
            int numY = y + (diametro + fm.getAscent()) / 2 - 4;
            g2d.drawString(numStr, numX,numY);
        }
    }
}
