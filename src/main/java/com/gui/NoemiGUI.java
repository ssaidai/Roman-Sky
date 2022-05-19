package com.gui;

import javax.swing.*;
import java.awt.*;

public class NoemiGUI extends JFrame{
    // dichiarazione componenti in maniera privata
    private Container cont;
    private JPanel pCentro;
    private JPanel pNord;
    private JLabel labeLogo;
    private JLabel label;
    private JComboBox menù;
    private String dynasties[] = {"DINASTIA GIULIO CLAUDIA","DINASTIA 2","DINASTIA 3","DINASTIA 4","DINASTIA 5","DINASTIA 6" };


    public static void main(String[] args) {
        NoemiGUI gui = new NoemiGUI();
    }


    public NoemiGUI(){
        super("Imperatori Romani");


        label = new JLabel("Scegli la dinastia romana:");
        labeLogo = new JLabel("LOGO");    // inserimento LOGO
        menù = new JComboBox(dynasties);

        labeLogo.setBackground(Color.ORANGE);
        labeLogo.setOpaque(true);

        pCentro = new JPanel();     // I panel verranno utilizzati come componenti più esterni
        pNord = new JPanel();       // ed ospiteranno gli altri elementi grafici.

        pCentro.add(label);
        pCentro.add(menù);
        pNord.add(labeLogo);

        cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(pCentro, BorderLayout.CENTER);
        cont.add(pNord, BorderLayout.NORTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
    }

}
