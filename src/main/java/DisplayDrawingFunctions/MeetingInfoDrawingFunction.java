package DisplayDrawingFunctions;
import javax.swing.*;
import java.awt.*;

public class MeetingInfoDrawingFunction extends JPanel {

    public void paint (Graphics2D g) {
        g.setColor(Color.BLACK);
        //title box
        g.fillRect(25, 25, 1, 30);
        g.fillRect(555, 25, 1, 30);
        g.fillRect(25, 25, 530, 1);
        g.fillRect(25, 54, 531, 1);
        //write text box
        g.fillRect(25, 75, 1, 240);
        g.fillRect(285, 75, 1, 240);
        g.fillRect(25, 75, 260, 1);
        g.fillRect(25, 315, 261, 1);
        //dependencies
        g.fillRect(295, 75, 1, 115);
        g.fillRect(555, 75, 1, 115);
        g.fillRect(295, 75, 260, 1);
        g.fillRect(295, 190, 261, 1);

        g.fillRect(295, 200, 1, 115);
        g.fillRect(555, 200, 1, 115);
        g.fillRect(295, 200, 260, 1);
        g.fillRect(295, 315, 261, 1);
    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        paint(graphics);
    }

}
