package part2;
import java.awt.*;
import javax.swing.*;

public class TypingRaceSimulator {
    public static void main(String[] args){
        JFrame mainApp = new JFrame("Typing Race Simulator");
        mainApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CardLayout layout = new CardLayout();
        JPanel appCard = new JPanel(layout);
        JPanel introCard = new IntroPage(appCard);
        appCard.add(introCard, "Intro");
        mainApp.add(appCard);
        mainApp.pack();
        mainApp.setLocationRelativeTo(null);
        mainApp.setVisible(true);
        layout.show(appCard, "Intro");
    }
}
