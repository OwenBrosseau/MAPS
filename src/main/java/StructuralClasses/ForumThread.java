package StructuralClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ForumThread {
    String forumTitle;
    Member forumCreator;
    ArrayList<Topic> topics;
    ArrayList<String> posts;
    ArrayList<Member> authors;

    //Base constructor
    ForumThread(String title, String firstMessage, Member creator) {
        forumTitle = title;
        forumCreator = creator;
        topics = new ArrayList<>();
        posts = new ArrayList<>();
        authors = new ArrayList<>();
        addPost(firstMessage, creator);
    }

    //Rebuilding constroctor for opening from file
    ForumThread(String path, String titleInput, List<Topic> listTopics, List<Member> listMembers) {
        topics = new ArrayList<>();
        posts = new ArrayList<>();
        authors = new ArrayList<>();
        forumTitle = titleInput;
        try {
            File dir = new File (path);
            File file = new File (dir, forumTitle + ".txt");
            Scanner myReader = new Scanner(file);
            String tempString = myReader.nextLine();
            for (Member member : listMembers) {
                if (tempString.equals(member.getName())) {
                    forumCreator = member;
                    break;
                }
            }
            int numTopics = Integer.parseInt(myReader.nextLine());
            for (int i = 0; i < numTopics; i++) {
                String temp = myReader.nextLine();
                for (Topic listTopic : listTopics) {
                    if (listTopic.getTitle().equals(temp)) {
                        listTopic.addForumThread(this);
                        topics.add(listTopic);
                        break;
                    }
                }
            }
            int numPosts = Integer.parseInt(myReader.nextLine());
            for (int i = 0; i < numPosts; i++) {
                String content = myReader.nextLine();
                tempString = myReader.nextLine();
                for (Member listMember : listMembers) {
                    if (tempString.equals(listMember.getName())) {
                        addPost(content, listMember);
                        break;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Member> getAuthors() {
        return authors;
    }

    public ArrayList<String> getPosts() {
        return posts;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public Member getForumCreator() {
        return forumCreator;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void addPost(String content, Member author) {
        posts.add(content);
        authors.add(author);
    }

    void deletePost(int index) {
        posts.remove(index);
        authors.remove(index);
    }

    public void addTopic(Topic newTopic) {
        topics.add(newTopic);
        newTopic.getLead().addNotification(new Notification("A new forum thread has been opened concerning a topic you may be able to help with!", this));
    }

    //Creating the string used to save the data to file
    String saveToFile() {
        String data = forumCreator.getName() + "\r\n" + topics.size() + "\r\n";
        for (Topic topic : topics) {
            data += topic.getTitle() + "\r\n";
        }
        data += posts.size() + "\r\n";
        for (int i = 0; i < posts.size(); i++) {
            data += posts.get(i) + "\r\n";
            data += authors.get(i).getName();
            if (i < posts.size() - 1) {
                data += "\r\n";
            }
        }
        return data;
    }
}
