import javax.swing.*;
import java.awt.*;

public class Quiz extends JFrame {

    private JMenuBar menuBar;
    private JMenu homeMenu;
    private JPanel überschriftPanel, quizPanel, ergebnisPanel, buttonPanel;
    private JTextField frage = new JTextField("Frage 1");
    private JTextField antwort = new JTextField();
    private JLabel richtig, falsch;
    private JButton edit,einfach,schwer,again;
    private int r = 0, f = 0;

    public Quiz() {
        setTitle("WordWizard");
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
        JLabel überschrift = new JLabel("Quiz-Modus");
        überschrift.setFont(new Font("Arial", Font.BOLD, 30));
        überschriftPanel.add(überschrift);

        //Eingabefelder
        quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.X_AXIS));
        quizPanel.setBackground(Color.decode("#C6E2FF"));

        frage.setEditable(false);
        frage.setBackground(Color.WHITE);
        frage.setFont(new Font("Arial", Font.BOLD, 30));
        frage.setHorizontalAlignment(SwingConstants.CENTER);
        frage.setPreferredSize(new Dimension(getWidth()/3, 150));
        frage.setMaximumSize(new Dimension(getWidth()/3, 150));
        frage.setBorder(BorderFactory.createLineBorder(Color.decode("#2F4F4F"), 1));

        antwort.setFont(new Font("Arial", Font.BOLD, 30));
        antwort.setHorizontalAlignment(SwingConstants.CENTER);
        antwort.setPreferredSize(new Dimension(getWidth()/3, 150));
        antwort.setMaximumSize(new Dimension(getWidth()/3, 150));
        antwort.setBorder(BorderFactory.createLineBorder(Color.decode("#2F4F4F"), 1));

        quizPanel.add(Box.createHorizontalGlue());
        quizPanel.add(frage);
        quizPanel.add(Box.createRigidArea(new Dimension(80, 0)));
        quizPanel.add(antwort);
        quizPanel.add(Box.createHorizontalGlue());

        //Ergebnise
        ergebnisPanel = new JPanel();
        ergebnisPanel.setLayout(new BoxLayout(ergebnisPanel, BoxLayout.Y_AXIS));
        ergebnisPanel.setBackground(Color.decode("#C6E2FF"));
        ergebnisPanel.setPreferredSize(new Dimension(getWidth(), getHeight()/2));
        ergebnisPanel.setMinimumSize(new Dimension(getWidth(), getHeight()/2));
        //Richitge und Falsche Antworten
        richtig = new JLabel("Richtig: " + r);
        richtig.setFont(new Font("Arial", Font.BOLD, 20));
        richtig.setAlignmentX(Component.CENTER_ALIGNMENT);
        falsch = new JLabel("Falsch: " + f);
        falsch.setFont(new Font("Arial", Font.BOLD, 20));
        falsch.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Panel für alle Buttons
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
        add(quizPanel, BorderLayout.CENTER);
        add(ergebnisPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
    }

    public void setRichtig(int r) {
        this.r = r;
    }
    public void setFalsch(int f) {
        this.f = f;
    }


}
