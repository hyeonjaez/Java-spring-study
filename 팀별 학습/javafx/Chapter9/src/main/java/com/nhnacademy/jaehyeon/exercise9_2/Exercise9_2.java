package com.nhnacademy.jaehyeon.exercise9_2;

import java.io.*;
import java.util.Arrays;

/*
* https://math.hws.edu/javanotes/c9/exercises.html
* txt 파일의 모든 단어를 알파벳 순으로 나열하고 이 목록을 다른 파일에 저장하는 프로그램을 이진 정렬트리에 단어를 저장하도록 하자.
 */
public class Exercise9_2 {
    public static void main(String[] args) {
        String inputFilePath = "/Users/hyeon/Desktop/DefaultProject/DefaultProject/Chapter9/src/main/java/com/nhnacademy/jaehyeon/exercise9_2/read.txt";
        String outputFilePath = "/Users/hyeon/Desktop/DefaultProject/DefaultProject/Chapter9/src/main/java/com/nhnacademy/jaehyeon/exercise9_2/write.txt";

        SortTree wordTree = new SortTree();
        fileRead(wordTree, inputFilePath);
        fileWrite(wordTree, outputFilePath);
    }

    private static void fileRead(SortTree sortTree, String path) {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                Arrays.stream(line.split(" "))
                        .forEach(s -> sortTree.insert(s.toLowerCase()));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void fileWrite(SortTree sortTree, String path) {
        try (PrintWriter writer = new PrintWriter(path)) {
            sortTree.writeTree(writer);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
