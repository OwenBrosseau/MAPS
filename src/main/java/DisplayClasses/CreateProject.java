package DisplayClasses;
import DisplayDrawingFunctions.CreateProjectDrawingFunction;
import StructuralClasses.Project;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class CreateProject {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    CreateProject(){
        JFrame currentWindow = new JFrame();
        CreateProjectDrawingFunction panel = new CreateProjectDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JTextField enterTitle = new JTextField("Name of Project:");
        enterTitle.setBounds(27, 27, 100, 25);
        enterTitle.setFont(enterTitle.getFont().deriveFont(Font.BOLD, 12f));
        enterTitle.setBackground(TITLE_COLOR);
        enterTitle.setEditable(false);
        panel.add(enterTitle);

        JTextField writeTitle = new JTextField();
        writeTitle.setBounds(127, 27, 428, 25);
        writeTitle.setBackground(BACKGROUND_COLOR);
        writeTitle.setEditable(true);
        panel.add(writeTitle);

        JTextField newMember = new JTextField("Name of Project Manager:");
        newMember.setBounds(27, 62, 158, 25);
        newMember.setFont(newMember.getFont().deriveFont(Font.BOLD, 12f));
        newMember.setBackground(TITLE_COLOR);
        newMember.setEditable(false);
        panel.add(newMember);

        JTextField writeMember = new JTextField();
        writeMember.setBounds(185, 62, 370, 25);
        writeMember.setBackground(BACKGROUND_COLOR);
        writeMember.setEditable(true);
        panel.add(writeMember);

        JTextField descriptionText = new JTextField("Enter Description:");
        descriptionText.setBounds(26, 101, 373, 25);
        descriptionText.setFont(descriptionText.getFont().deriveFont(Font.BOLD, 12f));
        descriptionText.setHorizontalAlignment(JTextField.CENTER);
        descriptionText.setBackground(TITLE_COLOR);
        descriptionText.setEditable(false);
        panel.add(descriptionText);

        JTextField dateText = new JTextField("Select Due Date:");
        dateText.setBounds(415, 101, 140, 25);
        dateText.setFont(descriptionText.getFont().deriveFont(Font.BOLD, 12f));
        dateText.setHorizontalAlignment(JTextField.CENTER);
        dateText.setBackground(TITLE_COLOR);
        dateText.setEditable(false);
        panel.add(dateText);

        JTextArea writeDescription = new JTextArea();
        writeDescription.setBackground(BACKGROUND_COLOR);
        writeDescription.setWrapStyleWord(true);
        writeDescription.setLineWrap(true);
        writeDescription.setEditable(true);

        JScrollPane scroll = new JScrollPane(writeDescription);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(26, 126, 373, 192);

        writeDescription.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");
        scroll.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");
        panel.add(scroll);

        String datePattern = "dd/MM/yy";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
        UtilDateModel model = new UtilDateModel();
        Properties defaultProps = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, defaultProps);
        datePanel.setBounds(414,126,142 , 175);
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

        JButton createProjectButton = new JButton("Enter");
        createProjectButton.setBounds(255, 325, 90, 25);
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!writeTitle.getText().equals("") && !writeDescription.getText().equals("") && !writeMember.getText().equals("") && !datePicker.getJFormattedTextField().getText().equals("")) {
                    Project workingProject = null;
                    try {
                        workingProject = new Project(writeTitle.getText(), writeDescription.getText(), writeMember.getText(), dateFormatter.parse(datePicker.getJFormattedTextField().getText()));
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    Dashboard newDashboard = new Dashboard(workingProject);
                    currentWindow.dispose();
                }
            }
        });
        panel.add(createProjectButton);

        currentWindow.setTitle("Create a New Project");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,400);
        currentWindow.setVisible(true);

    };

    public static int getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

}


