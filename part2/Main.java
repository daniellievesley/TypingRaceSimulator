package part2;
import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame mainApp = new JFrame("Typing Race Simulator");
        mainApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel appCard = new JPanel(new CardLayout());
        JPanel introCard = new IntroPage(appCard);
    }
}
