package DisplayClasses;
import DisplayDrawingFunctions.ForumDisplayDrawingFunction;
import StructuralClasses.ForumThread;
import StructuralClasses.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ForumDisplay {
    public static final Color BACKGROUND_COLOR = new Color(194,246,255);
    public static final Color TITLE_COLOR = new Color(131,217,232);
    ForumDisplay(ForumThread thisThread, Member loggedIn){
        JFrame currentWindow = new JFrame();
        ForumDisplayDrawingFunction panel = new ForumDisplayDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextField forumTitle = new JTextField(thisThread.getForumTitle());
        forumTitle.setFont(forumTitle.getFont().deriveFont(Font.BOLD, 14f));
        forumTitle.setBounds(25, 15, 435, 25);
        forumTitle.setHorizontalAlignment(JTextField.CENTER);
        forumTitle.setBackground(TITLE_COLOR);
        forumTitle.setEditable(false);
        panel.add(forumTitle);

        JTextField writeText = new JTextField("Write Message Here");
        writeText.setBounds(25, 550, 335, 25);
        writeText.setBackground(BACKGROUND_COLOR);
        writeText.setEditable(true);
        panel.add(writeText);

        JTextArea pastMessages = new JTextArea();
        pastMessages.setBackground(BACKGROUND_COLOR);
        pastMessages.setEditable(false);
        pastMessages.setLineWrap(true);
        pastMessages.setWrapStyleWord(true);
        String messages = "";
        for (int i = 0; i < thisThread.getPosts().size(); i++) {
            if (i > 0 && thisThread.getAuthors().get(i).getName().equals(thisThread.getAuthors().get(i-1).getName())) {
                messages += thisThread.getPosts().get(i) + "\r\n";
            }
            else {
                messages += thisThread.getAuthors().get(i).getName() + ":\r\n" + thisThread.getPosts().get(i) + "\r\n";
            }
        }
        pastMessages.setText(messages);

        JScrollPane messagesScrollPane = new JScrollPane(pastMessages);
        messagesScrollPane.setBounds(26, 56, 434, 470);
        messagesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(messagesScrollPane);

        JButton sendButton = new JButton("Send");
        sendButton.setBounds(360, 549, 100,25);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!writeText.getText().equals("")) {
                    thisThread.addPost(writeText.getText(), loggedIn);
                    String messages = "";
                    for (int i = 0; i < thisThread.getPosts().size(); i++) {
                        if (i > 0 && thisThread.getAuthors().get(i).getName().equals(thisThread.getAuthors().get(i - 1).getName())) {
                            messages += thisThread.getPosts().get(i) + "\r\n";
                        } else {
                            messages += thisThread.getAuthors().get(i).getName() + ":\r\n" + thisThread.getPosts().get(i) + "\r\n";
                        }
                    }
                    pastMessages.setText(messages);
                }
            }
        });
        panel.add(sendButton);


        currentWindow.setTitle(thisThread.getForumTitle());
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(500,650);
        currentWindow.setVisible(true);

    }
    ForumDisplay(ForumThread thisThread){
        JFrame currentWindow = new JFrame();
        ForumDisplayDrawingFunction panel = new ForumDisplayDrawingFunction();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        currentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextField forumTitle = new JTextField(thisThread.getForumTitle());
        forumTitle.setFont(forumTitle.getFont().deriveFont(Font.BOLD, 14f));
        forumTitle.setBounds(25, 15, 435, 25);
        forumTitle.setHorizontalAlignment(JTextField.CENTER);
        forumTitle.setBackground(TITLE_COLOR);
        forumTitle.setEditable(false);
        panel.add(forumTitle);

        JTextArea pastMessages = new JTextArea();
        pastMessages.setBackground(BACKGROUND_COLOR);
        pastMessages.setEditable(true);
        pastMessages.setLineWrap(true);
        pastMessages.setWrapStyleWord(true);
        String messages = "";
        for (int i = 0; i < thisThread.getPosts().size(); i++) {
            if (i > 0 && thisThread.getAuthors().get(i).getName().equals(thisThread.getAuthors().get(i-1).getName())) {
                messages += thisThread.getPosts().get(i) + "\r\n";
            }
            else {
                messages += thisThread.getAuthors().get(i).getName() + ":\r\n" + thisThread.getPosts().get(i) + "\r\n";
            }
        }
        pastMessages.setText(messages);

        JScrollPane buttonsScrollPane = new JScrollPane(pastMessages);
        buttonsScrollPane.setBounds(26, 56, 434, 470);
        buttonsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(buttonsScrollPane);

        currentWindow.setTitle(thisThread.getForumTitle()); //change to forum title
        currentWindow.setResizable(false);
        currentWindow.add(panel);
        currentWindow.pack();
        currentWindow.setSize(500,600);
        currentWindow.setVisible(true);

    };
}

