package src.main.java.org.example.dict;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvParse implements FileParse {
    @Override
    public Map<String, List<String>> parseFile(File file) {
        Map<String, List<String>> fileList = new HashMap<>();
        String line = "";

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {

            while ((line = br.readLine()) != null) {
                String first = line.substring(0, line.indexOf(","));
                String second = line.substring(line.indexOf(",") + 1);

                if (fileList.containsKey(first)) {
                    fileList.get(first).add(second);
                } else {
                    List<String> value = new ArrayList<>();
                    value.add(second);
                    fileList.put(first, value);
                }

                List<String> list = fileList.getOrDefault(first, new ArrayList<>());

                list.add(second);

                fileList.putIfAbsent(first, list);


            }
        } catch (FileNotFoundException e) {
            //Todo
        } catch (IOException e) {
            //Todo
        }
        return fileList;
    }
}
