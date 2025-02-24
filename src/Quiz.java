import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Quiz extends JFrame {

    private JMenuBar menuBar;
    private JMenu homeMenu;
    private JMenuItem homeMenuItem;
    private JPanel überschriftPanel, quizPanel, ergebnisPanel, buttonPanel;
    private JTextField frage;
    private JTextField antwort;
    private JLabel richtigLabel, falschLabel;
    private JButton edit, einfach, schwer, again;
    private Controller controller;
    private QuizModel model;
    private List<String> fragenListe;
    private int aktuelleFrageIndex;
    private int r = 0, f = 0;

    public Quiz(Controller controller, QuizModel model) {
        this.controller = controller;
        this.model = model;
        this.fragenListe = new ArrayList<>(model.getFragenAntworten().keySet());
        System.out.println("Geladene Fragenanzahl: " + fragenListe.size());
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
        überschriftPanel.setBackground(Color.WHITE);
        JLabel überschrift = new JLabel("Quiz-Modus");
        überschrift.setFont(new Font("Arial", Font.BOLD, 30));
        überschriftPanel.add(überschrift);

        // Eingabefelder
        quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.X_AXIS));
        quizPanel.setBackground(Color.decode("#C6E2FF"));

        frage = new JTextField();
        frage.setEditable(false);
        frage.setBackground(Color.WHITE);
        frage.setFont(new Font("Arial", Font.BOLD, 15));
        frage.setHorizontalAlignment(SwingConstants.CENTER);
        frage.setPreferredSize(new Dimension(getWidth() / 3, 150));
        frage.setMaximumSize(new Dimension(getWidth() / 3, 150));
        frage.setBorder(BorderFactory.createLineBorder(Color.decode("#2F4F4F"), 1));

        antwort = new JTextField();
        antwort.setFont(new Font("Arial", Font.BOLD, 30));
        antwort.setHorizontalAlignment(SwingConstants.CENTER);
        antwort.setPreferredSize(new Dimension(getWidth() / 3, 150));
        antwort.setMaximumSize(new Dimension(getWidth() / 3, 150));
        antwort.setBorder(BorderFactory.createLineBorder(Color.decode("#2F4F4F"), 1));

        quizPanel.add(Box.createHorizontalGlue());
        quizPanel.add(frage);
        quizPanel.add(Box.createRigidArea(new Dimension(80, 0)));
        quizPanel.add(antwort);
        quizPanel.add(Box.createHorizontalGlue());

        // Ergebnis
        ergebnisPanel = new JPanel();
        ergebnisPanel.setLayout(new BoxLayout(ergebnisPanel, BoxLayout.Y_AXIS));
        ergebnisPanel.setBackground(Color.decode("#C6E2FF"));
        ergebnisPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 2));
        ergebnisPanel.setMinimumSize(new Dimension(getWidth(), getHeight() / 2));

        richtigLabel = new JLabel("Richtig: " + r);
        richtigLabel.setFont(new Font("Arial", Font.BOLD, 20));
        richtigLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        falschLabel = new JLabel("Falsch: " + f);
        falschLabel.setFont(new Font("Arial", Font.BOLD, 20));
        falschLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button-Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.decode("#C6E2FF"));

        edit = new JButton("Edit");
        einfach = new JButton("Einfach");
        schwer = new JButton("Schwer");
        again = new JButton("Next");

        edit.setFont(new Font("Arial", Font.BOLD, 20));
        einfach.setFont(new Font("Arial", Font.BOLD, 20));
        schwer.setFont(new Font("Arial", Font.BOLD, 20));
        again.setFont(new Font("Arial", Font.BOLD, 20));

        again.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zeigeNaechsteFrage();
            }
        });

        buttonPanel.add(edit);
        buttonPanel.add(einfach);
        buttonPanel.add(schwer);
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
        zeigeNaechsteFrage();

        setVisible(true);
    }

    private void zeigeNaechsteFrage() {
        if (fragenListe.isEmpty()) {
            frage.setText("Keine Fragen verfügbar.");
            return;
        }

        // Wenn es noch unbeantwortete Fragen gibt, zeige die nächste an
        if (aktuelleFrageIndex < fragenListe.size()) {
            String naechsteFrage = fragenListe.get(aktuelleFrageIndex);
            frage.setText(naechsteFrage);
            antwort.setText(""); // Antwortfeld leeren
            aktuelleFrageIndex++;
        } else {
            // Alle Fragen wurden durchgegangen
            frage.setText("Quiz beendet.");
            antwort.setEnabled(false); // oder eine andere Logik, um das Quiz zu beenden
        }
    }


    public void setRichtig(int r) {
        this.r = r;
        richtigLabel.setText("Richtig: " + r);
    }

    public void setFalsch(int f) {
        this.f = f;
        falschLabel.setText("Falsch: " + f);
    }
}
