// Import der benötigten Bibliotheken für die GUI und Layout-Verwaltung
import javax.swing.*;
import java.awt.*;

public class Vocab extends JFrame {

    // Controller und Model für die Kommunikation zwischen View und Logik
    private Controller controller;
    private VocabModel vocabModel;

    // GUI-Komponenten für das Menü, Eingabefelder, Ergebnisse und Buttons
    private JMenuBar menuBar;
    private JMenu homeMenu;
    private JMenuItem homeMenuItem;
    private JPanel überschriftPanel, vocabPanel, ergebnisPanel, buttonPanel;
    private JTextField englisch = new JTextField("English Word"); // Voreingestellter Text für das englische Wort
    private JTextField deutsch = new JTextField(); // Eingabefeld für das deutsche Wort (dies wird nicht direkt genutzt)
    private JTextField eingabeFeld = new JTextField(); // Eingabefeld für die Antwort des Benutzers

    // Labels für die Anzeige der korrekten und falschen Antworten
    private JLabel wortLabel, richtig, falsch;

    // Buttons für die Bearbeitung und das Fortsetzen des Tests
    private JButton edit, next;

    // Zähler für die richtigen und falschen Antworten
    private int r = 0, f = 0;

    // Aktuelles Index der Vokabel, die im Test angezeigt wird
    private int currentIndex = 0;

    public Vocab(Controller controller, VocabModel vocabModel) {
        // Konstruktor der Vocab Klasse, der Controller und das VocabModel entgegennimmt
        this.controller = controller;
        this.vocabModel = vocabModel;

        // Fenster-Einstellungen
        setTitle("WordWizard - Vokabeltest");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height); // Maximale Fenstergröße
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiert das Fenster
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beendet die Anwendung, wenn das Fenster geschlossen wird
        setLayout(new BorderLayout());

