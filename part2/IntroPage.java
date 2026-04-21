package part2;
import java.awt.*;
import javax.swing.*;

public class IntroPage extends JPanel{
    IntroPage(JPanel app){
        setLayout(new BorderLayout());
        JPanel panelTitle = new JPanel();
        JLabel titleText = new JLabel("Welcome to the Typing Race Simulator!");
        panelTitle.add(titleText);
        add(panelTitle, BorderLayout.NORTH);
        // passage chooser panel
        JPanel passageChooser = new JPanel();
        passageChooser.setBackground(Color.ORANGE);
        JLabel passageLabelText = new JLabel("Choose passage length");
        passageChooser.add(passageLabelText);
        String[] lengths = {"Short", "Medium", "Long", "Custom"};
        JComboBox<String> passageLenBox = new JComboBox<>(lengths);
        JTextField customText = new JTextField("Passage here", 20);
        passageLenBox.setEditable(true);
        passageChooser.add(passageLenBox);
        passageChooser.add(customText);
        add(passageChooser, BorderLayout.WEST);
        // seat chooser panel
        JPanel seatChooser = new JPanel();
        seatChooser.setBackground(Color.PINK);
        JLabel seatChooserLabel = new JLabel("Number of seats");
        seatChooser.add(seatChooserLabel);
        Integer [] noOfSeatsAvailable = { 2, 3, 4, 5, 6};
        JComboBox<Integer> seatsPicked = new JComboBox<>(noOfSeatsAvailable);
        seatChooser.add(seatsPicked);
        add(seatChooser, BorderLayout.CENTER);

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
        add(modifiersPanel, BorderLayout.EAST);

        // continue panel
        JPanel continuetoGamePanel = new JPanel();
        JButton continuetoGame = new JButton("Continue to game");
        continuetoGamePanel.add(continuetoGame);
        add(continuetoGamePanel, BorderLayout.SOUTH);
    }
}
