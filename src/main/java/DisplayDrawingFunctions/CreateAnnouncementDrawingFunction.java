package DisplayDrawingFunctions;

import javax.swing.*;
import java.awt.*;

public class CreateAnnouncementDrawingFunction extends JPanel {

    public void paint (Graphics2D g) {
        g.setColor(Color.BLACK);

        //write text box
        g.fillRect(25, 25, 1, 290);
        g.fillRect(555, 25, 1, 290);
        g.fillRect(25, 25, 530, 1);
        g.fillRect(25, 315, 531, 1);


    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        paint(graphics);
    }

}