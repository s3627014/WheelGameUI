package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WheelImagePanel extends JPanel {

    private BufferedImage image;

    public WheelImagePanel() {
        try {
            image = ImageIO.read(new File("src/view/resources/wheel.png"));
            setPreferredSize(new Dimension(5,5));
            setBackground(Color.darkGray);

        } catch (IOException ex) {
            // handle exception...
        }



    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(image, getWidth(), getHeight(), null);
    }

}