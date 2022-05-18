package com.gui;

import javax.swing.*;
import java.awt.*;



public class Window {

    private JFrame frame;
    private JLabel logoPrincipale =new JLabel();
    private JPanel p2 =new JPanel();
    private JPanel p3 =new JPanel();
    private String[] dynasties = {"DINASTIAsdfasdfasdfsadf 1", "DINASTIAsdafasdf 2", "DINASTIA 3"};
    JComboBox<String> mTendina = new JComboBox<>(dynasties);
    JButton button = new JButton("CREA ALBERO");
    public Window(){
        frame = new JFrame("WEB SCRAPER - IMPERATORI ROMANI");
    }
    public void setup(){


        p3.setBackground(Color.DARK_GRAY);   // forse servirà a qualcosa, sennò è da togliere

        // TODO: MODIFICARE IL LOGO
        logoPrincipale.setIcon(new ImageIcon("src/resources/logos/LOGO_AS_ROMA.png"));  // logo da cambiare
        logoPrincipale.setHorizontalAlignment(JLabel.CENTER);

        // TODO: DA MIGLIORARE/MODIFICARE IL BUTTON
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans", Font.BOLD, 15));
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setBackground(Color.LIGHT_GRAY);


        p2.add(mTendina);
        p2.add(button);

        //TODO: MIGLIORARE/MODIFICARE LA POSIZIONE DEI VARI COMPONENTI
        frame.add(logoPrincipale, BorderLayout.NORTH);
        frame.add(p2, BorderLayout.CENTER);
        frame.add(p3, BorderLayout.SOUTH);
        frame.setSize(600, 600);
        frame.setResizable(false);  // forse TRUE, per adesso FALSE
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new Window().setup();
    }
}
