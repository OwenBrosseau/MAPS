package DisplayDrawingFunctions;
import javax.swing.*;
import java.awt.*;

public class ForumDisplayDrawingFunction extends JPanel {

    public void paint (Graphics2D g) {
        g.setColor(Color.BLACK);

        //Select Members Box
        g.fillRect(25, 55, 1, 470);
        g.fillRect(460, 55, 1, 470);
        g.fillRect(25, 55, 435, 1);
        g.fillRect(25, 525, 436, 1);


    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        paint(graphics);
    }

}
