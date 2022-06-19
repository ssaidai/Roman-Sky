package com.gui.TreeWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * SaveWindow Class.
 */
public class SaveWindow extends JFrame {
    private JPanel pan = new JPanel();
    private JButton saveBttn = new JButton("Salva");
    private JButton closeBttn = new JButton("Chiudi");
    private JFileChooser jF = new JFileChooser(new File("c://"));
    private BufferedImage image;

    /**
     * Class constructor.
     * @param bf
     */
    public SaveWindow(BufferedImage bf){
        this.image = bf;
        setup();

    }

    /**
     * setup method
     */
    public void setup(){
        jF.setDialogTitle("Save as...");
        jF.showSaveDialog(null);
        jF.setFileFilter(new FileType(".jpeg", "jpeg file"));

        int res = jF.showSaveDialog(null);
        if(res == JFileChooser.APPROVE_OPTION){
            File file = jF.getSelectedFile();

            try
            {
                ImageIO.write(image,"jpeg", new File(new File(file.getPath()) + ".jpeg"));
            }
            catch(Exception exception)
            {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        }



    }

}