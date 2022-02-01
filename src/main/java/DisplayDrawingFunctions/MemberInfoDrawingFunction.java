package DisplayDrawingFunctions;

import javax.swing.*;
import java.awt.*;

public class MemberInfoDrawingFunction extends JPanel {

    public void paint (Graphics2D g) {
        g.setColor(Color.BLACK);

        //title box
        g.fillRect(25, 13, 1, 30);
        g.fillRect(555, 13, 1, 30);
        g.fillRect(25, 13, 530, 1);
        g.fillRect(25, 42, 531, 1);

        //Unpicked Tasks
        g.fillRect(18, 50, 1, 285);
        g.fillRect(288, 50, 1, 285);
        g.fillRect(18, 50, 270, 1);
        g.fillRect(18, 334, 271, 1);

        //Picked Tasks
        g.fillRect(298, 50, 1, 285);
        g.fillRect(568, 50, 1, 285);
        g.fillRect(298, 50, 270, 1);
        g.fillRect(298, 334, 271, 1);


    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        paint(graphics);
    }

}
