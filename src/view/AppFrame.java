package view;

import controller.GameEngineCallbackController;
import controller.MenuBarController;
import controller.WheelPanelController;
import model.GameEngineImpl;
import model.interfaces.GameEngine;

import java.awt.*;

import javax.swing.*;

import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AppFrame extends JFrame {

	private JMenuBar menubar = new JMenuBar();
	private StatusBar statusBar = new StatusBar();

	public AppFrame(GameEngine gameEngine) {
		//Set layout + title of Frame
		super("WheelGameUI");
		MenuBarController menuBarController = new MenuBarController(gameEngine);
		WheelPanelController wheelPanelController = new WheelPanelController(gameEngine);
		GameEngineCallbackController gameEngineCallbackController = new GameEngineCallbackController(gameEngine);
		SummaryPanel summaryPanel = new SummaryPanel(gameEngine);

		WheelPanel wheelPanel = new WheelPanel(wheelPanelController);
		gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(gameEngineCallbackController));
		setLayout(new BorderLayout());
		setBounds(100, 100, 1280, 720);


		//Create and add the menu bar
		JMenu menu = new JMenu("size");
		JMenuItem size = new JMenuItem("size");
		menu.add(size);
		var menuBar = new MenuBar(menuBarController);
		menubar.add(menuBar);
		setJMenuBar(menuBar);



		//Create controller and assign property change listeners
		PropertyChangeSupport menuBarPcs = new PropertyChangeSupport(menuBarController);
		menuBarPcs.addPropertyChangeListener(summaryPanel);
		menuBarPcs.addPropertyChangeListener(statusBar);
		menuBarController.setPCS(menuBarPcs);

		PropertyChangeSupport wheelPanelPcs = new PropertyChangeSupport(wheelPanelController);
		wheelPanelPcs.addPropertyChangeListener(wheelPanel);
		wheelPanelController.setPCS(menuBarPcs);


		PropertyChangeSupport gameEngineCallbackPcs = new PropertyChangeSupport(gameEngineCallbackController);
		gameEngineCallbackPcs.addPropertyChangeListener(summaryPanel);
		gameEngineCallbackPcs.addPropertyChangeListener(wheelPanel);
		gameEngineCallbackPcs.addPropertyChangeListener(menuBar);

		gameEngineCallbackPcs.addPropertyChangeListener(statusBar);
		gameEngineCallbackController.setPCS(gameEngineCallbackPcs);



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