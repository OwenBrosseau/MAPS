package DisplayClasses;
import DisplayDrawingFunctions.MeetingInfoDrawingFunction;
import StructuralClasses.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class TaskInfo {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    TaskInfo(Task thisTask){
        JFrame currentWindow = new JFrame();
        MeetingInfoDrawingFunction panel = new MeetingInfoDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String titleText = "Task: " + thisTask.getTitle();
        if (thisTask.isCompleted()) {
            titleText += " (Completed)";
        }
        JTextField taskInfoTitle = new JTextField(titleText);
        taskInfoTitle.setBounds(27, 27, 527, 25);
        taskInfoTitle.setFont(taskInfoTitle.getFont().deriveFont(Font.BOLD, 12f));
        taskInfoTitle.setHorizontalAlignment(JTextField.CENTER);
        taskInfoTitle.setBackground(TITLE_COLOR);
        taskInfoTitle.setEditable(false);
        panel.add(taskInfoTitle);

        JTextField finishedTasks = new JTextField("Topics:");
        finishedTasks.setBounds(296, 201, 259, 25);
        finishedTasks.setFont(finishedTasks.getFont().deriveFont(Font.BOLD, 12f));
        finishedTasks.setHorizontalAlignment(JTextField.CENTER);
        finishedTasks.setBackground(TITLE_COLOR);
        finishedTasks.setEditable(false);
        panel.add(finishedTasks);

        JTextField descriptionTitle = new JTextField("Description:");
        descriptionTitle.setBounds(26, 76, 259, 25);
        descriptionTitle.setFont(descriptionTitle.getFont().deriveFont(Font.BOLD, 12f));
        descriptionTitle.setHorizontalAlignment(JTextField.CENTER);
        descriptionTitle.setBackground(TITLE_COLOR);
        descriptionTitle.setEditable(false);
        panel.add(descriptionTitle);

        JTextArea descriptionText = new JTextArea();
        descriptionText.setBackground(BACKGROUND_COLOR);
        descriptionText.setText(thisTask.getDescription());
        descriptionText.setWrapStyleWord(true);
        descriptionText.setLineWrap(true);
        descriptionText.setEditable(false);

        JScrollPane descriptionScrollPane = new JScrollPane(descriptionText);
        descriptionScrollPane.setBounds(26,102,259, 214);
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(descriptionScrollPane);

        JTextField memberTitle = new JTextField("Members:");
        memberTitle.setBounds(296, 76, 259, 25);
        memberTitle.setFont(memberTitle.getFont().deriveFont(Font.BOLD, 12f));
        memberTitle.setHorizontalAlignment(JTextField.CENTER);
        memberTitle.setBackground(TITLE_COLOR);
        memberTitle.setEditable(false);
        panel.add(memberTitle);

        JPanel membersBox = new JPanel();
        membersBox.setLayout(new BoxLayout(membersBox, BoxLayout.Y_AXIS));
        membersBox.setBounds(0,0,259 , 90);
        membersBox.setBackground(BACKGROUND_COLOR);

        List<JButton> listButtons = new ArrayList<>();
        for (int i = 0; i < thisTask.getMembers().size(); i++) {
            listButtons.add(new JButton(thisTask.getMembers().get(i).getName()));
            listButtons.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listButtons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton thisButton = (JButton) e.getSource();
                    for (int i = 0; i < thisTask.getMembers().size(); i++) {
                        if (thisTask.getMembers().get(i).getName().equals(thisButton.getText())) {
                            FinalMemberInfo newMemberDisplay = new FinalMemberInfo(thisTask.getMembers().get(i));
                        }
                    }
                }
            });
            membersBox.add(listButtons.get(i));
        }

        JScrollPane membersScrollPane = new JScrollPane(membersBox);
        membersScrollPane.setBounds(296,101,259, 90);
        membersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(membersScrollPane);

        JPanel topicBox = new JPanel();
        topicBox.setLayout(new BoxLayout(topicBox, BoxLayout.Y_AXIS));
        topicBox.setBounds(0,0,259 , 90);
        topicBox.setBackground(BACKGROUND_COLOR);

        List<JButton> listTopicButtons = new ArrayList<>();
        for (int i = 0; i < thisTask.getTopics().size(); i++) {
            listTopicButtons.add(new JButton(thisTask.getTopics().get(i).getTitle()));
            listTopicButtons.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listTopicButtons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton thisButton = (JButton) e.getSource();
                    for (int i = 0; i < thisTask.getTopics().size(); i++) {
                        if (thisTask.getTopics().get(i).getTitle().equals(thisButton.getText())) {
                            TopicInfo newTopicDisplay = new TopicInfo(thisTask.getTopics().get(i));
                        }
                    }
                }
            });
            topicBox.add(listTopicButtons.get(i));
        }

        JScrollPane topicScrollPane = new JScrollPane(topicBox);
        topicScrollPane.setBounds(296, 226, 259, 90);
        topicScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(topicScrollPane);

        currentWindow.setTitle("Task Data");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,400);
        currentWindow.setVisible(true);

    };

}

