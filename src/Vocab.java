import javax.swing.*;
import java.awt.*;

public class Vocab extends JFrame {

    private JMenuBar menuBar;
    private JMenu homeMenu;
    private JPanel überschriftPanel, vocabPanel, ergebnisPanel, buttonPanel;
    private JTextField englisch = new JTextField("English Word");
    private JTextField deutsch = new JTextField();
    private JTextField eingabeFeld = new JTextField();

    private JLabel wortLabel, richtig, falsch;
    private JButton edit, einfach, schwer, again;
    private int r = 0, f = 0;

    public Vocab() {
        setTitle("WordWizard - Vokabeltest");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(getWidth(), 50));
        homeMenu = new JMenu("Home");
        menuBar.add(homeMenu);
        setJMenuBar(menuBar);

        überschriftPanel = new JPanel();
        überschriftPanel.setBackground(Color.WHITE);
        JLabel überschrift = new JLabel("Vokabeltest-Modus");
        überschrift.setFont(new Font("Arial", Font.BOLD, 30));
        überschriftPanel.add(überschrift);

        // Eingabefelder
        vocabPanel = new JPanel();
        vocabPanel.setLayout(new GridBagLayout());
        vocabPanel.setBackground(Color.decode("#C6E2FF"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

        wortLabel = new JLabel("Englisches Wort");
        wortLabel.setFont(new Font("Arial", Font.BOLD, 30));
        vocabPanel.add(wortLabel, gbc);

        gbc.gridy++;
        eingabeFeld = new JTextField(20);
        eingabeFeld.setFont(new Font("Arial", Font.PLAIN, 20));
        vocabPanel.add(eingabeFeld, gbc);

        // Ergebnisse
        ergebnisPanel = new JPanel();
        ergebnisPanel.setLayout(new BoxLayout(ergebnisPanel, BoxLayout.Y_AXIS));
        ergebnisPanel.setBackground(Color.decode("#C6E2FF"));
        ergebnisPanel.setPreferredSize(new Dimension(getWidth(), getHeight()/2));
        ergebnisPanel.setMinimumSize(new Dimension(getWidth(), getHeight()/2));

        richtig = new JLabel("Richtig: " + r);
        richtig.setFont(new Font("Arial", Font.BOLD, 20));
        richtig.setAlignmentX(Component.CENTER_ALIGNMENT);
        falsch = new JLabel("Falsch: " + f);
        falsch.setFont(new Font("Arial", Font.BOLD, 20));
        falsch.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel für Buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.decode("#C6E2FF"));

        edit = new JButton("Edit");
        edit.setFont(new Font("Arial", Font.BOLD, 20));
        edit.setAlignmentX(Component.CENTER_ALIGNMENT);

        einfach = new JButton("Einfach");
        einfach.setFont(new Font("Arial", Font.BOLD, 20));
        einfach.setAlignmentX(Component.CENTER_ALIGNMENT);

        schwer = new JButton("Schwer");
        schwer.setFont(new Font("Arial", Font.BOLD, 20));
        schwer.setAlignmentX(Component.CENTER_ALIGNMENT);

        again = new JButton("Again");
        again.setFont(new Font("Arial", Font.BOLD, 20));
        again.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(edit);
        buttonPanel.add(einfach);
        buttonPanel.add(schwer);
        buttonPanel.add(again);

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

    public static void main(String[] args) {
        Vocab vocabTest = new Vocab();
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
