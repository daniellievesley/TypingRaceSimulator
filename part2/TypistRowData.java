package part2;
import java.awt.*;
import javax.swing.*;

public class TypistRowData {
    JComboBox<String> typingStyle;
    JComboBox<String> keyboardType;
    JTextField symbol;
    Color colourChosen;
    JCheckBox wrist;
    JCheckBox energyDrink;
    JCheckBox headphones;

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
