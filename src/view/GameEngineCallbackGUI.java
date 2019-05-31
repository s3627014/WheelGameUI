package view;

import controller.interfaces.GameEngineCallbackController;
import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
    private GameEngineCallbackController controller;

    public GameEngineCallbackGUI(GameEngineCallbackController controller) {
        this.controller = controller;
    }

    @Override
    public void nextSlot(Slot slot, GameEngine engine) {
            controller.drawNextSlot(slot);
    }

    @Override
    public void result(Slot winningSlot, GameEngine engine) {
        controller.showResults(winningSlot);
    }
}
