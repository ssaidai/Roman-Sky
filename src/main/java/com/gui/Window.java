package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//  TODO: RINOMINATE LE VARIABILI IN INGLESE, ETTA HA DETTO CHE IL CODICE DEVE ESSERE IN INGLESE .-.

public class Window extends JFrame implements ActionListener{

    private Container cont;
    private JLabel mainLogo =new JLabel();
    private JPanel panelNord = new JPanel(new FlowLayout());
    private JPanel panelCenter =new JPanel();       //Forse Card Layout
    private JPanel panelSud =new JPanel();
    private JPanel panelWest = new JPanel();
    private JPanel panelEast = new JPanel();
    private JPanel panelCenter2 = new JPanel();
    private JPanel pCenter2NORD = new JPanel();
    private JPanel pCenter2CENTER = new JPanel();
    private JPanel pCenter2SUD = new JPanel();
    private JTextField text_Rome = new JTextField("Questa è Roma!");
    private JLabel dropdown_Text = new JLabel("SCEGLI LA DINASTIA ");
    private JLabel labSx = new JLabel();
    private JLabel labDx = new JLabel();
    private JLabel background1 = new JLabel();
    private JLabel background2 = new JLabel();
    private JLabel labLogo = new JLabel();
    private JLabel labSPQR = new JLabel();
    private JLabel emptySpace = new JLabel();
    private String[] dynasties = {"DINASTIA GIULIO CLAUDIA", "GUERRA CIVILE ROMANA", "DINASTIA DEI FLAVI", "IMPERATORI ADOTTIVI", "GUERRA CIVILE ROMANA 2", "DINASTIA DEI SEVERI", "ANARCHIA MILITARE", "DINASTIA VALERIANA", "IMPERATORI ILLIRICI", "RIFORMA TETRARCHICA", "GUERRA CIVILE ROMANA_3", "DINASTIA COSTANTINIANA", "CASATA VALENTINIANO TEODOSIO", "CASATA TEODOSIO", "ULTIMI IMPERATORI"};
    JComboBox<String> dropdown_menù = new JComboBox<>(dynasties);
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
        background1.setPreferredSize(new Dimension(1400, 30));
        background1.setIcon(new ImageIcon("src/resources/images/sfondo2Nord.jpg"));
        background2.setPreferredSize(new Dimension(1400, 30));
        background2.setIcon(new ImageIcon("src/resources/images/sfondo2Sud.jpg"));
        emptySpace.setPreferredSize(new Dimension(10, 400));
        labLogo.setIcon(new ImageIcon("src/resources/logos/logoPROVA.png"));
        labLogo.setBorder(BorderFactory.createEtchedBorder());
        mainLogo.setHorizontalAlignment(JLabel.CENTER);
        mainLogo.setVisible(true);
        text_Rome.setBorder(BorderFactory.createEmptyBorder());
        text_Rome.setEditable(false);
        dropdown_Text.setBounds(0,100,400,50);
        dropdown_Text.setForeground(new Color(0x000000));
        dropdown_Text.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin.ttf", 25f));
        labSx.setIcon(new ImageIcon("src/resources/images/Augusto3sx.png"));
        labDx.setIcon(new ImageIcon("src/resources/images/Augusto3dx.png"));
        labSPQR.setIcon(new ImageIcon("src/resources/logos/logo_spqr.png"));
        dropdown_menù.setBackground(new Color(0xFAF7F7));
        dropdown_menù.setFocusable(false);
        dropdown_menù.setBorder(BorderFactory.createRaisedBevelBorder());
        dropdown_menù.setFont(new Font("Sans Serif", Font.PLAIN, 17));
        dropdown_menù.setBounds(0,150,600,30);



        // GESTIONE BUTTON
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans", Font.BOLD, 15));
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setBackground(new Color(0x676060));
        button.setForeground(Color.white);
        button.setFont(MyFont.creaFont("src/resources/fonts/Coco-Gothic-Regular-trial.ttf", 15f));
        button.setBounds(0,300,160,30);


        // GESTIONE PANEL
        panelCenter.setLayout(new BorderLayout());
        panelCenter2.setLayout(new BorderLayout());
        //pCentro2.add(emptySpace, BorderLayout.SOUTH);
        pCenter2CENTER.setLayout(null);
        panelCenter2.add(pCenter2CENTER, BorderLayout.CENTER);
        panelCenter2.add(pCenter2NORD, BorderLayout.NORTH);
        panelCenter.add(panelCenter2, BorderLayout.CENTER);
        panelNord.setLayout(new GridBagLayout());
        panelWest.setLayout(new BorderLayout());
        panelEast.setLayout(new BorderLayout());
        panelNord.add(background1);
        panelNord.setBackground(new Color(0x282727));
        pCenter2CENTER.add(dropdown_Text);
        pCenter2CENTER.add(dropdown_menù);
        pCenter2CENTER.add(button);
        pCenter2NORD.add(labLogo);
        panelWest.add(labSx, BorderLayout.SOUTH);
        panelEast.add(labDx, BorderLayout.SOUTH);
        panelSud.setLayout(new GridBagLayout());
        panelSud.add(background2);


        // GESTIONE DEL FRAME E DEL CONTAINER
        cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(panelNord, BorderLayout.NORTH);
        cont.add(panelSud, BorderLayout.SOUTH);
        cont.add(panelCenter, BorderLayout.CENTER);
        cont.add(panelWest, BorderLayout.WEST);
        cont.add(panelEast, BorderLayout.EAST);
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
            System.out.println("FORZA ROMA E ABEL");

        }
    }
}
