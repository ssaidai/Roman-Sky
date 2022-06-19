package com.gui.TreeWindow;

import com.gui.MyFont;

import javax.swing.*;
import java.awt.*;

public class InfoWindow extends JFrame {
    JPanel panPrincipale = new JPanel();
    JPanel panLogo = new JPanel();
    JPanel panInfo = new JPanel();
    JLabel labeLogo = new JLabel();
    JLabel titolo = new JLabel();
    JTextArea testo = new JTextArea();

    public InfoWindow(){
        super("Info");
        setContentPane(super.getContentPane());
        setSize(450, 400);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setup();
    }

    public void setup(){
        //testo.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin Italic.ttf", 15f));
        testo.setEditable(false);
        String prova = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        testo.append(prova);

        labeLogo.setIcon(new ImageIcon("src/resources/icons/iconaFrame2.png"));
        titolo.setText("WEB SCRAPER 2021/2022");
        titolo.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin.ttf", 25f));
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        panInfo.setLayout(new BorderLayout());
        panInfo.add(titolo, BorderLayout.NORTH);
        panInfo.add(testo, BorderLayout.CENTER);
        panLogo.add(labeLogo);
        panPrincipale.setLayout(new BorderLayout());
        panPrincipale.add(panInfo, BorderLayout.CENTER);
        add(panPrincipale);
    }
}
