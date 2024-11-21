import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kategori {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Välj en kategori");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);


        JPanel panel = new JPanel();
        panel.setBackground(new Color(173, 216, 230));
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] categories = {"Sport", "Geografi", "Musik"};
        String[] imagePaths = {
                "sport.png",      // Bild för Sport
                "geography.png",  // Bild för Geografi
                "music.png"       // Bild för Musik
        };


        for (int i = 0; i < categories.length; i++) {
            JButton button = new JButton(categories[i]);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setVerticalTextPosition(SwingConstants.BOTTOM); // Text under bilden
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setIcon(new ImageIcon(imagePaths[i] )); // Lägg till ikon (symbol)
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            panel.add(button);


            String category = categories[i];
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Du valde kategorin: " + category);
                }
            });
        }


        frame.add(panel);


        frame.setVisible(true);
    }
}