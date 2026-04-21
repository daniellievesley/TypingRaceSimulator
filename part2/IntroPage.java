package part2;
import java.awt.*;
import javax.swing.*;

public class IntroPage {
    public static void main (String[] args){
        JFrame frame = new JFrame("Setup - Typing Race Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panelTitle = new JPanel();
        JLabel titleText = new JLabel("Welcome to the Typing Race Simulator!");
        frame.add(panelTitle, BorderLayout.NORTH);
        panelTitle.add(titleText);
        // passage chooser panel
        JPanel passageChooser = new JPanel();
        JLabel passageLabelText = new JLabel("Choose pre-set passage length or insert");
        frame.add(passageChooser, BorderLayout.CENTER);
        passageChooser.add(passageLabelText);
        String[] lengths = {"Short", "Medium", "Long", "Custom"};
        JComboBox passageLenBox = new JComboBox<>(lengths);
        //passageLenBox.addItemListener(s);
        passageLenBox.setEditable(true);
        passageChooser.add(passageLenBox);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
