package view;

import controller.GameEngineCallbackControllerImpl;
import controller.MenuBarControllerImpl;
import controller.WheelPanelControllerImpl;
import controller.interfaces.GameEngineCallbackController;
import controller.interfaces.MenuBarController;
import controller.interfaces.WheelPanelController;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.interfaces.GameEngineCallback;

import java.awt.*;

import javax.swing.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AppFrame extends JFrame {

	public AppFrame() {
		super("WheelGameUI");

		final GameEngine gameEngine = new GameEngineImpl();

		//Create View components
		StatusBar statusBar = new StatusBar();
		MenuBarController menuBarController = new MenuBarControllerImpl(gameEngine);
		MenuBar menuBar = createMenu(menuBarController);
		WheelPanelController wheelPanelController = new WheelPanelControllerImpl(gameEngine);
		WheelPanel wheelPanel = createWheelPanel(wheelPanelController);
		SummaryPanel summaryPanel = new SummaryPanel(gameEngine);

		//Add callbacks for console/UI. Create Wheel.
		GameEngineCallbackController gameEngineCallbackController = new GameEngineCallbackControllerImpl(gameEngine);
		GameEngineCallback gameEngineCallbackUI = new GameEngineCallbackGUI(gameEngineCallbackController);
		GameEngineCallback gameEngineCallback = new GameEngineCallbackImpl();
		gameEngine.addGameEngineCallback(gameEngineCallback);
		gameEngine.addGameEngineCallback(gameEngineCallbackUI);
		gameEngine.getWheelSlots();

		//Create controller and assign property change listeners
		PropertyChangeSupport menuBarPcs = generatePcs(menuBarController, summaryPanel, statusBar);
		menuBarController.setPCS(menuBarPcs);
		PropertyChangeSupport wheelPanelPcs = generatePcs(wheelPanelController, wheelPanel);
		wheelPanelController.setPCS(wheelPanelPcs);
		PropertyChangeSupport gameEngineCallbackPcs = generatePcs(gameEngineCallbackController, summaryPanel, wheelPanel, menuBar, statusBar);
		gameEngineCallbackController.setPCS(gameEngineCallbackPcs);

		//Add components to frame
		add(summaryPanel, BorderLayout.WEST);
		add(wheelPanel, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);

		setBounds(100, 100, 1280, 720);
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