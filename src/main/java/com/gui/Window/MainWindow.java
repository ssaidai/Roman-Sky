package main.java.com.gui.Window;


import main.java.com.gui.Window.TreeWindow.TreeWindow;
import main.java.com.scraper.Scraper;
import main.java.com.gui.MyFont;
import main.java.com.scraper.Scraper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

/**
 * Class which represent the principle window after starting the program.
 *
 * <p>The principle components implemented in this window are: JComboBox (used to choose the dinasty to show),
 * JButton (to upload data), JSlider (used to choose what dinasty to load) and the JProgressBar (used as loading bar)</p>
 *
 * @see JComboBox
 * @see JSlider
 * @see JProgressBar
 * @see JButton
 */
public class MainWindow extends JFrame implements ActionListener{
    private final JPanel panelNord = new JPanel();
    private final JPanel panelCenter =new JPanel();       //Forse Card Layout
    private final JPanel panelSud =new JPanel();
    private final JPanel panelWest = new JPanel();
    private final JPanel panelEast = new JPanel();
    private final JPanel pCen2 = new JPanel();
    private final JPanel pC2NORD = new JPanel();
    private final JPanel pCen2CEN = new JPanel();
    private final JLabel dropdown_Text = new JLabel("SCEGLI LA DINASTIA");
    private final JLabel labSx = new JLabel();
    private final JLabel labDx = new JLabel();
    private final JLabel background1 = new JLabel();
    private final JLabel background2 = new JLabel();
    private final JLabel labLogo = new JLabel();
    private final JLabel labSPQR = new JLabel();
    private final String[] dynasties = {"DINASTIA GIULIO CLAUDIA", "GUERRA CIVILE ROMANA", "DINASTIA DEI FLAVI", "IMPERATORI ADOTTIVI", "GUERRA CIVILE ROMANA 2", "DINASTIA DEI SEVERI", "ANARCHIA MILITARE", "DINASTIA VALERIANA", "IMPERATORI ILLIRICI", "RIFORMA TETRARCHICA", "GUERRA CIVILE ROMANA 3", "DINASTIA COSTANTINIANA", "CASATA VALENTINIANO TEODOSIO", "CASATA TEODOSIO", "ULTIMI IMPERATORI"};
    private final JComboBox<String> dropdown_menu = new JComboBox<>(dynasties);
    private final JButton button = new JButton("CARICA DATI");

    private Scraper scraper;
    private JProgressBar progressBar;
    private Dimension screenSize;

    private JSlider slider;

    private int sliderValue = 1;


    /**
     * Window class constructor.
     */
    public MainWindow(){
        super("RomanSky");
        setLayout(new BorderLayout());
        setupListener();

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        slider.setValue(1);

        progressBar = new JProgressBar(0, 100);
        setup();
    }
    /**
     * In this method we add Listeners to components.
     */
    private void setupListener(){
        button.addActionListener(this);
        dropdown_menu.addActionListener(this);
    }

