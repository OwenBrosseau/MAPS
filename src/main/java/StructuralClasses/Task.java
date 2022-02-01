package StructuralClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task extends Event {
    boolean completed;
    boolean claimed;

    //Base constructor
    Task(String newTitle, String newDescription) {
        completed = false;
        topics = new ArrayList<>();
        members = new ArrayList<>();
        title = newTitle;
        description = newDescription;
        claimed = false;
    }

    //Rebuilding constroctor for opening from file
    Task(String path, String inputTitle, ArrayList<Topic> listSections, ArrayList<Member> listMembers) {
        title = inputTitle;
        topics = new ArrayList<>();
        members = new ArrayList<>();
        try {
            File dir = new File (path);
            File file = new File (dir, title + ".txt");
            Scanner myReader = new Scanner(file);
            completed = Boolean.parseBoolean(myReader.nextLine());
            description = myReader.nextLine();
            claimed = Boolean.parseBoolean(myReader.nextLine());
            int temp = Integer.parseInt(myReader.nextLine());
            for (int i = 0; i < temp; i++) {
                String tempString = myReader.nextLine();
                for (Topic listSection : listSections) {
                    if (tempString.equals(listSection.getTitle())) {
                        topics.add(listSection);
                        listSection.addTask(this);
                        break;
                    }
                }
            }
            temp = Integer.parseInt(myReader.nextLine());
            for (int i = 0; i < temp; i++) {
                String tempString = myReader.nextLine();
                for (Member listMember : listMembers) {
                    if (tempString.equals(listMember.getName())) {
                        members.add(listMember);
                        listMember.addTask(this);
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

    public boolean isCompleted() {
        return completed;
    }

    public boolean isClaimed() {
        return claimed;
    }

    void setCompleted(boolean newStatus) {
        completed = newStatus;
    }

    public void setClaimed(boolean newStatus) {
        claimed = newStatus;
    }

    void addMember(Member newMember) {
        members.add(newMember);
        newMember.addTask(this);
        claimed = true;
    }

    public void requestHelp(ArrayList<Member> allMembers) {
        for (Member notHelping : allMembers) {
            boolean found = false;
            for (Member helping : members) {
                if (notHelping == helping) {
                    found = true;
                }
            }
            if (!found) {
                notHelping.addNotification(new Notification("Please see if you're able to help with this task: ", this));
            }
        }
    }

    public void addTopic(Topic newTopic) {
        topics.add(newTopic);
        newTopic.getLead().addNotification(new Notification("A new task has been created concerning a topic you may be able to help with!", this));
    }

    //Creating the string used to save the data to file
    String saveToFile() {
        String data = completed + "\r\n" + description + "\r\n" + claimed + "\r\n" + topics.size() + "\r\n";
        for (Topic topic : topics) {
            data += topic.getTitle() + "\r\n";
        }
        data += members.size() + "\r\n";
        for (Member member : members) {
            data += member.getName() + "\r\n";
        }
        return data;
    }
}
