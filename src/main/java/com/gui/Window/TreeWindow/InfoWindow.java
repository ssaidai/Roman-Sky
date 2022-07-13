package com.gui.Window.TreeWindow;

import com.gui.MyFont;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * InfoWindow è la classe che rappresenta tutte le info che riguardano la progettazione del progetto.
 */

public class InfoWindow extends JFrame implements ActionListener {
    JPanel panPrincipale = new JPanel();
    JLabel logo = new JLabel();

    JLabel text = new JLabel("Web Scraper 2021/2022 ");
    JTextArea textPrinc = new JTextArea();
    JButton okBTTN = new JButton("OK");


    /**
     *  Costruttore della classe InfoWindow
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
     * Setup dei componenti che compongono la finestra.
     */

    public void setup(){

        text.setBounds(125, 17, 270, 50);
        text.setFont(MyFont.creaFont("src/resources/fonts/lato.medium.ttf", 20f));

        textPrinc.setBounds(125, 65, 270, 180);
        textPrinc.setEditable(false);
        //textPrinc.setBackground(Color.WHITE);
        textPrinc.setFont(MyFont.creaFont("src/resources/fonts/lato.medium.ttf", 11f));
        String testo = "\n \nProgettato da: \n" +
                "\n" +
                "Max Cuzuc, Diego Guzman, Kevin Huang e \nVictor Lopata\n" +
                "\n" +
                "Solo a scopo formativo o didattico.\n\n" +
                "Versione programma: 1.0\n" +
                "\n"
                ;
        textPrinc.setText(testo);

        logo.setIcon(new ImageIcon("src/resources/icons/iconaFrame2.png"));
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
     * In questo metodo vengono aggiunti i vari Listener ai vari componenti.
     */
    private void setupListener(){
        okBTTN.addActionListener(this);
    }

    /**
     * Override del metodo actionPerformed.
     * <p>
     *     Il click del button okBTTN setta la visibilità del frame a falso.
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
