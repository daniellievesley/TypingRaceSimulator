package part2;
import java.awt.*;
import javax.swing.*;

public class TypistRowData {
    private JComboBox<String> typingStyle;
    private JComboBox<String> keyboardType;
    private JTextField symbol;
    private Color colourChosen;
    private JCheckBox wrist;
    private JCheckBox energyDrink;
    private JCheckBox headphones;

    public JComboBox<String> getTypingStyle(){
        return this.typingStyle;
    }

    public JComboBox<String> getKeyboardType(){
        return this.keyboardType;
    }

    public JTextField getSymbol(){
        return this.symbol;
    }

    public Color getColour(){
        return this.colourChosen;
    }

    public JCheckBox getWrist(){
        return this.wrist;
    }

    public JCheckBox getEnergy(){
        return this.energyDrink;
    }

    public JCheckBox getHeadphones(){
        return this.headphones;
    }



    TypistRowData(JComboBox<String> typingStyle, JComboBox<String> keyboardType, JTextField symbol, JCheckBox wrist, JCheckBox energyDrink, JCheckBox headphones){
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
