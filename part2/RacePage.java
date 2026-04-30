/**
 * RacePage is the class which houses the visuals of the race within the application
 * This class uses the race logic within the TypingRace class along with Swing visuals
 * This card is displayed within wider app when all customisations/settings have been chosen
 *
 * @author Daniel Lievesley
 * @version 1.0
 */

package part2;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * RacePage is the class which houses the visuals of the race within the application
 * This class uses the race logic within the TypingRace class along with Swing visuals
 * This card is displayed within wider app when all customisations/settings have been chosen
 */
public class RacePage extends JPanel {
    TypingRace game;
    List<JLabel>passageProgresses;
    Timer t;
    JPanel footer;
    JPanel winnerDisplay;
    List<JLabel>statusUpdates;
    long startTime;
    long endTime;
    //JPanel mainPanel;
    JPanel app;
    Leaderboard board;

    /**
     * constructor for object RacePage
     * creates object RacePage
     */
    public RacePage(){
    }

    /**
     * passData() method which creates game and typists based on settings chosen
     * using swing to display typist lanes and games visual progress
     * @param gameInfo contains the GameData object which holds customisations for global settings
     * @param typistData contains list of typist's individual settings
     * @param app contains the app panel which this is displayed in
     * @param board contains the leaderboard object which is the card following this
     */
    public void passData (GameData gameinfo, List<TypistRowData> typistData, JPanel app, Leaderboard board){
        this.app = app;
        this.board = board;
        String passagetoSend = "Sample passage";
        // selection statements to determine passage to be used (pre made or custom)
        if (gameinfo.getLen().equals("Short")){
            passagetoSend = "Malta is an island nation in Europe. With the smallest population in the European Union (EU), it packs a punch!";
        }
        else if (gameinfo.getLen().equals("Medium")){
            passagetoSend = "Mile End is a district in the London Borough of Tower Hamlets. Home to Queen Mary, University of London, Mile End was named after it's one mile location on the old Colchester road, leading from Aldgate.";
        }
        else if (gameinfo.getLen().equals("Long")){
            passagetoSend = "Colchester Zoo is a popular attraction for those visiting or living in Essex. Frequently ranked as one of the best zoos in the United Kingdom, Colchester Zoo is full of interactive exhibits for kids, teenagers and adults! A beautfiful 60 acre site home to rhinos, giraffes, lions and even pygmy hippos.";
        }
        else if (gameinfo.getLen().equals("Custom")){
            passagetoSend = gameinfo.getPassage();
        }
        // number of characters in passage
        int passageLength = passagetoSend.length();

        // reset screen - remove existing races etc
        removeAll();
        // using border layout
        setLayout(new BorderLayout());
        // footer will hold messages about wins etc
        footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        add(footer, BorderLayout.SOUTH);

        // initialisation of TypingRace object
        TypingRace game = new TypingRace(passagetoSend, gameinfo, this);
        //stored as attribute of this object
        this.game = game;
        
        // determine typist attributes based on settings chosen
        for (int i=0; i<typistData.size(); i++){
            TypistRowData d = typistData.get(i);
            double acc = 0.9;
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

            
            Typist t = new Typist(d.getSymbol().getText().trim().charAt(0), d.getTypistName().getText().trim(), acc, mistypeBase, duration, d.getEnergy().isSelected(), d.getColour());
            game.addTypist(t, i+1);
        }

        // area to display passage 
        JTextArea passageTxt = new JTextArea(passagetoSend);
        add(new JScrollPane(passageTxt), BorderLayout.NORTH);
        passageTxt.setEditable(false);

        // area to display races (rows)
        JPanel typistLanes = new JPanel();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        typistLanes.setLayout(new BoxLayout(typistLanes, BoxLayout.Y_AXIS));
        add(typistLanes, BorderLayout.CENTER);
        List<JLabel>passageProgresses=new ArrayList<>();
        this.passageProgresses=passageProgresses;
        List<JLabel>statusUpdates=new ArrayList<>();
        this.statusUpdates=statusUpdates;
        
        // creating row with symbol, passage and update area 
        for (int i=0; i<typistData.size(); i++){
            JPanel row = new JPanel(new BorderLayout());
            row.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel typistSym = new JLabel(typistData.get(i).getSymbol().getText() + "     ");
            row.add(typistSym, BorderLayout.WEST);
            JLabel statusUpdate = new JLabel();
            row.add(statusUpdate, BorderLayout.EAST);
            statusUpdates.add(statusUpdate);
            JLabel passage = new JLabel();
            passageProgresses.add(passage);
            passage.setText(passagetoSend);
            row.add(passage, BorderLayout.CENTER);
            typistLanes.add(row);
        }
        // record time and begin race
        recordStartTime();
        newRound();
        // output area for 'winner display' containing who won and accuracy change message etc
        this.winnerDisplay = new JPanel();
        this.winnerDisplay.setLayout(new BoxLayout(this.winnerDisplay, BoxLayout.Y_AXIS));
        footer.add(winnerDisplay);
        // buttons to start new race or view leaderboard both with action listeners
        JButton newRace = new JButton("Start new race");
        JButton viewLeaderboard = new JButton("View leaderboard");
        footer.add(newRace);
        footer.add(viewLeaderboard);
        newRace.addActionListener(e ->  {
            winnerDisplay.removeAll();
            newRound();
        });
        viewLeaderboard.addActionListener(e -> {
            CardLayout layout = (CardLayout) app.getLayout();
            board.create(game, app);
            layout.show(app, "Leaderboard");

        });
    }

