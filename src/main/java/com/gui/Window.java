package com.gui;
import javax.swing.*;
import java.awt.*;

public class Window {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Abelito");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JLabel label = new JLabel("Choose one:");
        JButton button = new JButton("Noemi");
        JButton button1 = new JButton("Abelito");
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(label);
        frame.getContentPane().add(button); // Adds Button to content pane of frame
        frame.getContentPane().add(button1);
        frame.setVisible(true);
    }
}
