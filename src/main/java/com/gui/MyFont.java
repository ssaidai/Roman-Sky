package main.java.com.gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;


// FONT SCARICABILI DAL SITO https://www.1001fonts.com/minimalistic-fonts.html?page=1 O DA UNO QUALSIASI
// BASTA CHE SIA .TTF

/**
 * This class, thorugh his unique static method, enables the realization of custom fonts.
 *
 */
public class MyFont {

    /**
     * Create custom fonts.
     * <p>
     *     It takes in input a file .TTF and a floating point number.
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
