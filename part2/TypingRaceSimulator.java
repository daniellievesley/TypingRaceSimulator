/**
 * This class creates all cards used within the application.
 * This sets up a main frame application, within a principal card (a panel - appCard)
 * Card Layout manager is used and passed as a parameter to the prinicpal card
 * Cards are created by initialising the classes of different UIs within the application
 * The cards can be switched by using show which allows for 'multi page' feel
 *
 * Run this to start the application
 *
 * @author Daniel Lievesley
 * @version 1.0
 */

package part2;
import java.awt.*;
import javax.swing.*;

public class TypingRaceSimulator {
    public static void main(String[] args){
        startRaceGUI();
    }

    public static void startRaceGUI(){
        JFrame mainApp = new JFrame("Typing Race Simulator");
        mainApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // card layout manager created
        CardLayout layout = new CardLayout();
        // passing this into principal card to enforce layout standard
        JPanel appCard = new JPanel(layout);
        // class object of further UIs initialised
        CustomiseTypists customiseCard = new CustomiseTypists(appCard);
        RacePage raceUI = new RacePage();
        Leaderboard boardUI = new Leaderboard();
        IntroPage introCard = new IntroPage(appCard, customiseCard, raceUI, boardUI);
        // added to card layout manager each with an identifier string name
        appCard.add(introCard, "Intro");
        appCard.add(customiseCard, "CustomiseTypists");
        appCard.add(raceUI, "RacePage");
        appCard.add(boardUI, "Leaderboard");
        mainApp.add(appCard);
        mainApp.pack();
        mainApp.setSize(1500,600);
        mainApp.setLocationRelativeTo(null);
        mainApp.setVisible(true);
        // showing first card - Intro
        layout.show(appCard, "Intro");
    }
}
