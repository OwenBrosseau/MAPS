package DisplayClasses;
import DisplayDrawingFunctions.MeetingsDrawingFunction;
import StructuralClasses.Meeting;
import StructuralClasses.Notification;
import StructuralClasses.Project;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateMeeting {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    CreateMeeting(Project workingProject){
        JFrame currentWindow = new JFrame();
        MeetingsDrawingFunction panel = new MeetingsDrawingFunction();
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

        String datePattern = "dd/MM/yy";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
        UtilDateModel model = new UtilDateModel();
        Properties defaultProps = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, defaultProps);
        datePanel.setBounds(415,211,141 , 175);
        panel.add(datePanel);
        JFormattedTextField.AbstractFormatter aFormatter = new JFormattedTextField.AbstractFormatter() {
            @Override
            public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }
            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }
                return "";
            }
        };
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, aFormatter);
        panel.add(datePicker);

        JTextField enterTitle = new JTextField("Enter Title:");
        enterTitle.setBounds(27, 27, 68, 25);
        enterTitle.setFont(enterTitle.getFont().deriveFont(Font.BOLD, 12f));
        enterTitle.setBackground(TITLE_COLOR);
        enterTitle.setEditable(false);
        panel.add(enterTitle);

        JTextField enterDescription = new JTextField("Enter Description:");
        enterDescription.setBounds(262, 77, 110, 25);
        enterDescription.setFont(enterDescription.getFont().deriveFont(Font.BOLD, 12f));
        enterDescription.setBackground(TITLE_COLOR);
        enterDescription.setEditable(false);
        panel.add(enterDescription);

        JTextField selectMembers = new JTextField("Select Members:");
        selectMembers.setBounds(27, 77, 105, 25);
        selectMembers.setFont(selectMembers.getFont().deriveFont(Font.BOLD, 12f));
        selectMembers.setBackground(TITLE_COLOR);
        selectMembers.setEditable(false);
        panel.add(selectMembers);

        JTextField relatedTopics = new JTextField("Select Related Topics:");
        relatedTopics.setBounds(262, 187, 143, 25);
        relatedTopics.setFont(relatedTopics.getFont().deriveFont(Font.BOLD, 12f));
        relatedTopics.setHorizontalAlignment(JTextField.CENTER);
        relatedTopics.setBackground(TITLE_COLOR);
        relatedTopics.setEditable(false);
        panel.add(relatedTopics);

        JTextField selectDay = new JTextField("Select Day:");
        selectDay.setBounds(417, 187, 138, 25);
        selectDay.setFont(selectDay.getFont().deriveFont(Font.BOLD, 12f));
        selectDay.setHorizontalAlignment(JTextField.CENTER);
        selectDay.setBackground(TITLE_COLOR);
        selectDay.setEditable(false);
        panel.add(selectDay);

        JPanel membersBox = new JPanel();
        membersBox.setLayout(new BoxLayout(membersBox, BoxLayout.Y_AXIS));
        membersBox.setBackground(BACKGROUND_COLOR);

        List<JRadioButton> listButtons = new ArrayList<>();
        for (int i = 0; i < workingProject.getMembers().size(); i++) {
            listButtons.add(new JRadioButton(workingProject.getMembers().get(i).getName()));
            listButtons.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listButtons.get(i).setBackground(BACKGROUND_COLOR);
            membersBox.add(listButtons.get(i));
        }

        JScrollPane membersScrollPane = new JScrollPane(membersBox);
        membersScrollPane.setBounds(26,103,224, 322);
        membersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(membersScrollPane);

        JPanel topicsBox = new JPanel();
        topicsBox.setLayout(new BoxLayout(topicsBox, BoxLayout.Y_AXIS));
        topicsBox.setBackground(BACKGROUND_COLOR);

        List<JRadioButton> listTopics = new ArrayList<>();
        for (int i = 0; i < workingProject.getTopics().size(); i++) {
            listTopics.add(new JRadioButton(workingProject.getTopics().get(i).getTitle()));
            listTopics.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            listTopics.get(i).setBackground(BACKGROUND_COLOR);
            topicsBox.add(listTopics.get(i));
        }

        JScrollPane topicsScrollPane = new JScrollPane(topicsBox);
        topicsScrollPane.setBounds(261,212,145, 213);
        topicsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(topicsScrollPane);

        JTextArea writeDescription = new JTextArea();
        writeDescription.setLineWrap(true);
        writeDescription.setWrapStyleWord(true);
        writeDescription.setEditable(true);
        writeDescription.setBackground(BACKGROUND_COLOR);

        JScrollPane scroll = new JScrollPane(writeDescription);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(262, 103, 292, 70);
        writeDescription.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");
        scroll.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");
        panel.add(scroll);

        JTextField writeTitle = new JTextField();
        writeTitle.setBounds(95, 27, 457, 25);
        writeTitle.setBackground(BACKGROUND_COLOR);
        writeTitle.setEditable(true);
        panel.add(writeTitle);

        JButton CreateButton = new JButton("Create");
        CreateButton.setBounds(435, 395, 100, 25);
        CreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!writeTitle.getText().equals("") && !writeDescription.getText().equals("") && !datePicker.getJFormattedTextField().getText().equals("")) {
                    try {
                        if (getDifferenceDays(workingProject.getStartDay(), dateFormatter.parse(datePicker.getJFormattedTextField().getText())) >= 0) {
                            Meeting newMeeting = new Meeting(writeTitle.getText(), writeDescription.getText(), dateFormatter.parse(datePicker.getJFormattedTextField().getText()));
                            workingProject.getMeetings().add(newMeeting);
                            for (int i = 0; i < listButtons.size(); i++) {
                                if (listButtons.get(i).isSelected()) {
                                    workingProject.getMeetings().get(workingProject.getMeetings().size() - 1).addMember(workingProject.getMember(listButtons.get(i).getText()));
                                    workingProject.getMember(listButtons.get(i).getText()).addMeeting(workingProject.getMeetings().get(workingProject.getMeetings().size() - 1));
                                    workingProject.getMember(listButtons.get(i).getText()).addNotification(new Notification("You've been asked to attend a new meeting: " + workingProject.getMeetings().get(workingProject.getMeetings().size() - 1).getTitle() + ", on " + dateFormatter.format(workingProject.getMeetings().get(workingProject.getMeetings().size() - 1).getDay()), workingProject.getMeetings().get(workingProject.getMeetings().size() - 1)));
                                }
                            }
                            for (int i = 0; i < listTopics.size(); i++) {
                                if (listTopics.get(i).isSelected()) {
                                    workingProject.getMeetings().get(workingProject.getMeetings().size() - 1).addTopic(workingProject.getTopic(listTopics.get(i).getText()));
                                    workingProject.getTopic(listTopics.get(i).getText()).addMeeting(workingProject.getMeetings().get(workingProject.getMeetings().size() - 1));
                                }
                            }
                            Dashboard newDashboard = new Dashboard(workingProject);
                            currentWindow.dispose();
                        }
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }
            }
        });
        panel.add(CreateButton);

        currentWindow.setTitle("Create a New Meeting");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,475);
        currentWindow.setVisible(true);

    };
    public static int getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}

