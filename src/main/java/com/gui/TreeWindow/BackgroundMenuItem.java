package com.gui.TreeWindow;

import javax.swing.*;
import java.awt.*;

public class BackgroundMenuItem extends JMenuItem {

    private Color bgColor=Color.WHITE;
    public BackgroundMenuItem(String txt){
        super(txt);
    }

    public void setColor(Color color) {
        bgColor=color;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(4, 4, 14, getHeight() - 8);

    }
}
