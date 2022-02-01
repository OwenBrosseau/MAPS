package DisplayDrawingFunctions;
import javax.swing.*;
import java.awt.*;

public class ProjectManagerDrawingFunction extends JPanel {

    public void paint (Graphics2D g) {
        g.setColor(Color.BLACK);

        //title box
        g.fillRect(25, 25, 1, 30);
        g.fillRect(555, 25, 1, 30);
        g.fillRect(25, 25, 530, 1);
        g.fillRect(25, 54, 531, 1);

        //title box
        g.fillRect(25, 58, 1, 30);
        g.fillRect(555, 58, 1, 30);
        g.fillRect(25, 58, 530, 1);
        g.fillRect(25, 88, 531, 1);

        //Topic Lead
        g.fillRect(18, 100, 1, 240);
        g.fillRect(288, 100, 1, 240);
        g.fillRect(18, 100, 270, 1);
        g.fillRect(18, 340, 271, 1);

        //Delete Project
        g.fillRect(298, 150, 1, 150);
        g.fillRect(568, 150, 1, 150);
        g.fillRect(298, 150, 270, 1);
        g.fillRect(298, 300, 271, 1);

    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        paint(graphics);
    }

}
