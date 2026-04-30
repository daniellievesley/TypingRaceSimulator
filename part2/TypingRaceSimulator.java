package part2;
import java.awt.*;
import javax.swing.*;

public class TypingRaceSimulator {
    public static void main(String[] args){
        JFrame mainApp = new JFrame("Typing Race Simulator");
        mainApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CardLayout layout = new CardLayout();
        JPanel appCard = new JPanel(layout);
        CustomiseTypists customiseCard = new CustomiseTypists(appCard);
        RacePage raceUI = new RacePage();
        Leaderboard boardUI = new Leaderboard();
        IntroPage introCard = new IntroPage(appCard, customiseCard, raceUI, boardUI);
        appCard.add(introCard, "Intro");
        appCard.add(customiseCard, "CustomiseTypists");
        appCard.add(raceUI, "RacePage");
        appCard.add(boardUI, "Leaderboard");
        mainApp.add(appCard);
        mainApp.pack();
        mainApp.setLocationRelativeTo(null);
        mainApp.setVisible(true);
        layout.show(appCard, "Intro");
    }
}
