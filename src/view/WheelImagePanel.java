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
            image = ImageIO.read(new File("C:\\Users\\luke\\Documents\\ees\\WheelGameUI\\src\\view\\resources\\wheel.png")); //need to fix relative path
            setBackground(Color.darkGray);
            setLayout(new BorderLayout());

        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }

}