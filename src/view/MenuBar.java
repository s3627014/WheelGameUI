package view;

import controller.interfaces.MenuBarController;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuBar  extends JMenuBar implements PropertyChangeListener {

    private JMenuItem closeItem, aboutItem, addPlayerItem, removePlayerItem, setBetItem;
    private JMenu playersMenu = new JMenu("Players");
    private JMenu gameMenu = new JMenu("Game");

    MenuBar(MenuBarController controller) {


        aboutItem = gameMenu.add("About");
        closeItem = gameMenu.add("Close");
        addPlayerItem = playersMenu.add("Add player");
        removePlayerItem = playersMenu.add("Remove player");
        setBetItem = playersMenu.add("Set player bet");

        addPlayerItem.addActionListener(controller);
        removePlayerItem.addActionListener(controller);
        setBetItem.addActionListener(controller);
        aboutItem.addActionListener(controller);
        closeItem.addActionListener(controller);

        add(gameMenu);
        add(playersMenu);

    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        playersMenu.setEnabled(pce.getPropertyName().equals("Spin complete") || pce.getPropertyName() == "Spin complete, not bets placed");
    }
}