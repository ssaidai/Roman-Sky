package com.gui.TreeWindow;

import com.gui.MyFont;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;


//TODO: 1) SISTEMARE LA FINESTRA QUANDO SI SALVA A SCHERMO INTERO
//TODO: 2) SISTEMARE LA FINESTRA SALVA CHE SI APRE DUE VOLTE
//TODO: 3) IMPLEMENTARE TASTO ELIMINA MODIFICHE (CANCELLA DISEGNO)
//TODO: 4) MIGLIORARE LA PARTE GRAFICA (O ANCHE NO)


/**
 * SaveWindow Class.
 */
public class SaveWindow extends JFrame implements ActionListener, MouseMotionListener {


    private JFileChooser jF = new JFileChooser(new File("c://"));
    private BufferedImage image;
    private JPanel panel = new JPanel(null);
    private JButton save = new JButton("SAVE");
    private JButton cancel = new JButton("CANCEL");
    private JPanel pane;
    private JLabel toolsText = new JLabel("STRUMENTI DISEGNO ");
    private JLabel colorText = new JLabel("SCEGLI COLORE ");
    private JLabel pixelText = new JLabel("INSERISCI PIXEL ");
    private String[] c = {"NERO", "ROSSO", "GIALLO", "BLU"};

    private JComboBox<String> colMenu = new JComboBox<>(c);

    private JTextArea pixMenu = new JTextArea();
    private String paintColor = "BLACK";
    private boolean contr = true;
    private JPanel p2 = new JPanel(null);
    private int pixel = 1;
    private JButton pixBttn = new JButton("O");
    private JPanel pPrincipale = new JPanel(null);
    private JLabel errorStrFormat = new JLabel("Numero di pixel non valido...");


    /**
     * Class constructor.
     * @param bf
     */
    public SaveWindow(BufferedImage bf){

        super("SAVE");
        panel.setBackground(new Color(0xFFFFFF));
        setupListener();
        setResizable(false);
        setSize(bf.getWidth() + 200,bf.getHeight() + 100);
        setVisible(true);
        pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bf, 0, 0, null);
            }
        };


        toolsText.setBounds(bf.getWidth() + 20, 10, 150, 20 );
        toolsText.setForeground(new Color(0x000000));
        toolsText.setFont(MyFont.creaFont("src/resources/fonts/lato.light.ttf" , 14f));


        colorText.setBounds(bf.getWidth() + 20, 40, 150, 20 );
        colorText.setForeground(new Color(0x000000));
        colorText.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin.ttf" , 12f));

        colMenu.setBounds(bf.getWidth() + 20, 60, 150, 20 );

        pixelText.setBounds(bf.getWidth() + 20, 100, 150, 20 );
        pixelText.setForeground(new Color(0x000000));
        pixelText.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin.ttf" , 12f));

        pixMenu.setBounds(bf.getWidth() + 20, 120, 70, 20 );
        pixMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pixBttn.setBounds(bf.getWidth() + 100, 120, 80, 20);
        pixBttn.setText("OK");
        pixBttn.setFocusable(false);
        errorStrFormat.setBounds(bf.getWidth() + 20, 150, 190, 20);
        errorStrFormat.setVisible(false);
        errorStrFormat.setForeground(new Color(0xF30000));
        errorStrFormat.setFont(MyFont.creaFont("src/resources/fonts/lato.light.ttf" , 11f));




        save.setBounds(10,getHeight() - 80,120, 30 );
        save.setFocusable(false);
        cancel.setBounds(140,getHeight() - 80,120, 30 );
        cancel.setFocusable(false);




        pane.setBounds(0,0,bf.getWidth() ,bf.getHeight());
        pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(pane);
        panel.add(errorStrFormat);
        panel.add(pixBttn);
        panel.add(colorText);
        panel.add(pixelText);
        panel.add(pixMenu);
        panel.add(toolsText);
        panel.add(colMenu);
        panel.add(colMenu);
        panel.add(cancel);
        panel.add(save);
        //JScrollPane p = new JScrollPane(panel);
        add(panel);









    }

    public void setupListener(){
        addMouseMotionListener(this);
        save.addActionListener(this);
        cancel.addActionListener(this);
        colMenu.addActionListener(this);
        pixBttn.addActionListener(this);


    }


    /**
     * setup method
     */
    public void setup(){

        //salva immagine
        try {

            Point p = pane.getLocationOnScreen();

            Dimension dim = pane.getSize();

            Rectangle rect = new Rectangle(p, dim);

            Robot robot = new Robot();
            this.image = robot.createScreenCapture(rect);

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

    public boolean isNum(String txt){
        try {
            Integer number = Integer.valueOf(txt);
            if(number < 0){
                errorStrFormat.setVisible(true);
                return false;
            }
            errorStrFormat.setVisible(false);
        }catch (NumberFormatException ex){
            errorStrFormat.setVisible(true);
            return false;
        }
        return true;
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
        if(e.getSource() == pixBttn){
            if(isNum(pixMenu.getText()) == true){
                pixel = Integer.parseInt(pixMenu.getText());
            }
        }


    }

    @Override
    public void mouseDragged(MouseEvent e) {
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

        g.fillRect(e.getX(), e.getY(), pixel, pixel);



    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}