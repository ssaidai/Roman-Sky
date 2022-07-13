package com.gui.Window.TreeWindow;

import javax.swing.*;
import java.awt.*;

/**
 * Questa classe estende la classe JMenuItem. Il suo scopo è offrire la possibilità di poter disegnare forme geometriche ( in questo caso solo quaadrato ) su un oggetto di tipo JMenuItem.
 */
public class BackgroundMenuItem extends JMenuItem {

    private Color bgColor=Color.WHITE;

    /**
     * Costruttore della classe BackgroundMenuItem.
     * @param txt
     */
    public BackgroundMenuItem(String txt){
        super(txt);
    }

    /**
     * set del colore
     * @param color
     */
    public void setColor(Color color) {
        bgColor=color;
    }

    /**
     * Override del metodo paintComponent.
     * Grazie a questo metodo siamo in grado di disegnare sopra il nostro oggetto di tipo JMenuItem
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(4, 4, 14, getHeight() - 8);

    }
}
