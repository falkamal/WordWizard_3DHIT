import javax.swing.*;
import java.awt.*;

public class Vocab extends JFrame {

    private Controller controller;
    private JMenuBar menuBar;
    private JMenu homeMenu;
    private JMenuItem homeMenuItem;
    private JPanel überschriftPanel, vocabPanel, ergebnisPanel, buttonPanel;
    private JTextField englisch = new JTextField("English Word");
    private JTextField deutsch = new JTextField();
    private JTextField eingabeFeld = new JTextField();

    private JLabel wortLabel, richtig, falsch;
    private JButton edit, next;
    private int r = 0, f = 0;
    private VocabModel vocabModel;
    private int currentIndex = 0; // Aktuelles Vokabelindex

    public Vocab(Controller controller, VocabModel vocabModel) {
        this.controller = controller;
        this.vocabModel = vocabModel;
        setTitle("WordWizard - Vokabeltest");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

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
        überschriftPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila
        JLabel überschrift = new JLabel("Vokabeltest-Modus");
        überschrift.setFont(new Font("Arial", Font.BOLD, 30));
        überschrift.setForeground(Color.WHITE); // Weißer Text für die Überschrift
        überschriftPanel.add(überschrift);

        // Eingabefelder
        vocabPanel = new JPanel();
        vocabPanel.setLayout(new GridBagLayout());
        vocabPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

        wortLabel = new JLabel("Englisches Wort");
        wortLabel.setFont(new Font("Arial", Font.BOLD, 30));
        wortLabel.setForeground(Color.WHITE); // Weißer Text für das Label
        vocabPanel.add(wortLabel, gbc);

        gbc.gridy++;
        eingabeFeld = new JTextField(20);
        eingabeFeld.setFont(new Font("Arial", Font.PLAIN, 20));
        vocabPanel.add(eingabeFeld, gbc);

        // Ergebnisse
        ergebnisPanel = new JPanel();
        ergebnisPanel.setLayout(new BoxLayout(ergebnisPanel, BoxLayout.Y_AXIS));
        ergebnisPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila
        ergebnisPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 2));
        ergebnisPanel.setMinimumSize(new Dimension(getWidth(), getHeight() / 2));

        richtig = new JLabel("Richtig: " + r);
        richtig.setFont(new Font("Arial", Font.BOLD, 20));
        richtig.setForeground(Color.WHITE); // Weißer Text für "Richtig"
        richtig.setAlignmentX(Component.CENTER_ALIGNMENT);
        falsch = new JLabel("Falsch: " + f);
        falsch.setFont(new Font("Arial", Font.BOLD, 20));
        falsch.setForeground(Color.WHITE); // Weißer Text für "Falsch"
        falsch.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel für Buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila

        edit = new JButton("Edit");
        edit.setActionCommand("Save");
        edit.addActionListener(controller);
        edit.setFont(new Font("Arial", Font.BOLD, 20));
        edit.setBackground(Color.decode("#4B0082")); // Lila Button Hintergrund
        edit.setForeground(Color.WHITE); // Weiße Schrift
        edit.setAlignmentX(Component.CENTER_ALIGNMENT);

        next = new JButton("Next");
        next.setFont(new Font("Arial", Font.BOLD, 20));
        next.setBackground(Color.decode("#4B0082")); // Lila Button Hintergrund
        next.setForeground(Color.WHITE); // Weiße Schrift
        next.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(edit);
        buttonPanel.add(next);

        ergebnisPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        ergebnisPanel.add(richtig);
        ergebnisPanel.add(falsch);
        ergebnisPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        ergebnisPanel.add(buttonPanel);

        add(überschriftPanel, BorderLayout.NORTH);
        add(vocabPanel, BorderLayout.CENTER);
        add(ergebnisPanel, BorderLayout.SOUTH);
        setVisible(true);

        loadNextWord(); // Lade das erste Wort
        addNextButtonListener(); // Listener für den Next-Button
    }

    public void setRichtig(int r) {
        this.r = r;
        richtig.setText("Richtig: " + r);
    }

    public void setFalsch(int f) {
        this.f = f;
        falsch.setText("Falsch: " + f);
    }

    public void loadNextWord() {
        if (currentIndex < vocabModel.getVocabEntries().size()) {
            VocabModel.VocabEntry currentEntry = vocabModel.getVocabEntries().get(currentIndex);
            wortLabel.setText(currentEntry.getEnglishWord());
            deutsch.setText(currentEntry.getGermanWord());
            currentIndex++;
        } else {
            // Keine weiteren Vokabeln, evtl. Test beenden
            JOptionPane.showMessageDialog(this, "Test abgeschlossen!");
        }
    }

    public void checkAnswer() {
        String userAnswer = eingabeFeld.getText().trim().toLowerCase();
        String correctAnswer = vocabModel.getVocabEntries().get(currentIndex - 1).getGermanWord().toLowerCase();

        if (userAnswer.equals(correctAnswer)) {
            setRichtig(r + 1);
        } else {
            setFalsch(f + 1);
        }

        loadNextWord(); // Lade das nächste Wort
        eingabeFeld.setText(""); // Leere das Eingabefeld
    }

    public void addNextButtonListener() {
        next.addActionListener(e -> checkAnswer());
    }
}
