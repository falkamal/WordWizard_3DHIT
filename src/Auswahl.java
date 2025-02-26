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
        setTitle("WordWizard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,3));
        this.controller = controller;

        // Menübar
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(getWidth(), 50));
        homeMenu = new JMenu("Home");

        homeMenuItem = new JMenuItem("Zur Startseite");
        homeMenuItem.setActionCommand("Home");
        homeMenuItem.addActionListener(controller);
        homeMenu.add(homeMenuItem);

        menuBar.add(homeMenu);
        setJMenuBar(menuBar);

        // Quizpanel
        quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        quizPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila

        quizLabel = new JLabel("Quiz");
        quizLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizLabel.setFont(new Font("Arial", Font.BOLD, 40));
        quizLabel.setForeground(Color.decode("#F4A300")); // Helles Gelb/Orange für Kontrast
        quizPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#1F0030"), 1)); // Dunklerer Rand

        quizButton = new JButton("Spielen");
        quizButton.setActionCommand("Quiz");
        quizButton.addActionListener(controller);
        quizButton.setBackground(Color.decode("#1E0034")); // Sehr dunkles Lila
        quizButton.setForeground(Color.WHITE); // Weißer Text für besseren Kontrast
        quizButton.setPreferredSize(new Dimension(100, 40));
        quizButton.setMaximumSize(new Dimension(100, 40));
        quizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizButton.setFont(new Font("Arial", Font.BOLD, 17));

        quizPanel.add(Box.createVerticalGlue());
        quizPanel.add(quizLabel);
        quizPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        quizPanel.add(quizButton);
        quizPanel.add(Box.createVerticalGlue());

        // Vokabelpanel
        vokabelPanel = new JPanel();
        vokabelPanel.setLayout(new BoxLayout(vokabelPanel, BoxLayout.Y_AXIS));
        vokabelPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila

        vokLabel = new JLabel("Vokabel");
        vokLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        vokLabel.setFont(new Font("Arial", Font.BOLD, 40));
        vokLabel.setForeground(Color.decode("#F4A300")); // Helles Gelb/Orange
        vokabelPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#1F0030"), 1)); // Dunklerer Rand

        vokButton = new JButton("Spielen");
        vokButton.setActionCommand("Vocab");
        vokButton.addActionListener(controller);
        vokButton.setBackground(Color.decode("#1E0034")); // Sehr dunkles Lila
        vokButton.setForeground(Color.WHITE); // Weißer Text
        vokButton.setPreferredSize(new Dimension(100, 40));
        vokButton.setMaximumSize(new Dimension(100, 40));
        vokButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        vokButton.setFont(new Font("Arial", Font.BOLD, 17));

        vokabelPanel.add(Box.createVerticalGlue());
        vokabelPanel.add(vokLabel);
        vokabelPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        vokabelPanel.add(vokButton);
        vokabelPanel.add(Box.createVerticalGlue());

        // Memorypanel
        memoryPanel = new JPanel();
        memoryPanel.setLayout(new BoxLayout(memoryPanel, BoxLayout.Y_AXIS));
        memoryPanel.setBackground(Color.decode("#2E003E")); // Dunkles Lila

        memoryLabel = new JLabel("Memory");
        memoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        memoryLabel.setFont(new Font("Arial", Font.BOLD, 40));
        memoryLabel.setForeground(Color.decode("#F4A300")); // Helles Gelb/Orange
        memoryPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#1F0030"), 1)); // Dunklerer Rand

        memoryButton = new JButton("Spielen");
        memoryButton.setBackground(Color.decode("#1E0034")); // Sehr dunkles Lila
        memoryButton.setActionCommand("Memory");
        memoryButton.addActionListener(controller);
        memoryButton.setPreferredSize(new Dimension(100, 40));
        memoryButton.setMaximumSize(new Dimension(100, 40));
        memoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        memoryButton.setFont(new Font("Arial", Font.BOLD, 17));
        memoryButton.setForeground(Color.WHITE); // Weißer Text

        memoryPanel.add(Box.createVerticalGlue());
        memoryPanel.add(memoryLabel);
        memoryPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        memoryPanel.add(memoryButton);
        memoryPanel.add(Box.createVerticalGlue());

        add(quizPanel);
        add(vokabelPanel);
        add(memoryPanel);
        setVisible(true);
    }
}
