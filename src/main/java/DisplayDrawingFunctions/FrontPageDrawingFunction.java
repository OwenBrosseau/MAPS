package DisplayDrawingFunctions;

import javax.swing.*;
import java.awt.*;

public class FrontPageDrawingFunction extends JPanel {


    public void paint (Graphics2D g) {
        g.setColor(Color.BLACK);
        //com.company.Project Selection Box
        g.fillRect(25, 300, 1, 230);
        g.fillRect(250, 300, 1, 230);
        g.fillRect(25, 300, 225, 1);
        g.fillRect(25, 530, 226, 1);
    }

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        paint(graphics);
    }


}
