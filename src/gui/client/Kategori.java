package gui.client;

import clent_server.DemoClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kategori {
    private DemoClient client;
    public Kategori(DemoClient client) {

        this.client = client;

        JFrame frame = new JFrame("Välj en kategori");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);


        JPanel panel = new JPanel();
        panel.setBackground(new Color(173, 216, 230));
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));





        String[] categories = new String[3];

        try {

            categories = client.in.readLine().split(",");


            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(categories.toString());

        for(String x: categories) {
            System.out.println("Kategori: ");
            System.out.println(x);
        }

        for (int i = 0; i < categories.length; i++) {
            JButton button = new JButton(categories[i]);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setVerticalTextPosition(SwingConstants.BOTTOM); // Text under bilden
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            //button.setIcon(new ImageIcon(imagePaths[i] )); // Lägg till ikon (symbol)
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            panel.add(button);


            String category = categories[i];
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        frame.dispose();
                        new ClientGui(client, category);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            });
        }
        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}