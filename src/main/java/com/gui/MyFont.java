package com.gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;


// FONT SCARICABILI DAL SITO https://www.1001fonts.com/minimalistic-fonts.html?page=1 O DA UNO QUALSIASI
// BASTA CHE SIA .TTF

/**
 * Questa classe ,attraverso il suo unico metodo statico, rende possibile la realizzazione di font personalizzati.
 *
 */
public class MyFont {

    /**
     * Crea font personalizzati.
     * <p>
     *     Prende in input il percorso di un file .TTF e un numero a virgola mobile size.
     * </p>
     *
     * @param path
     * @param size
     * @return
     */
    public static Font creaFont(String path, float size) {
        Font customFont = null;
        try {
            //create the font to use. Specify the size!
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        return customFont;
    }

}
