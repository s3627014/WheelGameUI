package view.dialogBoxes;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import java.beans.PropertyChangeSupport;
import java.util.Collection;

public class AddPlayerDialogBox {

    public AddPlayerDialogBox(GameEngine gameEngine, PropertyChangeSupport pcs) {
        JTextField name = new JTextField();
        SpinnerModel model = new SpinnerNumberModel(100, 1, 10000, 5);

        JSpinner points = new JSpinner(model);

        Object[] message = {
                "Name:", name,
                "Points:", points,
                "(Any non numeric characters will be ignored)"
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (name.getText().trim().length() == 0){
                JOptionPane.showMessageDialog(null, "Name cannot be empty");
                new AddPlayerDialogBox(gameEngine, pcs);
                return;
            }

            int value = (Integer) points.getValue();

            gameEngine.addPlayer(new SimplePlayer(getUniqueId(gameEngine.getAllPlayers(), 0), name.getText(), value));
            pcs.firePropertyChange("Player Added", false, true);
        }

    }

    private String getUniqueId(Collection<Player> players, int id){
        String stringId;
        while (true){
            stringId = Integer.toString(id);

            for (Player player: players
            ) {
                if (player.getPlayerId().equals(stringId)){
                    id++;
                }
            }
            if (stringId.equals(Integer.toString(id))){
                break;
            }
        }
        return stringId;
    }

}
