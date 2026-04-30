package part2;
import java.awt.*;
import javax.swing.*;

/**
 * Class which holds the data collected about the customisation of the typist during the game.
 * This holds the references to swing elements so that data can later be referred to
 *
 * @author Daniel Lievesley
 * @version 1.0
 */
public class TypistRowData {
    private JComboBox<String> typingStyle;
    private JComboBox<String> keyboardType;
    private JTextField symbol;
    private Color colourChosen;
    private JCheckBox wrist;
    private JCheckBox energyDrink;
    private JCheckBox headphones;
    private JTextField name;

    /**
     * getTypingStyle() method which returns the reference to the typing style swing box 
     * @return JComboBox reference, holding string value 
     */
    public JComboBox<String> getTypingStyle(){
        return this.typingStyle;
    }

    /**
     * getTypistName() method which returns the reference to the typist name swing field
     * @return JTextField reference
     */
    public JTextField getTypistName(){
        return this.name;
    }

    /**
     * getKeyboardType() method which returns the reference to the keyboard type chosen
     * @return JComboBox reference, holding string value 
     */
    public JComboBox<String> getKeyboardType(){
        return this.keyboardType;
    }

    /**
     * getSymbol() method which returns the reference to the typist symbol field
     * @return JTextField reference
     */
    public JTextField getSymbol(){
        return this.symbol;
    }

    /**
     * getColour() method which returns the colour object stored 
     * @return Color object chosen by typist
     */
    public Color getColour(){
        return this.colourChosen;
    }

    /**
     * getWrist method which returns the reference to the checkbox holding the state of wrist support by typist
     * @return JCheckbox reference
     */
    public JCheckBox getWrist(){
        return this.wrist;
    }

    /**
     * getEnergy method which returns the reference to the checkbox holding the state of energy drink addon by typist
     * @return JCheckbox reference
     */
    public JCheckBox getEnergy(){
        return this.energyDrink;
    }

    /**
     * getHeadphones method which returns the reference to the checkbox holding the state of headphones addon by typist
     * @return JCheckbox reference
     */
    public JCheckBox getHeadphones(){
        return this.headphones;
    }



    /**
    * Constructor for TypistRowData
    * @param name contains the reference to the name field
    * @param typingStyle contains the reference to the style chosen
    * @param keyboardType contains the reference to the keyboard type chosen 
    * @param symbol contains the reference to the symbol entered
    * @param wrist contains the reference to whether wrist support is chosen
    * @param energyDrink contains the reference to whether energy drink is chosen as an accessory
    * @param headphones contains the reference to whether headphones is chosen as an accessory
    */
    TypistRowData(JTextField name, JComboBox<String> typingStyle, JComboBox<String> keyboardType, JTextField symbol, JCheckBox wrist, JCheckBox energyDrink, JCheckBox headphones){
        this.name = name;
        this.typingStyle = typingStyle;
        this.keyboardType = keyboardType;
        this.symbol = symbol;
        this.colourChosen = Color.RED;
        this.wrist = wrist;
        this.energyDrink = energyDrink;
        this.headphones = headphones;
    }

    
    public void updateColour(Color color){
        this.colourChosen = color;
    }
}
