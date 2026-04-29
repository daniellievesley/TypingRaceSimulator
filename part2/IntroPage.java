/**
 * IntroPage is a class which contains the intial UI presented to the user upon launch
 * First point of interaction for user - uses Swing elements to take inputs - global game settings
 * First 'card' displayed within wider app.
 *
 * @author Daniel Lievesley
 * @version 1.0
 */

package part2;
import java.awt.*;
import javax.swing.*;

public class IntroPage extends JPanel{

    /**
     * Constructor for objects of class IntroPage
     *
     * @param app contains wider app panel which this card is displayed in 
     * @param customiseCard contains the card which houses individual typist customisation to be displayed later 
     * @param racePage contains card of class RacePage which will house race graphics, displayed later
     * @param board contains the leaderboard class object, which will show results later via card layout
     */
    IntroPage(JPanel app, CustomiseTypists customiseCard, RacePage racePage, Leaderboard board){
        // border layout used for structure within panel
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
        JTextField customText = new JTextField(20);
        // better info on purposes of boxes - hover message to outline purpose of box
        customText.setToolTipText("Type a custom passage");
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

        // action listener on button to take current values and pass them to the customisation object UI
        continuetoGame.addActionListener(e ->{
        CardLayout layout = (CardLayout) app.getLayout();
        // now to take values from fields and pass them 
        String length = (String) passageLenBox.getSelectedItem();
        String customPassage = customText.getText();
        Integer seats = (Integer) seatsPicked.getSelectedItem();
        Boolean auto = autoCorrect.isSelected();
        Boolean caffeine = caffeineMode.isSelected();
        Boolean night = nightShift.isSelected();
        customiseCard.takeData(length, customPassage, seats, auto, caffeine, night, app, racePage, board);
        layout.show(app, "CustomiseTypists");
        
    });
}
}
