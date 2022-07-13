package com.gui.Window.TreeWindow;

import com.graph.DynastyTree;
import com.mxgraph.swing.mxGraphComponent;
import com.scraper.Scraper;
import org.jgrapht.ext.JGraphXAdapter;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;



/**
 *  This class has as its main objective the representation of the genealogical tree of the chosen dynasty.
 *
 *  <p>
 *      There are secondaty implementations also such as the possiblity to save the screen and to do drawings over it and to change colors to the various relationships.
 *      It is also possible to open an INFO window where there will be all the information about the projects.
 *  </p>
 */
public class TreeWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenu menuEdit= new JMenu("Edit");
    private JMenu menuHelp = new JMenu("Help");
    private JMenuItem saveItem = new JMenuItem("Save");
    private JMenuItem infoItem = new JMenuItem("Info");
    private JMenuItem drawItem = new JMenuItem("Draw");
    private JMenuItem exitItem = new JMenuItem("Exit");
    private BackgroundMenuItem colFItem = new BackgroundMenuItem("COLORE RELAZIONE CON FIGLIO");
    private BackgroundMenuItem colMItem = new BackgroundMenuItem("COLORE RELAZIONE CON MOGLIE");
    private JMenu changeRelColor = new JMenu("Change colors"){
        private KeyStroke accelerator;

        @Override
        public KeyStroke getAccelerator() {
            return accelerator;
        }

        @Override
        public void setAccelerator(KeyStroke keyStroke) {
            KeyStroke oldAccelerator = accelerator;
            this.accelerator = keyStroke;
            repaint();
            revalidate();
            firePropertyChange("accelerator", oldAccelerator, accelerator);
        }
    };

    private JGraphXAdapter jGraphXAdapter;
    private mxGraphComponent graphComponent;
    final JColorChooser picker = new JColorChooser();

    private JScrollPane pane;

    private int deltaX, deltaY;
    private double initialZoom = 0;
    private DynastyTree tree;
    private Color colMoglie = new Color(182, 24, 24);
    private Color colFiglio = new Color(107, 81, 42);

    /**
     * Constructor of the TreeWindow class.
     * @param dinasty
     * @param dIndex
     * @param scraper
     */
    public TreeWindow(String dinasty, int dIndex, Scraper scraper){
        super(dinasty);
        setLayout(new BorderLayout());
        setSize(1300,750);
        setMaximumSize(new Dimension(1300, 750));
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);          // Si apre la finestra al centro dello schermo
        setVisible(true);
        tree = scraper.getDynastyTree(dIndex);
        jGraphXAdapter = tree.getGraphAdapter();
        tree.setCellsStyle("married", colMoglie);
        tree.setCellsStyle("kin", colFiglio);
        tree.setCellsStyle("adopted", colFiglio);
        graphComponent = new mxGraphComponent(jGraphXAdapter);
        graphComponent.setAutoscrolls(true);
        pane = new JScrollPane(graphComponent);
        pane.setBorder(null);
        add(pane);
        setup();
        setupListener();
        mnemonics();
    }

    /**
     * In this method we add Listeners to various components.
     */
    private void setupListener(){
        saveItem.addActionListener(this);
        drawItem.addActionListener(this);
        changeRelColor.addActionListener(this);
        infoItem.addActionListener(this);
        exitItem.addActionListener(this);
        colFItem.addActionListener(this);
        colMItem.addActionListener(this);


        /**
         * The graphComponent is draggable thanks to the MouseAdapter whenever mouse is pressed
         * and to the MouseMotionListener if mouse is dragged.
                *
         * The two method calculate the difference of position between the mouse and graph position and then
         * set a new location for the graph if mouse is dragged.
         */

        graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                 deltaX = e.getXOnScreen() - graphComponent.getX();
                 deltaY = e.getYOnScreen() - graphComponent.getY();
            }
        });
        graphComponent.getGraphControl().addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                graphComponent.setLocation(e.getXOnScreen() - deltaX,
                        e.getYOnScreen() - deltaY);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

         /**
         * Using MouseWheelListener we're able to zoom in and out.
                * Whenever control is pressed on the keyboard, if the mouse wheel is scrolled forward we'll get a zoom in
                * or else it's scrolled down we'll get a zoom out.
                *
         * initialZoom is a counter which is increased by one when zoomIn() is called and decreased by one if zoomOut() is called
         * Only if initialZoom is major than 0 a zoomOut() can occur, that's why while zooming out the graph will never be smaller than the initial dimension
          */

        graphComponent.getGraphControl().addMouseWheelListener(e -> {
            if (e.isControlDown()){
                if (e.getPreciseWheelRotation() < 0){
                    initialZoom ++;
                    graphComponent.zoomIn();
                }
                else{
                    if (initialZoom > 0) {
                        initialZoom --;
                        graphComponent.zoomOut();
                        if (initialZoom <= 0){
                            graphComponent.zoomActual();
                        }
                    }
                    else {
                        initialZoom = 0;
                        graphComponent.zoomActual();
                        graphComponent.getParent().dispatchEvent(e);
                    }
                }
            }
            else{
                graphComponent.getParent().dispatchEvent(e);
            }
        });

    }

    /**
     * GUI Setup.
     *
     * <p>
     *     MENU MANAGER: This section is about the setup of the implementation of the menu bar.
     *     In particular menuBar is a JMenuBar object type. menuFile, menuEdit, changeRelColor and menuHelp are JMenu object type. saveItem, exitItem and infoItem
     *     are JMenuItem object types. colMItem, colFItem e colFAItem are BackgroundMenuItem object types.
     * </p>
     *
     * <p>
     *     PICKER MODIFIER: This section concerns the changes of the panel which allow the user to change the colors.
     * </p>
     *
     * <p>
     *     MENU ICON SET: Section dedicated to add icons in the menu.
     * </p>
     *
     */
    public void setup(){

        // GESTIONE MENU
        setJMenuBar(menuBar);
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);
        menuFile.add(saveItem);
        menuFile.add(exitItem);
        menuEdit.add(changeRelColor);
        menuHelp.add(infoItem);
        changeRelColor.add(colFItem);
        changeRelColor.add(colMItem);
        colMItem.setColor(colMoglie);
        colFItem.setColor(colFiglio);



        //MODIFICA DI PICKER
        AbstractColorChooserPanel[] ab = picker.getChooserPanels();
        AbstractColorChooserPanel[] ab2 = ab.clone();
        picker.removeChooserPanel(ab[0]);
        picker.removeChooserPanel(ab[1]);
        picker.removeChooserPanel(ab[2]);
        picker.addChooserPanel(ab2[2]);
        picker.addChooserPanel(ab2[1]);
        picker.addChooserPanel(ab2[0]);


        //SET DI ICONE NEL MENU
        saveItem.setIcon(new ImageIcon("res/icons/menuIcons/iconaSalva.png"));
        infoItem.setIcon(new ImageIcon("res/icons/menuIcons/iconaInfo.png"));
        exitItem.setIcon(new ImageIcon("res/icons/menuIcons/iconaExit.png"));
        changeRelColor.setIcon(new ImageIcon("res/icons/menuIcons/iconaColors.png"));




    }
    /**
     * Mnemonics set. It offers the possibility to surf to the menu bar thanks to shortcuts.
     *
     * <p>
     *     saveItem: Ctrl + S
     *     infoItem: Ctrl + I
     *     exitItem: Ctrl + E
     *     changeRelColor: Ctrl + C
     * </p>
     */
    public void mnemonics(){
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        infoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        changeRelColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
    }
    /**
     * Override of the method actionPerformed.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == colFItem){
            JColorChooser.createDialog(colFItem,"Change kin relation color",true,picker, this, null ).setVisible(true);
            tree.setCellsStyle("kin" , picker.getColor());
            tree.setCellsStyle("adopted" , picker.getColor());
            colFItem.setColor(picker.getColor());

        }


        if(e.getSource() == colMItem){
            JColorChooser.createDialog(colFItem,"Change married relation color",true,picker, this, null ).setVisible(true);
            tree.setCellsStyle("married" , picker.getColor());
            colMItem.setColor(picker.getColor());
        }


        if(e.getSource() == exitItem){
            setVisible(false);
        }
        if(e.getSource() == saveItem){
            try {
                BufferedImage image = new BufferedImage(pane.getWidth(), pane.getHeight(), BufferedImage.TYPE_INT_RGB);
                pane.paint(image.getGraphics());
                new SaveWindow(image);

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
