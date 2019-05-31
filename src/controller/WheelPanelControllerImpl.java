package controller;

import controller.interfaces.WheelPanelController;
import model.interfaces.GameEngine;
import view.GameEngineCallbackImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;

public class WheelPanelControllerImpl implements WheelPanelController {

    private PropertyChangeSupport pcs;
    private GameEngine gameEngine;

    public WheelPanelControllerImpl(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        new Thread()
        {
            @Override
            public void run()
            {
                gameEngine.spin(1, 200, 5);

            }
        }.start();
    }

    @Override
    public void setPCS(PropertyChangeSupport pcs) {
        this.pcs = pcs;
    }
}
