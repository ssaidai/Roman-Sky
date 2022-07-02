package com.gui.TreeWindow;

import com.graph.DynastyTree;
import com.mxgraph.swing.mxGraphComponent;
import com.scraper.Scraper;
import org.jgrapht.ext.JGraphXAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    private JPanel pan = new JPanel();

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
        setSize(1300,700);
        setMaximumSize(new Dimension(1400, 800));
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);          // Si apre la finestra al centro dello schermo
        setVisible(true);
        DynastyTree tree = scraper.getDinastyTree(dIndex);
        jGraphXAdapter = tree.getGraphAdapter();
        this.dinasty = dinasty;
        pan.add(new mxGraphComponent(jGraphXAdapter));
        JScrollPane pan2 = new JScrollPane(pan);
        add(pan2);




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
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));      //FIXME: Se viene chiusa la finestra di save, viene riaperta per una seconda volta
        infoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));      // Non è necessarrio mettere sia setAccelerator che setMnemonic
        drawItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        changeNamesColorItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitItem){
            setVisible(false);
        }
        if(e.getSource() == saveItem){
            try {
                Point p1 = getLocationOnScreen();
                Point p = new Point((int)p1.getX() + 10, (int)p1.getY() + 25);

                Dimension dim = new Dimension(getWidth() - 20, getHeight() - 40);
                Rectangle rect = new Rectangle(p, dim);

                Robot robot = new Robot();
                BufferedImage background = robot.createScreenCapture(rect);
                new SaveWindow(background);

            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        if(e.getSource() == infoItem){
            new InfoWindow();
        }
    }

}
