import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SaveEdit extends JFrame {
    private DefaultListModel<Question> questionListModel;
    private JList<Question> questionList;
    private JPanel cardPanel, controlPanel;
    private JTextArea questionArea, answerArea;
    private JButton addButton, saveButton;
    private JComboBox<String> modeSelector;
    private String currentFileName;

    public SaveEdit(Controller controller) {
        setTitle("WordWizard - Fragen Editor");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menübar
        JMenuBar menuBar = new JMenuBar();
        JMenu homeMenu = new JMenu("Home");
        JMenuItem homeMenuItem = new JMenuItem("Zur Startseite");
        homeMenuItem.setActionCommand("Home");
        homeMenuItem.addActionListener(controller);
        homeMenu.add(homeMenuItem);
        menuBar.add(homeMenu);
        setJMenuBar(menuBar);

        // Fragenliste
        questionListModel = new DefaultListModel<>();
        questionList = new JList<>(questionListModel);
        questionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        questionList.setFont(new Font("Arial", Font.PLAIN, 18));
        questionList.setBorder(BorderFactory.createLineBorder(Color.decode("#2F4F4F"), 1));
        questionList.setBackground(Color.decode("#C6E2FF"));
        questionList.addListSelectionListener(e -> showSelectedQuestion());

        JScrollPane listScrollPane = new JScrollPane(questionList);
        listScrollPane.setPreferredSize(new Dimension(300, getHeight()));

        // Karten-Panel
        cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createTitledBorder("Frage Details"));

        questionArea = new JTextArea(5, 30);
        questionArea.setFont(new Font("Arial", Font.BOLD, 18));
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);

        answerArea = new JTextArea(3, 30);
        answerArea.setFont(new Font("Arial", Font.ITALIC, 16));
        answerArea.setLineWrap(true);
        answerArea.setWrapStyleWord(true);

        cardPanel.add(new JLabel("Frage:"));
        cardPanel.add(new JScrollPane(questionArea));
        cardPanel.add(new JLabel("Antwort:"));
        cardPanel.add(new JScrollPane(answerArea));

        // Kontroll-Panel
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        saveButton = new JButton("Frage Speichern");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveQuestionsToFile();
            }
        });

        addButton = new JButton("Frage hinzufügen");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addQuestion();
            }
        });

        String[] modes = {"Quiz", "Vokabel", "Memory"};
        modeSelector = new JComboBox<>(modes);
        modeSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFileName();
            }
        });

        controlPanel.add(new JLabel("Spielmodus:"));
        controlPanel.add(modeSelector);
        controlPanel.add(addButton);
        controlPanel.add(saveButton);

        // Hauptlayout mit SplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, cardPanel);
        splitPane.setDividerLocation(300);

        add(splitPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        setVisible(true);

        // Initiales Laden der Fragen
        updateFileName();
        loadQuestionsFromFile();
    }

    private void updateFileName() {
        String selectedMode = (String) modeSelector.getSelectedItem();
        switch (selectedMode) {
            case "Quiz":
                currentFileName = "QuizFragen.txt";
                break;
            case "Vokabel":
                currentFileName = "VokabelFragen.txt";
                break;
            case "Memory":
                currentFileName = "MemoryFragen.txt";
                break;
            default:
                currentFileName = "QuizFragen.txt";
        }
        loadQuestionsFromFile();
    }

    private void loadQuestionsFromFile() {
        questionListModel.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(currentFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String questionText = line.trim();
                String answerText = br.readLine().trim();
                questionListModel.addElement(new Question(questionText, answerText));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveQuestionsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(currentFileName))) {
            for (int i = 0; i < questionListModel.size(); i++) {
                Question q = questionListModel.get(i);
                bw.write(q.getQuestion() + "\n");
                bw.write(q.getAnswer() + "\n");
            }
            JOptionPane.showMessageDialog(this, "Fragen erfolgreich gespeichert!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Fehler beim Speichern der Fragen!", "Fehler", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showSelectedQuestion() {
        Question selected = questionList.getSelectedValue();
        if (selected != null) {
            questionArea.setText(selected.getQuestion());
            answerArea.setText(selected.getAnswer());
        }
    }

    private void addQuestion() {
        String questionText = questionArea.getText().trim();
        String answerText = answerArea.getText().trim();

        if (!questionText.isEmpty() && !answerText.isEmpty()) {
            questionListModel.addElement(new Question(questionText, answerText));
            questionArea.setText("");
            answerArea.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Bitte sowohl eine Frage als auch eine Antwort eingeben.", "Fehlende Eingabe", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new SaveEdit(new Controller());
    }
}

// Hilfsklasse für Fragen
class Question {
    private String question;
    private String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return question;
    }
}
