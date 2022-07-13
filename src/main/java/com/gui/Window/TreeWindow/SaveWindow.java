package com.gui.Window.TreeWindow;

import com.gui.MyFont;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;


//TODO: 1) SISTEMARE LA FINESTRA QUANDO SI SALVA A SCHERMO INTERO
//TODO: 2) SISTEMARE LA FINESTRA SALVA CHE SI APRE DUE VOLTE
//TODO: 3) IMPLEMENTARE TASTO ELIMINA MODIFICHE (CANCELLA DISEGNO)
//TODO: 4) MIGLIORARE LA PARTE GRAFICA (O ANCHE NO)


/**
 * SaveWindow Class.
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




    /**
     * Class constructor.
     * @param bf
     */
    public SaveWindow(BufferedImage bf){

        super("SAVE");

        panel.setBackground(new Color(0xFFFFFF));
        setupListener();
        mnemonics();
        setResizable(false);

        setSize(Math.max(bf.getWidth(), 740), bf.getHeight()+100);
        setVisible(true);
        pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bf, 0, 0, null);
            }
        };
        pane.setBounds(0, 45, bf.getWidth(), bf.getHeight());
        pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pane.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon("src/resources/icons/cursorIconss/MATITA.png").getImage(),
                new Point(0,0),"custom cursor"));
        panel.add(pane);



            /*
            toolsText.setBounds(bf.getWidth() + 20, 10, 150, 20);
            toolsText.setForeground(new Color(0x000000));
            toolsText.setFont(MyFont.creaFont("src/resources/fonts/lato.light.ttf", 14f));

             */


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



        //System.out.println("WIDTH : " + width + " \n" + "HEIGHT : " + height);
        add(panel);
    }
    public void mnemonics(){
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));      //FIXME: Se viene chiusa la finestra di save, viene riaperta per una seconda volta
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));      // Non è necessarrio mettere sia setAccelerator che setMnemonic
        drawItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
    }

    public void setupListener(){
        addMouseListener(this);
        addMouseMotionListener(this);
        save.addActionListener(this);
        cancel.addActionListener(this);
        colMenu.addActionListener(this);
        saveItem.addActionListener(this);
        drawItem.addActionListener(this);
        utMenu.addActionListener(this);
        dimPixel.addActionListener(this);



    }


    /**
     * setup method
     */
    public void setup(){

        //salva immagine
        try {
            /*
            Point p = pane.getLocationOnScreen();

            Dimension dim = new Dimension(pane.getWidth() - 15, pane.getHeight());

            Rectangle rect = new Rectangle(p, dim);

            Robot robot = new Robot();
            this.image = robot.createScreenCapture(rect);

             */
            BufferedImage image = new BufferedImage(pane.getWidth(), pane.getHeight(), BufferedImage.TYPE_INT_RGB);
            pane.paint(image.getGraphics());
            new SaveWindow(image);

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        //-------------------
        jF.setDialogTitle("Save as...");
        jF.showSaveDialog(null);
        jF.setFileFilter(new FileType(".jpeg", "jpeg file"));

        int res = jF.showSaveDialog(null);
        if(res == JFileChooser.APPROVE_OPTION){
            File file = jF.getSelectedFile();

            try
            {
                ImageIO.write(image,"jpeg", new File(new File(file.getPath()) + ".jpeg"));
            }
            catch(Exception exception)
            {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        }



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == save){
            setup();

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

        if(e.getSource() == saveItem){
            setup();
        }
        if(e.getSource() == exitItem){
            setVisible(false);
        }
        if(e.getSource() == utMenu){
            utContr = utMenu.getSelectedItem().toString();
            pane.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                    new ImageIcon("src/resources/icons/cursorIconss/"+utMenu.getSelectedItem().toString()+".png").getImage(),
                    new Point(0,0),"custom cursor"));
        }


    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(utContr.equals("MATITA")){


            Graphics2D g = (Graphics2D)pane.getGraphics();
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
            currentX = e.getX() - 10;
            currentY = e.getY() - 40;


            errorStrFormat.setVisible(false);
            g.setStroke(new BasicStroke(pixel));
            g.drawLine(oldX , oldY , currentX , currentY);



            oldX = currentX;
            oldY = currentY;
        }else if(utContr == "GOMMA"){
            repaint(e.getX() - 5, e.getY() + 28, pixel, pixel);
        }


    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }

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

    @Override
    public void mousePressed(MouseEvent e) {
        oldX = e.getX() - 10;
        oldY = e.getY() - 40;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}