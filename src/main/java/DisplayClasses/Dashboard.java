package DisplayClasses;

import DisplayDrawingFunctions.DashboardDrawingFunction;
import StructuralClasses.Project;
import StructuralClasses.Topic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Dashboard {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    public static final Color DAY_COLOR = new Color(232,178,153);

    Dashboard(Project workingProject){
        JFrame currentWindow = new JFrame();
        DashboardDrawingFunction panel = new DashboardDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int dayOfWeek = getDayOfWeek(workingProject.getCurrentDay());

        JPanel forumButtonsPanel = new JPanel();
        forumButtonsPanel.setBackground(BACKGROUND_COLOR);
        forumButtonsPanel.setLayout(new BoxLayout(forumButtonsPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < workingProject.getForumThreads().size(); i++)
        {
            JButton forumButton = new JButton(workingProject.getForumThreads().get(i).getForumTitle());
            forumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            forumButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton thisButton = (JButton) e.getSource();
                    ForumDisplay newForumDisplay = new ForumDisplay(workingProject.getForumThread(thisButton.getText()), workingProject.getLoggedIn());
                }
            });
            forumButtonsPanel.add(forumButton);
        }

        JScrollPane forumsScroll = new JScrollPane(forumButtonsPanel);
        forumsScroll.setBounds(763, 26, 180, 515);
        forumsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(forumsScroll);

        int buttonsInt = 0;
        JButton pickTaskButton = new JButton("Pick Tasks");
        pickTaskButton.setBounds(253, 316, 176,25);
        pickTaskButton.setEnabled(!workingProject.isCompleted());
        pickTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PickTask newTaskPickerDisplay = new PickTask(workingProject);
                currentWindow.dispose();
            }
        });
        panel.add(pickTaskButton);

        JButton createMeetingButton = new JButton("Create a New Meeting");
        createMeetingButton.setBounds(253, 346, 176,25);
        createMeetingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateMeeting newMeetingDisplay = new CreateMeeting(workingProject);
                currentWindow.dispose();
            }
        });
        panel.add(createMeetingButton);

        JButton createForumButton = new JButton("Create a New Forum");
        createForumButton.setBounds(253, 376, 176,25);
        createForumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateForum newForumDisplay = new CreateForum(workingProject);
                currentWindow.dispose();
            }
        });
        panel.add(createForumButton);

        boolean topicsButtonCreated = false;
        for (int i = 0; i < workingProject.getTopics().size(); i++) {
            if (workingProject.getLoggedIn() == workingProject.getTopics().get(i).getLead() && !topicsButtonCreated) {
                JButton progressButton = new JButton("Your Topics");
                buttonsInt++;
                progressButton.setBounds(253, 376 + buttonsInt * 30, 176,25);
                progressButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (Topic searchTopic : workingProject.getTopics()) {
                            if (searchTopic.getLead() == workingProject.getLoggedIn()) {
                                TopicInfo newTopicDisplay = new TopicInfo(searchTopic);
                            }
                        }
                    }
                });
                panel.add(progressButton);
                topicsButtonCreated = true;
            }
        }

        if (workingProject.getLoggedIn() == workingProject.getProjectManager()) {
            JButton createTaskButton = new JButton("Create a New Task");
            createTaskButton.setEnabled(!workingProject.isCompleted());
            buttonsInt++;
            createTaskButton.setBounds(253, 376 + buttonsInt * 30, 176, 25);
            createTaskButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CreateTasks newTaskDisplay = new CreateTasks(workingProject);
                    currentWindow.dispose();
                }
            });
            panel.add(createTaskButton);

            JButton projectManagerButton = new JButton("Project Manager Options");
            buttonsInt++;
            projectManagerButton.setBounds(253, 376 + buttonsInt * 30, 176,25);
            projectManagerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ProjectManager PMControls = new ProjectManager(workingProject);
                    currentWindow.dispose();
                }
            });
            panel.add(projectManagerButton);
        }

        JButton myInfoButton = new JButton("My To Do List");
        buttonsInt++;
        myInfoButton.setBounds(253, 376 + buttonsInt * 30, 176,25);
        myInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyMemberInfo myInfoDisplay = new MyMemberInfo(workingProject);
            }
        });
        panel.add(myInfoButton);

        JButton switchUserButton = new JButton("Switch User");
        switchUserButton.setBounds(533, 470, 176,25);
        switchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PickPerson logInDisplay = new PickPerson(workingProject);
                currentWindow.dispose();
            }
        });
        panel.add(switchUserButton);

        JButton saveButton = new JButton("Save Project Data");
        saveButton.setBounds(533, 500, 176,25);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workingProject.saveToFile();
            }
        });
        panel.add(saveButton);

        JTextField titleTextField = new JTextField(workingProject.getTitle());
        titleTextField.setFont(titleTextField.getFont().deriveFont(Font.BOLD, 16f));
        titleTextField.setBounds(204, 0, 555, 39);
        titleTextField.setHorizontalAlignment(JTextField.CENTER);
        titleTextField.setBackground(TITLE_COLOR);
        titleTextField.setEditable(false);
        panel.add(titleTextField);

        JTextField forumTitleText = new JTextField("Forums");
        forumTitleText.setFont(forumTitleText.getFont().deriveFont(Font.BOLD, 14f));
        forumTitleText.setBounds(763, 0, 197, 25);
        forumTitleText.setHorizontalAlignment(JTextField.CENTER);
        forumTitleText.setBackground(TITLE_COLOR);
        forumTitleText.setEditable(false);
        panel.add(forumTitleText);

        JTextField tasksToDo = new JTextField("To Do:");
        tasksToDo.setFont(tasksToDo.getFont().deriveFont(Font.BOLD, 14f));
        tasksToDo.setBounds(203, 43, 183, 25);
        tasksToDo.setHorizontalAlignment(JTextField.CENTER);
        tasksToDo.setBackground(TITLE_COLOR);
        tasksToDo.setEditable(false);
        panel.add(tasksToDo);

        JTextField tasksInProgress = new JTextField("In Progress:");
        tasksInProgress.setFont(tasksInProgress.getFont().deriveFont(Font.BOLD, 14f));
        tasksInProgress.setBounds(389, 43, 185, 25);
        tasksInProgress.setHorizontalAlignment(JTextField.CENTER);
        tasksInProgress.setBackground(TITLE_COLOR);
        tasksInProgress.setEditable(false);
        panel.add(tasksInProgress);

        JTextField tasksFinished = new JTextField("Finished:");
        tasksFinished.setFont(tasksFinished.getFont().deriveFont(Font.BOLD, 14f));
        tasksFinished.setBounds(577, 43, 183, 25);
        tasksFinished.setHorizontalAlignment(JTextField.CENTER);
        tasksFinished.setBackground(TITLE_COLOR);
        tasksFinished.setEditable(false);
        panel.add(tasksFinished);

        JTextField notifications = new JTextField("Notifications:");
        notifications.setFont(notifications.getFont().deriveFont(Font.BOLD, 14f));
        notifications.setBounds(203, 173, 557, 25);
        notifications.setHorizontalAlignment(JTextField.CENTER);
        notifications.setBackground(TITLE_COLOR);
        notifications.setEditable(false);
        panel.add(notifications);

        JTextField actions = new JTextField("Actions:");
        actions.setFont(actions.getFont().deriveFont(Font.BOLD, 14f));
        actions.setBounds(203, 273, 277, 25);
        actions.setHorizontalAlignment(JTextField.CENTER);
        actions.setBackground(TITLE_COLOR);
        actions.setEditable(false);
        panel.add(actions);

        JTextField suggestions = new JTextField("Suggestions:");
        suggestions.setFont(actions.getFont().deriveFont(Font.BOLD, 14f));
        suggestions.setBounds(483, 273, 277, 25);
        suggestions.setHorizontalAlignment(JTextField.CENTER);
        suggestions.setBackground(TITLE_COLOR);
        suggestions.setEditable(false);
        panel.add(suggestions);

        JTextField scheduleTitle = new JTextField("Weekly Schedule:");
        scheduleTitle.setFont(scheduleTitle.getFont().deriveFont(Font.BOLD, 14f));
        scheduleTitle.setBounds(0, 0, 200, 25);
        scheduleTitle.setHorizontalAlignment(JTextField.CENTER);
        scheduleTitle.setBackground(TITLE_COLOR);
        scheduleTitle.setEditable(false);
        panel.add(scheduleTitle);

        JTextField monday = new JTextField("Monday:");
        monday.setBounds(0, 28, 200, 25);
        if (dayOfWeek == 2) {
            monday.setBackground(DAY_COLOR);
        }
        else {
            monday.setBackground(TITLE_COLOR);
        }
        monday.setEditable(false);
        panel.add(monday);

        JTextField tuesday = new JTextField("Tuesday:");
        tuesday.setBounds(0, 128, 200, 25);
        if (dayOfWeek == 3) {
            tuesday.setBackground(DAY_COLOR);
        }
        else {
            tuesday.setBackground(TITLE_COLOR);
        }
        tuesday.setEditable(false);
        panel.add(tuesday);

        JTextField wednesday = new JTextField("Wednesday:");
        wednesday.setBounds(0, 231, 200, 25);
        if (dayOfWeek == 4) {
            wednesday.setBackground(DAY_COLOR);
        }
        else {
            wednesday.setBackground(TITLE_COLOR);
        }
        wednesday.setEditable(false);
        panel.add(wednesday);

        JTextField thursday = new JTextField("Thursday:");
        thursday.setBounds(0, 334, 200, 25);
        if (dayOfWeek == 5) {
            thursday.setBackground(DAY_COLOR);
        }
        else {
            thursday.setBackground(TITLE_COLOR);
        }
        thursday.setEditable(false);
        panel.add(thursday);

        JTextField friday = new JTextField("Friday:");
        friday.setBounds(0, 437, 200, 25);
        if (dayOfWeek == 6) {
            friday.setBackground(DAY_COLOR);
        }
        else {
            friday.setBackground(TITLE_COLOR);
        }
        friday.setEditable(false);
        panel.add(friday);

        JPanel mondayPanel = new JPanel();
        mondayPanel.setLayout(new BoxLayout(mondayPanel, BoxLayout.Y_AXIS));
        mondayPanel.setBackground(BACKGROUND_COLOR);

        if (getDifferenceDays(getRelativeDay(workingProject.getCurrentDay(), -dayOfWeek), workingProject.getDueDay()) == 2) {
            JLabel dueDayLabel = new JLabel("PROJECT DUE");
            dueDayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mondayPanel.add(dueDayLabel);
        }

        List<JButton> mondayMeetings = new ArrayList<>();
        for (int i = 0; i < workingProject.getLoggedIn().getMeetings().size(); i++) {
            if (getDifferenceDays(getRelativeDay(workingProject.getCurrentDay(), -dayOfWeek), workingProject.getLoggedIn().getMeetings().get(i).getDay()) == 2) {
                mondayMeetings.add(new JButton(workingProject.getLoggedIn().getMeetings().get(i).getTitle()));
                mondayMeetings.get(mondayMeetings.size() - 1).setAlignmentX(Component.CENTER_ALIGNMENT);
                mondayMeetings.get(mondayMeetings.size() - 1).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton thisButton = (JButton) e.getSource();
                        for (int i = 0; i < workingProject.getLoggedIn().getMeetings().size(); i++) {
                            if (workingProject.getLoggedIn().getMeetings().get(i).getTitle().equals(thisButton.getText())) {
                                MeetingInfo newMeetingDisplay = new MeetingInfo(workingProject.getLoggedIn().getMeetings().get(i));
                            }
                        }
                    }
                });
                mondayPanel.add(mondayMeetings.get(mondayMeetings.size() - 1));
            }
        }

        JScrollPane mondayScrollPanel = new JScrollPane(mondayPanel);
        mondayScrollPanel.setBounds(0, 51, 200, 77);
        mondayScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(mondayScrollPanel);

        JPanel tuesdayPanel = new JPanel();
        tuesdayPanel.setLayout(new BoxLayout(tuesdayPanel, BoxLayout.Y_AXIS));
        tuesdayPanel.setBackground(BACKGROUND_COLOR);

        if (getDifferenceDays(getRelativeDay(workingProject.getCurrentDay(), -dayOfWeek), workingProject.getDueDay()) == 3) {
            JLabel dueDayLabel = new JLabel("PROJECT DUE");
            dueDayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            tuesdayPanel.add(dueDayLabel);
        }

        List<JButton> tuesdayMeetings = new ArrayList<>();
        for (int i = 0; i < workingProject.getLoggedIn().getMeetings().size(); i++) {
            if (getDifferenceDays(getRelativeDay(workingProject.getCurrentDay(), -dayOfWeek), workingProject.getLoggedIn().getMeetings().get(i).getDay()) == 3) {
                tuesdayMeetings.add(new JButton(workingProject.getLoggedIn().getMeetings().get(i).getTitle()));
                tuesdayMeetings.get(tuesdayMeetings.size() - 1).setAlignmentX(Component.CENTER_ALIGNMENT);
                tuesdayMeetings.get(tuesdayMeetings.size() - 1).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton thisButton = (JButton) e.getSource();
                        for (int i = 0; i < workingProject.getLoggedIn().getMeetings().size(); i++) {
                            if (workingProject.getLoggedIn().getMeetings().get(i).getTitle().equals(thisButton.getText())) {
                                MeetingInfo newMeetingDisplay = new MeetingInfo(workingProject.getLoggedIn().getMeetings().get(i));
                            }
                        }
                    }
                });
                tuesdayPanel.add(tuesdayMeetings.get(tuesdayMeetings.size() - 1));
            }
        }

        JScrollPane tuesdayScrollPanel = new JScrollPane(tuesdayPanel);
        tuesdayScrollPanel.setBounds(0, 154, 200, 77);
        tuesdayScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(tuesdayScrollPanel);

        JPanel wednesdayPanel = new JPanel();
        wednesdayPanel.setLayout(new BoxLayout(wednesdayPanel, BoxLayout.Y_AXIS));
        wednesdayPanel.setBackground(BACKGROUND_COLOR);

        if (getDifferenceDays(getRelativeDay(workingProject.getCurrentDay(), -dayOfWeek), workingProject.getDueDay()) == 4) {
            JLabel dueDayLabel = new JLabel("PROJECT DUE");
            dueDayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            wednesdayPanel.add(dueDayLabel);
        }

        List<JButton> wednesdayMeetings = new ArrayList<>();
        for (int i = 0; i < workingProject.getLoggedIn().getMeetings().size(); i++) {
            if (getDifferenceDays(getRelativeDay(workingProject.getCurrentDay(), -dayOfWeek), workingProject.getLoggedIn().getMeetings().get(i).getDay()) == 4) {
                wednesdayMeetings.add(new JButton(workingProject.getLoggedIn().getMeetings().get(i).getTitle()));
                wednesdayMeetings.get(wednesdayMeetings.size() - 1).setAlignmentX(Component.CENTER_ALIGNMENT);
                wednesdayMeetings.get(wednesdayMeetings.size() - 1).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton thisButton = (JButton) e.getSource();
                        for (int i = 0; i < workingProject.getLoggedIn().getMeetings().size(); i++) {
                            if (workingProject.getLoggedIn().getMeetings().get(i).getTitle().equals(thisButton.getText())) {
                                MeetingInfo newMeetingDisplay = new MeetingInfo(workingProject.getLoggedIn().getMeetings().get(i));
                            }
                        }
                    }
                });
                wednesdayPanel.add(wednesdayMeetings.get(wednesdayMeetings.size() - 1));
            }
        }

        JScrollPane wednesdayScrollPanel = new JScrollPane(wednesdayPanel);
        wednesdayScrollPanel.setBounds(0, 257, 200, 77);
        wednesdayScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(wednesdayScrollPanel);

        JPanel thursdayPanel = new JPanel();
        thursdayPanel.setLayout(new BoxLayout(thursdayPanel, BoxLayout.Y_AXIS));
        thursdayPanel.setBackground(BACKGROUND_COLOR);

        if (getDifferenceDays(getRelativeDay(workingProject.getCurrentDay(), -dayOfWeek), workingProject.getDueDay()) == 5) {
            JLabel dueDayLabel = new JLabel("PROJECT DUE");
            dueDayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            thursdayPanel.add(dueDayLabel);
        }

        List<JButton> thursdayMeetings = new ArrayList<>();
        for (int i = 0; i < workingProject.getLoggedIn().getMeetings().size(); i++) {
            if (getDifferenceDays(getRelativeDay(workingProject.getCurrentDay(), -dayOfWeek), workingProject.getLoggedIn().getMeetings().get(i).getDay()) == 5) {
                thursdayMeetings.add(new JButton(workingProject.getLoggedIn().getMeetings().get(i).getTitle()));
                thursdayMeetings.get(thursdayMeetings.size() - 1).setAlignmentX(Component.CENTER_ALIGNMENT);
                thursdayMeetings.get(thursdayMeetings.size() - 1).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton thisButton = (JButton) e.getSource();
                        for (int i = 0; i < workingProject.getLoggedIn().getMeetings().size(); i++) {
                            if (workingProject.getLoggedIn().getMeetings().get(i).getTitle().equals(thisButton.getText())) {
                                MeetingInfo newMeetingDisplay = new MeetingInfo(workingProject.getLoggedIn().getMeetings().get(i));
                            }
                        }
                    }
                });
                thursdayPanel.add(thursdayMeetings.get(thursdayMeetings.size() - 1));
            }
        }

        JScrollPane thursdayScrollPanel = new JScrollPane(thursdayPanel);
        thursdayScrollPanel.setBounds(0, 360, 200, 77);
        thursdayScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(thursdayScrollPanel);

        JPanel fridayPanel = new JPanel();
        fridayPanel.setLayout(new BoxLayout(fridayPanel, BoxLayout.Y_AXIS));
        fridayPanel.setBackground(BACKGROUND_COLOR);

        if (getDifferenceDays(getRelativeDay(workingProject.getCurrentDay(), -dayOfWeek), workingProject.getDueDay()) == 6) {
            JLabel dueDayLabel = new JLabel("PROJECT DUE");
            dueDayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            fridayPanel.add(dueDayLabel);
        }

        List<JButton> fridayMeetings = new ArrayList<>();
        for (int i = 0; i < workingProject.getLoggedIn().getMeetings().size(); i++) {
            if (getDifferenceDays(getRelativeDay(workingProject.getCurrentDay(), -dayOfWeek), workingProject.getLoggedIn().getMeetings().get(i).getDay()) == 6) {
                fridayMeetings.add(new JButton(workingProject.getLoggedIn().getMeetings().get(i).getTitle()));
                fridayMeetings.get(fridayMeetings.size() - 1).setAlignmentX(Component.CENTER_ALIGNMENT);
                fridayMeetings.get(fridayMeetings.size() - 1).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton thisButton = (JButton) e.getSource();
                        for (int i = 0; i < workingProject.getLoggedIn().getMeetings().size(); i++) {
                            if (workingProject.getLoggedIn().getMeetings().get(i).getTitle().equals(thisButton.getText())) {
                                MeetingInfo newMeetingDisplay = new MeetingInfo(workingProject.getLoggedIn().getMeetings().get(i));
                            }
                        }
                    }
                });
                fridayPanel.add(fridayMeetings.get(fridayMeetings.size() - 1));
            }
        }

        JScrollPane fridayScrollPanel = new JScrollPane(fridayPanel);
        fridayScrollPanel.setBounds(0, 462, 200, 78);
        fridayScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(fridayScrollPanel);

        JPanel notificationBox = new JPanel();
        notificationBox.setLayout(new BoxLayout(notificationBox, BoxLayout.Y_AXIS));
        notificationBox.setBackground(BACKGROUND_COLOR);

        List<JButton> listNotifications = new ArrayList<>();
        for (int i = 0; i < workingProject.getLoggedIn().getNotifications().size(); i++) {
            listNotifications.add(new JButton((i + 1) + ". " + workingProject.getLoggedIn().getNotifications().get(i).getMessage()));
            listNotifications.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listNotifications.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton thisButton = (JButton) e.getSource();
                    for (int i = 0; i < workingProject.getLoggedIn().getNotifications().size(); i++) {
                        if (((i + 1) + ". " + workingProject.getLoggedIn().getNotifications().get(i).getMessage()).equals(thisButton.getText())) {
                            NotificationInfo newNotificationDisplay = new NotificationInfo(workingProject.getLoggedIn().getNotifications().get(i), workingProject);
                        }
                    }
                }
            });
            notificationBox.add(listNotifications.get(i));
        }

        JScrollPane notificationScrollPanel = new JScrollPane(notificationBox);
        notificationScrollPanel.setBounds(204,198,556,72);
        notificationScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(notificationScrollPanel);

        JTextArea suggestionText = new JTextArea();
        suggestionText.setBackground(BACKGROUND_COLOR);
        suggestionText.setLineWrap(true);
        suggestionText.setWrapStyleWord(true);
        suggestionText.setEditable(false);
        suggestionText.setText("Project description: " + workingProject.getDescription() + "\r\n" + workingProject.generateSuggestion());

        JScrollPane suggestionScrollPane = new JScrollPane(suggestionText);
        suggestionScrollPane.setBounds(483, 298, 277, 153);
        suggestionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(suggestionScrollPane);

        JPanel toDoBox = new JPanel();
        toDoBox.setBounds(0,0,182, 102);
        toDoBox.setLayout(new BoxLayout(toDoBox, BoxLayout.Y_AXIS));
        toDoBox.setBackground(BACKGROUND_COLOR);

        List<JButton> listToDo = new ArrayList<>();
        for (int i = 0; i < workingProject.getUncompletedTasks().size(); i++) {
            if (!workingProject.getUncompletedTasks().get(i).isClaimed()) {
                listToDo.add(new JButton(workingProject.getUncompletedTasks().get(i).getTitle()));
                listToDo.get(listToDo.size() - 1).setAlignmentX(Component.CENTER_ALIGNMENT);
                listToDo.get(listToDo.size() - 1).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton thisButton = (JButton) e.getSource();
                        for (int i = 0; i < workingProject.getUncompletedTasks().size(); i++) {
                            if (workingProject.getUncompletedTasks().get(i).getTitle().equals(thisButton.getText())) {
                                TaskInfo newTaskDisplay = new TaskInfo(workingProject.getUncompletedTasks().get(i));
                            }
                        }
                    }
                });
                toDoBox.add(listToDo.get(listToDo.size() - 1));
            }
        }

        JScrollPane toDoScrollPanel = new JScrollPane(toDoBox);
        toDoScrollPanel.setBounds(204, 68, 182, 102);
        toDoScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(toDoScrollPanel);

        JPanel inProgressBox = new JPanel();
        inProgressBox.setBounds(0,0,184, 102);
        inProgressBox.setLayout(new BoxLayout(inProgressBox, BoxLayout.Y_AXIS));
        inProgressBox.setBackground(BACKGROUND_COLOR);

        List<JButton> listInProgress = new ArrayList<>();
        for (int i = 0; i < workingProject.getUncompletedTasks().size(); i++) {
            if (workingProject.getUncompletedTasks().get(i).isClaimed()) {
                listInProgress.add(new JButton(workingProject.getUncompletedTasks().get(i).getTitle()));
                listInProgress.get(listInProgress.size() - 1).setAlignmentX(Component.CENTER_ALIGNMENT);
                listInProgress.get(listInProgress.size() - 1).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton thisButton = (JButton) e.getSource();
                        for (int i = 0; i < workingProject.getUncompletedTasks().size(); i++) {
                            if (workingProject.getUncompletedTasks().get(i).getTitle().equals(thisButton.getText())) {
                                TaskInfo newTaskDisplay = new TaskInfo(workingProject.getUncompletedTasks().get(i));
                            }
                        }
                    }
                });
                inProgressBox.add(listInProgress.get(listInProgress.size() - 1));
            }
        }

        JScrollPane inProgressScrollPanel = new JScrollPane(inProgressBox);
        inProgressScrollPanel.setBounds(390, 68, 184, 102);
        inProgressScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(inProgressScrollPanel);

        JPanel completedBox = new JPanel();
        completedBox.setBounds(0,0,183, 102);
        completedBox.setLayout(new BoxLayout(completedBox, BoxLayout.Y_AXIS));
        completedBox.setBackground(BACKGROUND_COLOR);

        List<JButton> listCompleted = new ArrayList<>();
        for (int i = 0; i < workingProject.getCompletedTasks().size(); i++) {
            listCompleted.add(new JButton(workingProject.getCompletedTasks().get(i).getTitle()));
            listCompleted.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listCompleted.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton thisButton = (JButton) e.getSource();
                    for (int i = 0; i < workingProject.getCompletedTasks().size(); i++) {
                        if (workingProject.getCompletedTasks().get(i).getTitle().equals(thisButton.getText())) {
                            TaskInfo newTaskDisplay = new TaskInfo(workingProject.getCompletedTasks().get(i));
                        }
                    }
                }
            });
            completedBox.add(listCompleted.get(i));
        }

        JScrollPane completedScrollPanel = new JScrollPane(completedBox);
        completedScrollPanel.setBounds(577, 68, 183, 102);
        completedScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(completedScrollPanel);

        currentWindow.setResizable(false);
        currentWindow.setTitle("MAPS: Management and Productivity Streamliner");
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(960,579);
        currentWindow.setVisible(true);

    }

    public static int getDayOfWeek(Date d1) {
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static Date getRelativeDay(Date d1, int days) {
        return new Date(d1.getTime() + TimeUnit.MILLISECONDS.convert(days, TimeUnit.DAYS));
    }
}
