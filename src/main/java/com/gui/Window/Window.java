package com.gui.Window;



import com.formdev.flatlaf.FlatIntelliJLaf;
import com.gui.MyFont;
import com.gui.TreeWindow.TreeWindow;
import com.scraper.Scraper;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener{

    private final JLabel mainLogo =new JLabel();
    private final JPanel panelNord = new JPanel();
    private final JPanel panelCenter =new JPanel();       //Forse Card Layout
    private final JPanel panelSud =new JPanel();
    private final JPanel panelWest = new JPanel();
    private final JPanel panelEast = new JPanel();
    private final JPanel panelCenter2 = new JPanel();
    private final JPanel pCenter2NORD = new JPanel();
    private final JPanel pCenter2CENTER = new JPanel();
    private final JLabel dropdown_Text = new JLabel("SCEGLI LA DINASTIA ");
    private final JLabel labSx = new JLabel();
    private final JLabel labDx = new JLabel();
    private final JLabel background1 = new JLabel();
    private final JLabel background2 = new JLabel();
    private final JLabel labLogo = new JLabel();
    private final JLabel labSPQR = new JLabel();
    private final String[] dynasties = {"DINASTIA GIULIO CLAUDIA", "GUERRA CIVILE ROMANA", "DINASTIA DEI FLAVI", "IMPERATORI ADOTTIVI", "GUERRA CIVILE ROMANA 2", "DINASTIA DEI SEVERI", "ANARCHIA MILITARE", "DINASTIA VALERIANA", "IMPERATORI ILLIRICI", "RIFORMA TETRARCHICA", "GUERRA CIVILE ROMANA 3", "DINASTIA COSTANTINIANA", "CASATA VALENTINIANO TEODOSIO", "CASATA TEODOSIO", "ULTIMI IMPERATORI"};
    private final JComboBox<String> dropdown_menu = new JComboBox<>(dynasties);
    private final JButton button = new JButton("CREA ALBERO");
    private final JLabel loadingGif = new JLabel();

    private Scraper scraper;



    public Window(){
        super("Imperatori Romani");
        setLayout(new BorderLayout());
        setupListener();
    }

    private void setupListener(){
        button.addActionListener(this);
        dropdown_menu.addActionListener(this);
    }

    public void setup() {
        // GESTIONE LABEL
        background1.setPreferredSize(new Dimension(1400, 30));
        background1.setIcon(new ImageIcon("src/resources/images/sfondo2Nord.jpg"));
        background2.setPreferredSize(new Dimension(1400, 30));
        background2.setIcon(new ImageIcon("src/resources/images/sfondo2Sud.jpg"));
        labLogo.setIcon(new ImageIcon("src/resources/logos/logo_small.png"));
        mainLogo.setHorizontalAlignment(JLabel.CENTER);
        mainLogo.setVisible(true);
        dropdown_Text.setBounds(230,100,400,50);
        dropdown_Text.setForeground(new Color(0x000000));
        dropdown_Text.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin.ttf", 25f));
        labSx.setIcon(new ImageIcon("src/resources/images/GC2.png"));
        labDx.setIcon(new ImageIcon("src/resources/images/Augusto3dx.png"));
        labSPQR.setIcon(new ImageIcon("src/resources/logos/logo_spqr.png"));
        dropdown_menu.setBackground(new Color(0xFAF7F7));
        dropdown_menu.setFocusable(false);
        dropdown_menu.setBorder(BorderFactory.createRaisedBevelBorder());
        dropdown_menu.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin Italic.ttf", 15f));
        dropdown_menu.setBounds(85,150,515,30);
        loadingGif.setIcon(new ImageIcon("src/resources/videos or gif/loadingGif2.gif"));
        loadingGif.setBounds(325,340,40,40);



        // GESTIONE BUTTON
        button.setFocusable(false);
        button.setFont(new Font("Comic Sans", Font.BOLD, 15));
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setBackground(new Color(0x4D3939));
        button.setForeground(Color.white);
        button.setFont(MyFont.creaFont("src/resources/fonts/Coco-Gothic-Regular-trial.ttf", 15f));
        button.setBounds(260,300,160,30);
        button.setEnabled(false);


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
        pCenter2CENTER.add(dropdown_menu);
        pCenter2CENTER.add(button);
        pCenter2CENTER.add(loadingGif);
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
    public void openBttn(){
        button.setEnabled(true);
    }
    public void setInvisibleGif(){
        loadingGif.setVisible(false);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Window window = new Window();
        window.setup();
        Scraper scraper = new Scraper();        //  TODO: SETUPPARE UN THREAD APPARTE PER LO SCRAPER  COSI NON SI FREEZZA LA GUI MENTRE CARICA
        System.out.println(" FINITO ");                                        //  TODO: E' MEGLIO ISTANZIARE LO SCRAPER NEL COSTRUTTORE E NON NEL MAIN PERCHE CI SERVE
                                                //  TODO: ANCHE NELL'ALTRA CLASSE (VEDI SOTTO)

        window.scraper = scraper;
        window.openBttn();
        window.setInvisibleGif();

    }


    // GESTIONE DEGLI EVENTI
    @Override
    public void actionPerformed(ActionEvent e) {
        String bottone = e.getActionCommand();
        if(bottone.equals("CREA ALBERO"))
        {
            TreeWindow tree = new TreeWindow(dropdown_menu.getSelectedItem().toString(), dropdown_menu.getSelectedIndex(), scraper);
            //System.out.println(dropdown_menù.getSelectedItem().toString());

        }
    }
}
