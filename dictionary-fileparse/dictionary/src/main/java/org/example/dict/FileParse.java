package org.example.dict;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface FileParse {
    Map<String, List<String>> parseFile(File file);

}
