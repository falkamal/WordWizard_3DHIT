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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dateipfad))) {
            bw.write(frage + ";" + antwort);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAntwort(String frage) {
        return fragenAntworten.get(frage);
    }

    public int getRichtig() {
        return richtig;
    }

    public void increaseRichtig() {
        this.richtig++;
    }

    public int getFalsch() {
        return falsch;
    }

    public void increaseFalsch() {
        this.falsch++;
    }

    public boolean checkAntwort(String frage, String userAntwort) {
        String korrekteAntwort = fragenAntworten.get(frage);
        return korrekteAntwort != null && korrekteAntwort.equalsIgnoreCase(userAntwort.trim());
    }
    public Map<String, String> getFragenAntworten() {
        return fragenAntworten;
    }
}
