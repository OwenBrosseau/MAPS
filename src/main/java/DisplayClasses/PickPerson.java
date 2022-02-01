package DisplayClasses;
import DisplayDrawingFunctions.PickPersonDrawingFunction;
import StructuralClasses.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class PickPerson {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    PickPerson(Project workingProject){
        JFrame currentWindow = new JFrame();
        PickPersonDrawingFunction panel = new PickPersonDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        if (workingProject.getLoggedIn() != null) {
            currentWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    Dashboard newDashboard = new Dashboard(workingProject);
                    currentWindow.dispose();
                }
            });
            currentWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
        else {
            currentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }


        JTextField enterTitle = new JTextField("Pick a Member:");
        enterTitle.setBounds(27, 27, 222, 25);
        enterTitle.setFont(enterTitle.getFont().deriveFont(Font.BOLD, 12f));
        enterTitle.setHorizontalAlignment(JTextField.CENTER);
        enterTitle.setBackground(TITLE_COLOR);
        enterTitle.setEditable(false);
        panel.add(enterTitle);

        JPanel membersBox = new JPanel();
        membersBox.setLayout(new BoxLayout(membersBox, BoxLayout.Y_AXIS));
        membersBox.setBounds(0,0,224 , 274);
        membersBox.setBackground(BACKGROUND_COLOR);

        List<JRadioButton> listButtons = new ArrayList<>();
        for (int i = 0; i < workingProject.getMembers().size(); i++) {
            listButtons.add(new JRadioButton(workingProject.getMembers().get(i).getName()));
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
            membersBox.add(listButtons.get(i));
        }

        JScrollPane membersScrollPane = new JScrollPane(membersBox);
        membersScrollPane.setBounds(26,76,224 , 274);
        membersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(membersScrollPane);

        JButton signInButton = new JButton("Sign In");
        signInButton.setBounds(100, 372, 90, 25);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < listButtons.size(); i++) {
                        if (listButtons.get(i).isSelected()) {
                            workingProject.setLoggedIn(workingProject.getMember(listButtons.get(i).getText()));
                            Dashboard newDashboard = new Dashboard(workingProject);
                            currentWindow.dispose();
                        }
                    }
            }
        });
        panel.add(signInButton);

        currentWindow.setTitle("Who are you?");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(295,450);
        currentWindow.setVisible(true);

    };

}

