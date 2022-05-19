package com.gui;

import javax.swing.*;
import java.awt.*;



public class Window {

    private JFrame frame;
    private JLabel logoPrincipale =new JLabel();
    private JPanel pNord = new JPanel(new FlowLayout());
    private JPanel pCentro =new JPanel();       //Forse Card Layout
    private JPanel pSud =new JPanel();
    private JPanel pOvest = new JPanel();
    private JPanel pEst = new JPanel();
    private JTextField testoRoma = new JTextField("Questa è Roma!");
    private JLabel testoTendina = new JLabel("Scegli la dinastia: ");
    private String[] dynasties = {"DINASTIA_GIULIO_CLAUDIA", "GUERRA_CIVILE_ROMANA", "DINASTIA_DEI_FLAVI", "IMPERATORI_ADOTTIVI", "GUERRA_CIVILE_ROMANA2", "DINASTIA_DEI_SEVERI", "ANARCHIA_MILITARE", "DINASTIA_VALERIANA", "IMPERATORI_ILLIRICI", "RIFORMA_TETRARCHICA", "GUERRA_CIVILE_ROMANA_3", "DINASTIA_COSTANTINIANA", "CASATA_VALENTINIANO_TEODOSIO", "CASATA_TEODOSIO", "ULTIMI_IMPERATORI"};
    JComboBox<String> mTendina = new JComboBox<>(dynasties);
    JButton button = new JButton("CREA ALBERO");
    public Window(){
        frame = new JFrame("WEB SCRAPER - IMPERATORI ROMANI");
        frame.setLayout(new BorderLayout());
    }
    public void setup(){

        //panel_text.setBackground(Color.BLACK);   // forse servirà a qualcosa, sennò è da togliere

        // TODO: MODIFICARE IL LOGO E IL PANEL NORD
        pNord.add(logoPrincipale);
        logoPrincipale.setIcon(new ImageIcon("src/resources/logos/LOGO_AS_ROMA.png"));  // logo da cambiare
        logoPrincipale.setHorizontalAlignment(JLabel.CENTER);
        logoPrincipale.setVisible(true);


        // TODO: DA MIGLIORARE/MODIFICARE IL BUTTON E I COMPONENTI DEL PANEL CENTRO
        testoTendina.setForeground(new Color(0xD57412));
        pCentro.setBackground(new Color(0x7C0016));       //Da levare o cambiare colore in base ai nostri gusti
        pCentro.add(testoTendina);
        pCentro.add(mTendina);
        pCentro.add(button);
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans", Font.BOLD, 15));
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setBackground(Color.yellow);


        // TODO: DA MODIFICARE/MIGLIORARE IL TESTO NEL PANEL SOUTH
        testoRoma.setBorder(BorderFactory.createEmptyBorder());
        pSud.add(testoRoma);            //Testo da mettere nel panel sud
        testoRoma.setEditable(false);



        // GESTIONE DEL FRAME
        frame.add(pNord, BorderLayout.NORTH);
        frame.add(pSud, BorderLayout.SOUTH);
        frame.add(pCentro, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setResizable(false);  // forse TRUE, per adesso FALSE
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);          //Si apre la finestra al centro dello schermo
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new Window().setup();
    }
}
