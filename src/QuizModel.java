import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class QuizModel {
    // Das QuizModel speichert Fragen und Antworten in einer HashMap und verwaltet die Logik für das Quiz.
    private Map<String, String> fragenAntworten; // HashMap, um die Fragen und die entsprechenden Antworten zu speichern
    private int richtig; // Zähler für die Anzahl der richtig beantworteten Fragen
    private int falsch; // Zähler für die Anzahl der falsch beantworteten Fragen
    private String dateipfad; // Pfad zur Datei, die die Fragen und Antworten enthält

    // Konstruktor für QuizModel. Initialisiert die HashMap und lädt die Fragen aus einer Datei.
    public QuizModel(String dateipfad) {
        this.fragenAntworten = new HashMap<>(); // Initialisierung der HashMap für Fragen und Antworten
        this.richtig = 0; // Anfangswert für richtig beantwortete Fragen
        this.falsch = 0; // Anfangswert für falsch beantwortete Fragen
        this.dateipfad = dateipfad; // Der Dateipfad wird gesetzt
        ladeFragenAusDatei(dateipfad); // Lädt Fragen und Antworten aus der Datei
    }

    // Diese Methode lädt Fragen und Antworten aus einer Datei und fügt sie der HashMap hinzu.
    private void ladeFragenAusDatei(String dateipfad) {
        try (BufferedReader br = new BufferedReader(new FileReader(dateipfad))) { // BufferedReader für effizientes Lesen der Datei
            String zeile;
            while ((zeile = br.readLine()) != null) { // Liest jede Zeile der Datei
                if (zeile.trim().isEmpty()) continue; // Leere Zeilen überspringen
                String[] teile = zeile.split(";", 2); // Teilt die Zeile an dem Semikolon
                if (teile.length == 2) { // Stellt sicher, dass es genau zwei Teile (Frage und Antwort) gibt
                    fragenAntworten.put(teile[0].trim(), teile[1].trim()); // Fügt die Frage und Antwort zur HashMap hinzu
                    System.out.println("Geladen: " + teile[0].trim() + " -> " + teile[1].trim()); // Ausgabe zur Bestätigung
                } else {
                    System.out.println("Formatfehler in Zeile: " + zeile); // Ausgabe bei Formatfehler
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Gibt eine Fehlermeldung aus, falls beim Lesen ein Fehler auftritt
        }
    }

    // Diese Methode fügt eine neue Frage und Antwort zur HashMap hinzu und speichert die Änderungen in der Datei.
    public void frageHinzufuegen(String frage, String antwort) {
        fragenAntworten.put(frage, antwort); // Fügt die neue Frage und Antwort hinzu
        // Datei mit den neuen Fragen und Antworten überschreiben
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dateipfad))) { // BufferedWriter zum Schreiben in die Datei
            for (Map.Entry<String, String> entry : fragenAntworten.entrySet()) { // Durchläuft alle Einträge der HashMap
                bw.write(entry.getKey() + ";" + entry.getValue()); // Schreibt Frage und Antwort in die Datei
                bw.newLine(); // Fügt eine neue Zeile hinzu
            }
        } catch (IOException e) {
            e.printStackTrace(); // Gibt eine Fehlermeldung aus, falls beim Schreiben ein Fehler auftritt
        }
    }

    // Gibt die gesamte HashMap von Fragen und Antworten zurück
    public Map<String, String> getFragenAntworten() {
        return fragenAntworten;
    }

    // Diese Methode überprüft, ob die gegebene Antwort korrekt ist.
    public boolean checkAntwort(String frage, String antwort) {
        String korrekteAntwort = fragenAntworten.get(frage); // Holt die korrekte Antwort aus der HashMap
        return korrekteAntwort != null && korrekteAntwort.equalsIgnoreCase(antwort.trim()); // Vergleicht die Antwort (ignoriere Groß- und Kleinschreibung)
    }

    // Gibt die Antwort auf eine gegebene Frage zurück.
    public String getAntwort(String frage) {
        return fragenAntworten.get(frage); // Gibt die Antwort für die angegebene Frage zurück
    }
}