    /**
     * Components setup.
     *
     * <p>
     *     LABEL MANAGEMENT: Management of the various label objects. In particular background1 and background2 are used to insert the images sfondo2Nord.jpg and
     *     sfondo2Sud.jpg as wallpaper, labLogo used to insert the main logo to the center of the screen, dropdown_Text represent the main text "Scegli la dinastia"
     *     e infine labSx e labDx che raffigurano le statue di Cesare e Augusto.
     * </p>
     *
     * <p>
     *     COMBOBOX MANAGEMENT: Section dedicated to manage the dropdown_box.
     * </p>
     *
     * <p>
     *     BUTTON MANAGEMENT: Section dedicated to manage the main button.
     * </p>
     *
     * <p>
     *     PROGRESS BAR MANAGEMENT: Management of the progress bar.
     * </p>
     *
     * <p>
     *     SLIDER MANAGEMENT: Management of the slider.
     * </p>
     *
     * <p>
     *     PANEL MANAGEMENT: Management of the different panels -> panelCenter, panelCenter2, pCenter2CENTER, panelNord, panelWest, panelEast, panelSud, pCenter2NORD.
     * </p>
     *
     * <p>
     *     FRAME MANAGEMENT: Management of the frame and its components.
     * </p>
     */
    public void setup() {
        // GESTIONE LABEL
        background1.setPreferredSize(new Dimension(1400, 30));
        background1.setIcon(new ImageIcon("res/images/sfondo2Nord.jpg"));
        background2.setPreferredSize(new Dimension(1400, 30));
        background2.setIcon(new ImageIcon("res/images/sfondo2Sud.jpg"));
        labLogo.setIcon(new ImageIcon("res/logos/logo_small.png"));
        dropdown_Text.setBounds(188,100,400,50);
        dropdown_Text.setForeground(new Color(0x000000));
        dropdown_Text.setFont(MyFont.creaFont("res/fonts/Uni Sans Thin.ttf", 25f));
        labSx.setIcon(new ImageIcon("res/images/GC2.png"));
        labDx.setIcon(new ImageIcon("res/images/Augusto3dx.png"));
        labSPQR.setIcon(new ImageIcon("res/logos/logo_spqr.png"));

        // GESTIONE COMBOBOX
        dropdown_menu.setBackground(new Color(0xFAF7F7));
        dropdown_menu.setFocusable(false);
        dropdown_menu.setBorder(BorderFactory.createRaisedBevelBorder());
        dropdown_menu.setFont(MyFont.creaFont("res/fonts/Uni Sans Thin.ttf", 16f));
        dropdown_menu.setBounds(44,150,516,30);
        dropdown_menu.setEnabled(false);

        // GESTIONE BUTTON
        button.setFocusable(false);
        button.setActionCommand("CARICA DATI");
        button.setFont(new Font("Comic Sans", Font.BOLD, 15));
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setBackground(new Color(0x4D3939));
        button.setForeground(Color.white);
        button.setFont(MyFont.creaFont("res/fonts/lato.medium.ttf", 15f));
        button.setBounds(219,280,160,30);
        button.setEnabled(true);


        // GESTIONE PROGRESS BAR
        progressBar.setFocusable(false);
        progressBar.setFont(MyFont.creaFont("res/fonts/lato.medium.ttf", 15f));
        progressBar.setString("INSERISCI PROFONDITÀ RICERCA");
        progressBar.setBorder(BorderFactory.createEtchedBorder());
        progressBar.setBackground(Color.white);
        progressBar.setForeground(new Color(0x4D3939));
        progressBar.setBounds(55,330,494,30);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);


        //  GESTIONE SLIDER
        slider.setEnabled(true);
        slider.setForeground(new Color(0x4D3939));
        slider.setBackground(Color.white);
        slider.setBounds(79,200,436,60); // Dimensioni JPanel[x=0,y=175,width=594,height=476]
        slider.setMajorTickSpacing(1);
        slider.setFocusable(false);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        Hashtable labelTable = (Hashtable) slider.getLabelTable();
        labelTable.replace(10, new JLabel("∞"));
        slider.setLabelTable(labelTable);
        slider.setFont(MyFont.creaFont("res/fonts/lato.medium.ttf", 15f));
        //slider.setBorder(BorderFactory.createEtchedBorder());
        slider.setVisible(true);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                sliderValue = source.getValue();
            }
        });


        // GESTIONE PANEL
        panelCenter.setLayout(new BorderLayout());
        pCen2.setLayout(new BorderLayout());
        pCen2CEN.setLayout(null);
        pCen2.add(pCen2CEN, BorderLayout.CENTER);
        pCen2.add(pC2NORD, BorderLayout.NORTH);
        panelCenter.add(pCen2, BorderLayout.CENTER);
        panelNord.setLayout(new GridBagLayout());
        panelWest.setLayout(new BorderLayout());
        panelEast.setLayout(new BorderLayout());
        panelNord.add(background1);
        panelNord.setBackground(new Color(0x282727));
        pCen2CEN.add(dropdown_Text);
        pCen2CEN.add(dropdown_menu);
        pCen2CEN.add(button);
        pCen2CEN.add(progressBar);
        pCen2CEN.add(slider);
        pC2NORD.add(labLogo);
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
        setSize(1300, 750);
        //setSize(screenSize.width, screenSize.height);
        setResizable(false);  // forse TRUE, per adesso FALSE
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);          //Si apre la finestra al centro dello schermo
        setVisible(true);
    }

    /**
     *  Override of the method actionPerformed.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String bottone = e.getActionCommand();
        if(bottone.equals("CARICA DATI")){
            button.setText("CREA ALBERO");
            button.setActionCommand("Generate Tree");
            button.setEnabled(false);
            slider.setVisible(false);
            Thread thread = new Thread(() -> {
                scraper = new Scraper(progressBar, sliderValue);
                button.setEnabled(true);
                dropdown_menu.setEnabled(true);
            });
            thread.start();
        }
        if(bottone.equals("Generate Tree"))
        {
            new TreeWindow(dropdown_menu.getSelectedItem().toString(), dropdown_menu.getSelectedIndex(), scraper);
        }
    }
}
