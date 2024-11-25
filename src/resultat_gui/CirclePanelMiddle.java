package src.resultat_gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CirclePanelMiddle extends JPanel {
    private Color color = Color.GRAY;
    private int number;

    public void setNumber(int number){
        this.number = number;
        repaint();
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

        int diametro = Math.min(getWidth(),getHeight()) -18;
        int x = (getWidth() - diametro) / 2;
        int y = (getHeight() - diametro) / 10;
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

            ArrayList<String> text = new ArrayList<>();

        }

        String textUnderCirkeln = "Categoria";
        g2d.setFont(new Font("Arial", Font.BOLD, 13));
        FontMetrics fmText = g2d.getFontMetrics();
        int textX = x + (diametro - fmText.stringWidth(textUnderCirkeln)) / 2;
        int textY = y + diametro + fmText.getHeight()-5;
        g2d.setColor(Color.BLACK);
        g2d.drawString(textUnderCirkeln, textX, textY);
    }
}
