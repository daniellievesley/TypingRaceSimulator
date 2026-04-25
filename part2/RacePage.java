package part2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import part1.TypingRace;
import part1.Typist;

public class RacePage extends JPanel {
    TypingRace game;
    List<JLabel>passageProgresses;
    Timer t;

    public RacePage(JPanel mainPanel){
        JLabel label = new JLabel("RACE PAGE", SwingConstants.CENTER);
        JButton finishBtn = new JButton("Finish race");
    }

    public void passData (GameData gameinfo, List<TypistRowData> typistData){
        String passagetoSend = "The quick brown fox jumps over the lazy dog. It is often used to practice typing because it includes every letter.";
        int passageLength = 123;
        if (gameinfo.getLen().equals("Short")){
            passagetoSend = "The quick brown fox jumps over the lazy dog. It is often used to practice typing because it includes every letter.";
        }
        else if (gameinfo.getLen().equals("Medium")){
            passagetoSend = "Typing is a useful skill that improves with regular practice. By focusing on accuracy first, you can build a strong foundation and gradually increase your typing speed over time.";
            passageLength = 187;
        }
        else if (gameinfo.getLen().equals("Long")){
            passagetoSend = "Typing is an essential skill in today’s digital world, helping people communicate quickly and efficiently. With consistent practice and proper technique, you can improve both speed and accuracy, allowing your thoughts to flow more naturally as you type.";
            passageLength= 262;
        }
        else if (gameinfo.getLen().equals("Custom")){
            passagetoSend = gameinfo.getPassage();
            passageLength = gameinfo.getPassage().length();
        }

        removeAll();
        setLayout(new BorderLayout());

        TypingRace game = new TypingRace(passagetoSend, gameinfo);
        this.game = game;
        
        for (int i=0; i<typistData.size(); i++){
            TypistRowData d = typistData.get(i);
            double acc = 0.7;
            if (d.getTypingStyle().getSelectedItem().equals("Touch Typist")){
                acc=acc-0.1; // accuracy decreased
            }
            else if (d.getTypingStyle().getSelectedItem().equals("Hunt & Peck")){
                acc=acc-0.15; // accuracy decreased
            }
            else if (d.getTypingStyle().getSelectedItem().equals("Voice-to-Text")){
                acc=acc-0.2; // accuracy decreased
            }

            double mistypeBase = 0.1;
            if (d.getKeyboardType().getSelectedItem().equals("Mechanical")){
                mistypeBase=mistypeBase*0.9; // mistype chance decreased
            }
            else if (d.getKeyboardType().getSelectedItem().equals("Membrane")){
                mistypeBase=mistypeBase*1.1; // mistype chance increased
            }
            else if (d.getKeyboardType().getSelectedItem().equals("Stenography")){
                mistypeBase=mistypeBase*1.1; // mistype chance increased
            }

            int duration = 3;
            if (d.getWrist().isSelected()==true){
                duration = 2; // wrist support decreases duration of burn out 
            }
            if (d.getHeadphones().isSelected()==true){
                mistypeBase=mistypeBase*0.8; // mistype chance decreased due to headphones
            }

            if (gameinfo.getNight()){
                acc=acc*0.7; // accuracy reduced for everyone due to night mode
            }

            
            Typist t = new Typist(d.getSymbol().getText().trim().charAt(0), d.getTypistName().getText().trim(), acc, mistypeBase, duration, d.getEnergy().isSelected());
            game.addTypist(t, i+1);
        }

        // area to display passage 
        JTextArea passageTxt = new JTextArea(passagetoSend);
        add(new JScrollPane(passageTxt), BorderLayout.NORTH);

        // area to display races (rows)
        JPanel typistLanes = new JPanel();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        typistLanes.setLayout(new BoxLayout(typistLanes, BoxLayout.Y_AXIS));
        add(typistLanes, BorderLayout.CENTER);
        List<JLabel>passageProgresses=new ArrayList<>();
        this.passageProgresses=passageProgresses;
        for (int i=0; i<typistData.size(); i++){
            JPanel row = new JPanel(new BorderLayout());
            row.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel typistSym = new JLabel(typistData.get(i).getSymbol().getText() + "     ");
            row.add(typistSym, BorderLayout.WEST);
            JLabel passage = new JLabel();
            passageProgresses.add(passage);
            //passage.setEditable(false);
            passage.setText(passagetoSend);
            row.add(passage, BorderLayout.CENTER);
            typistLanes.add(row);
        }

        game.resetAll();
        
        Timer t = new Timer(120, e -> {
            game.startTurn();
            displayProgress();
            if (game.isFinished()){
                ((Timer) e.getSource()).stop();
                //showResults();
            }
        });

        displayProgress();
        t.start();

    }
        
    public void displayProgress(){
        Typist[] typistsInGame = game.getTypists();
        for (int i=0; i<game.getTypists().length; i++){
            String passage = game.getPassage();
            int individualProgress = typistsInGame[i].getProgress();
            if (individualProgress>=passage.length()){
                individualProgress=passage.length()-1;
            }
            String before = passage.substring(0, individualProgress);
            String current = passage.substring(individualProgress, individualProgress+1);
            String after = passage.substring(individualProgress+1, passage.length());
            String toInsert = "<html> <span style='background-color: green'>" + before + "</span> <span style='background-color: yellow'>" + current + "</span>" + after + "</html>";
            passageProgresses.get(i).setText(toInsert);

        }
    }

}
