package com.nhnacademy.jaehyeon.exercise10_6;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileWrite {
    private final String selectFilePath;

    public FileWrite(String selectFilePath) {
        this.selectFilePath = selectFilePath;
    }

    public void writeFile(Map<String, List<Integer>> wordList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.selectFilePath))) {
            for (Map.Entry<String, List<Integer>> entry : wordList.entrySet()) {
                String liner = entry.getKey() + " : " + entry.getValue().toString();
                writer.write(liner);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}