package com.gui.Window.TreeWindow;

import javax.swing.*;
import java.awt.*;

/**
 * This class extends JMenuItem. Its purpose is to offer the possibility of being able to draw geometric shapes ( in this case only a square ) on a JMenuItem object type.
 */
public class BackgroundMenuItem extends JMenuItem {

    private Color bgColor=Color.WHITE;

    /**
     * Constructor of the class BackgroundMenuItem.
     * @param txt
     */
    public BackgroundMenuItem(String txt){
        super(txt);
    }

    /**
     * set of the color
     * @param color
     */
    public void setColor(Color color) {
        bgColor=color;
    }

    /**
     * Override of the method paintComponent
     * Thanks to this method we are able to draw over our JMenuItem object.
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
