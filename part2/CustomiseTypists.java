package part2;
import java.awt.*;
import javax.swing.*;

public class CustomiseTypists extends JPanel {
    Integer len;
    String passage;
    Integer seats;
    Boolean auto;
    Boolean caffeine;
    Boolean night;
    public CustomiseTypists(JPanel mainPanel) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Customising typists", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }

    public void takeData(Integer len, String passage, Integer seats, Boolean auto, Boolean caffeine, boolean night) {
        this.len = len;
        this.passage = passage;
        this.seats = seats;
        this.auto = auto;
        this.caffeine = caffeine;
        this.night = night;
    }

    
}
