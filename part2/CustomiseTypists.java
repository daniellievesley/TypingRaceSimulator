package part2;
import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class CustomiseTypists extends JPanel {
    String len;
    String passage;
    Integer seats;
    boolean auto;
    boolean caffeine;
    boolean night;

    public CustomiseTypists(JPanel mainPanel) {
        setLayout(new GridLayout(0,1));
        JLabel label = new JLabel("Customising typists", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }

    public void takeData(String len, String passage, Integer seats, Boolean auto, Boolean caffeine, boolean night, JPanel app, RacePage racePage) {
        this.len = len;
        this.passage = passage;
        this.seats = seats;
        this.auto = auto;
        this.caffeine = caffeine;
        this.night = night;
        List<JPanel> typistPanels = new ArrayList<>();
        for (int i=1; i<=seats; i++){
            JPanel typistChildPanel = new JPanel();
            JLabel typistLabel = new JLabel("Typist " + i);
            typistLabel.setFont(typistLabel.getFont().deriveFont(Font.BOLD));
            typistChildPanel.add(typistLabel);
            add(typistChildPanel);
            // customising each typist - controls 
            String[] styles = {"Touch Typist", "Hunt & Peck", "Phone Thumbs", "Voice-to-Text"};
            JComboBox<String> typingStyle = new JComboBox<>(styles);
            typistChildPanel.add(typingStyle);
            String[] keyboardTypes = {"Mechanical", "Membrane", "Touchscreen", "Stenography"};
            JComboBox<String> keyboardType = new JComboBox<>(keyboardTypes);
            typistChildPanel.add(keyboardType);
            JTextField symbol = new JTextField(10);
            typistChildPanel.add(symbol);
            JButton clrPickerReq = new JButton("Pick a typist colour");
            typistChildPanel.add(clrPickerReq);
            typistPanels.add(typistChildPanel);
            clrPickerReq.addActionListener(e -> {
                JColorChooser clrChooser = new JColorChooser();
                Color colorTypist = JColorChooser.showDialog(null, "Pick a typist colour", Color.blue);
                System.out.println(colorTypist);
                typistChildPanel.add(clrChooser);
            });

        } 
        JButton contToGame = new JButton("Start game now");
        add(contToGame);

        contToGame.addActionListener(e -> {
            CardLayout layout = (CardLayout) app.getLayout();
            createTypists(typistPanels);
            
            // racePage.passData()
            //layout.show(app, "RacePage");
    });

}

public void createTypists(List<JPanel> panels){
    for (int i=0; i<panels.size(); i++){
        Component[] c = panels.get(i).getComponents();
        for (int j=0; j<c.length; j++){

        }
    }
}
}
