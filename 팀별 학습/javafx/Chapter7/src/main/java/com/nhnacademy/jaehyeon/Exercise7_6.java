package com.nhnacademy.jaehyeon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * https://math.hws.edu/javanotes/c7/exercises.html
 * Write a program that will read a text file selected by the user
 * Will make an alphabetical list of all the different words in that file
 * All words should be converted to lower case
 * Duplicates should be eliminated from the list
 * The list should be written to an output file selected by the user
 */
public class Exercise7_6 {

    public static void main(String[] args) {
        TextIO.readFile("/Users/hyeon/Desktop/default_project/JaeHyeon/chapter7/test.txt");
        List<String> stringList = new ArrayList<>();

        while (!TextIO.eof()) {
            String str = readNextWord();
            if (str != null) {
                stringList.add(str);
            }
        }

        List<String> stringLists = stringList.stream()
                .map(String::toLowerCase)
                .distinct()
                .sorted().collect(Collectors.toList());

        for (String str : stringLists) {
            System.out.println(str);
        }
    }


    private static String readNextWord() {
        char ch = TextIO.peek();
        while (ch != TextIO.EOF && !Character.isLetter(ch)) {

            TextIO.getAnyChar();
            ch = TextIO.peek();
        }
        if (ch == TextIO.EOF) {
            return null;
        }

        String word = "";
        while (true) {
            word += TextIO.getAnyChar();
            ch = TextIO.peek();
            if (ch == '\'') {
                TextIO.getAnyChar();
                ch = TextIO.peek();
                if (Character.isLetter(ch)) {
                    word += "\'" + TextIO.getAnyChar();
                    ch = TextIO.peek();
                } else {
                    break;
                }
            }
            if (!Character.isLetter(ch)) {

                break;
            }

        }
        return word;
    }


}


