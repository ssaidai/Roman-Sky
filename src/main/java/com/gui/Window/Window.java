package com.gui.Window;



import com.formdev.flatlaf.FlatIntelliJLaf;
import com.gui.MyFont;
import com.gui.TreeWindow.TreeWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener{

    private JLabel mainLogo =new JLabel();
    private JPanel panelNord = new JPanel();
    private JPanel panelCenter =new JPanel();       //Forse Card Layout
    private JPanel panelSud =new JPanel();
    private JPanel panelWest = new JPanel();
    private JPanel panelEast = new JPanel();
    private JPanel panelCenter2 = new JPanel();
    private JPanel pCenter2NORD = new JPanel();
    private JPanel pCenter2CENTER = new JPanel();
    private JLabel dropdown_Text = new JLabel("SCEGLI LA DINASTIA ");
    private JLabel labSx = new JLabel();
    private JLabel labDx = new JLabel();
    private JLabel background1 = new JLabel();
    private JLabel background2 = new JLabel();
    private JLabel labLogo = new JLabel();
    private JLabel labSPQR = new JLabel();
    private String[] dynasties = {"DINASTIA GIULIO CLAUDIA", "GUERRA CIVILE ROMANA", "DINASTIA DEI FLAVI", "IMPERATORI ADOTTIVI", "GUERRA CIVILE ROMANA 2", "DINASTIA DEI SEVERI", "ANARCHIA MILITARE", "DINASTIA VALERIANA", "IMPERATORI ILLIRICI", "RIFORMA TETRARCHICA", "GUERRA CIVILE ROMANA 3", "DINASTIA COSTANTINIANA", "CASATA VALENTINIANO TEODOSIO", "CASATA TEODOSIO", "ULTIMI IMPERATORI"};
    JComboBox<String> dropdown_menù = new JComboBox<>(dynasties);
    JButton button = new JButton("CREA ALBERO");



    public Window(){
        super("Imperatori Romani");
        setLayout(new BorderLayout());
        setupListener();
    }

    private void setupListener(){
        button.addActionListener(this);
        dropdown_menù.addActionListener(this);
    }

    public void setup() {


        // GESTIONE LABEL
        background1.setPreferredSize(new Dimension(1400, 30));
        background1.setIcon(new ImageIcon("src/resources/images/sfondo2Nord.jpg"));
        background2.setPreferredSize(new Dimension(1400, 30));
        background2.setIcon(new ImageIcon("src/resources/images/sfondo2Sud.jpg"));
        labLogo.setIcon(new ImageIcon("src/resources/logos/logoPROVA.png"));
        labLogo.setBorder(BorderFactory.createEtchedBorder());
        mainLogo.setHorizontalAlignment(JLabel.CENTER);
        mainLogo.setVisible(true);
        dropdown_Text.setBounds(85,100,400,50);
        dropdown_Text.setForeground(new Color(0x000000));
        dropdown_Text.setFont(MyFont.creaFont("src/resources/fonts/opificio_light.ttf", 25f));
        labSx.setIcon(new ImageIcon("src/resources/images/GC2.png"));
        labDx.setIcon(new ImageIcon("src/resources/images/Augusto3dx.png"));
        labSPQR.setIcon(new ImageIcon("src/resources/logos/logo_spqr.png"));
        dropdown_menù.setBackground(new Color(0xFAF7F7));
        dropdown_menù.setFocusable(false);
        dropdown_menù.setBorder(BorderFactory.createRaisedBevelBorder());
        dropdown_menù.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin Italic.ttf", 15f));
        dropdown_menù.setBounds(85,150,515,30);



        // GESTIONE BUTTON
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans", Font.BOLD, 15));
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setBackground(new Color(0x4D3939));
        button.setForeground(Color.white);
        button.setFont(MyFont.creaFont("src/resources/fonts/Coco-Gothic-Regular-trial.ttf", 15f));
        button.setBounds(85,300,160,30);


        // GESTIONE PANEL
        panelCenter.setLayout(new BorderLayout());
        panelCenter2.setLayout(new BorderLayout());
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


        // GESTIONE DEL FRAME
        add(panelNord, BorderLayout.NORTH);
        add(panelSud, BorderLayout.SOUTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelWest, BorderLayout.WEST);
        add(panelEast, BorderLayout.EAST);
        setSize(1400, 800);
        setResizable(false);  // forse TRUE, per adesso FALSE
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);          //Si apre la finestra al centro dello schermo
        setVisible(true);
    }



    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Window().setup();
    }


    // GESTIONE DEGLI EVENTI
    @Override
    public void actionPerformed(ActionEvent e) {
        String bottone = e.getActionCommand();
        if(bottone.equals("CREA ALBERO"))
        {

            TreeWindow tree = new TreeWindow(dropdown_menù.getSelectedItem().toString());
            //System.out.println(dropdown_menù.getSelectedItem().toString());

        }
    }
}
