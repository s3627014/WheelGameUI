package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.dialogBoxes.AboutDialogBox;
import view.dialogBoxes.AddPlayerDialogBox;
import view.dialogBoxes.BetDialogBox;
import view.dialogBoxes.RemovePlayerDialogBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class MenuBarController implements ActionListener {

    private PropertyChangeSupport pcs;
    private GameEngine gameEngine;

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
            case "About":
                aboutDialog();
                break;
            case "Close":
                System.exit(0);
                break;

        }


    }

    public void setPCS(PropertyChangeSupport pcs) {
        this.pcs = pcs;
    }

    private void addPlayerPopupDialog() {
        new AddPlayerDialogBox(gameEngine);
        pcs.firePropertyChange("Player Added", false, true);
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

        new RemovePlayerDialogBox(gameEngine);

        pcs.firePropertyChange("Player Removed", false, true);
    }

    private void betDialog() {
        var allPlayers = gameEngine.getAllPlayers();
        if (allPlayers.size() == 0) {
            JOptionPane.showMessageDialog(null, "No players found!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new BetDialogBox(gameEngine);
        pcs.firePropertyChange("Bet Set", false, true);

        //Check if a player still has a bet to place, if not spin the wheel.
        for (Player player: allPlayers
             ) {
            if (player.getBetType() == null){
                return;
            }
        }
        new Thread()
        {
            @Override
            public void run()
            {
                gameEngine.spin(1, 200, 5);

            }
        }.start();

    }

    private void aboutDialog() {
        new AboutDialogBox();
    }

}
