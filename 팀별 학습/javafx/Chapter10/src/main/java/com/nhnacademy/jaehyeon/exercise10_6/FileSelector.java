package com.nhnacademy.jaehyeon.exercise10_6;

import java.awt.*;
import java.io.FileNotFoundException;

public class FileSelector {
    private final Frame frame;

    public FileSelector() {
        this.frame = new Frame();
    }

    public String selectReadFile() throws FileNotFoundException {
        FileDialog fileDialog = new FileDialog(this.frame, "SelectInputFile", FileDialog.LOAD);
        fileDialog.setVisible(true);
        return getFilepath(fileDialog);
    }

    public String selectWriteFile() throws FileNotFoundException {
        FileDialog fileDialog = new FileDialog(this.frame, "Select Output File", FileDialog.SAVE);
        fileDialog.setVisible(true);
        return getFilepath(fileDialog);
    }

    private String getFilepath(FileDialog fileDialog) throws FileNotFoundException {
        String selectFilePath = fileDialog.getDirectory() + fileDialog.getFile();
        if (fileDialog.getFile() == null) {
            throw new FileNotFoundException("파일이 없다");
        }

        return selectFilePath;
    }

}
