package com.gui.TreeWindow;

import com.gui.MyFont;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;






/**
 * Questa classe consiste nell'implementazione della finestra che si apre dopo aver premuto il comando <b>Save</b> nella finestra <b>Treewindow</b>.
 * Contiene principalmente due funzionalità: draw e il file chooser.
 *
 * <p>
 *     La funzionalità draw permette all'utente di poter disegnare sopra la finestra che si vuole salvare. E' possibile scegliere vari colori e varie grandezze
 *     della matita. E' presente anche la gomma.
 *     Il draw è stato implementato attraverso le interfacce <b>MouseMotionListener</b> e <b>MouseListener</b> e anche attraverso alla classe <b>Graphics2D</b>.
 * </p>
 *
 * <p>
 *     Il file chooser è stato implementato attraverso la classe <b>JFileChooser</b>. Salva l'immagine desiderata in formato jpeg.
 * </p>
 *
 * @see MouseMotionListener
 * @see MouseListener
 * @see JFileChooser
 * @see Graphics2D
 *
 */
public class SaveWindow extends JFrame implements ActionListener, MouseMotionListener, MouseListener {


    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenu menuEdit= new JMenu("Edit");
    private JMenuItem saveItem = new JMenuItem("Save");
    private JMenuItem drawItem = new JMenuItem("Draw");
    private JFileChooser jF = new JFileChooser(new File("c://"));
    private BufferedImage image;
    private JPanel panel = new JPanel(null);
    private JButton save = new JButton("SAVE");
    private JButton cancel = new JButton("EXIT");
    private JPanel pane;
    private JLabel toolsText = new JLabel("STRUMENTI DISEGNO ");
    private JLabel colorText = new JLabel("SCEGLI COLORE ");
    private JLabel pixelText = new JLabel("SELEZIONA PIXEL ");
    private String[] c = {"NERO", "ROSSO", "GIALLO", "BLU"};

    private JComboBox<String> colMenu = new JComboBox<>(c);

    private String[] dPix = {"2 px", "5 px", "10 px"};
    private JComboBox<String> dimPixel = new JComboBox<>(dPix);
    private JLabel strText = new JLabel("SELEZIONA STRUMENTO ");

    private String paintColor = "BLACK";
    private boolean contr = true;
    private JPanel p2 = new JPanel(null);
    private int pixel = 2;
    private JButton pixBttn = new JButton("O");
    private JPanel pPrincipale = new JPanel(null);
    private JLabel errorStrFormat = new JLabel("Numero di pixel non valido...");
    private JMenuItem exitItem = new JMenuItem("Exit");
    private int currentX, currentY, oldX, oldY;
    private String[] ut = {"MATITA","GOMMA"};

    private JComboBox<String> utMenu = new JComboBox<>(ut);
    private String utContr = "MATITA";
    private Graphics2D g;
    private BufferedImage bf;




    /**
     * Costruttore della classe.
     * @param bf paramentro di tipo BufferedImage.
     * @see BufferedImage
     */
    public SaveWindow(BufferedImage bf){

        super("SAVE");
        this.bf = bf;
        setupListener();
        setResizable(false);
        setSize(Math.max(bf.getWidth(), 740), bf.getHeight()+100);
        setVisible(true);
        setup();




    }

