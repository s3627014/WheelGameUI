package view;

import controller.interfaces.WheelPanelController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class WheelPanel extends JPanel implements PropertyChangeListener {
    private BufferedImage wheelImg;
    private Double slot;

    WheelPanel(WheelPanelController controller) {

        try {
            wheelImg = ImageIO.read(new File("src/view/resources/wheel.png"));

        } catch (IOException ex) {
            // handle exception...
        }
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);
        JButton spinBtn = new JButton("Spin!");
        spinBtn.setBackground(new Color(118, 33, 25));
        spinBtn.setForeground(Color.WHITE);
        spinBtn.setMaximumSize(new Dimension(5, 25));
        add(spinBtn, BorderLayout.SOUTH);
        spinBtn.addActionListener(controller);


    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals("Spinning...")) {
            int slotPosition = Integer.parseInt(pce.getNewValue().toString());
            calculateAngle(slotPosition);
        }
    }

    //Calculates the angle of the slot in order to paint the ball.
    private void calculateAngle(int slotPosition) {

        // slotPosition - 9 because degrees 0 points to the right most slot instead of the top slot.
        // 0.165 is the slot width and 0.1 is the offset angle.
        var slotAngle = 0.165;
        var wheelAngleOffset = 0.1;
        slot = (slotPosition - 9) * slotAngle + wheelAngleOffset;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        var heightOfSpinBtn = 28;
        var wheelDimension = 0;
        var w = getWidth();
        var h = getHeight() - heightOfSpinBtn; //move wheel above the button
        var horizontalScaleValue = w < h ? 0 : w / 2 - h / 2; //Keeps the wheel centered in the wheelPanel
        //Keep wheel as a square, Pick the smallest of the h/w and set that as the
        if (w > h) {
            wheelDimension = h;
        } else {
            wheelDimension = w;
        }

        g.drawImage(wheelImg, horizontalScaleValue, 0, wheelDimension, wheelDimension, null);

        if (slot != null) {

            var r = wheelDimension / 2;
            var x = r * Math.cos(slot) * 0.93 + r;
            var y = r * Math.sin(slot) * 0.93 + r;


            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.YELLOW);
            Shape planet = new Ellipse2D.Double(x + horizontalScaleValue, y, wheelDimension / 50, wheelDimension / 50);
            g2d.fill(planet);
            g2d.dispose();
        }
    }
}
