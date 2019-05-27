package view;

import controller.MenuBarController;
import model.GameEngineImpl;
import model.interfaces.GameEngine;

import java.awt.*;

import javax.swing.*;

import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AppFrame extends JFrame {

	private GameEngine gameEngine = new GameEngineImpl();
	private SummaryPanel summaryPanel = new SummaryPanel(gameEngine);
	private WheelPanel wheelPanel = new WheelPanel();
	private JMenuBar menubar = new JMenuBar();
	private StatusBar statusBar = new StatusBar();
	private MenuBarController menuBarController = new MenuBarController(gameEngine);

	public AppFrame() {

		//Set layout of Frame
		super("WheelGameUI");
		setLayout(new BorderLayout());
		setBounds(100, 100, 1280, 720);

		//Create controller property change listeners
		PropertyChangeSupport menuBarPcs = new PropertyChangeSupport(menuBarController);
		menuBarPcs.addPropertyChangeListener(summaryPanel);
		menuBarPcs.addPropertyChangeListener(statusBar);

		menuBarController.setPCS(menuBarPcs);


		//Create and add the menu bar
		JMenu menu = new JMenu("size");
		JMenuItem size = new JMenuItem("size");
		menu.add(size);
		menubar.add(new MenuBar(menuBarController));
		setJMenuBar(menubar);

		//Add summary pannel
		add(summaryPanel, BorderLayout.WEST);

		//Add wheel panel
		add(wheelPanel, BorderLayout.CENTER);

		//Add status bar
		add(statusBar, BorderLayout.SOUTH);


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
}