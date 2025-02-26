import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class VocabModel {
    private List<VocabEntry> vocabEntries;
    private Path filePath;
    private WatchService watchService;
    private Thread fileWatcherThread;

    public VocabModel(String fileName) {
        this.filePath = Paths.get(fileName);
        this.vocabEntries = new ArrayList<>();
        loadVocabFromFile();
        startFileWatcher();
    }

    private void loadVocabFromFile() {
        vocabEntries.clear();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    vocabEntries.add(new VocabEntry(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startFileWatcher() {
        fileWatcherThread = new Thread(() -> {
            try {
                watchService = FileSystems.getDefault().newWatchService();
                filePath.getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

                while (true) {
                    WatchKey key;
                    try {
                        key = watchService.take();
                    } catch (InterruptedException e) {
                        return;
                    }

                    for (WatchEvent<?> event : key.pollEvents()) {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                            if (event.context().toString().equals(filePath.getFileName().toString())) {
                                loadVocabFromFile();
                            }
                        }
                    }

                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        fileWatcherThread.setDaemon(true);
        fileWatcherThread.start();
    }

    public List<VocabEntry> getVocabEntries() {
        return vocabEntries;
    }

    public static class VocabEntry {
        private String germanWord;
        private String englishWord;

        public VocabEntry(String germanWord, String englishWord) {
            this.germanWord = germanWord;
            this.englishWord = englishWord;
        }

        public String getGermanWord() {
            return germanWord;
        }

        public String getEnglishWord() {
            return englishWord;
        }
    }
}