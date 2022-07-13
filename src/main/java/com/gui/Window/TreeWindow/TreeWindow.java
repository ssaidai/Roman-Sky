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
 *  Questa classe ha come obiettivo principale la rappresentazione dell'albero genealogico della dinastia scelta.
 *
 *  <p>
 *      Sono presenti anche implementazioni secondarie quali la possibilità di poter salvare la schermata e fare disegni su di essa e cambiare colori alle varie relazioni.
 *      E' inoltre possibile aprire una finestra INFO nella quale ci saranno le informazioni del progetto.
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
    private BackgroundMenuItem colFAItem = new BackgroundMenuItem("COLORE RELAZIONE CON FIGLIO ADOTTIVO");
    private BackgroundMenuItem colMItem = new BackgroundMenuItem("COLORE RELAZIONE CON MOGLIE");
    private JMenu changeRelColor = new JMenu("Change colors");

    private JGraphXAdapter jGraphXAdapter;
    private mxGraphComponent graphComponent;
    final JColorChooser picker = new JColorChooser();

    private JScrollPane pane;

    private int deltaX, deltaY;
    private double initialZoom = 0;
    private DynastyTree tree;
    private Color colMoglie = new Color(182, 24, 24);
    private Color colFiglio = new Color(107, 81, 42);
    private Color colFA = new Color(107, 81, 42);

    /**
     * Costruttore della classe TreeWindow.
     * @param dinasty
     * @param dIndex
     * @param scraper
     */
    public TreeWindow(String dinasty, int dIndex, Scraper scraper){
        super(dinasty);
        //this.dinasty = dinasty;
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
        tree.setCellsStyle("adopted", colFA);
        graphComponent = new mxGraphComponent(jGraphXAdapter);
        graphComponent.setAutoscrolls(true);
        pane = new JScrollPane(graphComponent);
        add(pane);
        setup();
        setupListener();
        mnemonics();
    }

    /**
     * In questo metodo vengono aggiunti i vari Listener ai vari componenti.
     */
    private void setupListener(){
        saveItem.addActionListener(this);
        drawItem.addActionListener(this);
        changeRelColor.addActionListener(this);
        infoItem.addActionListener(this);
        exitItem.addActionListener(this);
        colFAItem.addActionListener(this);
        colFItem.addActionListener(this);
        colMItem.addActionListener(this);


        //MOUSE - DRAG AND SCROLL
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

        //ZOOM IN/OUT
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
     * Setup della GUI.
     *
     * <p>
     *     GESTIONE MENU: questa sezione riguarda il setup riguardante l'implementazione della barra del menu (menu bar).
     *     In particolare menuBar è l'ogetto di tipo JMenuBar. menuFile, menuEdit, changeRelColor e menuHelp sono gli oggetti di tipo JMenu. saveItem, exitItem e infoItem
     *     sono gli oggetti di tipo JMenuItem. colMItem, colFItem e colFAItem sono oggetti di tipo BackgroundMenuItem.
     * </p>
     *
     * <p>
     *     MODIFICA PICKER: questa sezione riguarda la modifica del pannello che permette all'utente di selezionare un colore.
     * </p>
     *
     * <p>
     *     SET DI ICONE NEL MENU: sezione dedicata all'aggiunta di icone nella barra del menu.
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
        changeRelColor.add(colFAItem);
        changeRelColor.add(colMItem);
        colMItem.setColor(colMoglie);
        colFItem.setColor(colFiglio);
        colFAItem.setColor(colFA);


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
        saveItem.setIcon(new ImageIcon("src/resources/icons/menuIcons/iconaSalva.png"));
        infoItem.setIcon(new ImageIcon("src/resources/icons/menuIcons/iconaInfo.png"));
        exitItem.setIcon(new ImageIcon("src/resources/icons/menuIcons/iconaExit.png"));
        changeRelColor.setIcon(new ImageIcon("src/resources/icons/menuIcons/iconaColors.png"));




    }
    /**
     * Set delle/di mnemonics. Offre la possibilità di navigare nella menu bar tramite una combinazione di tasti.
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
     * Override del metodo actionPerformed.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == colFItem){
            JColorChooser.createDialog(colFItem,"Change kin relation color",true,picker, this, null ).setVisible(true);
            tree.setCellsStyle("kin" , picker.getColor());
            colFItem.setColor(picker.getColor());

        }

        if(e.getSource() == colFAItem){
            JColorChooser.createDialog(colFItem,"Change adopted relation color",true,picker, this, null ).setVisible(true);
            tree.setCellsStyle("adopted" , picker.getColor());
            colFAItem.setColor(picker.getColor());
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