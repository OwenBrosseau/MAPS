package DisplayClasses;
import DisplayDrawingFunctions.FrontPageDrawingFunction;
import StructuralClasses.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FrontPage {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    public FrontPage(){
        JFrame currentWindow = new JFrame();
        FrontPageDrawingFunction panel = new FrontPageDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<String> projectTitles = new ArrayList<String>();
        try {
             String filePath = System.getProperty("user.dir") + "/Projects";
             File dir = new File(filePath);
             File file = new File(dir, "Projects.txt");
             Scanner myReader = new Scanner(file);
             while (myReader.hasNextLine()) {
                 String data = myReader.nextLine();
                 projectTitles.add(data);
             }
             myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(BACKGROUND_COLOR);

        List<JRadioButton> listButtons = new ArrayList<>();
        for (int i = 0; i < projectTitles.size(); i++) {
            listButtons.add(new JRadioButton(projectTitles.get(i)));
            listButtons.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listButtons.get(i).setBackground(BACKGROUND_COLOR);
            listButtons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JRadioButton thisButton = (JRadioButton) e.getSource();
                    for (int j = 0; j < listButtons.size(); j++) {
                        if (listButtons.get(j) != thisButton) {
                            listButtons.get(j).setSelected(false);
                        }
                    }
                }
            });
            buttonsPanel.add(listButtons.get(i));
        }

        JScrollPane buttonsScrollPane = new JScrollPane(buttonsPanel);
        buttonsScrollPane.setBounds(26, 301, 223, 228);
        buttonsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(buttonsScrollPane);

        ImageIcon logo = new ImageIcon("Logo.png");
        JLabel label = new JLabel(logo);
        label.setBounds(0,0,500,300);
        panel.add(label);

        JButton OpenButton = new JButton("Open Project");
        OpenButton.setBounds(275, 350, 150, 25);
        if (projectTitles.size() == 0) {
            OpenButton.setEnabled(false);
        }
        OpenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listButtons.size(); i++) {
                    if (listButtons.get(i).isSelected()) {
                        Project workingProject = null;
                        try {
                            workingProject = new Project(projectTitles.get(i));
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                        PickPerson logInWindow = new PickPerson(workingProject);
                        currentWindow.dispose();
                    }
                }
            }
        });
        panel.add(OpenButton);

        JButton NewButton = new JButton("Create New Project");
        NewButton.setBounds(275, 400, 150, 25);
        NewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateProject newProjectWindow = new CreateProject();
                currentWindow.dispose();
            }
        });
        panel.add(NewButton);

        currentWindow.setTitle("Welcome");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(500,580);
        currentWindow.setVisible(true);

    };

}

