import java.io.*;
import java.nio.file.*;
import java.util.*;

public class VocabModel {
    private List<VocabEntry> vocabEntries;  // Liste der Vokabeleinträge
    private Path filePath;  // Pfad zur Datei, die die Vokabeln enthält
    private WatchService watchService;  // Service zum Überwachen von Dateiänderungen
    private Thread fileWatcherThread;  // Thread, der die Datei auf Änderungen überwacht

    // Konstruktor, der die Vokabeldatei lädt und den Dateiwatcher startet
    public VocabModel(String fileName) {
        this.filePath = Paths.get(fileName);  // Der Pfad zur Vokabeldatei wird gesetzt
        this.vocabEntries = new ArrayList<>();  // Die Liste für die Vokabeleinträge wird initialisiert
        loadVocabFromFile();  // Vokabeln aus der Datei laden
        startFileWatcher();  // Startet den Überwachungsmechanismus für Dateiänderungen
    }

    // Methode zum Laden der Vokabeln aus der Datei
    private void loadVocabFromFile() {
        vocabEntries.clear();  // Zuerst die Liste der Vokabeln leeren
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {  // Versuche, die Datei mit einem BufferedReader zu öffnen
            String line;
            while ((line = reader.readLine()) != null) {  // Zeile für Zeile lesen
                String[] parts = line.split(";");  // Zeile anhand des Trennzeichens ';' in zwei Teile teilen
                if (parts.length == 2) {  // Wenn die Zeile zwei Teile enthält (z.B. deutsches Wort und englisches Wort)
                    vocabEntries.add(new VocabEntry(parts[0], parts[1]));  // Neues Vokabelobjekt hinzufügen
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  // Fehlerbehandlung bei IO-Problemen (z.B. Datei nicht gefunden)
        }
    }

    // Methode, die den Dateiüberwacher startet, um Änderungen an der Vokabeldatei zu erkennen
    private void startFileWatcher() {
        fileWatcherThread = new Thread(() -> {  // Ein neuer Thread wird erstellt, um die Dateiänderungen zu überwachen
            try {
                watchService = FileSystems.getDefault().newWatchService();  // Erstelle einen neuen WatchService für Dateiüberwachung
                filePath.getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);  // Registrierung des Verzeichnisses für Dateiänderungen

                while (true) {  // Endlosschleife zur kontinuierlichen Überwachung
                    WatchKey key;
                    try {
                        key = watchService.take();  // Warte auf das nächste Ereignis
                    } catch (InterruptedException e) {
                        return;  // Wenn der Thread unterbrochen wird, beende die Überwachung
                    }

                    // Gehe durch alle Ereignisse, die vom WatchService erzeugt wurden
                    for (WatchEvent<?> event : key.pollEvents()) {
                        // Überprüfe, ob die Datei geändert wurde
                        if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                            // Wenn die geänderte Datei die überwachte Datei ist, lade sie erneut
                            if (event.context().toString().equals(filePath.getFileName().toString())) {
                                loadVocabFromFile();  // Vokabeln aus der Datei erneut laden
                            }
                        }
                    }

                    boolean valid = key.reset();  // Setzt den WatchKey zurück
                    if (!valid) {  // Wenn der WatchKey ungültig ist, breche ab
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();  // Fehlerbehandlung bei IO-Problemen
            }
        });

        fileWatcherThread.setDaemon(true);  // Setze den Thread als Daemon-Thread, damit er im Hintergrund läuft
        fileWatcherThread.start();  // Starte den Dateiwatcher-Thread
    }

    // Methode, um die Vokabeleinträge zurückzugeben
    public List<VocabEntry> getVocabEntries() {
        return vocabEntries;  // Gibt die Liste der Vokabeleinträge zurück
    }

    // Innere Klasse, die einen Vokabeleintrag darstellt
    public static class VocabEntry {
        private String germanWord;  // Das deutsche Wort
        private String englishWord;  // Das englische Wort

        // Konstruktor zum Erstellen eines Vokabeleintrags
        public VocabEntry(String germanWord, String englishWord) {
            this.germanWord = germanWord;  // Setze das deutsche Wort
            this.englishWord = englishWord;  // Setze das englische Wort
        }

        // Getter-Methode für das deutsche Wort
        public String getGermanWord() {
            return germanWord;
        }

        // Getter-Methode für das englische Wort
        public String getEnglishWord() {
            return englishWord;
        }
    }
}
