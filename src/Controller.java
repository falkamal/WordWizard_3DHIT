import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private JFrame frame;

    public Controller() {
        frame = new Home(this);

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
        }else if(ac.equals("Beenden")) {
            System.exit(0);
        }else if(ac.equals("Quiz")) {
            frame.setVisible(false);
            frame = new Quiz(this);
            frame.setVisible(true);

        }else if(ac.equals("Vocab")) {
            frame.setVisible(false);
            frame = new Vocab();
            frame.setVisible(true);

        }else if(ac.equals("Memory")) {
            frame.setVisible(false);
            frame = new Memory();
            frame.setVisible(true);
        }else if(ac.equals("Save")) {
            frame.setVisible(false);
            frame = new SaveEdit(this);
            frame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        Controller app = new Controller();
    }

}
