import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private JFrame frame; // Das Hauptfenster, das gewechselt wird
    private QuizModel quizModel; // Das Modell für das Quiz
    private VocabModel vocabModel; // Das Modell für das Vokabeln

    // Konstruktor des Controllers, der das Hauptfenster und die Modelle initialisiert
    public Controller() {
        frame = new Home(this); // Wir starten mit dem Home-Fenster
        quizModel = new QuizModel("./QuizFragen.txt"); // Laden der Quizfragen
        vocabModel = new VocabModel("./VokabelFragen.txt"); // Laden der Vokabelfragen
    }

    // Die Methode, die die Benutzeraktionen (Button-Klicks) verarbeitet
    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand(); // Die Aktion des gedrückten Buttons

        // Wenn die Aktion "Home" ist, wechseln wir zum Home-Fenster
        if(ac.equals("Home")) {
            frame.setVisible(false); // Das aktuelle Fenster wird ausgeblendet
            frame = new Home(this); // Ein neues Home-Fenster wird erstellt
            frame.setVisible(true); // Das neue Fenster wird sichtbar gemacht
        }
        // Wenn die Aktion "Auswahl" ist, wechseln wir zum Auswahl-Fenster
        else if(ac.equals("Auswahl")) {
            frame.setVisible(false); // Das aktuelle Fenster wird ausgeblendet
            frame = new Auswahl(this); // Ein neues Auswahl-Fenster wird erstellt
            frame.setVisible(true); // Das neue Fenster wird sichtbar gemacht
        }
        // Wenn die Aktion "Beenden" ist, beenden wir die Anwendung
        else if(ac.equals("Beenden")) {
            System.exit(0); // Die Anwendung wird beendet
        }
        // Wenn die Aktion "Quiz" ist, wechseln wir zum Quiz-Fenster
        else if(ac.equals("Quiz")) {
            frame.setVisible(false); // Das aktuelle Fenster wird ausgeblendet
            frame = new Quiz(this, quizModel); // Ein neues Quiz-Fenster wird erstellt
            frame.setVisible(true); // Das neue Fenster wird sichtbar gemacht
        }
        // Wenn die Aktion "Vocab" ist, wechseln wir zum Vokabel-Fenster
        else if(ac.equals("Vocab")) {
            frame.setVisible(false); // Das aktuelle Fenster wird ausgeblendet
            frame = new Vocab(this, vocabModel); // Ein neues Vokabel-Fenster wird erstellt
            frame.setVisible(true); // Das neue Fenster wird sichtbar gemacht
        }
        // Wenn die Aktion "Memory" ist, wechseln wir zum Memory-Fenster
        else if(ac.equals("Memory")) {
            frame.setVisible(false); // Das aktuelle Fenster wird ausgeblendet
            frame = new Memory(this); // Ein neues Memory-Fenster wird erstellt
            frame.setVisible(true); // Das neue Fenster wird sichtbar gemacht
        }
        // Wenn die Aktion "Save" ist, wechseln wir zum Speichern/ Bearbeiten-Fenster
        else if(ac.equals("Save")) {
            frame.setVisible(false); // Das aktuelle Fenster wird ausgeblendet
            frame = new SaveEdit(this); // Ein neues Speichern/ Bearbeiten-Fenster wird erstellt
            frame.setVisible(true); // Das neue Fenster wird sichtbar gemacht
        }
        // Wenn die Aktion "Next" oder "Pruefe" ist und wir uns im Quiz-Fenster befinden, prüfen wir die Antwort
        else if((ac.equals("Next") || ac.equals("Pruefe")) && frame instanceof Quiz) {
            ((Quiz) frame).pruefeAntwort(); // Wir überprüfen die Antwort der aktuellen Frage
        }
    }

    // Die Hauptmethode, um die Anwendung zu starten
    public static void main(String[] args) {
        Controller app = new Controller(); // Erstellen und Starten der Anwendung
    }
}
