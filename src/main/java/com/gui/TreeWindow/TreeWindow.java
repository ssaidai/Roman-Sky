package com.gui.TreeWindow;

import com.graph.DynastyTree;
import com.mxgraph.swing.mxGraphComponent;
import com.scraper.Scraper;
import org.jgrapht.ext.JGraphXAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class TreeWindow extends JFrame implements ActionListener {

    private String dinasty;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenu menuEdit= new JMenu("Edit");
    private JMenu menuHelp = new JMenu("Help");
    private JMenuItem saveItem = new JMenuItem("Save");
    private JMenuItem infoItem = new JMenuItem("Info");
    private JMenuItem drawItem = new JMenuItem("Draw");
    private JMenuItem exitItem = new JMenuItem("Exit");
    private JMenuItem changeNamesColorItem = new JMenuItem("Change colors");

    public TreeWindow(String dinasty, int dIndex, Scraper scraper){
        super(dinasty);
        setLayout(new BorderLayout());
        setSize(1400, 800);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);          // Si apre la finestra al centro dello schermo
        setVisible(true);
        DynastyTree tree = scraper.getDinastyTree(dIndex);
        JGraphXAdapter jGraphXAdapter = tree.getGraphAdapter();
        this.dinasty = dinasty;
        this.add(new mxGraphComponent(jGraphXAdapter));


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
        menuFile.setFont(new Font("Bell MT", Font.PLAIN, 15));  //vedere se funziona --- FUNZIONA :D --- daje
        menuEdit.setFont(new Font("Bell MT", Font.PLAIN, 15));
        menuHelp.setFont(new Font("Bell MT", Font.PLAIN, 15));

    }
    //TODO: KEYBOARD SHORTCUTS PER MENU ATTRAVERSO IL METODO setMnemonic

    public void mnemonics(){
        //non sono ancora riuscito a impostarlo
            //forza roma allora
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitItem){
            System.exit(0);
        }
        if(e.getSource() == saveItem){
            try
            {
                BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D = image.createGraphics();
                paint(graphics2D);
                // TODO: BISOGNA MODIFICARE IL PATH.
                // TODO: CREARE FINESTRA CHE CHIEDA COME SALVARE IL NOME DEL FILE E GESTIRE LE ...
                ImageIO.write(image,"jpeg", new File("C:/Users/spide/Downloads/prova.jpeg"));

                // FIXME: LO SCREEN LO DEVI FARE AL CONTENUTO DEL PANEL, NON ALLA FINESTRA, SENNO VIENE TRONCATO
            }
            catch(Exception exception)
            {
                //code
            }
        }
    }
}
