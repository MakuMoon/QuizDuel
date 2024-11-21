package src.resultat_gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RedondearImagen {
    public static void main(String[] args) throws IOException {
        // Cargar la imagen original
        BufferedImage originalImage = ImageIO.read(new File("GroupProject/src/Server/RandomImage/imagen1.png"));

        // Crear una versión redonda de la imagen
        ImageIcon roundIcon = new ImageIcon(getRoundedImage(originalImage));

        // Mostrar la imagen en un JLabel
        JLabel roundLabel = new JLabel(roundIcon);

        // Crear el JFrame para mostrar el resultado
        JFrame frame = new JFrame("Imagen Redonda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
        frame.add(roundLabel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static BufferedImage getRoundedImage(BufferedImage originalImage) {
        int diameter = Math.min(originalImage.getWidth(), originalImage.getHeight());
        BufferedImage roundedImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = roundedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar el círculo
        g2.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
        g2.drawImage(originalImage, 0, 0, diameter, diameter, null);
        g2.dispose();

        return roundedImage;
    }
}
