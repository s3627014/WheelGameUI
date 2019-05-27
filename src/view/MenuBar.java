package view;

import controller.MenuBarController;

import javax.swing.*;

public class MenuBar  extends JMenuBar {
    private JMenuItem newItem, openItem, closeItem, saveItem, saveAsItem, printItem, addPlayerItem, removePlayerItem, setBetItem;

    public MenuBar(MenuBarController controller) {

        JMenu gameMenu = new JMenu("Game");
        JMenu playersMenu = new JMenu("Players");
        newItem = gameMenu.add("New");
        openItem = gameMenu.add("Open");
        closeItem = gameMenu.add("Close");
        gameMenu.addSeparator();
        saveItem = gameMenu.add("Save");
        saveAsItem = gameMenu.add("Save As...");
        gameMenu.addSeparator();
        printItem = gameMenu.add("Print");
        addPlayerItem = playersMenu.add("Add player");
        removePlayerItem = playersMenu.add("Remove player");
        setBetItem = playersMenu.add("Set Bet");

        add(gameMenu);
        add(playersMenu);

        addPlayerItem.addActionListener(controller);
    }
}