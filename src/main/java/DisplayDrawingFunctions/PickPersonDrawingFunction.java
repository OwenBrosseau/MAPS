package DisplayDrawingFunctions;
import javax.swing.*;
import java.awt.*;

public class PickPersonDrawingFunction extends JPanel {

    public void paint (Graphics2D g) {
        g.setColor(Color.BLACK);
        //title box
        g.fillRect(25, 25, 1, 30);
        g.fillRect(250, 25, 1, 30);
        g.fillRect(25, 25, 225, 1);
        g.fillRect(25, 54, 226, 1);
        //Select Members Box
        g.fillRect(25, 75, 1, 275);
        g.fillRect(250, 75, 1, 275);
        g.fillRect(25, 75, 225, 1);
        g.fillRect(25, 350, 226, 1);
    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        paint(graphics);
    }

}
