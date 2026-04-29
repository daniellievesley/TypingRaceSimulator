package part2;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import part1.TypingRace;
import part1.Typist;

public class Leaderboard extends JPanel{
    JPanel app;
    public Leaderboard(JPanel mainPanel){
        setLayout(new BorderLayout());
    }

    public void create(TypingRace game, JPanel app){
        removeAll();
        this.app = app;
        JPanel tablePanel = new JPanel();
        JPanel comparisonView = new JPanel();
        comparisonView.setLayout(new BoxLayout(comparisonView, BoxLayout.Y_AXIS));
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        add(tablePanel, BorderLayout.NORTH);
        add(comparisonView, BorderLayout.CENTER);
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable (model);
        model.addColumn("Rank");
        model.addColumn("Name");
        model.addColumn("Points");
        Typist[] t = game.getTypists();
        Arrays.sort(t, Comparator.comparing(Typist::getPoints).reversed()); // typists sorted on order of H-L points
        for (int i=0; i<t.length; i++){
            Object[] row = {i+1, game.getTypists()[i].getName(), game.getTypists()[i].getPoints()};
            model.insertRow(i, row);
        }
        tablePanel.add(table);

        // comparison view 
        List<JCheckBox> typistSelectionBoxes = new ArrayList<>();
        for (int i=0; i<game.getTypists().length; i++){
            JCheckBox typistSelection = new JCheckBox(game.getTypists()[i].getName());
            typistSelectionBoxes.add(typistSelection);
            comparisonView.add(typistSelection);
        }

        List<String> metricToCompare = List.of("WPM", "Points", "Accuracy");
        JComboBox<String> metricChosen = new JComboBox<>(metricToCompare.toArray(new String[0]));
        comparisonView.add(metricChosen);
        JButton compareTypistsReq = new JButton("Compare typists");
        comparisonView.add(compareTypistsReq);
        JTextArea comparisonOutput = new JTextArea();
        comparisonView.add(comparisonOutput);
        comparisonOutput.setEditable(false);
        compareTypistsReq.addActionListener(e -> {
            comparisonOutput.setText("");
            List<Typist> typiststoCompare = new ArrayList<>();
            for (int i=0; i<typistSelectionBoxes.size(); i++){
                if (typistSelectionBoxes.get(i).isSelected()){
                    typiststoCompare.add(game.getTypists()[i]);
                }
            }
            if (metricChosen.getSelectedItem().equals("WPM")){
                for (int i=0; i<typiststoCompare.size(); i++){
                    comparisonOutput.append(typiststoCompare.get(i).getName() + ": " + typiststoCompare.get(i).getBestWPMSoFar() + "\n");
                }
            }
            else if (metricChosen.getSelectedItem().equals("Points")){
                for (int i=0; i<typiststoCompare.size(); i++){
                    comparisonOutput.append(typiststoCompare.get(i).getName() + ": " + typiststoCompare.get(i).getPoints() + "\n");
                }
            }
            else if (metricChosen.getSelectedItem().equals("Accuracy")){
                for (int i=0; i<typiststoCompare.size(); i++){
                    comparisonOutput.append(typiststoCompare.get(i).getName() + ": " + typiststoCompare.get(i).getAccuracy() + "\n");
                }
            }

        });
        JButton returntoGame = new JButton("Return to game");
        add(returntoGame, BorderLayout.SOUTH);
        returntoGame.addActionListener(e -> {
            CardLayout layout = (CardLayout) app.getLayout();
            layout.show(app, "RacePage");

        });
    }
}