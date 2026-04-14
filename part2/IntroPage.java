package part2;
import java.awt.*;
import javax.swing.*;

public class IntroPage {
    public static void main (String[] args){
        JFrame frame = new JFrame("Setup - Typing Race Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel panelTitle = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel titleText = new JLabel("Welcome to the Typing Race Simulator!");
        JSlider passageSlider = new JSlider(0, 20, 10);
        JLabel labelforPassageSlider = new JLabel("Set passage length");
        passageSlider.setPaintTicks(true);
        passageSlider.setPaintLabels(true);
        passageSlider.setMajorTickSpacing(5);
        passageSlider.setMinorTickSpacing(1);

        panel2.add(labelforPassageSlider);
        panel2.add(passageSlider);

        frame.add(panelTitle, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        panelTitle.add(titleText);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
