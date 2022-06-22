package com.gui.Window.TreeWindow;

import com.graph.DynastyTree;
import com.mxgraph.swing.mxGraphComponent;
import com.scraper.Scraper;
import org.jgrapht.ext.JGraphXAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;



/**
 *  tree GUI
 */
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



    private JGraphXAdapter jGraphXAdapter;

    /**
     * Class constructor
     * @param dinasty
     * @param dIndex
     * @param scraper
     */
    public TreeWindow(String dinasty, int dIndex, Scraper scraper){
        super(dinasty);
        setLayout(new BorderLayout());
        setSize(1400, 800);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);          // Si apre la finestra al centro dello schermo
        setVisible(true);
        DynastyTree tree = scraper.getDinastyTree(dIndex);
        jGraphXAdapter = tree.getGraphAdapter();
        this.dinasty = dinasty;
        this.add(new mxGraphComponent(jGraphXAdapter));


        setup();
        setupListener();
        mnemonics();
    }

    /**
     * setup the addActionListener method.
     */
    private void setupListener(){
        saveItem.addActionListener(this);
        drawItem.addActionListener(this);
        changeNamesColorItem.addActionListener(this);
        infoItem.addActionListener(this);
        exitItem.addActionListener(this);
    }

    /**
     * GUI setup.
     *
     */
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



    }
    //TODO: KEYBOARD SHORTCUTS PER MENU ATTRAVERSO IL METODO setMnemonic

    /**
     * mnemonic method
     */
    public void mnemonics(){
        saveItem.setMnemonic(KeyEvent.VK_S);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        infoItem.setMnemonic(KeyEvent.VK_I);
        infoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        exitItem.setMnemonic(KeyEvent.VK_E);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        drawItem.setMnemonic(KeyEvent.VK_D);
        drawItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitItem){
            setVisible(false);
        }
        if(e.getSource() == saveItem){
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            paint(graphics2D);
            new SaveWindow(image);

        }
        if(e.getSource() == infoItem){
            new InfoWindow();
        }
    }
}
