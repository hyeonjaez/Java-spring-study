package src.main.java.org.example.dict;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FileProxy implements FileParse {
    @Getter
    private final File file;
    private String extension;

    public FileProxy(File file) {
        this.file = file;
        this.extension = "";
        toExtractExtension();
    }

    public void toExtractExtension() {
        String name = this.file.getName();
        this.extension = name.substring(name.lastIndexOf(".") + 1);
    }

    public FileParse match() {
        if (Objects.equals(this.extension, "json")) {
            return new JsonParse();
        }
        return new CsvParse();
    }

    @Override
    public Map<String, List<String>> parseFile(File file) {
        return this.match().parseFile(file);
    }
}
