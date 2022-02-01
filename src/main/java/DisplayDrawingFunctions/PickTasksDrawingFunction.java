package DisplayDrawingFunctions;
import javax.swing.*;
import java.awt.*;

public class PickTasksDrawingFunction extends JPanel {

    public void paint (Graphics2D g) {
        g.setColor(Color.BLACK);

        //Unpicked Tasks
        g.fillRect(18, 30, 1, 128);
        g.fillRect(288, 30, 1, 128);
        g.fillRect(18, 30, 270, 1);
        g.fillRect(18, 158, 271, 1);

        g.fillRect(18, 188, 1, 128);
        g.fillRect(288, 188, 1, 128);
        g.fillRect(18, 188, 270, 1);
        g.fillRect(18, 315, 271, 1);

        //Picked Tasks
        g.fillRect(298, 30, 1, 285);
        g.fillRect(568, 30, 1, 285);
        g.fillRect(298, 30, 270, 1);
        g.fillRect(298, 315, 271, 1);


    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        paint(graphics);
    }

}
