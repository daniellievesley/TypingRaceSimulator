package part2;
import java.util.List;
import javax.swing.*;
import part1.TypingRace;
import part1.Typist;

public class RacePage extends JPanel {
    public RacePage(JPanel mainPanel){
        JLabel label = new JLabel("RACE PAGE", SwingConstants.CENTER);
        JButton finishBtn = new JButton("Finish race");
    }

    public void passData (GameData gameinfo, List<TypistRowData> typistData){
        String passagetoSend = "The quick brown fox jumps over the lazy dog. It is often used to practice typing because it includes every letter.";
        if (gameinfo.getLen().equals("Short")){
            passagetoSend = "The quick brown fox jumps over the lazy dog. It is often used to practice typing because it includes every letter.";
        }
        else if (gameinfo.getLen().equals("Medium")){
            passagetoSend = "Typing is a useful skill that improves with regular practice. By focusing on accuracy first, you can build a strong foundation and gradually increase your typing speed over time.";
        }
        else if (gameinfo.getLen().equals("Long")){
            passagetoSend = "Typing is an essential skill in today’s digital world, helping people communicate quickly and efficiently. With consistent practice and proper technique, you can improve both speed and accuracy, allowing your thoughts to flow more naturally as you type.";
        }
        else if (gameinfo.getLen().equals("Custom")){
            passagetoSend = gameinfo.getPassage();
        }

        TypingRace game = new TypingRace(passagetoSend);
        
        for (int i=0; i<typistData.size(); i++){
            TypistRowData d = typistData.get(i);
            double acc = 0.7;
            if (d.getTypingStyle().getSelectedItem().equals("Touch Typist")){
                acc=acc-0.1;
            }
            else if (d.getTypingStyle().getSelectedItem().equals("Hunt & Peck")){
                acc=acc-0.15;
            }
            else if (d.getTypingStyle().getSelectedItem().equals("Voice-to-Text")){
                acc=acc-0.2;
            }
            
            Typist t = new Typist(d.getSymbol().getText().trim().charAt(0), d.getTypistName().getText().trim(), acc);
            game.addTypist(t, i);
        }


        
    }
}
