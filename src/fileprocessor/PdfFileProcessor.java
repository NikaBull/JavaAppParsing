package nikita.parsit.papki.com.fileprocessor;

import nikita.parsit.papki.com.exceptions.FileContentNotReadException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfFileProcessor implements FileProcessor {
    @Override
    public String process(File file) {

        try (PDDocument document = PDDocument.load(file)) {

            if (!document.isEncrypted()) {

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);
                System.out.println("Text:" + pdfFileInText);
                return pdfFileInText;
            } else {
                throw new FileContentNotReadException("Processing file is encrypted.Filename : " + file.getName());
            }
        } catch (IOException ex) {
            throw new FileContentNotReadException(ex);
        }

    }

}