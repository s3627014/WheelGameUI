package view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StatusBar extends JPanel implements PropertyChangeListener {

    private JLabel statusLabel = new JLabel("Game launched");

    public StatusBar(){
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(statusLabel);

    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        statusLabel.setText("Player added");
    }
}
