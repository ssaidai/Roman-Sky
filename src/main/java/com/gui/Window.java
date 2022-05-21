package com.gui;

import javax.swing.*;
import java.awt.*;


//  TODO: RINOMINATE LE VARIABILI IN INGLESE, ETTA HA DETTO CHE IL CODICE DEVE ESSERE IN INGLESE .-.

public class Window extends JFrame{

    private Container cont;
    private JLabel logoPrincipale =new JLabel();
    private JPanel pNord = new JPanel(new FlowLayout());
    private JPanel pCentro =new JPanel();       //Forse Card Layout
    private JPanel pSud =new JPanel();
    private JPanel pOvest = new JPanel();
    private JPanel pEst = new JPanel();
    private JPanel pCentro2 = new JPanel();
    private JPanel pCentro2NORD = new JPanel();
    private JPanel pCentro2CENTRO = new JPanel();
    private JPanel pCentro2SUD = new JPanel();
    private JTextField testoRoma = new JTextField("Questa è Roma!");
    private JLabel testoTendina = new JLabel("SCEGLI LA DINASTIA: ");
    private JLabel labSx = new JLabel();
    private JLabel labDx = new JLabel();
    private JLabel testoProvvisiorio = new JLabel();
    private JLabel testoProvvisiorio2 = new JLabel();
    private JLabel labLogo = new JLabel();
    private JLabel labSPQR = new JLabel();
    private JLabel spazioBianco = new JLabel();



    private String[] dynasties = {"               DINASTIA GIULIO CLAUDIA               ", "               GUERRA CIVILE ROMANA               ", "               DINASTIA DEI FLAVI               ", "               IMPERATORI ADOTTIVI               ", "               GUERRA CIVILE ROMANA2               ", "               DINASTIA DEI SEVERI               ", "               ANARCHIA MILITARE               ", "               DINASTIA VALERIANA               ", "               IMPERATORI ILLIRICI               ", "               RIFORMA TETRARCHICA               ", "               GUERRA CIVILE ROMANA_3               ", "               DINASTIA COSTANTINIANA               ", "               CASATA VALENTINIANO TEODOSIO               ", "               CASATA TEODOSIO               ", "               ULTIMI IMPERATORI               "};
    JComboBox<String> mTendina = new JComboBox<>(dynasties);
    JButton button = new JButton("CREA ALBERO");
    public Window(){
        super("Imperatori Romani");
        setLayout(new BorderLayout());
    }
    public void setup(){

        //TODO: BISOGNA MODIFICARE IL PANEL CENTRALE PER RENDERLO PIU' MINIMAL E CARINO DA VEDERE.
        //TODO: BISOGNA INIZIARE AD IMPLEMENTARE ACTIONLISTENER :))
        //TODO: MODIFICARE I COLORI E/O INSERIRE SFONDO PIU' CARINO


        // GESTIONE LABEL
        /*
        testoProvvisiorio.setText("DINASTIE ROMANE");
        testoProvvisiorio.setFont(new Font("Sans Serif", Font.BOLD, 70));
        testoProvvisiorio.setPreferredSize(new Dimension(1400,120));
        testoProvvisiorio.setForeground(new Color(0x757474));

         */

        testoProvvisiorio.setPreferredSize(new Dimension(1400, 30));
        testoProvvisiorio.setIcon(new ImageIcon("src/resources/images/sfondo2Nord.jpg"));
        testoProvvisiorio2.setPreferredSize(new Dimension(1400, 30));
        testoProvvisiorio2.setIcon(new ImageIcon("src/resources/images/sfondo2Sud.jpg"));
        /*
        testoProvvisiorio2.setText("Fere libenter homines id quod volunt credunt");
        testoProvvisiorio2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        testoProvvisiorio2.setForeground(new Color(0x757474));

         */
        spazioBianco.setPreferredSize(new Dimension(10,400));
        labLogo.setIcon(new ImageIcon("src/resources/logos/logoPROVA.png"));
        labLogo.setBorder(BorderFactory.createEtchedBorder());
        logoPrincipale.setHorizontalAlignment(JLabel.CENTER);
        logoPrincipale.setVisible(true);
        testoRoma.setBorder(BorderFactory.createEmptyBorder());
        testoRoma.setEditable(false);
        testoTendina.setForeground(new Color(0x000000));
        testoTendina.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        labSx.setIcon(new ImageIcon("src/resources/images/Giuliosx.png"));
        labDx.setIcon(new ImageIcon("src/resources/images/Augustodx.png"));
        labSPQR.setIcon(new ImageIcon("src/resources/logos/logo_spqr.png"));
        mTendina.setBounds(2,2,10,10);


        // GESTIONE BUTTON
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans", Font.BOLD, 15));
        button.setBorder(BorderFactory.createEtchedBorder());
        //button.setBackground(Color.yellow);


        // GESTIONE PANEL

        pCentro.setLayout(new BorderLayout());
        //pCentro2.setBackground(new Color(0xFFFFFF));
        //pCentro2NORD.setBackground(new Color(0xFFFFFF));
        pCentro2.setLayout(new BorderLayout());
        pCentro2.add(spazioBianco, BorderLayout.SOUTH);
        pCentro2CENTRO.setLayout(new GridLayout(3,1, 0,30));
        //pCentro2.setBackground(new Color(0xEFEBEB));
        //pCentro2CENTRO.setBackground(new Color(0xFFFFFF));
        pCentro2.add(pCentro2CENTRO, BorderLayout.CENTER);
        pCentro2.add(pCentro2NORD, BorderLayout.NORTH);
        pCentro.add(pCentro2,BorderLayout.CENTER);
        pNord.setLayout(new GridBagLayout());
        pOvest.setLayout(new BorderLayout());
        pEst.setLayout(new BorderLayout());
        pNord.add(testoProvvisiorio);
        pNord.setBackground(new Color(0x282727));
        //.setBackground(new Color(0xEFEBEB));       //Da levare o cambiare colore in base ai nostri gusti
        pCentro2CENTRO.add(testoTendina);
        //pEst.setBackground(new Color(0xEFEBEB));
        //pOvest.setBackground(new Color(0xEFEBEB));
        pCentro2CENTRO.add(mTendina);
        pCentro2CENTRO.add(button);
        //pCentro2NORD.setBackground(new Color(0xEFEBEB));
        pCentro2NORD.add(labLogo);
        pOvest.add(labSx, BorderLayout.SOUTH);
        pEst.add(labDx, BorderLayout.SOUTH);
        pSud.setLayout(new GridBagLayout());
        pSud.add(testoProvvisiorio2);
        //pEst.setBackground(new Color(0xFFFFFF));
        //pOvest.setBackground(new Color(0xFFFFFF));
        //pSud.setBackground(new Color(0x282727));


        // GESTIONE DEL FRAME E DEL CONTAINER
        cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(pNord, BorderLayout.NORTH);
        cont.add(pSud, BorderLayout.SOUTH);
        cont.add(pCentro, BorderLayout.CENTER);
        cont.add(pOvest, BorderLayout.WEST);
        cont.add(pEst, BorderLayout.EAST);
        setSize(1400, 800);
        setResizable(false);  // forse TRUE, per adesso FALSE
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);          //Si apre la finestra al centro dello schermo
        setVisible(true);
    }
    public static void main(String[] args) {
        new Window().setup();
    }
}
