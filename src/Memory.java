import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Memory extends JFrame {

    private JMenuBar menuBar; // Menüleiste, die die Navigation ermöglicht
    private JMenu homeMenu; // Menü für den Heimbereich
    private JMenuItem homeMenuItem; // Menüeintrag, um zur Startseite zurückzukehren
    private JPanel überschriftPanel, vocabPanel, ergebnisPanel, buttonPanel; // Panels für verschiedene Teile der Benutzeroberfläche
    private JButton next; // Button für den Übergang zur nächsten Runde
    private Controller controller; // Controller, der die Logik des Spiels steuert
    private int round = 1; // Aktuelle Runde, beginnt bei 1
    private final int maxRounds = 5; // Maximale Anzahl der Runden
    private ArrayList<JButton> buttons; // Liste von Buttons, die die Vokabelkarten darstellen
    private Map<JButton, String> wordMap; // Zuordnung der Buttons zu ihren Vokabeln
    private JButton firstSelected = null; // Die erste ausgewählte Schaltfläche (für das Paar)
    private boolean lockSelection = false; // Flag, das verhindert, dass mehrere Karten gleichzeitig ausgewählt werden

    // Konstruktor für das Memory-Spiel
    public Memory(Controller controller) {
        this.controller = controller;
        setTitle("WordWizard - Memory"); // Fenster Titel festlegen
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fenster auf maximaler Größe anzeigen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Das Programm schließt sich, wenn das Fenster geschlossen wird
        setLayout(new BorderLayout()); // Layout des Fensters festlegen

        // Hintergrundfarbe für das gesamte Fenster festlegen
        getContentPane().setBackground(new Color(46, 0, 62)); // Lila Hintergrund

        // Menüleiste erstellen
        menuBar = new JMenuBar();
        homeMenu = new JMenu("Home"); // Menü "Home" erstellen
        homeMenuItem = new JMenuItem("Zur Startseite"); // Menüeintrag "Zur Startseite"
        homeMenuItem.setActionCommand("Home"); // Befehl für den Menüeintrag festlegen
        homeMenuItem.addActionListener(controller); // ActionListener für den Menüeintrag hinzufügen
        homeMenu.add(homeMenuItem); // Menüeintrag zum Menü hinzufügen
        menuBar.add(homeMenu); // Menü zur Menüleiste hinzufügen
        setJMenuBar(menuBar); // Menüleiste im Fenster setzen

        // Panel für Überschrift erstellen
        überschriftPanel = new JPanel();
        JLabel überschrift = new JLabel("Memory-Modus"); // Überschrift für den Memory-Modus
        überschrift.setFont(new Font("Arial", Font.BOLD, 24)); // Schriftart und -größe für die Überschrift
        überschrift.setForeground(Color.WHITE); // Schriftfarbe auf Weiß setzen
        überschriftPanel.add(überschrift); // Überschrift zum Panel hinzufügen
        überschriftPanel.setBackground(new Color(46, 0, 62)); // Hintergrundfarbe für das Überschrift-Panel

        // Panel für die Vokabelkarten erstellen
        vocabPanel = new JPanel(new GridLayout(4, 4, 10, 10)); // Ein Gitterlayout für die Karten erstellen
        vocabPanel.setBackground(new Color(46, 0, 62)); // Hintergrundfarbe für das Vokabelpanel

        initializeGame(); // Das Spiel initialisieren (Vokabeln und Buttons erstellen)

        // Panel für die Ergebnisse erstellen
        ergebnisPanel = new JPanel();
        ergebnisPanel.setLayout(new GridBagLayout()); // Layout für das Ergebnis-Panel festlegen
        GridBagConstraints gbc = new GridBagConstraints(); // Gitter-Bag-Constraints für das Layout
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE; // Die Position der Komponenten festlegen
        gbc.anchor = GridBagConstraints.CENTER; // Die Komponenten zentrieren

        next = new JButton("Next"); // Button für die nächste Runde erstellen
        next.setFont(new Font("Arial", Font.BOLD, 16)); // Schriftart und -größe für den Button festlegen
        next.setForeground(Color.WHITE); // Textfarbe auf Weiß setzen
        next.setBackground(new Color(128, 0, 128)); // Hintergrundfarbe des Buttons
        next.addActionListener(e -> nextRound()); // ActionListener für den Button hinzufügen, der zur nächsten Runde wechselt

        ergebnisPanel.add(next, gbc); // Den Button zum Ergebnis-Panel hinzufügen
        ergebnisPanel.setBackground(new Color(46, 0, 62)); // Hintergrundfarbe für das Ergebnis-Panel

        // Alle Panels zum JFrame hinzufügen
        add(überschriftPanel, BorderLayout.NORTH); // Überschrift-Panel oben hinzufügen
        add(vocabPanel, BorderLayout.CENTER); // Vokabel-Panel in der Mitte hinzufügen
        add(ergebnisPanel, BorderLayout.SOUTH); // Ergebnis-Panel unten hinzufügen
        setVisible(true); // Das Fenster sichtbar machen
    }

    // Methode, die das Spiel initialisiert und die Vokabelkarten anzeigt
    private void initializeGame() {
        buttons = new ArrayList<>(); // Neue Liste für die Buttons erstellen
        wordMap = new HashMap<>(); // Neue HashMap für die Zuordnung von Buttons und Wörtern erstellen
        vocabPanel.removeAll(); // Alle bisherigen Karten entfernen

        // Vokabelpaare (deutsch-englisch) erstellen
        String[][] wordPairs = {
                {"Haus", "House"}, {"Baum", "Tree"}, {"Auto", "Car"},
                {"Hund", "Dog"}, {"Katze", "Cat"}, {"Buch", "Book"},
                {"Stuhl", "Chair"}, {"Wasser", "Water"}
        };

        // Eine Liste der Wörter vorbereiten und mischen
        ArrayList<String> words = new ArrayList<>();
        for (String[] pair : wordPairs) {
            words.add(pair[0]); // Deutsche Wörter hinzufügen
            words.add(pair[1]); // Englische Wörter hinzufügen
        }

        Collections.shuffle(words); // Die Wörter mischen

        // Buttons für jedes Wort erstellen
        for (String word : words) {
            JButton button = new JButton("?");
            button.setFont(new Font("Arial", Font.BOLD, 16)); // Schriftart und -größe für den Button
            button.setForeground(Color.WHITE); // Textfarbe auf Weiß setzen
            button.setBackground(new Color(128, 0, 128)); // Hintergrundfarbe des Buttons
            button.setPreferredSize(new Dimension(80, 80)); // Die Größe der Buttons anpassen
            button.addActionListener(e -> checkSelection(button)); // ActionListener hinzufügen, der die Auswahl überprüft
            buttons.add(button); // Den Button zur Liste hinzufügen
            wordMap.put(button, word); // Den Button und das zugehörige Wort in der Map speichern
            vocabPanel.add(button); // Den Button zum Vokabel-Panel hinzufügen
        }

        vocabPanel.revalidate(); // Das Panel neu validieren, um die Änderungen anzuzeigen
        vocabPanel.repaint(); // Das Panel neu zeichnen
    }

    // Methode, die prüft, ob zwei ausgewählte Karten ein Paar bilden
    private void checkSelection(JButton button) {
        if (lockSelection || button.getText() != "?") return; // Verhindern, dass mehr als eine Karte gleichzeitig ausgewählt wird

        String word = wordMap.get(button); // Das Wort auf der aktuellen Karte holen
        button.setText(word); // Den Text des Buttons auf das Wort setzen

        // Wenn noch keine Karte ausgewählt wurde, wird die erste Karte gesetzt
        if (firstSelected == null) {
            firstSelected = button;
        } else {
            lockSelection = true; // Auswahl sperren, um eine zweite Karte auszuwählen
            JButton secondSelected = button; // Die zweite ausgewählte Karte speichern

            // Wenn die beiden Karten übereinstimmen
            if (isMatchingPair(firstSelected, secondSelected)) {
                firstSelected.setEnabled(false); // Beide Karten deaktivieren, wenn sie übereinstimmen
                secondSelected.setEnabled(false);
                firstSelected.setBackground(Color.GREEN); // Hintergrund der Karten auf grün setzen
                secondSelected.setBackground(Color.GREEN);
                firstSelected = null; // Die erste Auswahl zurücksetzen
                lockSelection = false; // Auswahl freigeben
            } else {
                // Wenn die Karten nicht übereinstimmen, diese nach kurzer Zeit wieder zurückdrehen
                Timer timer = new Timer(800, e -> {
                    if (firstSelected != null && secondSelected != null) {
                        firstSelected.setText("?");
                        secondSelected.setText("?");
                    }
                    firstSelected = null;
                    lockSelection = false;
                });
                timer.setRepeats(false); // Timer nur einmal ausführen
                timer.start(); // Timer starten
            }
        }
    }

    // Methode, die überprüft, ob zwei Karten ein passendes Paar sind
    private boolean isMatchingPair(JButton first, JButton second) {
        String word1 = wordMap.get(first);
        String word2 = wordMap.get(second);
        // Überprüfung der Wortpaare
        for (String[] pair : new String[][] {
                {"Haus", "House"}, {"Baum", "Tree"}, {"Auto", "Car"},
                {"Hund", "Dog"}, {"Katze", "Cat"}, {"Buch", "Book"},
                {"Stuhl", "Chair"}, {"Wasser", "Water"}}) {
            if ((word1.equals(pair[0]) && word2.equals(pair[1])) ||
                    (word1.equals(pair[1]) && word2.equals(pair[0]))) {
                return true;
            }
        }
        return false; // Keine Übereinstimmung gefunden
    }

    // Methode, die zur nächsten Runde übergeht
    private void nextRound() {
        if (round < maxRounds) {
            round++; // Runde erhöhen
            initializeGame(); // Das Spiel für die nächste Runde initialisieren
        }
    }
}
