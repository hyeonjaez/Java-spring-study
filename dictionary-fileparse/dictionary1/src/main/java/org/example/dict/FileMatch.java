package src.main.java.org.example.dict;

import java.io.File;
import java.util.Objects;

public class FileMatch {

    File file;
    String extension;

    public FileMatch(File file) {
        this.file = file;
        this.extension = "";
        toExtractExtension();
    }

    public File getFile(){
        return this.file;
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


}
