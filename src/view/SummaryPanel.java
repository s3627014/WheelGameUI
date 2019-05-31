package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class SummaryPanel extends JPanel implements PropertyChangeListener {

    private GameEngine gameEngine;
    private JTextPane textPane = new JTextPane();


    SummaryPanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        setBackground(new Color(32, 118, 31));
        textPane.setPreferredSize(new Dimension(200, 620));
        textPane.setEditable(false);
        textPane.setForeground(Color.WHITE);
        textPane.setBackground(new Color(32, 118, 31));
        textPane.setText("       ***** Player Summary *****");
        add(textPane);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        setBorder(blackLine);
    }

    public void propertyChange(PropertyChangeEvent pce) {
        //Dont update the summary panel if wheel is spinning
        if (!pce.getPropertyName().equals("Spinning...")) {
            updateText(pce.getPropertyName(), pce.getNewValue(), pce.getOldValue());
        }
    }


    /**
     * Updates the player summary.
     *
     * @param event   that triggered the update.
     * @param winners list of players who won.
     * @param losers  list of players who lost.
     */
    private void updateText(String event, Object winners, Object losers) {
        textPane.setText("       ***** Player Summary *****");
        String playerSummary = "\n";
        for (Player player : gameEngine.getAllPlayers()
        ) {
            var result = "";
            var playerId = player.getPlayerId();
            var name = player.getPlayerName();
            var points = player.getPoints();
            var bet = player.getBet();
            var betType = player.getBetType();
            if (event == "Spin complete") {
                for (String id : (ArrayList<String>) winners
                ) {
                    if (player.getPlayerId() == id) {
                        result = " : WIN";
                    }
                }
                for (String id : (ArrayList<String>) losers
                ) {
                    if (playerId == id) {
                        result = " : LOSS";
                    }
                }

            }

            playerSummary += String.format("\n\n(%s) %s%s\n__________________________\nPoints: %s",
                    playerId,
                    name,
                    result,
                    points,
                    bet,
                    player.getBetType());

            if (betType != null) {
                playerSummary += String.format("\nBet: %s placed on %s",
                        bet,
                        player.getBetType());
            }

        }
        textPane.setText(textPane.getText() + playerSummary);
    }

}
