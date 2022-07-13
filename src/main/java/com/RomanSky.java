package main.java.com;

import com.formdev.flatlaf.FlatIntelliJLaf;
import main.java.com.gui.Window.MainWindow;

import javax.swing.*;

public class RomanSky {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MainWindow();
    }
}