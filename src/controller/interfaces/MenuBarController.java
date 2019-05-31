package controller.interfaces;

import java.awt.event.ActionListener;

public interface MenuBarController extends ActionListener, PropertyChangeSupportSetter {

    /**
     * Each of these methods create the relevant dialog box from the menu items
     */

    void addPlayerPopupDialog();

    void removePlayerDialog();

    void betDialog();

    void aboutDialog();
}
