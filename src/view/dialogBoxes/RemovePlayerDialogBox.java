package view.dialogBoxes;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import javax.swing.*;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class RemovePlayerDialogBox {
    private GameEngine gameEngine;
    public RemovePlayerDialogBox(GameEngine gameEngine, PropertyChangeSupport pcs) {
        this.gameEngine = gameEngine;
        Object[] playerList = generatePlayerDropdownList().toArray();
        JComboBox<Object> playerComboBox = new JComboBox<>(playerList);
        Object[] message = {
                "list:", playerComboBox,
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            var player = getPlayerFromComboBox(playerComboBox.getSelectedItem().toString());
            gameEngine.removePlayer(player);
            pcs.firePropertyChange("Player Removed", false, true);
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
}
