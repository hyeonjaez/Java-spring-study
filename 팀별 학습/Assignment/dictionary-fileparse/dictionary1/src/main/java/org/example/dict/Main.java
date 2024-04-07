package src.main.java.org.example.dict;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String path = "/Users/hyeon/Desktop/nhn-academy/NHN-ACADEMY-dictionary-fileparse/dictionary/src/main/java/org.example";
        File file = new File(path);
        new Dictionary(file);

    }
}
