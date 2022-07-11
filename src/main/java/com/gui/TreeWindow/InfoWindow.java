package com.gui.TreeWindow;

import com.gui.MyFont;

import javax.swing.*;
import java.awt.*;
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
        String testo = "Build #IU-221.5921.22, built on June 21, 2022\n" +
                "\n" +
                "Licensed to Victor Lopata\n" +
                "\n" +
                "For educational use only.\n" +
                "Runtime version: 11.0.15+10-b2043.56 amd64\n" +
                "\n" +
                "Windows 11 10.0\n" +
                "GC: G1 Young Generation, G1 Old Generation\n" +
                "Memory: 752M\n" +
                "Cores: 8";
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
