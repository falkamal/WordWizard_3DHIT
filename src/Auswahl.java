import javax.swing.*;
import java.awt.*;

public class Auswahl extends JFrame {
    private JPanel quizPanel, vokabelPanel, memoryPanel;
    private JLabel quizLabel, vokLabel, memoryLabel;
    private JButton quizButton, vokButton, memoryButton;
    private JMenuBar menuBar;
    private JMenu homeMenu;

    public Auswahl() {
        setTitle("WordWizard");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,3));

        //Menübar
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(getWidth(), 50));
        homeMenu = new JMenu("Home");
        menuBar.add(homeMenu);
        setJMenuBar(menuBar);

        //Quizpanel
        quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        quizPanel.setBackground(Color.decode("#C6E2FF"));

        quizLabel = new JLabel("Quiz");
        quizLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizLabel.setFont(new Font("Arial", Font.BOLD, 40));
        quizLabel.setForeground(Color.decode("#2F4F4F"));
        quizPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#2F4F4F"), 1));

        quizButton = new JButton("Spielen");
        quizButton.setBackground(Color.WHITE);
        quizButton.setPreferredSize(new Dimension(100, 40));
        quizButton.setMaximumSize(new Dimension(100, 40));
        quizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizButton.setFont(new Font("Arial", Font.BOLD, 17));

        quizPanel.add(Box.createVerticalGlue());
        quizPanel.add(quizLabel);
        quizPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        quizPanel.add(quizButton);
        quizPanel.add(Box.createVerticalGlue());

        //Vokabelpanel
        vokabelPanel = new JPanel();
        vokabelPanel.setLayout(new BoxLayout(vokabelPanel, BoxLayout.Y_AXIS));
        vokabelPanel.setBackground(Color.decode("#C6E2FF"));

        vokLabel = new JLabel("Vokabel");
        vokLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        vokLabel.setFont(new Font("Arial", Font.BOLD, 40));
        vokLabel.setForeground(Color.decode("#2F4F4F"));
        vokabelPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#2F4F4F"), 1));

        vokButton = new JButton("Spielen");
        vokButton.setBackground(Color.WHITE);
        vokButton.setPreferredSize(new Dimension(100, 40));
        vokButton.setMaximumSize(new Dimension(100, 40));
        vokButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        vokButton.setFont(new Font("Arial", Font.BOLD, 17));

        vokabelPanel.add(Box.createVerticalGlue());
        vokabelPanel.add(vokLabel);
        vokabelPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        vokabelPanel.add(vokButton);
        vokabelPanel.add(Box.createVerticalGlue());

        //Memorypanel
        memoryPanel = new JPanel();
        memoryPanel.setLayout(new BoxLayout(memoryPanel, BoxLayout.Y_AXIS));
        memoryPanel.setBackground(Color.decode("#C6E2FF"));

        memoryLabel = new JLabel("Memory");
        memoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        memoryLabel.setFont(new Font("Arial", Font.BOLD, 40));
        memoryLabel.setForeground(Color.decode("#2F4F4F"));
        memoryPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#2F4F4F"), 1));

        memoryButton = new JButton("Spielen");
        memoryButton.setBackground(Color.WHITE);
        memoryButton.setPreferredSize(new Dimension(100, 40));
        memoryButton.setMaximumSize(new Dimension(100, 40));
        memoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        memoryButton.setFont(new Font("Arial", Font.BOLD, 17));

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
    public static void main(String[] args) {
        Auswahl app = new Auswahl();
    }
}