    /**
     * Imposta e configura la finestra.
     */
    public void setup(){

        // pane contiene l'immagine che si vuole salvare
        pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bf, 0, 0, null);
            }
        };
        pane.setBounds(0, 45, bf.getWidth(), bf.getHeight());
        pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pane.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/resources/icons/cursorIconss/MATITA.png").getImage(), new Point(0,0),"custom cursor"));
        panel.add(pane);


        // GESTIONE COMPONENTI RIGUARDANTI GLI STRUMENTI DI DISEGNO
        colorText.setBounds(10, 2, 150, 20);
        colorText.setForeground(new Color(0x000000));
        colorText.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin.ttf", 12f));
        colMenu.setBounds(10, 20, 150, 20);
        pixelText.setBounds(180, 2, 150, 20);
        pixelText.setForeground(new Color(0x000000));
        pixelText.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin.ttf", 12f));
        dimPixel.setBounds(180, 20, 70, 20);
        strText.setBounds(290, 2, 150, 20);
        strText.setForeground(new Color(0x000000));
        strText.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin.ttf", 12f));
        utMenu.setBounds(290, 20, 150, 20);
        save.setBounds(getWidth() - 280, 10, 120, 30);
        save.setFocusable(false);
        cancel.setBounds(getWidth() - 150, 10, 120, 30);
        cancel.setFocusable(false);


        //GESTIONE PANEL
        panel.setBackground(new Color(0xFFFFFF));
        panel.add(errorStrFormat);
        panel.add(pixBttn);
        panel.add(colorText);
        panel.add(pixelText);
        panel.add(dimPixel);
        panel.add(toolsText);
        panel.add(colMenu);
        panel.add(strText);
        panel.add(cancel);
        panel.add(utMenu);
        panel.add(save);
        panel.setPreferredSize(new Dimension(bf.getWidth() + 200, bf.getHeight() + 100));


        add(panel);

    }


    /**
     * In questo metodo vengono aggiunti i vari Listener ai vari componenti.
     */
    public void setupListener(){
        addMouseListener(this);
        addMouseMotionListener(this);
        save.addActionListener(this);
        cancel.addActionListener(this);
        colMenu.addActionListener(this);
        utMenu.addActionListener(this);
        dimPixel.addActionListener(this);
    }


    /**
     *  Realizzazione del salvataggio attraverso <b>JFileChooser</b>.
     *  Inizialmente vengono prese le coordinate x e y del panello nel quale è presente l'area di disegno e la dimensione di quest'ultimo e viene creato un oggetto Rectangle
     *  con le informazioni prese. Per catturare l'immagine usufruiamo del metodo createScreenCapture() della classe <b>Robot</b>.
     *
     *  Per salvare l'immagine utilizziamo la classe JFileChooser che fornisce un semplice meccanismo all'utente per salvare l'immagine. Attraverso il
     *  metodo <b>showSaveDialog(Component parent)</b> si apre una finestra che ci consente di navigare nel nostro dispositivo e salvare l'immagine nella cartella
     *  che desideriamo.
     *  Viene utilizzato il metodo setCurrentDirectory(File dir) per impostare la cartella di partenza.
     *  Attraverso il metodo statico write della classe <b>ImageIO</b> salviamo/scriviamo effettivamente la nostra immagine nella cartella desiderata.
     *
     * @see Point
     * @see Dimension
     * @see Rectangle
     * @see Robot
     * @see JFileChooser
     * @see ImageIO
     */
    public void saveImg(){

        //salva immagine
        try {

            Point p = pane.getLocationOnScreen();

            Dimension dim = new Dimension(pane.getWidth() - 15, pane.getHeight());

            Rectangle rect = new Rectangle(p, dim);

            Robot robot = new Robot();
            this.image = robot.createScreenCapture(rect);

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        //-------------------

        jF.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator")+ "Downloads"));


        int res = jF.showSaveDialog(this);
        if(res == JFileChooser.APPROVE_OPTION){
            File file = jF.getSelectedFile();

            try
            {
                ImageIO.write(image,"jpeg", new File(file.getPath() + ".jpeg"));
            }
            catch(Exception exception)
            {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        }



    }

    /**
     *  Override del metodo actionPerformed.
     *
     *  <p>
     *      Se viene premuto il button save
     *  </p>
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == save){
            saveImg();

        }
        if(e.getSource() == cancel){
            setVisible(false);
        }
        if(e.getSource() == colMenu){
            paintColor = colMenu.getSelectedItem().toString();
        }
        if(e.getSource() == dimPixel){
            pixel = Integer.parseInt(dimPixel.getSelectedItem().toString().split(" ")[0]);
        }

        if(e.getSource() == utMenu){
            utContr = utMenu.getSelectedItem().toString();
            pane.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                    new ImageIcon("src/resources/icons/cursorIconss/"+utMenu.getSelectedItem().toString()+".png").getImage(),
                    new Point(0,0),"custom cursor"));
        }


    }

    /**
     * Override del metodo mouseDragged.
     * Implementaa tutto il necessario alla realizzazione del draw.
     * Utilizziamo la classe Graphics2D per disegnare nell'area da disegno.
     * Attraverso i metodi setColor() riusciamo a cambiare colore della matita.
     *
     * Il disegno avviene tramite il disegno di piccole linee che sono collegate l'una all'altra. E' necessario salvare le coordinate (x,y) del mouse precedenti e correnti
     * rispettivamente nelle variabili oldX, oldY, currentX, currentY. Conoscendo queste informazioni disegnamo la linea partendo dalla posizione precedente del mouse fino alla posizione
     * corrente; utilizziamo il metodo drawLine(oldX , oldY , currentX , currentY).
     * Per settare la dimensione della nostra matita utilizziamo il metodo setStroke().
     *
     * La realizzazione della gomma avviene attraverso il metodo repaint().
     * @see Graphics2D
     *
     * @param e the event to be processed
     */

    @Override
    public void mouseDragged(MouseEvent e) {
        if(utContr.equals("MATITA")){
            g = (Graphics2D)pane.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //imposto il colore della matita
            if(paintColor == "NERO"){
                g.setColor(Color.BLACK);

            }
            else if(paintColor == "ROSSO"){
                g.setColor(Color.RED);
            }
            else if(paintColor == "GIALLO"){
                g.setColor(Color.YELLOW);
            }
            else if(paintColor == "BLU"){
                g.setColor(Color.BLUE);
            }
            //salvo la posizione corrente del mouse
            currentX = e.getX() - 10;
            currentY = e.getY() - 40;

            errorStrFormat.setVisible(false);
            //imposto la grandezza della matita
            g.setStroke(new BasicStroke(pixel));
            //disegno
            g.drawLine(oldX , oldY , currentX , currentY);
            //salvo la posizione corrente nella posizione precedente
            oldX = currentX;
            oldY = currentY;
        }else if(utContr == "GOMMA"){
            repaint(e.getX() - 2, e.getY() + 20, pixel + 7 , pixel + 7);
        }


    }

    /**
     * Override metodo mouseMoved.
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * override mouseClicked.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Graphics g = pane.getGraphics();
        if(paintColor == "NERO"){
            g.setColor(Color.BLACK);

        }
        else if(paintColor == "ROSSO"){
            g.setColor(Color.RED);
        }
        else if(paintColor == "GIALLO"){
            g.setColor(Color.YELLOW);
        }
        else if(paintColor == "BLU"){
            g.setColor(Color.BLUE);
        }
    }

    /**
     * override mousePressed.
     * Salva in oldX, oldY la posizione corrente del mouse.
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        oldX = e.getX() - 10;
        oldY = e.getY() - 40;
    }


    /**
     * override mouseReleased.
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    /**
     * override mouseEntered.
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    /**
     * override mouseExited.
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}