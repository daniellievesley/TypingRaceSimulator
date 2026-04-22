package part2;
import java.awt.*;
import javax.swing.*;

public class RacePage extends JPanel {
    public RacePage(JPanel mainPanel){
        JLabel label = new JLabel("RACE PAGE", SwingConstants.CENTER);
        JButton finishBtn = new JButton("Finish race");
        
        mainPanel.add(label, BorderLayout.CENTER);
        mainPanel.add(finishBtn, BorderLayout.SOUTH);
    }
}
