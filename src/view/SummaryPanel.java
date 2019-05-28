package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SummaryPanel extends JPanel implements PropertyChangeListener {

    private GameEngine gameEngine;
    private JTextPane textPane = new JTextPane();


    public SummaryPanel(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        setBackground(new Color(32, 118, 31));
        textPane.setPreferredSize(new Dimension(200, 620));
        textPane.setEditable(false);
        textPane.setForeground(Color.WHITE);
        textPane.setBackground(new Color(32, 118, 31));
        textPane.setText("       ***** Player Summary *****");
        add(textPane);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
    }

    public void propertyChange(PropertyChangeEvent pce){
                updateText(pce.getPropertyName(), pce.getNewValue(), pce.getOldValue());
    }

    private void updateText(String event, Object winners, Object losers){
        textPane.setText("       ***** Player Summary *****");
        String playerSummary = "\n";
        for (Player player: gameEngine.getAllPlayers()
        ) {
            var result = "";
            var name = player.getPlayerName();
            var points = player.getPoints();
            var bet = player.getBet();
            var betType = player.getBetType();
            if (event == "Spin complete"){
                for (String id: (ArrayList<String>) winners
                     ) {
                    if (player.getPlayerId() == id) {
                        result = " : WIN";
                    }
                }
                for (String id: (ArrayList<String>) losers
                ) {
                    if (player.getPlayerId() == id) {
                        result = " : LOSS";
                    }
                }

            }
            if (betType == null){
                playerSummary += "\n\n" + name + result +"\n__________________________\nPoints: " + points +"\nBet: 0";
            }
            else{
                playerSummary += "\n\n" + name + result + "\n__________________________\nPoints: " + points +"\nBet: " + bet + " placed on " + player.getBetType();
            }

        }
        textPane.setText(textPane.getText() + playerSummary);
    }

}
