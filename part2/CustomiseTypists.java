package part2;
import java.awt.*;
import javax.swing.*;

public class CustomiseTypists extends JPanel {
    String len;
    String passage;
    Integer seats;
    boolean auto;
    boolean caffeine;
    boolean night;

    public CustomiseTypists(JPanel mainPanel) {
        setLayout(new BoxLayout());
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
            JPanel typistChildPanel = new JPanel();
            typistChildPanel.add(new JLabel("Typist " + i));
            String[] styles = {"Touch Typist", "Hunt & Peck", "Phone Thumbs", "Voice-to-Text"};
            JComboBox<String> typingStyle = new JComboBox<>(styles);
            String[] keyboardTypes = {"Mechanical", "Membrane", "Touchscreen", "Stenography"};
            JComboBox<String> keyboardType = new JComboBox<>(keyboardTypes);
            JTextField symbol = new JTextField(10);
            JButton clrPickerReq = new JButton("Pick a typist colour");
            clrPickerReq.addActionListener(e -> {
                JColorChooser clrChooser = new JColorChooser();
                Color color = JColorChooser.showDialog(null, "Pick a typist colour", Color.blue);
            });
            

        }
        
    }

    
    
}
