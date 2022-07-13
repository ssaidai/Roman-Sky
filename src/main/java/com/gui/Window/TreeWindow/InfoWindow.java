package main.java.com.gui.Window.TreeWindow;

import main.java.com.gui.MyFont;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * InfoWindow is the class which represent all the informations concerning the design of the project.
 */

public class InfoWindow extends JFrame implements ActionListener {
    JPanel panPrincipale = new JPanel();
    JLabel logo = new JLabel();

    JLabel text = new JLabel("Web Scraper 2021/2022 ");
    JTextArea textPrinc = new JTextArea();
    JButton okBTTN = new JButton("OK");


    /**
     *  Constructor of the class InfoWindow.
     */
    public InfoWindow(){
        super("Info");
        setContentPane(super.getContentPane());
        setSize(440, 340);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setup();
        setupListener();
    }

    /**
     * Setup of the components that make up the window.
     */

    public void setup(){

        text.setBounds(125, 17, 270, 50);
        text.setFont(MyFont.creaFont("res/fonts/lato.medium.ttf", 20f));

        textPrinc.setBounds(125, 65, 270, 180);
        textPrinc.setEditable(false);
        //textPrinc.setBackground(Color.WHITE);
        textPrinc.setFont(MyFont.creaFont("res/fonts/lato.medium.ttf", 11f));
        String testo = "\n \nProgettato da: \n" +
                "\n" +
                "Max Cuzuc, Diego Guzman, Kevin Huang e \nVictor Lopata\n" +
                "\n" +
                "Solo a scopo formativo o didattico.\n\n" +
                "Versione programma: 1.0\n" +
                "\n"
                ;
        textPrinc.setText(testo);

        logo.setIcon(new ImageIcon("res/icons/iconaFrame2.png"));
        logo.setBounds(15,15, 85,85);
        okBTTN.setFocusable(false);
        okBTTN.setBounds(320, 250, 68, 28);




        panPrincipale.add(okBTTN);
        //panPrincipale.setBackground(Color.WHITE);
        panPrincipale.add(textPrinc);
        panPrincipale.setLayout(null);
        panPrincipale.add(logo);
        panPrincipale.add(text);
        add(panPrincipale);
    }

    /**
     * In this method, Listeners are added to the various components.
     */
    private void setupListener(){
        okBTTN.addActionListener(this);
    }

    /**
     * Override of the method ActionPerformed
     * <p>
     *     The click on the button okBTTN set the visibility of the JFrame to false.
     * </p>
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == okBTTN){
            setVisible(false);
        }
    }
}
