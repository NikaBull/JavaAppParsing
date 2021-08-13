package nikita.parsit.papki.com.fileprocessor;

import nikita.parsit.papki.com.exceptions.FileContentNotReadException;
import org.odftoolkit.simple.TextDocument;
import java.io.File;

public class OdtFileProcessor implements FileProcessor {
    @Override
    public String process(File file) {
        try {
            String textContent = TextDocument.loadDocument(file).getContentRoot().getTextContent();
            System.out.println(textContent);
            return textContent;
        } catch (Exception e) {
            throw new FileContentNotReadException(e);

        }

    }
}
