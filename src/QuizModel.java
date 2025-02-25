import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class QuizModel {
    private Map<String, String> fragenAntworten;
    private int richtig;
    private int falsch;
    private String dateipfad;

    public QuizModel(String dateipfad) {
        this.fragenAntworten = new HashMap<>();
        this.richtig = 0;
        this.falsch = 0;
        this.dateipfad = dateipfad;
        ladeFragenAusDatei(dateipfad);
    }

    private void ladeFragenAusDatei(String dateipfad) {
        try (BufferedReader br = new BufferedReader(new FileReader(dateipfad))) {
            String zeile;
            while ((zeile = br.readLine()) != null) {
                if (zeile.trim().isEmpty()) continue; // leere Zeilen überspringen
                String[] teile = zeile.split(";", 2);
                if (teile.length == 2) {
                    fragenAntworten.put(teile[0].trim(), teile[1].trim());
                    System.out.println("Geladen: " + teile[0].trim() + " -> " + teile[1].trim());
                } else {
                    System.out.println("Formatfehler in Zeile: " + zeile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void frageHinzufuegen(String frage, String antwort) {
        fragenAntworten.put(frage, antwort);
        // Datei mit den neuen Fragen und Antworten überschreiben
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dateipfad))) {
            for (Map.Entry<String, String> entry : fragenAntworten.entrySet()) {
                bw.write(entry.getKey() + ";" + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getFragenAntworten() {
        return fragenAntworten;
    }

    public boolean checkAntwort(String frage, String antwort) {
        String korrekteAntwort = fragenAntworten.get(frage);
        return korrekteAntwort != null && korrekteAntwort.equalsIgnoreCase(antwort.trim());
    }

    public String getAntwort(String frage) {
        return fragenAntworten.get(frage);
    }
}
