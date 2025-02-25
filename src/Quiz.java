import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz extends JFrame {

    private JMenuBar menuBar;
    private JMenu homeMenu;
    private JMenuItem homeMenuItem;
    private JPanel überschriftPanel, quizPanel, ergebnisPanel, buttonPanel;
    private JTextField frage;
    private JTextField antwort;
    private JLabel richtigLabel, falschLabel;
    private JButton edit, again;
    private Controller controller;
    private QuizModel model;
    private int aktuelleFrageIndex;
    private int r = 0, f = 0;

    public Quiz(Controller controller, QuizModel model) {
        this.controller = controller;
        this.model = model;
        this.aktuelleFrageIndex = 0; // Startet bei der ersten Frage

        setTitle("WordWizard");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menü
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(getWidth(), 50));
        homeMenu = new JMenu("Home");

        homeMenuItem = new JMenuItem("Zur Startseite");
        homeMenuItem.setActionCommand("Home");
        homeMenuItem.addActionListener(controller);
        homeMenu.add(homeMenuItem);

        menuBar.add(homeMenu);
        setJMenuBar(menuBar);

        überschriftPanel = new JPanel();
        überschriftPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila für das Überschrift-Panel
        JLabel überschrift = new JLabel("Quiz-Modus");
        überschrift.setFont(new Font("Arial", Font.BOLD, 30));
        überschrift.setForeground(Color.WHITE); // Weiße Schrift auf dunklem Hintergrund
        überschriftPanel.add(überschrift);

        // Eingabefelder
        quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.X_AXIS));
        quizPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila für das Quiz Panel

        frage = new JTextField();
        frage.setEditable(false);
        frage.setBackground(Color.WHITE);
        frage.setFont(new Font("Arial", Font.BOLD, 15));
        frage.setHorizontalAlignment(SwingConstants.CENTER);
        frage.setPreferredSize(new Dimension(getWidth() / 3, 150));
        frage.setMaximumSize(new Dimension(getWidth() / 3, 150));
        frage.setBorder(BorderFactory.createLineBorder(Color.decode("#2E003E"), 1)); // Dunkles Lila für den Rand

        antwort = new JTextField();
        antwort.setFont(new Font("Arial", Font.BOLD, 15));
        antwort.setHorizontalAlignment(SwingConstants.CENTER);
        antwort.setPreferredSize(new Dimension(getWidth() / 3, 150));
        antwort.setMaximumSize(new Dimension(getWidth() / 3, 150));
        antwort.setBorder(BorderFactory.createLineBorder(Color.decode("#2E003E"), 1));
        antwort.setActionCommand("Pruefe");
        antwort.addActionListener(controller);

        quizPanel.add(Box.createHorizontalGlue());
        quizPanel.add(frage);
        quizPanel.add(Box.createRigidArea(new Dimension(80, 0)));
        quizPanel.add(antwort);
        quizPanel.add(Box.createHorizontalGlue());

        // Ergebnis
        ergebnisPanel = new JPanel();
        ergebnisPanel.setLayout(new BoxLayout(ergebnisPanel, BoxLayout.Y_AXIS));
        ergebnisPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila für das Ergebnis-Panel
        ergebnisPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 2));
        ergebnisPanel.setMinimumSize(new Dimension(getWidth(), getHeight() / 2));

        richtigLabel = new JLabel("Richtig: " + r);
        richtigLabel.setFont(new Font("Arial", Font.BOLD, 20));
        richtigLabel.setForeground(Color.WHITE); // Weiße Schrift für "Richtig"
        richtigLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        falschLabel = new JLabel("Falsch: " + f);
        falschLabel.setFont(new Font("Arial", Font.BOLD, 20));
        falschLabel.setForeground(Color.WHITE); // Weiße Schrift für "Falsch"
        falschLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button-Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila für das Button-Panel

        edit = new JButton("Edit");
        edit.setActionCommand("Save");
        edit.addActionListener(controller);
        again = new JButton("Next");
        again.setActionCommand("Next");
        again.addActionListener(controller);

        edit.setFont(new Font("Arial", Font.BOLD, 20));
        again.setFont(new Font("Arial", Font.BOLD, 20));

        buttonPanel.add(edit);
        buttonPanel.add(again);

        ergebnisPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        ergebnisPanel.add(richtigLabel);
        ergebnisPanel.add(falschLabel);
        ergebnisPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        ergebnisPanel.add(buttonPanel);

        add(überschriftPanel, BorderLayout.NORTH);
        add(quizPanel, BorderLayout.CENTER);
        add(ergebnisPanel, BorderLayout.SOUTH);

        // Erste Frage setzen
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                zeigeNaechsteFrage();
            }
        });

        setVisible(true);
    }

    private void zeigeNaechsteFrage() {
        // Hole alle Fragen aus dem Modell und zeige die nächste an
        if (aktuelleFrageIndex < model.getFragenAntworten().size()) {
            String[] fragenArray = model.getFragenAntworten().keySet().toArray(new String[0]);
            String naechsteFrage = fragenArray[aktuelleFrageIndex];
            frage.setText(naechsteFrage);
            antwort.setText(""); // Antwortfeld leeren
            aktuelleFrageIndex++;
        } else {
            frage.setText("Quiz beendet.");
            antwort.setEnabled(false); // Quiz beenden
        }
    }

    public void pruefeAntwort() {
        String aktuelleFrage = frage.getText();
        String userAntwort = antwort.getText().trim();

        // Überprüfe die Antwort
        boolean richtigGeantwortet = model.checkAntwort(aktuelleFrage, userAntwort);

        if (richtigGeantwortet) {
            r++;
            richtigLabel.setText("Richtig: " + r);
            JOptionPane.showMessageDialog(this, "Richtige Antwort!", "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
        } else {
            f++;
            falschLabel.setText("Falsch: " + f);
            String richtigeAntwort = model.getAntwort(aktuelleFrage);
            JOptionPane.showMessageDialog(this, "Falsche Antwort! Die richtige Antwort ist: " + richtigeAntwort, "Ergebnis", JOptionPane.ERROR_MESSAGE);
        }

        // Gehe zur nächsten Frage
        zeigeNaechsteFrage();
    }
}
