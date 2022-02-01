package DisplayClasses;
import DisplayDrawingFunctions.PickTasksDrawingFunction;
import StructuralClasses.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class PickTask {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    PickTask(Project workingProject){
        JFrame currentWindow = new JFrame();
        PickTasksDrawingFunction panel = new PickTasksDrawingFunction();
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

        //listUnassigned
        JTextField unassignedTasks = new JTextField("Unassigned Tasks:");
        unassignedTasks.setBounds(100, 5, 115, 22);
        unassignedTasks.setFont(unassignedTasks.getFont().deriveFont(Font.BOLD, 12f));
        unassignedTasks.setHorizontalAlignment(JTextField.CENTER);
        unassignedTasks.setBackground(TITLE_COLOR);
        unassignedTasks.setEditable(false);
        panel.add(unassignedTasks);

        //listUnavailable
        JTextField notAvailableTasks = new JTextField("Claimed Tasks:");
        notAvailableTasks.setBounds(100, 162, 115, 22);
        notAvailableTasks.setFont(notAvailableTasks.getFont().deriveFont(Font.BOLD, 12f));
        notAvailableTasks.setHorizontalAlignment(JTextField.CENTER);
        notAvailableTasks.setBackground(TITLE_COLOR);
        notAvailableTasks.setEditable(false);
        panel.add(notAvailableTasks);

        //listClaimed
        JTextField assignedTasks = new JTextField("Your Tasks:");
        assignedTasks.setBounds(390, 5, 75, 22);
        assignedTasks.setFont(assignedTasks.getFont().deriveFont(Font.BOLD, 12f));
        assignedTasks.setHorizontalAlignment(JTextField.CENTER);
        assignedTasks.setBackground(TITLE_COLOR);
        assignedTasks.setEditable(false);
        panel.add(assignedTasks);

        JPanel assignedTasksPanel = new JPanel(null);
        assignedTasksPanel.setBounds(19, 189, 269, 127);
        assignedTasksPanel.setBackground(BACKGROUND_COLOR);
        panel.add(assignedTasksPanel);

        JPanel unassignedTasksPanel = new JPanel(null);
        unassignedTasksPanel.setBounds(19, 31, 269, 126);
        unassignedTasksPanel.setBackground(BACKGROUND_COLOR);
        panel.add(unassignedTasksPanel);

        JPanel myTasksPanel = new JPanel(null);
        myTasksPanel.setBounds(299, 31, 269, 284);
        myTasksPanel.setBackground(BACKGROUND_COLOR);
        panel.add(myTasksPanel);

        //creating radio buttons for unassigned tasks *******************************************************************************************************
        JPanel unassignedButtonsPanel = new JPanel();
        unassignedButtonsPanel.setLayout(new BoxLayout(unassignedButtonsPanel, BoxLayout.Y_AXIS));
        unassignedButtonsPanel.setBackground(BACKGROUND_COLOR);
        int numSelectableTasks = 0;
        for (int i = 0; i < workingProject.getUncompletedTasks().size(); i++) {
            if (workingProject.getUncompletedTasks().get(i).getMembers().size() == 0) {
                numSelectableTasks++;
            }
        }
        unassignedButtonsPanel.setBounds(0, 0, 269, numSelectableTasks*25 + 30);

        List<JRadioButton> listUnassigned = new ArrayList<>();
        List<JRadioButton> listUnavailable = new ArrayList<>();
        List<JRadioButton> listClaimed = new ArrayList<>();

        int spacingCounter = 0;
        for (int i = 0; i < workingProject.getUncompletedTasks().size(); i++) {
            if (!workingProject.getUncompletedTasks().get(i).isClaimed()) {
                listUnassigned.add(new JRadioButton(workingProject.getUncompletedTasks().get(i).getTitle()));
                listUnassigned.get(listUnassigned.size() - 1).setAlignmentX(Component.CENTER_ALIGNMENT);
                listUnassigned.get(listUnassigned.size() - 1).setBackground(BACKGROUND_COLOR);
                listUnassigned.get(listUnassigned.size() - 1).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JRadioButton thisButton = (JRadioButton) e.getSource();
                        for (int j = 0; j < listUnassigned.size(); j++) {
                            if (listUnassigned.get(j) != thisButton) {
                                listUnassigned.get(j).setSelected(false);
                            }
                        }
                        for (int j = 0; j < listUnavailable.size(); j++) {
                            if (listUnavailable.get(j) != thisButton) {
                                listUnavailable.get(j).setSelected(false);
                            }
                        }
                        for (int j = 0; j < listClaimed.size(); j++) {
                            if (listClaimed.get(j) != thisButton) {
                                listClaimed.get(j).setSelected(false);
                            }
                        }
                    }
                });
                unassignedButtonsPanel.add(listUnassigned.get(listUnassigned.size() - 1));
                spacingCounter++;
            }
        }

        JScrollPane unassignedButtonsScrollPane = new JScrollPane(unassignedButtonsPanel);
        unassignedButtonsScrollPane.setBounds(0, 0, 269, 126);
        unassignedButtonsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        unassignedTasksPanel.add(unassignedButtonsScrollPane);
        //*************************************************************************************************************************************************

        //creating radio buttons for unavailable tasks ****************************************************************************************************
        JPanel unavailableButtonsPanel = new JPanel();
        unavailableButtonsPanel.setLayout(new BoxLayout(unavailableButtonsPanel, BoxLayout.Y_AXIS));;
        unavailableButtonsPanel.setBackground(BACKGROUND_COLOR);
        numSelectableTasks = workingProject.getUncompletedTasks().size() - numSelectableTasks;
        unavailableButtonsPanel.setBounds(0, 0, 269, numSelectableTasks*25 + 30);

        spacingCounter = 0;
        for (int i = 0; i < workingProject.getUncompletedTasks().size(); i++) {
            if (workingProject.getUncompletedTasks().get(i).isClaimed()) {
                listUnavailable.add(new JRadioButton(workingProject.getUncompletedTasks().get(i).getTitle()));
                listUnavailable.get(listUnavailable.size() - 1).setAlignmentX(Component.CENTER_ALIGNMENT);
                listUnavailable.get(listUnavailable.size() - 1).setBackground(BACKGROUND_COLOR);
                listUnavailable.get(listUnavailable.size() - 1).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JRadioButton thisButton = (JRadioButton) e.getSource();
                        for (int j = 0; j < listUnavailable.size(); j++) {
                            if (listUnavailable.get(j) != thisButton) {
                                listUnavailable.get(j).setSelected(false);
                            }
                        }
                        for (int j = 0; j < listUnassigned.size(); j++) {
                            if (listUnassigned.get(j) != thisButton) {
                                listUnassigned.get(j).setSelected(false);
                            }
                        }
                        for (int j = 0; j < listClaimed.size(); j++) {
                            if (listClaimed.get(j) != thisButton) {
                                listClaimed.get(j).setSelected(false);
                            }
                        }
                    }
                });
                unavailableButtonsPanel.add(listUnavailable.get(listUnavailable.size() - 1));
                spacingCounter++;
            }
        }

        JScrollPane assignedButtonsScrollPane = new JScrollPane(unavailableButtonsPanel);
        assignedButtonsScrollPane.setBounds(0, 0, 269, 127);
        assignedButtonsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        assignedTasksPanel.add(assignedButtonsScrollPane);
        //*************************************************************************************************************************************************

        //creating radio buttons for your tasks ****************************************************************************************************
        JPanel claimedButtonsPanel = new JPanel();
        claimedButtonsPanel.setBackground(BACKGROUND_COLOR);

        claimedButtonsPanel.setBounds(0, 0, 269, workingProject.getLoggedIn().getCurrentTasks().size()*25 + 30);
        claimedButtonsPanel.setLayout(new BoxLayout(claimedButtonsPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < workingProject.getLoggedIn().getCurrentTasks().size(); i++) {
            listClaimed.add(new JRadioButton(workingProject.getLoggedIn().getCurrentTasks().get(i).getTitle()));
            listClaimed.get(listClaimed.size() - 1).setAlignmentX(Component.CENTER_ALIGNMENT);
            listClaimed.get(listClaimed.size() - 1).setBackground(BACKGROUND_COLOR);
            listClaimed.get(listClaimed.size() - 1).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JRadioButton thisButton = (JRadioButton) e.getSource();
                    for (int j = 0; j < listUnavailable.size(); j++) {
                        if (listUnavailable.get(j) != thisButton) {
                            listUnavailable.get(j).setSelected(false);
                        }
                    }
                    for (int j = 0; j < listUnassigned.size(); j++) {
                        if (listUnassigned.get(j) != thisButton) {
                            listUnassigned.get(j).setSelected(false);
                        }
                    }
                    for (int j = 0; j < listClaimed.size(); j++) {
                        if (listClaimed.get(j) != thisButton) {
                            listClaimed.get(j).setSelected(false);
                        }
                    }
                }
            });
            claimedButtonsPanel.add(listClaimed.get(listClaimed.size() - 1));
        }

        JScrollPane claimedButtonsScrollPane = new JScrollPane(claimedButtonsPanel);
        claimedButtonsScrollPane.setBounds(0, 0, 269, 284);
        claimedButtonsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        myTasksPanel.add(claimedButtonsScrollPane);

        JButton claimTaskButton = new JButton("Claim Task");
        claimTaskButton.setBounds(25, 325, 100, 25);
        claimTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listUnassigned.size(); i++) {
                    if (listUnassigned.get(i).isSelected()) {
                        workingProject.claimTask(workingProject.getUncompletedTask(listUnassigned.get(i).getText()));
                        workingProject.getUncompletedTask(listUnassigned.get(i).getText()).setClaimed(true);
                        Dashboard newDashboard = new Dashboard(workingProject);
                        currentWindow.dispose();
                    }
                }
                for (int i = 0; i < listUnavailable.size(); i++) {
                    if (listUnavailable.get(i).isSelected()) {
                        boolean alreadyClaimed = false;
                        for (int j = 0; j < listClaimed.size(); j++) {
                            if (listClaimed.get(j).getText().equals(listUnavailable.get(i).getText())) {
                                alreadyClaimed = true;
                            }
                        }
                        if (!alreadyClaimed) {
                            workingProject.claimTask(workingProject.getUncompletedTask(listUnavailable.get(i).getText()));
                            workingProject.getUncompletedTask(listUnavailable.get(i).getText()).setClaimed(true);
                            Dashboard newDashboard = new Dashboard(workingProject);
                            currentWindow.dispose();
                        }
                    }
                }
            }
        });
        panel.add(claimTaskButton);

        JButton viewTaskButton = new JButton("View Task");
        viewTaskButton.setBounds(150, 325, 100, 25);
        viewTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listUnassigned.size(); i++) {
                    if (listUnassigned.get(i).isSelected()) {
                        TaskInfo newTaskDisplay = new TaskInfo(workingProject.getUncompletedTask(listUnassigned.get(i).getText()));
                    }
                }
                for (int i = 0; i < listUnavailable.size(); i++) {
                    if (listUnavailable.get(i).isSelected()) {
                        TaskInfo newTaskDisplay = new TaskInfo(workingProject.getUncompletedTask(listUnavailable.get(i).getText()));
                    }
                }
                for (int i = 0; i < listClaimed.size(); i++) {
                    if (listClaimed.get(i).isSelected()) {
                        TaskInfo newTaskDisplay = new TaskInfo(workingProject.getUncompletedTask(listClaimed.get(i).getText()));
                    }
                }
            }
        });
        panel.add(viewTaskButton);

        JButton turnInTaskButton = new JButton("Turn In");
        turnInTaskButton.setBounds(305, 325, 100, 25);
        turnInTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listClaimed.size(); i++) {
                    if (listClaimed.get(i).isSelected()) {
                        workingProject.completeTask(listClaimed.get(i).getText());
                        Dashboard newDashboard = new Dashboard(workingProject);
                        currentWindow.dispose();
                    }
                }
            }
        });
        panel.add(turnInTaskButton);

        JButton helpButton = new JButton("Request Help");
        helpButton.setBounds(451, 325, 110, 25);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listClaimed.size(); i++) {
                    if (listClaimed.get(i).isSelected()) {
                        for (int j = 0; j < workingProject.getUncompletedTasks().size(); j++) {
                            if (listClaimed.get(i).getText().equals(workingProject.getUncompletedTasks().get(j).getTitle())) {
                                workingProject.getUncompletedTasks().get(j).requestHelp(workingProject.getMembers());
                            }
                        }
                        Dashboard newDashboard = new Dashboard(workingProject);
                        currentWindow.dispose();
                    }
                }
            }
        });
        panel.add(helpButton);

        currentWindow.setTitle("Tasks");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,400);
        currentWindow.setVisible(true);

    };

}

