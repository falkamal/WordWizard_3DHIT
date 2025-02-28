// Importieren von notwendigen Bibliotheken für die GUI-Entwicklung und Event-Handling
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz extends JFrame {

    // Deklaration der GUI-Komponenten
    private JMenuBar menuBar;           // Menüleiste für die Anwendung
    private JMenu homeMenu;             // Menü für Home-Option
    private JMenuItem homeMenuItem;     // Menüpunkt für Home
    private JPanel überschriftPanel, quizPanel, ergebnisPanel, buttonPanel;
    private JTextField frage;           // Eingabefeld für die Frage
    private JTextField antwort;         // Eingabefeld für die Antwort des Nutzers
    private JLabel richtigLabel, falschLabel; // Labels zur Anzeige der Anzahl richtiger und falscher Antworten
    private JButton edit, again;        // Buttons für Editieren und zum nächsten Frage
    private Controller controller;      // Steuerung der Benutzerinteraktionen
    private QuizModel model;            // Modell, das die Fragen und Antworten enthält
    private int aktuelleFrageIndex;     // Index der aktuellen Frage
    private int r = 0, f = 0;           // Zähler für richtige und falsche Antworten

    // Konstruktor der Quiz-Klasse
    public Quiz(Controller controller, QuizModel model) {
        this.controller = controller;   // Initialisierung des Controllers
        this.model = model;             // Initialisierung des Modells
        this.aktuelleFrageIndex = 0;    // Start bei der ersten Frage

        // Festlegen des Fenstertitels und der Größe
        setTitle("WordWizard");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maximiert das Fenster
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beendet das Programm, wenn das Fenster geschlossen wird
        setLayout(new BorderLayout()); // Setzt das Layout auf BorderLayout

        // Erstellung der Menüleiste
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(getWidth(), 50)); // Größe der Menüleiste festlegen
        homeMenu = new JMenu("Home");

        // Menüpunkt für die Startseite
        homeMenuItem = new JMenuItem("Zur Startseite");
        homeMenuItem.setActionCommand("Home"); // Setzt den ActionCommand
        homeMenuItem.addActionListener(controller); // Verknüpft den ActionListener mit dem Controller
        homeMenu.add(homeMenuItem); // Fügt den Menüpunkt zum Menü hinzu

        menuBar.add(homeMenu); // Fügt das Menü zur Menüleiste hinzu
        setJMenuBar(menuBar); // Setzt die Menüleiste für das Fenster

        // Panel für die Überschrift
        überschriftPanel = new JPanel();
        überschriftPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila als Hintergrundfarbe
        JLabel überschrift = new JLabel("Quiz-Modus");
        überschrift.setFont(new Font("Arial", Font.BOLD, 30));  // Schriftart und Größe der Überschrift
        überschrift.setForeground(Color.WHITE); // Weiße Schriftfarbe
        überschriftPanel.add(überschrift); // Überschrift zum Panel hinzufügen

        // Panel für die Fragen und Antworten
        quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.X_AXIS)); // Setzt das Layout auf eine horizontale Ausrichtung
        quizPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila für das Quiz-Panel

        // Fragefeld
        frage = new JTextField();
        frage.setEditable(false); // Das Fragefeld ist nur lesbar
        frage.setBackground(Color.WHITE); // Hintergrund weiß
        frage.setFont(new Font("Arial", Font.BOLD, 15)); // Schriftart und Größe
        frage.setHorizontalAlignment(SwingConstants.CENTER); // Text zentrieren
        frage.setPreferredSize(new Dimension(getWidth() / 3, 150)); // Größe des Textfeldes
        frage.setMaximumSize(new Dimension(getWidth() / 3, 150)); // Maximale Größe
        frage.setBorder(BorderFactory.createLineBorder(Color.decode("#2E003E"), 1)); // Randfarbe

        // Antwortfeld
        antwort = new JTextField();
        antwort.setFont(new Font("Arial", Font.BOLD, 15)); // Schriftart und Größe
        antwort.setHorizontalAlignment(SwingConstants.CENTER); // Text zentrieren
        antwort.setPreferredSize(new Dimension(getWidth() / 3, 150)); // Größe des Antwortfeldes
        antwort.setMaximumSize(new Dimension(getWidth() / 3, 150)); // Maximale Größe
        antwort.setBorder(BorderFactory.createLineBorder(Color.decode("#2E003E"), 1)); // Randfarbe
        antwort.setActionCommand("Pruefe"); // Setzt den ActionCommand für das Überprüfen der Antwort
        antwort.addActionListener(controller); // Verknüpft den ActionListener mit dem Controller

        quizPanel.add(Box.createHorizontalGlue()); // Fügt einen horizontalen Abstand hinzu
        quizPanel.add(frage);  // Fügt das Fragefeld hinzu
        quizPanel.add(Box.createRigidArea(new Dimension(80, 0))); // Fügt einen festen Abstand zwischen den Feldern hinzu
        quizPanel.add(antwort);  // Fügt das Antwortfeld hinzu
        quizPanel.add(Box.createHorizontalGlue()); // Fügt einen weiteren horizontalen Abstand hinzu

        // Panel für die Ergebnisse (richtige und falsche Antworten)
        ergebnisPanel = new JPanel();
        ergebnisPanel.setLayout(new BoxLayout(ergebnisPanel, BoxLayout.Y_AXIS)); // Vertikale Ausrichtung der Komponenten
        ergebnisPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila als Hintergrundfarbe
        ergebnisPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 2)); // Größe des Panels
        ergebnisPanel.setMinimumSize(new Dimension(getWidth(), getHeight() / 2)); // Mindestgröße des Panels

        richtigLabel = new JLabel("Richtig: " + r); // Label für die Anzahl der richtigen Antworten
        richtigLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Schriftart und Größe
        richtigLabel.setForeground(Color.WHITE); // Weiße Schriftfarbe
        richtigLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Zentriert das Label
        falschLabel = new JLabel("Falsch: " + f); // Label für die Anzahl der falschen Antworten
        falschLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Schriftart und Größe
        falschLabel.setForeground(Color.WHITE); // Weiße Schriftfarbe
        falschLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Zentriert das Label

        // Button-Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); // Horizontale Anordnung der Buttons
        buttonPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila als Hintergrundfarbe

        // Buttons für Editieren und zum nächsten Frage
        edit = new JButton("Edit");
        edit.setActionCommand("Save"); // ActionCommand für den Edit-Button
        edit.addActionListener(controller); // Verknüpft den ActionListener mit dem Controller
        again = new JButton("Next");
        again.setActionCommand("Next"); // ActionCommand für den Next-Button
        again.addActionListener(controller); // Verknüpft den ActionListener mit dem Controller

        edit.setFont(new Font("Arial", Font.BOLD, 20)); // Schriftart und Größe
        again.setFont(new Font("Arial", Font.BOLD, 20)); // Schriftart und Größe

        buttonPanel.add(edit); // Fügt den Edit-Button hinzu
        buttonPanel.add(again); // Fügt den Next-Button hinzu

        // Hinzufügen der Labels und Buttons zum Ergebnis-Panel
        ergebnisPanel.add(Box.createRigidArea(new Dimension(0, 80))); // Fügt Abstand hinzu
        ergebnisPanel.add(richtigLabel); // Fügt das Label für richtige Antworten hinzu
        ergebnisPanel.add(falschLabel); // Fügt das Label für falsche Antworten hinzu
        ergebnisPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Fügt Abstand hinzu
        ergebnisPanel.add(buttonPanel); // Fügt das Button-Panel hinzu

        // Hinzufügen der Panels zum Hauptfenster
        add(überschriftPanel, BorderLayout.NORTH);
        add(quizPanel, BorderLayout.CENTER);
        add(ergebnisPanel, BorderLayout.SOUTH);

        // Setzt die erste Frage
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                zeigeNaechsteFrage(); // Zeigt die erste Frage an
            }
        });

        setVisible(true); // Macht das Fenster sichtbar
    }

    // Methode zum Anzeigen der nächsten Frage
    private void zeigeNaechsteFrage() {
        if (aktuelleFrageIndex < model.getFragenAntworten().size()) {
            String[] fragenArray = model.getFragenAntworten().keySet().toArray(new String[0]);
            String naechsteFrage = fragenArray[aktuelleFrageIndex]; // Nächste Frage holen
            frage.setText(naechsteFrage); // Frage ins Textfeld setzen
            antwort.setText(""); // Antwortfeld leeren
            aktuelleFrageIndex++; // Index für die nächste Frage erhöhen
        } else {
            frage.setText("Quiz beendet."); // Anzeige wenn das Quiz beendet ist
            antwort.setEnabled(false); // Deaktiviert das Antwortfeld
        }
    }

    // Methode zum Überprüfen der Antwort
    public void pruefeAntwort() {
        String aktuelleFrage = frage.getText();
        String userAntwort = antwort.getText().trim(); // Trim entfernt führende und nachfolgende Leerzeichen

        // Antwortüberprüfung
        boolean richtigGeantwortet = model.checkAntwort(aktuelleFrage, userAntwort);

        if (richtigGeantwortet) {
            r++; // Erhöhe die Anzahl richtiger Antworten
            richtigLabel.setText("Richtig: " + r); // Aktualisiere das Label für richtige Antworten
            JOptionPane.showMessageDialog(this, "Richtige Antwort!", "Ergebnis", JOptionPane.INFORMATION_MESSAGE); // Anzeige einer Nachricht bei richtiger Antwort
        } else {
            f++; // Erhöhe die Anzahl falscher Antworten
            falschLabel.setText("Falsch: " + f); // Aktualisiere das Label für falsche Antworten
            String richtigeAntwort = model.getAntwort(aktuelleFrage); // Hole die richtige Antwort
            JOptionPane.showMessageDialog(this, "Falsche Antwort! Die richtige Antwort ist: " + richtigeAntwort, "Ergebnis", JOptionPane.ERROR_MESSAGE); // Anzeige einer Nachricht bei falscher Antwort
        }

        // Nächste Frage anzeigen
        zeigeNaechsteFrage();
    }
}
