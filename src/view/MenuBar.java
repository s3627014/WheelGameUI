package view;

import controller.MenuBarController;

import javax.swing.*;

public class MenuBar  extends JMenuBar {
    private JMenuItem closeItem, aboutItem, addPlayerItem, removePlayerItem, setBetItem;

    public MenuBar(MenuBarController controller) {

        JMenu gameMenu = new JMenu("Game");
        JMenu playersMenu = new JMenu("Players");

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
}