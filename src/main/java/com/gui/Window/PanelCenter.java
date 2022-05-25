package com.gui.Window;

import javax.swing.*;
import java.awt.*;


// TODO: Ho provato a usare una classe a parte per il panel centrale cosi da eliminare righe di codice e variabili (NON FUNZIONA :'( quindi ho lasciato il codice in Window invariato)
public class PanelCenter extends JPanel {

    private JPanel panel2 = new JPanel();
    private JPanel panelChoiceDinasty = new JPanel();
    private JPanel panelLogo = new JPanel();
    private JLabel labLogo = new JLabel();
    private JLabel dropdown_Text = new JLabel("SCEGLI LA DINASTIA ");
    private String[] dynasties = {"DINASTIA GIULIO CLAUDIA", "GUERRA CIVILE ROMANA", "DINASTIA DEI FLAVI", "IMPERATORI ADOTTIVI", "GUERRA CIVILE ROMANA 2", "DINASTIA DEI SEVERI", "ANARCHIA MILITARE", "DINASTIA VALERIANA", "IMPERATORI ILLIRICI", "RIFORMA TETRARCHICA", "GUERRA CIVILE ROMANA_3", "DINASTIA COSTANTINIANA", "CASATA VALENTINIANO TEODOSIO", "CASATA TEODOSIO", "ULTIMI IMPERATORI"};
    JComboBox<String> dropdown_menù = new JComboBox<>(dynasties);
    JButton button = new JButton("CREA ALBERO");


    public PanelCenter(){
        setLayout(new BorderLayout());
        labLogo.setIcon(new ImageIcon("src/resources/logos/logoPROVA.png"));
        labLogo.setBorder(BorderFactory.createEtchedBorder());
        panel2.setLayout(new BorderLayout());
        panelLogo.add(labLogo);
        panelChoiceDinasty.setLayout(null);
        panelChoiceDinasty.add(dropdown_Text);
        panelChoiceDinasty.add(dropdown_menù);
        panelChoiceDinasty.add(button);
        panel2.add(panelChoiceDinasty, BorderLayout.CENTER);
        panel2.add(panelLogo, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);

    }
}
