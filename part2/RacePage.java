package part2;
import java.util.List;
import javax.swing.*;

public class RacePage extends JPanel {
    public RacePage(JPanel mainPanel){
        JLabel label = new JLabel("RACE PAGE", SwingConstants.CENTER);
        JButton finishBtn = new JButton("Finish race");
    }

    public void passData (GameData gameinfo, List<TypistRowData> typistData){
        if (gameinfo.len.equals("Short")){
            String passagetoSend = "The quick brown fox jumps over the lazy dog. It is often used to practice typing because it includes every letter.";
        }
        else if (gameinfo.len.equals("Medium")){
            String passagetoSend = "Typing is a useful skill that improves with regular practice. By focusing on accuracy first, you can build a strong foundation and gradually increase your typing speed over time.";
        }
        else if (gameinfo.len.equals("Long")){
            String passagetoSend = "Typing is an essential skill in today’s digital world, helping people communicate quickly and efficiently. With consistent practice and proper technique, you can improve both speed and accuracy, allowing your thoughts to flow more naturally as you type.";
        }
        else if (gameinfo.len.equals("Custom")){
            String passagetoSend = gameinfo.passage;
        }
        

        
    }
}
