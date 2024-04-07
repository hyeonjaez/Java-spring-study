//package src.main.java.org.example.dict;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//public class JsonParse implements FileParse {
//
//    @Override
//    public Map<String, List<String>> parseFile(File file) {
//        Map<String, List<String>> parseList = new HashMap<>();
//        try (Reader reader = new FileReader(file)) {
//            JSONParser parser = new JSONParser();
//            Object obj = parser.parse(reader);
//            JSONArray jobj = (JSONArray) obj;
//
//            for (Object object : jobj) {
//                JSONObject jsonObj = (JSONObject) object;
//
//                String first = jsonObj.keySet().toString().replaceAll("[\\[\\[\\]]", "");
//                String second = jsonObj.values().toString().replaceAll("[\\[\\[\\]]", "");
//
//                if (parseList.containsKey(first)) {
//                    parseList.get(first).add(second);
//                } else {
//                    List<String> value = new ArrayList<>();
//                    value.add(second);
//                    parseList.put(first, value);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//
//        return parseList;
//    }
//}
