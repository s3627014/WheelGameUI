package controller;

import model.SimplePlayer;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class MenuBarController implements ActionListener {

    private PropertyChangeSupport pcs;
    private GameEngine gameEngine;
    private int idCount = 0;

    public MenuBarController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Add player":
                addPlayerPopupDialog();
                break;
            case "Remove player":
                removePlayerDialog();
                break;
            case "Set player bet":
                betDialog();
                break;
            default:
                // code block
        }


    }

    public void setPCS(PropertyChangeSupport pcs) {
        this.pcs = pcs;
    }

    private void addPlayerPopupDialog() {
        JTextField name = new JTextField();

        SpinnerModel model = new SpinnerNumberModel(100, 1, 1000, 50);
        JSpinner points = new JSpinner(model);

        Object[] message = {
                "Name:", name,
                "Points:", points
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int value = (Integer) points.getValue();
            gameEngine.addPlayer(new SimplePlayer(String.valueOf(idCount), name.getText(), value));
            idCount++; //will work for now
        }
        pcs.firePropertyChange("Player Added", false, ": " + name.getText());
    }

    private void removePlayerDialog() {
        var choices = new ArrayList<String>();
        for (Player player : gameEngine.getAllPlayers()
        ) {
            choices.add(player.getPlayerId() + " : " + player.getPlayerName());
        }

        if (choices.size() == 0) {
            JOptionPane.showMessageDialog(null, "No players to remove!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String input = (String) JOptionPane.showInputDialog(null, "Choose a player to remove",
                "Remove Player", JOptionPane.WARNING_MESSAGE, null, // Use
                // default
                // icon
                choices.toArray(), // Array of choices
                choices.get(0)); // Initial choice

        var playerId = input.split(" ")[0];
        var playerToRemove = gameEngine.getPlayer(playerId);
        gameEngine.removePlayer(playerToRemove);
        pcs.firePropertyChange("Player Removed", false, ": " + playerToRemove.getPlayerName());
    }

    private void betDialog() {
        if (gameEngine.getAllPlayers().size() == 0) {
            JOptionPane.showMessageDialog(null, "No players found!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
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

            pcs.firePropertyChange("Bet Set", false, ": " + betValue + " - " + betType);
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
