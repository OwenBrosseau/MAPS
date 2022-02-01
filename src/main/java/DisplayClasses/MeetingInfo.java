package DisplayClasses;
import DisplayDrawingFunctions.MeetingInfoDrawingFunction;
import StructuralClasses.Meeting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MeetingInfo  {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    MeetingInfo(Meeting thisMeeting){
        JFrame currentWindow = new JFrame();
        MeetingInfoDrawingFunction panel = new MeetingInfoDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

        JTextField titleField = new JTextField("Meeting: " + thisMeeting.getTitle() + " (Happening on: " + sdf.format(thisMeeting.getDay()) + ")");
        titleField.setBounds(27, 27, 527, 25);
        titleField.setFont(titleField.getFont().deriveFont(Font.BOLD, 12f));
        titleField.setHorizontalAlignment(JTextField.CENTER);
        titleField.setBackground(TITLE_COLOR);
        titleField.setEditable(false);
        panel.add(titleField);

        JTextArea descriptionText = new JTextArea();
        descriptionText.setBackground(BACKGROUND_COLOR);
        descriptionText.setLineWrap(true);
        descriptionText.setWrapStyleWord(true);
        descriptionText.setEditable(false);
        descriptionText.setText(thisMeeting.getDescription());

        JScrollPane descriptionScrollPane = new JScrollPane(descriptionText);
        descriptionScrollPane.setBounds(26,102,259, 213);
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(descriptionScrollPane);

        JPanel membersBox = new JPanel();
        membersBox.setBounds(0,0,259 , 89);
        membersBox.setLayout(new BoxLayout(membersBox, BoxLayout.Y_AXIS));
        membersBox.setBackground(BACKGROUND_COLOR);

        List<JButton> listButtons = new ArrayList<>();
        for (int i = 0; i < thisMeeting.getMembers().size(); i++) {
            listButtons.add(new JButton(thisMeeting.getMembers().get(i).getName()));
            listButtons.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listButtons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton thisButton = (JButton) e.getSource();
                    for (int j = 0; j < thisMeeting.getMembers().size(); j++) {
                        if (thisMeeting.getMembers().get(j).getName().equals(thisButton.getText())) {
                            FinalMemberInfo newMemberInfo = new FinalMemberInfo(thisMeeting.getMembers().get(j));
                        }
                    }
                }
            });
            membersBox.add(listButtons.get(i));
        }

        JScrollPane membersScrollPane = new JScrollPane(membersBox);
        membersScrollPane.setBounds(296,101,259 , 89);
        membersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(membersScrollPane);

        JPanel topicsBox = new JPanel();
        topicsBox.setBounds(0,0,259,88);
        topicsBox.setLayout(new BoxLayout(topicsBox, BoxLayout.Y_AXIS));
        topicsBox.setBackground(BACKGROUND_COLOR);

        List<JButton> listTopics = new ArrayList<>();
        for (int i = 0; i < thisMeeting.getTopics().size(); i++) {
            listTopics.add(new JButton(thisMeeting.getTopics().get(i).getTitle()));
            listTopics.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listTopics.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton thisButton = (JButton) e.getSource();
                    for (int j = 0; j < thisMeeting.getTopics().size(); j++) {
                        if (thisMeeting.getTopics().get(j).getTitle().equals(thisButton.getText())) {
                            TopicInfo newTopicDisplay = new TopicInfo(thisMeeting.getTopics().get(j));
                        }
                    }
                }
            });
            topicsBox.add(listTopics.get(i));
        }

        JScrollPane topicsScrollPane = new JScrollPane(topicsBox);
        topicsScrollPane.setBounds(296,227,259 , 88);
        topicsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(topicsScrollPane);

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

        JTextField memberBox = new JTextField("Members:");
        memberBox.setBounds(296, 76, 259, 25);
        memberBox.setFont(memberBox.getFont().deriveFont(Font.BOLD, 12f));
        memberBox.setHorizontalAlignment(JTextField.CENTER);
        memberBox.setBackground(TITLE_COLOR);
        memberBox.setEditable(false);
        panel.add(memberBox);

        currentWindow.setTitle("Meeting Description");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,400);
        currentWindow.setVisible(true);

    };

    public static Date getDifferenceDay(Date d1, int diff) {
        long dateDiff = TimeUnit.MILLISECONDS.convert((long) diff, TimeUnit.DAYS) + d1.getTime();
        Date d2 = new Date(dateDiff);
        return d2;
    }

}

