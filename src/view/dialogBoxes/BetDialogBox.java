package view.dialogBoxes;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class BetDialogBox {
    private GameEngine gameEngine;
    public BetDialogBox(GameEngine gameEngine, PropertyChangeSupport pcs) {
        this.gameEngine = gameEngine;
        SpinnerModel model = new SpinnerNumberModel();
        JSpinner bet = new JSpinner(model);
        Object[] playerList = generatePlayerDropdownList().toArray();
        Object[] betTypes = BetType.values();
        JComboBox<Object> playerComboBox = new JComboBox<>(playerList);
        JComboBox<Object> betTypesComboBox = new JComboBox<>(betTypes);
        setBetRange(playerComboBox, model);
        playerComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                setBetRange(playerComboBox, model);
            }
        });

        Object[] message = {
                "list:", playerComboBox,
                "Bet Type:", betTypesComboBox,
                "Bet:", bet
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            var betValue = (Integer) bet.getValue();
            var player = getPlayerFromComboBox(playerComboBox.getSelectedItem().toString());
            var betType = (betTypesComboBox.getSelectedItem());
            player.setBet(betValue);
            player.setBetType((BetType) betType);
            if (betValue == 0) {
                JOptionPane.showMessageDialog(null,
                        "A bet of 0 has been placed. " +
                                "You will not be participating in this round.");
                player.setBetType(null);

                return;
            }
            if (betValue == ((SpinnerNumberModel) model).getMaximum()) {
                JOptionPane.showMessageDialog(null,
                        "Maximum Bet limit reached. " +
                                "Bet placed is " + betValue);
            }
            pcs.firePropertyChange("Bet Set", false, true);
        }
    }
    private ArrayList<String> generatePlayerDropdownList() {
        var playerList = new ArrayList<String>();
        for (Player player : gameEngine.getAllPlayers()
        ) {
            playerList.add(player.getPlayerId() + " : " + player.getPlayerName());
        }
        return playerList;
    }
    private Player getPlayerFromComboBox(String input) {
        var playerId = input.split(" ")[0];
        return gameEngine.getPlayer(playerId);
    }
    private void setBetRange(JComboBox<Object> comboBox, SpinnerModel model) {
        var selection = comboBox.getSelectedItem().toString();
        var selectedPlayer = getPlayerFromComboBox(selection);
        ((SpinnerNumberModel) model).setMaximum(selectedPlayer.getPoints());
        model.setValue(selectedPlayer.getPoints());
        ((SpinnerNumberModel) model).setStepSize(5);
        ((SpinnerNumberModel) model).setMinimum(0);
    }
}
