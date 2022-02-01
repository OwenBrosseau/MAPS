package DisplayDrawingFunctions;

import javax.swing.*;
import java.awt.*;

public class DashboardDrawingFunction extends JPanel {

    public void paint (Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(200, 0, 3, 540);
        g.fillRect(760, 0, 3, 540);
        g.fillRect(200, 40, 560, 3);
        g.fillRect(200, 170, 560, 3);
        g.fillRect(200, 270, 560, 3);
        g.fillRect(386, 40, 3, 130);
        g.fillRect(574, 40, 3, 130);
        g.fillRect(480,273,3,275);
        g.fillRect(482,452,278,3);
        g.fillRect(0, 25, 200, 3);
    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        paint(graphics);
    }

}
