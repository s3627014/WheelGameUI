package view;

import controller.WheelPanelController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WheelPanel  extends JPanel implements PropertyChangeListener {

        public WheelPanel(WheelPanelController controller){
            setLayout(new BorderLayout());
            setBackground(Color.DARK_GRAY);
            JButton spinBtn = new JButton("Spin!");
            spinBtn.setBackground(new Color(118, 33, 25));
            spinBtn.setForeground(Color.WHITE);
            spinBtn.setMaximumSize(new Dimension(5,25));
            add(spinBtn, BorderLayout.SOUTH);
            spinBtn.addActionListener(controller);
            WheelImagePanel wheelImage = new WheelImagePanel();
            add(wheelImage, BorderLayout.CENTER);
        }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {

    }
}
