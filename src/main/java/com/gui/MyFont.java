package com.gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;


// FONT SCARICABILI DAL SITO https://www.1001fonts.com/minimalistic-fonts.html?page=1 O DA UNO QUALSIASI
// BASTA CHE SIA .TTF
public class MyFont {

    /**
     * Crea font personalizzati.
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

        return customFont;
    }

}
