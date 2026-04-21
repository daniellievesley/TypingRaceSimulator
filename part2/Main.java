package part2;
import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args){
    JFrame frame = new JFrame("Typing Race Simulator");
    CardLayout layout = new CardLayout();
    JPanel mainPanel = new JPanel(layout);
    IntroPage introSetup = new IntroPage();
    RacePage raceArea = new RacePage();
    ResultsPage resultArea = new ResultsPage();
    mainPanel.add(IntroPage, "INTRO");
    mainPanel.add(RacePage, "RACE");
    mainPanel.add(ResultsPage, "RESULTS");
    frame.add(mainPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
