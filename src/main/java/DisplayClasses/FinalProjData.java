package DisplayClasses;
import DisplayDrawingFunctions.FinalProjDrawingFunction;
import StructuralClasses.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class FinalProjData  {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    public FinalProjData(Project completedProject){
        JFrame currentWindow = new JFrame();
        FinalProjDrawingFunction panel = new FinalProjDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextField projectSummary = new JTextField("Complete Project Data:");
        projectSummary.setBounds(27, 15, 527, 25);
        projectSummary.setFont(projectSummary.getFont().deriveFont(Font.BOLD, 12f));
        projectSummary.setHorizontalAlignment(JTextField.CENTER);
        projectSummary.setBackground(TITLE_COLOR);
        projectSummary.setEditable(false);
        panel.add(projectSummary);

        JTextField members = new JTextField("Members:");
        members.setBounds(19, 51, 269, 25);
        members.setFont(members.getFont().deriveFont(Font.BOLD, 12f));
        members.setHorizontalAlignment(JTextField.CENTER);
        members.setBackground(TITLE_COLOR);
        members.setEditable(false);
        panel.add(members);

        JTextField tasks = new JTextField("Tasks:");
        tasks.setBounds(299, 51, 269, 25);
        tasks.setFont(tasks.getFont().deriveFont(Font.BOLD, 12f));
        tasks.setHorizontalAlignment(JTextField.CENTER);
        tasks.setBackground(TITLE_COLOR);
        tasks.setEditable(false);
        panel.add(tasks);

        JPanel membersBox = new JPanel();
        membersBox.setBounds(0,0,269, 258);
        membersBox.setLayout(new BoxLayout(membersBox, BoxLayout.Y_AXIS));
        membersBox.setBackground(BACKGROUND_COLOR);

        List<JButton> listMembers = new ArrayList<>();
        for (int i = 0; i < completedProject.getMembers().size(); i++) {
            listMembers.add(new JButton(completedProject.getMembers().get(i).getName()));
            listMembers.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listMembers.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton thisButton = (JButton) e.getSource();
                    for (int i = 0; i < completedProject.getMembers().size(); i++) {
                        if (completedProject.getMembers().get(i).getName().equals(thisButton.getText())) {
                            FinalMemberInfo newFinalMemberDisplay = new FinalMemberInfo(completedProject.getMembers().get(i));
                        }
                    }
                }
            });
            membersBox.add(listMembers.get(i));
        }

        JScrollPane membersScrollPanel = new JScrollPane(membersBox);
        membersScrollPanel.setBounds(19,76,269,258);
        membersScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(membersScrollPanel);

        JPanel completedBox = new JPanel();
        completedBox.setBounds(0,0,269, 258);
        completedBox.setLayout(new BoxLayout(completedBox, BoxLayout.Y_AXIS));
        completedBox.setBackground(BACKGROUND_COLOR);

        List<JButton> listCompleted = new ArrayList<>();
        for (int i = 0; i < completedProject.getCompletedTasks().size(); i++) {
            listCompleted.add(new JButton(completedProject.getCompletedTasks().get(i).getTitle()));
            listCompleted.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listCompleted.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton thisButton = (JButton) e.getSource();
                    for (int i = 0; i < completedProject.getCompletedTasks().size(); i++) {
                        if (completedProject.getCompletedTasks().get(i).getTitle().equals(thisButton.getText())) {
                            TaskInfo newTaskDisplay = new TaskInfo(completedProject.getCompletedTasks().get(i));
                        }
                    }
                }
            });
            completedBox.add(listCompleted.get(i));
        }

        JScrollPane completedScrollPanel = new JScrollPane(completedBox);
        completedScrollPanel.setBounds(299,76,269,258);
        completedScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(completedScrollPanel);

        currentWindow.setTitle("Final Project Data");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,400);
        currentWindow.setVisible(true);

    };

}

