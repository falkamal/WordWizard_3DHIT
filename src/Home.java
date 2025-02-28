import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    private JMenuBar menuBar; // Die Menüleiste für das Hauptmenü
    private JMenu homeMenu; // Das Home-Menü
    private JPanel auswahlpanel; // Das Panel, das die Auswahl-Buttons enthält
    JLabel titel; // Das Titel-Label
    JButton auswahl, save, close; // Die Buttons für Auswahl, Speichern und Beenden
    JTextField modus; // Das Textfeld für den Modus (falls nötig)
    Controller controller; // Der Controller, der die Ereignisse verarbeitet

    // Konstruktor für das Home-Fenster, der die Benutzeroberfläche initialisiert
    public Home(Controller controller) {
        this.controller = controller; // Der Controller wird gesetzt
        setTitle("WordWizard"); // Der Titel des Fensters
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Das Fenster wird maximiert
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Das Fenster wird geschlossen, wenn das Programm beendet wird
        setLayout(new BorderLayout()); // BorderLayout für die Anordnung der Komponenten

        // Menüleiste erstellen
        menuBar = new JMenuBar(); // Neue Menüleiste erstellen
        menuBar.setPreferredSize(new Dimension(getWidth(), 50)); // Die Höhe der Menüleiste wird festgelegt
        homeMenu = new JMenu("Home"); // Das Home-Menü
        homeMenu.setActionCommand("Home"); // Die Aktion für das Home-Menü
        homeMenu.addActionListener(controller); // Der Controller wird als Listener hinzugefügt
        menuBar.add(homeMenu); // Das Home-Menü wird zur Menüleiste hinzugefügt
        setJMenuBar(menuBar); // Die Menüleiste wird zum Fenster hinzugefügt

        // Panel für die Auswahl-Buttons
        auswahlpanel = new JPanel();
        auswahlpanel.setLayout(new BoxLayout(auswahlpanel, BoxLayout.Y_AXIS)); // Vertikale Anordnung der Buttons
        auswahlpanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila für den Hintergrund

        // Titel-Label für das Fenster
        titel = new JLabel("Welcome to WordWizard", SwingConstants.CENTER);
        titel.setFont(new Font("Arial", Font.BOLD, 40)); // Schriftart und Größe für den Titel
        titel.setForeground(Color.decode("#FFFFFF")); // Weißer Text für den Titel
        titel.setAlignmentX(Component.CENTER_ALIGNMENT); // Der Titel wird zentriert

        // Button für die Auswahl-Seite
        auswahl = new JButton("AUSWAHL");
        auswahl.setActionCommand("Auswahl"); // Die Aktion für diesen Button
        auswahl.addActionListener(controller); // Der Controller wird als Listener hinzugefügt
        auswahl.setFont(new Font("Arial", Font.BOLD, 20)); // Schriftart und Größe für den Button
        auswahl.setBackground(Color.decode("#4B0082")); // Lila Hintergrundfarbe für den Button
        auswahl.setForeground(Color.WHITE); // Weiße Schriftfarbe für den Button
        auswahl.setPreferredSize(new Dimension(400, 100)); // Die Größe des Buttons
        auswahl.setMaximumSize(new Dimension(400, 100)); // Maximale Größe des Buttons
        auswahl.setAlignmentX(Component.CENTER_ALIGNMENT); // Der Button wird zentriert

        // Button für Speichern und Bearbeiten
        save = new JButton("<html><center>SPEICHERN/<br>BEARBEITEN</center></html>");
        save.setActionCommand("Save"); // Die Aktion für diesen Button
        save.addActionListener(controller); // Der Controller wird als Listener hinzugefügt
        save.setFont(new Font("Arial", Font.BOLD, 20)); // Schriftart und Größe für den Button
        save.setBackground(Color.decode("#4B0082")); // Lila Hintergrundfarbe für den Button
        save.setForeground(Color.WHITE); // Weiße Schriftfarbe für den Button
        save.setPreferredSize(new Dimension(400, 100)); // Die Größe des Buttons
        save.setMaximumSize(new Dimension(400, 100)); // Maximale Größe des Buttons
        save.setAlignmentX(Component.CENTER_ALIGNMENT); // Der Button wird zentriert

        // Button für das Beenden der Anwendung
        close = new JButton("BEENDEN");
        close.setActionCommand("Beenden"); // Die Aktion für diesen Button
        close.addActionListener(controller); // Der Controller wird als Listener hinzugefügt
        close.setFont(new Font("Arial", Font.BOLD, 20)); // Schriftart und Größe für den Button
        close.setBackground(Color.decode("#4B0082")); // Lila Hintergrundfarbe für den Button
        close.setForeground(Color.WHITE); // Weiße Schriftfarbe für den Button
        close.setPreferredSize(new Dimension(400, 100)); // Die Größe des Buttons
        close.setMaximumSize(new Dimension(400, 100)); // Maximale Größe des Buttons
        close.setAlignmentX(Component.CENTER_ALIGNMENT); // Der Button wird zentriert

        // Abstand und Anordnung der Elemente im Panel
        auswahlpanel.add(Box.createRigidArea(new Dimension(0, 100))); // Abstand zum nächsten Element
        auswahlpanel.add(titel); // Das Titel-Label wird hinzugefügt
        auswahlpanel.add(Box.createRigidArea(new Dimension(0, 50))); // Abstand zum nächsten Element
        auswahlpanel.add(Box.createRigidArea(new Dimension(0, 20))); // Abstand zwischen den Buttons
        auswahlpanel.add(auswahl); // Der Auswahl-Button wird hinzugefügt
        auswahlpanel.add(Box.createRigidArea(new Dimension(0, 20))); // Abstand zwischen den Buttons
        auswahlpanel.add(save); // Der Speichern-Button wird hinzugefügt
        auswahlpanel.add(Box.createRigidArea(new Dimension(0, 20))); // Abstand zwischen den Buttons
        auswahlpanel.add(close); // Der Beenden-Button wird hinzugefügt
        add(auswahlpanel, BorderLayout.CENTER); // Das Panel wird zum Zentrum des Fensters hinzugefügt
        setVisible(true); // Das Fenster wird sichtbar gemacht
    }
}
