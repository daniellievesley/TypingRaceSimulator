package part2;
import java.awt.*;
import javax.swing.*;
import javax.swing.colorchooser.*;

public class CustomiseTypists extends JPanel {
    String len;
    String passage;
    Integer seats;
    boolean auto;
    boolean caffeine;
    boolean night;
    
    public CustomiseTypists(JPanel mainPanel) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Customising typists", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }

    public void takeData(String len, String passage, Integer seats, Boolean auto, Boolean caffeine, boolean night) {
        this.len = len;
        this.passage = passage;
        this.seats = seats;
        this.auto = auto;
        this.caffeine = caffeine;
        this.night = night;
        for (int i=0; i<seats; i++){
            String[] styles = {"Touch Typist", "Hunt & Peck", "Phone Thumbs", "Voice-to-Text"};
            JComboBox<String> typingStyle = new JComboBox<>(styles);
            String[] keyboardTypes = {"Mechanical", "Membrane", "Touchscreen", "Stenography"};
            JComboBox<String> keyboardType = new JComboBox<>(keyboardTypes);
            JTextField symbol = new JTextField(10);
            JColorChooser
            

        }
        
    }

    
    
}
