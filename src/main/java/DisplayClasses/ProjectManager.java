package DisplayClasses;
import DisplayDrawingFunctions.ProjectManagerDrawingFunction;
import StructuralClasses.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class ProjectManager {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    ProjectManager(Project workingProject){
        JFrame currentWindow = new JFrame();
        ProjectManagerDrawingFunction panel = new ProjectManagerDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Dashboard newDashboard = new Dashboard(workingProject);
                currentWindow.dispose();
            }
        });
        currentWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


        JTextField enterTitle = new JTextField("Create a New Topic:");
        enterTitle.setBounds(27, 27, 120, 25);
        enterTitle.setFont(enterTitle.getFont().deriveFont(Font.BOLD, 12f));
        enterTitle.setBackground(TITLE_COLOR);
        enterTitle.setEditable(false);
        panel.add(enterTitle);

        JTextField writeTitle = new JTextField();
        writeTitle.setBounds(145, 27, 320, 25);
        writeTitle.setBackground(BACKGROUND_COLOR);
        writeTitle.setEditable(true);
        panel.add(writeTitle);

        JTextField newMember = new JTextField("Add Name of New Member:");
        newMember.setBounds(27, 62, 160, 25);
        newMember.setFont(newMember.getFont().deriveFont(Font.BOLD, 12f));
        newMember.setBackground(TITLE_COLOR);
        newMember.setEditable(false);
        panel.add(newMember);

        JTextField writeMember = new JTextField();
        writeMember.setBounds(185, 62, 280, 25);
        writeMember.setBackground(BACKGROUND_COLOR);
        writeMember.setEditable(true);
        panel.add(writeMember);

        JTextField pickLead = new JTextField("Pick a Topic Lead:");
        pickLead.setBounds(19, 101, 269, 25);
        pickLead.setFont(pickLead.getFont().deriveFont(Font.BOLD, 12f));
        pickLead.setHorizontalAlignment(JTextField.CENTER);
        pickLead.setBackground(TITLE_COLOR);
        pickLead.setEditable(false);
        panel.add(pickLead);

        JTextField deleteProject = new JTextField("Complete Project?");
        deleteProject.setBounds(299, 151, 269, 25);
        deleteProject.setFont(deleteProject.getFont().deriveFont(Font.BOLD, 12f));
        deleteProject.setHorizontalAlignment(JTextField.CENTER);
        deleteProject.setBackground(TITLE_COLOR);
        deleteProject.setEditable(false);
        panel.add(deleteProject);

        JTextField warningMessage = new JTextField("The Data Will No Longer Be Editable ");
        warningMessage.setBounds(299, 275, 269, 25);
        //warningMessage.setFont(warningMessage.getFont().deriveFont(Font.BOLD, 12f));
        warningMessage.setHorizontalAlignment(JTextField.CENTER);
        warningMessage.setBackground(BACKGROUND_COLOR);
        warningMessage.setEditable(false);
        panel.add(warningMessage);

        JButton overviewButton = new JButton("Project Overview");
        overviewButton.setBounds(355, 235, 150, 25);
        overviewButton.setEnabled(workingProject.isCompleted());
        overviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FinalProjData newFinalDataDisplay = new FinalProjData(workingProject);
            }
        });
        panel.add(overviewButton);

        JButton completeButton = new JButton("COMPLETE");
        completeButton.setBounds(380, 195, 100, 25);
        completeButton.setEnabled(!workingProject.isCompleted() && workingProject.getUncompletedTasks().size() == 0);
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workingProject.setCompleted();
                completeButton.setEnabled(false);
                overviewButton.setEnabled(true);
            }
        });
        panel.add(completeButton);

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

        JButton addMemberButton = new JButton("Add");
        addMemberButton.setBounds(465, 61, 90, 25);
        addMemberButton.setEnabled(!workingProject.isCompleted());
        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!writeMember.getText().equals("")) {
                    workingProject.addMember(writeMember.getText());
                    writeMember.setText("Member Added");
                    listButtons.add(new JRadioButton(workingProject.getMembers().get(workingProject.getMembers().size() - 1).getName()));
                    listButtons.get(workingProject.getMembers().size() - 1).setBackground(BACKGROUND_COLOR);
                    listButtons.get(workingProject.getMembers().size() - 1).setAlignmentX(Component.CENTER_ALIGNMENT);
                    listButtons.get(workingProject.getMembers().size() - 1).addActionListener(new ActionListener() {
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
                    membersBox.add(listButtons.get(workingProject.getMembers().size() - 1));
                }
            }
        });
        panel.add(addMemberButton);

        JButton createTopicButton = new JButton("Add");
        createTopicButton.setBounds(465, 27, 90, 25);
        createTopicButton.setEnabled(!workingProject.isCompleted());
        createTopicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!writeTitle.getText().equals("")) {
                    for (int i = 0; i < listButtons.size(); i++) {
                        if (listButtons.get(i).isSelected()) {
                            workingProject.createTopic(writeTitle.getText(), workingProject.getMembers().get(i));
                            writeTitle.setText("Topic Added");
                            return;
                        }
                    }
                    writeTitle.setText("Please select a topic lead");
                }
            }
        });
        panel.add(createTopicButton);

        JButton doneButton = new JButton("Done");
        doneButton.setBounds(380, 320, 100, 25);
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard newDashboard = new Dashboard(workingProject);
                currentWindow.dispose();
            }
        });
        panel.add(doneButton);

        JButton announcementButton = new JButton("Create Announcement");
        announcementButton.setBounds(340, 106, 180, 25);
        announcementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAnnouncement newAnnouncement = new CreateAnnouncement(workingProject);
            }
        });
        panel.add(announcementButton);

        JScrollPane membersScrollPane = new JScrollPane(membersBox);
        membersScrollPane.setBounds(19,126,269 , 213);
        membersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(membersScrollPane);

        currentWindow.setTitle("Project Manager Options");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,400);
        currentWindow.setVisible(true);

    };
}