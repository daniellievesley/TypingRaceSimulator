package part2;
import javax.swing.*;

public class IntroPage {
    public static void main (String[] args){
        JFrame frame = new JFrame("Setup - Typing Race Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        JPanel panel = new JPanel();
        JSlider passageSlider = new JSlider(0,20,10);
        panel.add(passageSlider);
    }
}
