package view;

import controller.WheelPanelController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class WheelPanel  extends JPanel implements PropertyChangeListener {
    private BufferedImage wheelImg;
    private BufferedImage ballImg;
    private double slot = 0;
        public WheelPanel(WheelPanelController controller){

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
            spinBtn.setMaximumSize(new Dimension(5,25));
            add(spinBtn, BorderLayout.SOUTH);
            spinBtn.addActionListener(controller);

            new Thread()
            {
                @Override
                public void run()
                {
                    var count = 28;
                    while (true){
                        count = count +5;
                        slot = count * 0.1125 ;
                        count++;
                        if (count == 38){
                            count = 0;
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        repaint();
                        break;
                    }
                }
            }.start();


        }
        //find tne coords of a point on a circle.

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
            if (pce.getPropertyName() == "Draw next slot"){
                int slotPosition = Integer.parseInt(pce.getNewValue().toString());
                spin(slotPosition);
            }

    }
    public void spin(int slotPostition){
        slot = (slotPostition-9) * 0.165 + 0.1;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        var wheelDimension = 0;
        var w = getWidth();
        var h = getHeight() - 40; //height of swap button
        var horizontalScaleValue = getWidth() < 600 ? 0 :  getWidth()/2 -h/2;
        System.out.println(getWidth());

        //Keep wheel as a square, Pick the smallest of the h/w and set that as the
        if (w>h){
            wheelDimension = h;
        }
        else {
            wheelDimension = w;
        }


        g.drawImage(wheelImg, horizontalScaleValue, 0, wheelDimension, wheelDimension, null);

        var r = wheelDimension/2;
        var x = r * Math.cos(slot)*0.93 + r;
        var y = r * Math.sin(slot)*0.93 + r;


        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.YELLOW);
        Shape planet = new Ellipse2D.Double(x + horizontalScaleValue, y, wheelDimension/50, wheelDimension/50);
        g2d.fill(planet);
        g2d.dispose();
    }
}
