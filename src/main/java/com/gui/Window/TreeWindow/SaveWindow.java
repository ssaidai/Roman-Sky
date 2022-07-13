package main.java.com.gui.Window.TreeWindow;

import main.java.com.gui.MyFont;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;






/**
 * This class consists in the implementation of the window which opens after pressing the command <b>Save</b> in the window <b>Treewindow</b>.
 * It contains especially two functionality: draw and the file chooser.
 *
 * <p>
 *     The functionality draw allows the user to be able to draw over the window you want to save. It is possible to choose between a lot of colors and sizes.
 *     of the pencil. There is also an eraser.
 *     The draw was implemented with the interfaces <b>MouseMotionListener</b> and <b>MouseListener</b> and also thanks to <b>Graphics2D</b>.
 * </p>
 *
 * <p>
 *     The file chooser was implemented thanks to <b>JFileChooser</b>. It saves the image in jpeg format.
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
     * Constructor of the class
     * @param bf parameter of BufferedImage type.
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
     * Setup and configure the window.
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
        pane.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("res/icons/cursorIconss/MATITA.png").getImage(), new Point(0,0),"custom cursor"));
        panel.add(pane);


        // GESTIONE COMPONENTI RIGUARDANTI GLI STRUMENTI DI DISEGNO
        colorText.setBounds(10, 2, 150, 20);
        colorText.setForeground(new Color(0x000000));
        colorText.setFont(MyFont.creaFont("res/fonts/Uni Sans Thin.ttf", 12f));
        colMenu.setBounds(10, 20, 150, 20);
        pixelText.setBounds(180, 2, 150, 20);
        pixelText.setForeground(new Color(0x000000));
        pixelText.setFont(MyFont.creaFont("res/fonts/Uni Sans Thin.ttf", 12f));
        dimPixel.setBounds(180, 20, 70, 20);
        strText.setBounds(290, 2, 150, 20);
        strText.setForeground(new Color(0x000000));
        strText.setFont(MyFont.creaFont("res/fonts/Uni Sans Thin.ttf", 12f));
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
     * In this method we add various Listener and components.
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
     *  Realization of the save operation thanks to <b>JFileChooser</b>.
     *  At first, we get the coordinates  and y of the panel where the draw area is and the dimension of this one and it is created an object Rectangle
     *  with the informations taken. To capture the image we use the method createScreenCapture() of the class <b>Robot</b>.
     *
     *  To save the image, we use the class JFileChooser which provides a simple mechanism to the user for saving the image. Through the
     *  method <b>showSaveDialog(Component parent)</b> a window will open that allows you to surf in our device and to save the image in the folder
     *  we want.
     *  The method setCurrentDirectory(File dir) is used for setting the starting folder.
     *  Through the static method write of the class <b>ImageIO</b> we save/write our image in the chosen folder.
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
     *  Override of the method actionPerformed.
     *
     *  <p>
     *      If the button save is pressed
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
                    new ImageIcon("res/icons/cursorIconss/"+utMenu.getSelectedItem().toString()+".png").getImage(),
                    new Point(0,0),"custom cursor"));
        }


    }

    /**
     * Override of the method mouseDragged.
     * Implements everything necessary for the realization of the draw.
     * We use the class Graphics2D to draw in the drawing area.
     * Through the method setColor() we are able to change the color of the pencil.
     *
     * The drawing is done by drawing small lines that are connected to each other. It is necessary to save the current and previous coordinates (x,y) of the mouse
     * respectively in the variables oldX, oldY, currentX, currentY. With these informations we draw the line starting from the previous position of the mouse until the current
     * position; we use the method drawLine(oldX , oldY , currentX , currentY).
     * We use the method setStroke() for setting the dimension of our pencil.
     *
     * The realization of the eraser occurs through the method repaint().
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
     * Override of the method mouseMoved.
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Override mouseClicked.
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
     * Override mousePressed.
     * It saves the current position of the mouse in oldX and oldY.
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        oldX = e.getX() - 10;
        oldY = e.getY() - 40;
    }


    /**
     * Override mouseReleased.
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    /**
     * Override mouseEntered.
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    /**
     * Override mouseExited.
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}