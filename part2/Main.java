package part2;
import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args){
    JFrame frame = new JFrame("Typing Race Simulator");
    CardLayout layout = new CardLayout();
    JPanel mainPanel = new JPanel(mainPanel, layout);
    IntroPage introSetup = new IntroPage(mainPanel, layout);
    RacePage raceArea = new RacePage(lmainPanel, ayout);
    ResultsPage resultArea = new ResultsPage(layout);
    mainPanel.add(IntroPage, "INTRO");
    mainPanel.add(RacePage, "RACE");
    mainPanel.add(ResultsPage, "RESULTS");
    frame.add(mainPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
