package com.gui.TreeWindow;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FileType extends FileFilter {
    private final String ext;
    private final String descr;
    public FileType(String ext, String d){
        this.descr = d;
        this.ext  = ext;
    }
    @Override
    public boolean accept(File f) {
        if(f.isDirectory()){
            return true;
        }
        return f.getName().endsWith(ext);
    }

    @Override
    public String getDescription() {
        return descr + String.format(" (*%s)", ext);
    }
}
