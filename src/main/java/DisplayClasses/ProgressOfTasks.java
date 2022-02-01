package DisplayClasses;
import DisplayDrawingFunctions.ProgressDrawingFunction;

import javax.swing.*;
import java.awt.*;


public class ProgressOfTasks {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    ProgressOfTasks(){
        JFrame currentWindow = new JFrame();
        ProgressDrawingFunction panel = new ProgressDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JTextField projectSummary = new JTextField("Project Summary:");
        projectSummary.setBounds(27, 27, 527, 25);
        projectSummary.setFont(projectSummary.getFont().deriveFont(Font.BOLD, 12f));
        projectSummary.setHorizontalAlignment(JTextField.CENTER);
        projectSummary.setBackground(TITLE_COLOR);
        projectSummary.setEditable(false);
        panel.add(projectSummary);

        JTextArea summaryText = new JTextArea();
        summaryText.setBounds(26,76,529,239);
        summaryText.setBackground(BACKGROUND_COLOR);
        summaryText.setEditable(false);
        summaryText.setLineWrap(true);
        panel.add(summaryText);



        currentWindow.setTitle("Project Summary");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,400);
        currentWindow.setVisible(true);

    };

}

