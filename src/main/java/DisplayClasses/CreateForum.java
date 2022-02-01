package DisplayClasses;
import DisplayDrawingFunctions.ForumsDrawingFunction;
import StructuralClasses.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class CreateForum {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    CreateForum(Project workingProject){
        JFrame currentWindow = new JFrame();
        ForumsDrawingFunction panel = new ForumsDrawingFunction();
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

        JTextField enterTitle = new JTextField("Enter Title:");
        enterTitle.setBounds(27, 27, 68, 25);
        enterTitle.setFont(enterTitle.getFont().deriveFont(Font.BOLD, 12f));
        enterTitle.setBackground(TITLE_COLOR);
        enterTitle.setEditable(false);
        panel.add(enterTitle);

        JTextField writeTitle = new JTextField();
        writeTitle.setBounds(95, 27, 457, 25);
        writeTitle.setBackground(BACKGROUND_COLOR);
        writeTitle.setEditable(true);
        panel.add(writeTitle);

        JTextField enterMessage = new JTextField("Write Message:");
        enterMessage.setBounds(27, 76, 97, 27);
        enterMessage.setFont(enterMessage.getFont().deriveFont(Font.BOLD, 12f));
        enterMessage.setBackground(TITLE_COLOR);
        enterMessage.setEditable(false);
        panel.add(enterMessage);

        JTextArea writeMessage = new JTextArea();
        writeMessage.setLineWrap(true);
        writeMessage.setWrapStyleWord(true);
        writeMessage.setEditable(true);
        writeMessage.setBackground(BACKGROUND_COLOR);

        JScrollPane scroll = new JScrollPane(writeMessage);
        scroll.setBounds(27, 103, 363, 212);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        writeMessage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");
        scroll.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");
        panel.add(scroll);

        JTextField relatedTopics = new JTextField("Select Related Topics:");
        relatedTopics.setBounds(410, 76, 145, 27);
        relatedTopics.setFont(relatedTopics.getFont().deriveFont(Font.BOLD, 12f));
        relatedTopics.setHorizontalAlignment(JTextField.CENTER);
        relatedTopics.setBackground(TITLE_COLOR);
        relatedTopics.setEditable(false);
        panel.add(relatedTopics);

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
        topicsScrollPane.setBounds(410,103,145, 212);
        topicsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(topicsScrollPane);

        JButton postButton = new JButton("Post");
        postButton.setBounds(250, 325, 100, 25);
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!writeTitle.getText().equals("") && !writeMessage.getText().equals("")) {
                    workingProject.createForumThread(writeTitle.getText(), writeMessage.getText(), workingProject.getLoggedIn());
                    for (int i = 0; i < listTopics.size(); i++) {
                        if (listTopics.get(i).isSelected()) {
                            workingProject.getForumThreads().get(workingProject.getForumThreads().size() - 1).addTopic(workingProject.getTopic(listTopics.get(i).getText()));
                            workingProject.getTopic(listTopics.get(i).getText()).addForumThread(workingProject.getForumThreads().get(workingProject.getForumThreads().size() - 1));
                        }
                    }
                    Dashboard newDashboard = new Dashboard(workingProject);
                    currentWindow.dispose();
                }
            }
        });
        panel.add(postButton);

        currentWindow.setTitle("Create a New Forum");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,400);
        currentWindow.setVisible(true);

    };

}

