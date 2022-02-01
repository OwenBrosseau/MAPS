package DisplayClasses;

import DisplayDrawingFunctions.ProgressDrawingFunction;
import StructuralClasses.Notification;
import StructuralClasses.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationInfo  {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    NotificationInfo(Notification thisNotification, Project workingProject){
        JFrame currentWindow = new JFrame();
        ProgressDrawingFunction panel = new ProgressDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JTextField projectSummary = new JTextField("Notification:");
        projectSummary.setBounds(27, 27, 527, 25);
        projectSummary.setFont(projectSummary.getFont().deriveFont(Font.BOLD, 12f));
        projectSummary.setHorizontalAlignment(JTextField.CENTER);
        projectSummary.setBackground(TITLE_COLOR);
        projectSummary.setEditable(false);
        panel.add(projectSummary);

        JTextArea notificationText = new JTextArea();
        notificationText.setBackground(BACKGROUND_COLOR);
        notificationText.setLineWrap(true);
        notificationText.setWrapStyleWord(true);
        notificationText.setEditable(false);
        notificationText.setText(thisNotification.getMessage());

        JScrollPane scroll = new JScrollPane(notificationText);
        scroll.setBounds(26, 76, 529, 239);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);

        JButton goToEvent = new JButton("Go To Event");
        goToEvent.setBounds(190, 325, 100, 25);
        switch (thisNotification.getType()) {
            case 1:
                goToEvent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TaskInfo newTaskDisplay = new TaskInfo(thisNotification.getTask());
                    }
                });
                break;
            case 2:
                goToEvent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ForumDisplay newForumDisplay = new ForumDisplay(thisNotification.getThread(), workingProject.getLoggedIn());
                    }
                });
                break;
            case 3:
                goToEvent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MeetingInfo newMeetingDisplay = new MeetingInfo(thisNotification.getMeeting());
                    }
                });
                break;
            default:
                goToEvent.setEnabled(false);
                break;
        }
        panel.add(goToEvent);

        JButton deleteNotification = new JButton("Clear Notification");
        deleteNotification.setBounds(310, 325, 150, 25);
        deleteNotification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workingProject.getLoggedIn().removeNotification(thisNotification);
                currentWindow.dispose();
            }
        });
        panel.add(deleteNotification);

        currentWindow.setTitle("Notification Description");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,400);
        currentWindow.setVisible(true);

    };

}

