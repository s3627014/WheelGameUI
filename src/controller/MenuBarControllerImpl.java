package controller;

import controller.interfaces.MenuBarController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.dialogBoxes.AboutDialogBox;
import view.dialogBoxes.AddPlayerDialogBox;
import view.dialogBoxes.BetDialogBox;
import view.dialogBoxes.RemovePlayerDialogBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class MenuBarControllerImpl implements MenuBarController {

    private PropertyChangeSupport pcs;
    private GameEngine gameEngine;

    public MenuBarControllerImpl(GameEngine gameEngine) {
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

    @Override
    public void setPCS(PropertyChangeSupport pcs) {
        this.pcs = pcs;
    }

    @Override
    public void addPlayerPopupDialog() {
        new AddPlayerDialogBox(gameEngine, pcs);
    }

    @Override
    public void removePlayerDialog() {
        var choices = new ArrayList<String>();

        for (Player player : gameEngine.getAllPlayers()
        ) {
            choices.add(player.getPlayerId() + " : " + player.getPlayerName());
        }

        //Error if no players to remove.
        if (choices.size() == 0) {
            JOptionPane.showMessageDialog(null, "No players to remove!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new RemovePlayerDialogBox(gameEngine, pcs);
    }

    @Override
    public void betDialog() {
        var allPlayers = gameEngine.getAllPlayers();

        //If no players are found show error.
        if (allPlayers.size() == 0) {
            JOptionPane.showMessageDialog(null, "No players found!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }


        new BetDialogBox(gameEngine, pcs);


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

    @Override
    public void aboutDialog() {
        new AboutDialogBox();
    }

}
