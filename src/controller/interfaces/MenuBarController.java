package controller.interfaces;

import java.awt.event.ActionListener;

public interface MenuBarController extends ActionListener, PropertyChangeSupportSetter{

    void addPlayerPopupDialog();

    void removePlayerDialog();

    void betDialog();

    void aboutDialog();
}
