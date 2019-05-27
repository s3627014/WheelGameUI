package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SummaryPanel extends JPanel implements PropertyChangeListener {

    private GameEngine gameEngine;
    private JTextPane textPane = new JTextPane();


    public SummaryPanel(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        setBackground(Color.RED);
        textPane.setPreferredSize(new Dimension(200, 620));
        textPane.setEditable(false);
        textPane.setText("***** Wheel Game *****\n\nPlayers:");
        add(textPane);
    }

    public void propertyChange(PropertyChangeEvent pce){
        System.out.println("FIRED");
        textPane.setText("***** Wheel Game *****\n\nPlayers:");
        String playerSummary = "\n";
        for (Player player: gameEngine.getAllPlayers()
             ) {
            var name = player.getPlayerName();
            playerSummary += name + "\n";

        }
        textPane.setText(textPane.getText() + playerSummary);

    }

}
