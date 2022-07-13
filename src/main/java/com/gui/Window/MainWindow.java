package com.gui.Window;

import com.gui.MyFont;
import com.gui.Window.TreeWindow.TreeWindow;
import com.scraper.Scraper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

/**
 * Classe che rappresenta la finestra principale dopo aver avviato il programma.
 *
 * <p>I componenti principali implementati in questa finestra sono: JComboBox (utilizzata per scegliere quale dinastia raffigurare),
 * JButton (per caricare i dati), JSlider (utulizzata per scegliere quante dinastie caricare) e la JProgressBar (utilizzatta come barra di caricamento)</p>
 *
 * @see JComboBox
 * @see JSlider
 * @see JProgressBar
 * @see JButton
 */
public class MainWindow extends JFrame implements ActionListener{

    private final JLabel mainLogo =new JLabel();
    private final JPanel panelNord = new JPanel();
    private final JPanel panelCenter =new JPanel();       //Forse Card Layout
    private final JPanel panelSud =new JPanel();
    private final JPanel panelWest = new JPanel();
    private final JPanel panelEast = new JPanel();
    private final JPanel panelCenter2 = new JPanel();
    private final JPanel pCenter2NORD = new JPanel();
    private final JPanel pCenter2CENTER = new JPanel();
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
     * Costruttore della classe Window.
     */
    public MainWindow(){
        super("Imperatori Romani");
        setLayout(new BorderLayout());
        setupListener();

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        slider.setValue(1);

        progressBar = new JProgressBar(0, 100);
        setup();
    }
    /**
     * In questo metodo vengono aggiunti i Listener ai vari componenti.
     */
    private void setupListener(){
        button.addActionListener(this);
        dropdown_menu.addActionListener(this);
    }

    /**
     * setup dei vari componenti utilizzati.
     *
     * <p>
     *     Gestione label: gestione dei vari oggetti di tipo JLabel. In particolare background1 e background2 vengono utilizzati per l'inserimento delle immagini sfondo2Nord.jpg e
     *     sfondo2Sud.jpg come sfondo, labLogo utilizzata per l'inserimento del logo principale al centro dello schermo, dropdown_Text raffigura il testo principale "Scegli la dinastia"
     *     e infine labSx e labDx che raffigurano le statue di Cesare e Augusto.
     * </p>
     *
     * <p>
     *     Gestione Combobox: sezione dedicata alla gestione di dropdown_menu.
     * </p>
     *
     * <p>
     *     Gestione Button: sezione dedicata alla gestione del buttone principale button.
     * </p>
     *
     * <p>
     *     Gestione ProgressBar: gestione della barra di caricamento
     * </p>
     *
     * <p>
     *     Gestione slider: gestione dello slider.
     * </p>
     *
     * <p>
     *     Gestione panel: gestione dei vari panel -> panelCenter, panelCenter2, pCenter2CENTER, panelNord, panelWest, panelEast, panelSud, pCenter2NORD.
     * </p>
     *
     * <p>
     *     Gestione frame: gestione del frame e dei suoi componenti.
     * </p>
     */
    public void setup() {
        // GESTIONE LABEL
        background1.setPreferredSize(new Dimension(1400, 30));
        background1.setIcon(new ImageIcon("src/resources/images/sfondo2Nord.jpg"));
        background2.setPreferredSize(new Dimension(1400, 30));
        background2.setIcon(new ImageIcon("src/resources/images/sfondo2Sud.jpg"));
        labLogo.setIcon(new ImageIcon("src/resources/logos/logo_small.png"));
        dropdown_Text.setBounds(188,100,400,50);
        dropdown_Text.setForeground(new Color(0x000000));
        dropdown_Text.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin.ttf", 25f));
        labSx.setIcon(new ImageIcon("src/resources/images/GC2.png"));
        labDx.setIcon(new ImageIcon("src/resources/images/Augusto3dx.png"));
        labSPQR.setIcon(new ImageIcon("src/resources/logos/logo_spqr.png"));

        // GESTIONE COMBOBOX
        dropdown_menu.setBackground(new Color(0xFAF7F7));
        dropdown_menu.setFocusable(false);
        dropdown_menu.setBorder(BorderFactory.createRaisedBevelBorder());
        dropdown_menu.setFont(MyFont.creaFont("src/resources/fonts/Uni Sans Thin.ttf", 16f));
        dropdown_menu.setBounds(44,150,516,30);
        dropdown_menu.setEnabled(false);

        // GESTIONE BUTTON
        button.setFocusable(false);
        button.setActionCommand("CARICA DATI");
        button.setFont(new Font("Comic Sans", Font.BOLD, 15));
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setBackground(new Color(0x4D3939));
        button.setForeground(Color.white);
        button.setFont(MyFont.creaFont("src/resources/fonts/lato.medium.ttf", 15f));
        button.setBounds(219,280,160,30);
        button.setEnabled(true);


        // GESTIONE PROGRESS BAR
        progressBar.setFocusable(false);
        progressBar.setFont(MyFont.creaFont("src/resources/fonts/lato.medium.ttf", 15f));
        progressBar.setString("Inserisci profondità ricerca");
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
        slider.setFont(MyFont.creaFont("src/resources/fonts/lato.medium.ttf", 15f));
        //slider.setBorder(BorderFactory.createEtchedBorder());
        slider.setVisible(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    sliderValue = source.getValue();
                }
            }
        });


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
        pCenter2CENTER.add(progressBar);
        pCenter2CENTER.add(slider);
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
        setSize(1300, 750);
        //setSize(screenSize.width, screenSize.height);
        setResizable(false);  // forse TRUE, per adesso FALSE
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);          //Si apre la finestra al centro dello schermo
        setVisible(true);
    }

    /**
     *  Override del metodo actionPerformed.
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