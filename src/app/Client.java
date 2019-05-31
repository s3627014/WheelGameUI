package app;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.AppFrame;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;

import javax.swing.SwingUtilities;
import java.util.logging.Logger;

public class Client
{
	private static final Logger logger = Logger.getLogger(Client.class.getName());
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void createAndShowGUI() throws Exception {
		new AppFrame();

	}
}
