package nikita.parsit.papki.com.folder;

import nikita.parsit.papki.com.exceptions.FileExtensionNotFoundException;
import nikita.parsit.papki.com.fileprocessor.FileProcessor;
import nikita.parsit.papki.com.fileprocessor.TxtFileProcessor;
import nikita.parsit.papki.com.fileprocessor.WordFileProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FolderProcessor implements Runnable {

    //    private final long minFileAge = TimeUnit.SECONDS.toMillis(5);
    private final Path folderPath;
    private static long fileCount = 0;

    private boolean stop = false;
    private final ESSaver esSaver;
    private final Map<String, FileProcessor> extensionToProcessor = Map.of(
            "txt", new TxtFileProcessor(),
            "doc", new WordFileProcessor(),
            "docx", new WordFileProcessor()

    );


    public FolderProcessor(Path folderPath, ESSaver esSaver) throws IOException {
        this.esSaver = esSaver;
        this.folderPath = folderPath;

        initFolder();
    }

    @Override
    public void run() {
        this.stop = false;
        while (!stop) {
            List<File> files = null;
            try {
                files = getFiles();
            } catch (IOException ex) {
                Logger.getLogger(FolderProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(files);
            files.forEach(this::processFile);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void stop() {
        this.stop = true;
    }

    private void initFolder() throws IOException {
        if (!folderPath.toFile().exists()) {
            System.out.println("Folder is missing: " + folderPath);
            if (folderPath.toFile().mkdir()) {
                System.out.println("Folder is created: " + folderPath);
            } else {
                throw new IOException("Failed to create a folder: " + folderPath);
            }
        }
    }

    private List<File> getFiles() throws IOException {
        final List<File> remainingFiles = new ArrayList<>();

        File[] files = folderPath.toFile().listFiles();
        if (files == null) {
            System.err.println("Failed to get file list");
            return new ArrayList<>();
        }
        System.out.println("fielsprocessed :" + fileCount);
        for (File file : files) {
            fileCount++;
            remainingFiles.add(file);
        }


        return remainingFiles;
    }

    private void processFile(File file) {
        try {
            String extension = FileExtensionFinder.getExtension(file);
            FileProcessor fileProcessor = extensionToProcessor.get(extension);
            if (fileProcessor == null) {
                System.err.println("Can't process file with this extension " + extension);
                return;
            }

            String content = fileProcessor.process(file);
            boolean isSuccess = esSaver.save(content, "allcontent", file.getName());
            if (!isSuccess) {
                System.out.println("Error save file with name " + file.getName());

            }

        } catch (FileExtensionNotFoundException e) {
            e.printStackTrace();
        }

    }


}

