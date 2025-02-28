import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class SaveEdit extends JFrame {
    // Initialisierung der GUI-Komponenten und Variablen
    private DefaultListModel<Question> questionListModel; // Modell für die Liste der Fragen
    private JList<Question> questionList; // Anzeige der Fragenliste
    private JTextArea questionArea, answerArea; // Textfelder für Frage und Antwort
    private JButton addButton, saveButton; // Buttons zum Hinzufügen und Speichern
    private JComboBox<String> modeSelector; // Auswahl des Spielmodus (Quiz oder Vokabel)
    private String currentFileName; // Der Dateiname zum Speichern/ Laden der Fragen
    private JMenuBar menuBar; // Menüleiste
    private JMenuItem homeMenuItem; // Menüpunkt "Zur Startseite"
    private JMenu homeMenu; // Menü "Home"

    // Konstruktor
    public SaveEdit(Controller controller) {
        setTitle("WordWizard - Fragen Editor"); // Fenstertitel setzen
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fenster maximieren
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fenster schließen beim Beenden
        setLayout(new BorderLayout()); // Layout des Fensters

        // Menü erstellen und konfigurieren
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(getWidth(), 50)); // Menüleiste an die Fensterbreite anpassen
        homeMenu = new JMenu("Home");

        homeMenuItem = new JMenuItem("Zur Startseite"); // Menüpunkt erstellen
        homeMenuItem.setActionCommand("Home"); // Befehl für ActionListener festlegen
        homeMenuItem.addActionListener(controller); // Controller als Listener hinzufügen
        homeMenu.add(homeMenuItem); // Menüpunkt zum Menü hinzufügen

        menuBar.add(homeMenu); // Menüleiste zum Fenster hinzufügen
        setJMenuBar(menuBar); // Menüleiste im Fenster anzeigen

        // Fragenliste erstellen
        questionListModel = new DefaultListModel<>();
        questionList = new JList<>(questionListModel);
        questionList.addListSelectionListener(e -> showSelectedQuestion()); // Listener für die Auswahl von Fragen
        add(new JScrollPane(questionList), BorderLayout.WEST); // Fragenliste auf der linken Seite hinzufügen

        // Bereich für Frage- und Antworttexte erstellen
        JPanel editPanel = new JPanel(new GridLayout(4, 1)); // GridLayout für Textfelder
        questionArea = new JTextArea(); // Textfeld für Fragen
        answerArea = new JTextArea(); // Textfeld für Antworten
        editPanel.add(new JLabel("Frage:"));
        editPanel.add(new JScrollPane(questionArea)); // Frage in Scrollpane einbetten
        editPanel.add(new JLabel("Antwort:"));
        editPanel.add(new JScrollPane(answerArea)); // Antwort in Scrollpane einbetten
        add(editPanel, BorderLayout.CENTER); // Zum Fenster hinzufügen

        // Kontrollpanel für Buttons und Auswahl
        JPanel controlPanel = new JPanel();
        String[] modes = {"Quiz", "Vokabel"}; // Auswahlmöglichkeiten für Spielmodus
        modeSelector = new JComboBox<>(modes); // Dropdown-Menü für den Spielmodus
        modeSelector.addActionListener(e -> loadQuestions()); // Listener für Moduswechsel

        addButton = new JButton("Frage hinzufügen"); // Button zum Hinzufügen einer Frage
        addButton.addActionListener(e -> addQuestion()); // Listener für Hinzufügen

        saveButton = new JButton("Speichern"); // Button zum Speichern der Fragen
        saveButton.addActionListener(e -> saveQuestions()); // Listener für Speichern

        controlPanel.add(new JLabel("Spielmodus:"));
        controlPanel.add(modeSelector); // Modus-Auswahl hinzufügen
        controlPanel.add(addButton); // Hinzufügen-Button hinzufügen
        controlPanel.add(saveButton); // Speichern-Button hinzufügen
        add(controlPanel, BorderLayout.SOUTH); // Zum unteren Bereich des Fensters hinzufügen

        loadQuestions(); // Fragen laden
        setVisible(true); // Fenster sichtbar machen
    }

    // Methode zur Anzeige der ausgewählten Frage und Antwort
    private void showSelectedQuestion() {
        Question selected = questionList.getSelectedValue(); // Ausgewählte Frage abrufen
        if (selected != null) {
            questionArea.setText(selected.getQuestion()); // Frage im Textfeld anzeigen
            answerArea.setText(selected.getAnswer()); // Antwort im Textfeld anzeigen
        }
    }

    // Methode zum Hinzufügen einer neuen Frage
    private void addQuestion() {
        String questionText = questionArea.getText().trim(); // Frage-Text abrufen und trimmen
        String answerText = answerArea.getText().trim(); // Antwort-Text abrufen und trimmen
        if (!questionText.isEmpty() && !answerText.isEmpty()) {
            questionListModel.addElement(new Question(questionText, answerText)); // Neue Frage und Antwort zur Liste hinzufügen
            questionArea.setText(""); // Textfelder leeren
            answerArea.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Bitte eine Frage und Antwort eingeben.", "Fehlende Eingabe", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Methode zum Speichern der Fragen in eine Datei
    private void saveQuestions() {
        setFileName(); // Dateinamen je nach Modus festlegen
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFileName))) { // Writer zum Schreiben in Datei
            for (int i = 0; i < questionListModel.size(); i++) { // Jede Frage in der Liste speichern
                Question q = questionListModel.get(i);
                writer.write(q.getQuestion() + ";" + q.getAnswer()); // Frage und Antwort trennen durch ";"
                writer.newLine(); // Neue Zeile für die nächste Frage
            }
            JOptionPane.showMessageDialog(this, "Fragen gespeichert in " + currentFileName); // Erfolgreiche Speicherung melden
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Fehler beim Speichern!", "Fehler", JOptionPane.ERROR_MESSAGE); // Fehler melden
        }
    }

    // Methode zum Laden der Fragen aus einer Datei
    private void loadQuestions() {
        setFileName(); // Dateinamen je nach Modus festlegen
        questionListModel.clear(); // Alte Fragen aus der Liste entfernen
        try (BufferedReader reader = new BufferedReader(new FileReader(currentFileName))) { // Reader zum Lesen der Datei
            String line;
            while ((line = reader.readLine()) != null) { // Zeilenweise lesen
                String[] parts = line.split(";"); // Zeile in Frage und Antwort aufteilen
                if (parts.length == 2) {
                    questionListModel.addElement(new Question(parts[0], parts[1])); // Frage und Antwort zur Liste hinzufügen
                }
            }
        } catch (IOException e) {
            System.out.println("Keine gespeicherten Fragen gefunden für " + currentFileName); // Fehlerbehandlung
        }
    }

    // Methode zur Festlegung des Dateinamens basierend auf dem ausgewählten Modus
    private void setFileName() {
        switch ((String) modeSelector.getSelectedItem()) {
            case "Vokabel":
                currentFileName = "VokabelFragen.txt"; // Vokabel-Dateiname festlegen
                break;
            default:
                currentFileName = "QuizFragen.txt"; // Standard-Quiz-Dateiname festlegen
        }
    }
}

// Hilfsklasse für Frage und Antwort
class Question {
    private String question; // Frage
    private String answer; // Antwort

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question; // Frage zurückgeben
    }

    public String getAnswer() {
        return answer; // Antwort zurückgeben
    }

    @Override
    public String toString() {
        return question; // Frage als String zurückgeben
    }
}
