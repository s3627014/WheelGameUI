package controller.interfaces;

import model.interfaces.Slot;

public interface GameEngineCallbackController extends PropertyChangeSupportSetter {

    void showResults(Slot winningSlot);

    void drawNextSlot(Slot slot);
}
