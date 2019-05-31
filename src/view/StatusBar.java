package view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StatusBar extends JPanel implements PropertyChangeListener {

    private JLabel statusLabel = new JLabel("Game launched...");

    StatusBar() {
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(statusLabel);

    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        statusLabel.setText(pce.getPropertyName());
    }
}