        // Menüleiste erstellen
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(getWidth(), 50)); // Setzt die Höhe der Menüleiste
        homeMenu = new JMenu("Home");

        homeMenuItem = new JMenuItem("Zur Startseite");
        homeMenuItem.setActionCommand("Home"); // Befehl für den Menüpunkt
        homeMenuItem.addActionListener(controller); // Event-Listener für den Menüpunkt
        homeMenu.add(homeMenuItem);

        menuBar.add(homeMenu); // Fügt das "Home" Menü zur Menüleiste hinzu
        setJMenuBar(menuBar); // Setzt die Menüleiste des Fensters

        // Panel für die Überschrift
        überschriftPanel = new JPanel();
        überschriftPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila als Hintergrundfarbe
        JLabel überschrift = new JLabel("Vokabeltest-Modus");
        überschrift.setFont(new Font("Arial", Font.BOLD, 30)); // Setzt die Schriftart und Größe der Überschrift
        überschrift.setForeground(Color.WHITE); // Weißer Text
        überschriftPanel.add(überschrift);

        // Panel für das Vokabeltest-Eingabefeld
        vocabPanel = new JPanel();
        vocabPanel.setLayout(new GridBagLayout());
        vocabPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila als Hintergrundfarbe
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Abstand für die Komponenten

        wortLabel = new JLabel("Englisches Wort");
        wortLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Setzt Schriftart und -größe
        wortLabel.setForeground(Color.WHITE); // Weißer Text
        vocabPanel.add(wortLabel, gbc);

        gbc.gridy++;
        eingabeFeld = new JTextField(20); // Textfeld für die Benutzereingabe
        eingabeFeld.setFont(new Font("Arial", Font.PLAIN, 20)); // Setzt die Schriftgröße des Eingabefeldes
        vocabPanel.add(eingabeFeld, gbc);

        // Panel für die Anzeige der Ergebnisse
        ergebnisPanel = new JPanel();
        ergebnisPanel.setLayout(new BoxLayout(ergebnisPanel, BoxLayout.Y_AXIS));
        ergebnisPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila
        ergebnisPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 2)); // Größe des Panels
        ergebnisPanel.setMinimumSize(new Dimension(getWidth(), getHeight() / 2));

        richtig = new JLabel("Richtig: " + r);
        richtig.setFont(new Font("Arial", Font.BOLD, 20));
        richtig.setForeground(Color.WHITE); // Weißer Text
        richtig.setAlignmentX(Component.CENTER_ALIGNMENT);

        falsch = new JLabel("Falsch: " + f);
        falsch.setFont(new Font("Arial", Font.BOLD, 20));
        falsch.setForeground(Color.WHITE); // Weißer Text
        falsch.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel für die Buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila

        // Edit Button (zum Bearbeiten von Vokabeln)
        edit = new JButton("Edit");
        edit.setActionCommand("Save");
        edit.addActionListener(controller); // Event-Listener für den Edit-Button
        edit.setFont(new Font("Arial", Font.BOLD, 20));
        edit.setBackground(Color.decode("#4B0082")); // Lila Button Hintergrund
        edit.setForeground(Color.WHITE); // Weiße Schrift
        edit.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Next Button (für den nächsten Test-Schritt)
        next = new JButton("Next");
        next.setFont(new Font("Arial", Font.BOLD, 20));
        next.setBackground(Color.decode("#4B0082"));
        next.setForeground(Color.WHITE); // Weiße Schrift
        next.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(edit);
        buttonPanel.add(next); // Fügt die Buttons zum Panel hinzu

        // Fügt das Ergebnispanel zum Hauptpanel hinzu
        ergebnisPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        ergebnisPanel.add(richtig);
        ergebnisPanel.add(falsch);
        ergebnisPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        ergebnisPanel.add(buttonPanel);

        // Fügt alle Panels zum JFrame hinzu
        add(überschriftPanel, BorderLayout.NORTH);
        add(vocabPanel, BorderLayout.CENTER);
        add(ergebnisPanel, BorderLayout.SOUTH);
        setVisible(true); // Sichtbar machen des Fensters

        loadNextWord(); // Lädt das erste Wort
        addNextButtonListener(); // Fügt den Listener für den "Next"-Button hinzu
    }

    // Methode zum Aktualisieren der Anzahl richtiger Antworten
    public void setRichtig(int r) {
        this.r = r;
        richtig.setText("Richtig: " + r); // Setzt den Text für die richtige Antwort
    }

    // Methode zum Aktualisieren der Anzahl falscher Antworten
    public void setFalsch(int f) {
        this.f = f;
        falsch.setText("Falsch: " + f); // Setzt den Text für die falsche Antwort
    }

    // Lädt das nächste Wort aus dem Vokabelmodell und zeigt es im Interface an
    public void loadNextWord() {
        if (currentIndex < vocabModel.getVocabEntries().size()) {
            VocabModel.VocabEntry currentEntry = vocabModel.getVocabEntries().get(currentIndex);
            wortLabel.setText(currentEntry.getEnglishWord()); // Zeigt das englische Wort an
            deutsch.setText(currentEntry.getGermanWord()); // Zeigt das deutsche Wort an
            currentIndex++; // Erhöht den Index für das nächste Wort
        } else {
            // Keine weiteren Vokabeln, Test beenden
            JOptionPane.showMessageDialog(this, "Test abgeschlossen!");
        }
    }

    // Überprüft die Antwort des Benutzers und aktualisiert die Ergebnisse
    public void checkAnswer() {
        String userAnswer = eingabeFeld.getText().trim().toLowerCase();
        String correctAnswer = vocabModel.getVocabEntries().get(currentIndex - 1).getGermanWord().toLowerCase();

        if (userAnswer.equals(correctAnswer)) {
            setRichtig(r + 1); // Wenn richtig, erhöhe die Anzahl der richtigen Antworten
        } else {
            setFalsch(f + 1); // Wenn falsch, erhöhe die Anzahl der falschen Antworten
        }

        loadNextWord(); // Lade das nächste Wort
        eingabeFeld.setText(""); // Leere das Eingabefeld
    }

    // Fügt den Listener für den "Next"-Button hinzu
    public void addNextButtonListener() {
        next.addActionListener(e -> checkAnswer());
    }
}
