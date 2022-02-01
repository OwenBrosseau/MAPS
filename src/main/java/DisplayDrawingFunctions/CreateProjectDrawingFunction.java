package DisplayDrawingFunctions;
import javax.swing.*;
import java.awt.*;

public class CreateProjectDrawingFunction extends JPanel {

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

        //description box
        g.fillRect(25, 100, 1, 219);
        g.fillRect(399, 100, 1, 219);
        g.fillRect(25, 100, 375, 1);
        g.fillRect(25, 318, 375, 1);

        //date picker box
        g.fillRect(414, 100, 1, 30);
        g.fillRect(555, 100, 1, 30);
        g.fillRect(414, 100, 141, 1);

    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        paint(graphics);
    }

}
