package DisplayClasses;

import DisplayDrawingFunctions.MemberInfoDrawingFunction;
import StructuralClasses.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FinalMemberInfo {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    FinalMemberInfo(Member thisMember){
        JFrame currentWindow = new JFrame();
        MemberInfoDrawingFunction panel = new MemberInfoDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JTextField projectSummary = new JTextField("Member: " + thisMember.getName());
        projectSummary.setBounds(27, 15, 527, 25);
        projectSummary.setFont(projectSummary.getFont().deriveFont(Font.BOLD, 12f));
        projectSummary.setHorizontalAlignment(JTextField.CENTER);
        projectSummary.setBackground(TITLE_COLOR);
        projectSummary.setEditable(false);
        panel.add(projectSummary);

        JTextField finishedTasks = new JTextField("Finished Tasks:");
        finishedTasks.setBounds(19, 51, 269, 25);
        finishedTasks.setFont(finishedTasks.getFont().deriveFont(Font.BOLD, 12f));
        finishedTasks.setHorizontalAlignment(JTextField.CENTER);
        finishedTasks.setBackground(TITLE_COLOR);
        finishedTasks.setEditable(false);
        panel.add(finishedTasks);

        JTextField unfinishedTasks = new JTextField("Unfinished Tasks:");
        unfinishedTasks.setBounds(299, 51, 269, 25);
        unfinishedTasks.setFont(unfinishedTasks.getFont().deriveFont(Font.BOLD, 12f));
        unfinishedTasks.setHorizontalAlignment(JTextField.CENTER);
        unfinishedTasks.setBackground(TITLE_COLOR);
        unfinishedTasks.setEditable(false);
        panel.add(unfinishedTasks);

        List<JRadioButton> listCompleted = new ArrayList<>();
        List<JRadioButton> listCurrent = new ArrayList<>();

        JPanel finishedTaskList = new JPanel();
        finishedTaskList.setLayout(new BoxLayout(finishedTaskList, BoxLayout.Y_AXIS));
        finishedTaskList.setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < thisMember.getCompletedTasks().size(); i++) {
            listCompleted.add(new JRadioButton(thisMember.getCompletedTasks().get(i).getTitle()));
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
                }
            });
            finishedTaskList.add(listCompleted.get(i));
        }

        JScrollPane userScroll = new JScrollPane(finishedTaskList);
        userScroll.setBounds(19,76,269,258);
        userScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(userScroll);

        JPanel unfinishedTaskList = new JPanel();
        unfinishedTaskList.setLayout(new BoxLayout(unfinishedTaskList, BoxLayout.Y_AXIS));
        unfinishedTaskList.setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < thisMember.getCurrentTasks().size(); i++) {
            listCurrent.add(new JRadioButton(thisMember.getCurrentTasks().get(i).getTitle()));
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
                    }
                });
                unfinishedTaskList.add(listCurrent.get(i));
        }

        JScrollPane taskScroll = new JScrollPane(unfinishedTaskList);
        taskScroll.setBounds(299,76,269,258);
        taskScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(taskScroll);

        JButton viewButton = new JButton("View Task");
        viewButton.setBounds(250, 350, 100,25);
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listCurrent.size(); i++) {
                    if (listCurrent.get(i).isSelected()) {
                        TaskInfo newTaskDisplay = new TaskInfo(thisMember.getCurrentTasks().get(i));
                    }
                }
                for (int i = 0; i < listCompleted.size(); i++) {
                    if (listCompleted.get(i).isSelected()) {
                        TaskInfo newTaskDisplay = new TaskInfo(thisMember.getCompletedTasks().get(i));
                    }
                }
            }
        });
        panel.add(viewButton);

        currentWindow.setTitle("Member Data");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,425);
        currentWindow.setVisible(true);

    };

}

