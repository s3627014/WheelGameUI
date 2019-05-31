package view;

import controller.GameEngineCallbackController;
import controller.MenuBarController;
import controller.WheelPanelController;
import model.GameEngineImpl;
import model.interfaces.GameEngine;

import java.awt.*;

import javax.swing.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AppFrame extends JFrame {

	public AppFrame() {
		//Set layout + title of Frame
		super("WheelGameUI");
		setBounds(100, 100, 1280, 720);

		final GameEngine gameEngine = new GameEngineImpl();
		var statusBar = new StatusBar();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		gameEngine.getWheelSlots();
		MenuBarController menuBarController = new MenuBarController(gameEngine);
		var menuBar = createMenu(menuBarController);
		WheelPanelController wheelPanelController = new WheelPanelController(gameEngine);
		GameEngineCallbackController gameEngineCallbackController = new GameEngineCallbackController(gameEngine);
		SummaryPanel summaryPanel = new SummaryPanel(gameEngine);
		WheelPanel wheelPanel = createWheelPanel(wheelPanelController);
		gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(gameEngineCallbackController));
		//Create controller and assign property change listeners
		PropertyChangeSupport menuBarPcs = generatePcs(menuBarController, summaryPanel, statusBar);
		menuBarController.setPCS(menuBarPcs);

		PropertyChangeSupport wheelPanelPcs = generatePcs(wheelPanelController, wheelPanel);
		wheelPanelController.setPCS(wheelPanelPcs);

		PropertyChangeSupport gameEngineCallbackPcs = generatePcs(gameEngineCallbackController, summaryPanel, wheelPanel, menuBar, statusBar);
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

	private MenuBar createMenu(MenuBarController controller){
		JMenu menu = new JMenu("size");
		JMenuItem size = new JMenuItem("size");
		menu.add(size);
		var menuBar = new MenuBar(controller);
		JMenuBar menubar = new JMenuBar();
		menubar.add(menuBar);
		setJMenuBar(menuBar);
		return menuBar;
	}
	private WheelPanel createWheelPanel(WheelPanelController wheelPanelController){

		WheelPanel wheelPanel = new WheelPanel(wheelPanelController);

		return wheelPanel;
	}
	private PropertyChangeSupport generatePcs(Object controller, Object... listeners){
		PropertyChangeSupport pcs = new PropertyChangeSupport(controller);

		for (Object listener: listeners
		) {
			pcs.addPropertyChangeListener((PropertyChangeListener) listener);
		}
		return pcs;

	}

}