import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    private JMenuBar menuBar;
    private JMenu homeMenu;
    private JPanel auswahlpanel;
    JLabel titel;
    JButton  auswahl, save,close;
    JTextField modus;
    Controller controller;

    public Home(Controller controller) {
        this.controller = controller;
        setTitle("WordWizard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());



        // Menüleiste
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(getWidth(), 50));
        homeMenu = new JMenu("Home");
        homeMenu.setActionCommand("Home");
        homeMenu.addActionListener(controller);
        menuBar.add(homeMenu);
        setJMenuBar(menuBar);


        auswahlpanel = new JPanel();
        auswahlpanel.setLayout(new BoxLayout(auswahlpanel, BoxLayout.Y_AXIS));
        auswahlpanel.setBackground(Color.decode("#C6E2FF"));

        titel = new JLabel("Welcome to WordWizard", SwingConstants.CENTER);
        titel.setFont(new Font("Arial", Font.BOLD, 40));
        titel.setForeground(Color.decode("#2F4F4F"));
        titel.setAlignmentX(Component.CENTER_ALIGNMENT);


        auswahl = new JButton("AUSWAHL");
        auswahl.setActionCommand("Auswahl");
        auswahl.addActionListener(controller);
        auswahl.setFont(new Font("Arial", Font.BOLD, 20));
        auswahl.setBackground(Color.WHITE);
        auswahl.setPreferredSize(new Dimension(400, 100));
        auswahl.setMaximumSize(new Dimension(400, 100));
        auswahl.setAlignmentX(Component.CENTER_ALIGNMENT);

        save = new JButton("<html><center>SPEICHERN/<br>BEARBEITEN</center></html>");
        save.setActionCommand("Save");
        save.addActionListener(controller);
        save.setFont(new Font("Arial", Font.BOLD, 20));
        save.setBackground(Color.WHITE);
        save.setPreferredSize(new Dimension(400, 100));
        save.setMaximumSize(new Dimension(400, 100));
        save.setAlignmentX(Component.CENTER_ALIGNMENT);

        close = new JButton("BEENDEN");
        close.setActionCommand("Beenden");
        close.addActionListener(controller);
        close.setFont(new Font("Arial", Font.BOLD, 20));
        close.setBackground(Color.WHITE);
        close.setPreferredSize(new Dimension(400, 100));
        close.setMaximumSize(new Dimension(400, 100));
        close.setAlignmentX(Component.CENTER_ALIGNMENT);

        auswahlpanel.add(Box.createRigidArea(new Dimension(0, 100))); // Abstand zum nächsten Element
        auswahlpanel.add(titel);
        auswahlpanel.add(Box.createRigidArea(new Dimension(0, 50))); // Abstand zum nächsten Element
        auswahlpanel.add(Box.createRigidArea(new Dimension(0, 20))); // Abstand zwischen den Buttons
        auswahlpanel.add(auswahl);
        auswahlpanel.add(Box.createRigidArea(new Dimension(0, 20))); // Abstand zwischen den Buttons
        auswahlpanel.add(save);
        auswahlpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        auswahlpanel.add(close);
        add(auswahlpanel, BorderLayout.CENTER);
        setVisible(true);

    }


}
