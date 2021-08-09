package nikita.parsit.papki.com.fileprocessor;

import nikita.parsit.papki.com.exceptions.FileContentNotReadException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class WordFileProcessor implements FileProcessor {
    @Override
    public String process(File file) {

        try {
            XWPFDocument document = new XWPFDocument(Files.newInputStream(file.toPath()));
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            String content = paragraphs.stream().map(XWPFParagraph::getText).collect(Collectors.joining());
            document.close();
            System.out.println(content);
            return content;
        } catch (IOException e) {
            throw new FileContentNotReadException(e);

        }

    }


}
