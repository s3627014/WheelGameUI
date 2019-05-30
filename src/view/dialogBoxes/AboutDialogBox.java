package view.dialogBoxes;

import javax.swing.*;

public class AboutDialogBox {
    public AboutDialogBox() {
        String message = String.format("%s\n%s\n%s\n%s\n%s\n%s",
                "Wheel Game UI",
                "Author: Luke Waldren s3627014",
                "Tutor: Halil",
                "To add, remove and place bets use the players item in the menu bar.",
                "To spin the board press the spin button under the wheel.",
                "Thanks!");

        JOptionPane.showMessageDialog(null, message);

    }
}
