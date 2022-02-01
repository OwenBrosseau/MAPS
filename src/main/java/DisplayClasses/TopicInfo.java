package DisplayClasses;
import DisplayDrawingFunctions.MeetingInfoDrawingFunction;
import StructuralClasses.Topic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class TopicInfo {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    TopicInfo(Topic thisTopic){
        JFrame currentWindow = new JFrame();
        MeetingInfoDrawingFunction panel = new MeetingInfoDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JTextField projectSummary = new JTextField("Topic: " + thisTopic.getTitle() + " (Topic Lead: " + thisTopic.getLead().getName() + ")");
        projectSummary.setBounds(27, 27, 527, 25);
        projectSummary.setFont(projectSummary.getFont().deriveFont(Font.BOLD, 12f));
        projectSummary.setHorizontalAlignment(JTextField.CENTER);
        projectSummary.setBackground(TITLE_COLOR);
        projectSummary.setEditable(false);
        panel.add(projectSummary);

        JTextField finishedTasks = new JTextField("Forums:");
        finishedTasks.setBounds(296, 201, 259, 25);
        finishedTasks.setFont(finishedTasks.getFont().deriveFont(Font.BOLD, 12f));
        finishedTasks.setHorizontalAlignment(JTextField.CENTER);
        finishedTasks.setBackground(TITLE_COLOR);
        finishedTasks.setEditable(false);
        panel.add(finishedTasks);

        JTextField descriptionText = new JTextField("Tasks:");
        descriptionText.setBounds(26, 76, 259, 25);
        descriptionText.setFont(descriptionText.getFont().deriveFont(Font.BOLD, 12f));
        descriptionText.setHorizontalAlignment(JTextField.CENTER);
        descriptionText.setBackground(TITLE_COLOR);
        descriptionText.setEditable(false);
        panel.add(descriptionText);

        JTextField memberBox = new JTextField("Meetings:");
        memberBox.setBounds(296, 76, 259, 25);
        memberBox.setFont(memberBox.getFont().deriveFont(Font.BOLD, 12f));
        memberBox.setHorizontalAlignment(JTextField.CENTER);
        memberBox.setBackground(TITLE_COLOR);
        memberBox.setEditable(false);
        panel.add(memberBox);

        JPanel meetingBox = new JPanel();
        meetingBox.setLayout(new BoxLayout(meetingBox, BoxLayout.Y_AXIS));
        meetingBox.setBackground(BACKGROUND_COLOR);

        List<JButton> listMeetings = new ArrayList<>();
        for (int i = 0; i < thisTopic.getMeetings().size(); i++) {
            listMeetings.add(new JButton(thisTopic.getMeetings().get(i).getTitle()));
            listMeetings.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listMeetings.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton thisButton = (JButton) e.getSource();
                    for (int i = 0; i < thisTopic.getMeetings().size(); i++) {
                        if (thisTopic.getMeetings().get(i).getTitle().equals(thisButton.getText())) {
                            MeetingInfo newMeetingDisplay = new MeetingInfo(thisTopic.getMeetings().get(i));
                        }
                    }
                }
            });
            meetingBox.add(listMeetings.get(i));
        }

        JScrollPane meetingsScrollPanel = new JScrollPane(meetingBox);
        meetingsScrollPanel.setBounds(296,101,259,89);
        meetingsScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(meetingsScrollPanel);

        JPanel forumsBox = new JPanel();
        forumsBox.setLayout(new BoxLayout(forumsBox, BoxLayout.Y_AXIS));
        forumsBox.setBounds(0,0,259 , 89);
        forumsBox.setBackground(BACKGROUND_COLOR);

        List<JButton> listForums = new ArrayList<>();
        for (int i = 0; i < thisTopic.getForumThreads().size(); i++) {
            listForums.add(new JButton(thisTopic.getForumThreads().get(i).getForumTitle()));
            listForums.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listForums.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton thisButton = (JButton) e.getSource();
                    for (int i = 0; i < thisTopic.getForumThreads().size(); i++) {
                        if (thisTopic.getForumThreads().get(i).getForumTitle().equals(thisButton.getText())) {
                            ForumDisplay newTaskDisplay = new ForumDisplay(thisTopic.getForumThreads().get(i));
                        }
                    }
                }
            });
            forumsBox.add(listForums.get(i));
        }

        JScrollPane forumsScrollPanel = new JScrollPane(forumsBox);
        forumsScrollPanel.setBounds(296,226,259,89);
        forumsScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(forumsScrollPanel);

        JPanel taskBox = new JPanel();
        taskBox.setBounds(0,0,259 , 214);
        taskBox.setLayout(new BoxLayout(taskBox, BoxLayout.Y_AXIS));
        taskBox.setBackground(BACKGROUND_COLOR);

        List<JButton> listTasks = new ArrayList<>();
        for (int i = 0; i < thisTopic.getTasks().size(); i++) {
            listTasks.add(new JButton(thisTopic.getTasks().get(i).getTitle()));
            listTasks.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listTasks.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton thisButton = (JButton) e.getSource();
                    for (int i = 0; i < thisTopic.getTasks().size(); i++) {
                        if (thisTopic.getTasks().get(i).getTitle().equals(thisButton.getText())) {
                            TaskInfo newTaskDisplay = new TaskInfo(thisTopic.getTasks().get(i));
                        }
                    }
                }
            });
            taskBox.add(listTasks.get(i));
        }

        JScrollPane taskScrollPanel = new JScrollPane(taskBox);
        taskScrollPanel.setBounds(26,101,259,214);
        taskScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(taskScrollPanel);

        currentWindow.setTitle("Topic Data");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,400);
        currentWindow.setVisible(true);

    };

}

