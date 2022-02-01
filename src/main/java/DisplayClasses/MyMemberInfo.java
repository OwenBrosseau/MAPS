package DisplayClasses;
import DisplayDrawingFunctions.MeetingInfoDrawingFunction;
import StructuralClasses.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class MyMemberInfo  {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    MyMemberInfo(Project workingProject){

        JFrame currentWindow = new JFrame();
        MeetingInfoDrawingFunction panel = new MeetingInfoDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JTextField projectSummary = new JTextField("Your Tasks and Meetings:");           //do the + taskname
        projectSummary.setBounds(27, 27, 527, 25);
        projectSummary.setFont(projectSummary.getFont().deriveFont(Font.BOLD, 12f));
        projectSummary.setHorizontalAlignment(JTextField.CENTER);
        projectSummary.setBackground(TITLE_COLOR);
        projectSummary.setEditable(false);
        panel.add(projectSummary);

        JTextField finishedTasks = new JTextField("Completed Tasks:");
        finishedTasks.setBounds(296, 201, 259, 25);
        finishedTasks.setFont(finishedTasks.getFont().deriveFont(Font.BOLD, 12f));
        finishedTasks.setHorizontalAlignment(JTextField.CENTER);
        finishedTasks.setBackground(TITLE_COLOR);
        finishedTasks.setEditable(false);
        panel.add(finishedTasks);

        JTextField descriptionText = new JTextField("Meetings:");
        descriptionText.setBounds(26, 76, 259, 25);
        descriptionText.setFont(descriptionText.getFont().deriveFont(Font.BOLD, 12f));
        descriptionText.setHorizontalAlignment(JTextField.CENTER);
        descriptionText.setBackground(TITLE_COLOR);
        descriptionText.setEditable(false);
        panel.add(descriptionText);

        JTextField memberBox = new JTextField("Uncompleted Tasks:");
        memberBox.setBounds(296, 76, 259, 25);
        memberBox.setFont(memberBox.getFont().deriveFont(Font.BOLD, 12f));
        memberBox.setHorizontalAlignment(JTextField.CENTER);
        memberBox.setBackground(TITLE_COLOR);
        memberBox.setEditable(false);
        panel.add(memberBox);

        List<JRadioButton> listCompleted = new ArrayList<>();
        List<JRadioButton> listCurrent = new ArrayList<>();
        List<JRadioButton> listMeetings = new ArrayList<>();

        JPanel finishedTaskList = new JPanel();
        finishedTaskList.setLayout(new BoxLayout(finishedTaskList, BoxLayout.Y_AXIS));
        finishedTaskList.setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < workingProject.getLoggedIn().getCompletedTasks().size(); i++) {
            listCompleted.add(new JRadioButton(workingProject.getLoggedIn().getCompletedTasks().get(i).getTitle()));
            listCompleted.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listCompleted.get(i).setBackground(BACKGROUND_COLOR);
            listCompleted.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JRadioButton thisButton = (JRadioButton) e.getSource();
                    for (int j = 0; j < listCompleted.size(); j++) {
                        if (listCompleted.get(j) != thisButton) {
                            listCompleted.get(j).setSelected(false);
                        }
                    }
                    for (int j = 0; j < listCurrent.size(); j++) {
                        if (listCurrent.get(j) != thisButton) {
                            listCurrent.get(j).setSelected(false);
                        }
                    }
                    for (int j = 0; j < listMeetings.size(); j++) {
                        if (listMeetings.get(j) != thisButton) {
                            listMeetings.get(j).setSelected(false);
                        }
                    }
                }
            });
            finishedTaskList.add(listCompleted.get(i));
        }

        JScrollPane completedScroll = new JScrollPane(finishedTaskList);
        completedScroll.setBounds(296,226,259,89);
        completedScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(completedScroll);

        JPanel unfinishedTaskList = new JPanel();
        unfinishedTaskList.setLayout(new BoxLayout(unfinishedTaskList, BoxLayout.Y_AXIS));
        unfinishedTaskList.setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < workingProject.getLoggedIn().getCurrentTasks().size(); i++) {
            listCurrent.add(new JRadioButton(workingProject.getLoggedIn().getCurrentTasks().get(i).getTitle()));
            listCurrent.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listCurrent.get(i).setBackground(BACKGROUND_COLOR);
            listCurrent.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JRadioButton thisButton = (JRadioButton) e.getSource();
                    for (int j = 0; j < listCompleted.size(); j++) {
                        if (listCompleted.get(j) != thisButton) {
                            listCompleted.get(j).setSelected(false);
                        }
                    }
                    for (int j = 0; j < listCurrent.size(); j++) {
                        if (listCurrent.get(j) != thisButton) {
                            listCurrent.get(j).setSelected(false);
                        }
                    }
                    for (int j = 0; j < listMeetings.size(); j++) {
                        if (listMeetings.get(j) != thisButton) {
                            listMeetings.get(j).setSelected(false);
                        }
                    }
                }
            });
            unfinishedTaskList.add(listCurrent.get(i));
        }

        JScrollPane uncompletedScroll = new JScrollPane(unfinishedTaskList);
        uncompletedScroll.setBounds(296,101,259,89);
        uncompletedScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(uncompletedScroll);

        JPanel meetingsPanel = new JPanel();
        meetingsPanel.setLayout(new BoxLayout(meetingsPanel, BoxLayout.Y_AXIS));
        meetingsPanel.setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < workingProject.getLoggedIn().getMeetings().size(); i++) {
            listMeetings.add(new JRadioButton(workingProject.getLoggedIn().getMeetings().get(i).getTitle()));
            listMeetings.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listMeetings.get(i).setBackground(BACKGROUND_COLOR);
            listMeetings.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JRadioButton thisButton = (JRadioButton) e.getSource();
                    for (int j = 0; j < listCompleted.size(); j++) {
                        if (listCompleted.get(j) != thisButton) {
                            listCompleted.get(j).setSelected(false);
                        }
                    }
                    for (int j = 0; j < listCurrent.size(); j++) {
                        if (listCurrent.get(j) != thisButton) {
                            listCurrent.get(j).setSelected(false);
                        }
                    }
                    for (int j = 0; j < listMeetings.size(); j++) {
                        if (listMeetings.get(j) != thisButton) {
                            listMeetings.get(j).setSelected(false);
                        }
                    }
                }
            });
            meetingsPanel.add(listMeetings.get(i));
        }

        JScrollPane meetingScroll = new JScrollPane(meetingsPanel);
        meetingScroll.setBounds(26,101,259,214);
        meetingScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(meetingScroll);

        JButton viewButton = new JButton("View Details");
        viewButton.setBounds(220, 320, 160,25);
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listCurrent.size(); i++) {
                    if (listCurrent.get(i).isSelected()) {
                        TaskInfo newTaskDisplay = new TaskInfo(workingProject.getLoggedIn().getCurrentTasks().get(i));
                    }
                }
                for (int i = 0; i < listCompleted.size(); i++) {
                    if (listCompleted.get(i).isSelected()) {
                        TaskInfo newTaskDisplay = new TaskInfo(workingProject.getLoggedIn().getCompletedTasks().get(i));
                    }
                }
                for (int i = 0; i < listMeetings.size(); i++) {
                    if (listMeetings.get(i).isSelected()) {
                        MeetingInfo newMeetingDisplay = new MeetingInfo(workingProject.getLoggedIn().getMeetings().get(i));
                    }
                }
            }
        });
        panel.add(viewButton);

        currentWindow.setTitle("Task Data");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,400);
        currentWindow.setVisible(true);

    };

}

