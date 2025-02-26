import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class SaveEdit extends JFrame {
    private DefaultListModel<Question> questionListModel;
    private JList<Question> questionList;
    private JTextArea questionArea, answerArea;
    private JButton addButton, saveButton;
    private JComboBox<String> modeSelector;
    private String currentFileName;
    private JMenuBar menuBar;
    private JMenuItem homeMenuItem;
    private JMenu homeMenu;

    public SaveEdit(Controller controller) {
        setTitle("WordWizard - Fragen Editor");
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

        // Fragenliste
        questionListModel = new DefaultListModel<>();
        questionList = new JList<>(questionListModel);
        questionList.addListSelectionListener(e -> showSelectedQuestion());
        add(new JScrollPane(questionList), BorderLayout.WEST);

        // Frage-/Antwort-Bereich
        JPanel editPanel = new JPanel(new GridLayout(4, 1));
        questionArea = new JTextArea();
        answerArea = new JTextArea();
        editPanel.add(new JLabel("Frage:"));
        editPanel.add(new JScrollPane(questionArea));
        editPanel.add(new JLabel("Antwort:"));
        editPanel.add(new JScrollPane(answerArea));
        add(editPanel, BorderLayout.CENTER);

        // Kontroll-Panel
        JPanel controlPanel = new JPanel();
        String[] modes = {"Quiz", "Vokabel", "Memory"};
        modeSelector = new JComboBox<>(modes);
        modeSelector.addActionListener(e -> loadQuestions());

        addButton = new JButton("Frage hinzufügen");
        addButton.addActionListener(e -> addQuestion());

        saveButton = new JButton("Speichern");
        saveButton.addActionListener(e -> saveQuestions());

        controlPanel.add(new JLabel("Spielmodus:"));
        controlPanel.add(modeSelector);
        controlPanel.add(addButton);
        controlPanel.add(saveButton);
        add(controlPanel, BorderLayout.SOUTH);

        loadQuestions();
        setVisible(true);
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
            JOptionPane.showMessageDialog(this, "Bitte eine Frage und Antwort eingeben.", "Fehlende Eingabe", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveQuestions() {
        setFileName();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFileName))) {
            for (int i = 0; i < questionListModel.size(); i++) {
                Question q = questionListModel.get(i);
                writer.write(q.getQuestion() + ";" + q.getAnswer());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(this, "Fragen gespeichert in " + currentFileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Fehler beim Speichern!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadQuestions() {
        setFileName();
        questionListModel.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(currentFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    questionListModel.addElement(new Question(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Keine gespeicherten Fragen gefunden für " + currentFileName);
        }
    }

    private void setFileName() {
        switch ((String) modeSelector.getSelectedItem()) {
            case "Vokabel":
                currentFileName = "VokabelFragen.txt";
                break;
            case "Memory":
                currentFileName = "MemoryFragen.txt";
                break;
            default:
                currentFileName = "QuizFragen.txt";
        }
    }
}

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