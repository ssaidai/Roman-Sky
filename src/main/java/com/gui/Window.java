package com.gui;

import javax.swing.*;
import java.awt.*;



public class Window extends JFrame{

    private Container cont;
    private JLabel logoPrincipale =new JLabel();
    private JPanel pNord = new JPanel(new FlowLayout());
    private JPanel pCentro =new JPanel();       //Forse Card Layout
    private JPanel pSud =new JPanel();
    private JPanel pOvest = new JPanel();
    private JPanel pEst = new JPanel();
    private JTextField testoRoma = new JTextField("Questa è Roma!");
    private JLabel testoTendina = new JLabel("SCEGLI LA DINASTIA: ");
    private JLabel labSx = new JLabel();
    private JLabel labDx = new JLabel();
    private JLabel testoProvvisiorio = new JLabel();
    private JLabel testoProvvisiorio2 = new JLabel();



    private String[] dynasties = {"DINASTIA_GIULIO_CLAUDIA", "GUERRA_CIVILE_ROMANA", "DINASTIA_DEI_FLAVI", "IMPERATORI_ADOTTIVI", "GUERRA_CIVILE_ROMANA2", "DINASTIA_DEI_SEVERI", "ANARCHIA_MILITARE", "DINASTIA_VALERIANA", "IMPERATORI_ILLIRICI", "RIFORMA_TETRARCHICA", "GUERRA_CIVILE_ROMANA_3", "DINASTIA_COSTANTINIANA", "CASATA_VALENTINIANO_TEODOSIO", "CASATA_TEODOSIO", "ULTIMI_IMPERATORI"};
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
        testoProvvisiorio.setText("DINASTIE ROMANE");
        testoProvvisiorio.setFont(new Font("Times New Roman", Font.BOLD, 80));
        testoProvvisiorio.setPreferredSize(new Dimension(1400,120));
        testoProvvisiorio.setForeground(new Color(0xE7D3D3));
        testoProvvisiorio2.setText("Fere libenter homines id quod volunt credunt");
        testoProvvisiorio2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        testoProvvisiorio2.setForeground(new Color(0xE7D3D3));
        logoPrincipale.setHorizontalAlignment(JLabel.CENTER);
        logoPrincipale.setVisible(true);
        testoRoma.setBorder(BorderFactory.createEmptyBorder());
        testoRoma.setEditable(false);
        testoTendina.setForeground(new Color(0xD57412));
        labSx.setIcon(new ImageIcon("src/resources/images/Augusto3sx.png"));
        labDx.setIcon(new ImageIcon("src/resources/images/Augusto3dx.png"));


        // GESTIONE BUTTON
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans", Font.BOLD, 15));
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setBackground(Color.yellow);


        // GESTIONE PANEL
        pCentro.setLayout(new GridBagLayout());
        pNord.setLayout(new GridBagLayout());
        pOvest.setLayout(new BorderLayout());
        pEst.setLayout(new BorderLayout());
        pNord.add(testoProvvisiorio);
        pNord.setBackground(new Color(0x493939));
        pCentro.setBackground(new Color(0xEFEBEB));       //Da levare o cambiare colore in base ai nostri gusti
        pCentro.add(testoTendina);
        pEst.setBackground(new Color(0xEFEBEB));
        pOvest.setBackground(new Color(0xEFEBEB));
        pCentro.add(mTendina);
        pCentro.add(button);
        pOvest.add(labSx, BorderLayout.SOUTH);
        pEst.add(labDx, BorderLayout.SOUTH);
        pSud.add(testoProvvisiorio2);
        pSud.setBackground(new Color(0x493939));


        // GESTIONE DEL FRAME E DEL CONTAINER
        cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(pNord, BorderLayout.NORTH);
        cont.add(pSud, BorderLayout.SOUTH);
        cont.add(pCentro, BorderLayout.CENTER);
        cont.add(pOvest, BorderLayout.WEST);
        cont.add(pEst, BorderLayout.EAST);
        setSize(1400, 1000);
        setResizable(false);  // forse TRUE, per adesso FALSE
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);          //Si apre la finestra al centro dello schermo
        setVisible(true);
    }
    public static void main(String[] args) {
        new Window().setup();
    }
}
