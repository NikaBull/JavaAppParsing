package nikita.parsit.papki.com.fileprocessor;

import nikita.parsit.papki.com.exceptions.FileContentNotReadException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TxtFileProcessor implements FileProcessor {


    @Override
    public String process(File file) {
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            throw new FileContentNotReadException(e);
        }

    }
}
