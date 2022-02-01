package DisplayClasses;
import DisplayDrawingFunctions.CreateAnnouncementDrawingFunction;
import StructuralClasses.Member;
import StructuralClasses.Notification;
import StructuralClasses.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class CreateAnnouncement {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    CreateAnnouncement(Project workingProject){
        JFrame currentWindow = new JFrame();
        CreateAnnouncementDrawingFunction panel = new CreateAnnouncementDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextField enterMessage = new JTextField("Write Message:");
        enterMessage.setBounds(27, 27, 97, 25);
        enterMessage.setFont(enterMessage.getFont().deriveFont(Font.BOLD, 12f));
        enterMessage.setBackground(TITLE_COLOR);
        enterMessage.setEditable(false);
        panel.add(enterMessage);

        JTextArea writeMessage = new JTextArea();
        writeMessage.setBackground(BACKGROUND_COLOR);
        writeMessage.setLineWrap(true);
        writeMessage.setWrapStyleWord(true);
        writeMessage.setEditable(true);

        JScrollPane scroll = new JScrollPane(writeMessage);
        scroll.setBounds(27, 52, 528, 261);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        writeMessage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");
        scroll.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");
        panel.add(scroll);

        JButton postButton = new JButton("Post");
        postButton.setBounds(250, 325, 100, 25);
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Member thisMember : workingProject.getMembers()) {
                    if (thisMember != workingProject.getLoggedIn()) {
                        thisMember.addNotification(new Notification(writeMessage.getText()));
                    }
                }
                currentWindow.dispose();
            }
        });
        panel.add(postButton);

        currentWindow.setTitle("Create an Announcement");
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(600,400);
        currentWindow.setVisible(true);

    };

}

