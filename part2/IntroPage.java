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
        passageChooser.setBackground(Color.ORANGE);
        JLabel passageLabelText = new JLabel("Choose passage length");
        frame.add(passageChooser, BorderLayout.EAST);
        passageChooser.add(passageLabelText);
        String[] lengths = {"Short", "Medium", "Long", "Custom"};
        JComboBox passageLenBox = new JComboBox<>(lengths);
        JTextField customText = new JTextField("Enter custom passage, if appropriate");
        passageLenBox.setEditable(true);
        passageChooser.add(passageLenBox);
        passageChooser.add(customText);
        // seat chooser panel
        JPanel seatChooser = new JPanel();
        seatChooser.setBackground(Color.PINK);
        JLabel seatChooserLabel = new JLabel("Number of seats");
        seatChooser.add(seatChooserLabel);
        Integer [] noOfSeatsAvailable = { 2, 3, 4, 5, 6};
        JComboBox seatsPicked = new JComboBox<>(noOfSeatsAvailable);
        seatChooser.add(seatsPicked);
        frame.add(seatChooser, BorderLayout.CENTER);

        // dificulty modifiers panel 
        JPanel modifiersPanel = new JPanel();
        modifiersPanel.setBackground(Color.CYAN);
        JLabel modifiersPanelLabel = new JLabel("Spice up the game: add modifiers!");
        modifiersPanel.add(modifiersPanelLabel);
        JCheckBox autoCorrect = new JCheckBox("Auto correct on/off");
        JCheckBox caffeineMode = new JCheckBox("Caffeine Mode");
        JCheckBox nightShift = new JCheckBox("Night shift");
        modifiersPanel.add(autoCorrect);
        modifiersPanel.add(caffeineMode);
        modifiersPanel.add(nightShift);
        frame.add(modifiersPanel, BorderLayout.WEST);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
