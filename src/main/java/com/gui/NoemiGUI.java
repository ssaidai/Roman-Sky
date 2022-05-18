package com.gui;

import javax.swing.*;
import java.awt.*;

public class NoemiGUI extends JFrame{
    public static void main(String[] args) {
        NoemiGUI gui = new NoemiGUI();
    }
    public NoemiGUI(){
        super("Imperatori Romani");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        JLabel label = new JLabel("Scegli la dinastia romana:");
        JComboBox menù = new JComboBox();   //Va aggiunta la lista delle dinastie
        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(menù, BorderLayout.CENTER);
        cont.add(label, BorderLayout.NORTH);
        this.setVisible(true);
    }

}
