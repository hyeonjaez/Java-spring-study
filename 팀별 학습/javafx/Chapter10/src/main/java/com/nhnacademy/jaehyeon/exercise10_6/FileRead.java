package com.nhnacademy.jaehyeon.exercise10_6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FileRead {
    private final String selectFilePath;
    private final Map<String, List<Integer>> wordList;


    public FileRead(String filePath) {
        this.selectFilePath = filePath;
        this.wordList = new TreeMap<>();
    }


    public void readFileOneLine() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.selectFilePath))) {
            String line;
            int countLine = 1;

            while ((line = reader.readLine()) != null) {
                lineNormalize(line, countLine);
                countLine++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void lineNormalize(String line, int countLine) {
        List<String> normalizeString = Arrays.stream(line.split(" "))
                .map(s -> s.replaceAll("[^0-9a-zA-Z가-힣`']", ""))
                .map(s -> s.replace(" ", ""))
                .filter(s -> s.length() >= 3)
                .filter(s -> !s.equals("the"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        intoMap(normalizeString, countLine);
    }

    private void intoMap(List<String> lines, int countLine) {
        for (String str : lines) {
            List<Integer> currentList = this.wordList.getOrDefault(str, new ArrayList<>());
            currentList.add(countLine);
            currentList = distinctList(currentList);
            this.wordList.put(str, currentList);
        }
    }


    private List<Integer> distinctList(List<Integer> a) {
        return a.stream().distinct().collect(Collectors.toList());
    }

    public Map<String, List<Integer>> getWordList() {
        return wordList;
    }
}


