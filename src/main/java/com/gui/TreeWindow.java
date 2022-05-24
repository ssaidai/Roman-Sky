package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class TreeWindow extends JFrame implements ActionListener {

    private String dinasty;

    private Container container;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenu menuEdit= new JMenu("Edit");
    private JMenu menuHelp = new JMenu("Help");
    private JMenuItem saveItem = new JMenuItem("Save", KeyEvent.VK_S);
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
        infoItem.setIcon(new ImageIcon("src/resources/icons/menuIcons/iconaInfo.png"));
        exitItem.setIcon(new ImageIcon("src/resources/icons/menuIcons/iconaExit.png"));
        drawItem.setIcon(new ImageIcon("src/resources/icons/menuIcons/iconaDraw.png"));
        changeNamesColorItem.setIcon(new ImageIcon("src/resources/icons/menuIcons/iconaColors.png"));

        //TODO: CAMBIARE FONT DEL MENU SE NON PIACE QUELLO DI DEFAULT
        menuFile.setFont(new Font("Georgia", Font.PLAIN, 10));  //vedere se funziona --- FUNZIONA :D

        // Gestione container
        container = this.getContentPane();
    }
    //TODO: KEYBOARD SHORTCUTS PER MENU ATTRAVERSO IL METODO setMnemonic

    public void mnemonics(){
        saveItem.setMnemonic(KeyEvent.VK_S);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK)); //a me non funziona ctrl + s
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitItem){
            System.exit(0);
        }
        if(e.getSource() == saveItem){
            System.out.println("--- HAI SALVATO IL FILE ---");
        }
    }
}
