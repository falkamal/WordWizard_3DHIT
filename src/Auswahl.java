import javax.swing.*;
import java.awt.*;

public class Auswahl extends JFrame {
    private JPanel quizPanel, vokabelPanel, memoryPanel;
    private JLabel quizLabel, vokLabel, memoryLabel;
    private JButton quizButton, vokButton, memoryButton;
    private JMenuBar menuBar;
    private JMenu homeMenu;
    private JMenuItem homeMenuItem;
    private Controller controller;

    public Auswahl(Controller controller) {
        setTitle("WordWizard"); // Titel des Fensters setzen
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiert das Fenster
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fenster schließt beim Schließen
        setLayout(new GridLayout(1, 3)); // Layout mit 3 Spalten
        this.controller = controller;

        // Menübar erstellen
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(getWidth(), 50)); // Größe der Menüleiste festlegen
        homeMenu = new JMenu("Home");

        homeMenuItem = new JMenuItem("Zur Startseite");
        homeMenuItem.setActionCommand("Home");
        homeMenuItem.addActionListener(controller); // ActionListener für das Menü hinzufügen
        homeMenu.add(homeMenuItem);

        menuBar.add(homeMenu); // Menü zu Menüleiste hinzufügen
        setJMenuBar(menuBar); // Menüleiste setzen

        // Quizpanel erstellen
        quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS)); // Vertikales Layout
        quizPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila als Hintergrund

        quizLabel = new JLabel("Quiz");
        quizLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Label zentrieren
        quizLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Schriftgröße und -stil
        quizLabel.setForeground(Color.decode("#F4A300")); // Helles Gelb/Orange für Kontrast
        quizPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#1F0030"), 1)); // Dunklerer Rand

        quizButton = new JButton("Spielen");
        quizButton.setActionCommand("Quiz");
        quizButton.addActionListener(controller); // ActionListener für Button hinzufügen
        quizButton.setBackground(Color.decode("#1E0034")); // Sehr dunkles Lila für Button Hintergrund
        quizButton.setForeground(Color.WHITE); // Weißer Text
        quizButton.setPreferredSize(new Dimension(100, 40)); // Buttongröße festlegen
        quizButton.setMaximumSize(new Dimension(100, 40)); // Maximale Buttongröße
        quizButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Zentrieren des Buttons
        quizButton.setFont(new Font("Arial", Font.BOLD, 17)); // Schriftstil für den Button

        // Hinzufügen der Komponenten zum Quizpanel
        quizPanel.add(Box.createVerticalGlue());
        quizPanel.add(quizLabel);
        quizPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        quizPanel.add(quizButton);
        quizPanel.add(Box.createVerticalGlue());

        // Vokabelpanel erstellen
        vokabelPanel = new JPanel();
        vokabelPanel.setLayout(new BoxLayout(vokabelPanel, BoxLayout.Y_AXIS)); // Vertikales Layout
        vokabelPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila als Hintergrund

        vokLabel = new JLabel("Vokabel");
        vokLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Label zentrieren
        vokLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Schriftgröße und -stil
        vokLabel.setForeground(Color.decode("#F4A300")); // Helles Gelb/Orange für Kontrast
        vokabelPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#1F0030"), 1)); // Dunklerer Rand

        vokButton = new JButton("Spielen");
        vokButton.setActionCommand("Vocab");
        vokButton.addActionListener(controller); // ActionListener für Button hinzufügen
        vokButton.setBackground(Color.decode("#1E0034")); // Sehr dunkles Lila für Button Hintergrund
        vokButton.setForeground(Color.WHITE); // Weißer Text
        vokButton.setPreferredSize(new Dimension(100, 40)); // Buttongröße festlegen
        vokButton.setMaximumSize(new Dimension(100, 40)); // Maximale Buttongröße
        vokButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Zentrieren des Buttons
        vokButton.setFont(new Font("Arial", Font.BOLD, 17)); // Schriftstil für den Button

        // Hinzufügen der Komponenten zum Vokabelpanel
        vokabelPanel.add(Box.createVerticalGlue());
        vokabelPanel.add(vokLabel);
        vokabelPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        vokabelPanel.add(vokButton);
        vokabelPanel.add(Box.createVerticalGlue());

        // Memorypanel erstellen
        memoryPanel = new JPanel();
        memoryPanel.setLayout(new BoxLayout(memoryPanel, BoxLayout.Y_AXIS)); // Vertikales Layout
        memoryPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila als Hintergrund

        memoryLabel = new JLabel("Memory");
        memoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Label zentrieren
        memoryLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Schriftgröße und -stil
        memoryLabel.setForeground(Color.decode("#F4A300")); // Helles Gelb/Orange für Kontrast
        memoryPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#1F0030"), 1)); // Dunklerer Rand

        memoryButton = new JButton("Spielen");
        memoryButton.setBackground(Color.decode("#1E0034")); // Sehr dunkles Lila für Button Hintergrund
        memoryButton.setActionCommand("Memory");
        memoryButton.addActionListener(controller); // ActionListener für Button hinzufügen
        memoryButton.setPreferredSize(new Dimension(100, 40)); // Buttongröße festlegen
        memoryButton.setMaximumSize(new Dimension(100, 40)); // Maximale Buttongröße
        memoryButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Zentrieren des Buttons
        memoryButton.setFont(new Font("Arial", Font.BOLD, 17)); // Schriftstil für den Button
        memoryButton.setForeground(Color.WHITE); // Weißer Text

        // Hinzufügen der Komponenten zum Memorypanel
        memoryPanel.add(Box.createVerticalGlue());
        memoryPanel.add(memoryLabel);
        memoryPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        memoryPanel.add(memoryButton);
        memoryPanel.add(Box.createVerticalGlue());

        // Alle Panels zum Frame hinzufügen
        add(quizPanel);
        add(vokabelPanel);
        add(memoryPanel);
        setVisible(true); // Fenster sichtbar machen
    }
}
