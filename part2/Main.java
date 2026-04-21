package part2;
import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args){}
    JFrame frame = new JFrame("Typing Race Simulator");
    CardLayout layout = new CardLayout();
    JPanel mainPanel = new JPanel(layout);
    IntroPage introSetup = new IntroPage(layout, mainPanel);
    RacePage raceArea = new RacePage(layout, mainPanel);
    ResultsPage resultArea = new ResultsPage(layout, mainPanel);
    mainPanel.add();
}
}
