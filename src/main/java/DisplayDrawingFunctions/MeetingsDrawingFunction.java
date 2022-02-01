package DisplayDrawingFunctions;
import javax.swing.*;
import java.awt.*;

public class MeetingsDrawingFunction extends JPanel {

    public void paint (Graphics2D g) {
        g.setColor(Color.BLACK);
        //title box
        g.fillRect(25, 25, 1, 30);
        g.fillRect(555, 25, 1, 30);
        g.fillRect(25, 25, 530, 1);
        g.fillRect(25, 54, 531, 1);
        //Select Members Box
        g.fillRect(25, 75, 1, 350);
        g.fillRect(250, 75, 1, 350);
        g.fillRect(25, 75, 225, 1);
        g.fillRect(25, 425, 226, 1);
        //description box
        g.fillRect(260, 75, 1, 100);
        g.fillRect(555, 75, 1, 100);
        g.fillRect(260, 75, 295, 1);
        g.fillRect(260, 175, 296, 1);
        //related topics box
        g.fillRect(260, 185, 1, 240);
        g.fillRect(406, 185, 1, 240);
        g.fillRect(260, 185, 146, 1);
        g.fillRect(260, 425, 147, 1);
        //days box
        g.fillRect(415, 185, 1, 200);
        g.fillRect(555, 185, 1, 200);
        g.fillRect(415, 185, 140, 1);
        g.fillRect(415, 385, 141, 1);

    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        paint(graphics);
    }
}
