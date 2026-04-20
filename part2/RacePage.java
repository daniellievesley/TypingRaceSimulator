package part2;
import java.awt.*;
import javax.swing.*;

public class RacePage {
    public RacePage(JPanel mainPanel, CardLayout layout){
        JLabel label = new JLabel("RACE PAGE", SwingConstants.CENTER);
        JButton finishBtn = new JButton("Finish race");
        finishBtn.addActionListener(e -> {
            layout.show(mainPanel, "RESULTS");
        }
        
        mainPanel.add(label, BorderLayout.CENTER);
        mainPanel.add(finishBtn, BorderLayout.SOUTH);
    }
}
