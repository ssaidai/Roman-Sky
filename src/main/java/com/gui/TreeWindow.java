package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreeWindow extends JFrame implements ActionListener {

    private String dinasty;

    private Container container;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenu menuEdit= new JMenu("Edit");
    private JMenu menuHelp = new JMenu("Help");
    private JMenuItem saveItem = new JMenuItem("Save");
    private JMenuItem infoItem = new JMenuItem("Info");
    private JMenuItem drawItem = new JMenuItem("Draw");
    private JMenuItem exitItem = new JMenuItem("Exit");
    private JMenuItem changeNamesColorItem = new JMenuItem("Change colors");
    public TreeWindow(String din){
        super("Imperatori Romani");
        setSize(1400, 800);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);          // Si apre la finestra al centro dello schermo
        setVisible(true);
        dinasty = din;
        setup();
        setupListener();
    }
    private void setupListener(){
        saveItem.addActionListener(this);
        drawItem.addActionListener(this);
        changeNamesColorItem.addActionListener(this);
        infoItem.addActionListener(this);
        exitItem.addActionListener(this);
    }
    public void setup(){

        // Gestione menù
        setJMenuBar(menuBar);
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);
        menuFile.add(saveItem);
        menuFile.add(exitItem);
        menuEdit.add(drawItem);
        menuEdit.add(changeNamesColorItem);
        menuHelp.add(infoItem);
        //TODO: INSERIRE ICONE (24x24) PER MENU (sito da dove ho scaricato l'icona https://icons8.it/icons)
        saveItem.setIcon(new ImageIcon("src/resources/icons/menuIcons/iconaSalva.png"));

        //TODO: CAMBIARE FONT DEL MENU SE NON PIACE QUELLO DI DEFAULT


        // Gestione container
        container = this.getContentPane();
    }
    //TODO: KEYBOARD SHORTCUTS PER MENU ATTRAVERSO IL METODO setMnemonic

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitItem){
            System.exit(0);
        }
    }
}
