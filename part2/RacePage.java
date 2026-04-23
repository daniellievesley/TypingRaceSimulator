package part2;
import java.util.List;
import javax.swing.*;

public class RacePage extends JPanel {
    public RacePage(JPanel mainPanel){
        JLabel label = new JLabel("RACE PAGE", SwingConstants.CENTER);
        JButton finishBtn = new JButton("Finish race");
    }

    public void passData (GameData gameinfo, List<TypistRowData> typistData){
        System.out.println("Data received");
    }
}
