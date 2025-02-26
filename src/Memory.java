import javax.swing.*;
import java.awt.*;

public class Memory extends JFrame {

    private JMenuBar menuBar;
    private JMenu homeMenu;
    private JMenuItem homeMenuItem;
    private JPanel überschriftPanel, vocabPanel, ergebnisPanel, buttonPanel;
    private JLabel richtig, falsch;
    private JButton edit, next;
    private Controller controller;
    private int r = 0, f = 0;

    public Memory(Controller controller) {
        this.controller = controller;
        setTitle("WordWizard - Vokabeltest");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menüleiste
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(getWidth(), 50));
        homeMenu = new JMenu("Home");
        homeMenuItem = new JMenuItem("Zur Startseite");
        homeMenuItem.setActionCommand("Home");
        homeMenuItem.addActionListener(controller);
        homeMenu.add(homeMenuItem);
        menuBar.add(homeMenu);
        setJMenuBar(menuBar);

        // Überschrift Panel
        überschriftPanel = new JPanel();
        überschriftPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila
        JLabel überschrift = new JLabel("Vokabeltest-Modus");
        überschrift.setFont(new Font("Arial", Font.BOLD, 30));
        überschrift.setForeground(Color.WHITE); // Weiße Schrift für den Titel
        überschriftPanel.add(überschrift);

        // Vokabelkarten Panel
        vocabPanel = new JPanel();
        vocabPanel.setLayout(new GridLayout(3, 2, 20, 20));
        vocabPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila

        for (int i = 0; i < 6; i++) {
            JPanel card = new JPanel();
            card.setLayout(new BorderLayout());
            card.setBackground(Color.WHITE);
            JLabel image = new JLabel("Bild"); // Platzhalter für Bild
            image.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel word = new JLabel("Wort"); // Platzhalter für Wort
            word.setHorizontalAlignment(SwingConstants.CENTER);
            card.add(image, BorderLayout.CENTER);
            card.add(word, BorderLayout.SOUTH);
            vocabPanel.add(card);
        }

        // Ergebnis Panel
        ergebnisPanel = new JPanel();
        ergebnisPanel.setLayout(new BoxLayout(ergebnisPanel, BoxLayout.Y_AXIS));
        ergebnisPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila

        richtig = new JLabel("Richtig: " + r);
        richtig.setFont(new Font("Arial", Font.BOLD, 20));
        richtig.setForeground(Color.WHITE); // Weiße Schrift
        richtig.setAlignmentX(Component.CENTER_ALIGNMENT);
        falsch = new JLabel("Falsch: " + f);
        falsch.setFont(new Font("Arial", Font.BOLD, 20));
        falsch.setForeground(Color.WHITE); // Weiße Schrift
        falsch.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila

        edit = new JButton("Edit");
        edit.setActionCommand("Save");
        edit.addActionListener(controller);
        edit.setFont(new Font("Arial", Font.BOLD, 20));
        edit.setBackground(Color.decode("#4B0082")); // Lila Button Hintergrund
        edit.setForeground(Color.WHITE); // Weiße Schrift

        next = new JButton("Next");
        next.setFont(new Font("Arial", Font.BOLD, 20));
        next.setBackground(Color.decode("#4B0082")); // Lila Button Hintergrund
        next.setForeground(Color.WHITE); // Weiße Schrift

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
    }

    public void setRichtig(int r) {
        this.r = r;
        richtig.setText("Richtig: " + r);
    }

    public void setFalsch(int f) {
        this.f = f;
        falsch.setText("Falsch: " + f);
    }
}
