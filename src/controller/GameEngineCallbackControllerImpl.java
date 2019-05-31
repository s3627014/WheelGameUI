package controller;

import controller.interfaces.GameEngineCallbackController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class GameEngineCallbackControllerImpl implements GameEngineCallbackController {
    private PropertyChangeSupport pcs;
    private GameEngine gameEngine;

    public GameEngineCallbackControllerImpl(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void setPCS(PropertyChangeSupport pcs) {
        this.pcs = pcs;
    }

    @Override
    public void showResults(Slot winningSlot){
        var winners = new ArrayList<String>();
        var losers = new ArrayList<String>();

        for (Player player : gameEngine.getAllPlayers()
             ) {
            if (player.getBetType() == null || player.getBet() == 0){
                continue;
            }
            if (player.getBetType().toString() == winningSlot.getColor().name().toUpperCase()){
                winners.add(player.getPlayerId());
            }
            else {
                losers.add(player.getPlayerId());
            }
            player.resetBet();
        }
        if (losers.size() == 0 && winners.size() == 0){
            pcs.firePropertyChange("Spin complete, not bets placed", false, true);
        }
        else{
            pcs.firePropertyChange("Spin complete", losers, winners);
        }
    }

    @Override
    public void drawNextSlot(Slot slot){
        pcs.firePropertyChange("Spinning...", false, slot.getPosition());
    }
}
