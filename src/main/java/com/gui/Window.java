package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;


//  TODO: RINOMINATE LE VARIABILI IN INGLESE, ETTA HA DETTO CHE IL CODICE DEVE ESSERE IN INGLESE .-.

public class Window extends JFrame implements ActionListener{

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
    private JLabel testoTendina = new JLabel("SCEGLI LA DINASTIA ");
    private JLabel labSx = new JLabel();
    private JLabel labDx = new JLabel();
    private JLabel immagineSfondo = new JLabel();
    private JLabel immagineSfondo2 = new JLabel();
    private JLabel labLogo = new JLabel();
    private JLabel labSPQR = new JLabel();
    private JLabel spazioBianco = new JLabel();
    private String[] dynasties = {"DINASTIA GIULIO CLAUDIA", "GUERRA CIVILE ROMANA", "DINASTIA DEI FLAVI", "IMPERATORI ADOTTIVI", "GUERRA CIVILE ROMANA 2", "DINASTIA DEI SEVERI", "ANARCHIA MILITARE", "DINASTIA VALERIANA", "IMPERATORI ILLIRICI", "RIFORMA TETRARCHICA", "GUERRA CIVILE ROMANA_3", "DINASTIA COSTANTINIANA", "CASATA VALENTINIANO TEODOSIO", "CASATA TEODOSIO", "ULTIMI IMPERATORI"};
    JComboBox<String> mTendina = new JComboBox<>(dynasties);
    JButton button = new JButton("CREA ALBERO");



    public Window(){
        super("Imperatori Romani");
        setLayout(new BorderLayout());
        setupListener();
    }

    private void setupListener(){
        button.addActionListener(this);
    }

    public void setup() {





        //TODO: BISOGNA MODIFICARE IL PANEL CENTRALE PER RENDERLO PIU' MINIMAL E CARINO DA VEDERE.
        //TODO: BISOGNA INIZIARE AD IMPLEMENTARE ACTIONLISTENER :))



        // GESTIONE LABEL
        immagineSfondo.setPreferredSize(new Dimension(1400, 30));
        immagineSfondo.setIcon(new ImageIcon("src/resources/images/sfondo2Nord.jpg"));
        immagineSfondo2.setPreferredSize(new Dimension(1400, 30));
        immagineSfondo2.setIcon(new ImageIcon("src/resources/images/sfondo2Sud.jpg"));
        spazioBianco.setPreferredSize(new Dimension(10, 400));
        labLogo.setIcon(new ImageIcon("src/resources/logos/logoPROVA.png"));
        labLogo.setBorder(BorderFactory.createEtchedBorder());
        logoPrincipale.setHorizontalAlignment(JLabel.CENTER);
        logoPrincipale.setVisible(true);
        testoRoma.setBorder(BorderFactory.createEmptyBorder());
        testoRoma.setEditable(false);
        testoTendina.setBounds(0,100,400,50);
        testoTendina.setForeground(new Color(0x000000));
        testoTendina.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin.ttf", 25f));
        labSx.setIcon(new ImageIcon("src/resources/images/Augusto3sx.png"));
        labDx.setIcon(new ImageIcon("src/resources/images/Augusto3dx.png"));
        labSPQR.setIcon(new ImageIcon("src/resources/logos/logo_spqr.png"));
        mTendina.setBackground(new Color(0xFAF7F7));
        mTendina.setFocusable(false);
        mTendina.setBorder(BorderFactory.createRaisedBevelBorder());
        mTendina.setFont(new Font("Sans Serif", Font.PLAIN, 17));
        mTendina.setBounds(0,150,600,30);



        // GESTIONE BUTTON
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans", Font.BOLD, 15));
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setBackground(new Color(0x676060));
        button.setForeground(Color.white);
        button.setFont(MyFont.creaFont("src/resources/fonts/Coco-Gothic-Regular-trial.ttf", 15f));
        button.setBounds(0,300,160,30);


        // GESTIONE PANEL
        pCentro.setLayout(new BorderLayout());
        pCentro2.setLayout(new BorderLayout());
        //pCentro2.add(spazioBianco, BorderLayout.SOUTH);
        pCentro2CENTRO.setLayout(null);
        pCentro2.add(pCentro2CENTRO, BorderLayout.CENTER);
        pCentro2.add(pCentro2NORD, BorderLayout.NORTH);
        pCentro.add(pCentro2, BorderLayout.CENTER);
        pNord.setLayout(new GridBagLayout());
        pOvest.setLayout(new BorderLayout());
        pEst.setLayout(new BorderLayout());
        pNord.add(immagineSfondo);
        pNord.setBackground(new Color(0x282727));
        pCentro2CENTRO.add(testoTendina);
        pCentro2CENTRO.add(mTendina);
        pCentro2CENTRO.add(button);
        pCentro2NORD.add(labLogo);
        pOvest.add(labSx, BorderLayout.SOUTH);
        pEst.add(labDx, BorderLayout.SOUTH);
        pSud.setLayout(new GridBagLayout());
        pSud.add(immagineSfondo2);


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


    // GESTIONE DEGLI EVENTI
    @Override
    public void actionPerformed(ActionEvent e) {
        String bottone = e.getActionCommand();
        if(bottone.equals("CREA ALBERO"))
        {
            System.out.println("FORZA ROMA");
        }
    }
}
