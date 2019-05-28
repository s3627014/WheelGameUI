package view;

import controller.MenuBarController;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuBar  extends JMenuBar implements PropertyChangeListener {
    private JMenuItem closeItem, aboutItem, addPlayerItem, removePlayerItem, setBetItem;
    private JMenu playersMenu = new JMenu("Players");

    public MenuBar(MenuBarController controller) {

        JMenu gameMenu = new JMenu("Game");

        aboutItem = gameMenu.add("About");
        closeItem = gameMenu.add("Close");
        addPlayerItem = playersMenu.add("Add player");
        removePlayerItem = playersMenu.add("Remove player");
        setBetItem = playersMenu.add("Set player bet");

        addPlayerItem.addActionListener(controller);
        removePlayerItem.addActionListener(controller);
        setBetItem.addActionListener(controller);
        add(gameMenu);
        add(playersMenu);

    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        playersMenu.setEnabled(pce.getPropertyName() == "Spin complete");
    }
}