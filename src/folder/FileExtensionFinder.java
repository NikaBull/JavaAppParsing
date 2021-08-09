package nikita.parsit.papki.com.folder;

import nikita.parsit.papki.com.exceptions.FileExtensionNotFoundException;

import java.io.File;
import java.util.Optional;

public class FileExtensionFinder {

    public static String getExtension(File file) throws FileExtensionNotFoundException {
        String filename = file.getName();
        return Optional.of(file)
                .map(File::getName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                .orElseThrow(() -> new FileExtensionNotFoundException(filename + " Has no extension "));
                

    }

}
