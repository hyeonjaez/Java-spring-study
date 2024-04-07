package org.example.dict;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dictionary implements Dictionary1 {
    private Map<String, List<String>> dictionary;
    private FileParse parse;

    public Dictionary(File file) {
        parse = new FileProxy(file);
        load(file);
    }

    @Override
    public void load(File file) {
        this.dictionary = parse.parseFile(file);
    }

    @Override
    public List<String> findEngByKor(String kor) {
        return this.dictionary.get(kor);
    }

    @Override
    public int count() {
        return this.dictionary.size();
    }

    @Override
    public Collection<String> findAllListKor() {
        return this.dictionary.keySet();
    }

    @Override
    public List<String> findAllEngByKorOrderByHomonymCountDescAndKorDesc() {
        List<String> korList = new ArrayList<>();
//        List<Map.Entry<String, List<String>>> entryList = new ArrayList<>(this.dictionary.entrySet());
//
//
//        entryList.sort((o1, o2) -> {
//            int count = o2.getValue().size() - o1.getValue().size();
//
//            if (count == 0) {
//                return o2.getKey().compareTo(o1.getKey());
//            }
//
//            return count;
//        });
//
//
//        for (Map.Entry<String, List<String>> d : entryList) {
//            korList.add(d.getKey());
//        }
//
//        dictionary.entrySet().stream()
//                .sorted((o1, o2) -> {
//                    int count = o1.getValue().size() - o2.getValue().size();
//                    if (count == 0) {
//                        return o2.getKey().compareTo(o1.getKey());
//                    }
//
//                    return count;
//                }).map(entry -> String.join("", entryList))


        return korList;
    }

    public List<String> findAllEngByKorOrderByHomonymCountAscAndKorAsc() {
        return dictionary.entrySet().stream()
                .sorted((o1, o2) -> {
                    int count = o2.getValue().size() - o1.getValue().size();

                    if (count == 0) {
                        return o2.getKey().compareTo(o1.getKey());
                    }
                    return count;
                })
                .map(entry -> String.join(",", entry.getValue()).split(","))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }
}
