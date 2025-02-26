import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private JFrame frame;
    private QuizModel quizModel;
    private VocabModel vocabModel;

    public Controller() {
        frame = new Home(this);
        quizModel = new QuizModel("./QuizFragen.txt");
        vocabModel = new VocabModel("./VokabelFragen.txt");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        if(ac.equals("Home")) {
            frame.setVisible(false);
            frame = new Home(this);
            frame.setVisible(true);
        } else if(ac.equals("Auswahl")) {
            frame.setVisible(false);
            frame = new Auswahl(this);
            frame.setVisible(true);
        } else if(ac.equals("Beenden")) {
            System.exit(0);
        } else if(ac.equals("Quiz")) {
            frame.setVisible(false);
            frame = new Quiz(this, quizModel);
            frame.setVisible(true);
        } else if(ac.equals("Vocab")) {
            frame.setVisible(false);
            frame = new Vocab(this, vocabModel);
            frame.setVisible(true);
        } else if(ac.equals("Memory")) {
            frame.setVisible(false);
            frame = new Memory(this);
            frame.setVisible(true);
        } else if(ac.equals("Save")) {
            frame.setVisible(false);
            frame = new SaveEdit(this);
            frame.setVisible(true);
        } else if((ac.equals("Next") || ac.equals("Pruefe")) && frame instanceof Quiz) {
            ((Quiz) frame).pruefeAntwort();
        }
    }

    public static void main(String[] args) {
        Controller app = new Controller();
    }

}
