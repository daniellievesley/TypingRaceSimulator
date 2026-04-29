/**
 * CustomiseTypists is a class which creates GUI interface for the user to determine add ons/settings for each typist
 * This class contains methods which take data from previous object, and also those that call the next object.
 *
 * @author Daniel Lievesley
 * @version 1.0
 */

package part2;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class CustomiseTypists extends JPanel {

    /**
     * Constructor for objects of class CustomiseTypists
     * Creates a new object with grid layout and label set to center.
     *
     * @param mainPanel contains the 'app' card panel which occupies all of the JFrame - this is where cards such as CustomiseTypists will be displayed
     */
    public CustomiseTypists(JPanel mainPanel) {
        setLayout(new GridLayout(0,1));
        JLabel label = new JLabel("Customising typists", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }

    /**
     * takeData() 
     * Takes data from IntroPage object, such as seat count and type of race length and begins to set up panel which will house customisation
     * Customisation taken via swing UI elements such as comboboxes or checkboxes - these references are stored in a list, so the contents can be accessed later
     *
     * @param len contains the length of the passage selected by the user
     * @param passage contains the custom passage entered by the user, if applicable
     * @param seats contains the number of seats chosen
     * @param auto contains the boolean value of if autocorrect is selected
     * @param caffeine contains the boolean value of if caffeine is selected
     * @param night contains boolean value of if night shift is selected
     * @param app contains the main panel which holds all cards of the game
     * @param racePage contains UI object where race will be shown
     * @param board contains UI object where results will be shown
     */
    public void takeData(String len, String passage, Integer seats, Boolean auto, Boolean caffeine, boolean night, JPanel app, RacePage racePage, Leaderboard board) {
        // gameInfo object created to store game data selected by user, easier to handle settings
        GameData gameInfo = new GameData(len, passage, seats, auto, caffeine, night);
        // list created to store references to each typists' panel - housing individual customisation input areas via UI
        List<JPanel> typistPanels = new ArrayList<>();
        // list created to store objects of TypistData - holding individual customisations
        List<TypistRowData> typistData = new ArrayList<>();
        for (int i=1; i<=seats; i++){
            // new typist child panel created for every seat needed
            JPanel typistChildPanel = new JPanel();
            JLabel typistLabel = new JLabel("Typist " + i);
            typistLabel.setFont(typistLabel.getFont().deriveFont(Font.BOLD));
            typistChildPanel.add(typistLabel);
            add(typistChildPanel);
            // customising each typist - controls 
            JTextField name = new JTextField("Name", 10);
            typistChildPanel.add(name);
            String[] styles = {"Touch Typist", "Hunt & Peck", "Phone Thumbs", "Voice-to-Text"};
            JComboBox<String> typingStyle = new JComboBox<>(styles);
            typingStyle.setName("TypingStyle");
            typistChildPanel.add(typingStyle);
            String[] keyboardTypes = {"Mechanical", "Membrane", "Touchscreen", "Stenography"};
            JComboBox<String> keyboardType = new JComboBox<>(keyboardTypes);
            keyboardType.setName("KeyboardType");
            typistChildPanel.add(keyboardType);
            JTextField symbol = new JTextField("Symbol", 10);
            keyboardType.setName("Symbol");
            typistChildPanel.add(symbol);
            JButton clrPickerReq = new JButton("Pick a typist colour");
            typistChildPanel.add(clrPickerReq);
            typistPanels.add(typistChildPanel);
            JCheckBox wristSupport = new JCheckBox("Wrist support");
            typistChildPanel.add(wristSupport);
            JCheckBox energyDrink = new JCheckBox("Energy drink");
            typistChildPanel.add(energyDrink);
            JCheckBox ncHeadphones = new JCheckBox("Noise-cancelling headphones");
            typistChildPanel.add(ncHeadphones);
            // new object created with references to each input field (JTextField, JCheckBox etc )
            TypistRowData rowofData = new TypistRowData(name, typingStyle, keyboardType, symbol, wristSupport, energyDrink, ncHeadphones);
            typistData.add(rowofData);
            // action listener on colour request btn to allow for colour pop up - this allows for colour to be chosen PER typist
            clrPickerReq.addActionListener(e -> {
                JColorChooser clrChooser = new JColorChooser();
                Color colorTypist = JColorChooser.showDialog(null, "Pick a typist colour", Color.blue); // default selected colour is blue
                System.out.println(colorTypist);
                rowofData.updateColour(colorTypist);
                typistChildPanel.add(clrChooser);
            });

        } 
        // start game button created to pass to next UI card
        JButton contToGame = new JButton("Start game now");
        add(contToGame);

        // action listener on start game btn to pass data and show next UI 
        contToGame.addActionListener(e -> {
            CardLayout layout = (CardLayout) app.getLayout();
            racePage.passData(gameInfo, typistData, app, board);
            layout.show(app, "RacePage");
    });

}

}
