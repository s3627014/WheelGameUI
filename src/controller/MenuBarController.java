package controller;

import model.SimplePlayer;
import model.interfaces.GameEngine;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.text.NumberFormat;
import java.text.ParseException;

public class MenuBarController implements ActionListener {

    private PropertyChangeSupport pcs;
    private GameEngine gameEngine;

    public MenuBarController(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var currentPlayers = gameEngine.getAllPlayers();
        showAddPlayerPopup();
        pcs.firePropertyChange("Player Added", false,true );

    }

    public void setPCS(PropertyChangeSupport pcs)
    {
        this.pcs=pcs;
    }


    public void showAddPlayerPopup()  {
        JTextField name = new JTextField();
        SpinnerModel model = new SpinnerNumberModel(9.9, 1, 15, 0.1);
        JSpinner points = new JSpinner(model);
        Object[] message = {
                "Name:", name,
                "Points:", points
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int value = (Integer) points.getValue();

            gameEngine.addPlayer(new SimplePlayer("", name.getText(), value));
        }

    }
}