    /**
     * recordStartTime() method which record current time and store this as obj attribute
     */
    public void recordStartTime(){
        this.startTime = System.nanoTime();
    }

    /**
     * recordEndTime() method which record current time and store this as obj attribute
     */
    public void recordEndTime(){
        this.endTime = System.nanoTime();
    }

    /**
     * recordStartTime() method which returns start time attribute
     * @return time stored as start 
     */
    public long getStartTime(){
        return this.startTime;
    }

    /**
     * recordEndTime() method which returns end time attribute
     * @return time stored as end time
     */
    public long getEndTime(){
        return this.endTime;
    }

    /**
     * showTypistUpdate() method which update the row of the typist
     * @param updatetoDisplay string which will be displayed
     * @param identifier the number of the typist - which will be used to show it in the correct row
     */
    public void showTypistUpdate(String updatetoDisplay, int identifier){
        for (int i=0; i<statusUpdates.size(); i++){
            if (identifier==i){
                statusUpdates.get(i).removeAll();
                statusUpdates.get(i).setText(updatetoDisplay);
                return;
            }
        }
    }


    /**
     * newRound() method which creates a new race starting a turn on game object and timing it 
     */
    public void newRound(){
        game.resetAll();
        
        Timer t = new Timer(240, e -> {
            game.startTurn();
            displayProgress();
            recordEndTime();
            if (game.isFinished()){
                ((Timer) e.getSource()).stop();
                
            }
        });

        displayProgress();
        t.start();

    }

    /**
     * printResults() method which will print the string into the winner panel (winnerDisplay)
     * @param winnerText - contains the string to be displayed
     */
    public void printResults(String winnerText){
        if (winnerDisplay == null) {
            return;
        }
        JLabel texttoShow = new JLabel(winnerText);
        winnerDisplay.add(texttoShow);
    }
    
    /**
     * displayProgress() method which shows the live progress of each typist using HTML styling to colour in the chosen colour by the typist, as according to progress from game logic
     */
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
            String chosenColor = String.format("#%02x%02x%02x", typistsInGame[i].getColour().getRed(), typistsInGame[i].getColour().getBlue(), typistsInGame[i].getColour().getGreen());
            String toInsert = "<html> <span style='background-color: green'>" + before + "</span> <span style='background-color: " + chosenColor + ";'>" + current + "</span>" + after + "</html>";
            passageProgresses.get(i).setText(toInsert);

        }
    }

}
